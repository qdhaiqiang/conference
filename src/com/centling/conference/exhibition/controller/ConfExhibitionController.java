package com.centling.conference.exhibition.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exhibition.DAO.ConfExhibitionDAO;
import com.centling.conference.exhibition.entity.ConfExhibition;
import com.centling.conference.exhibition.service.ConfExhibitionService;

@Controller("confExhibitionController")
@RequestMapping("/confExhibitionController/*")
public class ConfExhibitionController {
	@Resource
	ConfExhibitionService confExhibitionService;
	
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitionDAO.class);
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody Pagination retrieve (PageBean pageBean, HttpSession session, @ModelAttribute ConfExhibition confExhibition) {
		log.debug("1");
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confExhibitionService.retrive(pageBean, confExhibition, meetingId);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfExhibition confExhibition,HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		confExhibition.setMeetingId(meetingId);
		confExhibitionService.save(confExhibition);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody Map<String,Object> delete(@RequestParam String id, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	return confExhibitionService.delete(id,meetingId);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfExhibition confExhibition, HttpSession httpSession) throws Exception {
    	String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
    	confExhibition.setMeetingId(meetingId);
    	confExhibitionService.update(confExhibition);
        return "更新成功";
    }
    @RequestMapping(value = "/getTypeList", method = RequestMethod.GET)
    public @ResponseBody
    List<ConfExhibition> getTypeList(){
    	return confExhibitionService.getTypeList();
    }
    
    /**
     * 首页面点击展会信息跳转到展会主页面
     * @param essayId
     * @param request
     * @return
     */
    @RequestMapping(value="/goToExhibitIndex/{secondEssayTypeId}", method=RequestMethod.GET)
    public String goToExhibitIndex(@PathVariable String secondEssayTypeId, HttpServletRequest request) {
    	request.setAttribute("secondEssayTypeId", secondEssayTypeId);
    	return "front/exhibition/exhibition_index";
    }
}
