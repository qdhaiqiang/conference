package com.centling.conference.materialreg.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.base.Constant;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.materialreg.DAO.ConfMaterialRegistrationDAO;
import com.centling.conference.materialreg.entity.ConfMaterialRegistration;
import com.centling.conference.materialreg.service.MaterialRegService;

@Controller("materialRegController")
@RequestMapping("/materialReg/*")
public class MaterialRegController {
	@Resource
	MaterialRegService materialRegService;
	
	private static final Logger log = LoggerFactory
			.getLogger(ConfMaterialRegistrationDAO.class);
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve (PageBean pageBean) {
		log.debug("1");
		return materialRegService.retrive(pageBean);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody List<ConfMaterialRegistration> findAll() {
		return materialRegService.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfMaterialRegistration materialReg,HttpSession session) throws Exception {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		materialReg.setMeetingId(meetingId);
		materialRegService.save(materialReg);
        return "添加成功";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody String delete(@RequestParam String equipIds, HttpSession session) throws Exception {
    	return materialRegService.delete(equipIds);
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfMaterialRegistration materialReg, HttpSession httpSession) throws Exception {
    	String meetingId = (String)httpSession.getAttribute(Constant.SESSION_MEETING_ID);
    	materialReg.setMeetingId(meetingId);
    	materialRegService.update(materialReg);
        return "更新成功";
    }
}
