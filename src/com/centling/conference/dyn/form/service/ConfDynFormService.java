package com.centling.conference.dyn.form.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dyn.field.DAO.ConfDynFieldDAO;
import com.centling.conference.dyn.field.entity.ConfDynField;
import com.centling.conference.dyn.form.DAO.ConfDynFormDAO;
import com.centling.conference.dyn.form.entity.ConfDynForm;
import com.centling.conference.dyn.value.DAO.ConfDynValueDAO;
import com.centling.conference.dyn.value.entity.ConfDynValue;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.DynFormParseUtil;

/**
 * 动态表单提交原始字符串Service
 * @author Dirk
 *
 */
@Service("confDynFormService")
public class ConfDynFormService {
	@Resource
	private ConfDynFormDAO confDynFormDAO;
	
	@Resource
	private ConfDynFieldDAO confDynFieldDAO;
	
	@Resource
	private ConfDynValueDAO confDynValueDAO;
	
	/**
	 * 根据用户类型和会议ID查询动态表单
	 * @param userType 用户类型
	 * @param meetingId 会议ID
	 * @return 
	 */
	public Map<String ,Object> getDynTemplateByUserType(String userType, String meetingId) {
		// 根据用户类型与会议ID查询动态模板
		ConfDynForm confDynForm = new ConfDynForm();
		confDynForm.setMeetingId(meetingId);
		confDynForm.setUserType(userType);
		List<ConfDynForm> dynFormList = confDynFormDAO.findByExample(confDynForm);
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询到动态表单
		if (dynFormList!=null&&!dynFormList.isEmpty()) {
			// 判断用户是否已经填写表单，如果已有用户填写表单，则不允许修改
			if (checkFormWrite(userType, meetingId)) {
				map.put("status", "2");
				map.put("info", "已有用户填写该表单，不允许修改表单");
			} else {
				map.put("status", "0");
				map.put("info", "动态表单已创建，可以修改");
			}
			map.put("data", dynFormList.get(0));
		// 未查询到动态表单
		} else {
			map.put("status", "1");
			map.put("info", "该用户类型未创建动态表单");
		}
		return map;
	}

	/**
	 * 判断表单是否已有用户填写
	 * @param userType 用户类型
	 * @param meetingId 会议ID
	 * @return true：用户已填写    false：用户未填写
	 */
	private boolean checkFormWrite(String userType, String meetingId) {
		ConfDynValue confDynValue = new ConfDynValue();
		confDynValue.setMeetingId(meetingId);
		confDynValue.setUserType(userType);
		List<ConfDynValue> confDynValueList = confDynValueDAO.findByExample(confDynValue);
		if (confDynValueList!=null && !confDynValueList.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 保存动态表单
	 * @param payload 待保存的动态表单json串
	 * @param userType 用户类型
	 * @param meetingId 会议ID
	 * @return true: 保存成功  false:保存失败
	 */
	public boolean saveDynForm(String payload, String userType, String meetingId,String sort) {
		// 1、判断用户是否已填写表单，如果表单已填写，则不允许修改
		if (checkFormWrite(userType, meetingId)) {
			return false;
		} 
		// 2、根据用户类型、会议ID判断conf_dyn_form是否已经存在记录，存在则删除记录
		ConfDynForm confDynForm = new ConfDynForm();
		confDynForm.setMeetingId(meetingId);
		confDynForm.setUserType(userType);
		List<ConfDynForm> dynFormList = confDynFormDAO.findByExample(confDynForm);
		// 查询到conf_dyn_form数据，删除
		if (dynFormList!=null && !dynFormList.isEmpty()) {
			for (ConfDynForm delDynForm : dynFormList) {
				confDynFormDAO.delete(delDynForm);
			}
		}

		// 4、根据用户类型、会议ID判断conf_dyn_field是否已经存在记录，存在则删除记录
		ConfDynField confDynField = new ConfDynField();
		confDynField.setUserType(userType);
		confDynField.setMeetingId(meetingId);
		List<ConfDynField> confDynFieldList = confDynFieldDAO.findByExample(confDynField);
		// 查询到conf_dyn_field数据，批量删除
		if (confDynFieldList!=null && !confDynFieldList.isEmpty()) {
			for (ConfDynField delConfDynField : confDynFieldList) {
				confDynFieldDAO.delete(delConfDynField);
			}
		}
		//5、保存数据到conf_dyn_field表中
		// 解析payload字符串
		List<ConfDynField> dynFieldList = DynFormParseUtil.parse(payload);
		
		//6、处理编号
		String [] nums = sort.split("\\|");
		Map<String, String> numberMap = new HashMap<String, String>();
		for (int i = 0; i < nums.length; i++) {
			numberMap.put(nums[i].split("-")[1], nums[i].split("-")[0]);
		}
		
		// 3、保存数据到conf_dyn_form表中,  payload里的字符串，要经过排序
		confDynForm.setPayload( DynFormParseUtil.parsePayload(payload,numberMap));
		confDynFormDAO.save(confDynForm);
		
		// 保存数据
		if (dynFieldList!=null && !dynFieldList.isEmpty()) {
			for (ConfDynField addConfDynField : dynFieldList) {
				addConfDynField.setMeetingId(meetingId);
				addConfDynField.setUserType(userType);
				addConfDynField.setOrderNum(Integer.parseInt(numberMap.get(addConfDynField.getCid())));
				confDynFieldDAO.save(addConfDynField);
			}
		}
		return true;
	}
	
	/**
	 * （前台）获取用户Form表单
	 * @param userType 用户类型
	 * @param meetingId 会议编号
	 * @param userId 用户编号
	 * @return
	 */
	public Map<String, Object> getFormFront(String userType, String meetingId,String userId,HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 1、 判断用户之前是否填写过表单
		ConfDynValue confDynValue = new ConfDynValue();
		List<ConfDynValue> dynValueList = null;
		// 判断用户是否有Id
		if (userId != null) {
			confDynValue.setUserId(userId);
			confDynValue.setUserType(userType);
			confDynValue.setMeetingId(meetingId);
			dynValueList = confDynValueDAO.findByExample(confDynValue);
		}
		
		// 2、如果填写过，则直接查询conf_dyn_value表
		if (dynValueList!=null && !dynValueList.isEmpty()) {
			map.put("status", "0");
			map.put("info", "查询到用户数据");
			map.put("data", dynValueList);
		// 3、如果未填写，则查询conf_dyn_field表组装成ConfDynValue实体
		} else {
			ConfDynField confDynField = new ConfDynField();
			confDynField.setMeetingId(meetingId);
			confDynField.setUserType(userType);
			List<ConfDynField> dynFieldList = confDynFieldDAO.findByExample(confDynField);
			// 用户已经创建模板
			if (dynFieldList!=null && !dynFieldList.isEmpty()) {
				map.put("status", "1");
				map.put("info", "查询到表单模板");
				List<ConfDynValue> valueList = new ArrayList<ConfDynValue>();
				for (ConfDynField field : dynFieldList) {
					ConfDynValue value = new ConfDynValue();
					value.setFieldId(field.getId());
					value.setMeetingId(meetingId);
					value.setUserId(userId);
					value.setUserType(userType);
					value.setName(field.getName());
					value.setType(field.getType());
					value.setRequired(field.getRequired());
					value.setOptions(field.getOptions());
					value.setValue("");
					value.setDescription(field.getDescription());
					value.setOrderNum(field.getOrderNum());
					valueList.add(value);
				}
				map.put("data", valueList);
			} else {
				map.put("status", "2");
				map.put("info", "管理员未创建表单模板");
			}
		}
		session.setAttribute(Constant.SESSION_front_MeetingForm,map.get("data"));
		return map;
	}
	
	/**
	 * （前台）保存用户form表单
	 * @param payload 待保存的json字符串
	 * @return 
	 */
	public Map<String, String> saveFormFront(String payload,HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		List<ConfDynValue> confDynValueList = DynFormParseUtil.parseValue(payload);
		// 从Session中获取用户ID
		ConfUser confUser = (ConfUser)session.getAttribute(Constant.SESSION_CONF_USER);
		if (confDynValueList!=null && !confDynValueList.isEmpty()) {
			for (ConfDynValue confDynValue : confDynValueList) {
				if (confUser!=null) {
					confDynValue.setUserId(confUser.getId());
				}
				confDynValueDAO.merge(confDynValue);
			}
			map.put("status", "0");
			map.put("info", "数据保存成功");
		} else {
			map.put("status", "1");
			map.put("info", "数据保存失败");
		}
		session.setAttribute(Constant.SESSION_front_MeetingForm, confDynValueList);
		return map;
	}

	public Pagination retrieve(PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfDynForm> list = confDynFormDAO.retrieve(pageBean, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confDynFormDAO.count(meetingId)+"");
		return pagination;
	}
	
	/**
     * （后台）获取用户Form表单
     * @param meetingId 会议编号
     * @param userId 用户编号
     * @return
     */
    public Map<String, Object> getFormAdmin(String meetingId,String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 1、 判断用户之前是否填写过表单
        ConfDynValue confDynValue = new ConfDynValue();
        confDynValue.setUserId(userId);
        confDynValue.setMeetingId(meetingId);
        List<ConfDynValue> dynValueList = confDynValueDAO.findByExample(confDynValue);
        // 2、如果填写过，则直接查询conf_dyn_value表
        if (dynValueList != null && !dynValueList.isEmpty()) {
            map.put("status", "0");
            map.put("info", "查询到用户数据");
            map.put("data", dynValueList);
        // 3、如果未填写，则返回未填写信息message
        } else {
            map.put("data", "该用户没有创建动态表单信息");
        }
        return map;
    }
}