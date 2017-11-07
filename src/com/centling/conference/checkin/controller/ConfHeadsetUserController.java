package com.centling.conference.checkin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.checkin.service.ConfHeadsetUserService;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Controller("confHeadsetUserController")
@RequestMapping("/hasheadsetuser/*")
public class ConfHeadsetUserController {
	
	

	  @RequestMapping(value = "/r", method = RequestMethod.POST)
	  public @ResponseBody
		Pagination retrieve(PageBean pageBean, ConfGuest user, String scheduleId, HttpSession session) {
		  String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);

			return confHeadsetUserService.retrieve(pageBean,meetingId,scheduleId, user);
		}
	  
	
	@Resource
	private ConfHeadsetUserService confHeadsetUserService;
	
}
