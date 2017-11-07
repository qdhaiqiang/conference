package com.centling.conference.meeting.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.meeting.DAO.ConfMeetingDAO;
import com.centling.conference.meeting.DAO.ConfVMeetingIndexDAO;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meeting.entity.ConfVMeetingIndexId;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.schedule.DAO.ConfScheduleDAO;
import com.centling.conference.schedule.DAO.ConfScheduleUserDAO;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.entity.ConfScheduleUser;
import com.centling.conference.schedule.entity.frontSchel.TimeLine;
import com.centling.conference.util.Convertor;

@Service("meetingRevice")
public class ConfMeetingService {

    @Resource
    private ConfMeetingDAO confMeetingDAO;
    @Resource
    private ConfVMeetingIndexDAO confVMeetingIndexDAO;
    @Resource
    private ConfScheduleDAO confScheduleDAO;
    @Resource
    private ConfMeetingUserDAO confMeetingUserDAO;
    
    @Resource
    private ConfScheduleUserDAO confScheduleUserDAO;
    
    public Pagination mainRetrieve(PageBean pageBean ) {
        Pagination pagination = new Pagination();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String sysDate = format.format(new Date());
        
        List<ConfVMeetingIndexId> list = confVMeetingIndexDAO.retrieve(pageBean, sysDate);
        pagination.setRows(list);
        pagination.setTotal(confVMeetingIndexDAO.count(sysDate) +"");
        return pagination;
    }
    
    public Pagination retrieve(PageBean pageBean ) {
        Pagination pagination = new Pagination();
        List<ConfMeeting> list = confMeetingDAO.retrieve(pageBean);
        for (int i = 0;i < list.size();i++) {
            ConfMeeting meeting = list.get(i);
            if (meeting.getCity() != null && meeting.getCity().equals(Constant.MEETING_CITY_CODE1)) {
                meeting.setCity("澳门");
            } else if (meeting.getCity() != null && meeting.getCity().equals(Constant.MEETING_CITY_CODE2)) {
                meeting.setCity("北京");
            } else if (meeting.getCity() != null && meeting.getCity().equals(Constant.MEETING_CITY_CODE3)) {
                meeting.setCity("上海");
            } else if (meeting.getCity() != null && meeting.getCity().equals(Constant.MEETING_CITY_CODE4)) {
                meeting.setCity("株洲");
            }
        }
        pagination.setRows(list);
        pagination.setTotal(confMeetingDAO.count() +"");
        return pagination;
    }
    
    //根据ID查询会议
    public ConfMeeting findById(String id) {
        return confMeetingDAO.findById(id);
    }
    
    //查找所有会议
    public List<ConfMeeting> findAll() {
        return confMeetingDAO.findAll();
    }
    
    //新增会议
    public void save(ConfMeeting instance){
        confMeetingDAO.save(instance);
    }
    
    //更新会议
    public void update(ConfMeeting instance) {
        confMeetingDAO.update(instance);
    }
    
    //删除会议
    public void delete(ConfMeeting instance) {
        confMeetingDAO.delete(instance);
    }
    
    public TimeLine findMeetings(String meetingId,String locale,String userId){
        ConfMeeting cm = confMeetingDAO.findById(meetingId);
        
        
        ConfMeetingUser example = new ConfMeetingUser();
        example.setMeetingId(meetingId);
        example.setUserId(userId);
        
        ConfMeetingUser confMeetingUser = confMeetingUserDAO.findByExample(example).get(0);
        List<ConfSchedule> schedules = confScheduleDAO.findGeneralByMeetingId(meetingId);
        List<ConfScheduleUser> csuList = confScheduleUserDAO.findByUserId(userId);
        String userType = confMeetingUser.getUserType();
        
        return Convertor.convertMeetingToTimeLine(cm,csuList, schedules,locale,userType);
    }

    public List<ConfMeeting> findByProperty(String propertyName, Object value) {
    	return confMeetingDAO.findByProperty(propertyName, value);
    }
    /**
     * 根据会议ID查询未创建动态表单的用户类型
     * @param meetingId 会议ID
     * @return 查询到的动态表单列表
     */
	public List<ConfDict> findDictByMeetingId(String meetingId) {
		return confMeetingDAO.findDictByMeetingId(meetingId);
	}
}
