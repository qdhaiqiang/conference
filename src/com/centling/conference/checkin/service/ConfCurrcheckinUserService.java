package com.centling.conference.checkin.service;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.search.aggregator.Count;

import org.springframework.stereotype.Service;

import com.centling.conference.base.page.PageBean;
import com.centling.conference.base.page.Pagination;
import com.centling.conference.checkin.DAO.ConfCurrcheckinUserDAO;
import com.centling.conference.checkin.entity.ConfCurrcheckinUser;
import com.centling.conference.meetinguser.entity.ConfGuest;

@Service("confCurrcheckinUserService")
public class ConfCurrcheckinUserService {
    
	   @Resource
	    private ConfCurrcheckinUserDAO confCurrcheckinUserDAO;
	
	   public Pagination retrieve(PageBean pageBean ,String meetingId,String scheduleId, ConfGuest user) {
			Pagination pagination = new Pagination();
			pagination.setRows(confCurrcheckinUserDAO.retrieve(pageBean, meetingId, scheduleId, user));
			pagination.setTotal(confCurrcheckinUserDAO.count(meetingId,user,scheduleId)+"");
			return pagination;
		}
	   
	 
	   
	   public String deleteCurrcheckinUser(ConfCurrcheckinUser checkInUser){
	    	
	    	confCurrcheckinUserDAO.delete(checkInUser);
	    	return "Success";
	    	
	    }
	   
	   public String saveCurrcheckinUser(ConfCurrcheckinUser checkInUser){
	    	
	    	confCurrcheckinUserDAO.save(checkInUser);
	    	return "Success";
	    	
	    }
	   
	   public String updateCurrcheckinUser(ConfCurrcheckinUser checkInUser){
	    	
	    	confCurrcheckinUserDAO.update(checkInUser);
	    	return "Success";
	    	
	    }
	   public List retrieveAudience(String meetingId, String scheduleId, String[] userType){
		 
		   return confCurrcheckinUserDAO.retrieveAudience(meetingId, scheduleId, userType);
	   }
	   public List retrieveGuest(String meetingId, String scheduleId, String[] userType){
		 
		   return confCurrcheckinUserDAO.retrieveGuest(meetingId, scheduleId, userType);
	   }
	   
	  
	   public List findBymeetingIdAndUserId(String meetingId, String userId, String scheduleId){
		   return confCurrcheckinUserDAO.findByUserId(meetingId, userId, scheduleId);
	   }
	  }
