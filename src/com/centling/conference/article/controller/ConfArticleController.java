package com.centling.conference.article.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.centling.conference.article.entity.ConfArticle;
import com.centling.conference.article.service.ConfArticleService;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.essay.entity.ConfEssay;
import com.centling.conference.essay.service.ConfEssayService;

@Controller("confArticleController")
@RequestMapping("/article/*")
public class ConfArticleController {

	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean) {
		return confArticleService.retrieve(pageBean);
	}
	
	@RequestMapping(value = "/c", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String save(@ModelAttribute ConfArticle confArticle, HttpServletRequest request) throws Exception {
		confArticleService.save(confArticle);
		return "添加成功";
	}
	
	@RequestMapping(value = "/u", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String update(@ModelAttribute ConfArticle confArticle, HttpServletRequest request) throws Exception {
		ConfArticle cet = confArticleService.findById(confArticle.getId());
		if (null != cet) {
			confArticleService.update(confArticle);
		}

		return "修改成功 ";
	}

	@RequestMapping(value = "/d", method = RequestMethod.GET)
	public @ResponseBody String delete(String id, HttpServletRequest request) throws Exception {
		confArticleService.delete(id);
		return "删除成功";
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView getArticleById(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		ConfArticle confArticle = confArticleService.findById(id);
		modelAndView.addObject(confArticle);
		modelAndView.setViewName("front/showArticle");
		return modelAndView;
	}
	@RequestMapping(value="/mobile/{id}", method=RequestMethod.GET)
	public ModelAndView getMobileArticleById(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		ConfEssay confEssay = confEssayService.findById(id);
		modelAndView.addObject(confEssay);
		modelAndView.setViewName("mobile/newsDetail");
		return modelAndView;
	}
	
	@Resource
	private ConfArticleService confArticleService;
	
	@Resource
	private ConfEssayService confEssayService;
}
