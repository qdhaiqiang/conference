package com.centling.conference.materialreg.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.material.DAO.ConfMaterialScheduleDAO;
import com.centling.conference.materialreg.DAO.ConfMaterialRegistrationDAO;
import com.centling.conference.materialreg.entity.ConfMaterialRegistration;

@Service("materialRegService")
public class MaterialRegService {

	@Resource
	private ConfMaterialRegistrationDAO confMaterialRegistrationDAO;
	@Resource
	private ConfMaterialScheduleDAO confMaterialScheduleDAO;
	
	public Pagination retrive (PageBean pageBean) {
		Pagination pagination = new Pagination();
		pagination.setRows(confMaterialRegistrationDAO.retrieve(pageBean));
		pagination.setTotal(confMaterialRegistrationDAO.count()+"");
		
		return pagination; 
	}
	
	public List findAll(){
		return confMaterialRegistrationDAO.findAll();
	}
    
    //新增 物料信息
    public void save(ConfMaterialRegistration instance){
    	confMaterialRegistrationDAO.save(instance);
    }
    
    //更新物料信息
    public void update(ConfMaterialRegistration instance) {
    	confMaterialRegistrationDAO.merge(instance);
    }
    
    //删除物料信息
    public String delete(String equipIds) {
    	String result = "";
    	Long counts = confMaterialScheduleDAO.countBymaterialIds(equipIds);
    	if(counts != null && counts > 0){
    		result = "fail";
    	}else{
    		int delNum = confMaterialRegistrationDAO.deleteByIds(equipIds);
    		result = "success";
    	}
    	return result;
    }
	
}
