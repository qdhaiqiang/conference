package com.centling.conference.dyn.value.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.dyn.value.entity.ConfDynValue;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfDynValue entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.dyn.value.entity.ConfDynValue
 * @author MyEclipse Persistence Tools
 */
@Repository("confDynValueDAO")
public class ConfDynValueDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfDynValueDAO.class);
	// property constants
	public static final String FIELD_ID = "fieldId";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String MEETING_ID = "meetingId";
	public static final String USER_TYPE = "userType";
	public static final String USER_ID = "userId";
	public static final String REQUIRED = "required";
	public static final String DESCRIPTION = "description";
	public static final String OPTIONS = "options";
	public static final String VALUE = "value";

	public void save(ConfDynValue transientInstance) {
		log.debug("saving ConfDynValue instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfDynValue persistentInstance) {
		log.debug("deleting ConfDynValue instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfDynValue findById(java.lang.String id) {
		log.debug("getting ConfDynValue instance with id: " + id);
		try {
			ConfDynValue instance = (ConfDynValue) getSession()
					.get("com.centling.conference.dyn.value.entity.ConfDynValue",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfDynValue> findByExample(ConfDynValue instance) {
		log.debug("finding ConfDynValue instance by example");
		try {
			List<ConfDynValue> results = (List<ConfDynValue>) getSession()
					.createCriteria(
							"com.centling.conference.dyn.value.entity.ConfDynValue")
							.addOrder(Order.asc("orderNum"))
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
		log.debug("finding ConfDynValue instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfDynValue as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfDynValue> findByFieldId(Object fieldId) {
		return findByProperty(FIELD_ID, fieldId);
	}

	public List<ConfDynValue> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfDynValue> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ConfDynValue> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfDynValue> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<ConfDynValue> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfDynValue> findByRequired(Object required) {
		return findByProperty(REQUIRED, required);
	}

	public List<ConfDynValue> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<ConfDynValue> findByOptions(Object options) {
		return findByProperty(OPTIONS, options);
	}

	public List<ConfDynValue> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all ConfDynValue instances");
		try {
			String queryString = "from ConfDynValue";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfDynValue merge(ConfDynValue detachedInstance) {
		log.debug("merging ConfDynValue instance");
		try {
			ConfDynValue result = (ConfDynValue) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfDynValue instance) {
		log.debug("attaching dirty ConfDynValue instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfDynValue instance) {
		log.debug("attaching clean ConfDynValue instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteValue(String userId, String meetingId) {
		log.debug("delete confDynValue");
        try {
            String queryString = "delete from conf_dyn_value where user_id = ? and meeting_id=?";
            getSession().createSQLQuery(queryString).setString(0, userId).setString(1, meetingId).executeUpdate();
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
	}
}