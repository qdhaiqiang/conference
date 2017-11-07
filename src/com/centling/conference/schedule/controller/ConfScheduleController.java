package com.centling.conference.schedule.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.entity.ConfScheduleUser;
import com.centling.conference.schedule.service.ConfScheduleService;
import com.centling.conference.schedule.service.ConfScheduleUserService;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.exportWord.WordUtil;


@Controller("confScheduleController")
@RequestMapping("/schedule")
public class ConfScheduleController {
	private static final Logger log = LoggerFactory.getLogger(ConfScheduleController.class);
	@Resource
	private ConfScheduleUserService confScheduleUserService;
	
	@Resource
	private ConfScheduleService confScheduleService;
	
    @RequestMapping(value = "/subscribe/{userId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> subscribeOrUpdate(@RequestBody List<String> box,@PathVariable String userId, HttpSession session) throws Exception {
    	Map<String,String> result = new HashMap<String,String>();
    	String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
    	result = confScheduleUserService.subscribeOrUpdate(box,meetingId,userId);
    	//提交之后，删除预览时候的动态会议表单session
    	session.removeAttribute(Constant.SESSION_front_MeetingForm);
    	return result;
    }
    
    @RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confScheduleService.retrieve(pageBean,meetingId);
	}
    
    /**
     * 保存日程信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfSchedule confSchedule, HttpSession session) {
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	confSchedule.setMeetingId(meetingId);
    	if (StringUtils.isEmpty(confSchedule.getRestrictAudience())) {
    		confSchedule.setRestrictAudience(StringUtils.EMPTY);
    	}
    	confScheduleService.save(confSchedule);
        return "添加成功";
    }
    
    /**
     * 更新日程信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    public @ResponseBody
    String update(@ModelAttribute ConfSchedule confSchedule, HttpSession session) {
    	confScheduleService.update(confSchedule);
        return "修改成功";
    }
    
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody String delete(@RequestParam String scheduleId) {
    	confScheduleService.delete(scheduleId);
    	return "删除成功";
    }
    
    @RequestMapping(value = "/findByMeetingId", method = RequestMethod.GET)
    public @ResponseBody List<ConfSchedule> findById(HttpSession session) {
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        return confScheduleService.findByMeetingId(meetingId);
    }
    
    @RequestMapping(value = "/mobile/findByMeetingId", method = RequestMethod.GET)
    public @ResponseBody List<ConfSchedule> mobileFindById(HttpSession session) {
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
        return confScheduleService.findByMeetingId(meetingId);
    }
    
    @RequestMapping(value = "/mobile/frontUserSchedule", method = RequestMethod.GET)
    public @ResponseBody List<ConfScheduleUser> mobileFrontUserSchedule(HttpServletRequest request){
    	String meetingId = (String)request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID);
    	ConfUser user = (ConfUser)request.getSession().getAttribute(Constant.SESSION_CONF_USER);
    	String userId = user.getId();
    	return confScheduleUserService.findByUserAndMeeting(userId, meetingId);
    }
    
    @RequestMapping(value = "/findLiveById/{liveId}", method = RequestMethod.POST) 
    public @ResponseBody ConfSchedule findLiveById(@PathVariable String liveId) {
    	return confScheduleService.findLiveById(liveId);
    }
    
    /**
     * 导出分会场记
     * @param scheduleId 日程编号
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/exportBranchLog", method=RequestMethod.GET)
    public @ResponseBody String exportBranchLog(@RequestParam String scheduleId,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String webPath = request.getSession().getServletContext().getRealPath("/");
    	Map<String, Object> resultMap = confScheduleService.getBranchData(scheduleId,webPath);
    	response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=branchLog.doc");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			new WordUtil().createDoc(resultMap,"branchLogTemplate.ftl", sos);
			response.flushBuffer();
		} catch (Exception e) {
			log.error("导出分会场记发生异常"+e.getMessage());
			throw e;
		}
    	return "分会场记导出成功";
    }
}
