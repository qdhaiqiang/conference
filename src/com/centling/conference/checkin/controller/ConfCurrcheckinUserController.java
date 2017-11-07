package com.centling.conference.checkin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.checkin.entity.ConfCurrcheckinUser;
import com.centling.conference.checkin.service.ConfCurrcheckinUserService;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Controller("confCurrcheckinUserController")
@RequestMapping("/curcheckinuser/*")
public class ConfCurrcheckinUserController {
	
	

	  @RequestMapping(value = "/r", method = RequestMethod.POST)
	  public @ResponseBody
		Pagination retrieve(PageBean pageBean, ConfGuest user, String scheduleId, HttpSession session) {
		  String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);

			return confCurrcheckinUserService.retrieve(pageBean,meetingId,scheduleId, user);
		}
	  
	//参会观众实时显示
	  @RequestMapping(value = "/r/audiencelist", method = RequestMethod.GET)
	  public @ResponseBody
	  ModelAndView retrieveAudience(HttpSession httpSession) {
		  String[] userType = new String[6];
		  userType[0] = Constant.USER_TYPE_VIP;
		  userType[1] = Constant.USER_TYPE_VIP_US;
		  userType[2] = Constant.USER_TYPE_SPEAKER;
		  userType[3] = Constant.USER_TYPE_SPEAKER_EN;
		  userType[4] = Constant.USER_TYPE_ATTEND;
		  userType[5] = Constant.USER_TYPE_ATTEND_EN;
		  
		  String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		  String scheduleId = (String)httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);
		  List<ConfCurrcheckinUser> audienceList = confCurrcheckinUserService.retrieveAudience(meetingId, scheduleId, userType);
		  List<ConfCurrcheckinUser> guestList = confCurrcheckinUserService.retrieveGuest(meetingId, scheduleId, userType);
		
		  Map<String, Object> model =new HashMap<String, Object>();
		  model.put("audienceList", audienceList);
		  model.put("guestList", guestList);
			
		  return new ModelAndView("admin/checkinandout/guestandaudienceview",model);
		 
	  }
	@Resource
	private ConfCurrcheckinUserService confCurrcheckinUserService;
	
}
