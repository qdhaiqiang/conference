package com.centling.conference.checkin.controller;

import java.util.HashMap;
import java.util.List;

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
import com.centling.conference.checkin.entity.ConfSigninUser;
import com.centling.conference.checkin.service.ConfSigninUserService;
import com.centling.conference.sysuser.entity.ConfSysUser;

@Controller("confSigninUserController")
@RequestMapping("/signinuser/*")
public class ConfSigninUserController {
	
	

	  @RequestMapping(value = "/f/{userId}", method = RequestMethod.GET)
	    public @ResponseBody HashMap<String,Object> findByUserId(@PathVariable String userId, HttpSession httpSession) {
	        String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);   
	        return confSigninUserService.findByQCcode(userId, meetingId);
	   
	    }
	
	  @RequestMapping(value = "/mf", method = RequestMethod.GET)
	    public @ResponseBody HashMap<String,Object> findByUserNameandPhone(@RequestParam String mail, HttpSession httpSession) {
	        String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
	        return confSigninUserService.findByUserMail(mail, meetingId);
	    }
	
	  
	  @RequestMapping(value = "/s", method = RequestMethod.POST)
	    public @ResponseBody String SaveSigninUser(/*@RequestParam String mail,*/ @ModelAttribute ConfSigninUser signInuser, HttpSession httpSession) throws Exception {
		   ConfSysUser findSysUser = (ConfSysUser) httpSession.getAttribute(Constant.BACK_SESSION_USER);
		   String operator = findSysUser.getLoginName();
		   String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		   signInuser.setOperator(operator);
		   signInuser.setMeetingId(meetingId);
		   //signInuser.setOperator("will");
		   /*if(mail != null && mail.length() != 0){
			  List<ConfUser> userList =  confUserService.findByEmail(mail);
			  if(userList != null&&userList.size() != 0){
				  signInuser.setUserId(userList.get(0).getId()); 
			  }
			  
		   }*/
	        return confSigninUserService.saveSigninUser(signInuser);
	    }
	
	  @RequestMapping(value = "/selectSignUser", method = RequestMethod.POST)
	  public @ResponseBody Pagination selectSignUser(PageBean pageBean, @RequestParam String username, HttpSession httpSession){
		  String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		  
		 return confSigninUserService.findAllByMeetingId(pageBean, username,meetingId);
	  }
	  
	  //查询报到和注册某会议的人数
	  @RequestMapping(value = "/count", method = RequestMethod.GET)
	  public @ResponseBody HashMap<String,Object> count(HttpSession httpSession){
		  String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		  return confSigninUserService.count(meetingId);
	  }
	  
	@Resource
	private ConfSigninUserService confSigninUserService;
	
}
