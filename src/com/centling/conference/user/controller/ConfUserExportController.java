package com.centling.conference.user.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.centling.conference.user.entity.ConfUserExptSrchCondModel;
import com.centling.conference.user.service.ConfUserService;
import com.centling.conference.util.exportexcels.ExportExcelsUtil;

@Controller("confUserExportController")
@RequestMapping("/userExport/*")
public class ConfUserExportController {

    @Resource
    ConfUserService confUserService;
    String[] headers = new String[]{"用户类型","姓名(全名)","别名","性别","出生日期",
            "国籍","宗教信仰","饮食禁忌","个人病史","过敏食物","地址","邮编","固话","手机号码","邮箱",
            "传真","指定联系人","配偶信息","随行人员","证件类型","证件号码","入澳证件类型","入澳证件签发地点","入澳证件签发日期","入澳证件有效期至","入澳证件号码","入澳证件有效签注签发日期",
            "入澳证件有效签注有效期至","公司名称","公司性质","行业","单位会期工作内容","主管负责人姓名","主管负责人联系方式","职位",
            "个人说明(150个字以内)","英文自我介绍","是否公布真实姓名和职务","是否使用化名","化名","是否使用其他职务名称","职务名称","备注信息",
            "是否需要主办方提供官方邀请函","是否需要会前协助办理入境签证","是否需要组委会传真官方邀请函","是否需要中国大陆地区签证办理咨询服务",
            "出发地区","赴澳/返程交通方式","启程出发地","启程出发日期","启程出发时间段","返程地","返程日期","返程出发时间段","是否需要送关",
            "酒店服务（常规）","酒店服务（延时一日）","住宿特殊需求","文化考察路线","演讲题目","是否需要PPT演示","是否携带配偶","配偶电子邮箱","是否随行人员陪同出席","随行人员数量","随行电子邮箱",
            "希望组委会了解的重要信息（备注）","填写对某位或某几位嘉宾采访申请",
            "是否和特邀嘉宾同一联系人","是否陪同嘉宾参加会期活动","计划参加活动","是否同特邀嘉宾同一线路往返澳门","是否需要组委会协助预定往返票务","其他需求",
            "展品类型","展位简介","主导产品介绍","预申请展位数量","展位布置特殊需求","广告申请类别","友情赞助类别","独家视频播放媒体",
            "从哪种渠道得知会议消息","希望参会后有哪些收获","本次参会的目的","希望本次会议现场气氛的感觉？","最关注中国到底中医药产品展览会的哪方面",
            "本次参会网络注册方式的意见","本届会议有何建议",
            "来时交通类型","来时飞机/高铁班次","来时出发地","来时目的地","来时出发时间","来时到达时间","返回时交通类型","返回时飞机或高铁班次","返回时出发地",
            "返回时目的地","返回时出发时间","返回时到达时间","酒店","房间类型","房间号","入住日期","离开日期"};

    @RequestMapping(value = "findByCond", method = RequestMethod.POST)
    public @ResponseBody
    Pagination findUserBySearchCond(PageBean pageBean, @ModelAttribute ConfUserExptSrchCondModel sCond,  HttpSession session) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        
        return confUserService.findUserBySearchCond(pageBean, meetingId, sCond);
    }
    
    @RequestMapping(value = "exportUserFile", method = RequestMethod.POST)
    public @ResponseBody String exportUserFile(@ModelAttribute ConfUserExptSrchCondModel sCond,  HttpSession session, HttpServletResponse response) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        List<Object[]> dataset = confUserService.findAllUser(meetingId, sCond); 
        if(dataset == null || dataset.size() < 1){
        	return "没有查找到相应用户";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
		response.setHeader("Content-Disposition", "attachment;filename=exportUser.xls");
		response.setHeader("Pragma","No-cache");
		response.setHeader ( "Cache-Control", "no-store"); 
		try {
			OutputStream sos = response.getOutputStream();
			ExportExcelsUtil.exportExcel(2,1,2,"用户信息",headers,dataset, sos);//如果不需要额外数据exportExcel(headers,dataset, sos)
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "成功导出"+dataset.size()+"条用户数据。";
    }
    
    @RequestMapping(value = "exportSelectedUser", method = RequestMethod.POST)
    public @ResponseBody String exportSelectedUser(@RequestParam String userIds,  HttpSession session, HttpServletResponse response) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        List<Object[]> dataset = confUserService.findUserByUserIds(meetingId, userIds); 
        if(dataset == null || dataset.size() < 1){
            return "没有查找到相应的数据,请刷新数据后重试";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
        response.setHeader("Content-Disposition", "attachment;filename=exportUser.xls");
        response.setHeader("Pragma","No-cache");
        response.setHeader ( "Cache-Control", "no-store"); 
        try {
            OutputStream sos = response.getOutputStream();

            ExportExcelsUtil.exportExcel(2,1,2,"用户信息",headers,dataset, sos);//如果不需要额外数据exportExcel(headers,dataset, sos)
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "成功导出"+dataset.size()+"条用户数据。";
    }
    
    @RequestMapping(value = "exportQRInfo", method = RequestMethod.POST)
    public @ResponseBody String exportCardInfo (HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        String meetingId = (String)session.getAttribute(Constant.SESSION_MEETING_ID);
        String[] exportHeds = new String[]{"姓名","证件类型","照片","二维码","用户类型"};
        List<Object[]> dataset = confUserService.findAllForExportQR(meetingId, request);
        if(dataset == null || dataset.size() < 1){
            return "没有查找到相应的数据,请刷新数据后重试";
        }
        response.setContentType("application/vnd.ms-excel");//;charset=utf-8
        response.setHeader("Content-Disposition", "attachment;filename=exportUserQRInfo.xls");
        response.setHeader("Pragma","No-cache");
        response.setHeader ( "Cache-Control", "no-store"); 
        try {
            OutputStream sos = response.getOutputStream();
            ExportExcelsUtil.exportExcel(0, 1, 1, "身份卡信息", exportHeds, dataset, sos);
            response.flushBuffer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "成功导出" + dataset.size() + "条用户数据。";
    }

}
