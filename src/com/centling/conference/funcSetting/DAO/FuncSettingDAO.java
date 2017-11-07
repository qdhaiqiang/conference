package com.centling.conference.funcSetting.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.funcSetting.entity.ConfFuncSetting;

/**
 * 
 * 功能设置for手机APP
 * 
 */

@Repository("funcSettingDAO")
public class FuncSettingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FuncSettingDAO.class);

	public void save(ConfFuncSetting transientInstance) {
		log.debug("saving ConfFuncSetting instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfFuncSetting persistentInstance) {
		log.debug("deleting ConfFuncSetting instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfFuncSetting findById(java.lang.String id) {
		log.debug("getting ConfFuncSetting instance with id: " + id);
		try {
			ConfFuncSetting instance = (ConfFuncSetting) getSession()
					.get("com.centling.conference.funcSetting.entity.ConfFuncSetting",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfFuncSetting> findByExample(ConfFuncSetting instance) {
		log.debug("finding ConfFuncSetting instance by example");
		try {
			List<ConfFuncSetting> results = (List<ConfFuncSetting>) getSession()
					.createCriteria(
							"com.centling.conference.funcSetting.entity.ConfFuncSetting")
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
		log.debug("finding ConfFuncSetting instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfFuncSetting as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all ConfFuncSetting instances");
		try {
			String queryString = "from ConfFuncSetting";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfFuncSetting merge(ConfFuncSetting detachedInstance) {
		log.debug("merging ConfFuncSetting instance");
		try {
			ConfFuncSetting result = (ConfFuncSetting) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfFuncSetting instance) {
		log.debug("attaching dirty ConfFuncSetting instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfFuncSetting instance) {
		log.debug("attaching clean ConfFuncSetting instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 按照显示顺序查询
	 * @return
	 */
	public List findAllByOrder(String meetingId) {
		log.debug("finding all ConfFuncSetting instances");
		try {
			String queryString = "from ConfFuncSetting as model where model.meetingId= ? order by showOrder asc ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, meetingId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 按照显示顺序查询生效的功能
	 * @return
	 */
	public List findFuncByOrder(String meetingId) {
		log.debug("finding all ConfFuncSetting instances");
		try {
			String queryString = "from ConfFuncSetting as model where model.meetingId= ? and model.status =? order by showOrder asc ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, meetingId);
			queryObject.setParameter(1, 1);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}