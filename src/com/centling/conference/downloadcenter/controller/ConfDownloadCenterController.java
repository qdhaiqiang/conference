package com.centling.conference.downloadcenter.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.downloadcenter.service.ConfDownloadCenterService;

/**
 * 资源下载中心Controller
 * @author lizzh
 *
 */
@Controller("confDownloadCenterController")
@RequestMapping("/downloadCenter/*")
public class ConfDownloadCenterController {
	@Resource
	private ConfDownloadCenterService confDownloadCenterService;
	
	/**
	 * 分页查询待下载列表
	 * @return
	 */
	@RequestMapping(value="/getResList",method=RequestMethod.GET)
	public @ResponseBody Pagination getResList(PageBean pageBean,HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID); 
		return confDownloadCenterService.getResList(pageBean,meetingId);
	}
}
