package com.centling.conference.exclusiveschedule.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exclusiveschedule.service.ConfExclusiveScheduleService;
import com.centling.conference.meetinguser.entity.ConfGuest;

/**
 * 专属日程Controller
 * @author lizzh
 *
 */
@Controller("confExclusiveScheduleController")
@RequestMapping("/exclusiveschedule/*")
public class ConfExclusiveScheduleController {
	@Resource
	private ConfExclusiveScheduleService confExclusiveScheduleService;
	
    //专属日程页面初始化数据取得
    @RequestMapping(value="r", method = RequestMethod.POST)
    public @ResponseBody Pagination retrive (PageBean pageBean, ConfGuest guest, HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);

        return confExclusiveScheduleService.retrieve(pageBean, meetingId, guest);
    }
	
	@RequestMapping(value="/getExculScheInfo/{guestId}",method=RequestMethod.GET)
	public ModelAndView getExculScheInfo(HttpSession session, @PathVariable String guestId) {
		// 从Session中获取会议 ID 和指派人ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		Map<String, Object> model = confExclusiveScheduleService.getExculScheInfo(meetingId, guestId);
		return new ModelAndView("/admin/exclusiveschedule/exclusive_schedule", model);
	}
}
