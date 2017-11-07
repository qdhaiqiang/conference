package com.centling.conference.exclusiveschedule.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exclusiveschedule.DAO.ConfExclusiveScheduleDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;

/**
 * 专属日程Service
 * @author lizzh
 *
 */
@Service("confExclusiveScheduleService")
public class ConfExclusiveScheduleService {
	@Resource
	private ConfExclusiveScheduleDAO confExclusiveScheduleDAO;
	
    //页面初始化数据取得
    public Pagination retrieve(PageBean pageBean, String meetingId, ConfGuest guest) {
        Pagination pagination = new Pagination();
        if (selectedVal(guest.getUserType()).equals("")) {
            guest.setUserType("");
        }
        List<Map> list = confExclusiveScheduleDAO.retrive(pageBean, meetingId, guest);
        pagination.setRows(list);
        pagination.setTotal(confExclusiveScheduleDAO.count(meetingId, guest));
        return pagination;
    }
	
	/**
	 * 查询专属日程
	 */
	public Map<String, Object> getExculScheInfo(String meetingId, String guestId){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("scheduleInfo", confExclusiveScheduleDAO.getScheduleInfo(guestId, meetingId));
		model.put("eventInfo", confExclusiveScheduleDAO.getEventInfo(guestId, meetingId));
		model.put("vsInfo", confExclusiveScheduleDAO.getVsInfo(guestId, meetingId));
		return model;
	}
	
    //判断选择型检索条件是否有被选中
    public String selectedVal(String val){
        if (val == null || val.equals("") || val.length() == 0) {
            return "";
        } else {
            if (val.startsWith(",")){
                return val.substring(1, val.length());
            } else if (val.equals(",")) {
                return "";
            } else {
                return val;
            }
        }
    }
}
