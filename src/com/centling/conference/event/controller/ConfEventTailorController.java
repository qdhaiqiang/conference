package com.centling.conference.event.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.centling.conference.event.entity.ConfEvent;
import com.centling.conference.event.service.ConfEventTailorService;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Controller("confEventTailorController")
@RequestMapping("/eventTailor/*")
public class ConfEventTailorController {
	
	@Resource
	ConfEventTailorService eventTailorService;

	//初期化
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody 
	Pagination retrieve (PageBean pageBean, HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return eventTailorService.retrive(pageBean, meetingId);
	}
	
	//新增事件
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String add(@ModelAttribute ConfEvent event, HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		event.setMeetingId(meetingId);
		eventTailorService.save(event);
        return "添加成功";
    }
	
	//修改事件内容
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfEvent event, HttpSession session) throws Exception {
	    String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
	    event.setMeetingId(meetingId);
	    eventTailorService.update(event);
	    return "更新成功";
	}
    
	//删除选中的事件
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam String ids, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	int delCount = eventTailorService.delete(ids, meetingId);
    	if (delCount > 0) {
    		return "删除成功";
        } else {
        	return "删除不成功，请刷新后重试";
        }
    }
    //查找所有待指定事件的嘉宾
    @RequestMapping(value="getUser/{id}",method=RequestMethod.POST)
    public @ResponseBody List<Map> getMember(@PathVariable String id, ConfGuest user, HttpSession session) {
        //将选择的事件ID存入session中
        session.setAttribute(Constant.SESSION_EVENT_ID, id);
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return eventTailorService.findAssignUserByUserType(meetingId, user);
    }
    
    //查找已经绑定该事件的嘉宾
    @RequestMapping(value="getSelectedUsers/{id}",method=RequestMethod.POST)
    public @ResponseBody String getSelectedUsers(@PathVariable String id, HttpSession session) {
        return eventTailorService.findSelectedUserByEventId(id);
    }
    
    //保存事件指定人员
    @RequestMapping(value="saveEventUser", method=RequestMethod.POST)
    public @ResponseBody String saveEventUser(@RequestParam String userIds, HttpSession session){
        String eventId = (String)session.getAttribute(Constant.SESSION_EVENT_ID);
        if (eventId == null || eventId.equals("")) {
            return "操作失败,您选择的事件数据已丢失,请刷新页面后重新操作。";
        }
        eventTailorService.saveEventUser(eventId, userIds);
        return "定制事件人员指定成功";
    }
    
    //删除事件指定人员
    @RequestMapping(value="deleteEventUser", method=RequestMethod.GET)
    public @ResponseBody String deleteEventUser(HttpSession session) throws Exception{
        String eventId = (String)session.getAttribute(Constant.SESSION_EVENT_ID);
        if (eventId == null || eventId.equals("")) {
            return "操作失败,您选择的事件数据已丢失,请刷新页面后重新操作。";
        }
        eventTailorService.deleteEventUser(eventId);
        return "指定人员删除成功";
    }
    
    //查找定制事件的嘉宾人数
    @RequestMapping(value="queryUserCount/{eventId}", method=RequestMethod.POST)
    public @ResponseBody String queryUserCount(@PathVariable String eventId) {
        return eventTailorService.queryUserCount(eventId);
    }
    
    //给选中事件的嘉宾发送邮件或者短信提醒
    @RequestMapping(value="sendRemind",method=RequestMethod.POST)
    public @ResponseBody String sendRemind (@RequestParam String eventId, HttpSession session) throws Exception {
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        eventTailorService.sendRemind(eventId, meetingId);
        return "提示信息发送成功";
    }
    
    //查询选中了该事件的所有嘉宾
    @RequestMapping(value="/getUser", method=RequestMethod.POST)
    public @ResponseBody List<Map> getUserListByEventId(@RequestParam String eventId, HttpSession session) {
        String meetingId  = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return eventTailorService.getUserByEventId(meetingId, eventId);
    }
}
