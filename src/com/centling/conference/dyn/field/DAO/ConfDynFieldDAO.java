package com.centling.conference.dyn.field.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.dyn.field.entity.ConfDynField;

/**
 	* A data access object (DAO) providing persistence and search support for ConfDynField entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.dyn.field.entity.ConfDynField
  * @author MyEclipse Persistence Tools 
 */
@Repository("confDynFieldDAO")
public class ConfDynFieldDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfDynFieldDAO.class);
		//property constants
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String MEETING_ID = "meetingId";
	public static final String USER_TYPE = "userType";
	public static final String REQUIRED = "required";
	public static final String DESCRIPTION = "description";
	public static final String OPTIONS = "options";
	public static final String ORDER_NUM = "orderNum";



    
    public void save(ConfDynField transientInstance) {
        log.debug("saving ConfDynField instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfDynField persistentInstance) {
        log.debug("deleting ConfDynField instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfDynField findById( java.lang.String id) {
        log.debug("getting ConfDynField instance with id: " + id);
        try {
            ConfDynField instance = (ConfDynField) getSession()
                    .get("com.centling.conference.dyn.field.entity.ConfDynField", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfDynField> findByExample(ConfDynField instance) {
        log.debug("finding ConfDynField instance by example");
        try {
            List<ConfDynField> results = (List<ConfDynField>) getSession()
                    .createCriteria("com.centling.conference.dyn.field.entity.ConfDynField")
                    .addOrder(Order.asc("orderNum"))
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
      log.debug("finding ConfDynField instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfDynField as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfDynField> findByType(Object type
	) {
		return findByProperty(TYPE, type
		);
	}
	
	public List<ConfDynField> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<ConfDynField> findByMeetingId(Object meetingId
	) {
		return findByProperty(MEETING_ID, meetingId
		);
	}
	
	public List<ConfDynField> findByUserType(Object userType
	) {
		return findByProperty(USER_TYPE, userType
		);
	}
	
	public List<ConfDynField> findByRequired(Object required
	) {
		return findByProperty(REQUIRED, required
		);
	}
	
	public List<ConfDynField> findByDescription(Object description
	) {
		return findByProperty(DESCRIPTION, description
		);
	}
	
	public List<ConfDynField> findByOptions(Object options
	) {
		return findByProperty(OPTIONS, options
		);
	}
	
	public List<ConfDynField> findByOrderNum(Object orderNum
	) {
		return findByProperty(ORDER_NUM, orderNum
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfDynField instances");
		try {
			String queryString = "from ConfDynField";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfDynField merge(ConfDynField detachedInstance) {
        log.debug("merging ConfDynField instance");
        try {
            ConfDynField result = (ConfDynField) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfDynField instance) {
        log.debug("attaching dirty ConfDynField instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfDynField instance) {
        log.debug("attaching clean ConfDynField instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}