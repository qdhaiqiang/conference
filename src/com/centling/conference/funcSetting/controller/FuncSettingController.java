package com.centling.conference.funcSetting.controller;

import java.util.List;

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
import com.centling.conference.base.page.Pagination;
import com.centling.conference.funcSetting.service.FuncSettingService;
/**
 * 手机端 功能设置
 *
 */
@Controller("funcSettingController")
@RequestMapping("/funcSetting")
public class FuncSettingController {
	@Resource
	FuncSettingService confFuncSettingService;
	
	private static final Logger log = LoggerFactory
			.getLogger(FuncSettingController.class);
	/**
	 * 后台查询
	 * @param session
	 * @param confExhibition
	 * @return
	 */
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody 
	Pagination retrieve (HttpSession session) {
		log.debug("ConfFuncSettingController--retrieve--run");
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confFuncSettingService.retrive(meetingId);
	}
	/**
	 * 前台查询
	 * @param session
	 * @param confExhibition
	 * @return
	 */
	@RequestMapping(value = "/l", method = RequestMethod.GET)
	public @ResponseBody 
	List findFuncByOrder (HttpSession session) {
		log.debug("ConfFuncSettingController--findFuncByOrder--run");
		String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		return confFuncSettingService.findFuncByOrder(meetingId);
	}
	/**
	 * 后台更新状态
	 * @param session
	 * @param confExhibition
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody 
	String updateStatus (HttpSession session, @RequestParam String allStatus) {
		try{
		log.debug("ConfFuncSettingController--retrieve--run");
		confFuncSettingService.mergeFuncSetting(allStatus);
		return "更新成功";
		}catch (Exception e) {
			log.error("ConfFuncSettingController--updateStatus--error",e);
			e.printStackTrace();
			return "更新失败";
		}
	}
}
