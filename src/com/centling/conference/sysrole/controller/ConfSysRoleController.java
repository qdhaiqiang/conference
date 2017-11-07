package com.centling.conference.sysrole.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.sysrole.entity.ConfSysRole;
import com.centling.conference.sysrole.service.ConfSysRoleService;

@Controller("confSysRoleController")
@RequestMapping(value="/role")
public class ConfSysRoleController {
	@Resource
	private ConfSysRoleService confSysRoleService;
	
	/**
	 * 查询所有角色
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value="r", method=RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean) {
		return confSysRoleService.retrieve(pageBean);
	}
	
	@RequestMapping(value="c", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(ConfSysRole confSysRole) {
		confSysRoleService.save(confSysRole);
		return "添加成功";
	}
	
	@RequestMapping(value="u", method=RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(ConfSysRole confSysRole) {
		confSysRoleService.update(confSysRole);
		return "修改成功";
	}
	
	@RequestMapping(value="d/{confSysRoleId}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable String confSysRoleId) {
		return confSysRoleService.delete(confSysRoleId);
	}
}