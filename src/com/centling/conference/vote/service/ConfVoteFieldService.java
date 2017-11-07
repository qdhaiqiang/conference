package com.centling.conference.vote.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.vote.DAO.ConfVoteFieldDAO;
import com.centling.conference.vote.entity.ConfVoteField;

@Service("confVoteFieldService")
public class ConfVoteFieldService {
	
	@Resource
	private ConfVoteFieldDAO confVoteFieldDAO;
	
	public Pagination findVoteField(PageBean pageBean,String scheduleId, String meetingid){
		Pagination pagination = new Pagination();
		List<ConfVoteField> list = confVoteFieldDAO.findVoteField(pageBean,scheduleId,meetingid);
		pagination.setRows(list);
		pagination.setTotal(confVoteFieldDAO.count(scheduleId,meetingid));
		return pagination;
	}
	
	public String save(ConfVoteField confVoteField){
		String msg = StringUtils.EMPTY;
		ConfVoteField result = confVoteFieldDAO.merge(confVoteField);
		if(result != null){
			msg = "保存成功！";
		}else{
			msg = "保存失败！";
		}
		return msg;
	}
	
	public Map<String,String> deleteByIds(String voteIds){
		Map<String,String> map = new HashMap<String, String>();
		int result = confVoteFieldDAO.deleteByIds(voteIds);
		if(result > 0){
			map.put("status", "success");
			map.put("info", "success, 成功删除"+result+"条数据！");
		}else{
			map.put("status", "fail");
			map.put("info", "fail, 删除失败！");
		}
		return map;
	}
	
	public List findVotesBySchduelId(String schduelId,String meetingId){
		return confVoteFieldDAO.findVotesBySchduelId(schduelId, meetingId);
	}
	
	//查找某个日程的投票个数
	public String findVotesCountBySchduelId(String schduelId,String meetingId){
	    return String.valueOf(confVoteFieldDAO.findVotesCountBySchduelId(schduelId, meetingId));
	}
	
	//修改投票是否在会议墙上显示
	public int updateMeetingWallState(String id, String isShow){
	    return confVoteFieldDAO.updateMeetingWallState(id, isShow);
	}
}
