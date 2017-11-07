package com.centling.conference.sysrole.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.sysrole.DAO.ConfRoleUserDAO;
import com.centling.conference.sysrole.DAO.ConfSysRoleDAO;
import com.centling.conference.sysrole.entity.ConfRoleUser;
import com.centling.conference.sysrole.entity.ConfSysRole;
import com.centling.conference.util.ComboTree;

@Service("confRoleUserService")
public class ConfRoleUserService {
	@Resource
	private ConfRoleUserDAO confRoleUserDAO;
	
	@Resource
	private ConfSysRoleDAO confSysRoleDAO;

	public List<ComboTree> getRoleByUserId(String sysUserId) {
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		
		// 查询所有的角色
		List<ConfSysRole> roleList = confSysRoleDAO.findAll();
		
		// 根据sysUserId获取已有权限的菜单ID
		List<ConfRoleUser> roleUserList = confRoleUserDAO.findByUserId(sysUserId);
		
		for (ConfSysRole confSysRole : roleList) {
			ComboTree comboTree = new ComboTree();
			// 设置角色ID
			comboTree.setId(confSysRole.getId());
			// 设置角色名称
			comboTree.setText(confSysRole.getName());
			// 设置选中状态
			if (roleUserList!=null && !roleUserList.isEmpty()) {
				for (ConfRoleUser confRoleUser : roleUserList) {
					if (confSysRole.getId().equals(confRoleUser.getRoleId())) {
						comboTree.setChecked(true);
						break;
					}
				}
			}
			comboTrees.add(comboTree);
		}
		return comboTrees;
	}

	public void saveRoleMenu(String sysUserId, String selectNodes) {
		// 删除原有的用户角色
		confRoleUserDAO.deleteByUserId(sysUserId);
		// 保存新的用户角色
		if (StringUtils.isNotEmpty(selectNodes)) {
			String[] roleIds = selectNodes.split(",");
			for (String roleId : roleIds) {
				ConfRoleUser confRoleUser = new ConfRoleUser();
				confRoleUser.setRoleId(roleId);
				confRoleUser.setUserId(sysUserId);
				confRoleUserDAO.save(confRoleUser);
			}
		}
	}
}