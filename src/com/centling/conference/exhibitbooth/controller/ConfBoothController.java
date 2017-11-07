package com.centling.conference.exhibitbooth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exhibitbooth.DAO.ConfBoothDAO;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibitbooth.service.ConfBoothService;
import com.centling.conference.exhibition.DAO.ConfExhibitionDAO;
import com.centling.conference.exhibition.entity.ConfExhibition;
import com.centling.conference.exhibition.service.ConfExhibitionService;

@Controller("confBoothController")
@RequestMapping("/confBoothController/*")
public class ConfBoothController {
	@Resource
	ConfBoothService confBoothService;
	
	private static final Logger log = LoggerFactory
			.getLogger(ConfBoothDAO.class);
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody 
	Pagination retrieve (PageBean pageBean, HttpSession session, @ModelAttribute ConfBooth confBooth) {
		log.debug("1");
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confBoothService.retrive(pageBean, confBooth, meetingId);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfBooth confBooth,HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		confBooth.setMeetingId(meetingId);
		confBoothService.save(confBooth);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam String id, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	String[] ids = id.split(",");
    	confBoothService.delete(ids,meetingId);
        return "删除成功";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfBooth confBooth, HttpSession httpSession) throws Exception {
    	String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
    	confBooth.setMeetingId(meetingId);
    	confBoothService.update(confBooth);
        return "更新成功";
    }
}
