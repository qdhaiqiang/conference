package com.centling.conference.assignment.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.assignment.entity.ConfAssignment;
import com.centling.conference.base.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for ConfAssignment entities.
 			* Transaction control of the save(), update() and delete() operations
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions.
		Each of these methods provides additional information for how to configure it for the desired type of transaction control.
	 * @see com.centling.conference.assignment.entity.ConfAssignment
  * @author MyEclipse Persistence Tools
 */
@Repository("confAssignmentDAO")
public class ConfAssignmentDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfAssignmentDAO.class);
		//property constants
	public static final String MEETING_ID = "meetingId";
	public static final String USER_ID = "userId";
	public static final String GUEST_ID = "guestId";
	public static final String UPDATE_DATE = "updateDate";


    public void save(ConfAssignment transientInstance) {
        log.debug("saving ConfAssignment instance");
        try {
            getSession().save(transientInstance);
            getSession().flush();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

	public void delete(ConfAssignment persistentInstance) {
        log.debug("deleting ConfAssignment instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfAssignment findById( java.lang.String id) {
        log.debug("getting ConfAssignment instance with id: " + id);
        try {
            ConfAssignment instance = (ConfAssignment) getSession()
                    .get("com.centling.conference.assignment.entity.ConfAssignment", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }


    public List<ConfAssignment> findByExample(ConfAssignment instance) {
        log.debug("finding ConfAssignment instance by example");
        try {
            List<ConfAssignment> results = (List<ConfAssignment>) getSession()
                    .createCriteria("com.centling.conference.assignment.entity.ConfAssignment")
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
      log.debug("finding ConfAssignment instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfAssignment as model where model."
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public List<ConfAssignment> findByMeetingId(Object meetingId
    ) {
        return findByProperty(MEETING_ID, meetingId
        );
    }

	public List<ConfAssignment> findByUserId(Object userId
	) {
		return findByProperty(USER_ID, userId
		);
	}

	public List<ConfAssignment> findByGuestId(Object guestId
	) {
		return findByProperty(GUEST_ID, guestId
		);
	}

	public List<ConfAssignment> findByUpdateDate(Object updateDate
	) {
		return findByProperty(UPDATE_DATE, updateDate
		);
	}


	public List findAll() {
		log.debug("finding all ConfAssignment instances");
		try {
			String queryString = "from ConfAssignment";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

    public ConfAssignment merge(ConfAssignment detachedInstance) {
        log.debug("merging ConfAssignment instance");
        try {
            ConfAssignment result = (ConfAssignment) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfAssignment instance) {
        log.debug("attaching dirty ConfAssignment instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfAssignment instance) {
        log.debug("attaching clean ConfAssignment instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public int deleteByGuestId(String meetingId, String guestId) {
        log.debug("deleting ConfAssignment instance");
        try {
            String queryString = "delete from conf_assignment where meeting_id = ? and guest_id = ?";
            Query queryObject = getSession().createSQLQuery(queryString).setString(0, meetingId).setString(1, guestId);
            log.debug("delete successful");
            return queryObject.executeUpdate();
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

}