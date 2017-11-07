package com.centling.conference.dyn.form.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dyn.form.service.ConfDynFormService;
import com.centling.conference.user.entity.ConfUser;

/**
 * 动态表单提交串处理Controller
 * @author Dirk
 *
 */
@Controller("confDynFormController")
@RequestMapping("/dynForm/*")
public class ConfDynFormController {
	@Resource
	private ConfDynFormService confDynFormService;
	
	/**
	 * （后台）保存动态表单
	 * @param payload 待保存的动态表单字符串
	 * @return
	 */
	@RequestMapping(value="/c/saveDynForm",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> saveForm(@RequestParam String payload, 
			@RequestParam String userType,@RequestParam String sort, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		Map<String, String> map = new HashMap<String, String>();
		if (confDynFormService.saveDynForm(payload, userType, meetingId,sort)) {
			map.put("status", "0");
			map.put("info", "动态表单保存成功");
		} else {
			map.put("status", "1");
			map.put("info", "动态表单保存失败, 该类型已经有注册用户，禁止更改！");
		}
		return map;
	}
	
	/**
	 * （后台）根据会议ID与用户类型，查询动态模板
	 * @param userType 用户类型
	 * @param meetingId 会议ID
	 * @return json字符串 status：0-->查询到模板 status：1-->未查询到模板
	 */
	@RequestMapping(value="/r/getForm/{meetingId}/{userType}",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> getFormByUserType(@PathVariable String userType, @PathVariable String meetingId) {
		return confDynFormService.getDynTemplateByUserType(userType, meetingId);
	}
	
	@RequestMapping(value="/r/getForm2/{meetingId}/{userType}",method=RequestMethod.GET)
	public ModelAndView getFormByUserTypeReturnModelValue(@PathVariable String userType, @PathVariable String meetingId) {
		 Map<String,Object>  map=confDynFormService.getDynTemplateByUserType(userType, meetingId); 
		 map.put("meetingId", meetingId);
		 map.put("userType", userType);
		return new ModelAndView("admin/dynamicFormEdit",map);
	}
	
	@RequestMapping(value="/r/getNewForm", method=RequestMethod.GET)
	public ModelAndView getNewForm() {
		return new ModelAndView("admin/dynamicFormAdd");
	}
	
	/**
	 * （前台）根据会议ID与用户类型，查询要填写的表单
	 * @param userType 用户类型
	 * @param meetingId 会议ID
	 * @return
	 */
	@RequestMapping(value="/r/getFormFront/{meetingId}/{userType}", method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> getFormFront(@PathVariable String userType,@PathVariable String meetingId,HttpSession session) {
		// 从Session中获取用户信息
		ConfUser confUser = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
		String confUserId = null;
		if (confUser != null) {
			confUserId = confUser.getId();
		}
		return confDynFormService.getFormFront(userType, meetingId, confUserId,session);
	}
	
	/**
	 * （后台）根据会议ID与用户类型，查询要填写的表单
	 * @param meetingId 会议ID
	 * @param userId 用户ID
	 * @return
	 */
	@RequestMapping(value="/r/getFormAdmin/{meetingId}/{userId}", method=RequestMethod.GET)
	public @ResponseBody 
	Map<String, Object> getFormAdmin(@PathVariable String meetingId,
	        @PathVariable String userId, HttpSession session) {
	    // 从Session中获取用户信息
	    return confDynFormService.getFormAdmin(meetingId, userId);
	}
	
	/**
	 * （前台）保存动态表单数据
	 * @return
	 */
	@RequestMapping(value="/c/saveFormFront", method=RequestMethod.POST)
	public @ResponseBody Map<String, String> saveFormFront(@RequestParam String payload,HttpSession session) {
		
		return confDynFormService.saveFormFront(payload,session);
	}
	
	/**
	 * 分页获取表单模板
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confDynFormService.retrieve(pageBean,meetingId);
	}

}