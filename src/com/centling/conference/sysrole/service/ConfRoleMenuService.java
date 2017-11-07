package com.centling.conference.sysrole.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.sysmenu.DAO.ConfSysMenuDAO;
import com.centling.conference.sysmenu.entity.ConfSysMenu;
import com.centling.conference.sysrole.DAO.ConfRoleMenuDAO;
import com.centling.conference.sysrole.DAO.ConfRoleUserDAO;
import com.centling.conference.sysrole.entity.ConfRoleMenu;
import com.centling.conference.util.ComboTree;

@Service("confRoleMenuService")
public class ConfRoleMenuService {
	@Resource
	private ConfSysMenuDAO confSysMenuDAO;
	@Resource
	private ConfRoleMenuDAO confRoleMenuDAO;
	@Resource
	private ConfRoleUserDAO confRoleUserDAO;

	public List<ComboTree> getMenuByRoleId(String roleId,String menuId) {
		
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		List<ConfSysMenu> menuList = new ArrayList<ConfSysMenu>();
		
		// 查询菜单
		// 如果没有ID,查询一级目录
		if (StringUtils.isEmpty(menuId)) {
			menuList = confSysMenuDAO.findFirstMenu();
		// 查找二级目录
		} else {
			menuList = confSysMenuDAO.findByPid(menuId);
		}
		
		// 根据roleId获取已有权限的菜单ID
		List<ConfRoleMenu> roleMenuList = confRoleMenuDAO.findByRoleId(roleId);
		
		for (ConfSysMenu confSysMenu : menuList) {
			ComboTree comboTree = new ComboTree();
			// 设置菜单ID
			comboTree.setId(confSysMenu.getId());
			// 设置菜单名称
			comboTree.setText(confSysMenu.getName());
			// 设置选中状态
			if (roleMenuList!=null && !roleMenuList.isEmpty()) {
				for (ConfRoleMenu confRoleMenu : roleMenuList) {
					if (confSysMenu.getId().equals(confRoleMenu.getMenuId())) {
						comboTree.setChecked(true);
						break;
					}
				}
			}
			// 判断是否有子节点
			List<ConfSysMenu> childrenList = confSysMenuDAO.findByPid(confSysMenu.getId());
			if (childrenList!=null && !childrenList.isEmpty()) {
				comboTree.setState("closed");
				comboTree.setChecked(false);
			}
			comboTrees.add(comboTree);
		}
		return comboTrees;
	}
	
	public void saveRoleMenu(String roleId, String selectNodes) {
		// 删除原有的角色菜单
		confRoleMenuDAO.deleteByRoleId(roleId);
		// 保存新的角色菜单
		if (StringUtils.isNotEmpty(selectNodes)) {
			String[] menuIds = selectNodes.split(",");
			for (String menuId : menuIds) {
				ConfRoleMenu confRoleMenu = new ConfRoleMenu();
				confRoleMenu.setMenuId(menuId);
				confRoleMenu.setRoleId(roleId);
				confRoleMenuDAO.save(confRoleMenu);
			}
		}
	}
	
	//获取角色下的用户列表
	public List<Map> getUserByRoleId(String roleId) {
	    return confRoleUserDAO.getUserListByRoleId(roleId);
	}
}