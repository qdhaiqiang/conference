package com.centling.conference.exhibitfurniture.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibitExpressneeds entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneeds
 * @author MyEclipse Persistence Tools
 */
@Repository("confExhibitExpressneedsDAO")
public class ConfExhibitExpressneedsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitExpressneedsDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EXHIBITOR_ID = "exhibitorId";
	public static final String EXPRESS_NEED = "expressNeed";
	public static final String EXPRESS_FILE = "expressFile";
	public static final String MEMO = "memo";

	public void save(ConfExhibitExpressneeds transientInstance) {
		log.debug("saving ConfExhibitExpressneeds instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibitExpressneeds persistentInstance) {
		log.debug("deleting ConfExhibitExpressneeds instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibitExpressneeds findById(java.lang.String id) {
		log.debug("getting ConfExhibitExpressneeds instance with id: " + id);
		try {
			ConfExhibitExpressneeds instance = (ConfExhibitExpressneeds) getSession()
					.get("com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneeds",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibitExpressneeds> findByExample(
			ConfExhibitExpressneeds instance) {
		log.debug("finding ConfExhibitExpressneeds instance by example");
		try {
			List<ConfExhibitExpressneeds> results = (List<ConfExhibitExpressneeds>) getSession()
					.createCriteria(
							"com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneeds")
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
		log.debug("finding ConfExhibitExpressneeds instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibitExpressneeds as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibitExpressneeds> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfExhibitExpressneeds> findByExhibitorId(Object exhibitorId) {
		return findByProperty(EXHIBITOR_ID, exhibitorId);
	}

	public List<ConfExhibitExpressneeds> findByExpressNeed(Object expressNeed) {
		return findByProperty(EXPRESS_NEED, expressNeed);
	}

	public List<ConfExhibitExpressneeds> findByExpressFile(Object expressFile) {
		return findByProperty(EXPRESS_FILE, expressFile);
	}

	public List<ConfExhibitExpressneeds> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfExhibitExpressneeds instances");
		try {
			String queryString = "from ConfExhibitExpressneeds";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibitExpressneeds merge(
			ConfExhibitExpressneeds detachedInstance) {
		log.debug("merging ConfExhibitExpressneeds instance");
		try {
			ConfExhibitExpressneeds result = (ConfExhibitExpressneeds) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibitExpressneeds instance) {
		log.debug("attaching dirty ConfExhibitExpressneeds instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibitExpressneeds instance) {
		log.debug("attaching clean ConfExhibitExpressneeds instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据会议id和展商，查找出该展商是否需要物流的信息
	 * @param meetingId
	 * @param exhibitorId
	 * @return
	 */
	public List<ConfExhibitExpressneeds> findExpressneeds(String meetingId, String exhibitorId) {
		log.debug("finding ConfExhibitExpressneeds instance with meetingId: "
				+ meetingId + ", userId: " + exhibitorId);
		try {
			//"meetingId";exhibitorId
			String queryString = "from ConfExhibitExpressneeds as model where model.meetingId = ? AND model.exhibitorId = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, meetingId);
			queryObject.setParameter(1, exhibitorId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}