package com.centling.conference.vericode.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.vericode.service.ConfEmailVercodeService;

@Controller("confEmailVericodeController")
@RequestMapping("/emailVericode")
public class ConfEmailVericodeController {
	@Resource
	private ConfEmailVercodeService confEmailVercodeService;
	
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve(PageBean pageBean, @RequestParam String email) {
		return confEmailVercodeService.retrieve(pageBean,email);
	}
}
