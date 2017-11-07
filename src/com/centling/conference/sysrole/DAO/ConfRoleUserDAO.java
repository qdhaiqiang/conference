package com.centling.conference.sysrole.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.sysrole.entity.ConfRoleUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfRoleUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.sysrole.entity.ConfRoleUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confRoleUserDAO")
public class ConfRoleUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfRoleUserDAO.class);
	// property constants
	public static final String ROLE_ID = "roleId";
	public static final String USER_ID = "userId";

	public void save(ConfRoleUser transientInstance) {
		log.debug("saving ConfRoleUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfRoleUser persistentInstance) {
		log.debug("deleting ConfRoleUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfRoleUser findById(java.lang.String id) {
		log.debug("getting ConfRoleUser instance with id: " + id);
		try {
			ConfRoleUser instance = (ConfRoleUser) getSession().get(
					"com.centling.conference.sysrole.entity.ConfRoleUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfRoleUser> findByExample(ConfRoleUser instance) {
		log.debug("finding ConfRoleUser instance by example");
		try {
			List<ConfRoleUser> results = (List<ConfRoleUser>) getSession()
					.createCriteria(
							"com.centling.conference.sysrole.entity.ConfRoleUser")
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
		log.debug("finding ConfRoleUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfRoleUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfRoleUser> findByRoleId(Object roleId) {
		return findByProperty(ROLE_ID, roleId);
	}

	public List<ConfRoleUser> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all ConfRoleUser instances");
		try {
			String queryString = "from ConfRoleUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfRoleUser merge(ConfRoleUser detachedInstance) {
		log.debug("merging ConfRoleUser instance");
		try {
			ConfRoleUser result = (ConfRoleUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfRoleUser instance) {
		log.debug("attaching dirty ConfRoleUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfRoleUser instance) {
		log.debug("attaching clean ConfRoleUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByUserId(String sysUserId) {
		log.debug("deleting ConfRoleUser by userId");
		try {
			String delSql = "delete from ConfRoleUser where userId=?";
			getSession().createQuery(delSql).setString(0, sysUserId).executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	//通过角色Id获取用户一览表
    public List<Map> getUserListByRoleId (String roleId) {
        log.debug("finding ConfRoleUser instances");
        String sqlString = "select cru.id,cru.user_id,csu.name  from conf_role_user cru" +
                " inner join conf_sys_role csr on csr.id = cru.role_id " +
                " left join conf_sys_user csu on csu.id = cru.user_id " + 
                " where csr.id = :roleId";
        Query query = getSession().createSQLQuery(sqlString).setString("roleId", roleId);
        return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
}