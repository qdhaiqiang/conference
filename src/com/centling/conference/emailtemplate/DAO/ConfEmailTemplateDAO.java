package com.centling.conference.emailtemplate.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.emailtemplate.entity.ConfEmailTemplate;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEmailTemplate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.emailtemplate.DAO.ConfEmailTemplate
 * @author MyEclipse Persistence Tools
 */

@Repository("confEmailTemplateDAO")
public class ConfEmailTemplateDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfEmailTemplateDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String NAME = "name";
	public static final String CONTENT = "content";

	public void save(ConfEmailTemplate transientInstance) {
		log.debug("saving ConfEmailTemplate instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfEmailTemplate persistentInstance) {
		log.debug("deleting ConfEmailTemplate instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfEmailTemplate findById( java.lang.String id) {
        log.debug("getting ConfEmailTemplate instance with id: " + id);
        try {
        	String queryString = "from ConfEmailTemplate where id = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfEmailTemplate)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfEmailTemplate> findByExample(ConfEmailTemplate instance) {
		log.debug("finding ConfEmailTemplate instance by example");
		try {
			List<ConfEmailTemplate> results = (List<ConfEmailTemplate>) getSession()
					.createCriteria(
							"com.centling.conference.emailtemplate.DAO.ConfEmailTemplate")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfEmailTemplate> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfEmailTemplate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfEmailTemplate as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfEmailTemplate> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}
	
	public List<ConfEmailTemplate> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfEmailTemplate> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all ConfEmailTemplate instances");
		try {
			String queryString = "from ConfEmailTemplate";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfEmailTemplate merge(ConfEmailTemplate detachedInstance) {
		log.debug("merging ConfEmailTemplate instance");
		try {
			ConfEmailTemplate result = (ConfEmailTemplate) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfEmailTemplate instance) {
		log.debug("attaching dirty ConfEmailTemplate instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfEmailTemplate instance) {
		log.debug("attaching clean ConfEmailTemplate instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfEmailTemplate> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfEmailTemplate instances");
		try {
			String queryString = "from ConfEmailTemplate cet where cet.meetingId=:meetingId order by cet.name";
	         Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count(String meetingId) {
		log.debug("finding all ConfEmailTemplate instances");
		try {
			String queryString = "select count(*) from ConfEmailTemplate where meetingId=:meetingId";
	        return (Long)(getSession().createQuery(queryString).setString("meetingId", meetingId).uniqueResult());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void delete(String id) {
        log.debug("deleting ConfEmailTemplate instance");
        try {
        	String sql = "delete from ConfEmailTemplate where id in ( :id )";
        	Query queryObject= getSession().createQuery(sql);
        	queryObject.setParameterList("id", id.split(","));
        	queryObject.executeUpdate();
	        
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public void update(ConfEmailTemplate instance) {
		log.debug("update ConfEmailTemplate instance");
		try {
			getSession().clear();
			getSession().update(instance);
			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
	
	public List<Object[]> findByUserTypeAndName(String name, String userType) {
		log.debug("findByUserTypeAndName");
		try {
		    Session session =  getSession();
			String querySql = "SELECT title,template_name FROM conf_email_template WHERE (user_type LIKE ? OR user_type LIKE ? OR user_type LIKE ? OR user_type=?) AND NAME=?";
			List<Object[]> resultList = session.createSQLQuery(querySql).setString(0, userType+",%")
					.setString(1, "%,"+userType).setString(2, "%,"+userType+",%").setString(3, userType).setString(4, name).list();
			session.flush();
			return resultList;
		} catch (RuntimeException re) {
			log.error("findByUserTypeAndName failed", re);
			throw re;
		}
	}
}