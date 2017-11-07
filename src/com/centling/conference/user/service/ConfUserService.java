package com.centling.conference.user.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.dict.service.ConfDictService;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.DAO.ConfUserExportDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.user.entity.ConfUserExptSrchCondModel;
import com.centling.conference.userentourage.DAO.ConfUserEntourageDAO;
import com.centling.conference.userentourage.entity.ConfUserEntourage;
import com.centling.conference.util.CommonsMethod;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.ExcelOperations;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.Md5;
import com.centling.conference.util.QRCodeUtil;
import com.centling.conference.util.SecurityCode;
import com.centling.conference.util.SecurityCode.SecurityCodeLevel;

@Service("confUserService")
public class ConfUserService {

	private static final Logger logger = LoggerFactory.getLogger(ConfUserService.class);

	
    @Resource
    ConfUserDAO confUserDAO;
    @Resource
    ConfMeetingUserDAO confMeetingUserDAO;
    @Resource
    ConfUserEntourageDAO confUserEntourageDAO;
    @Resource
    ConfUserExportDAO confUserExportDAO;


	//通过用户输入的用户名和密码查找
    public List<ConfUser> findByExample (ConfUser instance) {
        return confUserDAO.findByExample(instance);
    }
    
    public Pagination findUsers(PageBean pageBean, String meetingId, ConfGuest user) {
    	Pagination pagination = new Pagination();
    	List<Map> list = confUserDAO.findUsers(pageBean, meetingId, user);
		pagination.setRows(list);
		pagination.setTotal(confUserDAO.count(meetingId, user));
		return pagination;
    }

	public ArrayList<Object> readExcel(String filePath) throws Exception{

		ExcelOperations oper = new ExcelOperations();
		ArrayList<Object> list = oper.readExcel(filePath, ConfGuest.class, columnHeaders);

	    return list;

	}

	public void validateDicValue(String value, List<ConfDict> params) throws Exception{
		boolean flag = false;

		for(ConfDict param: params){
			if(param.getCode().equals(value)){
				flag = true;
				return;
			}
		}

		if(!flag){
			throw new Exception("Invalid value: " + value);
		}

	}

	/*
	 * 导入支持以下字段：
  `cname` varchar(40) DEFAULT NULL COMMENT '中文名',
  `last_name` varchar(20) DEFAULT NULL COMMENT '姓',
  `ename` varchar(80) DEFAULT NULL COMMENT '英文名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别 （字典 男 女 保密）',
  `birth` varchar(10) DEFAULT NULL COMMENT '出生日期',
  `nation` varchar(2) DEFAULT NULL COMMENT '国籍（业务字典）',
  `cert_type` varchar(1) DEFAULT NULL COMMENT '证件类型（业务字典）',
  `cert_value` varchar(50) DEFAULT NULL COMMENT '证件号码',
  `cert_expiry_date` varchar(10) DEFAULT NULL COMMENT '证件过期日期',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `tele` varchar(20) DEFAULT NULL COMMENT '固话',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `postcode` varchar(10) DEFAULT NULL COMMENT '邮编',
  `industry` varchar(2) DEFAULT NULL COMMENT '行业(字典表)',
  `company` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
	 */
	public static final String[] columnHeaders = { "cname", "lastName",
			"ename", "sex", "birth", "nation", "certType", "certValue",
			"certExpiryDate", "email", "tele", "mobile", "fax", "address",
			"postcode", "industry", "company", "position" };


	/**
	 * 根据邮箱查找用户，判断注册的邮箱是否已经注册过
	 * @param email
	 * @return List<ConfUser>
	 */
	public List<ConfUser> findConfUsers(String email){
		List<ConfUser> confUsers = confUserDAO.findByEmail(email);
		return confUsers;
	}

	/**
	 * 保存注册用户信息
	 * @param confUser 待保存的用户信息
	 */
	public void save(ConfUser confUser) {
		confUserDAO.save(confUser);
	}

	/**
	 * 更新用户信息。未审核以及审核未通过的用户可以修改基本信息
	 * @param confUser
	 */
	public void updateUser(ConfUser confUser){
		if (confUser.getEntryDate() == null)
			confUser.setEntryDate("");
		    confUser.setUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));// 设置最后修改日期
		confUserDAO.update(confUser);
	}

	public ConfUser findbyId(String confUserId) {
		return confUserDAO.findById(confUserId);
	}


	/**
	 * 保存注册用户信息
	 * @param confUser 待保存的用户信息
	 */
	public void saveUser(ConfUser confUser, ConfMeetingUser confMeetingUser, ConfUserEntourage userEn) {
		if (confUser.getEntryDate() == null)
			confUser.setEntryDate("");
		confUserDAO.save(confUser); //保存用户

		confMeetingUser.setUserId(confUser.getId());
		confMeetingUser.setRemindFlag("0");
		confMeetingUserDAO.save(confMeetingUser); //保存用户会议关系
		
		if(userEn.getUserId()!=null){

			userEn.setEntourageId(confUser.getId());
			confUserEntourageDAO.save(userEn);  //保存用户随行关系
		}
	}

    //根据邮箱查询嘉宾
    public List<ConfUser> findByEmail(String email) {
        return confUserDAO.findByEmail(email.toLowerCase());
    }

    /**
	 * 忘记密码功能
	 * @param email
	 * @return
	 */
	public boolean findPass(String email) {
		boolean flag = false;
		// check Email registered
		List<ConfUser> userList = confUserDAO.findByEmail(email);
		// not Registered
		if (userList==null || userList.isEmpty()) {
			return false;
		}
		Email emailObj = new Email();
		emailObj.setTo(new String[]{email});
		emailObj.setSubject("找回密码");
		// 生成随机密码
		String newPass = SecurityCode.getSecurityCode(6,SecurityCodeLevel.Medium, false);
		emailObj.setText("您的新密码为："+newPass);
		try {
			// 发送邮件
			MailSenderService.getInstance().sendMail(emailObj);

			// 将新密码保存到数据库
			String encryPass = Md5.getMD5Str(newPass);
			if (userList!=null&&!userList.isEmpty()) {
				ConfUser confUser = userList.get(0);
				confUser.setPassword(encryPass);
				confUserDAO.updatePass(confUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 修改密码
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return
	 */
	public String changePass(String userId,String oldPass, String newPass) {
		// 用户信息
		ConfUser confUser = confUserDAO.findById(userId);

		// 判断原密码是否正确
		if (!Md5.checkpassword(oldPass, confUser.getPassword())) {
			return "2";
		}

		// 更新密码
		confUser.setPassword(Md5.getMD5Str(newPass));
		try {
			confUserDAO.updatePass(confUser);
		} catch (Exception e) {
			return "1";
		}
		return "0";
	}

	public List<Object[]> findUserByEmail(String meetingId,String email) {
		return confUserDAO.findUserByEmail(meetingId, email);
	}

	   //人员导出数据查询
    public Pagination findUserBySearchCond(PageBean pageBean, String meetingId, ConfUserExptSrchCondModel sCond) {
        Pagination pagination = new Pagination();
        List<Map> list = confUserExportDAO.findUserBySearchCond(pageBean, meetingId, sCond);
        pagination.setRows(list);
        pagination.setTotal(confUserExportDAO.count(meetingId, sCond));
        return pagination;
    }
    
    /**
     * 人员导出excel，查找符合条件的所有用户
     * @param meetingId
     * @param sCond 查询条件
     * @return
     */
    public List<Object[]> findAllUser(String meetingId, ConfUserExptSrchCondModel sCond) {
    	List<Object[]> list = confUserExportDAO.findAllUser(meetingId, sCond);
    	if(list == null || list.size() < 1){
    		return null;
    	}
        list = getDicMatchedList(list);
        return list;
    }
    
    /**
     * 导出选择的人员到excel
     * @param meetingId
     * @param sCond 查询条件
     * @return
     */
    public List<Object[]> findUserByUserIds(String meetingId, String userIds) {
        List<Object[]> list = confUserExportDAO.findUserByIds(meetingId, userIds);
        if(list == null || list.size() < 1){
            return null;
        }
        list = getDicMatchedList(list);
        return list;
    }
    
    public Object changeToDicValue(Object object,List<ConfDict> dicList){
    	if(object != null && !object.toString().equals("")){
    		for(ConfDict conf : dicList){
    			if(object.toString().equals(conf.getCode())){
    				object = conf.getName();
    				break;
    			}
    		}
    	}
    	return object;
    }
    
    //将"0"和"1"转换成"是"或者"否"
    public Object changeToYesOrNo(Object obj) {
        if (obj != null && !obj.toString().equals("")) {
            if (obj.equals(Constant.USER_EXP_SEARCH_CONDITION_HAS)) {
                return  Constant.USER_EXP_SEARCH_CONDITION_YES;
            }
            if (obj.equals(Constant.USER_EXP_SEARCH_CONDITION_NULL)) {
                return Constant.USER_EXP_SEARCH_CONDITION_NO;
            }
        }
        return "";
    }

    
    public String setContactPerson(Object ContactPerson){
    	String object = "";
    	if(ContactPerson != null && !ContactPerson.toString().equals("{}")){
    		object = ContactPerson.toString();
    		//{fullName:'nonono',position:'',sex:'1',contactNo:'',email:'',faxNo:'',mobile:'12345'}
    		if(object.contains("sex:'1'")){
    			object = object.replace("sex:'1'","性别：男");
    		}else{
    			object = object.replace("sex:'2'","性别：女");
    		}
    		object = object.replace("fullName", "姓名")
    				.replace("position", "职位信息")
    				.replace("contactNo", "固定电话")
    				.replace("email", "电子油箱")
    				.replace("faxNo", "传真号码")
    				.replace("mobile", "手机号")
    				.replace("{","")
    				.replace("}","");
    	}
    	return object;
    }
    
    /**
     * 来时交通类型，1飞机2高铁
     * @param typeCome
     * @return
     */
    public String setTypeCome(Object typeCome){
    	String object = "";
    	if(typeCome != null && !typeCome.toString().equals("")){
    		object = typeCome.toString();
    		if(object.equals("1")){
    			object = "飞机";
    		}else if(object.equals("2")){
    			object = "高铁";
    		}
    	}
    	return object;
    }
    //从数据库中获取字典对应的值
    public List<ConfDict> getDicList(String type, String flag) {
        return confDictService.findDictByCategory(type, flag);
    }
    
    //遍历取字典表中的值
    public List<Object[]> getDicMatchedList (List<Object[]> list ) {   	
      //获取用到的字典表中的内容
        List<ConfDict> userTypeDicList = confDictService.findDictByCategory("user_type","2");
        List<ConfDict> sexDicList = confDictService.findDictByCategory("sex","2");
        List<ConfDict> nationDicList = confDictService.findDictByCategory("nation","2");
        List<ConfDict> certTypeDicList = confDictService.findDictByCategory("cert_type","2");
        List<ConfDict> entryTypeDicList = confDictService.findDictByCategory("entry_type","2");
        List<ConfDict> companyTypeDicList = confDictService.findDictByCategory("company_type","2");
        List<ConfDict> industryDicList = confDictService.findDictByCategory("industry","2");
        List<ConfDict> roomTypeDicList = confDictService.findDictByCategory("room_type","2");
        
        for(int i=0;i<list.size();i++){
            Object[] object = list.get(i);
            //获取字典中的字典值，
            object[2] = changeToDicValue(object[2], userTypeDicList);
            object[5] = changeToDicValue(object[5], sexDicList);
            object[7] = changeToDicValue(object[7], nationDicList);
            object[18] = setContactPerson(object[18]);
            object[21] = changeToDicValue(object[21], certTypeDicList);
            object[23] = changeToDicValue(object[23], entryTypeDicList);
            object[31] = changeToDicValue(object[31], companyTypeDicList);
            object[32] = changeToDicValue(object[32], industryDicList);
            object[39] = changeToYesOrNo(object[39]);
            object[40] = changeToYesOrNo(object[40]);
            object[42] = changeToYesOrNo(object[42]);
            object[92] = setTypeCome(object[92]);
            object[98] = setTypeCome(object[98]);
            object[105] = changeToDicValue(object[105], roomTypeDicList);
        }
        return list;
    }
    @Resource
    private ConfDictService confDictService;

    /**
     * 查询未导入后台用户表的用户列表
     * @param confSysUser 查询条件
     * @return
     */
	public List<Map<String, Object>> getUnimportUser(ConfSysUser confSysUser, String meetingId) {
		return confUserDAO.getUnimportUser(confSysUser, meetingId);
	}

	public Pagination retrive(PageBean pageBean, ConfUser confUser, String meetingId, String userType) {
		Pagination pagination = new Pagination();
		List<Map<String,Object>> list = confUserDAO.retrive(pageBean, confUser, meetingId, userType);
		pagination.setRows(list);
		pagination.setTotal(confUserDAO.count(meetingId,confUser,userType));
		return pagination;
	}
	public List<Map<String,Object>> all(String meetingId){
		List<Map<String,Object>> list = confUserDAO.all(meetingId);
		return list;
	}
	public void syncData(String meetingId) {
		// 根据会议ID删除数据
		confUserDAO.deleteByMeetingId(meetingId);
		// 插入数据
		confUserDAO.insertDataByMeetingId(meetingId);
	}
	
    /**
     * 查询所有用户，用来导出所有人员的二维码信息
     * @return
     */
    public List<Object[]> findAllForExportQR(String meetingId, HttpServletRequest request) {
        List<Object[]> list =  confUserDAO.findAllForExportQR(meetingId);
        String QRimagePath = "";
        String qrCodeUrl = "";
        String qrCodePath = request.getServletContext().getRealPath("/") + "downloads"+File.separator+"QRcode" + File.separator;
        for (int i = 0;i < list.size();i++) {
            Object[] object = list.get(i);
            QRimagePath = QRCodeUtil.generateQrCode((String)object[0], null, qrCodePath);
            if (object[3] != null) {
                object[3] = (((String)object[3])).replace("\"", "/").replace(";", "");
            }
            qrCodeUrl = (QRimagePath.replace(request.getServletContext().getRealPath("/"), "")).replace(File.separator, "/");
            object[4] = qrCodeUrl;
        }
        list = getCardTypeMatchedList(list);
        return list;
    }
    
    //遍历取字典表中的值
    public List<Object[]> getCardTypeMatchedList (List<Object[]> list ) {
        //获取用到的字典表中的内容
        List<ConfDict> cardypeDicList = confDictService.findDictByCategory("card_type","2");
        List<ConfDict> userypeDicList = confDictService.findDictByCategory("user_type","2");
        for(int i=0;i<list.size();i++){
            Object[] object = list.get(i);
            //获取字典中的字典值，
            object[2] = changeToDicValue(object[2], cardypeDicList);
            object[5] = changeToDicValue(object[5], userypeDicList);
        }
        return list;
    }
}
