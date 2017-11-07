package com.centling.conference.yachtuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.yachtuser.DAO.ConfYachtUserDAO;
import com.centling.conference.yachtuser.entity.ConfYachtUser;

@Service("confYachtUserService")
public class ConfYachtUserService {
	@Resource
	private ConfYachtUserDAO confYachtUserDAO;
	/**
	 * 用户注册
	 * @param transientInstance
	 * @return
	 */
	public boolean save(ConfYachtUser transientInstance) {
		boolean isOK = false;
		try {
			confYachtUserDAO.save(transientInstance);
			isOK = true;
		} catch (RuntimeException re) {
			isOK = false;
			re.printStackTrace();
		}
		return isOK;
	}
	/**
	 * 更新ConfYachtUser信息
	 * @param user
	 * @return
	 */
	public boolean update(ConfYachtUser user){
		boolean isOK = false;
		ConfYachtUser cuser = new ConfYachtUser();
		cuser = (ConfYachtUser) confYachtUserDAO.findByEmail(user.getEmail()).get(0);
		cuser.setAddress(user.getAddress());
		cuser.setArriveNum(user.getArriveNum());
		cuser.setArriveTime(user.getArriveTime());
		cuser.setBirth(user.getBirth());
		cuser.setBuyInfo(user.getBuyInfo());
		cuser.setCertPic1(user.getCertPic1());
		cuser.setCertPic2(user.getCertPic2());
		cuser.setCertValue(user.getCertValue());
		cuser.setCname(user.getCname());
		cuser.setCompany(user.getCompany());
		cuser.setUpdateTime(user.getUpdateTime());
		cuser.setEntrance(user.getEntrance());
		cuser.setMobile(user.getMobile());
		cuser.setNation(user.getNation());
		cuser.setPickLocation(user.getPickLocation());
		cuser.setPosition(user.getPosition());
		cuser.setPushInfo(user.getPushInfo());
		cuser.setSex(user.getSex());
		isOK = confYachtUserDAO.update(cuser);
		return isOK;
	}
	/**
	 * 查看邮箱密码是否正确
	 * @param id
	 * @return
	 */
	public boolean loginIn(java.lang.String email,java.lang.String password) {
		boolean isOK = false;
		try {
			isOK = confYachtUserDAO.loginIn(email, password);
		} catch (RuntimeException re) {
			isOK = false;
			re.printStackTrace();
		}
		return isOK;
	}
	/**
	 * 查看邮箱是否存在
	 * @param id
	 * @return
	 */
	public  boolean emailIsExist(java.lang.String email) {
		boolean isOK = false;
		try {
			isOK = confYachtUserDAO.emailIsExist(email);
		} catch (RuntimeException re) {
			isOK = false;
			re.printStackTrace();
		}
		return isOK;
	}
	/**
	 * 根据邮箱找到ConfYachtUser List
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByEmail(String email) {
		try {
			return confYachtUserDAO.findByEmail(email);
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
			
		}
	}
	public Pagination getUser(PageBean pageBean, String cname, String userType) {
		Pagination pagination = new Pagination();
		List<Map<String, Object>> list = confYachtUserDAO.retrieve(pageBean, cname, userType);
		pagination.setRows(list);
		pagination.setTotal(confYachtUserDAO.count(cname,userType)+"");
		return pagination;
	}
	public List<Object[]> exportUser(String cname, String userType) {
		return confYachtUserDAO.exportUser(cname,userType);
	}
	
	public ConfYachtUser findUserById(String id){
		return confYachtUserDAO.findById(id);
	}
}
