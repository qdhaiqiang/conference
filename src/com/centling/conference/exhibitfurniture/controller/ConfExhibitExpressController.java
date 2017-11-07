package com.centling.conference.exhibitfurniture.controller;

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
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress;
import com.centling.conference.exhibitfurniture.service.ConfExhibitExpressService;

@Controller("confExhibitExpressController")
@RequestMapping("/express")
public class ConfExhibitExpressController {
	@Resource
	private ConfExhibitExpressService confExhibitExpressService;

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confExhibitExpressService.retrieve(pageBean,meetingId);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfExhibitExpress confExhibitExpress, HttpSession session) {
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	confExhibitExpress.setMeetingId(meetingId);
    	confExhibitExpressService.save(confExhibitExpress);
        return "添加成功";
    }
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfExhibitExpress confExhibitExpress) {
		confExhibitExpressService.update(confExhibitExpress);
        return "修改成功";
    }
	
	@RequestMapping(value = "/d", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestParam String expressId) {
		confExhibitExpressService.delete(expressId);
		return "删除成功";
	}
}
