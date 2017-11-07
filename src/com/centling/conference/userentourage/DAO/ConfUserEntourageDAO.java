package com.centling.conference.userentourage.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.userentourage.entity.ConfUserEntourage;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfUserEntourage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.userentourage.entity.ConfUserEntourage
 * @author MyEclipse Persistence Tools
 */
@Repository("confUserEntourageDAO")
public class ConfUserEntourageDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfUserEntourageDAO.class);
	// property constants
	public static final String MEETING_ID="meetingId";
	public static final String USER_ID = "userId";
	public static final String ENTOURAGE_ID = "entourageId";
	public static final String TYPE = "type";

	public void save(ConfUserEntourage transientInstance) {
		log.debug("saving ConfUserEntourage instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfUserEntourage persistentInstance) {
		log.debug("deleting ConfUserEntourage instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfUserEntourage findById(java.lang.String id) {
		log.debug("getting ConfUserEntourage instance with id: " + id);
		try {
			ConfUserEntourage instance = (ConfUserEntourage) getSession()
					.get("com.centling.conference.userentourage.entity.ConfUserEntourage",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfUserEntourage> findByExample(ConfUserEntourage instance) {
		log.debug("finding ConfUserEntourage instance by example");
		try {
			List<ConfUserEntourage> results = (List<ConfUserEntourage>) getSession()
					.createCriteria(
							"com.centling.conference.userentourage.entity.ConfUserEntourage")
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
		log.debug("finding ConfUserEntourage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfUserEntourage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfUserEntourage> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}
	
	public List<ConfUserEntourage> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfUserEntourage> findByEntourageId(Object entourageId) {
		return findByProperty(ENTOURAGE_ID, entourageId);
	}

	public List<ConfUserEntourage> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findAll() {
		log.debug("finding all ConfUserEntourage instances");
		try {
			String queryString = "from ConfUserEntourage";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfUserEntourage merge(ConfUserEntourage detachedInstance) {
		log.debug("merging ConfUserEntourage instance");
		try {
			ConfUserEntourage result = (ConfUserEntourage) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfUserEntourage instance) {
		log.debug("attaching dirty ConfUserEntourage instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfUserEntourage instance) {
		log.debug("attaching clean ConfUserEntourage instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}