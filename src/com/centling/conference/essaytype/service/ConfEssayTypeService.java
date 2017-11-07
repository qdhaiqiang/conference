package com.centling.conference.essaytype.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essaytype.DAO.ConfEssayTypeDAO;
import com.centling.conference.essaytype.entity.ConfEssayType;

@Service("confEssayTypeService")
public class ConfEssayTypeService {

	public Pagination retrieve(PageBean pageBean, String meetingId ) {
		Pagination pagination = new Pagination();
		pagination.setRows(confEssayTypeDAO.retrieve(pageBean,meetingId));
		pagination.setTotal(confEssayTypeDAO.count(meetingId,"0",null)+"");
		return pagination;
	}
	
	public Pagination retrieve2(PageBean pageBean, String meetingId,ConfEssayType confEssayType) {
		Pagination pagination = new Pagination();
		pagination.setRows(confEssayTypeDAO.retrieve2(pageBean,meetingId,confEssayType));
		pagination.setTotal(confEssayTypeDAO.count(meetingId,"1",confEssayType)+"");
		return pagination;
	}
	
	public void save(ConfEssayType ConfEssayType) {
		confEssayTypeDAO.save(ConfEssayType);
	}
	
	public void update(ConfEssayType ConfEssayType) {
		confEssayTypeDAO.update(ConfEssayType);
	}
	
	public void delete(String id) {
		confEssayTypeDAO.delete(id);
	}
	
	public ConfEssayType findById(String id){
		return confEssayTypeDAO.findById(id);
	}
	
	public List<ConfEssayType> findAll() {
		return confEssayTypeDAO.findAll();
	}
	
	public List<ConfEssayType> findParentMenu(String meetingId) {
		return confEssayTypeDAO.findParentMenu(meetingId);
	}
	
	public List<ConfEssayType> findMenu2(String meetingId) {
		return confEssayTypeDAO.findMenu2(meetingId);
	}
	
	/**
	 * 获取展会菜单信息
	 * @return
	 */
	public Map<String, Object> findMenuByMeetingId(String meetingId) {
		// 获取所有展会菜单
		List<ConfEssayType> essayTypeList = confEssayTypeDAO.findByProperty("meetingId", meetingId);
		// 展会一级菜单列表
		List<ConfEssayType> firstEssayTypeList = new ArrayList<ConfEssayType>();
		// 展会二级菜单列表
		List<ConfEssayType> secondEssayTypeList = new ArrayList<ConfEssayType>(); 
		Map<String, List<ConfEssayType>> essayMap = new HashMap<String, List<ConfEssayType>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 区分一级菜单与二级菜单
		for (ConfEssayType confEssayType : essayTypeList) {
			if (StringUtils.equals("0", confEssayType.getParentMenu())) {
				// 放入一级菜单列表
				firstEssayTypeList.add(confEssayType);
				// 一级菜单对应下的二级菜单列表
				essayMap.put(confEssayType.getId(),new ArrayList<ConfEssayType>());
			} else {
				secondEssayTypeList.add(confEssayType);
			}
		}
		
		// 遍历二级菜单列表，将二级菜单放到对应的一级菜单下
		for (ConfEssayType confEssayType : secondEssayTypeList) {
			ArrayList<ConfEssayType> confEssayTypeList = (ArrayList<ConfEssayType>)(essayMap.get(confEssayType.getParentMenu()));
			confEssayTypeList.add(confEssayType);
		}
		resultMap.put("essayTypeList", firstEssayTypeList);
		resultMap.put("essayMap", essayMap);
		return resultMap;
	}
	
	public List<Map<String, Object>> findNews() {
		return confEssayTypeDAO.findNews();
	}
	
	public List<Map<String, Object>> findGuide() {
		return confEssayTypeDAO.findGuide();
	}
	
	@Resource
	private ConfEssayTypeDAO confEssayTypeDAO;
}
