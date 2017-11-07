package com.centling.conference.userroom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;
import com.centling.conference.userroom.entity.ConfUserRoom;
import com.centling.conference.userroom.service.ConfUserRoomService;

@Controller("confUserRoomController")
@RequestMapping("/roomassign/*")
public class ConfUserRoomController {

	@Resource
	private ConfUserRoomService confUserRoomService;
	@Resource
	private ConfMeetingUserDAO confMeetingUserDAO;
	
	/**
	 * 查询所有房型分配
	 * @return
	 */
	@RequestMapping(value="/r",method = RequestMethod.GET)
	public @ResponseBody List<ConfUserRoom> retrieve(HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserRoomService.findRoomRulesByMeetingId(meetingId);
	}
	
	/**
	 * 新增用户类型房型分配
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String add(ConfUserRoom userRoom, HttpSession session) throws Exception{
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		userRoom.setMeetingId(meetingId);
		confUserRoomService.save(userRoom);
		return "操作成功！";
	}
	
	/**
	 * 查找未维护房型的用户类型
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/usertype",method = RequestMethod.GET)
	public @ResponseBody List<ConfDict> retrieveUserType( HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserRoomService.findShowDictByMeetingId(meetingId);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return string
	 */
	@RequestMapping(value = "/deleteids", method = RequestMethod.GET)
    public @ResponseBody
    String deleteByIds(@RequestParam String ids) throws Exception{
		//int len = providerIds.split(",").length;
    	int rows = confUserRoomService.deleteByIds(ids);
        if(rows>0){
        	return "成功删除 "+rows+" 条";
        }
        return "删除失败";
    }
	
	/**
	 * 单条记录删除
	 * @param id
	 * @return string
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String deleteById(String id) throws Exception{
		 
		int row = confUserRoomService.deleteById(id);
        if(row>0){
        	return "成功删除此条记录！";
        }
        return "删除失败";
    }
	
	/**
	 * 给参会人员自动分配房型、主办方是否承担费用信息
	 */
	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public @ResponseBody void updateRoomInfo(HttpSession session) throws Exception {
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		confUserRoomService.updateRoomInfo(meetingId);
	}
	
	/**
	 * 给参会人员自动分配房型、主办方是否承担费用信息  sname; semail; suserType;
	 */
	@RequestMapping(value = "/finduserroom", method = RequestMethod.POST)
	public @ResponseBody Pagination findRoomInfo(PageBean pageBean, String sname, String semail, String suserType, HttpSession session) {
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserRoomService.findRoomInfoPages(pageBean,meetingId,sname,semail,suserType);
	}

	/**
	 * 根据邮箱和会议id查找用户信息
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/finduserinfo", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> findUserInfoByEmail(String email,HttpSession session){
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> lists = confUserRoomService.findUserInfoByEmail(meetingId, email);
		if(lists!=null&&!lists.isEmpty()){
			//找到该用户，但还需判断是否已经进行过房型手动调整
			map = lists.get(0);
			if(map.get("roomType") !=null && map.get("roomType").toString().length() > 0){
				map.put("status", "2");//该用户已经调整了房型
			}else{
				map.put("status", "1");//该用户可以根据新增来调整房型
			}
			
		}else{
			map.put("status", "0");
		}
		return map;
	}
	
	
	/**
	 * 保存meeting_user表中的房间信息
	 */
	@RequestMapping(value = "/updateuserroom", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String updateUserRoom(ConfMeetingUser user) throws Exception{
		confUserRoomService.updateUserRoom(user);
		return "操作成功！";
	}
	
	@RequestMapping(value="/deluserroom",method = RequestMethod.POST)
	public @ResponseBody String deluserroom(String id) throws Exception{
		confUserRoomService.delUserRoom(id);
		return "操作成功";
	}
	
	@RequestMapping(value="/roomstat",method=RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> roomStat(String startDate,String endDate,HttpSession session){
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		return confUserRoomService.findRoomStat(startDate,endDate,meetingId);
	}
	
	/**
	 * 自动分配入住时间和离开时间，内容从航班信息表中取得
	 */
	@RequestMapping(value="/checkdate",method=RequestMethod.POST)
	public @ResponseBody void updateCheckDate(HttpSession session){
		String meetingId = (String) session.getAttribute(Constant.SESSION_MEETING_ID);
		confUserRoomService.updateCheckDate(meetingId);
	}
}
