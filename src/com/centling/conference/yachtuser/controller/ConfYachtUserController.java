package com.centling.conference.yachtuser.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.emailtemplate.service.ConfEmailTemplateService;
import com.centling.conference.meetinguser.controller.ConfMeetingUserController;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.SecurityCode;
import com.centling.conference.util.SecurityCode.SecurityCodeLevel;
import com.centling.conference.util.exportexcels.ExportExcelsUtil;
import com.centling.conference.yachtuser.entity.ConfYachtUser;
import com.centling.conference.yachtuser.service.ConfYachtUserService;

@Controller("confYachtUserController")
@RequestMapping("/confYachtUser/*")
public class ConfYachtUserController {
	private static final Logger log = LoggerFactory.getLogger(ConfMeetingUserController.class);

	@Resource
	private ConfYachtUserService confYachtUserService;
	@Resource
	private ConfEmailTemplateService confEmailTemplateService;
	
	/**
	 * 添加user
	 * @param user
	 * @param session
	 * @return
	 */
    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> addUser (@ModelAttribute ConfYachtUser user,HttpSession session) {
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	if(confYachtUserService.emailIsExist(user.getEmail())){
    		String msg = "1";
    		map.put("msg", msg);
    		return map;
    	}
    	String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    	user.setCreateTime(currentDate);
    	user.setUpdateTime(currentDate);
    	String veriCode = StringUtils.EMPTY;
    	veriCode = SecurityCode.getSecurityCode(6,SecurityCodeLevel.Medium, false);
    	user.setVeriCode(veriCode);
        if(confYachtUserService.save(user)){
        	map.put("user", user);
        	//session.setAttribute("confYachtUser", user);
        	//发送邮件
    		Email mail = new Email();    		
    		MailSenderService mailSenderService = MailSenderService.getInstance();
    		String emails[] = { user.getEmail() };
    		mail.setTo(emails);
    		Map<String, Object> model = new HashMap<String, Object>();
    		model.put("code", veriCode);
    		model.put("user", user.getCname());
    		Calendar ca = Calendar.getInstance();
    		model.put("year", ca.get(Calendar.YEAR));
    		model.put("month", ca.get(Calendar.MONTH)+1);
    		model.put("day", ca.get(Calendar.DATE));
    		
    		try {
    			List<Object[]> templateList = confEmailTemplateService.findByUserTypeAndName(Constant.EMAIL_TYPE_YACHT_CODE, "");
    			if (templateList!=null && !templateList.isEmpty()) {
    				mail.setSubject(templateList.get(0)[0].toString());
    				mailSenderService.setTemplateName(templateList.get(0)[1].toString());
    			}
    			mailSenderService.sendMail(mail,model);
    		} catch (Exception e) {
    			e.printStackTrace();
    			String msg = "2";
        		map.put("msg", msg);
    			return map;
    		}
    		String msg = "3";
    		map.put("msg", msg);
        	return map;
        }else{
        	String msg = "注册失败,请重新注册";
    		map.put("msg", msg);
        	return map;
        }
       
    }
    /**
  	 * 跳转到yacht_suc
  	 * @param user
  	 * @param session
  	 * @return
  	 */
	@RequestMapping(value = "/jumpToYacht_suc", method = RequestMethod.POST)
	public ModelAndView jumpToYacht_suc(String id, HttpSession session) {
		ConfYachtUser user = confYachtUserService.findUserById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "注册成功,稍后您将收到一份邮件";
		map.put("msg", msg);
		map.put("user", user);
		return new ModelAndView("front/yacht/yacht_suc", "map", map);

	}
      /**
       * 跳转到yacht_edit
       * @param user
       * @param session
       * @return
       */
      @RequestMapping(value="/jumpToYacht_edit/{id}", method = RequestMethod.GET)
      public ModelAndView jumpToYacht_edit (@PathVariable String id,HttpSession session) {
    	  ConfYachtUser user = confYachtUserService.findUserById(id);
    	  Map<String,Object> map = new HashMap<String,Object>();
    	  String msg = "登录成功";
    	  map.put("msg", msg);
    	  map.put("user", user);
    	  return new ModelAndView("front/yacht/yacht_edit","map",map);
    	  
      }
    /**
	 * 邮箱登陆
	 * @param user
	 * @param session
	 * @return
	 */
    @RequestMapping(value="/loginIn", method = RequestMethod.POST)
    public @ResponseBody Map loginIn (String loginEmail,String loginPassword,HttpSession session) {
    	Map<String,Object> map = new HashMap<String,Object>();
        if(confYachtUserService.emailIsExist(loginEmail)){
        	if(confYachtUserService.loginIn(loginEmail, loginPassword)){	
        		ConfYachtUser user = (ConfYachtUser) confYachtUserService.findByEmail(loginEmail).get(0);
        		map.put("user", user);
        		String msg = "1";
        		map.put("msg", msg);
        		return map;
        	}
        	String msg = "2";
    		map.put("msg", msg);
        	return map;
        }else{
        	String msg = "3";
    		map.put("msg", msg);
        	return map;
        }
       
    }
    /**
     * 检查邮箱是否注册过
     * @param email
     * @return
     */
    @RequestMapping(value="/checkEmailIsExist", method=RequestMethod.POST)
	public @ResponseBody boolean checkEmailIsExist(@RequestParam String email) {
		return confYachtUserService.emailIsExist(email);
	}
    
    /**
	 * 修改user
	 * @param user
	 * @param session
	 * @return
	 */
    @RequestMapping(value="/editUser", method = RequestMethod.POST)
    public @ResponseBody ModelAndView editUserInfo (@ModelAttribute ConfYachtUser user,HttpSession session) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	String currentDate = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    	user.setUpdateTime(currentDate);
    	if(confYachtUserService.update(user)){
    		map.put("user", user);
        	String msg = "1";
    		map.put("msg", msg);
    		return new ModelAndView("front/yacht/yacht_edit","map",map);
    	}else{
    		map.put("user", user);
        	String msg = "2";
    		map.put("msg", msg);
    		return new ModelAndView("front/yacht/yacht_edit","map",map);
    	}
    	
    }
    /**
	 * 人员信息导出
	 * @return
     * @throws IOException 
	 */
	@RequestMapping(value="/exportUser",method=RequestMethod.POST)
	public @ResponseBody String exportRoomStatic(String cname, String userType,HttpServletResponse response) throws IOException {
		String[] headers = new String[]{"姓名","性别","国家","证件号码","出生日期","工作单位","职务","手机号码","电子邮箱","邮寄地址","抵达航班","抵达时间","接送位置","用户类型","验证码","名片正面","名片反面"};
		// 从Session中获取会议ID
		List<Object[]> dataset = confYachtUserService.exportUser(cname,userType);
        if(dataset == null || dataset.size() < 1){
        	return "没有查找到相应数据";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=exportUser.xls");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			ExportExcelsUtil.exportExcel(headers,dataset, sos);
			response.flushBuffer();
		} catch (IOException e) {
			log.error("导出用户数据出现异常"+e.getMessage());
			throw e;
		}
        return "成功导出"+dataset.size()+"条用户数据。";
	}
	
	/**
	 * 人员信息查询
	 * @return
	 */
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	public @ResponseBody Pagination getUser(PageBean pageBean,@RequestParam String cname, @RequestParam String userType) {
		return confYachtUserService.getUser(pageBean,cname,userType);
	}
}