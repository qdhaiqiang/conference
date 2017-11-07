package com.centling.conference.serviceprovider.controller;

import javax.annotation.Resource;
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
import com.centling.conference.serviceprovider.entity.ConfServiceProvider;
import com.centling.conference.serviceprovider.service.ServiceProviderService;

@Controller("serviceProviderController")
@RequestMapping("/serviceprovider/*")
public class ServiceProviderController {
	
	@Resource
	private ServiceProviderService serviceProviderService;

	/**
	 * 查询所有外包商
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/r", method = RequestMethod.GET)
	public @ResponseBody Pagination retrieve (PageBean pageBean,HttpSession session,ConfServiceProvider provider) {
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		return serviceProviderService.retrive(pageBean,meetingId,provider);
	}
	
	/**
	 * 新增外包商
	 * @param provider
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String add(@ModelAttribute ConfServiceProvider provider, HttpSession session){
		String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
		provider.setMeetingId(meetingId);
		serviceProviderService.save(provider);
        return "添加成功";
    }
    
	
	/**
	 * 批量删除外包商信息
	 * @param providerIds
	 * @param session
	 * @return string
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody String delete(@RequestParam String providerIds, HttpSession session){
		//int len = providerIds.split(",").length;
    	int rows = serviceProviderService.delete(providerIds);
        if(rows>0){
        	return "成功删除 "+rows+" 条";
        }
        return "删除失败";
    }
    
    /**
     * 更新外包商
     * @param provider
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public @ResponseBody String update(@ModelAttribute ConfServiceProvider provider, HttpSession session){
    	String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
    	provider.setMeetingId(meetingId);
    	serviceProviderService.update(provider);
        return "更新成功";
    }
}
