package com.centling.conference.travel.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.assignment.DAO.ConfAssignmentDAO;
import com.centling.conference.assignment.entity.ConfAssignment;
import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.dict.service.ConfDictService;
import com.centling.conference.emailtemplate.DAO.ConfEmailTemplateDAO;
import com.centling.conference.sms.DAO.ConfSmsTemplateDAO;
import com.centling.conference.sysuser.DAO.ConfSysUserDAO;
import com.centling.conference.travel.DAO.ConfUserTravelDAO;
import com.centling.conference.travel.entity.ConfUserTravel;
import com.centling.conference.travel.entity.SearchParams;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.SMService;

@Service("userTravelService")
public class UserTravelService {
	
	@Resource
	private ConfUserTravelDAO confUserTravelDAO;
	@Resource 
	private ConfEmailTemplateDAO confEmailTemplateDAO;
	@Resource
	private ConfSmsTemplateDAO confSmsTemplateDAO;
	@Resource
	private ConfAssignmentDAO confAssignmentDAO;
	@Resource
    private ConfDictService confDictService;
	@Resource
    private ConfSysUserDAO confSysUserDAO;
	@Resource
	private ConfUserDAO confUserDAO;
	
	private static final Logger log = LoggerFactory
			.getLogger(UserTravelService.class);


	/**
	 * 保存行程信息
	 * 如果该嘉宾有分配一对一指派工作人员，需要查询出该工作人员的信息，conf_sys_user表中loginName关联conf_user的email才能查出
	 * @param travel
	 * @param user
	 * @param userType
	 * @param noticeEmail
	 * @param noticeMsg
	 * @throws Exception
	 */
	public void save(ConfUserTravel travel, ConfUser user, String userType,String noticeEmail,String noticeMsg ) throws Exception{
		if (StringUtils.isNotEmpty(noticeEmail)) {
			travel.setEmailSend(Constant.TRAVEL_MESSAGE_SEND);
		} else {
			travel.setEmailSend(Constant.TRAVEL_MESSAGE_UNSEND);
		}
		if (StringUtils.isNotEmpty(noticeMsg)) {
			travel.setMessageSend(Constant.TRAVEL_MESSAGE_SEND);
		} else {
			travel.setMessageSend(Constant.TRAVEL_MESSAGE_UNSEND);
		}
		confUserTravelDAO.merge(travel);
		log.info("行程信息保存成功！"+user.getCname()+user.getId());
		// noticeType为1，发送邮件，2发送短信
		sendMessage(travel, user, userType, noticeEmail, noticeMsg);
	}
	
	// 发送短信或邮件提醒
	private void sendMessage(ConfUserTravel travel, ConfUser user, String userType, String noticeEmail, String noticeMsg) throws Exception {
		if (noticeEmail!=null) {
			
			MailSenderService mailSenderService = MailSenderService.getInstance();
			// 获取邮件模板
			List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_TICKET,userType);
			
			if (templateList != null && !templateList.isEmpty()) {
				Email email = new Email();
				email.setTo(new String[] { user.getEmail() });
				email.setSubject(templateList.get(0)[0].toString());
				mailSenderService.setTemplateName(templateList.get(0)[1].toString());
				 
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user.getCname());
				model.put("number1", travel.getNumberCome());
				model.put("startPlace1", travel.getStartPlaceCome());
				model.put("endPlace1", travel.getEndPlaceCome());
				model.put("startTime1", travel.getStartTimeCome());
				model.put("endTime1", travel.getEndTimeCome());
				model.put("remark1", travel.getRemarkCome());
				model.put("typeGo1", "1".equals(travel.getTypeCome())?"飞机":"高铁");
				model.put("typeReturn1", "1".equals(travel.getTypeGo())?"飞机":"高铁");
				
				model.put("typeGo2", "1".equals(travel.getTypeCome())?"flight":"high speed railway");
				model.put("typeReturn2", "1".equals(travel.getTypeGo())?"flight":"high speed railway");
				
				model.put("number2", travel.getNumberGo());
				model.put("startPlace2", travel.getStartPlaceGo());
				model.put("endPlace2", travel.getEndPlaceGo());
				model.put("startTime2", travel.getStartTimeGo());
				model.put("endTime2", travel.getEndTimeGo());
				model.put("remark2", travel.getRemarkGo());

				Calendar ca = Calendar.getInstance();
				model.put("year", ca.get(Calendar.YEAR));
				model.put("month", ca.get(Calendar.MONTH) + 1);
				model.put("day", ca.get(Calendar.DATE));
				mailSenderService.sendMail(email, model);
				
				log.info("发送邮件成功！"+user.getCname()+user.getId());
			}

		} 
		if(noticeMsg!=null){
		    if (!user.getMobile().isEmpty() && user.getMobile().length() == 11) {
		        List<String> templates = confSmsTemplateDAO.findByUserTypeAndName(Constant.MSG_TYPE_TICKET,userType);
		        String msg = "";
		        if (templates != null && !templates.isEmpty()) {
		            String content = templates.get(0);
		            msg = content.replace("${user}", user.getCname()).replace("${number1}", travel.getNumberCome())
		                    .replace("${startPlace1}", travel.getStartPlaceCome()).replace("${endPlace1}", travel.getEndPlaceCome())
		                    .replace("${startTime1}", travel.getStartTimeCome()).replace("${endTime1}", travel.getEndTimeCome())
		                    .replace("${remark1}", travel.getRemarkCome()).replace("${number2}", travel.getNumberGo())
		                    .replace("${startPlace2}", travel.getStartPlaceGo()).replace("${endPlace2}", travel.getEndPlaceGo())
		                    .replace("${startTime2}", travel.getStartTimeGo()).replace("${endTime2}", travel.getEndTimeGo())
		                    .replace("${remark2}", travel.getRemarkGo()).replace("${typeGo}", "1".equals(travel.getTypeCome())?"飞机":"高铁")
		                    .replace("${typeReturn}", "1".equals(travel.getTypeGo())?"飞机":"高铁");
		        }else{
		            msg = "亲爱的" + user.getCname() + "，您的机票/高铁票已经预定完毕,赴澳行程信息如下：航班号/车次:" + travel.getNumberCome() + ",出发地:"
		                    + travel.getStartPlaceCome() + "，目的地:" + travel.getEndPlaceCome() + "，出发时间:" + travel.getStartTimeCome() + "，到达时间:"
		                    + travel.getEndTimeCome() + ";您的返程：航班号/车次:" + travel.getNumberGo() + ",出发地:" + travel.getStartPlaceGo() + "，目的地:"
		                    + travel.getEndPlaceGo() + "，出发时间:" + travel.getStartTimeGo() + "，到达时间:" + travel.getEndTimeGo() + "。【组委会】";
		        }
		        SMService.sendSMS(Constant.SMSURL, msg , user.getMobile());
		        log.info("发送短信成功！"+user.getCname()+user.getId());
		    }
		}
		
		//获取一对一指派人员信息并发送邮件通知
		List<ConfAssignment> staff = confAssignmentDAO.findByProperty("guestId", travel.getUserId());
		if (staff.size() > 0 ) {
			//staff.get(0).getUserId()是工作人员的id,在conf_sys_user表中要找出loginname,然后去找用户表
			List<Map> assignUser = confSysUserDAO.findUserAndUserType(staff.get(0).getUserId());//sysUserId
			//查询出一对一指派的工作人员的loginname去关联user表中的email,查询出userType和cname;userType要获取邮件模板
		    //List<Map> assignUser = confUserDAO.findUserAndUserType(staff.get(0).getUserId());
		    if(assignUser!=null && assignUser.size() > 0){
		    	if (!((String)assignUser.get(0).get("email")).isEmpty()) {
			        MailSenderService mailSenderService = MailSenderService.getInstance();
		            // 获取邮件模板
		            List<Object[]> templateList = confEmailTemplateDAO.findByUserTypeAndName(Constant.EMAIL_TYPE_TICKET_ASSIGN, (String)assignUser.get(0).get("user_type"));
		            
		            if (templateList != null && !templateList.isEmpty()) {
		                Email email = new Email();
		                email.setTo(new String[] { (String)assignUser.get(0).get("email") });
		                email.setSubject(templateList.get(0)[0].toString());
		                mailSenderService.setTemplateName(templateList.get(0)[1].toString());
		                
		                Map<String, Object> model = new HashMap<String, Object>();
		                model.put("user", (String)assignUser.get(0).get("cname"));
		                model.put("number1", travel.getNumberCome());
		                model.put("startPlace1", travel.getStartPlaceCome());
		                model.put("endPlace1", travel.getEndPlaceCome());
		                model.put("startTime1", travel.getStartTimeCome());
		                model.put("endTime1", travel.getEndTimeCome());
		                model.put("remark1", travel.getRemarkCome());
		                
		                model.put("number2", travel.getNumberGo());
		                model.put("startPlace2", travel.getStartPlaceGo());
		                model.put("endPlace2", travel.getEndPlaceGo());
		                model.put("startTime2", travel.getStartTimeGo());
		                model.put("endTime2", travel.getEndTimeGo());
		                model.put("remark2", travel.getRemarkGo());
		                
		                Calendar ca = Calendar.getInstance();
		                model.put("year", ca.get(Calendar.YEAR));
		                model.put("month", ca.get(Calendar.MONTH) + 1);
		                model.put("day", ca.get(Calendar.DATE));
		                mailSenderService.sendMail(email, model);
		                
		                log.info("指派工作人员提示邮件发送成功！"+(String)assignUser.get(0).get("cname") + staff.get(0).getUserId());
		            }
			    }
		    }
		}
	}
	
	
	 
	/**
	 *  查询参会者信息
	 * @param email
	 * @param meetingId
	 * @return
	 */
	public List<Map<String, Object>> findUserInfo(String email,String cname,String meetingId){
		List<Map<String, Object>> objects = confUserTravelDAO.retrieve(email,cname,meetingId);
		return objects;
	}

	
	/**
	 * 根据userId和meetingId查询行程信息
	 * @param travel
	 * @return
	 */
	public List<ConfUserTravel> findTravelInfo(ConfUserTravel travel){
		return confUserTravelDAO.findByExample(travel);
	}

	public List<Object[]> getTravelStatic(String meetingId,SearchParams searchParams) {
		List<Object[]> list = confUserTravelDAO.getTravelStatic(meetingId,searchParams);
		if(list == null || list.size() < 1){
    		return null;
    	}
        list = getDicMatchedList(list);
		return list;
	}

	private List<Object[]> getDicMatchedList(List<Object[]> list) {
		// 获取用户类型
		List<ConfDict> userTypeDicList = confDictService.findDictByCategory("user_type","2");
		for (Object[] object : list) {
			object[1] = changeToDicValue(object[1], userTypeDicList);
		}
		return list;
	}

	private Object changeToDicValue(Object object, List<ConfDict> dicList) {
		if(object != null && !object.toString().equals("")){
    		for(ConfDict conf : dicList){
    			if(object.toString().equals(conf.getCode())){
    				object = conf.getName();
    				break;
    			}
    		}
    	}
    	return object;
	}

	public Pagination getTravelInfo(PageBean pageBean, String meetingId,SearchParams searchParams) {
		Pagination pagination = new Pagination();
		List<Map<String, Object>> list = confUserTravelDAO.retrieve(pageBean, meetingId,searchParams);
		pagination.setRows(list);
		pagination.setTotal(confUserTravelDAO.count(meetingId,searchParams)+"");
		return pagination;
	}

	public List<Map<String, Object>> getEndPlaceCome(String meetingId) {
		return confUserTravelDAO.getEndPlaceCome(meetingId);
	}

	public List<Map<String, Object>> getStartPlaceGo(String meetingId) {
		return confUserTravelDAO.getStartPlaceGo(meetingId);
	}

	public void messageSend(String travelId, String userType,String noticeEmail, String noticeMsg) throws Exception {
		// 查询票务信息
		ConfUserTravel travel = confUserTravelDAO.findById(travelId);
		// 查询用户信息
		ConfUser confUser = confUserDAO.findById(travel.getUserId());
		// 发送信息
		sendMessage(travel, confUser, userType, noticeEmail, noticeMsg);
		// 修改票务提示信息
		if (StringUtils.isNotEmpty(noticeEmail) || StringUtils.isNotEmpty(noticeMsg)) {
			confUserTravelDAO.updateSendStatus(travelId,noticeEmail,noticeMsg);
		}
	}
}
