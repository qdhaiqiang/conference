package com.centling.conference.schedule.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.schedule.service.MyScheduleService;
import com.centling.conference.user.entity.ConfUser;

/**
 * 手机端我的议程
 * @author 
 *
 */
@Controller("myScheduleController")
@RequestMapping("/mySchedule")
public class MyScheduleController {

	@Resource
	private MyScheduleService myScheduleService;
	
	
    @RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody List retrieve(PageBean pageBean, HttpSession session,HttpServletRequest request) {
    	Locale local = RequestContextUtils.getLocale(request);
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		String userId = ((ConfUser)session.getAttribute(Constant.SESSION_CONF_USER)).getId();
		return myScheduleService.findScheduleByUser(meetingId, userId, local);
	}
}
