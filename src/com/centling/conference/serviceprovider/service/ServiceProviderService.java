package com.centling.conference.serviceprovider.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.serviceprovider.DAO.ConfServiceProviderDAO;
import com.centling.conference.serviceprovider.entity.ConfServiceProvider;

@Service("serviceProviderService")
public class ServiceProviderService {
	
	@Resource
	private ConfServiceProviderDAO confServiceProviderDAO;
	
	
	/**
	 * 查询所有的外包商
	 * @param pageBean
	 * @return
	 */
	public Pagination retrive (PageBean pageBean,String meetingId,ConfServiceProvider provider) {
		Pagination pagination = new Pagination();
		pagination.setRows(confServiceProviderDAO.retrieve(pageBean,meetingId,provider));
		pagination.setTotal(confServiceProviderDAO.count(meetingId,provider)+"");
		return pagination; 
	}
    
    
    /**
     * 新增外包商
     * @param instance
     */
    public void save(ConfServiceProvider instance){
    	confServiceProviderDAO.save(instance);
    }
    
    
    /**
     * 修改外包商信息
     * @param instance
     */
    public void update(ConfServiceProvider instance) {
    	confServiceProviderDAO.merge(instance);
    }
    

    /**
     * 批量删除外包商
     * @param providerIds
     */
    public int delete(String providerIds) {
        // 刪除外包服务商
        return confServiceProviderDAO.deleteById(providerIds);
    }
    
	
}

