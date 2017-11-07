package com.centling.conference.schedulelog.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.centling.conference.schedulelog.entity.ConfSchedulelogFiles;
import com.centling.conference.schedulelog.service.ConfSchedulelogFilesService;
import com.centling.conference.schedulelog.service.ConfSchedulelogUserAssignService;

/**
 * 分会场记-文档中心
 * @author centling
 *
 */
@Controller("confSchedulelogFilesController")
@RequestMapping("/schedulelog/files")
public class ConfSchedulelogFilesController {

	@Resource
	private ConfSchedulelogFilesService confSchedulelogFilesService;
	@Resource
	private ConfSchedulelogUserAssignService confSchedulelogUserAssignService;
	
	/**
	 * 根据条件加载出，用户所在的日程，列表
	 * @param pageBean
	 * @param request
	 * @param userEmail
	 * @param scheduleId
	 * @return
	 */
	@RequestMapping(value = "/findUers", method = RequestMethod.POST)
	public @ResponseBody Pagination findUers(PageBean pageBean,HttpServletRequest request,String userName,String userEmail,String scheduleId){
		String meetingId = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		return confSchedulelogUserAssignService.findUers(pageBean, userName, userEmail, scheduleId);
	}
	
	@RequestMapping(value = "/findUerFiles/{logid}", method = RequestMethod.POST)
	public @ResponseBody List<ConfSchedulelogFiles> findUserFiles(HttpServletRequest request,@PathVariable String logid){
		return confSchedulelogFilesService.findUserFiles(logid);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void save(@ModelAttribute ConfSchedulelogFiles file){
		confSchedulelogFilesService.save(file);
		return;
	}
	
	/**
	 * 2步操作：删除数据库，删除文件
	 * 1.根据id删除数据库的记录
	 * 2.然后根据path路径，删除文件
	 * @param id
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/dropFile", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> deleteFile(@RequestParam String ids,@RequestParam String paths,HttpServletRequest request){
		return confSchedulelogFilesService.deleteFile(ids,paths,request);
	}

}
