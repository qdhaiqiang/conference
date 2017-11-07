package com.centling.conference.schedulelog.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.schedulelog.DAO.ConfSchedulelogUserAssignDAO;
import com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign;
import com.centling.conference.user.DAO.ConfUserDAO;

/**
 * 分会场记-用户嘉宾指派
 * @author centling
 *
 */
@Service("confSchedulelogUserAssignService")
public class ConfSchedulelogUserAssignService {

	@Resource
	private ConfSchedulelogUserAssignDAO confSchedulelogUserAssignDAO;
	
	@Resource 
	private ConfUserDAO confUserDAO;
	
	/**
	 * 根据条件加载出，所有的用户所指派的日程，列表
	 * @param pageBean 分页条件
	 * @param userEmail 用户邮箱，查询条件（需要关联用户表）
	 * @param scheduleId 日程id，查询条件，前台的选择
	 * @return
	 */
	public Pagination findUers(PageBean pageBean,String userName,String userEmail,String scheduleId){
		Pagination pagination = new Pagination();
		List<Map<String,Object>> list = confSchedulelogUserAssignDAO.findUsers(pageBean, userName, userEmail, scheduleId);
		pagination.setRows(list);
		pagination.setTotal(confSchedulelogUserAssignDAO.count(userName, userEmail, scheduleId));
		return pagination;
	}
	
	
	/**
	 * 根据日程id查询演讲嘉宾
	 * @param scheduleId
	 * @return
	 */
	public List<Map<String, Object>> findSpeakersByScheduleId(String scheduleId){
		return confUserDAO.findSpeakersByScheduleId(scheduleId);
	}
	
	/**
	 * 新增、修改指派
	 * @param assign
	 */
	public void saveAssign(ConfSchedulelogUserAssign assign){
		confSchedulelogUserAssignDAO.merge(assign);
	}
	
	public void updateAssign(ConfSchedulelogUserAssign assign){
		ConfSchedulelogUserAssign newAssign = confSchedulelogUserAssignDAO.findById(assign.getId());
		newAssign.setSpeechTopic(assign.getSpeechTopic());
		newAssign.setSpeechOrder(assign.getSpeechOrder());
	}
	
	/**
	 * 删除指派
	 * @param id
	 */
	public void delAssign(String id){
		ConfSchedulelogUserAssign assign = confSchedulelogUserAssignDAO.findById(id);
		confSchedulelogUserAssignDAO.delete(assign);
	}
	
	
	
	/**
	 * 查询主讲嘉宾。
	 * @param scheduleId
	 * @return
	 */
	public List<Map<String,Object>> findMobileSpeakersByScheduleId(String scheduleId){
		return confSchedulelogUserAssignDAO.findMobileSpeakersByScheduleId(scheduleId);
	}
}
