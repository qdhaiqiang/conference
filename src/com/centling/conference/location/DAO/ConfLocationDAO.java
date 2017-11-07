package com.centling.conference.location.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.location.entity.ConfLocation;

/**
 	* A data access object (DAO) providing persistence and search support for ConfLocation entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.location.entity.ConfLocation
  * @author MyEclipse Persistence Tools 
 */
@Repository("confLocationDAO")
public class ConfLocationDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfLocationDAO.class);
		//property constants
	public static final String LOCATION = "location";
	public static final String SCHEDULE_ID = "scheduleId";



    
    public void save(ConfLocation transientInstance) {
        log.debug("saving ConfLocation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfLocation persistentInstance) {
        log.debug("deleting ConfLocation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfLocation findById( java.lang.String id) {
        log.debug("getting ConfLocation instance with id: " + id);
        try {
            ConfLocation instance = (ConfLocation) getSession()
                    .get("com.centling.conference.location.entity.ConfLocation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfLocation> findByExample(ConfLocation instance) {
        log.debug("finding ConfLocation instance by example");
        try {
            List<ConfLocation> results = (List<ConfLocation>) getSession()
                    .createCriteria("com.centling.conference.location.entity.ConfLocation")
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
      log.debug("finding ConfLocation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfLocation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfLocation> findByLocation(Object location
	) {
		return findByProperty(LOCATION, location
		);
	}
	
	public List<ConfLocation> findByScheduleId(Object scheduleId
	) {
		return findByProperty(SCHEDULE_ID, scheduleId
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfLocation instances");
		try {
			String queryString = "from ConfLocation";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfLocation merge(ConfLocation detachedInstance) {
        log.debug("merging ConfLocation instance");
        try {
            ConfLocation result = (ConfLocation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfLocation instance) {
        log.debug("attaching dirty ConfLocation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfLocation instance) {
        log.debug("attaching clean ConfLocation instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}