package com.centling.conference.sysrole.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.sysrole.service.ConfRoleMenuService;
import com.centling.conference.util.ComboTree;

@Controller("confRoleMenuController")
@RequestMapping("/roleMenu")
public class ConfRoleMenuController {
	@Resource
	private ConfRoleMenuService confRoleMenuService;

	/**
	 * 根据角色ID获取菜单树
	 * @param roleId 角色ID
	 * @param id 菜单ID
	 * @return
	 */
	@RequestMapping(value="/getMenu", method=RequestMethod.POST)
	public @ResponseBody List<ComboTree> getMenuByRoldId(@RequestParam String roleId, HttpServletRequest request) {
		// 获取菜单ID
		String menuId = request.getParameter("id");
		return confRoleMenuService.getMenuByRoleId(roleId,menuId);
	}
	
	/**
	 * 保存角色菜单
	 * @param roleId 角色ID
	 * @param selectNodes 菜单ID集合
	 * @return
	 */
	@RequestMapping(value="/saveRoleMenu", method=RequestMethod.POST)
	public @ResponseBody String saveRoleMenu(@RequestParam String roleId, @RequestParam String selectNodes) {
		confRoleMenuService.saveRoleMenu(roleId, selectNodes);
		return "角色菜单保存成功";
	}
	
	   /**
     * 根据角色ID获取用户列表
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value="/getUser", method=RequestMethod.POST)
    public @ResponseBody List<Map> getUserListByRoldId(@RequestParam String roleId, HttpServletRequest request) {
        return confRoleMenuService.getUserByRoleId(roleId);
    }
}