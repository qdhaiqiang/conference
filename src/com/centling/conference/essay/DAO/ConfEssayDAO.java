package com.centling.conference.essay.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.essay.entity.ConfEssay;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEssay entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.essay.DAO.ConfEssay
 * @author MyEclipse Persistence Tools
 */

@Repository("confEssayDAO")
public class ConfEssayDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfEssayDAO.class);
	// property constants
	public static final String ESSAY_TITLE = "essayTitle";
	public static final String CREATE_TIME = "createTime";
	public static final String CREATE_REAL_TIME = "createRealTime";
	public static final String LAST_UPDATE_TIME = "lastUpdateTime";
	public static final String LAST_UPDATE_REAL_TIME = "lastUpdateRealTime";
	public static final String AUTHOR = "author";
	public static final String REAL_AUTHOR = "realAuthor";
	public static final String VIEW_COUNT = "viewCount";
	public static final String ESSAY_MENU = "essayMenu";
	public static final String ESSAY_TYPE = "essayType";
	public static final String ESSAY_CONTENT = "essayContent";
	public static final String ESSAY_ABSTRACT = "essayAbstract";
	public static final String ARTICLE_SUMMARY = "articleSummary";
	public static final String ARTICLE_STATE = "articleState";

	public void save(ConfEssay transientInstance) {
		log.debug("saving ConfEssay instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfEssay persistentInstance) {
		log.debug("deleting ConfEssay instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfEssay findById( java.lang.String id) {
        log.debug("getting ConfEssay instance with id: " + id);
        try {
        	String queryString = "from ConfEssay where id = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfEssay)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfEssay> findByExample(ConfEssay instance) {
		log.debug("finding ConfEssay instance by example");
		try {
			List<ConfEssay> results = (List<ConfEssay>) getSession()
					.createCriteria(
							"com.centling.conference.essay.DAO.ConfEssay")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfEssay> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfEssay instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfEssay as model where model."
					+ propertyName + "= ? order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfEssay> findByEssayTitle(Object essayTitle) {
		return findByProperty(ESSAY_TITLE, essayTitle);
	}

	public List<ConfEssay> findByCreateTime(Object createTime) {
		return findByProperty(CREATE_TIME, createTime);
	}

	public List<ConfEssay> findByCreateRealTime(Object createRealTime) {
		return findByProperty(CREATE_REAL_TIME, createRealTime);
	}

	public List<ConfEssay> findByLastUpdateTime(Object lastUpdateTime) {
		return findByProperty(LAST_UPDATE_TIME, lastUpdateTime);
	}

	public List<ConfEssay> findByLastUpdateRealTime(Object lastUpdateRealTime) {
		return findByProperty(LAST_UPDATE_REAL_TIME, lastUpdateRealTime);
	}

	public List<ConfEssay> findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List<ConfEssay> findByRealAuthor(Object realAuthor) {
		return findByProperty(REAL_AUTHOR, realAuthor);
	}

	public List<ConfEssay> findByViewCount(Object viewCount) {
		return findByProperty(VIEW_COUNT, viewCount);
	}

	public List<ConfEssay> findByEssayMenu(Object essayMenu) {
		return findByProperty(ESSAY_MENU, essayMenu);
	}

	public List<ConfEssay> findByEssayType(Object essayType) {
		return findByProperty(ESSAY_TYPE, essayType);
	}

	public List<ConfEssay> findByEssayContent(Object essayContent) {
		return findByProperty(ESSAY_CONTENT, essayContent);
	}

	public List<ConfEssay> findByEssayAbstract(Object essayAbstract) {
		return findByProperty(ESSAY_ABSTRACT, essayAbstract);
	}

	public List<ConfEssay> findByArticleSummary(Object articleSummary) {
		return findByProperty(ARTICLE_SUMMARY, articleSummary);
	}

	public List<ConfEssay> findByArticleState(Object articleState) {
		return findByProperty(ARTICLE_STATE, articleState);
	}

	public List<ConfEssay> findAll() {
		log.debug("finding all ConfEssay instances");
		try {
			String queryString = "from ConfEssay";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfEssay merge(ConfEssay detachedInstance) {
		log.debug("merging ConfEssay instance");
		try {
			ConfEssay result = (ConfEssay) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfEssay instance) {
		log.debug("attaching dirty ConfEssay instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfEssay instance) {
		log.debug("attaching clean ConfEssay instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfEssay> retrieve(PageBean pageBean, String meetingId,ConfEssay confEssay) {
		log.debug("finding all ConfEssay instances");
		try {
			String queryString = "from ConfEssay cet where meetingId=:meetingId";
	        if (confEssay!=null) {
	        	if (StringUtils.isNotEmpty(confEssay.getEssayTitle())) {
	        		queryString+=" and cet.essayTitle like :essayTitle ";
	        	}
	        	if (StringUtils.isNotEmpty(confEssay.getEssayType())) {
	        		queryString+=" and cet.essayType=:essayType";
	        	}
	        }
	        queryString+=" order by cet.essayType,cet.showOrder";
			Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId);
			if (confEssay!=null) {
	        	if (StringUtils.isNotEmpty(confEssay.getEssayTitle())) {
	        		queryObject.setString("essayTitle", "%"+confEssay.getEssayTitle()+"%");
	        	}
	        	if (StringUtils.isNotEmpty(confEssay.getEssayType())) {
	        		queryObject.setString("essayType", confEssay.getEssayType());
	        	}
	        }
			queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count(String meetingId,ConfEssay confEssay) {
		log.debug("finding all ConfEssay instances");
		try {
			String queryString = "select count(*) from ConfEssay where meetingId=:meetingId ";
			if (confEssay!=null) {
	        	if (StringUtils.isNotEmpty(confEssay.getEssayTitle())) {
	        		queryString+=" and essayTitle like :essayTitle ";
	        	}
	        	if (StringUtils.isNotEmpty(confEssay.getEssayType())) {
	        		queryString+=" and essayType=:essayType";
	        	}
	        }
			Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId);
			if (confEssay!=null) {
	        	if (StringUtils.isNotEmpty(confEssay.getEssayTitle())) {
	        		queryObject.setString("essayTitle", "%"+confEssay.getEssayTitle()+"%");
	        	}
	        	if (StringUtils.isNotEmpty(confEssay.getEssayType())) {
	        		queryObject.setString("essayType", confEssay.getEssayType());
	        	}
	        }
			return (Long)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void delete(String id) {
        log.debug("deleting ConfEssay instance");
        try {
        	String sql = "delete from ConfEssay where id in ( :id )";
        	Query queryObject= getSession().createQuery(sql);
        	queryObject.setParameterList("id", id.split(","));
        	queryObject.executeUpdate();
	        
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public void update(ConfEssay instance) {
		log.debug("update ConfEssay instance");
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