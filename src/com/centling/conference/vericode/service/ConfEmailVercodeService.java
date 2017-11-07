package com.centling.conference.vericode.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.vericode.DAO.ConfEmailVericodeDAO;
import com.centling.conference.vericode.entity.ConfEmailVericode;

@Service("confEmailVercodeService")
public class ConfEmailVercodeService {
	@Resource
	private ConfEmailVericodeDAO confEmailVericodeDAO;
	
	public List<ConfEmailVericode> findByEmaile(String email) {
		return confEmailVericodeDAO.findByEmail(email);
	}
	
	public void save(ConfEmailVericode confEmailVericode) {
		confEmailVericodeDAO.save(confEmailVericode);
	}
	
	public List<ConfEmailVericode> findByExample(ConfEmailVericode confEmailVericode) {
		return confEmailVericodeDAO.findByExample(confEmailVericode);
	}

	public Pagination retrieve(PageBean pageBean,String email) {
		Pagination pagination = new Pagination();
		List<ConfEmailVericode> list = confEmailVericodeDAO.retrieve(pageBean,email);
		pagination.setRows(list);
		pagination.setTotal(confEmailVericodeDAO.count(email)+"");
		return pagination;
	}
}