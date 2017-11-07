package com.centling.conference.exhibitfurniture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitFurnitureDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;

@Service("confExhibitFurnitureService")
public class ConfExhibitFurnitureService {
	
	@Resource
	private ConfExhibitFurnitureDAO confExhibitFurnitureDAO;
	
	public List<ConfExhibitFurniture> findFurniture(String meetingId){
		List<ConfExhibitFurniture> furnitureList = confExhibitFurnitureDAO.findByMeetingId(meetingId);
		return furnitureList;
	}
	   
	public Pagination retrieve(PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfExhibitFurniture> list = confExhibitFurnitureDAO.retrieve(pageBean, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confExhibitFurnitureDAO.count(meetingId)+"");
		return pagination;
	}

	public void save(ConfExhibitFurniture confExhibitFurniture) {
		confExhibitFurnitureDAO.save(confExhibitFurniture);
	}

	public void update(ConfExhibitFurniture confExhibitFurniture) {
		confExhibitFurnitureDAO.update(confExhibitFurniture);
	}

	public Map<String,String> delete(String furnitureId) {
		Map<String,String> map = new HashMap<String, String>();
		ConfExhibitFurniture confExhibitFurniture = confExhibitFurnitureDAO.findById(furnitureId);
		if(confExhibitFurniture.getFurnitureRentMount() != null && confExhibitFurniture.getFurnitureRentMount() > 0){			
			map.put("data", "fail");
			map.put("info", "不可删除，该家具已被预订！");
		}else{
			confExhibitFurnitureDAO.delete(confExhibitFurniture);
			map.put("data", "success");
			map.put("info", "删除成功！");
		}
		return map;
	}
}