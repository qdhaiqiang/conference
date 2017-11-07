package com.centling.conference.dict.controller;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.dict.service.ConfDictService;

/**
 * 业务字典Controller
 * @author Dirk
 *
 */
@Controller("dictController")
@RequestMapping("/dict/*")
public class ConfDictController {
	/**
	 * 根据字典类型编号获取字典串
	 * @param categoryCode 字典类型编号
	 * @return 查询到的字典集合
	 */
	@RequestMapping(value="r/{categoryCode}", method=RequestMethod.GET)
	public @ResponseBody List<ConfDict> findDictByCategory(@PathVariable String categoryCode) {
		return confDictService.findDictByCategory(categoryCode,"2");
	}
	
	 /**
     * 根据字典类型编号获取字典串
     * @param categoryCode 字典类型编号
     * @return 查询到的字典集合
     */
    @RequestMapping(value="findGuest", method=RequestMethod.GET)
    public @ResponseBody List<ConfDict> findGuestDictByCategory() {
        return confDictService.findGuestOrStaff("guest");
    }
    
    
    /**
     * 根据字典类型编号获取字典串
     * @param categoryCode 字典类型编号
     * @return 查询到的字典集合
     */
    @RequestMapping(value="findStaff", method=RequestMethod.GET)
    public @ResponseBody List<ConfDict> findStaffDictByCategory() {
        return confDictService.findGuestOrStaff("staff");
    }
	
	/**
	 * 根据字典编号、用户类型、语言查询字典集合
	 * @param request
	 * @param categoryCode 字典类型编号
	 * @param type 用户类型
	 * @return
	 */
	@RequestMapping(value="r/getDict", method=RequestMethod.GET)
	public @ResponseBody
	List<ConfDict> findDictByLocaleAndCategory(HttpServletRequest request, 
			@RequestParam String categoryCode, @RequestParam String type) {
		// 获取Locale
		Locale locale = RequestContextUtils.getLocale(request);
		return confDictService.findDictByLocaleAndCategory(categoryCode,locale.toString(), type);
	}
	
	/**
	 * 业务字典Service
	 */
	@Resource
	private ConfDictService confDictService;
}