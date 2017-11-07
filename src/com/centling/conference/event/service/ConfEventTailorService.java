package com.centling.conference.event.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.assignment.DAO.ConfAssignmentDAO;
import com.centling.conference.assignment.DAO.ConfVAssignmentDAO;
import com.centling.conference.assignment.entity.ConfAssignment;
import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.emailtemplate.DAO.ConfEmailTemplateDAO;
import com.centling.conference.event.DAO.ConfEventDAO;
import com.centling.conference.event.DAO.ConfEventUserDAO;
import com.centling.conference.event.entity.ConfEvent;
import com.centling.conference.event.entity.ConfEventUser;
import com.centling.conference.meeting.DAO.ConfMeetingDAO;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.sms.DAO.ConfSmsTemplateDAO;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.SMService;

@Service("confEventTailorService")
public class ConfEventTailorService {
    private static final Logger log = LoggerFactory.getLogger(ConfAssignmentDAO.class);
    
    //初期化
    public Pagination retrive (PageBean pageBean, String meetingId) {
        Pagination pagination = new Pagination();
        pagination.setRows(confEventDAO.retrieve(pageBean, meetingId));
        pagination.setTotal(confEventDAO.count(meetingId));
        return pagination; 
    }
    
    //新增事件
    public void save(ConfEvent instance){
        instance.setUpdateDate(DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        confEventDAO.save(instance);
    }
    
    //更新事件信息
    public void update(ConfEvent instance) {
        instance.setUpdateDate(DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
        confEventDAO.merge(instance);
    }
    
    //删除事件
    public int delete(String equipIds, String meetingId) {
          ConfEventUserDAO.deleteByEventId(equipIds);
          return confEventDAO.deleteByIds(equipIds);
    }
    
    //查找出可参与定制事件的嘉宾
    public List<Map> findAssignUserByUserType(String meetingId, ConfGuest user) {
        String assignUserTypes = Constant.USER_TYPE_VIP + ","
                + Constant.USER_TYPE_VIP_US + "," 
                + Constant.USER_TYPE_SPEAKER + "," 
                + Constant.USER_TYPE_SPEAKER_EN + "," 
                + Constant.USER_TYPE_ATTEND + "," 
                + Constant.USER_TYPE_ATTEND_EN + "," 
                + Constant.USER_TYPE_AUDIENCE + "," 
                + Constant.USER_TYPE_AUDIENCE_EN + "," 
                + Constant.USER_TYPE_MEDIA + "," 
                + Constant.USER_TYPE_MEDIA_EN + "," 
                + Constant.USER_TYPE_EXHIBITORS + "," 
                + Constant.USER_TYPE_EXHIBITORS_EN + "," 
                + Constant.USER_TYPE_REGIST_MEDIA + "," 
                + Constant.USER_TYPE_REGIST_MEDIA_EN + "," 
                + Constant.USER_TYPE_SPOUSE + "," 
                + Constant.USER_TYPE_SPOUSE_EN + "," 
                + Constant.ENTOURAGE_SPOUSE + "," 
                + Constant.ENTOURAGE_FOLLOWER;
        return confEventDAO.findUserByUserType(meetingId, assignUserTypes, user);
    }
    
    //查找已经选择该事件的嘉宾
    public String findSelectedUserByEventId (String eventId) {
        List<Map> list = ConfEventUserDAO.findSelectedUserByEventId(eventId);
        String userIds = "";
        if (list != null && list.size() > 0) {
            userIds = (String)list.get(0).get("selectedIds");
        }
        return userIds;
    } 
    
    //保存选择事件的指定人员
    public void saveEventUser(String eventId, String userIds) {
        log.info("删除选择事件的指定人员信息");
        ConfEventUserDAO.deleteByEventId(eventId);
        
        ConfEventUser eventUser = new ConfEventUser();
        String[] ids = userIds.split(",");
        for (int i = 0;i < ids.length;i++) {
            eventUser.setEventId(eventId);
            eventUser.setUserId(ids[i]);
            eventUser.setUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            ConfEventUserDAO.merge(eventUser);
        }
        log.info("新增或者修改事件的人员指定信息");
    }
    
    //删除选择事件的指定人员
    public void deleteEventUser(String eventId) throws Exception {
        log.info("删除选择事件的指定人员信息");
        ConfEventUserDAO.deleteByEventId(eventId);
    }
    
    //查找定制事件的嘉宾人数
    public String queryUserCount (String eventId) {
        return ConfEventUserDAO.findSelectedUserCount(eventId);
    }
    
    
    //给选中事件的嘉宾发送邮件或者短信提醒
    public void sendRemind (String eventId, String meetingId) throws Exception {
        List<ConfEventUser> list = ConfEventUserDAO.findByProperty("eventId", eventId);
        ConfEvent event = confEventDAO.findById(eventId);
        List<ConfMeeting> meeting = confMeetingDAO.findByProperty("id", meetingId);
        for (int i=0;i<list.size();i++) {
            String userId = list.get(i).getUserId();
            ConfUser user = confUserDAO.findById(userId);
            List<ConfMeetingUser> meetingList = confMeetingUserDAO.findByProperty("userId", user.getId());
            if (event.getType().equals("4")) {
                sendEmail(user, meetingList.get(0), meeting.get(0), event);
                sendSMS(user, meetingList.get(0), meeting.get(0), event);
            } else if (event.getType().equals("2")) {
                sendEmail(user, meetingList.get(0), meeting.get(0), event);
            } else if (event.getType().equals("3")) {
                sendSMS(user, meetingList.get(0), meeting.get(0), event);
            }
            
            //一对一指派人员发送信息
            if (event.getType().equals("2") || event.getType().equals("3") || event.getType().equals("4")) {
                List<ConfAssignment> staff = confAssignmentDAO.findByGuestId(userId);
                if (staff != null && staff.size() > 0) {
                    List<Map> staffList = confVAssignmentDAO.findAssignedUsers(meetingId, userId);
                    if (staffList != null && staffList.size() > 0) {
                        sendEmailForStaff(staffList, meetingList.get(0), meeting.get(0), event);
                        sendSMSForStaff(staffList, meetingList.get(0), meeting.get(0), event);
                    }
                }
            }
        }
    }
    
    //发送邮件
    private void sendEmail(ConfUser user, ConfMeetingUser meetingUser, ConfMeeting meeting, ConfEvent event) throws Exception {
        if (user.getEmail() != null && !user.getEmail().equals("")) {
            MailSenderService mailSenderService = MailSenderService.getInstance();
            // 获取邮件模板
            List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_EVENT_REMIND, meetingUser.getUserType());
            
            if (templateList != null && !templateList.isEmpty()) {
                Email email = new Email();
                email.setTo(new String[] { user.getEmail() });
                email.setSubject(templateList.get(0)[0].toString());
                mailSenderService.setTemplateName(templateList.get(0)[1].toString());
                 
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("user", user.getCname());
                model.put("Ename", user.getEname());
                model.put("eventName", event.getName());
                model.put("location", event.getLocation());
                model.put("startTime", event.getStartTime());
                model.put("endTime", event.getEndTime());
                model.put("remark", event.getRemark());
                model.put("remarkEn", event.getRemarkEn());
                model.put("meetingName", meeting.getName());
                model.put("meetingNameEn", meeting.getNameEn());

                Calendar ca = Calendar.getInstance();
                model.put("year", ca.get(Calendar.YEAR));
                model.put("month", ca.get(Calendar.MONTH) + 1);
                model.put("day", ca.get(Calendar.DATE));
                mailSenderService.sendMail(email, model);
                
                log.info("事件提醒邮件发送成功！"+user.getCname() + ";用户邮箱："+ user.getEmail());
            }
        }
    }
    
    //发送短信
    private void sendSMS(ConfUser user, ConfMeetingUser meetingUser, ConfMeeting meeting, ConfEvent event) throws Exception {
        if(user.getMobile() != null && !user.getMobile().equals("") && user.getMobile().length() == 11){
            List<String> templates = confSmsTemplateDAO.findByUserTypeAndName(Constant.SMS_TYPE_EVENT_REMIND, meetingUser.getUserType());
            String msg = "";
            if (templates != null && !templates.isEmpty()) {
                String content = templates.get(0);
                msg = content.replace("${user}", user.getCname())
                        .replace("${Ename}", user.getEname())
                        .replace("${eventName}", event.getName())
                        .replace("${location}", event.getLocation())
                        .replace("${startTime}", event.getStartTime())
                        .replace("${endTime}", event.getEndTime())
                        .replace("${meetingName}", meeting.getName())
                        .replace("${remark}", event.getRemark());
            }else{
                msg = "尊敬的" + user.getCname() + "(" + user.getEname() + ")，组委会将在" + event.getStartTime()
                        + "至" + event.getEndTime() + "，在"+ event.getLocation() +"举行"
                        + event.getName() + "," + event.getRemark() + "。【 组委会】";
            }
            SMService.sendSMS(Constant.SMSURL, msg , user.getMobile());
            log.info("事件提醒短信发送成功！"+user.getCname() + " 手机号："+ user.getMobile() + ";用户ID"+ user.getId());
        }
    }
    
    //发送邮件(一对一指派人员)
    private void sendEmailForStaff(List<Map> staffList, ConfMeetingUser meetingUser, ConfMeeting meeting, ConfEvent event) throws Exception {
        if (staffList != null && staffList.size() > 0) {
            Map map = staffList.get(0);
            if (map.get("assignEmail") != null && !map.get("assignEmail").equals("")) {
                MailSenderService mailSenderService = MailSenderService.getInstance();
                // 获取邮件模板
                List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_EVENT_REMIND, meetingUser.getUserType());
                
                if (templateList != null && !templateList.isEmpty()) {
                    Email email = new Email();
                    email.setTo(new String[] { (String)map.get("assignEmail") });
                    email.setSubject(templateList.get(0)[0].toString());
                    mailSenderService.setTemplateName(templateList.get(0)[1].toString());
                    
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("user", map.get("assignName"));
                    model.put("Cname", map.get("cname"));
                    model.put("Ename", map.get("ename"));
                    model.put("eventName", event.getName());
                    model.put("location", event.getLocation());
                    model.put("startTime", event.getStartTime());
                    model.put("endTime", event.getEndTime());
                    model.put("remark", event.getRemark());
                    model.put("remarkEn", event.getRemarkEn());
                    model.put("meetingName", meeting.getName());
                    model.put("meetingNameEn", meeting.getNameEn());
                    
                    Calendar ca = Calendar.getInstance();
                    model.put("year", ca.get(Calendar.YEAR));
                    model.put("month", ca.get(Calendar.MONTH) + 1);
                    model.put("day", ca.get(Calendar.DATE));
                    mailSenderService.sendMail(email, model);
                    
                    log.info("事件提醒邮件发送成功！"+map.get("assignName") + ";用户邮箱："+ map.get("assignEmail"));
                }
            }
        }
    }
    
    //发送短信(一对一指派人员)
    private void sendSMSForStaff(List<Map> staffList, ConfMeetingUser meetingUser, ConfMeeting meeting, ConfEvent event) throws Exception {
        if (staffList != null && staffList.size() > 0) {
            Map<String, String> map = staffList.get(0);
            String mobileNum = map.get("mobile");
            if(mobileNum != null && !mobileNum.equals("") && mobileNum.length() == 11){
                List<String> templates = confSmsTemplateDAO.findByUserTypeAndName(Constant.SMS_TYPE_EVENT_REMIND, meetingUser.getUserType());
                String msg = "";
                if (templates != null && !templates.isEmpty()) {
                    String content = templates.get(0);
                    msg = content.replace("${user}", map.get("assignName"))
                            .replace("${Cname}", map.get("cname"))
                            .replace("${Ename}", map.get("ename"))
                            .replace("${eventName}", event.getName())
                            .replace("${location}", event.getLocation())
                            .replace("${startTime}", event.getStartTime())
                            .replace("${endTime}", event.getEndTime())
                            .replace("${meetingName}", meeting.getName())
                            .replace("${remark}", event.getRemark());
                }else{
                    msg = "亲爱的" + map.get("assignName") + "，在" + meeting.getName() + "会议中您负责接待的"+ map.get("cname") 
                            +"(" + map.get("ename") + ")参与组委会在" + event.getStartTime()
                            + "至" + event.getEndTime() + "，在"+ event.getLocation() +"举行"
                            + event.getName() + "活动。请根据日程安排好接待工作。【 组委会】";
                }
                SMService.sendSMS(Constant.SMSURL, msg , mobileNum);
                log.info("事件提醒短信发送成功！"+map.get("assignName") + " 手机号："+ mobileNum + ";用户ID"+ map.get("assignId"));
            }
        }
    }
    
    //获取角色下的用户列表
    public List<Map> getUserByEventId(String meetingId, String eventId) {
        return ConfEventUserDAO.findUserByEventId(meetingId, eventId);
    }
    
    @Resource
    private ConfEventDAO confEventDAO;
    @Resource
    private ConfEventUserDAO ConfEventUserDAO;
    @Resource
    private ConfUserDAO confUserDAO;
    @Resource 
    ConfEmailTemplateDAO confEmailTemplateDAO;
    @Resource
    ConfSmsTemplateDAO confSmsTemplateDAO;
    @Resource
    ConfMeetingUserDAO confMeetingUserDAO;
    @Resource
    ConfMeetingDAO confMeetingDAO;
    @Resource
    ConfAssignmentDAO confAssignmentDAO;
    @Resource
    ConfVAssignmentDAO confVAssignmentDAO;
}
