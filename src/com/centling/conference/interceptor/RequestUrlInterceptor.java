package com.centling.conference.interceptor;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.centling.conference.base.Constant;
import com.centling.conference.essaytype.service.ConfEssayTypeService;
import com.centling.conference.meeting.entity.ConfMeeting;
import com.centling.conference.meeting.service.ConfMeetingService;

/**
 * Spring请求拦截器
 * 
 * @author Dirk
 * 
 */
public class RequestUrlInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private ConfMeetingService confMeetingService;
	@Resource
	private ConfEssayTypeService confEssayTypeService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取请求路径
		String requestUrl = request.getRequestURL().toString();
		// 分析请求路径
		// 如果访问路径以namkwong.org结尾
		if (requestUrl.contains(Constant.URL_SUFFIX)) {
			// 截取访问路径URL
			int start = requestUrl.indexOf("http://")+7;
			int end = -1;
			if (requestUrl.indexOf("."+Constant.URL_SUFFIX)!=-1) {
				end = requestUrl.indexOf("."+Constant.URL_SUFFIX);
			} else {
				end = requestUrl.indexOf(Constant.URL_SUFFIX);
			}
			String meetingName = requestUrl.substring(start,end);
			// 如果得到会议名称为www，则将会议名称设置为空
			if (StringUtils.equals(Constant.URL_PREFIX, meetingName)) {
				meetingName = StringUtils.EMPTY;
			}
			int port = request.getServerPort();
			String requestPath = requestUrl.substring(requestUrl.indexOf(Constant.URL_SUFFIX)+Constant.URL_SUFFIX.length());
			if (port != 80) {
				requestPath = requestPath.replace(":"+port, StringUtils.EMPTY);
			}
			requestPath = requestPath.replace(Constant.PROJECT_NAME, StringUtils.EMPTY);
			// 是否是手机端登录
			boolean mobileFlag = false;
			if (meetingName.startsWith("m.")) {
				meetingName = meetingName.substring(2,meetingName.length());
				mobileFlag = true;
			}
			List<ConfMeeting> meetingList = confMeetingService.findByProperty("url", meetingName);
			// 如果URL不为空，则去数据库中匹配查找相应的会议ID
			if (StringUtils.isNotEmpty(meetingName)) {
				Map<String, Object> essayMenuInSession = (Map<String, Object>)request.getSession().getAttribute(Constant.CONF_ESSAY_MENU);
				if (requestPath.length()<2) {
					if (meetingList != null && !meetingList.isEmpty()) {
						// 获取会议ID
						String meetingId = meetingList.get(0).getId();
						// 根据会议ID查询菜单权限 非手机端
						if (!mobileFlag) {
							essayMenuInSession = confEssayTypeService.findMenuByMeetingId(meetingId);
							request.getSession().setAttribute(Constant.CONF_ESSAY_MENU, essayMenuInSession);
						// 手机端查询会议新闻与会议指南
						} else {
							// 获取会议新闻
							request.getSession().setAttribute(Constant.MOBILE_NEWS_LIST, confEssayTypeService.findNews());
							// 获取会议指南
							request.getSession().setAttribute(Constant.MOBILE_MEETING_GUIDE, confEssayTypeService.findGuide());
						}
						// 将会议信息保存到session中
						request.getSession().setAttribute(Constant.FRONT_SESSION_MEETING, meetingList.get(0));
						// 将会议ID保存到session中
						request.getSession().setAttribute(Constant.FRONT_SESSION_MEETING_ID, meetingId);
						// 页面跳转
						// 手机版跳转
						if (mobileFlag) {
							request.getRequestDispatcher("r/mobile/login").forward(request, response);
						// 网页版跳转
						} else {
							if (StringUtils.equals(Constant.YACHT_PREFIX, meetingName)) {
								request.getRequestDispatcher("r/front/yacht/yacht_register").forward(request, response);
							} else {
								request.getRequestDispatcher("r/front/meeting_intro").forward(request, response);
							}
						}
					// 未查找到会议
					} else {
						request.getRequestDispatcher("404.jsp").forward(request, response);
					}
					return false;
				} else {
					if (meetingList != null && !meetingList.isEmpty()) {
						// 获取会议ID
						String meetingId = meetingList.get(0).getId();
						// 将会议ID保存到session中
						request.getSession().setAttribute(Constant.FRONT_SESSION_MEETING_ID, meetingId);
					}
				}
			}
		}
		return true;
	}
}