package com.centling.conference.schedulelog.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign;
import com.centling.conference.schedulelog.service.ConfSchedulelogUserAssignService;

/**
 * 分会场记-用户嘉宾指派
 * @author centling
 *
 */
@Controller("confSchedulelogUserAssignController")
@RequestMapping("/schedulelog/userAssign")
public class ConfSchedulelogUserAssignController {

	@Resource
	private ConfSchedulelogUserAssignService confSchedulelogUserAssignService;
	
	/**
	 * 根据日程id查询该日程下已经分配了的嘉宾
	 * @param pageBean
	 * @param scheduleId
	 * @return
	 */
	@RequestMapping(value="/assignlist/{scheduleId}",method=RequestMethod.GET)
	public @ResponseBody Pagination findAssignBySchedule(PageBean pageBean, @PathVariable String scheduleId){
		return confSchedulelogUserAssignService.findUers(pageBean, "", "", scheduleId);
	}
	
	/**
	 * 根据日程id查询选择该日程的嘉宾
	 * @param scheduleId
	 * @return
	 */
	@RequestMapping(value="/speakers/{scheduleId}",method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> findSpeakersByScheduleId(@PathVariable String scheduleId){
		return confSchedulelogUserAssignService.findSpeakersByScheduleId(scheduleId);
	}
	
	/**
	 * 保存嘉宾指派信息
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String saveAssign(ConfSchedulelogUserAssign assign){
		confSchedulelogUserAssignService.saveAssign(assign);
		return "保存成功！";
	}
	
	/**
	 * 保存嘉宾指派信息
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String updateAssign(ConfSchedulelogUserAssign assign){
		confSchedulelogUserAssignService.updateAssign(assign);
		return "修改成功！";
	}
	
	/**
	 * 删除指派信息
	 * @return
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public @ResponseBody String delAssign(String id){
		confSchedulelogUserAssignService.delAssign(id);
		return "删除成功！";
	}
}
