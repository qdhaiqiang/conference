package com.centling.conference.vericode.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.vericode.entity.ConfEmailVericode;

/**
 * A data access object (DAO) providing persistence and search support for ConfEmailVericode entities. Transaction control of the save(), update() and delete() operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how to configure it for
 * the desired type of transaction control.
 * 
 * @see com.centling.conference.vericode.entity.ConfEmailVericode
 * @author MyEclipse Persistence Tools
 */
@Repository("confEmailVericodeDAO")
public class ConfEmailVericodeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfEmailVericodeDAO.class);
	// property constants
	public static final String EMAIL = "email";
	public static final String VERI_CODE = "veriCode";

	public void save(ConfEmailVericode transientInstance) {
		log.debug("saving ConfEmailVericode instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfEmailVericode persistentInstance) {
		log.debug("deleting ConfEmailVericode instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfEmailVericode findById(java.lang.String id) {
		log.debug("getting ConfEmailVericode instance with id: " + id);
		try {
			ConfEmailVericode instance = (ConfEmailVericode) getSession().get("com.centling.conference.vericode.entity.ConfEmailVericode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfEmailVericode> findByExample(ConfEmailVericode instance) {
		log.debug("finding ConfEmailVericode instance by example");
		try {
			List<ConfEmailVericode> results = (List<ConfEmailVericode>) getSession().createCriteria("com.centling.conference.vericode.entity.ConfEmailVericode").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfEmailVericode> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfEmailVericode instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfEmailVericode as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfEmailVericode> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<ConfEmailVericode> findByVeriCode(Object veriCode) {
		return findByProperty(VERI_CODE, veriCode);
	}

	public List findAll() {
		log.debug("finding all ConfEmailVericode instances");
		try {
			String queryString = "from ConfEmailVericode";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfEmailVericode merge(ConfEmailVericode detachedInstance) {
		log.debug("merging ConfEmailVericode instance");
		try {
			ConfEmailVericode result = (ConfEmailVericode) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfEmailVericode instance) {
		log.debug("attaching dirty ConfEmailVericode instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfEmailVericode instance) {
		log.debug("attaching clean ConfEmailVericode instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<ConfEmailVericode> retrieve(PageBean pageBean, String email) {
		log.debug("finding all ConfEmailVericode instances");
        try {
            String queryString = "from ConfEmailVericode ";
            if (!StringUtils.isEmpty(email)) {
            	queryString += "where email like ?";
            }
            queryString += "order by email";
            Query queryObject = getSession().createQuery(queryString);
            if (!StringUtils.isEmpty(email)) {
            	queryObject.setString(0, "%"+email+"%");
            }
            	queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public Long count(String email) {
		log.debug("finding all ConfEmailVericode count");
        try {
            String queryString = "select count(1) from ConfEmailVericode ";
            if (!StringUtils.isEmpty(email)) {
            	queryString += "where email like ?";
            }
            Query queryObject = getSession().createQuery(queryString);
            if (!StringUtils.isEmpty(email)) {
            	queryObject.setString(0, "%"+email+"%");
            }
            return (Long)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
}