package com.centling.conference.managermobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.managermobile.entity.ConfManagerMobile;
import com.centling.conference.managermobile.service.ManagerMobileService;

@Controller("managerMobileController")
@RequestMapping("/managerMobile/*")
public class ManagerMobileController {
	
	@Resource
	ManagerMobileService managerMobileService;

	//初期化设备租赁管理表
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody 
	Pagination retrieve (PageBean pageBean, HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return managerMobileService.retrive(pageBean, meetingId);
	}
	
	//新增嘉宾通知手机号
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfManagerMobile mobile, HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		mobile.setMeetingId(meetingId);
		managerMobileService.save(mobile);
        return "添加成功";
    }
	
	//更新领导手机号
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfManagerMobile mobile, HttpSession session) throws Exception {
	    String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
	    mobile.setMeetingId(meetingId);
	    managerMobileService.update(mobile);
	    return "更新成功";
	}
    
	//删除选中的电话记录
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam String ids, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	int delCount = managerMobileService.delete(ids, meetingId);
    	if (delCount > 0) {
    		return "删除成功";
        } else {
        	return "删除不成功，请刷新后重试";
        }
    }
}
