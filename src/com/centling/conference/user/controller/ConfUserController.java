package com.centling.conference.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.dict.service.ConfDictService;
import com.centling.conference.emailtemplate.service.ConfEmailTemplateService;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.meetinguser.service.ConfMeetingUserService;
import com.centling.conference.schedule.service.ConfScheduleUserService;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.util.CommonsMethod;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.Md5;
import com.centling.conference.util.QRCodeUtil;

@Controller("confUserController")
@RequestMapping("/user/*")
public class ConfUserController {
	
	//批量导入用户数据
	@RequestMapping(value= "/imp", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String imp ( @RequestParam MultipartFile myFile, HttpServletRequest request) throws Exception {
		if(myFile == null || myFile.getSize() == 0){
			return "文件格式错误，请上传.xls或.xlsx格式文件";
		}
		String fileType = myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."));
		if(!fileType.equals(".xls") && !fileType.equals(".xlsx")){
			return "文件格式错误，请上传.xls或.xlsx格式文件";
		}
		
		String path = CommonsMethod.getProjectPath() + File.separator;
		String fileattr = CommonsMethod.getNowCorrect2Millisecond() + myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."));
		final File targetFile = new File(path, fileattr);
		try {
			myFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<Object> dataList = new ArrayList<Object>();
		try { 
			dataList = confUserService.readExcel(targetFile.getAbsolutePath());
		} catch (Exception e) {
			return e.getMessage();
		}
		
		ConfGuest confGuest = null;
		List<ConfDict> sexDictList = confDictService.findDictByCategory("sex","2");
		List<ConfDict> nationDictList = confDictService.findDictByCategory("nation","2");
		List<ConfDict> certTypeDictList = confDictService.findDictByCategory("cert_type","2");
		List<ConfDict> industryDictList = confDictService.findDictByCategory("industry","2");
		
		for (Object object : dataList) {
			confGuest = (ConfGuest) object;
			try {
				confUserService.validateDicValue(confGuest.getNation(), nationDictList);
				confUserService.validateDicValue(confGuest.getCertType(), certTypeDictList);
				confUserService.validateDicValue(confGuest.getIndustry(), industryDictList);
				confGuest.setApproveState(Constant.APPROVE_PASS);
				confGuest.setUserType(Constant.USER_TYPE_ORG);
				confGuest.setMeetingId((String)request.getSession().getAttribute(Constant.SESSION_MEETING_ID));
				confGuest.setPassword(Md5.getMD5Str("666666"));
			} catch (Exception e) {
				return e.getMessage();
			}
		}
		
		for (Object object : dataList) {
			List userList = confUserService.findByEmail(((ConfGuest)object).getEmail().toLowerCase());
	        if (userList == null || userList.size() == 0) {
				confMeetingUserService.save((ConfGuest)object, "[]");
	        }
		}
		return "上传成功";
	}


	/**
	 * 发送确认函
	 * @param confUserId 用户编号
	 * @param type 1:提醒函 2:确认函
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(value="/mailSend",method=RequestMethod.POST)
	public @ResponseBody 
	String sendMail(@RequestParam String confUserId, @RequestParam String type, HttpSession sessoin) throws InterruptedException {
		String msg = "";
		String[] ids = confUserId.split(",");
        for (int i = 0; i < ids.length; i++ ) {
           confUserId = ids[i];
    		// 根据用户ID查询用户信息
    		ConfUser confUser = confUserService.findbyId(confUserId);
    		
    		if (confUser!=null) {
    			MailSenderService mailSenderService = MailSenderService.getInstance();
    			Email email = new Email();
    			email.setTo(new String[]{confUser.getEmail().toLowerCase()});
    			Map<String, Object> model = new HashMap<String, Object>();
    			model.put("user", confUser.getCname());
    			Calendar ca = Calendar.getInstance();
    			model.put("year", ca.get(Calendar.YEAR));
    			model.put("month", ca.get(Calendar.MONTH)+1);
    			model.put("day", ca.get(Calendar.DATE));
    			// 确认函
    			if (Constant.MAIL_CONFIRM.equals(type)) {
    				// 从Session中获取会议ID
    				String meetingId = (String)sessoin.getAttribute(Constant.SESSION_MEETING_ID);
    				// 根据meetingId和confUserId查询用户会议审批状态
    				ConfMeetingUser confMeetingUser = new ConfMeetingUser();
    				confMeetingUser.setMeetingId(meetingId);
    				confMeetingUser.setUserId(confUserId);
    				List<ConfMeetingUser> list = confMeetingUserService.findMeetingUser(confMeetingUser);
    				if (list!=null && !list.isEmpty()) {
    					// 得到用户会议审批状态
    					// 未审批或审批未通过，不发送邮件
    					if (!Constant.APPROVE_PASS.equals(list.get(0).getApproveState())) {
    						return "用户未审批或审批未通过，不能发送确认函";
    					}
    				} else {
    					return "用户未选择此会议，不能发送确认函";
    				}
    				// 得到二维码图片
    				String qrCodeUrl = QRCodeUtil.generateQrCode(confUserId);
    				email.setAttachName(new String[]{qrCodeUrl});
    				email.setInlineImg(new String[]{qrCodeUrl});
    				// 获取用户类型
    				String userType = list.get(0).getUserType();
    				List<Object[]> templateList = confEmailTemplateService.findByUserTypeAndName(Constant.EMAIL_TYPE_COMFIRM, userType);
    				if (templateList!=null && !templateList.isEmpty()) {
    					email.setSubject(templateList.get(0)[0].toString());
    					mailSenderService.setTemplateName(templateList.get(0)[1].toString());
    				} else {
    					email.setSubject("确认函");
    				}
    			// 提醒函
    			} else if(Constant.MAIL_NOTIFICATION.equals(type)) {
    				email.setSubject("提醒函");
    				mailSenderService.setTemplateName("notification.vm");
    			}
    			// 发送邮件
    			try {
    				mailSenderService.sendMail(email, model);
    				msg = "邮件发送成功";
    			} catch (Exception e) {
    				msg = "邮件发送失败";
    				e.printStackTrace();
    			}
    		}
		}
		return msg;
	} 
	
	/**
	 * 查询未导入后台用户表的工作人员列表
	 * @param confSysUser 查询条件
	 * @return
	 */
	@RequestMapping(value="/getUnimportUser", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getUnimportUser(@ModelAttribute ConfSysUser confSysUser, HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserService.getUnimportUser(confSysUser,meetingId);
	}
	
	/**
	 * Get the list of all users of current meeting
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/r",method=RequestMethod.POST)
	public @ResponseBody Pagination retrive(PageBean pageBean, HttpSession session, @ModelAttribute ConfUser confUser, String userType){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserService.retrive(pageBean, confUser, meetingId, userType);
	}
	
	/**
	 * 手动同步数据
	 * @return
	 */
	@RequestMapping(value="/dataSync", method=RequestMethod.POST)
	public @ResponseBody String syncData(HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		confUserService.syncData(meetingId);
		return "同步数据成功";
	}
	
	/**
	 * 获取全部用户
	 * @return
	 */
	@RequestMapping(value="/findUsers", method=RequestMethod.POST)
	public @ResponseBody Pagination findUsers(PageBean pageBean, ConfGuest user, HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserService.findUsers(pageBean, meetingId, user);
	}
	
	   
    //会议墙判断日程有没有演讲嘉宾信息
    @RequestMapping(value = "/findSpeakerCountBySchduelId/{schduelId}", method = RequestMethod.GET)
    public @ResponseBody String findSpeakerCountBySchduelId(@PathVariable String schduelId, HttpSession session){
        String meetingid = session.getAttribute(Constant.SESSION_MEETING_ID).toString();
        return confScheduleUserService.findSpeakerCountBySchduelId(meetingid, schduelId);
    }
    
   //会议墙显示会场的演讲嘉宾
   @RequestMapping(value = "/showSpeaker/{scheduleId}", method = RequestMethod.GET)
    public ModelAndView showMeetingGuest(@PathVariable String scheduleId, HttpSession session){
       String meetingid = session.getAttribute(Constant.SESSION_MEETING_ID).toString();
       Map<String, Object> map = new HashMap<String, Object>();
       List<Map> list = confScheduleUserService.findSpeakerBySchduelId(meetingid, scheduleId);
       map.put("scheduleId", scheduleId);
       map.put("list", list);
        return new ModelAndView("admin/meetingwall/guest_view", map);
    }
	
	@Resource
	private ConfUserService confUserService;
	
    /**
	 * 业务字典Service
	 */
	@Resource
	private ConfDictService confDictService;
	
	@Resource
	private ConfMeetingUserService confMeetingUserService;
	@Resource
	private ConfEmailTemplateService confEmailTemplateService;
	@Resource
	private ConfScheduleUserService confScheduleUserService;
}
