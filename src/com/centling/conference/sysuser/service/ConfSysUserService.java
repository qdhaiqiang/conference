package com.centling.conference.sysuser.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.sysmenu.DAO.ConfSysMenuDAO;
import com.centling.conference.sysmenu.entity.ConfSysMenu;
import com.centling.conference.sysrole.DAO.ConfRoleMenuDAO;
import com.centling.conference.sysrole.DAO.ConfRoleUserDAO;
import com.centling.conference.sysrole.DAO.ConfSysRoleDAO;
import com.centling.conference.sysrole.entity.ConfRoleMenu;
import com.centling.conference.sysrole.entity.ConfRoleUser;
import com.centling.conference.sysrole.entity.ConfSysRole;
import com.centling.conference.sysuser.DAO.ConfSysUserDAO;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.util.Md5;
import com.centling.conference.util.NumberComparator;


@Service("ConfSysUserService")
public class ConfSysUserService {
	private static final Logger log = LoggerFactory.getLogger(ConfSysUserDAO.class);
	@Resource
	private ConfSysUserDAO confSysUserDAO;
	
	@Resource
	private ConfRoleUserDAO confRoleUserDAO;
	
	@Resource
	private ConfRoleMenuDAO confRoleMenuDAO;
	
	@Resource
	private ConfSysMenuDAO confSysMenuDAO;
	
	@Resource
	private ConfSysRoleDAO confSysRoleDAO;
	
	public List<ConfSysUser> getUserList() {
		return confSysUserDAO.findAll();
	}

	public ConfSysUser getUser(String username) {
		List<ConfSysUser> users = confSysUserDAO.findByLoginName(username);
		if(users!=null&&users.size()!=0)
			return users.get(0);
		return null;
	}
	
	public List<ConfSysUser> getAvailableUsers() {
		List<ConfSysUser> userList = confSysUserDAO.findByStatus("true");
		return userList;
	}

//	public List<ConfSysUser> getUsersByRole(String role) {
//	}

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(ConfSysUser user) {
		String encoded = Md5.getMD5Str(user.getLoginPassword());
		user.setLoginPassword(encoded);
		confSysUserDAO.save(user);
		return true;
	}
	
	public boolean updateUser(ConfSysUser user) {
		confSysUserDAO.merge(user);
		return true;
	}
	
	public boolean deleteUser(String sysUserId){
		try {
			// 删除用户的角色信息
			confRoleUserDAO.deleteByUserId(sysUserId);
			// 删除用户信息
			ConfSysUser confSysUser = confSysUserDAO.findById(sysUserId);
			confSysUserDAO.delete(confSysUser);
		} catch (Exception e) {
			log.error("删除用户失败", e);
			return false;
		}
		return true;
	}

	public boolean changeUser(ConfSysUser user) {
		ConfSysUser userStored = confSysUserDAO.findById(user.getId());
		if(userStored!=null){
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String encoded = md5.encodePassword(user.getLoginPassword(), user.getLoginName());
			//changed password
			if(!userStored.getLoginPassword().equals(encoded)) user.setLoginPassword(encoded);
			confSysUserDAO.attachDirty(user);
			return true;
		}
		return false;
	}
	
	/**
	 * 根据用户名和密码查询用户
	 * @param confSysUser 待查询的用户
	 * @return
	 */
	public ConfSysUser checkUserExists(ConfSysUser confSysUser) {
		// 密码加密
		confSysUser.setLoginPassword(Md5.getMD5Str(confSysUser.getLoginPassword()));
		List<ConfSysUser> userList = confSysUserDAO.findByExample(confSysUser);
		if (userList!=null && !userList.isEmpty()) {
			return userList.get(0);
		}
		return null;
	}

	public Pagination retrieve(PageBean pageBean,ConfSysUser confSysUser) {
		Pagination pagination = new Pagination();
		List<ConfSysUser> list = confSysUserDAO.retrieve(pageBean,confSysUser);
		pagination.setRows(list);
		pagination.setTotal(confSysUserDAO.count(confSysUser)+"");
		return pagination;
	}
	
	/**
	 * 获取左侧菜单列表
	 * @param confSysUser
	 * @return
	 */
	public String getLeftMenuStr(ConfSysUser confSysUser) {
		// 获取用户ID
		String confSysUserId = confSysUser.getId();
		// 获取用户的角色信息
		List<ConfRoleUser> roleUserList = confRoleUserDAO.findByUserId(confSysUserId);
		// 用户所拥有的菜单集合
		Set<ConfSysMenu> menuSet = new HashSet<ConfSysMenu>();
		for (ConfRoleUser confRoleUser : roleUserList) {
			// 获取角色的菜单集合
			List<ConfRoleMenu> roleMenuList = confRoleMenuDAO.findByRoleId(confRoleUser.getRoleId());
	
			for (ConfRoleMenu confRoleMenu : roleMenuList) {
				// 查询所有的菜单
				ConfSysMenu confSysMenu = confSysMenuDAO.findById(confRoleMenu.getMenuId());
				menuSet.add(confSysMenu);
			}
		}
		
		// 划分一级菜单与二级菜单
		List<ConfSysMenu> firstLevelMenu = new ArrayList<ConfSysMenu>();
		List<ConfSysMenu> secondLevelMenu = new ArrayList<ConfSysMenu>();
		for (ConfSysMenu confSysMenu : menuSet) {
			if (confSysMenu.getLevel()==1) {
				firstLevelMenu.add(confSysMenu);
			} else if (confSysMenu.getLevel()==2) {
				secondLevelMenu.add(confSysMenu);
			}
		}
		
		// 菜单排序
		Collections.sort(firstLevelMenu, new NumberComparator());
		Collections.sort(secondLevelMenu, new NumberComparator());
		
		String str = getMenuStrByList(firstLevelMenu, secondLevelMenu);
		return str;
	}

	/**
	 * 根据菜单生成菜单字符串
	 * @param firstLevelMenu 一级菜单集合
	 * @param secondLevelMenu 二级菜单集合
	 * @return
	 */
	private String getMenuStrByList(List<ConfSysMenu> firstLevelMenu, List<ConfSysMenu> secondLevelMenu) {
		StringBuffer menuBuffer = new StringBuffer();
		// 循环遍历一级菜单,组装菜单字符串
		for (ConfSysMenu confSysMenu : firstLevelMenu) {
			menuBuffer.append("<div title='").append(confSysMenu.getName()).append("' iconCls='").append(confSysMenu.getIcon()).append("'>");
			menuBuffer.append("<ul>");
			for (ConfSysMenu confSysMenu2 : secondLevelMenu) {
				if (confSysMenu.getId().equals(confSysMenu2.getPid())) {
					menuBuffer.append("<li><div title='").append(confSysMenu2.getName()).append("' url='").append(confSysMenu2.getMenuUrl())
						.append("'  iconCls='").append(confSysMenu2.getIcon()).append("'><a class='").append(confSysMenu2.getIcon())
						.append("'><span class='icon ").append(confSysMenu2.getIcon()).append("'>&nbsp;</span><span class='nav'>")
						.append(confSysMenu2.getName()).append("</span></a></div></li>");
				}
			}
			menuBuffer.append("</ul></div>");
		}
		return menuBuffer.toString();
	}
	
	
	/**
	 * 修改密码
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return
	 */
	public String changePwd(ConfSysUser user,String oldPwd, String newPwd) {
		 
		// 判断原密码是否正确
		if (!Md5.checkpassword(oldPwd, user.getLoginPassword())) {
			return "1";
		}
		// 更新密码
		user.setLoginPassword(Md5.getMD5Str(newPwd));
		confSysUserDAO.updatePwd(user); 
		
		return "0";
	}

	public boolean checkUserNameExists(String loginName) {
		List<ConfSysUser> userList = confSysUserDAO.findByLoginName(loginName);
		if (userList==null || userList.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 批量导入用户
	 * @param userIds
	 * @return
	 */
	public String importUser(String userIds) {
		confSysUserDAO.importUser(userIds);
		return "导入用户成功";
	}
	
	/**
	 * 判断用户是否是管理员用户
	 * @param confSysUser
	 * @return true:管理员用户 false:普通用户
	 */
	public boolean checkAdmin(ConfSysUser confSysUser) {
		if (confSysUser==null) {
			return false;
		} 
		List<ConfRoleUser> roleUserList = confRoleUserDAO.findByUserId(confSysUser.getId());
		for (ConfRoleUser confRoleUser : roleUserList) {
			ConfSysRole confSysRole = confSysRoleDAO.findById(confRoleUser.getRoleId());
			if (Constant.ROLE_ADMIN.equals(confSysRole.getCode())) {
				return true;
			}
		}
		return false;
	}

}
