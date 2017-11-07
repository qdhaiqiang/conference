package com.centling.conference.emailtemplate.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.emailtemplate.entity.ConfEmailTemplate;
import com.centling.conference.emailtemplate.service.ConfEmailTemplateService;

@Controller("confEmailTemplateController")
@RequestMapping("/email_template/*")
public class ConfEmailTemplateController {

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody
	Pagination retrieve(PageBean pageBean, HttpSession session) {
		// 从session中获取meetingId
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEmailTemplateService.retrieve(pageBean,meetingId);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(@ModelAttribute ConfEmailTemplate confEmailTemplate, HttpSession session) throws Exception {
		// 从session中获取meetingId
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		confEmailTemplate.setMeetingId(meetingId);
		confEmailTemplateService.save(confEmailTemplate);
		return "添加成功";
	}
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfEmailTemplate confEmailTemplate) throws Exception {
		ConfEmailTemplate cet = confEmailTemplateService.findById(confEmailTemplate.getId());
		if (null != cet) {
			confEmailTemplateService.update(confEmailTemplate);
		}

		return "修改成功 ";
	}

	@RequestMapping(value = "/d", method = RequestMethod.GET)
	public @ResponseBody
	String delete(String id) throws Exception {
		confEmailTemplateService.delete(id);
		return "删除成功";
	}
	
	@Resource
	private ConfEmailTemplateService confEmailTemplateService;
}
