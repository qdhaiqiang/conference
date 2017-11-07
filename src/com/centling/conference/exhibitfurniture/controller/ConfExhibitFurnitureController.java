package com.centling.conference.exhibitfurniture.controller;

import java.util.Map;

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
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;
import com.centling.conference.exhibitfurniture.service.ConfExhibitFurnitureService;

@Controller(value="confExhibitFurnitureController")
@RequestMapping("/furniture")
public class ConfExhibitFurnitureController {
	@Resource
	private ConfExhibitFurnitureService confExhibitFurnitureService;

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confExhibitFurnitureService.retrieve(pageBean,meetingId);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfExhibitFurniture confExhibitFurniture, HttpSession session) {
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	confExhibitFurniture.setMeetingId(meetingId);
    	confExhibitFurnitureService.save(confExhibitFurniture);
        return "添加成功";
    }
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfExhibitFurniture confExhibitFurniture) {
		confExhibitFurnitureService.update(confExhibitFurniture);
        return "修改成功";
    }
	
	@RequestMapping(value = "/d", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> delete(@RequestParam String furnitureId) {
		return confExhibitFurnitureService.delete(furnitureId);
	}
}