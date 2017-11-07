package com.centling.conference.exhibition.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essaytype.DAO.ConfEssayTypeDAO;
import com.centling.conference.exhibitbooth.DAO.ConfBoothDAO;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibition.DAO.ConfExhibitionDAO;
import com.centling.conference.exhibition.entity.ConfExhibition;

@Service("confExhibitionService")
public class ConfExhibitionService {
	@Resource
	private ConfEssayTypeDAO confEssayTypeDAO;
	@Resource
	private ConfBoothDAO ConfBoothDAO;
	
	public ConfExhibition findById(String id){
		return confExhibitionDAO.findById(id);
	}
	
	public List<ConfExhibition> findByMeetingId(String meetingId){
		return confExhibitionDAO.findByMeetingId(meetingId);
	}
	public void save(ConfExhibition confExhibition){
		confExhibitionDAO.save(confExhibition);
	}
	
	/**
	 * 删除展位类别
	 * 首先判断是否该类别已创建展位，如果未有展位则直接删除；如果有展位，将展位数目返回前台
	 * @param exhibitionIDs
	 * @param meetingId
	 * @return
	 */
	public Map<String,Object> delete(String exhibitionIDs, String meetingId){
		Map<String,Object> map = new HashMap<String, Object>();
		String len = ConfBoothDAO.countBoothByParents(exhibitionIDs);
		if(len != null && Integer.valueOf(len) > 0){
			map.put("isDelete", "false");
			map.put("num", len);
		}else{
			int delNum = confExhibitionDAO.deleteByIds(exhibitionIDs);
			map.put("isDelete", "true");
			map.put("num", delNum);
		}
		return map;
	}
	
	public void update(ConfExhibition confExhibition){
		confExhibitionDAO.merge(confExhibition);
	}
	public Pagination retrive (PageBean pageBean, ConfExhibition confExhibition, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfExhibition> list = confExhibitionDAO.retrieve(pageBean, confExhibition, meetingId);
		pagination.setRows(list);
        pagination.setTotal(confExhibitionDAO.count(meetingId, confExhibition));
		
		return pagination; 
	}
	@Resource
	ConfExhibitionDAO confExhibitionDAO;
	public List<ConfExhibition> getTypeList() {
		return confExhibitionDAO.findAll();
	}

	public List<Map<String, String>> getEssayName(String essayId) {
		return confEssayTypeDAO.getEssayName(essayId);
	}
}
