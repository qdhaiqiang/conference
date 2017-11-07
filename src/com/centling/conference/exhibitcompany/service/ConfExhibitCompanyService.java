package com.centling.conference.exhibitcompany.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.exhibitcompany.DAO.ConfExhibitCompanyDAO;
import com.centling.conference.exhibitcompany.entity.ConfExhibitCompany;
import com.centling.conference.exhibition.entity.ConfExhibition;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.Md5;

@Service("confExhibitCompanyService")
public class ConfExhibitCompanyService {
	
	@Resource
	private ConfExhibitCompanyDAO confExhibitCompanyDAO;
	@Resource
    private ConfUserDAO confUserDAO;
	@Resource
	private ConfMeetingUserDAO confMeetingUserDAO;
	
	/**
	 * 展商登录，申请展位时登录
	  	1.登录时是以参展商公司表为准，先查找这个表中是否有这个用户email,如果有，验证密码是否正确；
		2.如果公司表中，没有此email,去用户表中查找，如果没有该email，不能登录；
		3.user表中，如果有此email,验证密码是否正确，并且验证是否是本次参会展商；
		4.如果正确，找到用户填写的公司company，在在公司表中根据此company查找，该公司是否已经有登录用户；
		5.如果没有登录用户，登录成功，提示：是否将此账号作为该参展商的登录帐号。
	 * @param email
	 * @param pswd
	 * @param meetingId
	 * @return
	 */
	public Map<String,String> checkLogin(String email,String pswd,String meetingId){
		Map<String, String> map = new HashMap<String, String>();
		boolean isLogSuccess = false;
		boolean isExhibitCompany = false;
		ConfExhibitCompany confExhibitCompany = new ConfExhibitCompany();
		confExhibitCompany.setLoginEmail(email);
		confExhibitCompany.setLoginPassword(Md5.getMD5Str(pswd));
		List<ConfExhibitCompany> list = confExhibitCompanyDAO.findByLoginEmail(email);//此处只根据邮箱查找，不要密码，有密码无法区分是没有用户还是密码错误
		if(list != null && list.size() > 0){
			//展商单位表中有此账号，然后去验证密码
			confExhibitCompany = list.get(0);
			if(confExhibitCompany.getLoginPassword().equals(Md5.getMD5Str(pswd))){
				//登录成功
				isLogSuccess = true;
			}
		}else{
			//没有此账号
			confExhibitCompany.setLoginEmail(email);
			confExhibitCompany.setLoginPassword(Md5.getMD5Str(pswd));
		}
		if(!isLogSuccess){
			//登录失败，分为两种情况1.没有此email;2.密码不正确
			if(confExhibitCompany.getCompanyId() == null || confExhibitCompany.getCompanyId().length() == 0){
				//展商单位表中没有此email用户，去user表中查询，并且必须注册了本次会议，用户角色必须是展商；
				ConfUser confUser = new ConfUser();
				confUser.setEmail(email);
				confUser.setPassword(Md5.getMD5Str(pswd));
				List<ConfUser> userList = confUserDAO.findByExample(confUser);
				if(userList != null && userList.size() > 0){
					//账号和密码正确，在此验证是否是本次参会展商
					confUser = userList.get(0);
					//该用户是否属于该会议的参展商
			    	ConfMeetingUser confMeetingUser = new ConfMeetingUser();
			    	confMeetingUser.setMeetingId(meetingId);
			    	confMeetingUser.setUserId(confUser.getId());
			    	//是否要审核通过？
			    	List<ConfMeetingUser> confMeetingUsersList = confMeetingUserDAO.findByExample(confMeetingUser);
			    	if(confMeetingUsersList != null && confMeetingUsersList.size() > 0){
			    		confMeetingUser = confMeetingUsersList.get(0);
			    		if(confMeetingUser.getUserType().equals(Constant.USER_TYPE_EXHIBITORS) || confMeetingUser.getUserType().equals(Constant.USER_TYPE_EXHIBITORS_EN)){
			        		//验证成功，此用户是本次会议的参展商用户；然后验证该用户所在的公司是否已经注册了登录账号
			    			List<ConfExhibitCompany> companyCnList = confExhibitCompanyDAO.findByCompanyNameCn(confUser.getCompany());
			    			List<ConfExhibitCompany> companyEnList = confExhibitCompanyDAO.findByCompanyNameEn(confUser.getCompany());
			    			if((companyCnList==null || companyCnList.size() == 0) && (companyEnList==null || companyEnList.size() == 0)){
			    				//说明该展商用户的单位，没有初始化公司的登录帐号，
			    				//此账号有登录的权限，前提是必须将此个人账号设为该展商公司的登录账号，该展商的其他账号将不能登录展商申请
			    				isExhibitCompany = true;
			    			}
			        	}
			    	}
				}
			}
		}
		
		if(isLogSuccess){
			map.put("status","1");//登录成功
			map.put("info","登录成功！");//info
		}else{
			if(isExhibitCompany){
				map.put("status","2");//有登录权限，但是需要页面的确认
				map.put("info","需要确认是否将此账号作为参展商登录帐号。");//info
			}else{
				//用户密码错误
				map.put("status","0");//登录失败
				map.put("info","用户名或者密码错误。");//info
			}
		}
		return map;
	}
	
	/**
	 * 用户登录，此时分为两种情况：1.公司帐号登录；2.个人帐号登录同意设为公司帐号
	 * 		1.查询此账号是否是公司帐号，如果是，直接登录，并存session
	 * 		2.如果是个人账户，执行参展商初始化公司帐号的操作
	 * @param email
	 * @param pswd
	 * @param meetingId
	 * @return
	 */
	public ConfExhibitCompany exhibitionLogin(String email,String pswd){
		ConfExhibitCompany confExhibitCompany = new ConfExhibitCompany();
		confExhibitCompany.setLoginEmail(email);
		confExhibitCompany.setLoginPassword(Md5.getMD5Str(pswd));
		//先查看此账号是否已经是公司帐号
		List<ConfExhibitCompany> list = confExhibitCompanyDAO.findByExample(confExhibitCompany);
		if(list != null && list.size() == 1){
			confExhibitCompany = list.get(0);
		}else{
			//不是公司帐号，要升级为公司帐号；将本用户的公司等信息加载初始化到公司帐号中
			//ConfUser confUser = confUserDAO.findByEmail(email).get(0);//此时除了后台删除此用户，否则一定存在此账号
			ConfUser confUser = new ConfUser();
			List<ConfUser> userList = confUserDAO.findByEmail(email);
			if(userList != null && userList.size() == 1){
				confUser = userList.get(0);
				confExhibitCompany.setCompanyNameEn(confUser.getCompany());
				confExhibitCompany.setCompanyNameCn(confUser.getCompany());
				confExhibitCompany = confExhibitCompanyDAO.merge(confExhibitCompany);
			}			
		}
		return confExhibitCompany;
	}

	public List<ConfExhibition> getCompanyList() {
		return confExhibitCompanyDAO.findAll();
	}
}
