package com.centling.conference.sysmenu.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.sysmenu.DAO.ConfSysMenuDAO;
import com.centling.conference.sysmenu.entity.ConfSysMenu;
import com.centling.conference.util.TreeGrid;

/**
 * 菜单Service
 * @author lenovo
 *
 */
@Service("confSysMenuService")
public class ConfSysMenuService {
	
	@Resource
	private ConfSysMenuDAO confSysMenuDAO;

	public List<TreeGrid> retrieve(TreeGrid treeGrid) {
		List<TreeGrid> treeList = new ArrayList<TreeGrid>();
		List<ConfSysMenu> menuList = new ArrayList<ConfSysMenu>();
		// 如果没有ID,查询一级目录
		if (StringUtils.isEmpty(treeGrid.getId())) {
			menuList = confSysMenuDAO.findFirstMenu();
		// 查找二级目录
		} else {
			menuList = confSysMenuDAO.findByPid(treeGrid.getId());
		}
		for (ConfSysMenu confSysMenu : menuList) {
			TreeGrid listTreeGrid = new TreeGrid();
			// 设置主键
			listTreeGrid.setId(confSysMenu.getId());
			// 设置名称
			listTreeGrid.setText(confSysMenu.getName());
			// 设置父节点ID
			if (StringUtils.isEmpty(confSysMenu.getPid())) {
				listTreeGrid.setParentId(StringUtils.EMPTY);
			} else {
				listTreeGrid.setParentId(confSysMenu.getPid());
			}
			// 设置图标
			listTreeGrid.setCode(confSysMenu.getIcon());
			// 设置访问路径
			if (StringUtils.isEmpty(confSysMenu.getMenuUrl())) {
				listTreeGrid.setSrc(StringUtils.EMPTY);
			} else {
				listTreeGrid.setSrc(confSysMenu.getMenuUrl());
			}
			// 设置顺序
			listTreeGrid.setOrder(confSysMenu.getShowOrder().toString());
			// 设置展开状态
			// 查询是否有子菜单
			List<ConfSysMenu> childrenList = confSysMenuDAO.findByPid(confSysMenu.getId());
			// 有子点
			if (childrenList!=null && !childrenList.isEmpty()) {
				listTreeGrid.setState("closed");
			// 无子节点
			} else {
				listTreeGrid.setState("open");
			}
			treeList.add(listTreeGrid);
		}
		return treeList;
	}

	public List<ConfSysMenu> getFirstMenu() {
		return confSysMenuDAO.findByLevel(Constant.MENU_LEVEL_FIRST);
	}

	public void save(ConfSysMenu confSysMenu) {
		confSysMenuDAO.save(confSysMenu);
	}

	public void update(ConfSysMenu confSysMenu) {
		confSysMenuDAO.update(confSysMenu);
	}
}