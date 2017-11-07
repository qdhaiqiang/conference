package com.centling.conference.dict.category.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.dict.category.entity.ConfDictCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfDictCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.dict.category.entity.ConfDictCategory
 * @author MyEclipse Persistence Tools
 */
@Repository("confDictCategoryDAO")
public class ConfDictCategoryDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfDictCategoryDAO.class);
	// property constants
	public static final String CODE = "code";
	public static final String NAME = "name";

	public void save(ConfDictCategory transientInstance) {
		log.debug("saving ConfDictCategory instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfDictCategory persistentInstance) {
		log.debug("deleting ConfDictCategory instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfDictCategory findById(java.lang.String id) {
		log.debug("getting ConfDictCategory instance with id: " + id);
		try {
			ConfDictCategory instance = (ConfDictCategory) getSession()
					.get("com.centling.conference.dict.category.entity.ConfDictCategory",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfDictCategory> findByExample(ConfDictCategory instance) {
		log.debug("finding ConfDictCategory instance by example");
		try {
			List<ConfDictCategory> results = (List<ConfDictCategory>) getSession()
					.createCriteria(
							"com.centling.conference.dict.category.entity.ConfDictCategory")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfDictCategory> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfDictCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfDictCategory as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfDictCategory> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<ConfDictCategory> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfDictCategory> findAll() {
		log.debug("finding all ConfDictCategory instances");
		try {
			String queryString = "from ConfDictCategory";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfDictCategory merge(ConfDictCategory detachedInstance) {
		log.debug("merging ConfDictCategory instance");
		try {
			ConfDictCategory result = (ConfDictCategory) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfDictCategory instance) {
		log.debug("attaching dirty ConfDictCategory instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfDictCategory instance) {
		log.debug("attaching clean ConfDictCategory instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}