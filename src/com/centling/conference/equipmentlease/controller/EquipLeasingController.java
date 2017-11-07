package com.centling.conference.equipmentlease.controller;

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
import com.centling.conference.equipmentlease.entity.ConfEquipLease;
import com.centling.conference.equipmentlease.service.EquipLeasingService;

@Controller("equipLeasingController")
@RequestMapping("/equipLease/*")
public class EquipLeasingController {
	
	@Resource
	EquipLeasingService equipLeasingService;

	//初期化设备租赁管理表
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody 
	Pagination retrieve (PageBean pageBean, @ModelAttribute ConfEquipLease equip, HttpSession session) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return equipLeasingService.retrive(pageBean, meetingId, equip);
	}
	
	//新增设备租赁项目
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfEquipLease equipLease, HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		equipLease.setMeetingId(meetingId);
		equipLeasingService.save(equipLease);
        return "添加成功";
    }
    
	//删除选中的设备租赁管理项目
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    String delete(@RequestParam String equipIds, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	int delCount = equipLeasingService.delete(equipIds, meetingId);
    	if (delCount > 0) {
    		return "删除成功";
        } else {
        	return "删除不成功，请刷新后重试";
        }
    }
    
    //更新设备租赁管理项目
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfEquipLease equipLease, HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		equipLease.setMeetingId(meetingId);
        equipLeasingService.update(equipLease);
        return "更新成功";
    }
}
