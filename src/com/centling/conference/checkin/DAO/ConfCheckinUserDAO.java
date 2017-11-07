package com.centling.conference.checkin.DAO;
// default package

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.Constant;
import com.centling.conference.checkin.entity.ConfCheckinUser;

/**
 	* A data access object (DAO) providing persistence and search support for ConfCheckinUser entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .ConfCheckinUser
  * @author MyEclipse Persistence Tools 
 */
@Repository("confCheckinUserDAO")
public class ConfCheckinUserDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfCheckinUserDAO.class);
		//property constants
	public static final String USER_ID = "userId";
	public static final String MEETING_ID = "meetingId";
	public static final String CHECK_STATE = "checkState";
	public static final String CHECK_TIME = "checkTime";
	public static final String CHECK_TIME_COPY = "checkTimeCopy";
	public static final String OPERATOR = "operator";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String STATE = "state";



    
    public void save(ConfCheckinUser transientInstance) {
        log.debug("saving ConfCheckinUser instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfCheckinUser persistentInstance) {
        log.debug("deleting ConfCheckinUser instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	
public List findCheckinRecord(String userId,String meetingId, String scheduleId){
	
	log.debug("getting ConfUser instance with userid: " + userId);
	
    try {
        Query query = null;
        String queryString =       
        		
        		        		"from ConfCheckinUser u " +
        		
        		" where u.meetingId = ? and u.userId = ? and u.scheduleId =? and u.checkState = ?"+
        		" order by u.checkTime desc  " ;

      
        query = getSession().createQuery(queryString);
        query.setString(0, meetingId);
        query.setString(1, userId);
        query.setString(2, scheduleId);
        query.setInteger(3,Constant.CHECKIN_STATE_IN);

        return query.list();
    } catch (RuntimeException re) {
        log.error("find signin user failed", re);
        throw re;
    }
   

	
}


public Object ifHeadSetRecord(String userId,String meetingId, String scheduleId){
	
	log.debug("getting ConfUser instance with userid: " + userId);	
    try {
        Query query = null;
        String queryString =       
        		
        		        		"select sum(isheadset) from conf_checkin_user u " +
        		
        		" where u.meeting_id = ? and u.user_id = ? and u.schedule_id =?";

      
        query = getSession().createSQLQuery(queryString);
        query.setString(0, meetingId);
        query.setString(1, userId);
        query.setString(2, scheduleId);
        return query.uniqueResult();
    } catch (RuntimeException re) {
        log.error("find signin user failed", re);
        throw re;
    }
   

	
}
public List findCheckoutRecord(String userId,String meetingId, String scheduleId){
	
	log.debug("getting ConfUser instance with userid: " + userId);
	
    try {
        Query query = null;
        String queryString =       
        		
        		        		"from ConfCheckinUser u " +
        		
        		" where u.meetingId = ? and u.userId = ? and u.scheduleId =? and u.checkState = ?"+
        		" order by u.checkTime desc  " ;

      
        query = getSession().createQuery(queryString);
        query.setString(0, meetingId);
        query.setString(1, userId);
        query.setString(2, scheduleId);
        query.setInteger(3, Constant.CHECKIN_STATE_OUT);

        return query.list();
    } catch (RuntimeException re) {
        log.error("find signin user failed", re);
        throw re;
    }
   

	
}
	
    public ConfCheckinUser findById( java.lang.String id) {
        log.debug("getting ConfCheckinUser instance with id: " + id);
        try {
            ConfCheckinUser instance = (ConfCheckinUser) getSession()
                    .get("ConfCheckinUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfCheckinUser> findByExample(ConfCheckinUser instance) {
        log.debug("finding ConfCheckinUser instance by example");
        try {
            List<ConfCheckinUser> results = (List<ConfCheckinUser>) getSession()
                    .createCriteria("ConfCheckinUser")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ConfCheckinUser instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfCheckinUser as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
    
    
    public List findByUserIdAndMeetingId(String userId, String meetingId, String scheduleId) {
        log.debug("finding ConfCheckinUser instance with userId: " + userId
              +", meetingId" +meetingId);
        try {
           String queryString = "from ConfCheckinUser as model where model.userId= ? and model.meetingId = ? and model.scheduleId = ?";
           Query queryObject = getSession().createQuery(queryString);
  		 queryObject.setParameter(0, userId);
  		 queryObject.setParameter(1, meetingId);
  		queryObject.setParameter(2, scheduleId);
  		 
  		 return queryObject.list();
        } catch (RuntimeException re) {
           log.error("find by property name failed", re);
           throw re;
        }
  	}


	public List<ConfCheckinUser> findByUserId(Object userId
	) {
		return findByProperty(USER_ID, userId
		);
	}
	
	public List<ConfCheckinUser> findByMeetingId(Object meetingId
	) {
		return findByProperty(MEETING_ID, meetingId
		);
	}
	
	public List<ConfCheckinUser> findByCheckState(Object checkState
	) {
		return findByProperty(CHECK_STATE, checkState
		);
	}
	
	public List<ConfCheckinUser> findByCheckTime(Object checkTime
	) {
		return findByProperty(CHECK_TIME, checkTime
		);
	}
	
	public List<ConfCheckinUser> findByCheckTimeCopy(Object checkTimeCopy
	) {
		return findByProperty(CHECK_TIME_COPY, checkTimeCopy
		);
	}
	
	public List<ConfCheckinUser> findByOperator(Object operator
	) {
		return findByProperty(OPERATOR, operator
		);
	}
	
	public List<ConfCheckinUser> findByScheduleId(Object scheduleId
	) {
		return findByProperty(SCHEDULE_ID, scheduleId
		);
	}
	
	public List<ConfCheckinUser> findByState(Object state
	) {
		return findByProperty(STATE, state
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfCheckinUser instances");
		try {
			String queryString = "from ConfCheckinUser";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfCheckinUser merge(ConfCheckinUser detachedInstance) {
        log.debug("merging ConfCheckinUser instance");
        try {
            ConfCheckinUser result = (ConfCheckinUser) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfCheckinUser instance) {
        log.debug("attaching dirty ConfCheckinUser instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfCheckinUser instance) {
        log.debug("attaching clean ConfCheckinUser instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}