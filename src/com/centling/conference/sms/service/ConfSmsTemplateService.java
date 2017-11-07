package com.centling.conference.sms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.sms.DAO.ConfSmsTemplateDAO;
import com.centling.conference.sms.entity.ConfSmsTemplate;

@Service("confSmsTemplateService")
public class ConfSmsTemplateService {

	@Resource
	private ConfSmsTemplateDAO confSmsTemplateDAO;
	
	public Pagination retrive (PageBean pageBean,String meetingId) {
		Pagination pagination = new Pagination();
		pagination.setRows(confSmsTemplateDAO.retrieve(pageBean,meetingId));
		pagination.setTotal(confSmsTemplateDAO.count(meetingId)+"");
		
		return pagination; 
	}
    
    //新增 物料信息
    public void save(ConfSmsTemplate instance){
    	confSmsTemplateDAO.save(instance);
    }
    
    //更新物料信息
    public void update(ConfSmsTemplate instance) {
    	confSmsTemplateDAO.merge(instance);
    }
    
    //删除物料信息
    public void delete(String[] templateIds, String meetingId) {
        for (int i = 0; i < templateIds.length; i++) {
            String id = templateIds[i];
            confSmsTemplateDAO.deleteById(id);
        }
    }
	
}
