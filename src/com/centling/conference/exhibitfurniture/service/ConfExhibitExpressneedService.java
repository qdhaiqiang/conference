package com.centling.conference.exhibitfurniture.service;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneedsDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;
import com.centling.conference.exhibitrent.service.ConfRentService;

@Service("confExhibitExpressneedService")
public class ConfExhibitExpressneedService {
	private static final Logger log = LoggerFactory.getLogger(ConfExhibitExpressneedService.class);

	@Resource
	private ConfExhibitExpressneedsDAO confExhibitExpressneedsDAO;
	
	public ConfExhibitExpressneeds findExpressneeds(String meetingId,String exhibitorId){
		ConfExhibitExpressneeds confExpressneeds = null;
		List<ConfExhibitExpressneeds> needList = confExhibitExpressneedsDAO.findExpressneeds(meetingId, exhibitorId);
		if(needList != null && needList.size() > 0){
			confExpressneeds = needList.get(0);
		}
		return confExpressneeds;
	}
	
}
