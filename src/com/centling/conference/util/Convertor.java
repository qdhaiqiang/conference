package com.centling.conference.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centling.conference.base.Constant;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.entity.ConfScheduleUser;
import com.centling.conference.schedule.entity.frontSchel.Asset;
import com.centling.conference.schedule.entity.frontSchel.Date;
import com.centling.conference.schedule.entity.frontSchel.TimeLine;

public class Convertor {
	
	private static final Logger log = LoggerFactory
			.getLogger(Convertor.class);
	
	public static TimeLine  convertMeetingToTimeLine(ConfMeeting meeting,List<ConfScheduleUser> csuList,List<ConfSchedule> schedules,String locale,String userType){
		TimeLine tl = new TimeLine();
		
		if(Constant.LOCALE_US.equals(locale)){
			tl.setHeadline(meeting.getNameEn());
			tl.setText(meeting.getMeetingIntroEn());
		}else{
			tl.setHeadline(meeting.getName());
			tl.setText(meeting.getMeetingIntro());
		}
		tl.setStartDate(dateConvert(meeting.getStartTime()));
		tl.setType("default");
		//地点和日程相关？
		tl.setDate(getScheduleDate(schedules,csuList,locale,userType));
		
		return tl;
	}
	
	private static Set<Date> getScheduleDate(List<ConfSchedule> schedules,List<ConfScheduleUser> userSubscribedSchedule,String locale,String userType){
		
		log.debug("meeting scheduel id="+schedules);
		log.debug("user subscribe scheduel id="+userSubscribedSchedule);
		log.debug("operation user type="+userType);
		
		Set<Date> dates = new HashSet<Date>();
		boolean isSubscribed = false;
		boolean allowed = false;
		for(ConfSchedule sch:schedules){
			Date d = new Date();
			isSubscribed = false;
			
			for(ConfScheduleUser csu:userSubscribedSchedule){
				if(csu.getScheduleId().equals(sch.getId())){
					isSubscribed = true;
				}
			}
			
			
			if(isSubscribed){
				if("en_US".equals(locale)){
					d.setHeadline(sch.getTitleEn());
					d.setText((sch.getIntroEn()==null?"":sch.getIntro())+"<p>Join<input type='checkbox' checked='true' name='join' value='"+sch.getId()+"|"+sch.getTitleEn()+"|"+sch.getStartTime()+"-"+sch.getEndTime()+"'></p>");
				}else{
					d.setHeadline(sch.getTitle());
					d.setText((sch.getIntro()==null?"":sch.getIntro())+"<p>是否参加<input type='checkbox' checked='true' name='join' value='"+sch.getId()+"|"+sch.getTitle()+"|"+sch.getStartTime()+"-"+sch.getEndTime()+"'></p>");
				}
			}else{
				
				String[] audienceType = sch.getRestrictAudience()==null? new String[0]:sch.getRestrictAudience().trim().split(",");
				allowed = false;
				if(audienceType.length!=0){
					for(String type:audienceType){
						log.debug("allowed user type="+type);
						if(userType.equals(type)){
							allowed = true;
							break;
						}
					}
				}
				
				if("en_US".equals(locale)){
					d.setHeadline(sch.getTitleEn());
					//只在允许的类型产生checkbox
					if(allowed){
						d.setText((sch.getIntroEn()==null?"":sch.getIntro())+"<p>Join<input type='checkbox' name='join' value='"+sch.getId()+"|"+sch.getTitleEn()+"|"+sch.getStartTime()+"-"+sch.getEndTime()+"'></p>");
					}else{
						d.setText((sch.getIntroEn()==null?"":sch.getIntro()));
					}
				}else{
					d.setHeadline(sch.getTitle());
					if(allowed){
						d.setText((sch.getIntro()==null?"":sch.getIntro())+"<p>是否参加<input type='checkbox'  name='join' value='"+sch.getId()+"|"+sch.getTitle()+"|"+sch.getStartTime()+"-"+sch.getEndTime()+"'></p>");
					}else{
						d.setText((sch.getIntro()==null?"":sch.getIntro()));
					}
				}
			}
			
			
			d.setStartDate(dateConvert(sch.getStartTime()));
			d.setEndDate(dateConvert(sch.getEndTime()));
			
			Asset asset = new Asset();
			asset.setMedia(sch.getMediaUrl());
			d.setAsset(asset);
			dates.add(d);
		}
		return dates;
	}
	
	
	private static String dateConvert(String input){
		//2014/11/21 9:00 to 2014,11,21,9,00
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
		java.util.Date d = null;
		try {
			d = sdf.parse(input);
			return sdf2.format(d);
		} catch (ParseException e) {
			try {
				d = sdf0.parse(input);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return sdf2.format(d);
	}
}
