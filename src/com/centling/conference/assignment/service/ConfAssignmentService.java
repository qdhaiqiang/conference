package com.centling.conference.assignment.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.assignment.DAO.ConfAssignmentDAO;
import com.centling.conference.assignment.DAO.ConfAssignmentOtherDAO;
import com.centling.conference.assignment.DAO.ConfVAssignmentDAO;
import com.centling.conference.assignment.entity.ConfAssignment;
import com.centling.conference.assignment.entity.ConfAssignmentOther;
import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.emailtemplate.DAO.ConfEmailTemplateDAO;
import com.centling.conference.meeting.DAO.ConfMeetingDAO;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.sms.DAO.ConfSmsTemplateDAO;
import com.centling.conference.sysuser.DAO.ConfSysUserDAO;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.SMService;

@Repository("confAssignmentService")
public class ConfAssignmentService {
    private static final Logger log = LoggerFactory.getLogger(ConfAssignmentDAO.class);
    //页面初始化数据取得
    public Pagination retrieve(PageBean pageBean, String meetingId, ConfGuest guest) {
        Pagination pagination = new Pagination();
        if (selectedVal(guest.getUserType()).equals("")) {
            guest.setUserType("");
        }
        List<Map> list = confVAssignmentDAO.retrive(pageBean, meetingId, guest);
        pagination.setRows(list);
        pagination.setTotal(confVAssignmentDAO.count(meetingId, guest));
        return pagination;
    }
    
    //页面初始化数据取得
    public Pagination retrieveOther(PageBean pageBean, String meetingId) {
        Pagination pagination = new Pagination();
        List<Map> list = confVAssignmentDAO.retriveOther(pageBean, meetingId);
        pagination.setRows(list);
        pagination.setTotal(confVAssignmentDAO.countOther(meetingId));
        return pagination;
    }
    
    //根据用户类型取得可指派人员列表
    //otherFlag
    public List<Map> findAssignUserByUserType(String meetingId, ConfGuest user) {
        String assignUserTypes = Constant.USER_TYPE_ORG + ","
                + Constant.USER_TYPE_ORG_EN + "," 
                + Constant.USER_TYPE_VOLUNTEER + "," 
                + Constant.USER_TYPE_VOLUNTEER_EN + "," 
                + Constant.USER_TYPE_SUPPLIER + "," 
                + Constant.USER_TYPE_SUPPLIER_EN;
        return confSysUserDAO.findUserByUserType(meetingId, assignUserTypes, user);
        
    }
    
    //根据指定会场查找该会场人员
    public Pagination findAssignedUserByScheduleId(PageBean pageBean, String scheduleId, String meetingId, ConfGuest user) {
    	List<ConfAssignmentOther> confAssignmentOther = confAssignmentOtherDAO.findByScheduleId(scheduleId);
    	String userIds = null;
    	if (confAssignmentOther.size() > 0) {
    		userIds = confAssignmentOther.get(0).getUserId();
    	}
    	Pagination pagination = new Pagination();
    	List<Map> list =  confUserDAO.findUsersById(pageBean, userIds, meetingId, user);
		pagination.setRows(list);
		pagination.setTotal(confUserDAO.count(meetingId, userIds, user));
		return pagination;
    }
    
    //根据嘉宾用户ID获取其指派人员
    public List<Map> findAssignedUserList(String meetingId, String guestId) {
        return confVAssignmentDAO.findAssignedUsers (meetingId, guestId);
            
    }
    
    //判断选择型检索条件是否有被选中
    public String selectedVal(String val){
        if (val == null || val.equals("") || val.length() == 0) {
            return "";
        } else {
            if (val.startsWith(",")){
                return val.substring(1, val.length());
            } else if (val.equals(",")) {
                return "";
            } else {
                return val;
            }
        }
    }
    
    //保存嘉宾的人员指派信息(一对一)
    public void saveAssignUser(String meetingId, String guestId, ConfGuest user, String guestInformWays, String staffInformWays) throws Exception {
        confAssignmentDAO.deleteByGuestId(meetingId, guestId);
        log.info("删除嘉宾的人员指派信息");
        
        ConfAssignment instance = new ConfAssignment();
        instance.setGuestId(guestId);
        instance.setMeetingId(meetingId);
        instance.setUserId(user.getUserId());
        instance.setUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        sendSMS(user, guestId, meetingId, guestInformWays, staffInformWays);
        confAssignmentDAO.save(instance);
        log.info("新增或者修改嘉宾的人员指派信息");
    }
    
    //删除嘉宾的人员指派信息(一对一)
    public int deleteAssignUser(String meetingId, String guestId) throws Exception {
        log.info("删除嘉宾的人员指派信息");
        return confAssignmentDAO.deleteByGuestId(meetingId, guestId);
    }
    
    //保存日程的人员指派信息（多对多）
    public void saveOtherAssignUser(String meetingId, String scheduleId, String assignUserIds, String assignUserNames) {
        confAssignmentOtherDAO.deleteByScheduleId(meetingId, scheduleId);
        log.info("删除选择日程的人员指派信息");
        ConfAssignmentOther instance = new ConfAssignmentOther();
        instance.setScheduleId(scheduleId);
        instance.setMeetingId(meetingId);
        instance.setUserId(assignUserIds);
        instance.setUserName(assignUserNames);
        instance.setUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        
        confAssignmentOtherDAO.save(instance);
        log.info("新增或者修改日程的人员指派信息");
    }
    
    //删除日程的人员指派信息(其他指派)
    public int deleteOtherAssignUser(String meetingId, String scheduleId) throws Exception {
        log.info("删除嘉宾的人员指派信息");
        return confAssignmentOtherDAO.deleteByScheduleId(meetingId, scheduleId);
    }
    
    public void sendSMS(ConfGuest user, String guestId, String meetingId, String guestInformWays, String staffInformWays) throws Exception {
        ConfMeeting meeting = confMeetingDAO.findById(meetingId);
        List<ConfMeetingUser> guestMeetUser = confMeetingUserDAO.findByUserId(guestId);
        ConfUser guest = confUserDAO.findById(guestId);
        //工作人员发邮件
        if(staffInformWays.indexOf(String.valueOf('1')) > -1){
            if (user.getEmail() != null && !user.getEmail().equals("")) {
                MailSenderService mailSenderService = MailSenderService.getInstance();
                // 获取邮件模板
                List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_ASSIGN_USER_EMAIL,user.getUserType());
                
                if (templateList != null && !templateList.isEmpty()) {
                    Email email = new Email();
                    email.setTo(new String[] { user.getEmail() });
                    email.setSubject(templateList.get(0)[0].toString());
                    mailSenderService.setTemplateName(templateList.get(0)[1].toString());
                     
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("user", user.getCname());
                    model.put("guestCname", guest.getCname());
                    model.put("guestEname", guest.getEname());
                    model.put("mobile", guest.getMobile());
                    model.put("email", guest.getEmail());               
    
                    Calendar ca = Calendar.getInstance();
                    model.put("year", ca.get(Calendar.YEAR));
                    model.put("month", ca.get(Calendar.MONTH) + 1);
                    model.put("day", ca.get(Calendar.DATE));
                    mailSenderService.sendMail(email, model);
                    
                    log.info("发送邮件成功！"+user.getCname() + ";用户邮箱："+ user.getEmail());
                }
    
            }
        }
        
        //工作人员发短信
        if(staffInformWays.indexOf(String.valueOf('2')) > -1){
            if(user.getMobile() != null && !user.getMobile().equals("") && user.getMobile().length() == 11){
                List<String> templates = confSmsTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_ASSIGN_USER_MSG, user.getUserType());
                String msg = "";
                if (templates != null && !templates.isEmpty()) {
                    String content = templates.get(0);
                    msg = content.replace("${user}", user.getCname())
                            .replace("${guestCname}", guest.getCname())
                            .replace("${guestEname}", guest.getEname())
                            .replace("${mobile}", guest.getMobile())
                            .replace("${email}", guest.getEmail());
                }else{
                    msg = "亲爱的" + user.getCname() + "，组委会决定由你负责" + guest.getCname() + 
                            "(" + guest.getEname() + ")嘉宾的会前咨询服务和会议现场服务。嘉宾的联系方式：" + guest.getMobile() +
                            "，注册邮箱：" + guest.getEmail() + "。请做好准备。【 组委会】";
                }
    
                SMService.sendSMS(Constant.SMSURL, msg , user.getMobile());
                log.info("发送短信成功！"+user.getCname() + " 手机号："+ user.getMobile() + ";用户ID"+ user.getUserId());
            }
        }
        
        //嘉宾发邮件
        if(guestInformWays.indexOf(String.valueOf('1')) > -1){
            if (guest.getEmail() != null && !guest.getEmail().equals("")) {
                MailSenderService mailSenderService = MailSenderService.getInstance();
                // 获取邮件模板
                List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_ASSIGN_GUEST_EMAIL, guestMeetUser.get(0).getUserType());
                
                if (templateList != null && !templateList.isEmpty()) {
                    Email email = new Email();
                    email.setTo(new String[] { guest.getEmail() });
                    email.setSubject(templateList.get(0)[0].toString());
                    mailSenderService.setTemplateName(templateList.get(0)[1].toString());
                     
                    Map<String, Object> model = new HashMap<String, Object>();
                    model.put("user", guest.getCname());
                    model.put("meetingName", meeting.getName());
                    model.put("userName", user.getCname());
                    model.put("mobile", user.getMobile());
                    model.put("email", user.getEmail());
    
                    Calendar ca = Calendar.getInstance();
                    model.put("year", ca.get(Calendar.YEAR));
                    model.put("month", ca.get(Calendar.MONTH) + 1);
                    model.put("day", ca.get(Calendar.DATE));
                    mailSenderService.sendMail(email, model);
                    
                    log.info("发送邮件成功！"+guest.getCname() + "(" + guest.getEname() + ");用户邮箱："+ guest.getEmail());
                }
    
            } 
        }

        //嘉宾短信
        if(guestInformWays.indexOf(String.valueOf('2')) > -1){
            if (guest.getMobile() != null && !guest.getMobile().equals("")) {
                List<String> templates = confSmsTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_ASSIGN_GUEST_MSG, user.getUserType());
                String msg = "";
                if (templates != null && !templates.isEmpty()) {
                    String content = templates.get(0);
                    msg = content.replace("${user}", guest.getCname())
                            .replace("${meetingName}", meeting.getName())
                            .replace("${userName}", user.getCname())
                            .replace("${mobile}", user.getMobile())
                            .replace("${email}", user.getEmail());
                }else{
                    msg = " 尊敬的" + guest.getCname() + "，我是您本次" + meeting.getName() + "会议的个人服务专员：" +
                            user.getCname() + "，我的联系方式：移动电话：" + user.getMobile() + "，邮箱：" + user.getEmail() 
                            + "。我将随时为您提供会前咨询服务和会议现场服务，期待您的到来。【组委会】";
                }
                SMService.sendSMS(Constant.SMSURL, msg , guest.getMobile());
                log.info("发送短信成功！"+ guest.getCname() + " 手机号："+ guest.getMobile() + ";用户ID"+ guest.getId());
            }
    
        }
    }
    
    //设置嘉宾提醒事件的值
    public void updateRemindflag (String meetingId, String userId, String flag) {
        confMeetingUserDAO.updateRemindFlag(meetingId, userId, flag);
    }
    
    @Resource
    ConfUserDAO confUserDAO;
    @Resource
    ConfAssignmentDAO confAssignmentDAO;
    @Resource
    ConfVAssignmentDAO confVAssignmentDAO;
    @Resource
    ConfMeetingUserDAO confMeetingUserDAO;
    @Resource
    ConfAssignmentOtherDAO confAssignmentOtherDAO;
    @Resource 
    ConfEmailTemplateDAO confEmailTemplateDAO;
    @Resource
    ConfSmsTemplateDAO confSmsTemplateDAO;
    @Resource
    ConfSysUserDAO confSysUserDAO;
    @Resource
    ConfMeetingDAO confMeetingDAO;

}
