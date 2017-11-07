package com.centling.conference.question.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.question.entity.ConfQuestion;
import com.centling.conference.question.service.QuestionService;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.entity.ConfUser;

@Controller("questionController")
@RequestMapping("/question/*")
public class QuestionController {
	
	@Resource
	QuestionService questionService;

	
	/**
	 * 根据会议id，日程id查询问题
	 * @param scheduleId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(String scheduleId, HttpSession session){
		
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return questionService.findQuestions(meetingId, scheduleId);
		
	}
	
	/**
	 * 保存提问
	 * @param question
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/saveq", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(ConfQuestion question, HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		String userId = ((ConfSysUser)session.getAttribute(Constant.BACK_SESSION_USER)).getId();
		question.setMeetingId(meetingId);
		question.setUserId(userId);
		question.setNickname("后台管理员");
		question.setState(Constant.APPROVED_UN); //默认未审核
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = format.format(new Date());
		question.setTime(timeNow);
		questionService.saveQuestion(question);
		return "保存成功！";
	}
	
	/**
	 * 删除信息
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value="/delq", method=RequestMethod.POST)
	public @ResponseBody String delQuestion(String questionId){
		questionService.delQuestion(questionId);
		return "删除成功！";
	}
	
	/**
	 * 审核问题
	 * @param questionId
	 * @param State
	 * @return
	 */
	@RequestMapping(value="/check", method=RequestMethod.POST)
	public @ResponseBody String checkQuestion(String questionId, String state){
		questionService.checkQuestion(questionId, state);
		return "操作成功！";
	}
	
    
    /**
     * @param session
     * @param type  类型：1现场提问2主讲嘉宾
     * @param request
     * @return
     */
    @RequestMapping(value = "/enter/{type}", method = RequestMethod.GET)
    public ModelAndView enter(HttpSession session,@PathVariable String type, HttpServletRequest request) {
        // 从Session中获取会议ID
        String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		String userId = ((ConfUser)session.getAttribute(Constant.SESSION_CONF_USER)).getId();
		
        List<Map<String, Object>> scheduleList = questionService.findByUserIdAndMeetingId(userId, meetingId);
        request.setAttribute("scheduleList", scheduleList);
        ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(scheduleList);
		
		String viewName = "";
		if("1".equals(type)){
			viewName = "mobile/question";
		}else{
			viewName = "mobile/speakers";
		}
		modelAndView.setViewName(viewName);
		return modelAndView;
    }
	
    
    /**
	 * 手机端保存提问
	 * @param question
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/savefrontq", method = RequestMethod.POST)
	public @ResponseBody String saveFrontQuestion(ConfQuestion question, HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		String userId = ((ConfUser)session.getAttribute(Constant.SESSION_CONF_USER)).getId();
//		String meetingId = "077c09d447ddc0600147ddc121a50000";
		question.setMeetingId(meetingId);
//		String userId="aa";
		question.setUserId(userId);
		question.setState(Constant.APPROVED_UN); //默认未审核
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeNow = format.format(new Date());
		question.setTime(timeNow);
		questionService.saveQuestion(question);
		return "1";
	}
	
	//后台会议墙菜单里点击上墙操作
   @RequestMapping(value = "/updateToMeetingWallQ", method = RequestMethod.POST)
    public @ResponseBody String canToMeetingWall(String questionId, HttpServletRequest request){
       questionService.updateToMeetingWallQ(questionId, "4");
        return "成功设置为上墙问题";
    }
   
   //后台会议墙菜单里点击下墙操作
   @RequestMapping(value = "/setNotMeetingWallQ", method = RequestMethod.POST)
   public @ResponseBody String setNotMeetingWallQ(String questionId, HttpServletRequest request){
       questionService.updateToMeetingWallQ(questionId, "2");
       return "操作成功，已取消上墙";
   }
   
   //会议墙菜单点击现场提问弹出问题页面
   @RequestMapping(value = "/showMeetingWallQ/{scheduleId}", method = RequestMethod.GET)
   public ModelAndView showMeetingWallQ(@PathVariable String scheduleId, HttpSession session){
       String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
       Map<String, Object> map = new HashMap<String, Object>();
       List<ConfQuestion> questions = questionService.findMeetingWallQ(meetingId, scheduleId);
       map.put("questions", questions);
       map.put("scheduleId", scheduleId);
       return new ModelAndView("admin/meetingwall/show_question", map);
   }
}
