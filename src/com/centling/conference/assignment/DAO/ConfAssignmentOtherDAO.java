package com.centling.conference.assignment.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.assignment.entity.ConfAssignmentOther;
import com.centling.conference.base.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfAssignmentOther entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.assignment.entity.ConfAssignmentOther
 * @author MyEclipse Persistence Tools
 */
@Repository("confAssignmentOtherDAO")
public class ConfAssignmentOtherDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfAssignmentOtherDAO.class);
    // property constants
    public static final String MEETING_ID = "meetingId";
    public static final String SCHEDULE_ID = "scheduleId";
    public static final String USER_ID = "userId";
    public static final String UPDATE_DATE = "updateDate";
    public static final String USER_NAME = "userName";

    public void save(ConfAssignmentOther transientInstance) {
        log.debug("saving ConfAssignmentOther instance");
        try {
            getSession().save(transientInstance);
            getSession().flush();
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfAssignmentOther persistentInstance) {
        log.debug("deleting ConfAssignmentOther instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfAssignmentOther findById(java.lang.String id) {
        log.debug("getting ConfAssignmentOther instance with id: " + id);
        try {
            ConfAssignmentOther instance = (ConfAssignmentOther) getSession()
                    .get("com.centling.conference.assignment.entity.ConfAssignmentOther",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfAssignmentOther> findByExample(ConfAssignmentOther instance) {
        log.debug("finding ConfAssignmentOther instance by example");
        try {
            List<ConfAssignmentOther> results = (List<ConfAssignmentOther>) getSession()
                    .createCriteria(
                            "com.centling.conference.assignment.entity.ConfAssignmentOther")
                    .add(create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding ConfAssignmentOther instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ConfAssignmentOther as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<ConfAssignmentOther> findByMeetingId(Object meetingId) {
        return findByProperty(MEETING_ID, meetingId);
    }
    
    public List<ConfAssignmentOther> findByUserName(Object meetingId) {
        return findByProperty(USER_NAME, meetingId);
    }

    public List<ConfAssignmentOther> findByScheduleId(Object scheduleId) {
        return findByProperty(SCHEDULE_ID, scheduleId);
    }

    public List<ConfAssignmentOther> findByUserId(Object userId) {
        return findByProperty(USER_ID, userId);
    }

    public List<ConfAssignmentOther> findByUpdateDate(Object updateDate) {
        return findByProperty(UPDATE_DATE, updateDate);
    }

    public List findAll() {
        log.debug("finding all ConfAssignmentOther instances");
        try {
            String queryString = "from ConfAssignmentOther";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfAssignmentOther merge(ConfAssignmentOther detachedInstance) {
        log.debug("merging ConfAssignmentOther instance");
        try {
            ConfAssignmentOther result = (ConfAssignmentOther) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfAssignmentOther instance) {
        log.debug("attaching dirty ConfAssignmentOther instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfAssignmentOther instance) {
        log.debug("attaching clean ConfAssignmentOther instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public int deleteByScheduleId(String meetingId, String scheduleId) {
        log.debug("deleting ConfAssignment instance");
        try {
            String queryString = "delete from conf_assignment_other where meeting_id = ? and schedule_id = ?";
            Query queryObject = getSession().createSQLQuery(queryString).setString(0, meetingId).setString(1, scheduleId);
            log.debug("delete successful");
            return queryObject.executeUpdate();
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

	public List<ConfAssignmentOther> findListByUserId(String userId, String meetingId) {
		log.debug("finding ConfAssignmentOther instance by userId");
        try {
            String queryString = "select model.* from conf_assignment_other as model where model.meeting_id=? and find_in_set(?,user_id)";
            Query queryObject = getSession().createSQLQuery(queryString).addEntity(ConfAssignmentOther.class);
            queryObject.setString(0, meetingId).setString(1, userId);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find List by userId failed", re);
            throw re;
        }
	}
}