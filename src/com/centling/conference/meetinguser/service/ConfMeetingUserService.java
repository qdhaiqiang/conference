package com.centling.conference.meetinguser.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.base.Email;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.dict.service.ConfDictService;
import com.centling.conference.dyn.value.DAO.ConfDynValueDAO;
import com.centling.conference.emailtemplate.DAO.ConfEmailTemplateDAO;
import com.centling.conference.emailtemplate.entity.ConfEmailTemplate;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.schedule.DAO.ConfScheduleDAO;
import com.centling.conference.schedule.DAO.ConfScheduleUserDAO;
import com.centling.conference.schedule.entity.ConfSchedule;
import com.centling.conference.schedule.entity.ConfScheduleUser;
import com.centling.conference.travel.entity.SearchParams;
import com.centling.conference.user.DAO.ConfUserDAO;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.DateUtils;
import com.centling.conference.util.MailSenderService;
import com.centling.conference.util.Md5;

@Service("confMeetingUserService")
public class ConfMeetingUserService {

    @Resource
    private ConfMeetingUserDAO confMeetingUserDAO;
    @Resource
    private ConfUserDAO confUserDAO;
    @Resource
    private ConfScheduleDAO confScheduleDAO;
    @Resource
    private ConfScheduleUserDAO confScheduleUserDAO;
    @Resource
    private ConfDynValueDAO confDynValueDAO;
    @Resource
    private ConfEmailTemplateDAO confEmailTemplateDAO;
    @Resource
    private ConfDictService confDictService;

    public Pagination retrieve(PageBean pageBean, String meetingId, ConfGuest guest) {
        Pagination pagination = new Pagination();
        List<ConfGuest> list = confMeetingUserDAO.retrieve(pageBean, meetingId, guest);
        pagination.setRows(list);
        pagination.setTotal(confMeetingUserDAO.count(meetingId, guest));
        return pagination;
    }

    //根据ID查询嘉宾
    public ConfMeetingUser findById(String id) {
        return confMeetingUserDAO.findById(id);
    }

    //新增注册嘉宾信息
    public String save(ConfGuest instance, String schIds){
        instance.setApproveState(Constant.APPROVED_UN);
        String userId = saveUser(instance, true);
        instance.setUserId(userId);
        saveMeetingUser(instance, true);
        saveSchedule(instance, schIds);
        return userId;
    }

    //更新嘉宾审批状态
    public void update(ConfGuest instance, String schIds) {
        String userId = saveUser(instance, false);
        instance.setUserId(userId);
        saveMeetingUser(instance, false);
        saveSchedule(instance, schIds);
        // 更新conf_v_info表中的用户手机号码
        if (StringUtils.isNotEmpty(instance.getMobile())) {
        	confUserDAO.updateMobile(instance);
        }
    }

    //删除嘉宾
    public void delete(String[] userIds, String meetingId) {
        for (int i = 0; i < userIds.length; i++) {
            String userId = userIds[i];
            // 删除用户会议关联信息
            confMeetingUserDAO.deleteByUserId(userId, meetingId);

            // 删除用户动态表单信息
            confDynValueDAO.deleteValue(userId,meetingId);

            //删除用户的日程信息
            confScheduleUserDAO.deleteSchedule(userId,meetingId);

            // 删除用户信息
            confUserDAO.deleteById(userId);
        }
    }

    private String saveUser(ConfGuest instance, boolean createFlag) {
        ConfUser user = new ConfUser();
        if(instance != null) {
            user.setCname(instance.getCname());
            user.setEname(instance.getEname());
            user.setSex(instance.getSex());
            user.setBirth(instance.getBirth());
            user.setNation(instance.getNation());
            user.setCertType(instance.getCertType());
            user.setCertValue(instance.getCertValue());
            user.setCertExpiryDate(instance.getCertExpiryDate());
            user.setCertPic1(instance.getCertPic1());
            user.setCertPic2(instance.getCertPic2());
            user.setEmail(instance.getEmail().toLowerCase());
            user.setTele(instance.getTele());
            user.setMobile(instance.getMobile());
            user.setFax(instance.getFax());
            user.setAddress(instance.getAddress());
            user.setPostcode(instance.getPostcode());
            user.setPhoto(instance.getPhoto());
            user.setMedlHis(instance.getMedlHis());
            user.setReligion(instance.getReligion());
            user.setSelfIntro(instance.getSelfIntro());
            user.setIndustry(instance.getIndustry());
            user.setCompany(instance.getCompany());
            user.setPosition(instance.getPosition());
            user.setVisaNeed(instance.getVisaNeed());
            user.setVisaCity(instance.getVisaCity());
            user.setContactPerson(instance.getContactPerson());
            user.setVegetarian(instance.getVegetarian());
            user.setMailChecked(instance.getMailChecked());
            user.setApproveState(instance.getApproveState());
            user.setPassword(instance.getPassword());
            user.setDietTaboo(instance.getDietTaboo());
            user.setEtiquette(instance.getEtiquette());
            user.setOfficialLang(instance.getOfficialLang());
            user.setEntryType(instance.getEntryType());
            user.setEntryPlace(instance.getEntryPlace());
            user.setEntryDate(instance.getEntryDate());
            if (user.getEntryDate() == null) {
            	user.setEntryDate("");
            }
            user.setEntryValidity(instance.getEntryValidity());
            user.setEntryNum(instance.getEntryNum());
            user.setEntryEndmtDate(instance.getEntryEndmtDate());
            user.setEntryEndmtValidity(instance.getEntryEndmtValidity());
            user.setEntryPic1(instance.getEntryPic1());
            user.setEntryPic2(instance.getEntryPic2());
            user.setCompanyType(instance.getCompanyType());

            user.setSelfIntroEn(instance.getSelfIntroEn());
            user.setFoodAllergies(instance.getFoodAllergies());
            user.setUseRealname(instance.getUseRealname());
            user.setUseAlias(instance.getUseAlias());
            user.setUalias(instance.getUalias());
            user.setUseOtherPtitle(instance.getUseOtherPtitle());
            user.setPositionTitle(instance.getPositionTitle());
            user.setRemark(instance.getRemark());
            user.setWorkContent(instance.getWorkContent());
            user.setSuperviserName(instance.getSuperviserName());
            user.setSuperviserPhone(instance.getSuperviserPhone());
            user.setFirstName(instance.getFirstName());
            user.setLastName(instance.getLastName());
            user.setCardType(instance.getCardType());
            if(createFlag) {
            	//设置默认初始密码
            	String pass = Constant.DEFULT_PASSWORD;
            	user.setPassword(Md5.getMD5Str(pass));
            	// 设置注册日期
            	user.setRegisterDate(DateUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));
                confUserDAO.save(user);
            } else {
                user.setId(instance.getUserId());
                // 设置最后修改日期
                user.setUpdateDate(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                confUserDAO.update(user);
            }
        }
        return user.getId();
    }

    private void saveMeetingUser(ConfGuest instance, boolean createFlag) {
        ConfMeetingUser meetingUser = new ConfMeetingUser();
        if(instance != null){
            meetingUser.setUserId(instance.getUserId());
            meetingUser.setMeetingId(instance.getMeetingId());
            meetingUser.setUserType(instance.getUserType());
            meetingUser.setApproveState(instance.getApproveState());
            if (createFlag) {
                meetingUser.setRemindFlag("0");
                confMeetingUserDAO.save(meetingUser);
            } else {
                String keyId = confMeetingUserDAO.findMeetingUserKey(instance.getUserId(), instance.getMeetingId());
                meetingUser.setId(keyId);
                confMeetingUserDAO.update(meetingUser);
            }
        }
    }

    public List<String> saveSchedule (ConfGuest instance, String schIds) {
        //删除用户从前的日程信息
        List<ConfScheduleUser> oldShList = confScheduleUserDAO.findByUserId(instance.getUserId());
        for (int i = 0; i < oldShList.size(); i++) {
            confScheduleUserDAO.deleteByPK(instance.getUserId(), oldShList.get(i).getScheduleId());
        }
        List<String> returnSchIdList = new ArrayList<String>();
        if (!schIds.equals("[]")) {
            String[] schIdArr = schIds.split(",");
            String id = "";
            for (String str : schIdArr) {
                if(str.startsWith("[")) {
                    id = str.substring(8, str.length() - 2);
                } else if (str.endsWith("]")) {
                    id = str.substring(7, str.length() - 3);
                } else {
                    id = str.substring(7, str.length() - 2);
                }
                ConfSchedule sch = null;
                ConfScheduleUser schUser = new ConfScheduleUser();
                sch = confScheduleDAO.findById(id);
                if (sch != null) {
                    schUser.setScheduleId(sch.getId());
                    schUser.setUserId(instance.getUserId());
                    schUser.setMeetingId(instance.getMeetingId());
                    //插入新的日程信息
                    confScheduleUserDAO.save(schUser);
                    returnSchIdList.add(schUser.getId());
                }
            }
        }
        return returnSchIdList;
    }

    //更新嘉宾审批状态
    public void updateApprove(ConfGuest instance) {
        confUserDAO.updateApprove(instance);
        confMeetingUserDAO.updateApprove(instance);
    }

    //批量更新嘉宾审批状态
    public void approveCheckedUser(String[] ids, String meetingId) {
        for (int i = 0;i < ids.length; i++) {
            ConfGuest instance = new ConfGuest();
            instance.setUserId(ids[i]);
            instance.setApproveState(Constant.APPROVE_PASS);
            instance.setMeetingId(meetingId);
            confUserDAO.updateApprove(instance);
            confMeetingUserDAO.updateApprove(instance);
        }
    }

    //根据用户id和会议id选择用户会议关系，意在取得用户类型
    public List<ConfMeetingUser> findMeetingUser(ConfMeetingUser meetingUser){
    	return confMeetingUserDAO.findByExample(meetingUser);
    }


    /**
     * 保存saveMeetingUser，注册用
     * @param meetingUser
     */
    public void saveMeetingUser(ConfMeetingUser meetingUser){
        meetingUser.setRemindFlag("0");
    	confMeetingUserDAO.save(meetingUser);
    }

    //验证用户类型的修改是否允许
    public boolean userTypeUpdateCheck(ConfGuest instance, String meetingId) {
        List<ConfMeetingUser> meetingUserList = confMeetingUserDAO.findByUserId(instance.getUserId());
        for(int i = 0; i < meetingUserList.size(); i++) {
            ConfMeetingUser meeetingUser = meetingUserList.get(i);
            if (meeetingUser.getUserType().equals(instance.getUserType())) {
                return true;
            }
            if (meeetingUser.getUserType().equals(Constant.USER_TYPE_SPEAKER)
                    || meeetingUser.getUserType().equals(Constant.USER_TYPE_SPEAKER_EN)
                    || meeetingUser.getUserType().equals(Constant.USER_TYPE_ATTEND)
                    || meeetingUser.getUserType().equals(Constant.USER_TYPE_ATTEND_EN)) {
                if (instance.getUserType().equals(Constant.USER_TYPE_VIP)
                        || instance.getUserType().equals(Constant.USER_TYPE_VIP_US)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 发送提醒函
     * @param meetingId 会议ID
     * @throws Exception
     */
	public void sendRemindMail(String meetingId) throws Exception {
		// 获取发送邮件列表
		List<Object[]> list = confMeetingUserDAO.getEmailList(meetingId);
		MailSenderService mailSenderService = MailSenderService.getInstance();
		// 获取邮件模板
		List<ConfEmailTemplate> templateList = confEmailTemplateDAO.findByProperty("name", Constant.EMAIL_TYPE_NOTIFY);
		if (templateList!=null && !templateList.isEmpty()) {
			if (list!=null && !list.isEmpty()) {
				for (Object[] objects : list) {
					Email email = new Email();
					email.setTo(new String[]{objects[0].toString()});
					email.setSubject(templateList.get(0).getTitle());
					mailSenderService.setTemplateName(templateList.get(0).getTemplateName());
					Map<String, Object> model = new HashMap<String, Object>();
	    			model.put("user", objects[1].toString());
	    			Calendar ca = Calendar.getInstance();
	    			model.put("year", ca.get(Calendar.YEAR));
	    			model.put("month", ca.get(Calendar.MONTH)+1);
	    			model.put("day", ca.get(Calendar.DATE));
	    			mailSenderService.sendMail(email, model);
				}
			}
		}
	}
	
    //新增注册嘉宾信息
    public String saveTempUser(ConfGuest instance, String schIds){
        instance.setApproveState(Constant.APPROVED_UN);
        String userId = saveUser(instance, true);
        instance.setUserId(userId);
        saveMeetingUser(instance, true);
        saveSchedule(instance, schIds);
        return userId;
    }
    

	public List<Object[]> getRoomStatic(String meetingId,SearchParams searchParams) {
		List<Object[]> list = confMeetingUserDAO.findRoomStatic(meetingId,searchParams);
		if(list == null || list.size() < 1){
    		return null;
    	}
        list = getDicMatchedList(list);
		return list;
	}

	private List<Object[]> getDicMatchedList(List<Object[]> list) {
		// 获取用户类型
		List<ConfDict> userTypeDicList = confDictService.findDictByCategory("user_type","2");
		// 获取房间类型
		List<ConfDict> roomTypeDicList = confDictService.findDictByCategory("room_type","2");
		for (Object[] object : list) {
			object[4] = changeToDicValue(object[4], userTypeDicList);
			object[7] = changeToDicValue(object[7], roomTypeDicList);
		}
		return list;
	}

	private Object changeToDicValue(Object object, List<ConfDict> dicList) {
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

	public Pagination getRoomInfo(PageBean pageBean, String meetingId, SearchParams searchParams) {
		Pagination pagination = new Pagination();
		List<Map<String, Object>> list = confMeetingUserDAO.retrieve(pageBean, meetingId,searchParams);
		pagination.setRows(list);
		pagination.setTotal(confMeetingUserDAO.count(meetingId,searchParams)+"");
		return pagination;
	}
}
