package com.centling.conference.additioninfo.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.additioninfo.entity.ConfAdditionInfo;
import com.centling.conference.base.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for ConfAdditionInfo entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.additioninfo.entity.ConfAdditionInfo
  * @author MyEclipse Persistence Tools 
 */
@Repository("confAdditionInfoDAO")
public class ConfAdditionInfoDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfAdditionInfoDAO.class);
		//property constants
	public static final String MEETING_ID = "meetingId";
	public static final String AIR_TICKET_ID = "airTicketId";
	public static final String SHIP_TICKET_ID = "shipTicketId";
	public static final String TRAIN_TICKET_ID = "trainTicketId";
	public static final String HOTEL_ID = "hotelId";
	public static final String ARRIVAL_TYPE = "arrivalType";
	public static final String ARRIVAL_TIME = "arrivalTime";
	public static final String NUMBER = "number";



    
    public void save(ConfAdditionInfo transientInstance) {
        log.debug("saving ConfAdditionInfo instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfAdditionInfo persistentInstance) {
        log.debug("deleting ConfAdditionInfo instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfAdditionInfo findById( java.lang.String id) {
        log.debug("getting ConfAdditionInfo instance with id: " + id);
        try {
            ConfAdditionInfo instance = (ConfAdditionInfo) getSession()
                    .get("com.centling.conference.additioninfo.entity.ConfAdditionInfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfAdditionInfo> findByExample(ConfAdditionInfo instance) {
        log.debug("finding ConfAdditionInfo instance by example");
        try {
            List<ConfAdditionInfo> results = (List<ConfAdditionInfo>) getSession()
                    .createCriteria("com.centling.conference.additioninfo.entity.ConfAdditionInfo")
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
      log.debug("finding ConfAdditionInfo instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfAdditionInfo as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfAdditionInfo> findByMeetingId(Object meetingId
	) {
		return findByProperty(MEETING_ID, meetingId
		);
	}
	
	public List<ConfAdditionInfo> findByAirTicketId(Object airTicketId
	) {
		return findByProperty(AIR_TICKET_ID, airTicketId
		);
	}
	
	public List<ConfAdditionInfo> findByShipTicketId(Object shipTicketId
	) {
		return findByProperty(SHIP_TICKET_ID, shipTicketId
		);
	}
	
	public List<ConfAdditionInfo> findByTrainTicketId(Object trainTicketId
	) {
		return findByProperty(TRAIN_TICKET_ID, trainTicketId
		);
	}
	
	public List<ConfAdditionInfo> findByHotelId(Object hotelId
	) {
		return findByProperty(HOTEL_ID, hotelId
		);
	}
	
	public List<ConfAdditionInfo> findByArrivalType(Object arrivalType
	) {
		return findByProperty(ARRIVAL_TYPE, arrivalType
		);
	}
	
	public List<ConfAdditionInfo> findByArrivalTime(Object arrivalTime
	) {
		return findByProperty(ARRIVAL_TIME, arrivalTime
		);
	}
	
	public List<ConfAdditionInfo> findByNumber(Object number
	) {
		return findByProperty(NUMBER, number
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfAdditionInfo instances");
		try {
			String queryString = "from ConfAdditionInfo";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfAdditionInfo merge(ConfAdditionInfo detachedInstance) {
        log.debug("merging ConfAdditionInfo instance");
        try {
            ConfAdditionInfo result = (ConfAdditionInfo) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfAdditionInfo instance) {
        log.debug("attaching dirty ConfAdditionInfo instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfAdditionInfo instance) {
        log.debug("attaching clean ConfAdditionInfo instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}