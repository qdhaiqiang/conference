package com.centling.conference.equipmentlease.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.equipmentlease.DAO.ConfEquipLeaseDAO;
import com.centling.conference.equipmentlease.entity.ConfEquipLease;

@Service("equipLeasingService")
public class EquipLeasingService {
	
	@Resource
	private ConfEquipLeaseDAO confEquipLeaseDAO;
	
	//初期化
	public Pagination retrive (PageBean pageBean, String meetingId, ConfEquipLease equip) {
		Pagination pagination = new Pagination();
		pagination.setRows(confEquipLeaseDAO.retrieve(pageBean, meetingId, equip));
		pagination.setTotal(confEquipLeaseDAO.count(meetingId, equip));
		
		return pagination; 
	}
    
    //新增设备租赁
    public void save(ConfEquipLease instance){
    	confEquipLeaseDAO.save(instance);
    }
    
    //更新设备租赁
    public void update(ConfEquipLease instance) {
    	confEquipLeaseDAO.merge(instance);
    }
    
    //删除嘉宾
    public int delete(String equipIds, String meetingId) {
          return confEquipLeaseDAO.deleteByIds(equipIds);
    }
	
}
