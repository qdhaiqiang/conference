package com.centling.conference.checkin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.checkin.DAO.ConfHeadsetUserDAO;
import com.centling.conference.checkin.entity.ConfHeadsetUser;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Service("confHeadsetUserService")
public class ConfHeadsetUserService {

	@Resource
	private ConfHeadsetUserDAO confHeadsetUserDAO;

	public Pagination retrieve(PageBean pageBean, String meetingId,
			String scheduleId, ConfGuest user) {
		Pagination pagination = new Pagination();
		pagination.setRows(confHeadsetUserDAO.retrieve(pageBean,
				meetingId, scheduleId, user));
		pagination.setTotal(confHeadsetUserDAO.count(meetingId, user,
				scheduleId) + "");
		return pagination;
	}

	public String save(ConfHeadsetUser user) {
		confHeadsetUserDAO.save(user);
		return "SUCCESS";
	}

	public String delete(ConfHeadsetUser user) {
		confHeadsetUserDAO.delete(user);
		return "SUCCESS";
	}
	
	public List findByuserIdandMeetingId(String userId, String meetingId, String scheduleId){
		return confHeadsetUserDAO.findByUserIdAndMeetingId( userId, meetingId, scheduleId);
	}

}
