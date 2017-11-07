package com.centling.conference.essay.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essay.entity.ConfEssay;
import com.centling.conference.essay.service.ConfEssayService;

@Controller("confEssayController")
@RequestMapping("/essay/*")
public class ConfEssayController {

	@RequestMapping(value = "/r", method = RequestMethod.POST)
	public @ResponseBody
	Pagination retrieve(PageBean pageBean, HttpSession session,@ModelAttribute ConfEssay confEssay) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return confEssayService.retrieve(pageBean,meetingId,confEssay);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(@ModelAttribute ConfEssay confEssay, HttpServletRequest request) throws Exception {
		String meetingId = (String)request.getSession().getAttribute(Constant.SESSION_MEETING_ID);
		confEssay.setMeetingId(meetingId);
		confEssayService.save(confEssay);
		return "添加成功";
	}
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfEssay confEssay, HttpServletRequest request) throws Exception {
		ConfEssay cet = confEssayService.findById(confEssay.getId());
		if (null != cet) {
			confEssayService.update(confEssay);
		}

		return "修改成功 ";
	}

	@RequestMapping(value = "/d", method = RequestMethod.GET)
	public @ResponseBody
	String delete(String id, HttpServletRequest request) throws Exception {
		confEssayService.delete(id);
		return "删除成功";
	}
	
	/**
	 * 获取文章ID
	 * @param essayId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/getEssayBySecondTypeId", method=RequestMethod.POST)
	public @ResponseBody List<ConfEssay> getEssayBySecondTypeId(@RequestParam String secondEssayTypeId,@RequestParam Integer infoType) {
		List<ConfEssay> confEssayList = confEssayService.findListBySecondTypeId(secondEssayTypeId,infoType);
		return confEssayList;
	}
	
	@Resource
	private ConfEssayService confEssayService;
}
