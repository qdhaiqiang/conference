package com.centling.conference.exhibitrent.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.exhibitrent.entity.ConfExhibitRent;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibitRent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibitrent.entity.ConfExhibitRent
 * @author MyEclipse Persistence Tools
 */

@Repository("confExhibitRentDAO")
public class ConfExhibitRentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitRentDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EXHIBITOR_ID = "exhibitorId";
	public static final String FURNITURE_ID = "furnitureId";
	public static final String RENT_MOUNT = "rentMount";
	public static final String RENT_CHARGE = "rentCharge";
	public static final String RENT_CHARGE_EN = "rentChargeEn";
	public static final String MEMO = "memo";

	public void save(ConfExhibitRent transientInstance) {
		log.debug("saving ConfExhibitRent instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibitRent persistentInstance) {
		log.debug("deleting ConfExhibitRent instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibitRent findById(java.lang.String id) {
		log.debug("getting ConfExhibitRent instance with id: " + id);
		try {
			ConfExhibitRent instance = (ConfExhibitRent) getSession()
					.get("com.centling.conference.exhibitrent.entity.ConfExhibitRent",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibitRent> findByExample(ConfExhibitRent instance) {
		log.debug("finding ConfExhibitRent instance by example");
		try {
			List<ConfExhibitRent> results = (List<ConfExhibitRent>) getSession()
					.createCriteria(
							"com.centling.conference.exhibitrent.entity.ConfExhibitRent")
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
		log.debug("finding ConfExhibitRent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibitRent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibitRent> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfExhibitRent> findByExhibitorId(Object exhibitorId) {
		return findByProperty(EXHIBITOR_ID, exhibitorId);
	}
	
	public List<ConfExhibitRent> findByFurnitureId(Object furnitureId) {
		return findByProperty(FURNITURE_ID, furnitureId);
	}

	public List<ConfExhibitRent> findByRentMount(Object rentMount) {
		return findByProperty(RENT_MOUNT, rentMount);
	}

	public List<ConfExhibitRent> findByRentCharge(Object rentCharge) {
		return findByProperty(RENT_CHARGE, rentCharge);
	}
	
	public List<ConfExhibitRent> findByRentChargeEn(Object rentChargeEn) {
		return findByProperty(RENT_CHARGE_EN, rentChargeEn);
	}

	public List<ConfExhibitRent> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfExhibitRent instances");
		try {
			String queryString = "from ConfExhibitRent";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibitRent merge(ConfExhibitRent detachedInstance) {
		log.debug("merging ConfExhibitRent instance");
		try {
			ConfExhibitRent result = (ConfExhibitRent) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibitRent instance) {
		log.debug("attaching dirty ConfExhibitRent instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibitRent instance) {
		log.debug("attaching clean ConfExhibitRent instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<ConfExhibitRent> findAllRent(String meetingId,String exhibitorId) {
		log.debug("finding ConfExhibitRent instance with meetingId: "
				+ meetingId + ", exhibitorId: " + exhibitorId);
		try {
			String queryString = "from ConfExhibitRent as model where model.meetingId = ? AND model.exhibitorId = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, meetingId);
			queryObject.setParameter(1, exhibitorId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property meetingId,exhibitorId failed", re);
			throw re;
		}
	}
}