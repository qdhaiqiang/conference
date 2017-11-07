package com.centling.conference.emailtemplate.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.emailtemplate.DAO.ConfEmailTemplateDAO;
import com.centling.conference.emailtemplate.entity.ConfEmailTemplate;

@Service("confEmailTemplateService")
public class ConfEmailTemplateService {

	public Pagination retrieve(PageBean pageBean,String meetingId) {
		Pagination pagination = new Pagination();
		pagination.setRows(confEmailTemplateDAO.retrieve(pageBean,meetingId));
		pagination.setTotal(confEmailTemplateDAO.count(meetingId)+"");
		return pagination;
	}
	
	public void save(ConfEmailTemplate confEmailTemplate) {
		confEmailTemplateDAO.save(confEmailTemplate);
	}
	
	public void update(ConfEmailTemplate confEmailTemplate) {
		confEmailTemplateDAO.update(confEmailTemplate);
		writeToFile("/"+confEmailTemplate.getTemplateName(),confEmailTemplate);
//		if (confEmailTemplate.getName().equals(Constant.EMAIL_TYPE_COMFIRM)) {
//			writeToFile("/"+confEmailTemplate.getTemplateName(),confEmailTemplate);
//		} else if (confEmailTemplate.getName().equals(Constant.EMAIL_TYPE_NOTIFY)) {
//			writeToFile("/notification.vm", confEmailTemplate);
//		}
//		else if (confEmailTemplate.getName().equals(Constant.EMAIL_TYPE_CODE)) {
//			writeToFile("/code.vm", confEmailTemplate);
//		}
	}
	
	private void writeToFile(String str, ConfEmailTemplate confEmailTemplate) {
		try {
			String filePath = this.getClass().getResource(str).getPath();
			filePath = java.net.URLDecoder.decode(filePath, "utf-8");
			FileUtil.writeAsString(new File(filePath), confEmailTemplate.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		confEmailTemplateDAO.delete(id);
	}
	
	public ConfEmailTemplate findById(String id){
		return confEmailTemplateDAO.findById(id);
	}
	
	public List<ConfEmailTemplate> findByProperty(String propertyName, Object value) {
		return confEmailTemplateDAO.findByProperty(propertyName, value);
	}
	
	public List<Object[]> findByUserTypeAndName(String name, String userType) {
		return confEmailTemplateDAO.findByUserTypeAndName(name,userType);
	}
	
	@Resource
	ConfEmailTemplateDAO confEmailTemplateDAO;
}
