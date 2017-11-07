package com.centling.conference.exhibit.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.exhibit.entity.ConfExhibit;
import com.centling.conference.exhibition.entity.ConfExhibition;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibit.entity.ConfExhibit
 * @author MyEclipse Persistence Tools
 */
@Repository("confExhibitDAO")
public class ConfExhibitDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EXHIBITOR_ID = "exhibitorId";
	public static final String EXHIBIT_COMPANY = "exhibitCompany";
	public static final String EXHIBIT_NAME = "exhibitName";
	public static final String EXHIBIT_SPECIFICATIONS = "exhibitSpecifications";
	public static final String EXHIBIT_MOUNT = "exhibitMount";
	public static final String EXHIBIT_PRICE = "exhibitPrice";
	public static final String EXHIBIT_IMAGE = "exhibitImage";
	public static final String EXHIBIT_INTRO = "exhibitIntro";
	public static final String EXHIBIT_OTHER = "exhibitOther";

	public void save(ConfExhibit transientInstance) {
		log.debug("saving ConfExhibit instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibit persistentInstance) {
		log.debug("deleting ConfExhibit instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibit findById( java.lang.String id) {
        log.debug("getting ConfExhibit instance with exhibitId: " + id);
        try {
        	String queryString = "from ConfExhibit where exhibitId = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfExhibit)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfExhibit> findByExample(ConfExhibit instance) {
		log.debug("finding ConfExhibit instance by example");
		try {
			List<ConfExhibit> results = (List<ConfExhibit>) getSession()
					.createCriteria(
							"com.centling.conference.exhibit.entity.ConfExhibit")
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
		log.debug("finding ConfExhibit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfExhibit as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibition> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}
	
	public List<ConfExhibit> findByExhibitorId(Object exhibitorId) {
		return findByProperty(EXHIBITOR_ID, exhibitorId);
	}

	public List<ConfExhibit> findByExhibitCompany(Object exhibitCompany) {
		return findByProperty(EXHIBIT_COMPANY, exhibitCompany);
	}

	public List<ConfExhibit> findByExhibitName(Object exhibitName) {
		return findByProperty(EXHIBIT_NAME, exhibitName);
	}

	public List<ConfExhibit> findByExhibitSpecifications(
			Object exhibitSpecifications) {
		return findByProperty(EXHIBIT_SPECIFICATIONS, exhibitSpecifications);
	}

	public List<ConfExhibit> findByExhibitMount(Object exhibitMount) {
		return findByProperty(EXHIBIT_MOUNT, exhibitMount);
	}

	public List<ConfExhibit> findByExhibitPrice(Object exhibitPrice) {
		return findByProperty(EXHIBIT_PRICE, exhibitPrice);
	}

	public List<ConfExhibit> findByExhibitImage(Object exhibitImage) {
		return findByProperty(EXHIBIT_IMAGE, exhibitImage);
	}

	public List<ConfExhibit> findByExhibitIntro(Object exhibitIntro) {
		return findByProperty(EXHIBIT_INTRO, exhibitIntro);
	}

	public List<ConfExhibit> findByExhibitOther(Object exhibitOther) {
		return findByProperty(EXHIBIT_OTHER, exhibitOther);
	}

	public List findAll() {
		log.debug("finding all ConfExhibit instances");
		try {
			String queryString = "from ConfExhibit";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibit merge(ConfExhibit detachedInstance) {
		log.debug("merging ConfExhibit instance");
		try {
			ConfExhibit result = (ConfExhibit) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibit instance) {
		log.debug("attaching dirty ConfExhibit instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibit instance) {
		log.debug("attaching clean ConfExhibit instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<ConfExhibit> findExhibitor(String meetingId,String exhibitorId) {
		log.debug("finding ConfExhibit instance with meetingId: " + meetingId
				+ ", exhibitorId: " + exhibitorId);
		try {
			String queryString = "from ConfExhibit as model where model.meetingId = ? AND model.exhibitorId = ? ";
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