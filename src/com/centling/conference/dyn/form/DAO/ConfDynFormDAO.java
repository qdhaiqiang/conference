package com.centling.conference.dyn.form.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.dyn.form.entity.ConfDynForm;

/**
 	* A data access object (DAO) providing persistence and search support for ConfDynForm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.dyn.form.entity.ConfDynForm
  * @author MyEclipse Persistence Tools 
 */
@Repository("confDynFormDAO")
public class ConfDynFormDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfDynFormDAO.class);
		//property constants
	public static final String MEETING_ID = "meetingId";
	public static final String USER_TYPE = "userType";
	public static final String PAYLOAD = "payload";



    
    public void save(ConfDynForm transientInstance) {
        log.debug("saving ConfDynForm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfDynForm persistentInstance) {
        log.debug("deleting ConfDynForm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfDynForm findById( java.lang.String id) {
        log.debug("getting ConfDynForm instance with id: " + id);
        try {
            ConfDynForm instance = (ConfDynForm) getSession()
                    .get("com.centling.conference.dyn.form.entity.ConfDynForm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfDynForm> findByExample(ConfDynForm instance) {
        log.debug("finding ConfDynForm instance by example");
        try {
            List<ConfDynForm> results = (List<ConfDynForm>) getSession()
                    .createCriteria("com.centling.conference.dyn.form.entity.ConfDynForm")
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
      log.debug("finding ConfDynForm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfDynForm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfDynForm> findByMeetingId(Object meetingId
	) {
		return findByProperty(MEETING_ID, meetingId
		);
	}
	
	public List<ConfDynForm> findByUserType(Object userType
	) {
		return findByProperty(USER_TYPE, userType
		);
	}
	
	public List<ConfDynForm> findByPayload(Object payload
	) {
		return findByProperty(PAYLOAD, payload
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfDynForm instances");
		try {
			String queryString = "from ConfDynForm";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfDynForm merge(ConfDynForm detachedInstance) {
        log.debug("merging ConfDynForm instance");
        try {
            ConfDynForm result = (ConfDynForm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfDynForm instance) {
        log.debug("attaching dirty ConfDynForm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfDynForm instance) {
        log.debug("attaching clean ConfDynForm instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ConfDynForm> retrieve(PageBean pageBean, String meetingId) {
        log.debug("finding all ConfDynForm instances");
        try {
            String queryString = "from ConfDynForm where meetingId=? order by userType asc";
             Query queryObject = getSession().createQuery(queryString).setString(0, meetingId).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public Long count(String meetingId) {
		log.debug("finding all ConfDynForm count");
        try {
            String queryString = "select count(1) from ConfDynForm where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
}