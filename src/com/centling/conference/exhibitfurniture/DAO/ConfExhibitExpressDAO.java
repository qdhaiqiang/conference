package com.centling.conference.exhibitfurniture.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibitExpress entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress
 * @author MyEclipse Persistence Tools
 */

@Repository("confExhibitExpressDAO")
public class ConfExhibitExpressDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitExpressDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EXPRESS_COMPANY = "expressCompany";
	public static final String EXPRESS_ORDER = "expressOrder";
	public static final String EXPRESS_CONTACTS = "expressContacts";
	public static final String MEMO = "memo";

	public void save(ConfExhibitExpress transientInstance) {
		log.debug("saving ConfExhibitExpress instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibitExpress persistentInstance) {
		log.debug("deleting ConfExhibitExpress instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibitExpress findById(java.lang.String id) {
		log.debug("getting ConfExhibitExpress instance with id: " + id);
		try {
			ConfExhibitExpress instance = (ConfExhibitExpress) getSession()
					.get("com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibitExpress> findByExample(ConfExhibitExpress instance) {
		log.debug("finding ConfExhibitExpress instance by example");
		try {
			List<ConfExhibitExpress> results = (List<ConfExhibitExpress>) getSession()
					.createCriteria(
							"com.centling.conference.exhibitfurniture.entity.ConfExhibitExpress")
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
		log.debug("finding ConfExhibitExpress instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibitExpress as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibitExpress> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfExhibitExpress> findByExpressCompany(Object expressCompany) {
		return findByProperty(EXPRESS_COMPANY, expressCompany);
	}

	public List<ConfExhibitExpress> findByExpressOrder(Object expressOrder) {
		return findByProperty(EXPRESS_ORDER, expressOrder);
	}

	public List<ConfExhibitExpress> findByExpressContacts(Object expressContacts) {
		return findByProperty(EXPRESS_CONTACTS, expressContacts);
	}

	public List<ConfExhibitExpress> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfExhibitExpress instances");
		try {
			String queryString = "from ConfExhibitExpress";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibitExpress merge(ConfExhibitExpress detachedInstance) {
		log.debug("merging ConfExhibitExpress instance");
		try {
			ConfExhibitExpress result = (ConfExhibitExpress) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibitExpress instance) {
		log.debug("attaching dirty ConfExhibitExpress instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibitExpress instance) {
		log.debug("attaching clean ConfExhibitExpress instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<ConfExhibitExpress> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfExhibitExpress instances");
        try {
            String queryString = "from ConfExhibitExpress where meetingId=?";
             Query queryObject = getSession().createQuery(queryString).setString(0, meetingId)
            		 .setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public Long count(String meetingId) {
		log.debug("finding all ConfExhibitExpress count");
        try {
            String queryString = "select count(1) from ConfExhibitExpress where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public void update(ConfExhibitExpress confExhibitExpress) {
		log.debug("updating ConfExhibitExpress instance");
		try {
			getSession().update(confExhibitExpress);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
}