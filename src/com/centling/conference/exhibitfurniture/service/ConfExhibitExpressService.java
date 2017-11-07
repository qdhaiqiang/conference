package com.centling.conference.exhibitfurniture.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress;

@Service("confExhibitExpressService")
public class ConfExhibitExpressService {

	@Resource
	private ConfExhibitExpressDAO confExhibitExpressDAO;
	
	public List<ConfExhibitExpress> findByMeetingId(String meetingId){
		return confExhibitExpressDAO.findByMeetingId(meetingId);
	}

	public Pagination retrieve(PageBean pageBean, String meetingId) {
		Pagination pagination = new Pagination();
		List<ConfExhibitExpress> list = confExhibitExpressDAO.retrieve(pageBean, meetingId);
		pagination.setRows(list);
		pagination.setTotal(confExhibitExpressDAO.count(meetingId)+"");
		return pagination;
	}

	public void save(ConfExhibitExpress confExhibitExpress) {
		confExhibitExpressDAO.save(confExhibitExpress);
	}

	public void update(ConfExhibitExpress confExhibitExpress) {
		confExhibitExpressDAO.update(confExhibitExpress);
	}

	public void delete(String expressId) {
		ConfExhibitExpress confExhibitExpress = confExhibitExpressDAO.findById(expressId);
		confExhibitExpressDAO.delete(confExhibitExpress);
	}
}
