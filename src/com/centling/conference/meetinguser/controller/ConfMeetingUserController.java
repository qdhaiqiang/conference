package com.centling.conference.meetinguser.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.meetinguser.service.ConfMeetingUserService;
import com.centling.conference.travel.entity.SearchParams;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.util.exportexcels.ExportExcelsUtil;

@Controller("confMeetingUserController")
@RequestMapping("/meetingUser/*")
public class ConfMeetingUserController {
	private static final Logger log = LoggerFactory.getLogger(ConfMeetingUserController.class);
    @Resource
    private ConfMeetingUserService confMeetingUserService;
    @Resource
    private ConfUserService confUserService;
    
    @RequestMapping(value = "/r", method = RequestMethod.POST)
    public @ResponseBody Pagination retrieve(PageBean pageBean, @ModelAttribute ConfGuest guest,  HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	return confMeetingUserService.retrieve(pageBean, meetingId, guest);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfGuest guest, @RequestParam String schData, HttpSession session) throws Exception {
      String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        guest.setMeetingId(meetingId);
        //验证邮箱是否已经存在
        List<ConfUser> userList = confUserService.findByEmail(guest.getEmail());
        if (userList.size() > 0) {
            return "该邮箱已存在";
        }
        confMeetingUserService.save(guest, schData);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody String delete(@RequestParam String userIds, HttpSession session) throws Exception {
    	// 从session中获取会议ID
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	String[] ids = userIds.split(",");
        confMeetingUserService.delete(ids,meetingId);
        return "删除成功";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfGuest guest, @RequestParam String schData, HttpSession httpSession) throws Exception {
        String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        guest.setMeetingId(meetingId);
        //验证用户类型变更是否允许
        boolean allow = confMeetingUserService.userTypeUpdateCheck(guest, meetingId);
        if (!allow) {
            return "不允许修改用户类型";
        }
        confMeetingUserService.update(guest, schData);
        return "更新成功";
    }
    
    @RequestMapping(value = "/findByMeetingId", method = RequestMethod.GET)
    public @ResponseBody ConfMeetingUser findById(HttpSession httpSession) {
        String id = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        ConfMeetingUser meeting = confMeetingUserService.findById(id);
        return meeting;
    }
    
    //用户信息审核通过
    @RequestMapping(value = "/updateApproveOK", method = RequestMethod.POST)
    public @ResponseBody
    String updateApproveOK(@ModelAttribute ConfGuest guest, HttpSession httpSession) throws Exception {
        String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        guest.setMeetingId(meetingId);
        guest.setApproveState(Constant.APPROVE_PASS);
        confMeetingUserService.updateApprove(guest);
        return "审核状态已改为审核通过。";
    }
    
    //用户信息审核不通过
    @RequestMapping(value = "/updateApproveNotOK", method = RequestMethod.POST)
    public @ResponseBody
    String updateApproveNotOK(@ModelAttribute ConfGuest guest, HttpSession httpSession) throws Exception {
        String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        guest.setMeetingId(meetingId);
        guest.setApproveState(Constant.APPROVE_FAIL);
        confMeetingUserService.updateApprove(guest);
        return "审核状态已改为未通过。";
    }
    
    //批量审核
    @RequestMapping(value="/approveCheckedUser", method = RequestMethod.GET)
    public @ResponseBody 
        String approveCheckeduser(@ModelAttribute ConfGuest guest, @RequestParam String snNumbers, HttpSession httpSession) {
        String[] ids = snNumbers.split(",");
        String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
        confMeetingUserService.approveCheckedUser(ids, meetingId);
        return ids.length + "条用户审核状态已改为审核通过。";
    }
    
    /**
     * 发送提醒函
     * @param session
     * @return
     */
    @RequestMapping(value="/remindMailSend", method = RequestMethod.GET)
    public @ResponseBody String sendMail(HttpSession session) {
    	// 获取会议ID
    	String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
    	try {
    		confMeetingUserService.sendRemindMail(meetingId);
    	} catch (Exception e) {
    		log.error(e.getMessage());
    		return "邮件发送失败";
    	}
    	return "邮件发送成功";
    }
    
    /**
	 * 酒店信息导出
	 * @return
     * @throws IOException 
	 */
	@RequestMapping(value="/exportRoomStatic",method=RequestMethod.POST)
	public @ResponseBody String exportRoomStatic(@ModelAttribute SearchParams searchParams,HttpSession session,HttpServletResponse response) throws IOException {
		String[] headers = new String[]{"姓名","性别","出生日期","职务","用户类型","证件号码","联系方式","房间类型","入住日期","退房日期","入住天数","备注"};
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID); 
		List<Object[]> dataset = confMeetingUserService.getRoomStatic(meetingId,searchParams);
        if(dataset == null || dataset.size() < 1){
        	return "没有查找到相应数据";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=exportRoom.xls");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			ExportExcelsUtil.exportExcel(headers,dataset, sos);
			response.flushBuffer();
		} catch (IOException e) {
			log.error("导出酒店数据出现异常"+e.getMessage());
			throw e;
		}
        return "成功导出"+dataset.size()+"条酒店数据。";
	}
	
	/**
	 * 酒店信息查询
	 * @return
	 */
	@RequestMapping(value="/getRoomStatic",method=RequestMethod.POST)
	public @ResponseBody Pagination getRoomStatic(PageBean pageBean,@ModelAttribute SearchParams searchParams,HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID); 
		return confMeetingUserService.getRoomInfo(pageBean,meetingId,searchParams);
	}
}
