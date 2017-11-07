package com.centling.conference.sms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.sms.entity.ConfSmsTemplate;
import com.centling.conference.sms.service.ConfSmsTemplateService;

@Controller("confSmsTempalteController")
@RequestMapping("/confSmsTempalteController/*")
public class ConfSmsTemplateController {
	@Resource
	private ConfSmsTemplateService confSmsTemplateService;
	
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve (PageBean pageBean,HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confSmsTemplateService.retrive(pageBean,meetingId);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfSmsTemplate confSmsTemplate,HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		confSmsTemplate.setMeetingId(meetingId);
		confSmsTemplateService.save(confSmsTemplate);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody String delete(@RequestParam String id, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	String[] ids = id.split(",");
    	confSmsTemplateService.delete(ids,meetingId);
        return "删除成功";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfSmsTemplate confSmsTemplate, HttpSession httpSession) throws Exception {
    	String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
    	confSmsTemplate.setMeetingId(meetingId);
    	confSmsTemplateService.update(confSmsTemplate);
        return "更新成功";
    }
}
