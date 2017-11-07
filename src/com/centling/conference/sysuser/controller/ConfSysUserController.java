package com.centling.conference.sysuser.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.service.ConfScheduleService;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.sysuser.service.ConfSysUserService;
import com.centling.conference.util.DateUtils;

@Controller("confSysUserController")
@RequestMapping("/confSysUser/**")
public class ConfSysUserController {
	private static final Logger log = LoggerFactory.getLogger(ConfSysUserController.class);
	@Resource
	private ConfSysUserService confSysUserService;
	
	@Resource
	private ConfScheduleService confScheduleService;
	
	@RequestMapping(value = "/403")
    public String errorpage(ModelMap model) { 
        model.addAttribute("message", "你没有访问权限");
        return "403";
    }
	
	
	@RequestMapping(value = "/uploadfile", method=RequestMethod.POST)
	public void processUpload(@RequestParam MultipartFile file, Model model) throws IOException {
		model.addAttribute("message", "File '" + file.getOriginalFilename() + "' uploaded successfully");
	}

	
	@RequestMapping(value = "/importStaff", method=RequestMethod.POST)
	public void importStaff(@RequestParam MultipartFile file) throws IOException {
		
		
	}
	
	
	@RequestMapping(value="/user/list")
	public @ResponseBody List<ConfSysUser> ListUser(){
		List<ConfSysUser> userList = confSysUserService.getAvailableUsers();
		return userList;
	}
	
	@RequestMapping(value = "/user/save",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String addUser(@ModelAttribute ConfSysUser user){
		if (confSysUserService.addUser(user)) {
			return "用户创建成功";
		} else {
			return "用户创建失败";
		}
	}
	
	@RequestMapping(value = "/user/delete/{sysUserId}")
	public @ResponseBody String delUser(@PathVariable String sysUserId){
		if (confSysUserService.deleteUser(sysUserId)) {
			return "用户删除成功";
		} else {
			return "用户删除失败";
		}
	}
	
	@RequestMapping(value = "/user/changePass",headers = {"content-type=application/json"},method=RequestMethod.POST)
	public @ResponseBody boolean changeUserPassword(@RequestBody ConfSysUser user){
		return confSysUserService.changeUser(user);
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/update",method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String updateUser(ConfSysUser user) {
		if (confSysUserService.updateUser(user)) {
			return "用户更新成功";
		} else {
			return "用户更新失败";
		}
	}
	
	/**
	 * 验证用户
	 * @param confSysUser
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/checkUser", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkUser(ConfSysUser confSysUser, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		ConfSysUser findSysUser = confSysUserService.checkUserExists(confSysUser);
		if (findSysUser!=null) {
			request.getSession().setAttribute(Constant.BACK_SESSION_USER, findSysUser);
			map.put("success", true);
			map.put("msg", "用户登录成功");
			log.info("用户【"+findSysUser.getLoginName()+"】登录成功，登录时间【"+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+"】，登录IP【"+request.getRemoteHost()+"】");
		} else {
			map.put("success", false);
			map.put("msg", "用户名或密码错误");
		}
		return map;
	}

	/**
	 * 跳转到后台首页面
	 * @param meetingId 会议ID
	 * @param httpSession session
	 * @return
	 */
	@RequestMapping(value="/goToBackMain", method=RequestMethod.POST)
	public String goToBackMain(@RequestParam String meetingId, HttpSession httpSession) {
		// 将会议ID保存到Session中
		httpSession.setAttribute(Constant.SESSION_MEETING_ID, meetingId);
		// 从Session中获取用户信息
		ConfSysUser confSysUser = (ConfSysUser)httpSession.getAttribute(Constant.BACK_SESSION_USER);
		// 根据会议ID，用户ID查询日程信息
		List<ConfSchedule> scheduleList = confScheduleService.findScheduleByUserId(confSysUser.getId(),meetingId);
		// 将日程信息保存到Session中
		httpSession.setAttribute(Constant.SESSION_SCHEDULE_LIST, scheduleList);
		// 跳转到后台首页面
		return "admin/index";
	}
	
	/**
	 * 用户退出
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// 从Session中获取用户
		ConfSysUser confSysUser = (ConfSysUser)session.getAttribute(Constant.BACK_SESSION_USER);
		// 注销session信息
		session.removeAttribute(Constant.SESSION_MEETING_ID);
		session.removeAttribute(Constant.BACK_SESSION_USER);
		if (confSysUser!=null) {
			log.info("用户【"+confSysUser.getLoginName()+"】退出成功，退出时间【"+DateUtils.format(new Date(), "yyyy-MM-dd")+"】，IP【"+request.getRemoteHost()+"】");
		} else {
			log.info("用户退出成功，退出时间【"+DateUtils.format(new Date(), "yyyy-MM-dd")+"】，IP【"+request.getRemoteHost()+"】");
		}
		// 跳转到登录页面
		return new ModelAndView("admin/login");
	}
	/**
	 * 获取左侧菜单
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getLeftMenu", method=RequestMethod.GET)
	public ModelAndView initLeftMenu(HttpSession session) {
		// 从Session中获取用户ID
		ConfSysUser confSysUser = (ConfSysUser)session.getAttribute(Constant.BACK_SESSION_USER);
		String leftMenuStr = confSysUserService.getLeftMenuStr(confSysUser);
		return new ModelAndView("admin/left", "leftMenuStr", leftMenuStr);
	}
	
	/**
	 * 查询所有管理用户
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="r", method=RequestMethod.POST)
	public @ResponseBody Pagination retrieve(PageBean pageBean,@ModelAttribute ConfSysUser confSysUser) {
		return confSysUserService.retrieve(pageBean,confSysUser);
	}
	
	@RequestMapping(value="/changepwd", method=RequestMethod.POST)
	public @ResponseBody String changePwd(HttpSession session, String oldPwd, String newPwd){
		ConfSysUser confSysUser = (ConfSysUser)session.getAttribute(Constant.BACK_SESSION_USER);
		return confSysUserService.changePwd(confSysUser, oldPwd, newPwd);
	}
	
	@RequestMapping(value="/checkUserName", method=RequestMethod.POST)
	public @ResponseBody boolean checkUserNameExists(String loginName) {
		return confSysUserService.checkUserNameExists(loginName);
	}
	
	/**
	 * 导入用户
	 * @param userIds 待导入的用户ID集合
	 * @return
	 */
	@RequestMapping(value="/importUser", method=RequestMethod.POST)
	public @ResponseBody String importUser(@RequestParam String userIds) {
		return confSysUserService.importUser(userIds);
	}
	
	@RequestMapping(value="/checkAdmin", method=RequestMethod.POST)
	public @ResponseBody boolean checkIsAdmin(HttpSession session) {
		// 从Session中获取用户信息
		ConfSysUser confSysUser = (ConfSysUser)session.getAttribute(Constant.BACK_SESSION_USER);
		return confSysUserService.checkAdmin(confSysUser);
	}
}
