package com.centling.conference.managermobile.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.managermobile.DAO.ConfManagerMobileDAO;
import com.centling.conference.managermobile.entity.ConfManagerMobile;

@Service("managerMobileService")
public class ManagerMobileService {
	
	@Resource
	private ConfManagerMobileDAO confManagerMobileDAO;
	
	//初期化
	public Pagination retrive (PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		pagination.setRows(confManagerMobileDAO.retrieve(pageBean, meetingId));
		pagination.setTotal(confManagerMobileDAO.count(meetingId));
		
		return pagination; 
	}
    
    //新增领导手机号
    public void save(ConfManagerMobile instance){
        confManagerMobileDAO.save(instance);
    }
    
    //更新领到手机号码
    public void update(ConfManagerMobile instance) {
        confManagerMobileDAO.merge(instance);
    }
    
    //删除记录
    public int delete(String equipIds, String meetingId) {
          return confManagerMobileDAO.deleteByIds(equipIds);
    }
	
}
