package com.centling.conference.article.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.article.entity.ConfArticle;
import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;

/**
 * A data access object (DAO) providing persistence and search support for ConfArticle entities. Transaction control of the save(), update() and delete() operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.article.entity.ConfArticle
 * @author MyEclipse Persistence Tools
 */
@Repository("confArticleDAO")
public class ConfArticleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfArticleDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String CONTEXT = "context";

	public void save(ConfArticle transientInstance) {
		log.debug("saving ConfArticle instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfArticle persistentInstance) {
		log.debug("deleting ConfArticle instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfArticle findById( java.lang.String id) {
        log.debug("getting ConfArticle instance with id: " + id);
        try {
        	String queryString = "from ConfArticle where id = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfArticle)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfArticle> findByExample(ConfArticle instance) {
		log.debug("finding ConfArticle instance by example");
		try {
			List<ConfArticle> results = (List<ConfArticle>) getSession().createCriteria("com.centling.conference.article.entity.ConfArticle").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfArticle instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfArticle as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfArticle> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<ConfArticle> findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all ConfArticle instances");
		try {
			String queryString = "from ConfArticle";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfArticle merge(ConfArticle detachedInstance) {
		log.debug("merging ConfArticle instance");
		try {
			ConfArticle result = (ConfArticle) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfArticle instance) {
		log.debug("attaching dirty ConfArticle instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfArticle instance) {
		log.debug("attaching clean ConfArticle instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfArticle> retrieve(PageBean pageBean) {
		log.debug("finding all ConfArticle instances");
		try {
			String queryString = "from ConfArticle cet order by cet.id";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count() {
		log.debug("finding all ConfArticle instances");
		try {
			String queryString = "select count(*) from ConfArticle";
	        return (Long)(getSession().createQuery(queryString).uniqueResult());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void delete(String id) {
        log.debug("deleting ConfArticle instance");
        try {
        	String sql = "delete from ConfArticle where id in ( :id )";
        	Query queryObject= getSession().createQuery(sql);
        	queryObject.setParameterList("id", id.split(","));
        	queryObject.executeUpdate();
	        
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public void update(ConfArticle instance) {
		log.debug("update ConfArticle instance");
		try {
			getSession().clear();
			getSession().update(instance);
			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
}