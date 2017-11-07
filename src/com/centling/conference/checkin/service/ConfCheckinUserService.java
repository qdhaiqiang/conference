package com.centling.conference.checkin.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.checkin.DAO.ConfCheckinUserDAO;
import com.centling.conference.checkin.DAO.ConfHeadsetUserDAO;
import com.centling.conference.checkin.entity.ConfCheckinUser;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.schedule.DAO.ConfScheduleUserDAO;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;

@Service("confCheckinUserService")
public class ConfCheckinUserService {

	@Resource
	private ConfCheckinUserDAO confCheckinUserDAO;
	@Resource
	private ConfUserDAO confUserDAO;
	@Resource
	private ConfScheduleUserDAO confScheduleUserDAO;
	@Resource
	private ConfMeetingUserDAO confMeetingUserDAO;
	@Resource
	private ConfHeadsetUserDAO confHeadsetUserDAO;

	// 通过二维码扫描签入
	public HashMap<String, Object> findUserForCheckIn(String userId, String meetingId, String scheduleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ConfUser confUser = null;
		List<ConfMeetingUser> confMeetingUser = confMeetingUserDAO.findUsersBymeetingIdAndUserId(userId, meetingId);
		if (confMeetingUser.size() == 0) {
			resultMap.put("OperatorState", "false");
			resultMap.put("Message", "该用户没有注册会议或没有通过审核。请联系组委会确认。");
		} else {
			List confScheduleUserList = confScheduleUserDAO.findByMeetingIdAndSchedule(userId, meetingId, scheduleId);
			if (confScheduleUserList != null) {
				if (confScheduleUserList.size() == 0) {
					resultMap.put("OperatorState", "false");
					resultMap.put("Message", "该用户没有注册该会议日程");
				} else {

					confUser = confUserDAO.findById(userId);
					resultMap.put("OperatorState", "true");
					resultMap.put("Message", "");
					resultMap.put("confUser", confUser);
					resultMap.put("userType", confMeetingUser.get(0).getUserType());
					// 从耳机表里查询
					List confHeadsetUser = confHeadsetUserDAO.findByUserIdAndMeetingId(userId, meetingId, scheduleId);

					if (confHeadsetUser.size() > 0) {
						resultMap.put("ifHeadset", "true");
					} else {
						resultMap.put("ifHeadset", "false");
					}

				}
			}
		}

		return resultMap;
	}

	// 通过邮件签入

	public HashMap<String, Object> findUserForCheckInByMail(String email, String meetingId, String scheduleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List confUserList = confUserDAO.findByEmail(email);
		if (confUserList != null && confUserList.size() != 0) {
			ConfUser confUser = (ConfUser) confUserList.get(0);
			return findUserForCheckIn(confUser.getId(), meetingId, scheduleId);
		} else {
			resultMap.put("OperatorState", "false");
			resultMap.put("Message", "该用户没有注册该会议日程");
			return resultMap;
		}

	}

	// 通过二维码扫描签出
	public HashMap<String, Object> findUserForCheckOut(String userId, String meetingId, String scheduleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		ConfUser confUser = null;
		List<ConfMeetingUser> confMeetingUser = confMeetingUserDAO.findUsersBymeetingIdAndUserId(userId, meetingId);
		if (confMeetingUser.size() == 0) {
			resultMap.put("OperatorState", "false");
			resultMap.put("Message", "该用户没有注册会议或没有通过审核。请联系组委会确认。");
		} else {
			List confScheduleUserList = confScheduleUserDAO.findByMeetingIdAndSchedule(userId, meetingId, scheduleId);
			if (confScheduleUserList != null) {
				if (confScheduleUserList.size() == 0) {
					resultMap.put("OperatorState", "false");
					resultMap.put("Message", "该用户没有注册该会议日程");

				} else {

					List confCheckinUserList = confCheckinUserDAO.findCheckinRecord(userId, meetingId, scheduleId);
					confUser = confUserDAO.findById(userId);
					if (confCheckinUserList != null) {
						if (confCheckinUserList.size() == 0) {
							resultMap.put("OperatorState", "false");
							resultMap.put("Message", "该用户没有正常签入");
							resultMap.put("confUser", confUser);

						} else {
							if (((ConfCheckinUser) confCheckinUserList.get(0)).getState() == Constant.CHECKIN_FAIL) {
								resultMap.put("OperatorState", "false");
								resultMap.put("Message", "该用户没有正常签入");
								resultMap.put("confUser", confUser);
							} else {
								List confHeadsetUser = confHeadsetUserDAO.findByUserIdAndMeetingId(userId, meetingId, scheduleId);

								if (confHeadsetUser.size() > 0) {
									resultMap.put("ifHeadset", "true");
								} else {
									resultMap.put("ifHeadset", "false");
								}
								resultMap.put("OperatorState", "true");
								resultMap.put("Message", "");
								resultMap.put("confUser", confUser);
								resultMap.put("userType", confMeetingUser.get(0).getUserType());
							}

						}
					}

				}
			}
		}
		return resultMap;
	}

	// 通过邮件签出

	public HashMap<String, Object> findUserForCheckOutByMail(String email, String meetingId, String scheduleId) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List confUserList = confUserDAO.findByEmail(email);
		if (confUserList != null && confUserList.size() != 0) {
			ConfUser confUser = (ConfUser) confUserList.get(0);
			return findUserForCheckOut(confUser.getId(), meetingId, scheduleId);
		} else {
			resultMap.put("OperatorState", "false");
			resultMap.put("Message", "该用户没有注册该会议日程");
			return resultMap;
		}

	}

	// public String saveCheckinUser(ConfCheckinUser checkInUser){
	// long currentTime = System.currentTimeMillis();
	// Date date = new Date(currentTime);
	// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	// String signinTime = df.format(date);
	// checkInUser.setCheckTime(signinTime);
	// Date datecopy = new Date(currentTime);
	// SimpleDateFormat dfcopy = new SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss");
	// String signinTimeCopy = dfcopy.format(datecopy);
	// checkInUser.setCheckTimeCopy(signinTimeCopy);
	// confCheckinUserDAO.save(checkInUser);
	// //用户有没有持有耳机
	// int ifHeadset = 0;
	// Object headSetSum =
	// confCheckinUserDAO.ifHeadSetRecord(checkInUser.getUserId(),
	// checkInUser.getMeetingId(),checkInUser.getScheduleId());
	// if(headSetSum != null){
	//
	// ifHeadset = Integer.valueOf(headSetSum.toString());
	// }
	//
	// if (checkInUser.getState().intValue() ==
	// Constant.CHECKIN_SUCESS.intValue() ) {
	// //如果是签入的话，需要把所有的签出记录都删除
	// List<ConfCurrcheckinUser> checkinuserList = confCurrcheckinUserDAO
	// .findByUserId(checkInUser.getMeetingId(),checkInUser.getUserId());
	// if (checkinuserList != null) {
	// if (checkinuserList.size() != 0) {
	// for (int i = 0; i < checkinuserList.size(); i++) {
	// confCurrcheckinUserDAO.delete(checkinuserList
	// .get(i));
	// }
	// }
	//
	// }
	// //如果是签出的话，需要把所有的签入记录都删除
	// List<ConfCurrcheckoutUser> checkoutuserList = confCurrcheckoutUserDAO
	// .findByUserId(checkInUser.getMeetingId(),checkInUser.getUserId());
	// if (checkoutuserList != null) {
	// if (checkoutuserList.size() != 0) {
	// for (int i = 0; i < checkoutuserList.size(); i++) {
	// confCurrcheckoutUserDAO.delete(checkoutuserList
	// .get(i));
	// }
	// }
	//
	// }
	//
	// if (checkInUser.getCheckState().intValue() ==
	// Constant.CHECKIN_STATE_IN.intValue()) {
	// ConfCurrcheckinUser curcheckinuser = new ConfCurrcheckinUser();
	// curcheckinuser.setCheckState(checkInUser.getCheckState());
	// ConfUser confUser = new ConfUser();
	// confUser.setId(checkInUser.getUserId());
	// curcheckinuser.setConfUser(confUser);
	// curcheckinuser.setIsheadset(ifHeadset);
	// curcheckinuser.setMeetingId(checkInUser.getMeetingId());
	// curcheckinuser.setOperator(checkInUser.getOperator());
	// curcheckinuser.setCheckTime(checkInUser.getCheckTime());
	// curcheckinuser.setCheckTimeCopy(checkInUser.getCheckTimeCopy());
	// curcheckinuser.setScheduleId(checkInUser.getScheduleId());
	// curcheckinuser.setState(checkInUser.getState());
	// List<ConfMeetingUser> confmeetingUser = confMeetingUserDAO
	// .findUsersBymeetingIdAndUserId(checkInUser.getUserId(),
	// checkInUser.getMeetingId());
	// if (confmeetingUser != null && confmeetingUser.size() != 0) {
	// curcheckinuser.setUserType(confmeetingUser.get(0)
	// .getUserType());
	// }
	// confCurrcheckinUserDAO.save(curcheckinuser);
	//
	// } else if(checkInUser.getCheckState().intValue() ==
	// Constant.CHECKIN_STATE_OUT.intValue()){
	// ConfCurrcheckoutUser curcheckoutuser = new ConfCurrcheckoutUser();
	// curcheckoutuser.setCheckState(checkInUser.getCheckState());
	// ConfUser confUser = new ConfUser();
	// confUser.setId(checkInUser.getUserId());
	// curcheckoutuser.setConfUser(confUser);
	// curcheckoutuser.setIsheadset(ifHeadset);
	// curcheckoutuser.setMeetingId(checkInUser.getMeetingId());
	// curcheckoutuser.setOperator(checkInUser.getOperator());
	// curcheckoutuser.setCheckTime(checkInUser.getCheckTime());
	// curcheckoutuser.setCheckTimeCopy(checkInUser.getCheckTimeCopy());
	// curcheckoutuser.setScheduleId(checkInUser.getScheduleId());
	// curcheckoutuser.setState(checkInUser.getState());
	// List<ConfMeetingUser> confmeetingUser = confMeetingUserDAO
	// .findUsersBymeetingIdAndUserId(checkInUser.getUserId(),
	// checkInUser.getMeetingId());
	// if (confmeetingUser != null && confmeetingUser.size() != 0) {
	// curcheckoutuser.setUserType(confmeetingUser.get(0)
	// .getUserType());
	// }
	// confCurrcheckoutUserDAO.save(curcheckoutuser);
	//
	//
	// }
	// }
	// return "Success";
	//
	// }

	public List findUsersByMeetingUsers(String userId, String meetingId) {
		return confMeetingUserDAO.findUsersBymeetingIdAndUserId(userId, meetingId);

	}

	public String saveCheckinUser(ConfCheckinUser checkInUser) {
		long currentTime = System.currentTimeMillis();
		Date date = new Date(currentTime);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String signinTime = df.format(date);
		checkInUser.setCheckTime(signinTime);
		Date datecopy = new Date(currentTime);
		SimpleDateFormat dfcopy = new SimpleDateFormat("yyyy/MM/dd/ hh:mm:ss");
		String signinTimeCopy = dfcopy.format(datecopy);
		checkInUser.setCheckTimeCopy(signinTimeCopy);
		confCheckinUserDAO.save(checkInUser);
		return "Success";

	}
}
