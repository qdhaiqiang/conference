package com.centling.conference.assignment.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.assignment.service.ConfAssignmentService;
import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Controller("confAssignmentController")
@RequestMapping("/assignment/**")
public class ConfAssignmentController {

    @Resource
    ConfAssignmentService confAssignmentService;

    //人员指派页面初始化数据取得
    @RequestMapping(value="rOne", method = RequestMethod.POST)
    public @ResponseBody Pagination retrive (PageBean pageBean, ConfGuest guest, HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);

        return confAssignmentService.retrieve(pageBean, meetingId, guest);
    }
    
    /**
     * 为嘉宾指派一对一服务人员
     * @param confUser 查询条件
     * @return
     */
    @RequestMapping(value="getAssignMember/{guestId}",method=RequestMethod.POST)
    public @ResponseBody List<Map> getAssignMember(@PathVariable String guestId, ConfGuest user, HttpSession session) {
        session.setAttribute(Constant.ASSIGN_GUEST_ID, guestId);
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return confAssignmentService.findAssignUserByUserType(meetingId, user);
    }
    
    //保存指派人员(一对一)
    @RequestMapping(value="saveAssignUser", method=RequestMethod.POST)
    public @ResponseBody String saveAssignUser(@RequestParam String assignUserId, @RequestParam String userType, 
            @RequestParam String userName,  @RequestParam String email, @RequestParam String mobile, 
            @RequestParam String guestInformWays, @RequestParam String staffInformWays, HttpSession session) throws Exception{
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        String guestId = (String)session.getAttribute(Constant.ASSIGN_GUEST_ID);
        if (guestId == null || guestId.equals("")) {
            return "操作失败,您选择的日程信息已丢失,请刷新父画面后重新操作。";
        }
        ConfGuest user = new ConfGuest();
        user.setUserId(assignUserId);
        user.setUserType(userType);
        user.setCname(userName);
        user.setEmail(email);
        user.setMobile(mobile);
        confAssignmentService.saveAssignUser(meetingId, guestId, user, guestInformWays, staffInformWays);
        return "操作成功";
    }
    
    //删除指派人员(一对一)
    @RequestMapping(value="deleteAssignUser", method=RequestMethod.GET)
    public @ResponseBody String deleteAssignUser(HttpSession session) throws Exception{
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        String guestId = (String)session.getAttribute(Constant.ASSIGN_GUEST_ID);
        if (guestId == null || guestId.equals("")) {
            return "操作失败,您选择的日程信息已丢失,请刷新父画面后重新操作。";
        }
        confAssignmentService.deleteAssignUser(meetingId, guestId);
        return "一对一指派人员删除成功";
    }
    
    //将嘉宾设置为到场提醒嘉宾
    @RequestMapping(value="setRemindGuest", method=RequestMethod.POST)
    public @ResponseBody String setRemindGuest(@RequestParam String guestId, HttpSession session){
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        confAssignmentService.updateRemindflag(meetingId, guestId, "1");
        return "操作成功";
    }
    
    //取消将嘉宾设置为提醒嘉宾
    @RequestMapping(value="removeRemind", method=RequestMethod.POST)
    public @ResponseBody String removeRemind(@RequestParam String guestId, HttpSession session){
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        confAssignmentService.updateRemindflag(meetingId, guestId, "0");
        return "操作成功";
    }
    
    
    //其他指派-页面初始化数据取得
    @RequestMapping(value="rOther", method = RequestMethod.POST)
    public @ResponseBody Pagination retriveOther (PageBean pageBean, HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return confAssignmentService.retrieveOther(pageBean, meetingId);
    }
    
    /**
     * 为某个日程指派服务人员
     * @param confUser 查询条件
     * @return
     */
    @RequestMapping(value="getOtherAssignMember/{scheduleId}",method=RequestMethod.POST)
    public @ResponseBody List<Map> getOtherAssignMember(@PathVariable String scheduleId, ConfGuest user, HttpSession session) {
        session.setAttribute(Constant.ASSIGN_SCHEDULE_ID, scheduleId);
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return confAssignmentService.findAssignUserByUserType(meetingId, user);
    }
    
    /**
     * 获取指定到某会场的人员
     * @param scheduleId
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value="getAssignedUserByScheduleId/{scheduleId}", method=RequestMethod.POST)
    public @ResponseBody Pagination getAssignedUserByScheduleId(PageBean pageBean, @PathVariable String scheduleId, ConfGuest user, HttpSession session) {
    	// 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return confAssignmentService.findAssignedUserByScheduleId(pageBean, scheduleId, meetingId, user);
    }
    
    //保存指派人员(多对多)
    @RequestMapping(value="saveOtherAssignUser", method=RequestMethod.POST)
    public @ResponseBody String saveOtherAssignUser(@RequestParam String assignUserIds, @RequestParam String assignUserNames, HttpSession session){
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        String scheduleId = (String)session.getAttribute(Constant.ASSIGN_SCHEDULE_ID);
        if (scheduleId == null || scheduleId.equals("")) {
            return "操作失败,您选择的日程信息已丢失,请刷新父画面后重新操作。";
        }
        confAssignmentService.saveOtherAssignUser(meetingId, scheduleId, assignUserIds, assignUserNames);
        return "指派成功";
    }
    
    //删除指派人员(其他指派)
    @RequestMapping(value="deleteOtherAssignUser", method=RequestMethod.GET)
    public @ResponseBody String deleteOtherAssignUser(HttpSession session) throws Exception{
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        String scheduleId = (String)session.getAttribute(Constant.ASSIGN_SCHEDULE_ID);
        if (scheduleId == null || scheduleId.equals("")) {
            return "操作失败,您选择的日程信息已丢失,请刷新父画面后重新操作。";
        }
        confAssignmentService.deleteOtherAssignUser(meetingId, scheduleId);
        return "指派人员删除成功";
    }
}
