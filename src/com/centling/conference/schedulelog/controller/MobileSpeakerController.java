package com.centling.conference.schedulelog.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.centling.conference.schedulelog.service.ConfSchedulelogUserAssignService;

@Controller("mobileSpeakerController")
@RequestMapping("/mobile/speaker")
public class MobileSpeakerController {
	
	@Resource
	ConfSchedulelogUserAssignService confSchedulelogUserAssignService;

	@RequestMapping(value="/r", method = RequestMethod.GET)
	 public @ResponseBody List<Map<String, Object>> findSpeakersByScheduleId(String scheduleId){
		 return confSchedulelogUserAssignService.findMobileSpeakersByScheduleId(scheduleId);
	 }
	 
}
