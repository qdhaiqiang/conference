package com.centling.conference.sysrole.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.sysrole.service.ConfRoleUserService;
import com.centling.conference.util.ComboTree;

@Controller("confRoleUserController")
@RequestMapping("/roleUser")
public class ConfRoleUserController {
	@Resource
	private ConfRoleUserService confRoleUserService;

	/**
	 * 根据用户ID获取角色树
	 * @param roleId 角色ID
	 * @return
	 */
	@RequestMapping(value="/getRole", method=RequestMethod.POST)
	public @ResponseBody List<ComboTree> getRoleByUserId(@RequestParam String sysUserId) {
		return confRoleUserService.getRoleByUserId(sysUserId);
	}
	
	/**
	 * 保存用户角色
	 * @param sysUserId 用户ID
	 * @param selectNodes 角色ID集合
	 * @return
	 */
	@RequestMapping(value="/saveRoleUser", method=RequestMethod.POST)
	public @ResponseBody String saveRoleUser(@RequestParam String sysUserId, @RequestParam String selectNodes) {
		confRoleUserService.saveRoleMenu(sysUserId, selectNodes);
		return "用户角色保存成功";
	}
}