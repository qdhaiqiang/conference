package com.centling.conference.question.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.Pagination;
import com.centling.conference.question.DAO.ConfQuestionDAO;
import com.centling.conference.question.entity.ConfQuestion;
import com.centling.conference.schedule.DAO.ConfScheduleUserDAO;
import com.centling.conference.util.DateUtils;

@Service("questionService")
public class QuestionService {
	
	@Resource
	ConfQuestionDAO confQuestionDAO;
	@Resource
	ConfScheduleUserDAO confScheduleUserDAO;
	
	/**
	 * 根据会议id和日程id查询现场提问
	 * @param meetingId
	 * @param scheduleId
	 */
	public Pagination findQuestions(String meetingId, String scheduleId){
		Pagination pagination = new Pagination();
		pagination.setRows(confQuestionDAO.findQuestions(meetingId, scheduleId));
		pagination.setTotal(confQuestionDAO.findCount(meetingId, scheduleId));
		return pagination;
	}
	
	
	/**
	 * 新增或修改 问题
	 * @param question
	 */
	public void saveQuestion(ConfQuestion question){
		confQuestionDAO.merge(question);
	}
	
	/**
	 * 删除现场提问问题
	 * @param question
	 */
	public void delQuestion(String questionId){
		ConfQuestion question = confQuestionDAO.findById(questionId);
		confQuestionDAO.delete(question);
	}

	
	/**
	 * 审核问题
	 * @param questionId
	 */
	public void checkQuestion(String questionId, String state){
		ConfQuestion question = confQuestionDAO.findById(questionId);
		question.setState(state);
	}
	
	
	public List<Map<String, Object>> findByUserIdAndMeetingId(String userId, String meetingId){
		return confScheduleUserDAO.findByUserIdAndMeetingId(userId, meetingId);
	}
	
	public List<ConfQuestion> findMeetingWallQ(String meetingId, String scheduleId) {
	    return confQuestionDAO.findMeetingWallQ(meetingId, scheduleId);
	}
	
	/**
	 * 设置为可上墙问题或者改为下墙问题
	 * @param questionId
	 * @param state 4:钉上墙  2：拉下墙
	 */
	public void updateToMeetingWallQ(String questionId, String state) {
	    String updateTime = DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss");
	    confQuestionDAO.updateToWallQuestion(questionId, updateTime, state);
	}
	
}
