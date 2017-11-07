package com.centling.conference.vote.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.vote.entity.ConfVoteField;
import com.centling.conference.vote.service.ConfVoteFieldService;

@Controller("confVoteFieldController")
@RequestMapping("/confVote")
public class ConfVoteFieldController {
	
	@Resource
	private ConfVoteFieldService confVoteFieldService;
	
	@RequestMapping(value = "/findVoteField", method = RequestMethod.POST)
    public @ResponseBody Pagination findVoteField(PageBean pageBean,String scheduleId,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		return confVoteFieldService.findVoteField(pageBean, scheduleId, meetingid);
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(ConfVoteField confVoteField,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		confVoteField.setMeetingId(meetingid);
		return confVoteFieldService.save(confVoteField);
	}
	
	/**
	 * 删除投票时调用此方法
	 * @param voteIds 格式：'1','2','3','4','5','6'
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody Map<String,String> deleteByIds(String voteIds){
		return confVoteFieldService.deleteByIds(voteIds);
	}
	
	@RequestMapping(value = "/findVotesBySchduelId/{schduelId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public @ResponseBody String findVotesBySchduelId(@PathVariable String schduelId,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		String str = JSONObject.valueToString(confVoteFieldService.findVotesBySchduelId(schduelId, meetingid));
		return str;
	}
	
	@RequestMapping(value = "/mobile/findVotesBySchduelId/{schduelId}", method = RequestMethod.GET)
    public ModelAndView mobileFindVotesBySchduelId(@PathVariable String schduelId,HttpServletRequest request){
		String meetingid = request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID).toString();
		List<ConfVoteField> list = confVoteFieldService.findVotesBySchduelId(schduelId, meetingid);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("votesList", JSONObject.valueToString(list));
		return new ModelAndView("mobile/votes_topic", map);
	}
	
	//判断日程有没有投票信息
    @RequestMapping(value = "/findVotesCountBySchduelId/{schduelId}", method = RequestMethod.GET)
    public @ResponseBody String findVotesCountBySchduelId(@PathVariable String schduelId,HttpServletRequest request){
        String meetingid = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
        return confVoteFieldService.findVotesCountBySchduelId(schduelId, meetingid);
    }
	
	//会议墙投票结果显示
   @RequestMapping(value = "/showVotesResult/{scheduleId}", method = RequestMethod.GET)
    public ModelAndView showVotesResult(@PathVariable String scheduleId, HttpServletRequest request){
       Map<String, String> map = new HashMap();
       map.put("scheduleId", scheduleId);
        return new ModelAndView("admin/meetingwall/vote_result", map);
    }
   
   @RequestMapping(value = "upMeetingWallState", method = RequestMethod.POST)
   public @ResponseBody String updateMeetingWallState(HttpSession session, String id, String isShow) {
       String showFlag = "false";
       if (isShow.equals("false")) {
           showFlag = "true";
       }
       int updateCount = confVoteFieldService.updateMeetingWallState(id, showFlag);
       if (updateCount <= 0) {
           return "操作失败，请刷新重试。";
       }
       return "操作成功!";
   }
}
