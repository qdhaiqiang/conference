package com.centling.conference.sysrole.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.sysrole.entity.ConfSysRole;

/**
 	* A data access object (DAO) providing persistence and search support for ConfSysRole entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.sysrole.entity.ConfSysRole
  * @author MyEclipse Persistence Tools 
 */
@Repository("confSysRoleDAO")
public class ConfSysRoleDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfSysRoleDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String CODE = "code";



    
    public void save(ConfSysRole transientInstance) {
        log.debug("saving ConfSysRole instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfSysRole persistentInstance) {
        log.debug("deleting ConfSysRole instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfSysRole findById( java.lang.String id) {
        log.debug("getting ConfSysRole instance with id: " + id);
        try {
            ConfSysRole instance = (ConfSysRole) getSession()
                    .get("com.centling.conference.sysrole.entity.ConfSysRole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfSysRole> findByExample(ConfSysRole instance) {
        log.debug("finding ConfSysRole instance by example");
        try {
            List<ConfSysRole> results = (List<ConfSysRole>) getSession()
                    .createCriteria("com.centling.conference.sysrole.entity.ConfSysRole")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ConfSysRole instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfSysRole as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfSysRole> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<ConfSysRole> findByCode(Object code
	) {
		return findByProperty(CODE, code
		);
	}
	

	public List<ConfSysRole> findAll() {
		log.debug("finding all ConfSysRole instances");
		try {
			String queryString = "from ConfSysRole";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfSysRole merge(ConfSysRole detachedInstance) {
        log.debug("merging ConfSysRole instance");
        try {
            ConfSysRole result = (ConfSysRole) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfSysRole instance) {
        log.debug("attaching dirty ConfSysRole instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfSysRole instance) {
        log.debug("attaching clean ConfSysRole instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public List<ConfSysRole> retrieve(PageBean pageBean) {
		log.debug("finding all ConfSysRole instances");
        try {
            String queryString = "from ConfSysRole";
             Query queryObject = getSession().createQuery(queryString)
            		 .setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public Long count() {
		log.debug("finding all ConfSysRole count");
        try {
            String queryString = "select count(1) from ConfSysRole";
            return (Long)(getSession().createQuery(queryString).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public void update(ConfSysRole confSysRole) {
		log.debug("updating ConfSysRole instance");
        try {
            getSession().update(confSysRole);
            log.debug("upate successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
	}
}