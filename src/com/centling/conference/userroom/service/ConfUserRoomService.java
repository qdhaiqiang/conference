package com.centling.conference.userroom.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.userroom.DAO.ConfUserRoomDAO;
import com.centling.conference.userroom.entity.ConfUserRoom;

@Service("confUserRoomService")
public class ConfUserRoomService {

	@Resource
	private ConfUserRoomDAO confUserRoomDAO;
	@Resource
	private ConfMeetingUserDAO confMeetingUserDAO;
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<ConfUserRoom> findRoomRulesByMeetingId(String meetingId){
		return confUserRoomDAO.findByMeetingId(meetingId);
	}
	
	/**
	 * 保存用户类型房型信息
	 * @return
	 */
	public void save(ConfUserRoom userRoom){
		confUserRoomDAO.merge(userRoom);
	}
	
	/**
	 * 根据meetingId查询出尚未分配房型的用户类型
	 * @param meetingId
	 * @return
	 */
	public List<ConfDict> findShowDictByMeetingId(String meetingId){
		return confUserRoomDAO.findShowDictByMeetingId(meetingId);
	}
	
	/**
	 * @param ids
	 * @return
	 */
	public int deleteByIds(String ids){
		return confUserRoomDAO.deleteById(ids);
	}
	
	/**
	 * @param ids
	 * @return
	 */
	public int deleteById(String id){
		confUserRoomDAO.delete(confUserRoomDAO.findById(id));
		return 1;
	}
	
	/**
     * 给参会人员自动分配房型、主办方是否承担费用信息
     * @param meetingId
     */
	public void updateRoomInfo(String meetingId){
		//先删除已保存的信息
//		confMeetingUserDAO.deleteRoomInfo(meetingId);
		confMeetingUserDAO.updateRoomInfo(meetingId);
	}
	
	
	/**
	 * 查询本次会议参会人员基本信息以及房型信息。
	 * 
	 * @param meetingId
	 */
	public List<Map<String, Object>> findUserRoomInfo(String meetingId) {
		return confMeetingUserDAO.findUserRoomInfo(meetingId);
	}
	
	/**
	 * 给参会人员自动分配房型、主办方是否承担费用信息
	 * 
	 * @param meetingId
	 */
	public Pagination findRoomInfoPages(PageBean pageBean, String meetingId,String sname, String semail, String suserType) {
		Pagination pagination = new Pagination();
		pagination.setRows(confMeetingUserDAO.findRoomInfoPages(pageBean,meetingId,sname,semail,suserType));
		pagination.setTotal(confMeetingUserDAO.roomCount(meetingId,sname,semail,suserType));
		return pagination; 
	}
	
	/**
	 * 根据邮箱和会议id查找用户信息
	 * @param email
	 * @param meetingId
	 * @return
	 */
	public List<Map<String,Object>> findUserInfoByEmail(String meetingId,String email){
		return confMeetingUserDAO.findUserInfoByEmail(meetingId, email);
	}
	
	/**
	 * 单独保存给参会人员自动分配房型、主办方是否承担费用信息
	 * 
	 * @param meetingId
	 */
	public void updateUserRoom(ConfMeetingUser user) {
		confMeetingUserDAO.updateUserRoom(user);
	}

	/**
	 * 根据meeting_user 的id，清楚房间信息
	 * @param id
	 */
	public void delUserRoom(String id){
		confMeetingUserDAO.delUserRoom(id);
	}
	
	/**
	 * 房间类型用户类型统计
	 * @param meetingId
	 */
	public List<Map<String,Object>> findRoomStat(String startDate, String endDate, String meetingId){
		 return confMeetingUserDAO.roomStat(startDate,endDate,meetingId);
	}
	
	
	/**
	 * 自动分配入住时间和离开时间，内容从航班信息表中取得
	 * @param meetingId
	 */
	public void updateCheckDate(String meetingId){
		confMeetingUserDAO.updateCheckDate(meetingId);
	}
}
