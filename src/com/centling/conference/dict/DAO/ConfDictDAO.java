package com.centling.conference.dict.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.dict.entity.ConfDict;
import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfDict entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.dict.entity.ConfDict
 * @author MyEclipse Persistence Tools
 */
@Repository("confDictDAO")
public class ConfDictDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfDictDAO.class);
	// property constants
	public static final String CATEGORY_ID = "categoryId";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String SHOW_ORDER = "showOrder";

	public void save(ConfDict transientInstance) {
		log.debug("saving ConfDict instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfDict persistentInstance) {
		log.debug("deleting ConfDict instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfDict findById(java.lang.String id) {
		log.debug("getting ConfDict instance with id: " + id);
		try {
			ConfDict instance = (ConfDict) getSession().get(
					"com.centling.conference.dict.entity.ConfDict", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfDict> findByExample(ConfDict instance) {
		log.debug("finding ConfDict instance by example");
		try {
			List<ConfDict> results = (List<ConfDict>) getSession()
					.createCriteria(
							"com.centling.conference.dict.entity.ConfDict")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfDict> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfDict instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfDict as model where model."
					+ propertyName + "= ?";
			if ("nation".equals(propertyName)) {
				queryString += " order by name";
			} else {
				queryString += " order by showOrder";
			}
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<ConfDict> findByCategoryId(Object categoryId) {
		return findByProperty(CATEGORY_ID, categoryId);
	}

	public List<ConfDict> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<ConfDict> findByName(Object name) {
		return findByProperty(NAME, name);
	}
	
	public List<ConfDict> findByShowOrder(Object name) {
		return findByProperty(SHOW_ORDER, name);
	}
	
	public List findAll() {
		log.debug("finding all ConfDict instances");
		try {
			String queryString = "from ConfDict";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfDict merge(ConfDict detachedInstance) {
		log.debug("merging ConfDict instance");
		try {
			ConfDict result = (ConfDict) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfDict instance) {
		log.debug("attaching dirty ConfDict instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfDict instance) {
		log.debug("attaching clean ConfDict instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//获得工作人员或者嘉宾字典表
    public List<ConfDict> findUserTypeByCodes(String codes) {
        log.debug("finding ConfDict");
        try {
            String queryString = "from ConfDict as model where model.categoryId = '1'";
            if (!codes.equals("")) {
                queryString += " and model.code in (:codes)";
            }
            queryString += " order by showOrder";
            Query queryObject = getSession().createQuery(queryString);
            if (!codes.equals("")) {
                queryObject.setParameterList("codes", codes.split(","));
            }
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }
}