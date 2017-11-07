package com.centling.conference.sysmenu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.sysmenu.entity.ConfSysMenu;
import com.centling.conference.sysmenu.service.ConfSysMenuService;
import com.centling.conference.util.TreeGrid;

/**
 * 菜单管理controller
 * @author lenovo
 *
 */
@Controller("confSysMenuController")
@RequestMapping("/menu")
public class ConfSysMenuController {
	@Resource
	private ConfSysMenuService confSysMenuService;
	
	/**
	 * 查询所有的菜单
	 * @return
	 */
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody List<TreeGrid> retrieve(TreeGrid treeGrid) {
		return confSysMenuService.retrieve(treeGrid);
	}
	
	@RequestMapping(value="/c", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(ConfSysMenu confSysMenu) {
		confSysMenuService.save(confSysMenu);
		return "添加成功";
	}
	
	@RequestMapping(value="/u", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(ConfSysMenu confSysMenu) {
		confSysMenuService.update(confSysMenu);
		return "修改成功";
	}
	/**
	 * 获取所有的父级菜单
	 * @return
	 */
	@RequestMapping(value="/getFirstLevelMenu", method=RequestMethod.POST)
	public @ResponseBody List<ConfSysMenu> getFirstMenu() {
		return confSysMenuService.getFirstMenu();
	}
}