package com.centling.conference.exhibitcompany.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.centling.conference.base.Constant;
import com.centling.conference.exhibit.entity.ConfExhibit;
import com.centling.conference.exhibit.service.ConfExhibitService;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibitbooth.service.ConfBoothService;
import com.centling.conference.exhibitcompany.entity.ConfExhibitCompany;
import com.centling.conference.exhibitcompany.service.ConfExhibitCompanyService;
import com.centling.conference.exhibitcompany.service.ExhibitStatisticResultService;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;
import com.centling.conference.exhibitfurniture.service.ConfExhibitExpressService;
import com.centling.conference.exhibitfurniture.service.ConfExhibitExpressneedService;
import com.centling.conference.exhibitfurniture.service.ConfExhibitFurnitureService;
import com.centling.conference.exhibitfurniture.service.SaveExhibitApplicationService;
import com.centling.conference.exhibition.entity.ConfExhibition;
import com.centling.conference.exhibition.entity.ExhibitionForm;
import com.centling.conference.exhibition.service.ConfExhibitionService;
import com.centling.conference.exhibitrent.entity.ConfExhibitRent;
import com.centling.conference.exhibitrent.entity.ExhibitFurnitureRent;
import com.centling.conference.exhibitrent.service.ConfRentService;
import com.centling.conference.user.entity.ConfUser;
import com.centling.conference.util.MyException;

@Controller("confExhibitCompanyController")
@RequestMapping("front/exhibitCompany")
public class ConfExhibitCompanyController {
	
	@Resource
	private ConfExhibitCompanyService confExhibitCompanyService;
	
	@Resource
	private ConfExhibitionService confExhibitionService;
	@Resource 
	private ConfBoothService confBoothService;
	@Resource
	private ConfExhibitService confExhibitService;
	@Resource
	private ConfRentService confRentService;
	@Resource
	private ConfExhibitFurnitureService confExhibitFurnitureService;
	@Resource
	private ConfExhibitExpressService confExhibitExpressService;
	@Resource
	private ConfExhibitExpressneedService confExhibitExpressneedService;
	@Resource
	private SaveExhibitApplicationService saveExhibitApplicationService;
	@Resource
	private ExhibitStatisticResultService exhibitStatisticResultService;
	

	/**
	 * 展商登录，申请展位时登录
	  	1.登录时是以参展商公司表为准，先查找这个表中是否有这个用户email,如果有，验证密码是否正确；
		2.如果公司表中，没有此email,去用户表中查找，如果没有该email，不能登录；
		3.user表中，如果有此email,验证密码是否正确；并且验证是否是本次参会展商；
		4.如果正确，找到用户填写的公司company，在在公司表中根据此company查找，该公司是否已经有登录用户；
		5.如果没有登录用户，登录成功，提示：是否将此账号作为该参展商的登录帐号。
	 * @param instance
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exhibitionlogin", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> exhibitionLogin(ConfUser instance, HttpServletRequest request) {
//		String meetingId = request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID).toString();
		return confExhibitCompanyService.checkLogin(instance.getEmail(), instance.getPassword(),"077c09d447ddc0600147ddc121a50000");
		//HttpSession session = request.getSession();
       // session.setAttribute(Constant.SESSION_CONF_USER, user);
	}
	
	/**
	 * 用户登录，此时分为两种情况：1.公司帐号登录；2.个人帐号登录同意设为公司帐号
	 * 		1.查询此账号是否是公司帐号，如果是，直接登录，并存session
	 * 		2.如果是个人账户，执行参展商初始化公司帐号的操作
	 * @param email
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exhibitionloginsuccess", method = RequestMethod.POST)
	public String exhibitionLoginSuccess(String email,String password,HttpServletRequest request) {
		HttpSession session = request.getSession();
		String meetingId= (String)session.getAttribute(Constant.FRONT_SESSION_MEETING_ID);
		ConfExhibitCompany company = confExhibitCompanyService.exhibitionLogin(email, password);
		//加载属于本次会议的展厅（展位）
		List<ConfExhibition> exhibitions = confExhibitionService.findByMeetingId(meetingId);
				//加载可以选择的展位号码
		List<ConfBooth> booths = confBoothService.findAvailableBooths(meetingId,company.getCompanyId());
		List<ConfExhibit> exhibits = new ArrayList<ConfExhibit>(Constant.EXHIBIT_NUM);
		exhibits.addAll(confExhibitService.findExhibitor(meetingId,company.getCompanyId()));
		//用户最多输入EXHIBIT_NUM个展品，为前台预留EXHIBIT_NUM展品对象
		if (exhibits.size() < Constant.EXHIBIT_NUM) { 
			int count = Constant.EXHIBIT_NUM - exhibits.size();
			for (int i = 0; i < count; i++) {
				exhibits.add(new ConfExhibit());
			}
		}
		
		List<ConfExhibitRent> rents = confRentService.findAllRent(meetingId,company.getCompanyId());//该展商已申请的额外家具
		//加载该会议所有的额外家具
		List<ConfExhibitFurniture> furnitures = confExhibitFurnitureService.findFurniture(meetingId);
		List<ExhibitFurnitureRent> FurnitureRents = confRentService.mergeFurnitureRent(furnitures, rents);
		List<ConfExhibitExpress> expresses = confExhibitExpressService.findByMeetingId(meetingId);
		//是否有物流需求
		ConfExhibitExpressneeds expressneed = confExhibitExpressneedService.findExpressneeds(meetingId,company.getCompanyId());

		//Gson gson = new Gson();gson.toJson();
		String exhibitionStr = JSONObject.valueToString(exhibitions);
		request.setAttribute("exhibitionStr", exhibitionStr);
		request.setAttribute("exhibitions", exhibitions);
		request.setAttribute("company", company);
		request.setAttribute("booths", booths);
		request.setAttribute("exhibits", exhibits);
		request.setAttribute("furnitures", FurnitureRents);
		request.setAttribute("expresses", expresses);
		request.setAttribute("expressneed", expressneed);
		session.setAttribute(Constant.SESSION_FRONT_CONF_COMPANY, company);
		return "front/exhibition/exhibition";
	}
	
	/**
	 * 保存展商信息
	 * @return
	 */
	@RequestMapping(value = "/exhibitionsave", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> exhibitionSave(@ModelAttribute ConfExhibitCompany company, @ModelAttribute ExhibitionForm exhibitionForm, HttpServletRequest request) { 
		String meetingId = request.getSession().getAttribute(Constant.FRONT_SESSION_MEETING_ID).toString();
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = saveExhibitApplicationService.saveExhibitionForm(exhibitionForm, company, meetingId);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			map.put("status", "2");
			map.put("info", e.getMessage());
		}
		return map;
	}
	
	/**
	 * 在展商申请确定申请之前，预览功能。
	 * @param user
	 * @param exhibitionForm
	 * @return
	 */
	@RequestMapping(value = "/exhibitionPreview", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> exhibitionPreview(@ModelAttribute ConfExhibitCompany company, @ModelAttribute ExhibitionForm exhibitionForm) { 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("company",company);
		map.put("exhibitionForm",exhibitionForm);
		return map;
	}
	
	@RequestMapping(value = "/getContactList", method = RequestMethod.GET)
    public @ResponseBody
    List<ConfExhibition> getContactList(){
		List<ConfExhibition> companyList = confExhibitCompanyService.getCompanyList();
    	return companyList;
    }
	
	/**
	 * 后台统计，获取所有展位类别和展位
	 * 	1、总共有多少展位，已预定多少展位；
		2、额外的家具分类汇总已经预定多少
		3、有多少展商有物流需求
	 */
	@RequestMapping(value = "/statisticResult", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getStatisticResult(HttpServletRequest request){
		String meetingId = request.getSession().getAttribute(Constant.SESSION_MEETING_ID).toString();
		//加载属于本次会议的展厅（展位）
		return exhibitStatisticResultService.statisticResult(meetingId);
	}
}
