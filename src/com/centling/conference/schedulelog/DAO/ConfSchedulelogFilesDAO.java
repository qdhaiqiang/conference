package com.centling.conference.schedulelog.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.schedulelog.entity.ConfSchedulelogFiles;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfSchedulelogFiles entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.schedulelog.entity.ConfSchedulelogFiles
 * @author MyEclipse Persistence Tools
 */
@Repository("confSchedulelogFilesDAO")
public class ConfSchedulelogFilesDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfSchedulelogFilesDAO.class);
	// property constants
	public static final String LOG_ID = "logId";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_PATH = "filePath";
	public static final String MEMO = "memo";

	public void save(ConfSchedulelogFiles transientInstance) {
		log.debug("saving ConfSchedulelogFiles instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSchedulelogFiles persistentInstance) {
		log.debug("deleting ConfSchedulelogFiles instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSchedulelogFiles findById(java.lang.String id) {
		log.debug("getting ConfSchedulelogFiles instance with id: " + id);
		try {
			ConfSchedulelogFiles instance = (ConfSchedulelogFiles) getSession()
					.get("com.centling.conference.schedulelog.entity.ConfSchedulelogFiles",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSchedulelogFiles> findByExample(
			ConfSchedulelogFiles instance) {
		log.debug("finding ConfSchedulelogFiles instance by example");
		try {
			List<ConfSchedulelogFiles> results = (List<ConfSchedulelogFiles>) getSession()
					.createCriteria(
							"com.centling.conference.schedulelog.entity.ConfSchedulelogFiles")
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
		log.debug("finding ConfSchedulelogFiles instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfSchedulelogFiles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSchedulelogFiles> findByLogId(Object logId) {
		return findByProperty(LOG_ID, logId);
	}

	public List<ConfSchedulelogFiles> findByFileName(Object fileName) {
		return findByProperty(FILE_NAME, fileName);
	}

	public List<ConfSchedulelogFiles> findByFilePath(Object filePath) {
		return findByProperty(FILE_PATH, filePath);
	}

	public List<ConfSchedulelogFiles> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfSchedulelogFiles instances");
		try {
			String queryString = "from ConfSchedulelogFiles";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfSchedulelogFiles merge(ConfSchedulelogFiles detachedInstance) {
		log.debug("merging ConfSchedulelogFiles instance");
		try {
			ConfSchedulelogFiles result = (ConfSchedulelogFiles) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSchedulelogFiles instance) {
		log.debug("attaching dirty ConfSchedulelogFiles instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSchedulelogFiles instance) {
		log.debug("attaching clean ConfSchedulelogFiles instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String ids){
		log.debug("deleting ConfSchedulelogFiles instance by ids:"+ids);
		int results = 0;
		try {
			String sql = " DELETE FROM ConfSchedulelogFiles WHERE id IN ( :id) ";
			Query query = getSession().createQuery(sql).setParameterList("id", ids.split(","));
			results = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return results;
	}
}