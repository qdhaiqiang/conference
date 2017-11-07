package com.centling.conference.schedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.schedule.DAO.ConfScheduleDAO;
import com.centling.conference.schedule.DAO.ConfScheduleUserDAO;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.entity.ConfScheduleUser;

@Service("confScheduleUserService")
public class ConfScheduleUserService {
	
	private static final Logger log = LoggerFactory
			.getLogger(ConfScheduleUserService.class);

	@Resource
	private ConfScheduleUserDAO confScheduleUserDAO;
	
	@Resource
	private ConfMeetingUserDAO confMeetingUserDAO;
	
	@Resource
	private ConfScheduleDAO confScheduleDAO;
	
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Map<String,String> subscribeOrUpdate(List<String> box,String meetingId,String userId) {
		log.info("update user subscribe schedlue");
		Map<String,String> result = new HashMap<String,String>();
		
		List<ConfMeetingUser> meetingUsers = confMeetingUserDAO.findByUserId(userId);
		if(meetingUsers==null||meetingUsers.size()==0){
			result.put("failed", "user not found");
			return result;
		}
		
		//check user type if is audience
		String userType = meetingUsers.get(0).getUserType();
		if(Constant.USER_TYPE_AUDIENCE_EN.equals(userType)||Constant.USER_TYPE_AUDIENCE.equals(userType)){
			//if so, check number limit
			for(String scheId:box){
				ConfSchedule schedule = confScheduleDAO.findById(scheId);
				if(schedule.getMaxAudience()==null||schedule.getMaxAudience()==0){
					//not set max value
					continue;
				}
				
				if(schedule.getCurrentAudience()>=schedule.getMaxAudience()){
					result.put("falied", schedule.getTitle()+"/"+schedule.getTitleEn()+" exceed max quota");
					return result;
				}
			}
			
		}
		
		List<ConfScheduleUser> csuList = confScheduleUserDAO.findByMeetingId(meetingId);
		//remove all already saved
		for(ConfScheduleUser csu_exist:csuList){
			if(userId.equals(csu_exist.getUserId()))
				confScheduleUserDAO.delete(csu_exist);
		}
		log.info("clean user subscribe schedlue done....");
		//insert new ones
		for(String scheId:box){
			ConfScheduleUser csu = new ConfScheduleUser(userId,scheId,meetingId);
			confScheduleUserDAO.save(csu);
			//add audience number
			ConfSchedule schedule = confScheduleDAO.findById(scheId);
			rwl.readLock().lock();
			int num = schedule.getCurrentAudience()+1;
			rwl.readLock().unlock();
			rwl.writeLock().lock();
			schedule.setCurrentAudience(num);
			rwl.writeLock().unlock();
			confScheduleDAO.update(schedule);
			
			
		}
		result.put("success", "true");
		return result;
	}
	
	public List<ConfScheduleUser> findByUserAndMeeting(String userId,String meetingId){
		return confScheduleUserDAO.findByUserAndMeeting(userId, meetingId);
	}
	
	//（查找日程中演讲嘉宾的人数）会议墙
	public String findSpeakerCountBySchduelId(String meetingId, String scheduleId) {
	    return confScheduleUserDAO.findSpeakerCountBySchduelId(meetingId, scheduleId);
	}
	
	//（查找日程中演讲嘉宾信息）会议墙
	public List<Map> findSpeakerBySchduelId(String meetingId, String scheduleId) {
	    return confScheduleUserDAO.findSpeakerBySchduelId(meetingId, scheduleId);
	}
}
