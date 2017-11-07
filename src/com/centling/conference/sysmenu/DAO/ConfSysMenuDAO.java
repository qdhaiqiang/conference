package com.centling.conference.sysmenu.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.sysmenu.entity.ConfSysMenu;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfSysMenu entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.sysmenu.entity.ConfSysMenu
 * @author MyEclipse Persistence Tools
 */
@Repository("confSysMenuDAO")
public class ConfSysMenuDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfSysMenuDAO.class);
	// property constants
	public static final String ICON = "icon";
	public static final String PID = "pid";
	public static final String NAME = "name";
	public static final String LEVEL = "level";
	public static final String MENU_URL = "menuUrl";
	public static final String SHOW_ORDER = "showOrder";

	public void save(ConfSysMenu transientInstance) {
		log.debug("saving ConfSysMenu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSysMenu persistentInstance) {
		log.debug("deleting ConfSysMenu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSysMenu findById(java.lang.String id) {
		log.debug("getting ConfSysMenu instance with id: " + id);
		try {
			ConfSysMenu instance = (ConfSysMenu) getSession().get(
					"com.centling.conference.sysmenu.entity.ConfSysMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSysMenu> findByExample(ConfSysMenu instance) {
		log.debug("finding ConfSysMenu instance by example");
		try {
			List<ConfSysMenu> results = (List<ConfSysMenu>) getSession()
					.createCriteria(
							"com.centling.conference.sysmenu.entity.ConfSysMenu")
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
		log.debug("finding ConfSysMenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfSysMenu as model where model."
					+ propertyName + "= ? order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSysMenu> findByIcon(Object icon) {
		return findByProperty(ICON, icon);
	}

	public List<ConfSysMenu> findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List<ConfSysMenu> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfSysMenu> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List<ConfSysMenu> findByMenuUrl(Object menuUrl) {
		return findByProperty(MENU_URL, menuUrl);
	}

	public List<ConfSysMenu> findByShowOrder(Object showOrder) {
		return findByProperty(SHOW_ORDER, showOrder);
	}

	public List<ConfSysMenu> findAll() {
		log.debug("finding all ConfSysMenu instances");
		try {
			String queryString = "from ConfSysMenu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<ConfSysMenu> findFirstMenu() {
		log.debug("finding Level1 ConfSysMenu instances");
		try {
			String queryString = "from ConfSysMenu where pid is null or pid='' order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public ConfSysMenu merge(ConfSysMenu detachedInstance) {
		log.debug("merging ConfSysMenu instance");
		try {
			ConfSysMenu result = (ConfSysMenu) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSysMenu instance) {
		log.debug("attaching dirty ConfSysMenu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSysMenu instance) {
		log.debug("attaching clean ConfSysMenu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void update(ConfSysMenu confSysMenu) {
		log.debug("updating ConfSysMenu instance");
		try {
			getSession().update(confSysMenu);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
}