package com.centling.conference.material.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.material.entity.ConfMaterialSchedule;
import com.centling.conference.material.service.ConfMaterialScheduleService;

/**
 * 会场物料管理和场地搭建
 * @author alisa
 *
 */
@Controller("confMaterialScheduleController")
@RequestMapping("/materialSchedule")
public class ConfMaterialScheduleController {

	@Resource
	private ConfMaterialScheduleService confMaterialScheduleService;
	
	/**
	 * 
	 * @param ConfMaterialSchedule 
	 * 		materialId 明确物料的id，只根据id即可查询出会场物料的详情
	 * 		scheduleId 会场id，根据会场日程id,查询本会场所用的所有的物料列表
	 * 		materialType 物料的类别，是普通物料还是场地搭建中的部分
	 * 		materialNameLike 物料名称，模糊匹配
	 * 		materialLeaderLike 物料负责人姓名，模糊匹配
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
    public @ResponseBody Pagination findMaterialSchedule(PageBean pageBean,ConfMaterialSchedule confMaterialSchedule,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		confMaterialSchedule.setMeetingId(meetingid);
		return confMaterialScheduleService.findMaterialSchedule(pageBean,confMaterialSchedule);
	}
	
	/**
	 * 新增或者修改的时候都调用此方法
	 * @param confMaterialSchedule
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(ConfMaterialSchedule confMaterialSchedule,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		confMaterialSchedule.setMeetingId(meetingid);
		String mapStr = JSONObject.valueToString(confMaterialScheduleService.save(confMaterialSchedule));
		return mapStr;
	}
	
	/**
	 * 删除物料时调用此方法
	 * @param materialIds 格式：'1','2','3','4','5','6'
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteByIds(String materialIds){
		return confMaterialScheduleService.deleteByIds(materialIds);
	}
	
	/**
	 * 快速将物料改为已到位状态
	 * @param materialIds 格式：'1','2','3','4','5','6'
	 * @return
	 */
	@RequestMapping(value = "/updateStateByIds", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> updateStateByIds(String materialIds){
		return confMaterialScheduleService.updateStateByIds(materialIds);
	}
}
