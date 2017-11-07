package com.centling.conference.checkin.controller;

import java.io.File;
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
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.checkin.entity.ConfCheckinUser;
import com.centling.conference.checkin.entity.ConfCurrcheckinUser;
import com.centling.conference.checkin.entity.ConfHeadsetUser;
import com.centling.conference.checkin.service.ConfCheckinUserService;
import com.centling.conference.checkin.service.ConfCurrcheckinUserService;
import com.centling.conference.checkin.service.ConfHeadsetUserService;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.meetinguser.service.ConfMeetingUserService;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.util.QRCodeUtil;

@Controller("confCheckinUserController")
@RequestMapping("/checkinuser/*")
public class ConfCheckinUserController {

	@RequestMapping(value = "/fforCheckin/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> findByUserIdforCheckin(@PathVariable String userId, HttpSession httpSession) {
		String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		String scheduleId = (String) httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);
		return confCheckinUserService.findUserForCheckIn(userId, meetingId, scheduleId);

	}

	@RequestMapping(value = "/fforCheckout/{userId}", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> findByUserIdforCheckout(@PathVariable String userId, HttpSession httpSession) {
		String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		String scheduleId = (String) httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);
		return confCheckinUserService.findUserForCheckOut(userId, meetingId, scheduleId);

	}

	@RequestMapping(value = "/fforCheckinbymail", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> findByUserIdforCheckinByMail(@RequestParam String mail, HttpSession httpSession) {
		String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		String scheduleId = (String) httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);

		return confCheckinUserService.findUserForCheckInByMail(mail, meetingId, scheduleId);

	}

	@RequestMapping(value = "/fforCheckoutbymail", method = RequestMethod.GET)
	public @ResponseBody
	HashMap<String, Object> findByUserIdforCheckoutByMail(@RequestParam String mail, HttpSession httpSession) {
		String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		String scheduleId = (String) httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);
		return confCheckinUserService.findUserForCheckOutByMail(mail, meetingId, scheduleId);

	}

	@RequestMapping(value = "/s", method = RequestMethod.GET)
	public @ResponseBody
	String saveCheckInUser(@ModelAttribute ConfCheckinUser checkInUser, HttpSession httpSession) {
		ConfSysUser findSysUser = (ConfSysUser) httpSession.getAttribute(Constant.BACK_SESSION_USER);
		String operator = findSysUser.getLoginName();
		String meetingId = (String) httpSession.getAttribute(Constant.SESSION_MEETING_ID);
		String scheduleId = (String) httpSession.getAttribute(Constant.SESSION_SCHEDULE_ID);
		checkInUser.setOperator(operator);
		checkInUser.setMeetingId(meetingId);
		checkInUser.setScheduleId(scheduleId);
		confCheckinUserService.saveCheckinUser(checkInUser);

		List<ConfMeetingUser> confmeetingUser = confCheckinUserService.findUsersByMeetingUsers(checkInUser.getUserId(), checkInUser.getMeetingId());

		if (checkInUser.getState().intValue() == Constant.CHECKIN_SUCESS.intValue()) {
			// TODO - 查询增加日程ID
			List<ConfCurrcheckinUser> currCheckinUserlist = confCurrcheckinUserService.findBymeetingIdAndUserId(checkInUser.getMeetingId(), checkInUser.getUserId(),checkInUser.getScheduleId());
			if (checkInUser.getCheckState().intValue() == Constant.CHECKIN_STATE_IN.intValue()) {
				if (currCheckinUserlist != null) {
					if (currCheckinUserlist.size() != 0) {
						currCheckinUserlist.get(0).setCheckTime(checkInUser.getCheckTime());
						currCheckinUserlist.get(0).setCheckTimeCopy(checkInUser.getCheckTimeCopy());
						if (checkInUser.getIsHeadset().intValue() == Constant.YES_HEADSET.intValue()) {
							currCheckinUserlist.get(0).setIsheadset(Constant.YES_HEADSET);
						}
						confCurrcheckinUserService.updateCurrcheckinUser(currCheckinUserlist.get(0));
					} else {
						ConfUser confUser = new ConfUser();
						ConfCurrcheckinUser curcheckinuser = new ConfCurrcheckinUser();
						confUser.setId(checkInUser.getUserId());
						curcheckinuser.setConfUser(confUser);
						curcheckinuser.setIsheadset(checkInUser.getIsHeadset());
						curcheckinuser.setMeetingId(checkInUser.getMeetingId());
						curcheckinuser.setOperator(checkInUser.getOperator());
						curcheckinuser.setCheckTime(checkInUser.getCheckTime());
						curcheckinuser.setCheckTimeCopy(checkInUser.getCheckTimeCopy());
						curcheckinuser.setScheduleId(checkInUser.getScheduleId());
						curcheckinuser.setState(checkInUser.getState());
						curcheckinuser.setCheckState(checkInUser.getCheckState());

						if (confmeetingUser != null && confmeetingUser.size() != 0) {
							curcheckinuser.setUserType(confmeetingUser.get(0).getUserType());
						}
						confCurrcheckinUserService.saveCurrcheckinUser(curcheckinuser);
					}
				}
				if (checkInUser.getIsHeadset().intValue() == Constant.YES_HEADSET.intValue()) {
					// 往耳机表里添加该人的信息
					ConfHeadsetUser headsetUser = new ConfHeadsetUser();
					ConfUser confuser = confUserService.findbyId(checkInUser.getUserId());
					if (confuser != null) {
						headsetUser.setUserId(checkInUser.getUserId());
						headsetUser.setEmail(confuser.getEmail());
						headsetUser.setPhone(confuser.getMobile());
						headsetUser.setCname(confuser.getCname());
						headsetUser.setEname(confuser.getEname());
						if (confmeetingUser != null && confmeetingUser.size() != 0) {
							headsetUser.setUserType(confmeetingUser.get(0).getUserType());
						}
						headsetUser.setMeetingId(checkInUser.getMeetingId());
						headsetUser.setScheduleId(checkInUser.getScheduleId());
						confHeadsetUserService.save(headsetUser);
					}
				}
			} else if (checkInUser.getCheckState().intValue() == Constant.CHECKIN_STATE_OUT.intValue()) {
				if (currCheckinUserlist != null) {
					if (currCheckinUserlist.size() != 0) {
						confCurrcheckinUserService.deleteCurrcheckinUser(currCheckinUserlist.get(0));
					}
					if (checkInUser.getIsHeadset().intValue() == Constant.NO_HEADSET.intValue()) {
						List confHeadsetUserlist = confHeadsetUserService.findByuserIdandMeetingId(checkInUser.getUserId(), checkInUser.getMeetingId(), checkInUser.getScheduleId());
						if (confHeadsetUserlist.size() != 0) {
							confHeadsetUserService.delete((ConfHeadsetUser) confHeadsetUserlist.get(0));
						}
					}
				}
			}
		}
		return "Success";
	}

	@RequestMapping(value = "/checkinSelectSchedule/{scheduleId}", method = RequestMethod.GET)
	public @ResponseBody
	String checkinSelectSchedule(@PathVariable String scheduleId, HttpSession httpSession) {
		httpSession.setAttribute(Constant.SESSION_SCHEDULE_ID, scheduleId);
		return "success";
		// return new ModelAndView(new
		// RedirectView("../admin/checkinandout/checkin.jsp"));
	}

	@RequestMapping(value = "/checkoutSelectSchedule/{scheduleId}", method = RequestMethod.GET)
	public @ResponseBody
	String checkoutSelectSchedule(@PathVariable String scheduleId, HttpSession httpSession) {
		httpSession.setAttribute(Constant.SESSION_SCHEDULE_ID, scheduleId);
		return "success";
		// return new ModelAndView(new
		// RedirectView("../admin/checkinandout/checkin.jsp"));
	}

	// 保存临时制证人员
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String add(@ModelAttribute ConfGuest guest, @RequestParam String schData, HttpSession session) throws Exception {
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		guest.setMeetingId(meetingId);
		// 验证邮箱是否已经存在
		List<ConfUser> userList = confUserService.findByEmail(guest.getEmail());
		if (userList.size() > 0) {
			return "该邮箱已存在";
		}
		String userId = confMeetingUserService.save(guest, schData);
		return "添加成功" + userId;
	}

	// 临时制证生成二维码
	@RequestMapping(value = "/createQR/{userId}", method = RequestMethod.GET)
	public ModelAndView getFormByUserTypeReturnModelValue(@PathVariable String userId, HttpServletRequest request) {
		String meetingId = (String) request.getSession().getAttribute(Constant.SESSION_MEETING_ID);
		// 生成二维码路径
		// String logoPathAndName = request.getServletContext().getRealPath("/")
		// + "images"+File.separator+"logo.png";
		String qrCodePath = request.getServletContext().getRealPath("/") + "images" + File.separator + "icons" + File.separator;
		String QRimagePath = QRCodeUtil.generateQrCode(userId, null, qrCodePath);
		String qrCodeUrl = QRimagePath.replace(request.getServletContext().getRealPath("/"), "");
		ConfUser user = confUserService.findbyId(userId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("meetingId", meetingId);
		map.put("cname", user.getCname());
		map.put("ename", user.getEname());
		map.put("sex", user.getSex());
		map.put("nation", user.getNation());
		map.put("cardType", user.getCardType());
		map.put("qrCodeUrl", qrCodeUrl);

		return new ModelAndView("admin/signin/showQRimage", map);
	}

	@Resource
	private ConfCheckinUserService confCheckinUserService;
	@Resource
	private ConfUserService confUserService;
	@Resource
	private ConfMeetingUserService confMeetingUserService;
	@Resource
	private ConfCurrcheckinUserService confCurrcheckinUserService;
	@Resource
	private ConfHeadsetUserService confHeadsetUserService;

}
