package com.centling.conference.checkin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.assignment.DAO.ConfAssignmentDAO;
import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.checkin.DAO.ConfSigninUserDAO;
import com.centling.conference.checkin.entity.ConfSigninUser;
import com.centling.conference.event.entity.ConfEvent;
import com.centling.conference.managermobile.DAO.ConfManagerMobileDAO;
import com.centling.conference.managermobile.entity.ConfManagerMobile;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.sms.DAO.ConfSmsTemplateDAO;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.SMService;

@Service("confSigninUserService")
public class ConfSigninUserService {
    private static final Logger log = LoggerFactory.getLogger(ConfAssignmentDAO.class);
    @Resource
    private ConfSigninUserDAO confSigninUserDAO;
    @Resource
    private ConfMeetingUserDAO confMeetingUserDAO;
    @Resource
    private ConfUserDAO confUserDAO;
    @Resource
    ConfSmsTemplateDAO confSmsTemplateDAO;
    @Resource
    ConfManagerMobileDAO confManagerMobileDAO;
	
	//通过扫描枪扫描的二维码查询用户
    public HashMap<String,Object> findByQCcode (String userId, String meetingId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ConfUser confUser = null;
		ConfMeetingUser confMeetingUser = null;
		List confUserList = confMeetingUserDAO.findUsersBymeetingIdAndUserId(
				userId, meetingId);
		if (confUserList != null) {
			if (confUserList.size() == 0) {
				resultMap.put("OperatorState", "false");
				resultMap.put("Message", "该用户没有注册该会议或者没有审核通过");
			} else {
				confMeetingUser = (ConfMeetingUser) confUserList.get(0);
				confUser = confUserDAO.findById(userId);
				List confSigninUserList = confSigninUserDAO
						.findBySigninUserbyIDandMeeting(userId, meetingId);
				if (confSigninUserList != null) {
					if (confSigninUserList.size() != 0) {
						resultMap.put("OperatorState", "false");
						resultMap.put("Message", "该用户已经签到过");
						resultMap.put("confSigninUser", confSigninUserList.get(0));
					} else {
						resultMap.put("OperatorState", "true");
						resultMap.put("Message", "");
						resultMap.put("confUser", confUser);
						resultMap.put("userType",confMeetingUser.getUserType());
					}
				}
			}
		}
       return resultMap;
    }
	
	
  //通过手动输入用户名和手机号查询用户
    public HashMap<String,Object> findByUserMail (String email, String meetingId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ConfUser confUser = null;
		ConfMeetingUser confMeetingUser = null;
		List confUserList = confUserDAO.findByEmail(email);
		if (confUserList != null) {
			if (confUserList.size() == 0) {
				resultMap.put("OperatorState", "false");
				resultMap.put("Message", "该用户没有注册该会议或者没有审核通过");
			} else {
				confUser = (ConfUser) confUserList.get(0);
				List confMeetUserList = confMeetingUserDAO
						.findUsersBymeetingIdAndUserId(confUser.getId(),
								meetingId);
				if (confMeetUserList != null) {
					if (confMeetUserList.size() == 0) {
						resultMap.put("OperatorState", "false");
						resultMap.put("Message", "该用户没有注册该会议或者没有审核通过");
					} else {
						confMeetingUser = (ConfMeetingUser) confMeetUserList.get(0);
						List confSigninUserList = confSigninUserDAO
								.findByUserId(confUser.getId());
						if (confSigninUserList != null) {
							if (confSigninUserList.size() != 0) {
								resultMap.put("OperatorState", "false");
								resultMap.put("Message", "该用户已经签到过");
								resultMap.put("confSigninUser", confSigninUserList.get(0));
							} else {
								resultMap.put("OperatorState", "true");
								resultMap.put("Message", "");
								resultMap.put("confUser", confUser);
								resultMap.put("userType",confMeetingUser.getUserType());
							}
						}
					}
				}
			}
		}
    	 return resultMap;
      
    }
		
	//保存签到信息
    public String saveSigninUser(ConfSigninUser signUser) throws Exception{
    	long currentTime = System.currentTimeMillis();
    	Date date = new Date(currentTime);
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    	String signinTime = df.format(date);
    	signUser.setSigninTime(signinTime);
    	Date datecopy = new Date(currentTime);
    	SimpleDateFormat dfcopy = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
    	String signinTimeCopy = dfcopy.format(datecopy);
    	signUser.setSigninTimeCopy(signinTimeCopy);
    	List<ConfMeetingUser> confmeetingUser = confMeetingUserDAO.findUsersBymeetingIdAndUserId(signUser.getConfUser().getId(), signUser.getMeetingId());
    	if(confmeetingUser!=null &&confmeetingUser.size() != 0){
    		signUser.setUserType(confmeetingUser.get(0).getUserType());
    	}
    	String remindFlag = confMeetingUserDAO.findRemindFlag(signUser.getMeetingId(), signUser.getConfUser().getId());
    	if (remindFlag != null && remindFlag.equals("1")) {
    	    sendSMS(signUser);//发送嘉宾到场提醒短信
    	}
    	if((signUser.getId()!=null) && (!signUser.getId().equals(""))){
    		confSigninUserDAO.update(signUser);
    	}else{
    		confSigninUserDAO.save(signUser);
    	}
    	//confSigninUserDAO.save(signUser);
    	return "Success";
    }
    
  //查询所有报到某一会议的人员
    public Pagination findAllByMeetingId(PageBean pageBean, String username, String meetingId) {
    	Pagination pagination = new Pagination();
    	List<ConfSigninUser> list = confSigninUserDAO.findAllByMeetingId(pageBean, username, meetingId);
    	pagination.setRows(list);
		pagination.setTotal(confSigninUserDAO.count(meetingId)+"");
		
    	return pagination;
    }
    public HashMap<String,Object> count(String meetingId) {
    	HashMap<String, Object> resultMap = new HashMap<String, Object>();
    	Long countSignedUser = confSigninUserDAO.count(meetingId);
    	Long countMeetingUser = confMeetingUserDAO.countByMeetingId(meetingId);
    	resultMap.put("countSignedUser", countSignedUser+"");
		resultMap.put("countMeetingUser", countMeetingUser+"");
		return resultMap;
    }
       
    //发送短信
    private void sendSMS(ConfSigninUser signUser) throws Exception {
        ConfUser user = confUserDAO.findById(signUser.getConfUser().getId());
        List<ConfManagerMobile> managerMobelList = confManagerMobileDAO.findByMeetingId(signUser.getMeetingId());
        for (int i=0;i<managerMobelList.size();i++) {
            if(managerMobelList.get(i).getManagerMobile() != null && !managerMobelList.get(i).getManagerMobile().equals("") && managerMobelList.get(i).getManagerMobile().length() == 11){
                List<String> templates = confSmsTemplateDAO.findByUserName(Constant.SMS_TYPE_GUEST_ARRIVED);
                String msg = "";
                if (templates != null && !templates.isEmpty()) {
                    String content = templates.get(0);
                    msg = content.replace("${user}", user.getCname())
                            .replace("${Ename}", user.getEname());
                }else{
                    msg = "尊敬的领导，本次会议的重要嘉宾" + user.getCname() + "(" + user.getEname() + ")" +
                            "已到达会议现场，请安排时间接待，谢谢您对本次会议的支持。【 组委会】";
                }
                
                SMService.sendSMS(Constant.SMSURL, msg , managerMobelList.get(i).getManagerMobile());
                log.info("嘉宾提醒短信发送成功！"+ " 手机号："+ managerMobelList.get(i).getManagerMobile());
            }
        }
    }
}
