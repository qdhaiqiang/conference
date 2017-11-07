package com.centling.conference.material.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.material.DAO.ConfMaterialScheduleDAO;
import com.centling.conference.material.entity.ConfMaterialSchedule;

@Service("confMaterialScheduleService")
public class ConfMaterialScheduleService {

	@Resource
	private ConfMaterialScheduleDAO confMaterialScheduleDAO;
	
	/**
	 * 
	 * @param confMaterialSchedule 查询条件，必有meetingid字段，所有的条件都只针对此会议
	 * 		scheduleId 会场id，根据会场日程id,查询本会场所用的所有的物料列表
	 * 		materialType 物料的类别，是普通物料还是场地搭建中的部分
	 * 		materialNameLike 物料名称，模糊匹配
	 * 		materialLeaderLike 物料负责人姓名，模糊匹配
	 * @return
	 */
	public Pagination findMaterialSchedule(PageBean pageBean,ConfMaterialSchedule confMaterialSchedule){
		Pagination pagination = new Pagination();
		List<ConfMaterialSchedule> materialList= new ArrayList<ConfMaterialSchedule>();
		if(confMaterialSchedule.getId() != null && confMaterialSchedule.getId().replace(" ", "").length() > 0){
			ConfMaterialSchedule material = confMaterialScheduleDAO.findById(confMaterialSchedule.getId());
			materialList.add(material);
			pagination.setRows(materialList);
			pagination.setTotal("1");
		}else{
			materialList = confMaterialScheduleDAO.findMaterialSchedule(pageBean, confMaterialSchedule);
			pagination.setRows(materialList);
			pagination.setTotal(confMaterialScheduleDAO.count(pageBean, confMaterialSchedule));
		}
		return pagination;
	}
	
	public Map<String,String> save(ConfMaterialSchedule confMaterialSchedule){
		Map<String,String> map = new HashMap<String, String>();		
		ConfMaterialSchedule result = confMaterialScheduleDAO.merge(confMaterialSchedule);
		if(result != null){
			map.put("status", "success");
			map.put("info", "success, 保存成功！");
		}else{
			map.put("status", "fail");
			map.put("info", "fail, 保存失败！");
		}
		return map;
	}
	
	public Map<String,String> deleteByIds(String materialIds){
		Map<String,String> map = new HashMap<String, String>();
		int result = confMaterialScheduleDAO.deleteByIds(materialIds);
		if(result > 0){
			map.put("status", "success");
			map.put("info", "success, 成功删除"+result+"条数据！");
		}else{
			map.put("status", "fail");
			map.put("info", "fail, 删除失败！");
		}
		return map;
	}
	
	public Map<String,String> updateStateByIds(String materialIds){
		Map<String,String> map = new HashMap<String, String>();
		int result = confMaterialScheduleDAO.updateStateByIds(materialIds);
		if(result > 0){
			map.put("status", "success");
			map.put("info", "success, 成功修改"+result+"条数据成已到位！");
		}else{
			map.put("status", "fail");
			map.put("info", "fail, 修改失败！");
		}
		return map;
	}
	
	
}
