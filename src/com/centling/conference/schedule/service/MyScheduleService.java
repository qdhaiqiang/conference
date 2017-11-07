package com.centling.conference.schedule.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.centling.conference.schedule.DAO.MyScheduleDAO;
import com.centling.conference.schedule.entity.MeetingLocationModel;
import com.centling.conference.schedule.entity.MeetingScheduleModel;

/**
 * 手机端 我的日程
 * @author Ivy
 *
 */
@Service("myScheduleService")
public class MyScheduleService {
	@Resource
	private MyScheduleDAO myScheduleDAO;
	
    /**
     * 根据用户查询日程列表
     * @param userId 用户ID
     * @param meetingId 会议ID
     * @return
     */
	public List<MeetingLocationModel> findScheduleByUser(String meetingId,String userId,Locale local) {
		List<MeetingLocationModel> locaList = new ArrayList<MeetingLocationModel>();
		List list = myScheduleDAO.findMeetingSchedualByUser(meetingId, userId);
		if(list!=null&&!list.isEmpty()){
			//会议地点model
			MeetingLocationModel location= new MeetingLocationModel(); 
			List <MeetingScheduleModel> scheduleList = new ArrayList<MeetingScheduleModel>();
			//会议议程model
			MeetingScheduleModel schedule= new MeetingScheduleModel();
			String locationName = "";
			for(int i = 0;i<list.size();i++){
				Object[] curSche = (Object[]) list.get(i);
				if(i==0){
					locationName = (String) curSche[0];
					location.setLocation(locationName);
				}
				String curLocationName = (String) curSche[0];
				if((locationName==null&&curLocationName!=null)||(locationName!=null&&!locationName.equals(curLocationName))){
					locationName = curLocationName;
					//添加日程
					location.setSchedules(scheduleList);
					locaList.add(location);
					location= new MeetingLocationModel(); 
					location.setLocation(curLocationName);
					scheduleList = new ArrayList<MeetingScheduleModel>();
				}
				schedule = copyObjectToSchedule(curSche,local);
				scheduleList.add(schedule);
				if (i==list.size()-1){
					//添加日程
					location.setSchedules(scheduleList);
					locaList.add(location);
				}
			}
		}
		return locaList;
	}
	private MeetingScheduleModel copyObjectToSchedule(Object[] curSche,Locale local){
		MeetingScheduleModel model = new MeetingScheduleModel();
		String title = (String) curSche[1];
		String titleEn = (String) curSche[2];
		String startTime = (String) curSche[3];
		String endTime = (String) curSche[4];
		String intro = (String) curSche[5];
		String introEn = (String) curSche[6];
		if ("en_US".equals(local.toString())) {//英文
			model.setTitle(titleEn);
			model.setIntro(introEn);
		}else{//中文
			model.setTitle(title);
			model.setIntro(intro);
		}
		model.setStartTime(startTime);
		model.setEndTime(endTime);
		return model;
	}
}