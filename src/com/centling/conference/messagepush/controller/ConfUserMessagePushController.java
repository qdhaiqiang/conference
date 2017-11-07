package com.centling.conference.messagepush.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meeting.service.ConfMeetingService;
import com.centling.conference.messagepush.entity.ConfMessagePush;
import com.centling.conference.messagepush.service.ConfMessagePushService;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.SMService;

@Controller("confUserMessagePushController")
@RequestMapping("/confUserMessagePushController/*")
public class ConfUserMessagePushController {
	private static final Logger log = LoggerFactory.getLogger(ConfUserDAO.class);
	/**
	 * Get the list of all users of current meeting
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/r",method=RequestMethod.POST)
	public @ResponseBody Pagination retrive(PageBean pageBean, HttpSession session, @ModelAttribute ConfUser confUser, String userType){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserService.retrive(pageBean, confUser, meetingId, userType);
	}
	@RequestMapping(value="/all",method=RequestMethod.POST)
	public @ResponseBody List<Map<String,Object>> getAll(HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		List<Map<String, Object>> list =  confUserService.all(meetingId);
		return list;
	}
	@RequestMapping(value="/push", method=RequestMethod.POST)
	public @ResponseBody String push(String ids, HttpSession session, String message, String gateway,String title){
		String result = "所有通知推送成功!";
		String[] id = ids.split(",");
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		ConfMeeting meeting = confMeetingService.findById(meetingId);
		String msg = StringUtils.EMPTY;;
		String wrongNum=StringUtils.EMPTY;
		for(int i=0; i<id.length; i++){
			ConfUser confUser = confUserDAO.findById(id[i]);
			String name = confUser.getCname();
			String tel = confUser.getMobile();
			String email = confUser.getEmail();
			msg = message.replace("{user}", name)
		            .replace("{meeting_name}", meeting.getName())
		            .replace("{meeting_start_time}", meeting.getStartTime())
		            .replace("{meeting_location}", meeting.getCity());
			//使用短信发送
			if(gateway.toUpperCase().contains("SMS")){
				if (tel.length()==11) {
					try {
						String httpResponse = SMService.sendSMS(Constant.SMSURL, msg, tel);
						log.debug(httpResponse);
						if(!httpResponse.toLowerCase().contains("ok")){
							result = "发送失败，请检查用户"+confUser.getCname()+"("+tel+(")的电话号码是否正确");
							log.error(httpResponse+"\n"+result);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					wrongNum+=confUser.getCname()+"("+tel+"),";
				}
			}
			//邮件 推送
			if(gateway.toUpperCase().contains("EMAIL")){
				MailSenderService service = MailSenderService.getInstance();
				Email sendEmail = new Email();
				sendEmail.setSubject(title);
				sendEmail.setText(msg);
				sendEmail.setTo(new String[] {email});
				try {
					service.sendMail(sendEmail);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//APP 推送
			if(gateway.toUpperCase().contains("APP")){
				ConfMessagePush confMessagePush = new ConfMessagePush();
				confMessagePush.setMeetingId(meetingId);
				confMessagePush.setUserId(id[i]);
				confMessagePush.setBirth(DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
				confMessagePush.setState("0");
				confMessagePush.setMessageType("0");
				confMessagePush.setContent(msg);
				confMessagePushService.save(confMessagePush);
			}
		}
		if (StringUtils.isNotEmpty(wrongNum)) {
			result = "*除以下号码外所有信息发送成功*:"+wrongNum.substring(0,wrongNum.length()-1);
		}
		return result;
	}
	@RequestMapping(value="/retrieve",method=RequestMethod.POST)
	public @ResponseBody List<ConfMessagePush> retrieve (PageBean pageBean, HttpSession session, @ModelAttribute ConfMessagePush confMessagePush){
		String meetingId = ((ConfMeeting)session.getAttribute(Constant.FRONT_SESSION_MEETING)).getId();
		ConfUser confUser = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
		String userId = confUser.getId();
		
		confMessagePush.setUserId(userId);
		return confMessagePushService.retrieve(pageBean,confMessagePush,meetingId);
	}
	@RequestMapping(value="/read",method=RequestMethod.POST)
	public @ResponseBody String read (HttpSession session, String messageId){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		ConfMessagePush confMessagePush = confMessagePushService.findById(messageId);
		confMessagePush.setState("1");
		confMessagePushService.update(confMessagePush);
		return "update success";
	}
	@Resource
	private ConfUserService confUserService;
	@Resource
	private ConfUserDAO confUserDAO;
	@Resource
	private ConfMessagePushService confMessagePushService;
	@Resource
	private ConfMeetingService confMeetingService;
}
