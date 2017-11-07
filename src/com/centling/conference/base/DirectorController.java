package com.centling.conference.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DirectorController {
	@RequestMapping("/r/{path1}")
	public String redirct(@PathVariable String path1) {
		return path1;
	}

	@RequestMapping("/r/{path1}/{path2}")
	public String redirct(@PathVariable String path1, @PathVariable String path2) {
		return path1 + "/" + path2;
	}

	@RequestMapping("/r/{path1}/{path2}/{path3}")
	public String redirct(@PathVariable String path1, @PathVariable String path2, @PathVariable String path3) {
		return path1 + "/" + path2 + "/" + path3;
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		 // 获取域名  
//		return "front/login";
		return "admin/login";
	}
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/formTest")
	public String formTest() {
		return "formTest";
	}
	
	//测试用
	@RequestMapping(value="/wizard", method=RequestMethod.GET)
	public String wizard(){
		return "front/test/first";
//		return "front/basic_info2";
	}
	
	//用户点击注册后进入注册页
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(){
		return "front/index";
	}
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String schedule(){
		return "front/schedule";
	}
	
	@RequestMapping(value="/newurl", method=RequestMethod.GET)
	public ModelAndView newurl(String url){
		Map<String,String> map = new HashMap<String,String>();
		map.put("newurl", url);
		return new ModelAndView("admin/open",map);
	}
}
