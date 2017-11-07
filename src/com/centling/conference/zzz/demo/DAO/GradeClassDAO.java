package com.centling.conference.zzz.demo.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.zzz.demo.entity.GradeClass;

/**
 * A data access object (DAO) providing persistence and search support for
 * GradeClass entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.zzz.demo.entity.GradeClass
 * @author MyEclipse Persistence Tools
 */


@Repository("gradeClassDAO")
public class GradeClassDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GradeClassDAO.class);
	// property constants
	public static final String CLASS_NAME = "className";

	public void save(GradeClass transientInstance) {
		log.debug("saving GradeClass instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GradeClass persistentInstance) {
		log.debug("deleting GradeClass instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GradeClass findById(java.lang.String id) {
		log.debug("getting GradeClass instance with id: " + id);
		try {
			GradeClass instance = (GradeClass) getSession().get(
					"com.centling.conference.zzz.demo.entity.GradeClass", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<GradeClass> findByExample(GradeClass instance) {
		log.debug("finding GradeClass instance by example");
		try {
			List<GradeClass> results = (List<GradeClass>) getSession()
					.createCriteria(
							"com.centling.conference.zzz.demo.entity.GradeClass")
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
		log.debug("finding GradeClass instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GradeClass as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<GradeClass> findByClassName(Object className) {
		return findByProperty(CLASS_NAME, className);
	}

	public List findAll() {
		log.debug("finding all GradeClass instances");
		try {
			String queryString = "from GradeClass";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GradeClass merge(GradeClass detachedInstance) {
		log.debug("merging GradeClass instance");
		try {
			GradeClass result = (GradeClass) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GradeClass instance) {
		log.debug("attaching dirty GradeClass instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GradeClass instance) {
		log.debug("attaching clean GradeClass instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}