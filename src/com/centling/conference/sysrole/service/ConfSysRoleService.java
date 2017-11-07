package com.centling.conference.sysrole.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.sysrole.DAO.ConfRoleMenuDAO;
import com.centling.conference.sysrole.DAO.ConfRoleUserDAO;
import com.centling.conference.sysrole.DAO.ConfSysRoleDAO;
import com.centling.conference.sysrole.entity.ConfRoleUser;
import com.centling.conference.sysrole.entity.ConfSysRole;

@Service("confSysRoleService")
public class ConfSysRoleService {
	@Resource
	private ConfSysRoleDAO confSysRoleDAO;
	
	@Resource
	private ConfRoleUserDAO confRoleUserDAO;
	
	@Resource
	private ConfRoleMenuDAO confRoleMenuDAO;
	
	public Pagination retrieve(PageBean pageBean) {
		Pagination pagination = new Pagination();
		List<ConfSysRole> list = confSysRoleDAO.retrieve(pageBean);
		pagination.setRows(list);
		pagination.setTotal(confSysRoleDAO.count()+"");
		return pagination;
	}

	public void save(ConfSysRole confSysRole) {
		confSysRoleDAO.save(confSysRole);
	}

	public void update(ConfSysRole confSysRole) {
		confSysRoleDAO.update(confSysRole);
	}
	
	/**
	 * 删除角色
	 * @param confSysRoleId
	 * @return
	 */
	public String delete(String confSysRoleId) {
		// 判断该角色下是否有用户，如果有用户使用，则不允许删除
		List<ConfRoleUser> roleUserList = confRoleUserDAO.findByRoleId(confSysRoleId);
		if (roleUserList!=null && !roleUserList.isEmpty()) {
			return "该角色已有用户使用，请先删除用户再删除该角色";
		}
		// 删除菜单角色关联表中数据
		confRoleMenuDAO.deleteByRoleId(confSysRoleId);
		// 删除角色
		confSysRoleDAO.delete(confSysRoleDAO.findById(confSysRoleId));
		return "删除成功";
	}
}