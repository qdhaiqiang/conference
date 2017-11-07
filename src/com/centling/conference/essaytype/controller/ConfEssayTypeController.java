package com.centling.conference.essaytype.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essaytype.entity.ConfEssayType;
import com.centling.conference.essaytype.service.ConfEssayTypeService;

@Controller("confEssayTypeController")
@RequestMapping("/essay_type/*")
public class ConfEssayTypeController {

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody
	Pagination retrieve(PageBean pageBean,HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEssayTypeService.retrieve(pageBean, meetingId);
	}
	
	@RequestMapping(value = "/r2", method = RequestMethod.POST)
	public @ResponseBody
	Pagination retrieve2(PageBean pageBean,HttpSession session,@ModelAttribute ConfEssayType confEssayType) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEssayTypeService.retrieve2(pageBean, meetingId,confEssayType);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(@ModelAttribute ConfEssayType confEssayType, HttpServletRequest request) throws Exception {
		String meetingId = (String)request.getSession().getAttribute(Constant.SESSION_MEETING_ID);
		confEssayType.setMeetingId(meetingId);
		confEssayTypeService.save(confEssayType);
		return "添加成功";
	}
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String update(@ModelAttribute ConfEssayType confEssayType, HttpServletRequest request) throws Exception {
		ConfEssayType cet = confEssayTypeService.findById(confEssayType.getId());
		if (null != cet) {
			confEssayTypeService.update(confEssayType);
		}

		return "修改成功 ";
	}

	@RequestMapping(value = "/d", method = RequestMethod.GET)
	public @ResponseBody
	String delete(String id, HttpServletRequest request) throws Exception {
		confEssayTypeService.delete(id);
		return "删除成功";
	}
	
	@RequestMapping(value="/r/essay_type", method=RequestMethod.GET)
	public @ResponseBody
	List<ConfEssayType> findEssayType() {
		return confEssayTypeService.findAll();
	}
	
	@RequestMapping(value="/r/menu2", method=RequestMethod.GET)
	public @ResponseBody
	List<ConfEssayType> findMenu2(HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEssayTypeService.findMenu2(meetingId);
	}
	
	@RequestMapping(value="/r/parent_menu", method=RequestMethod.GET)
	public @ResponseBody
	List<ConfEssayType> findParentMenu(HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEssayTypeService.findParentMenu(meetingId);
	}
	
	@Resource
	ConfEssayTypeService confEssayTypeService;
}
