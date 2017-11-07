package com.centling.conference.travel.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.travel.entity.ConfUserTravel;
import com.centling.conference.travel.entity.SearchParams;
import com.centling.conference.travel.service.UserTravelService;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.exportexcels.ExportExcelsUtil;

@Controller("userTravelController")
@RequestMapping("/travel/*")
public class UserTravelController {
	private static final Logger log = LoggerFactory.getLogger(UserTravelController.class);
	@Resource
	private UserTravelService userTravelService;

	/**
	 * 根据输入的email，查询参会人员的基本信息以及动态表单中信息。
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> retrieve (String email,String cname, HttpSession session, HttpServletRequest request) {
		
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> maps = userTravelService.findUserInfo(email,cname,meetingId);
		
		if(maps.size()>0){
			
			map = maps.get(0);
			//查询该参会人员是否已经录入过航程信息了。
			ConfUserTravel travel = new ConfUserTravel();
			travel.setMeetingId(meetingId);
			travel.setUserId((String) map.get(Constant.RESULT_KEY_USERID));
			List<ConfUserTravel> travels = userTravelService.findTravelInfo(travel);
			if(travels.size()>0){
				map.put("travel", travels.get(0));
			}
			map.put("status", "1");

		}else{
			map.put("status", "0");
		}
		
		return map;
		
	}
	
	/**
	 * 保存行程信息
	 * @param travel
	 * @param noticeType 通知类型。1：邮件通知；2：短信通知
	 * @param session
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody void save (ConfUserTravel travel, ConfUser user, String userType,String noticeEmail, String noticeMsg, HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		travel.setMeetingId(meetingId);
		userTravelService.save(travel,user,userType,noticeEmail,noticeMsg);
	}
	
	/**
	 * 票务信息导出
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/exportTravelStatic",method=RequestMethod.POST)
	public @ResponseBody String exportTravelStatic(@ModelAttribute SearchParams searchParams,HttpSession session,HttpServletResponse response) throws IOException {
		String[] headers = new String[]{"姓名","用户类型","启程出行方式","启程航班号","启程出发地","启程目的地","启程出发时间","启程到达时间","返程出行方式","返程航班号","返程出发地","返程目的地","返程出发时间","返程到达时间","是否需要送关"};
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID); 
		List<Object[]> dataset = userTravelService.getTravelStatic(meetingId,searchParams);
        if(dataset == null || dataset.size() < 1){
        	return "没有查找到相应数据";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=exportTravel.xls");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			ExportExcelsUtil.exportExcel(headers,dataset, sos);
			response.flushBuffer();
		} catch (IOException e) {
			log.error("导出票务数据出现异常"+e.getMessage());
			throw e;
		}
        return "成功导出"+dataset.size()+"条票务数据。";
	}
	
	/**
	 * 票务信息查询
	 * @return
	 */
	@RequestMapping(value="/getTravelStatic",method=RequestMethod.POST)
	public @ResponseBody Pagination getTravelStatic(PageBean pageBean,@ModelAttribute SearchParams searchParams,HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID); 
		return userTravelService.getTravelInfo(pageBean,meetingId,searchParams);
	}
	
	/**
	 * 获取启程目的地
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getDistinctEndPlaceCome", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getEndPlaceCome(HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return userTravelService.getEndPlaceCome(meetingId);
	}
	
	/**
	 * 获取返程出发地
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getDistinctStartPlaceGo", method=RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getStartPlaceGo(HttpSession session) {
		// 从Session中获取会议ID
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return userTravelService.getStartPlaceGo(meetingId);
	}
	
	/**
	 * 发送邮件或短信通知
	 * @param travelId
	 * @param noticeEmail
	 * @param noticeMsg
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sendMessage", method=RequestMethod.POST)
	public @ResponseBody String sendMessage(String travelId, String travelUserType,String noticeEmail, String noticeMsg) throws Exception {
		userTravelService.messageSend(travelId,travelUserType,noticeEmail,noticeMsg);
		return "发送成功";
	}
}
