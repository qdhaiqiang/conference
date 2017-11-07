package com.centling.conference.user.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.emailtemplate.service.ConfEmailTemplateService;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.meetinguser.service.ConfMeetingUserService;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.userentourage.entity.ConfUserEntourage;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.Md5;
import com.centling.conference.util.SecurityCode;
import com.centling.conference.util.SecurityCode.SecurityCodeLevel;
import com.centling.conference.vericode.entity.ConfEmailVericode;
import com.centling.conference.vericode.service.ConfEmailVercodeService;

@Controller("confRegisterController")
@RequestMapping("/front/reg/*")
public class ConfRegisterController {
	private static final Logger log = LoggerFactory.getLogger(ConfRegisterController.class);
	@Resource
	private ConfUserService confUserService;
	@Resource
	private ConfMeetingUserService confMeetingUserService;
	@Resource
	private ConfEmailVercodeService confEmailVercodeService;
	@Resource
	private ConfEmailTemplateService confEmailTemplateService;
	
	/**
	 * 登陆页面点击” 注册  “按钮进入邮箱验证。
	 * @return
	 */
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	public String emailCheck(@RequestParam String type, HttpServletRequest request) {
		// return "front/test/first";
		request.setAttribute("roleCode", "");
		request.setAttribute("type", type);
		return "front/email_check";
	}
	
	/**
	 * 需要填写
	 * @param code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/emailCheck/{type}", method = RequestMethod.GET)
	public String emailCheckUser( @PathVariable String type, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("categoryCode", "user_type");
		return "front/email_check";
	}

	/**
	 * 用户点击注册后进入注册页
	 * @param email 注册邮箱
	 * @param roleCode 用户类型code
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/register/{roleCode}", method = RequestMethod.POST)
	public String register(@RequestParam String email,@PathVariable String roleCode, 
			HttpServletRequest request) {
		
		ConfUser user = new ConfUser();
		// 新注册用户
		user.setEmail(email.toLowerCase());
		user.setContactPerson("{}"); // 初始化联系人
		request.setAttribute("user", user);
		request.setAttribute("roleCode", roleCode);
		
		//注册后给new一个user放session中
		HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_CONF_USER, user);
        session.removeAttribute(Constant.SESSION_front_approveState);
		
		// 组委会人员、供应商人员、志愿者 进staff页面
		if (roleCode.equals(Constant.USER_TYPE_ORG)
				|| roleCode.equals(Constant.USER_TYPE_ORG_EN)
				|| roleCode.equals(Constant.USER_TYPE_SUPPLIER)
				|| roleCode.equals(Constant.USER_TYPE_SUPPLIER_EN)
				|| roleCode.equals(Constant.USER_TYPE_VOLUNTEER)
				|| roleCode.equals(Constant.USER_TYPE_VOLUNTEER_EN)) {
			return "front/staff_info";
		} else if (roleCode.equals(Constant.USER_TYPE_AUDIENCE)
				|| roleCode.equals(Constant.USER_TYPE_AUDIENCE_EN)) {
			// 观众注册
			return "front/audience_info";
		} else if(roleCode.equals(Constant.USER_TYPE_ENTOURAGE)
				|| roleCode.equals(Constant.USER_TYPE_ENTOURAGE_EN)
				|| roleCode.equals(Constant.USER_TYPE_SPOUSE)
				|| roleCode.equals(Constant.USER_TYPE_SPOUSE_EN)){
			//随行人员和配偶
			return "front/entourage_info";
		} else if(roleCode.equals(Constant.USER_TYPE_EXHIBITORS)
				|| roleCode.equals(Constant.USER_TYPE_EXHIBITORS_EN)){
			//参展商
			return "front/exhibitor_info";
		} else if(roleCode.equals(Constant.USER_TYPE_MEDIA)
				|| roleCode.equals(Constant.USER_TYPE_MEDIA_EN)){
			return "front/media_info";
		} else if(roleCode.equals(Constant.USER_TYPE_REGIST_MEDIA)
				|| roleCode.equals(Constant.USER_TYPE_REGIST_MEDIA_EN)){
			return "front/reg_media_info";
		}
		return "front/index";

	}
	
	// 用户登陆成功，保存
	@RequestMapping(value = "/loginsuccess/{roleCode}", method = RequestMethod.POST)
	public String loginSuccess(@PathVariable String roleCode,
			HttpServletRequest request) {
		
		//已保存过user
		HttpSession session = request.getSession();
		ConfUser user = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
		
		//判断指定联系人是否为空，为空赋值默认{}
		if(user.getContactPerson()==null||"".equals(user.getContactPerson())){
			user.setContactPerson("{}");
		}
		
		//TODO 在此查询用户的审核状态，控制前台审核通过之后不可点击所有的提交按钮；根据userid meetingid，获取审核状态标志位
		ConfMeetingUser confMeetingUser = new ConfMeetingUser();
		confMeetingUser.setUserId(user.getId());
		confMeetingUser.setMeetingId(session.getAttribute(Constant.FRONT_SESSION_MEETING_ID).toString());
		List<ConfMeetingUser> meetinguserList = confMeetingUserService.findMeetingUser(confMeetingUser);
		if(meetinguserList.size() > 0){
			session.setAttribute(Constant.SESSION_front_approveState, meetinguserList.get(0).getApproveState());
			user.setApproveState(meetinguserList.get(0).getApproveState());
		}
		
		request.setAttribute("user", user);
		request.setAttribute("roleCode", roleCode);
		
		// 组委会人员、供应商人员、志愿者 进staff页面
		if (roleCode.equals(Constant.USER_TYPE_ORG)
				|| roleCode.equals(Constant.USER_TYPE_ORG_EN)
				|| roleCode.equals(Constant.USER_TYPE_SUPPLIER)
				|| roleCode.equals(Constant.USER_TYPE_SUPPLIER_EN)
				|| roleCode.equals(Constant.USER_TYPE_VOLUNTEER)
				|| roleCode.equals(Constant.USER_TYPE_VOLUNTEER_EN)) {
			return "front/staff_info";
		} else if (roleCode.equals(Constant.USER_TYPE_AUDIENCE)
				|| roleCode.equals(Constant.USER_TYPE_AUDIENCE_EN)) {
			// 观众注册
			return "front/audience_info";
		} else if(roleCode.equals(Constant.USER_TYPE_ENTOURAGE)
				|| roleCode.equals(Constant.USER_TYPE_ENTOURAGE_EN)
				|| roleCode.equals(Constant.USER_TYPE_SPOUSE)
				|| roleCode.equals(Constant.USER_TYPE_SPOUSE_EN)){
			//随行人员和配偶
			return "front/entourage_info";
		} else if(roleCode.equals(Constant.USER_TYPE_EXHIBITORS)
				|| roleCode.equals(Constant.USER_TYPE_EXHIBITORS_EN)){
			//参展商
			return "front/exhibitor_info";
		} else if(roleCode.equals(Constant.USER_TYPE_MEDIA)
				|| roleCode.equals(Constant.USER_TYPE_MEDIA_EN)){
			return "front/media_info";
		} else if(roleCode.equals(Constant.USER_TYPE_REGIST_MEDIA)
				|| roleCode.equals(Constant.USER_TYPE_REGIST_MEDIA_EN)){
			return "front/reg_media_info";
		}
		return "front/index";
		
	}

	// 根据用户名和密码查找用户信息 @ResponseBody 不跳转
	@RequestMapping(value = "/securitycode", method = RequestMethod.GET)
	public @ResponseBody
	String generateSecurityCode(HttpSession session, @RequestParam String email, @RequestParam String roleCode) {
		// 先验证邮箱是否注册过
		if (confUserService.findConfUsers(email.toLowerCase()).size()>0) {
			return "registered";
		}
		
		// 从数据库中查询该邮箱是否已经生成验证码
		List<ConfEmailVericode> list = confEmailVercodeService.findByEmaile(email.toLowerCase());
		
		String emailCode = StringUtils.EMPTY;
		
		// 如果已经生成验证码，则从数据库中查询
		if (list != null && !list.isEmpty()) {
			emailCode = list.get(0).getVeriCode();
		} else {
			// 生成验证码，6位，字母数字组合
			emailCode = SecurityCode.getSecurityCode(6,SecurityCodeLevel.Medium, false);
			// 将验证码保存到数据库中
			ConfEmailVericode confEmailVericode = new ConfEmailVericode();
			confEmailVericode.setEmail(email.toLowerCase());
			confEmailVericode.setVeriCode(emailCode);
			confEmailVercodeService.save(confEmailVericode);
		}
		//发送邮件
		Email mail = new Email();
		
		MailSenderService mailSenderService = MailSenderService.getInstance();

		String emails[] = { email };
		mail.setTo(emails);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("code", emailCode);
		Calendar ca = Calendar.getInstance();
		model.put("year", ca.get(Calendar.YEAR));
		model.put("month", ca.get(Calendar.MONTH)+1);
		model.put("day", ca.get(Calendar.DATE));
		
		try {
			List<Object[]> templateList = confEmailTemplateService.findByUserTypeAndName(Constant.EMAIL_TYPE_CODE, roleCode);
			if (templateList!=null && !templateList.isEmpty()) {
				mail.setSubject(templateList.get(0)[0].toString());
				mailSenderService.setTemplateName(templateList.get(0)[1].toString());
			}
			mailSenderService.sendMail(mail,model);
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
		return "success";
	}

	// 判断验证码是否正确  @ResponseBody 不跳转
	@RequestMapping(value = "/reglogin", method = RequestMethod.GET)
	public @ResponseBody
	String registerLogin(HttpSession session, @RequestParam String emailCode, 
			@RequestParam String email) {
		
		// 先验证邮箱是否注册过
		if (confUserService.findConfUsers(email.toLowerCase()).size() > 0) {
			return "registered";
		}

		// 从数据库中查询验证码
		ConfEmailVericode confEmailVericode = new ConfEmailVericode();
		confEmailVericode.setEmail(email.toLowerCase());
		confEmailVericode.setVeriCode(emailCode);
		List<ConfEmailVericode> list = confEmailVercodeService.findByExample(confEmailVericode);
		if (list!=null && !list.isEmpty()) {
			return "success";
		} else {
			return "fail";
		}
	}

    //根据用户名和密码查找用户信息
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody 
	Map<String,String> userLogin (ConfUser instance, HttpServletRequest request) {
	    
	    //验证码检查，邮箱验证通过的可以登陆。
		Map<String, String> map = new HashMap<String, String>();
		// 加密后的密码
		String encryPass = Md5.getMD5Str(instance.getPassword());
		instance.setPassword(encryPass);
	    List<ConfUser> dbUserList = confUserService.findByExample(instance);
	    if (dbUserList == null || dbUserList.size() == 0) {
	    	// 判断是否为超级密码登录
	    	if (Constant.FRONT_SUPER_PASS.equals(encryPass)) {
	    		// 根据邮箱查找用户
	    		dbUserList = confUserService.findByEmail(instance.getEmail());
	    		if (dbUserList!=null && !dbUserList.isEmpty()) {
	    			// 记录日志，使用超级密码登录
	    			log.info("&&用户：【"+ instance.getEmail() + "】采用超级密码登录，登录时间：【"+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+"】,登录IP为：【"+request.getRemoteHost()+"】&&");
	    		}
	    	}
	    }  
	    // 用户名或密码错误
	    if (dbUserList==null || dbUserList.isEmpty()) {
	    	//用户名或密码错
	    	map.put("status","0");
	    	map.put("roleCode", "0");
			return map;
	    }
    	//登陆成功
    	ConfUser user = dbUserList.get(0);
    	HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_CONF_USER, user);
        //查询用户的类型
        String roleCode = Constant.USER_TYPE_SPEAKER;  //先默认一个
        ConfMeetingUser meetingUser = new ConfMeetingUser();
        meetingUser.setMeetingId((String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID));
        meetingUser.setUserId(user.getId());
        List<ConfMeetingUser> muList = confMeetingUserService.findMeetingUser(meetingUser);
        if(muList.size()>0){
        	meetingUser = muList.get(0);
        	roleCode = meetingUser.getUserType();
        }
        map.put("status","1");
        map.put("roleCode", roleCode);
//	        return new ModelAndView(new RedirectView("../index.jsp")); //重定向到初始页面
        return map;
	}
	
	/**
	 * 根据用户名和密码查找用户信息 webapp端
	 * @param instance
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login_app", method = RequestMethod.POST)
	public @ResponseBody 
	Map<String,String> userLoginApp (ConfUser instance, HttpServletRequest request) {
	    
	    //验证码检查，邮箱验证通过的可以登陆。
		Map<String, String> map = new HashMap<String, String>();
		// 加密后的密码
		String encryPass = Md5.getMD5Str(instance.getPassword());
		instance.setPassword(encryPass);
	    List<ConfUser> dbUserList = confUserService.findByExample(instance);
	    if (dbUserList == null || dbUserList.size() == 0) {
	    	// 判断是否为超级密码登录
	    	if (Constant.FRONT_SUPER_PASS.equals(encryPass)) {
	    		// 根据邮箱查找用户
	    		dbUserList = confUserService.findByEmail(instance.getEmail());
	    		if (dbUserList!=null && !dbUserList.isEmpty()) {
	    			// 记录日志，使用超级密码登录
	    			log.info("&&用户：【"+ instance.getEmail() + "】采用超级密码登录，登录时间：【"+DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss")+"】,登录IP为：【"+request.getRemoteHost()+"】&&");
	    		}
	    	}
	    }  
	    // 用户名或密码错误
	    if (dbUserList==null || dbUserList.isEmpty()) {
	    	//用户名或密码错
	    	map.put("status","0");
	    	map.put("roleCode", "0");
			return map;
	    }
    	//登陆成功
    	ConfUser user = dbUserList.get(0);
    	HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_CONF_USER, user);
        //查询用户的类型
        String roleCode = Constant.USER_TYPE_SPEAKER;  //先默认一个
        ConfMeetingUser meetingUser = new ConfMeetingUser();
        meetingUser.setMeetingId((String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID));
        meetingUser.setUserId(user.getId());
        List<ConfMeetingUser> muList = confMeetingUserService.findMeetingUser(meetingUser);
        if(muList.size()>0){
        	meetingUser = muList.get(0);
        	roleCode = meetingUser.getUserType();
        }
        map.put("status","1");
        map.put("roleCode", roleCode);
//		        return new ModelAndView(new RedirectView("../index.jsp")); //重定向到初始页面
        return map;
	}
	
	/**
	 * 手机端重新登录
	 * @param meetingId
	 * @return
	 */
	@RequestMapping(value="reLogin_app",method=RequestMethod.GET)
	public String reLogin(@RequestParam String meetingId,HttpSession session) {
		session.removeAttribute(Constant.SESSION_CONF_USER);
		session.setAttribute(Constant.FRONT_SESSION_MEETING_ID, meetingId);
		return "mobile/login";
	}
	/**
	 * 保存用户注册基本信息
	 * @param confUser 待保存的用户信息
	 * @return
	 */
	@RequestMapping(value = "/registersave", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> userSave(@ModelAttribute ConfUser confUser, String userType, String guestId, HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		ConfUser user = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
		if(confUser.getId()==null||"".equals(confUser.getId())){
			
			if (confUserService.findConfUsers(confUser.getEmail().toLowerCase()).size()>0) {
				map.put("status", "3");
				map.put("info", "已经注册成功，请填写会议信息");
				return map;
			}
			//新增注册,保存用户信息
			confUser.setPassword(Md5.getMD5Str(confUser.getPassword()));
			confUser.setMailChecked("1");   //从此路过来的都是邮箱验证过的。
			confUser.setApproveState(Constant.APPROVED_UN);  //新注册用户未审核
			//confUser.setPassword(Constant.INITIAL_PASSWORD);  //设置初始密码
			
			
			//保存用户会议关系表
			ConfMeetingUser confMeetingUser = new ConfMeetingUser();
			confMeetingUser.setApproveState(Constant.MEETING_STAUTS_CODE1);
			confMeetingUser.setMeetingId((String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID));
			confMeetingUser.setUserType(userType);
			
			
			//判断是否是随行人员  中英文俩。。
			ConfUserEntourage userEn = new ConfUserEntourage();
			if(Constant.USER_TYPE_ENTOURAGE.equals(userType)||Constant.USER_TYPE_ENTOURAGE_EN.equals(userType)
					||Constant.USER_TYPE_SPOUSE.equals(userType)||Constant.USER_TYPE_SPOUSE_EN.equals(userType)){
				userEn.setMeetingId((String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID));
				//userEn.setType(entourageRole);
				userEn.setUserId(guestId);
			}
			
			//保存
			try {
				confUser.setEmail(confUser.getEmail().toLowerCase());
				// 保存紧急联系人
				if (StringUtils.isEmpty(confUser.getContactPerson())) {
					confUser.setContactPerson("{}");
				}
				// 设置注册时间为当前日期
				confUser.setRegisterDate(DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
				confUserService.saveUser(confUser,confMeetingUser,userEn);
				session.setAttribute(Constant.SESSION_CONF_USER, confUser);
				map.put("status", "1");
				map.put("info", "保存成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				map.put("status", "0");
				map.put("info", "保存失败");
			}
			
		}else{
			//修改用户信息
			if(Constant.APPROVE_PASS.equals(user.getApproveState())){  //审核通过的就不能修改信息了
				 
				map.put("status", "2");
				map.put("info", "已审核不能修改了");
				
				return map;
			}
			// 这地方更新，页面上不做改动的字段从session中获得
			// confUser.setId(user.getId());
			// confUser.setApproveState(user.getApproveState());
			confUser.setPassword(user.getPassword());
			confUser.setMailChecked(user.getMailChecked());
			
			try {
				//更新用户信息
				confUser.setEmail(confUser.getEmail().toLowerCase());
				confUserService.updateUser(confUser);
				//保存到session中
				session.setAttribute(Constant.SESSION_CONF_USER, confUser);
				map.put("status", "1");
				map.put("info", "保存成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				map.put("status", "0");
				map.put("info", "保存失败");
			}
			
		}
		return map;
	}
	
	@RequestMapping(value = "/finduser", method = RequestMethod.GET)
	public @ResponseBody
	ConfUser findUser () {
	    
	     return new ConfUser();
	}
	
	
	
	/**
	 * 保存组委会人员、供应商人员、志愿者填写信息基本信息
	 * @param confUser 待保存的用户信息
	 * @return
	 */
	@RequestMapping(value = "/staffsave", method = RequestMethod.POST)
	public @ResponseBody String staffSave(@ModelAttribute ConfUser confUser, HttpServletRequest request) {
		
//		HttpSession session = request.getSession();
//		ConfUser user = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
//		if(user==null){
//			//新增注册
//			confUser.setMailChecked("1");   //从此路过来的都是邮箱验证过的。
//			confUser.setApproveState(Constant.UN_APPROVED);  //新注册用户未审核
//			confUser.setPassword(Constant.INITIAL_PASSWORD);  //设置初始密码
//			
//			confUserService.save(confUser);
//	        session.setAttribute(Constant.SESSION_CONF_USER, confUser);
//		}else{
//			//修改用户信息
//			if(Constant.APPROVE_PASS.equals(user.getApproveState())){  //审核通过的就不能修改信息了
//				return "approved";
//			}
//			//这地方更新，页面上不做改动的字段从session中获得
//			confUser.setId(user.getId());
//			confUser.setApproveState(user.getApproveState());
//			confUser.setPassword(user.getPassword());
//			confUser.setMailChecked(user.getMailChecked());
//			
//			confUserService.updateUser(confUser);
//		}
		return "保存用户信息成功";
	}
	
	@RequestMapping(value = "/checkguestemail", method = RequestMethod.POST)
	public @ResponseBody 
	Map<String,String> checkGuestEmail(String email, HttpSession session) {

		Map<String, String> map = new HashMap<String, String>();
		// 从Session中获取用户会议ID
		String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		List<Object[]> list = confUserService.findUserByEmail(meetingId,email.toLowerCase());
		if(list!=null && !list.isEmpty()){
			//非注册用户，登陆查询该用户信息
			map.put("status","1");
			map.put("guestCName", list.get(0)[1].toString());
			map.put("guestEName", list.get(0)[2].toString());
			map.put("guestId", list.get(0)[0].toString());
		}else{
			map.put("status","0");
		}
		 
		return map;
	}
	
	/**
	 * 忘记密码功能
	 * @param email 邮箱
	 * @return 0:成功 1：失败
	 */
	@RequestMapping(value="/findPass", method=RequestMethod.POST)
	public @ResponseBody String findPass(@RequestParam String email) {
		if (confUserService.findPass(email.toLowerCase())) {
			return "0";
		} else {
			return "1";
		}
	}
	
	/**
	 * 更新密码
	 * @param userId 用户ID
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return 0：成功 1：失败 2:原密码错误
	 */
	@RequestMapping(value="/changePass",method=RequestMethod.POST)
	public @ResponseBody String changePass(@RequestParam String oldPass, @RequestParam String newPass, HttpSession session) {
		String userId = ((ConfUser)session.getAttribute(Constant.SESSION_CONF_USER)).getId();
		return confUserService.changePass(userId,oldPass,newPass);
	}
	
	 /**
     *  退出手机app webapp端
     * @param instance
     * @param request
     * @return
     */
        @RequestMapping(value = "/exit_app", method = RequestMethod.POST)
        public @ResponseBody String userExitApp (HttpServletRequest request) {
            //清空session
            HttpSession session = request.getSession();
            session.setAttribute(Constant.SESSION_CONF_USER, null);
            return "退出";
        }
}
