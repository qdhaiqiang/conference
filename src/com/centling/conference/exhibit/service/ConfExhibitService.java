package com.centling.conference.exhibit.service;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.centling.conference.exhibit.DAO.ConfExhibitDAO;
import com.centling.conference.exhibit.entity.ConfExhibit;
import com.centling.conference.user.entity.ConfUser;

@Service("confExhibitService")
public class ConfExhibitService {
	private static final Logger log = LoggerFactory.getLogger(ConfExhibitService.class);

	public List<ConfExhibit> findByExhibitorId(String exhibitorId) {
		return confExhibitDAO.findByExhibitorId(exhibitorId);
	}
	
	/**
	 * 查找本次会议该展商已录入的展品
	 * @param meetingId 会议id
	 * @param exhibitorId 展商id
	 * @return
	 */
	public List<ConfExhibit> findExhibitor(String meetingId,String exhibitorId) {
		return confExhibitDAO.findExhibitor(meetingId,exhibitorId);
	}
	
	public ConfExhibit findById(String id) {
		return confExhibitDAO.findById(id);
	}
	
	public void save(ConfExhibit confExhibit) {
		confExhibitDAO.save(confExhibit);
	}
	
	@Resource
	ConfExhibitDAO confExhibitDAO;
}
