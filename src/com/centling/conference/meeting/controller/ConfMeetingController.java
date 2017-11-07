package com.centling.conference.meeting.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meeting.service.ConfMeetingService;
import com.centling.conference.schedule.entity.frontSchel.TimeLine;
import com.centling.conference.user.entity.ConfUser;

@Controller("confMeetingController")
@RequestMapping("/meeting/*")
public class ConfMeetingController {

    @Resource
    private ConfMeetingService confMeetingService;
    
    @RequestMapping(value = "/r", method = RequestMethod.GET)
    public @ResponseBody
    Pagination retrieve(PageBean pageBean) {
    	return confMeetingService.mainRetrieve(pageBean);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfMeeting meeting) throws Exception {
        confMeetingService.save(meeting);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@PathVariable String id) throws Exception {
        ConfMeeting dbMeeting = confMeetingService.findById(id);
        if(dbMeeting != null){
            confMeetingService.delete(dbMeeting);
        }
        return "删除成功";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfMeeting meeting, HttpSession httpSession) throws Exception {
        String id = (String) httpSession.getAttribute("meetingId");
        meeting.setId(id);
        confMeetingService.update(meeting);
        return "更新成功";
    }
    
    @RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ConfMeeting active(@PathVariable String id, HttpServletRequest request) throws Exception {
        ConfMeeting meeting = confMeetingService.findById(id);
        if(meeting != null){
            confMeetingService.update(meeting);
        }
        return meeting;
    }
    
    @RequestMapping(value = "/meetingjson", method = RequestMethod.GET)
    public @ResponseBody TimeLine getTimeLine ( HttpSession httpSession,HttpServletRequest request) {
    	TimeLine tl = null;
    	ConfUser cu = (ConfUser)httpSession.getAttribute(Constant.SESSION_CONF_USER);
    	Locale locale = RequestContextUtils.getLocale(request);
		String meetingId = (String)httpSession.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		if (StringUtils.isNotEmpty(meetingId)) {
			String userId = cu.getId();
			tl = confMeetingService.findMeetings(meetingId,locale.toString(),userId);
		}
    	
    	
      return tl;
    }
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public @ResponseBody ConfMeeting findById(HttpSession httpSession) {
        String id = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        ConfMeeting meeting = confMeetingService.findById(id);
        return meeting;
    }
    

    /**
     * 查询未创建动态表单的用户类型
     * @return 查询到的用户类型列表
     */
    @RequestMapping(value="r/userType", method=RequestMethod.GET)
	public @ResponseBody List<ConfDict> findDictByMeetingId(HttpSession session) {
    	// 从Session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confMeetingService.findDictByMeetingId(meetingId);
	}
}
