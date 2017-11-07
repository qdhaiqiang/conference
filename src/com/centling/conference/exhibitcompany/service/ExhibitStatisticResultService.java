package com.centling.conference.exhibitcompany.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibitcompany.entity.ExhibitBoothStatistic;
import com.centling.conference.exhibitcompany.entity.ExhibitStatisticResult;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneedsDAO;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitFurnitureDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;
import com.centling.conference.exhibition.DAO.ConfExhibitionDAO;
import com.centling.conference.exhibition.entity.ConfExhibition;

@Service("exhibitStatisticResultService")
public class ExhibitStatisticResultService {

	@Resource
	private ConfExhibitFurnitureDAO confExhibitFurnitureDAO;
	@Resource
	private ConfExhibitionDAO confExhibitionDAO;
	@Resource
	private ConfExhibitExpressneedsDAO confExhibitExpressneedsDAO;
	/**
	 * 统计
	 * @return
	 */
	public Map<String,Object> statisticResult(String meetingId){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = confExhibitionDAO.statisticResult(meetingId);
		//处理成ExhibitStatisticResult的格式
		List<ConfExhibition> confExhibitionList = confExhibitionDAO.findByMeetingId(meetingId);		
		List<ExhibitStatisticResult> exhibitBooths = new ArrayList<ExhibitStatisticResult>();
		for(int k=0;k<confExhibitionList.size();k++){
			ExhibitStatisticResult result =  new ExhibitStatisticResult(confExhibitionList.get(k).getExhibitionId(),confExhibitionList.get(k).getExhibitionName(),0,0);
			exhibitBooths.add(result);
		}
		
		for(int i=0;i<list.size();i++){
			Map<String,Object> booth = list.get(i);
			for(int j=0;j<exhibitBooths.size();j++){
				if(exhibitBooths.get(j).getId().equals(booth.get("exhibitionId").toString())){
					ExhibitStatisticResult result = exhibitBooths.get(j);
					result.setResultAllNum(result.getResultAllNum()+Integer.valueOf(booth.get("rentAmount").toString()) );
					if(booth.get("rentState") != null && !booth.get("rentState").toString().equals("") && Integer.valueOf(booth.get("rentState").toString())==Constant.STATE_USE){
						result.setResultRentNum(result.getResultRentNum()+Integer.valueOf(booth.get("rentAmount").toString()));
						exhibitBooths.set(j, result);
					}
					break;
				}
			}
		}
		
		List<ConfExhibitFurniture> furniture = confExhibitFurnitureDAO.findByMeetingId(meetingId);
		List<ConfExhibitExpressneeds> expressNeeds = confExhibitExpressneedsDAO.findByMeetingId(meetingId);
		map.put("exhibitBooths", exhibitBooths);
		map.put("furniture", furniture);
		map.put("expressNeeds", expressNeeds==null ? 0 :expressNeeds.size());
		return map;
	}
}
