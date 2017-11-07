package com.centling.conference.essaytype.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.essaytype.entity.ConfEssayType;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEssayType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.essaytype.DAO.ConfEssayType
 * @author MyEclipse Persistence Tools
 */

@Repository("confEssayTypeDAO")
public class ConfEssayTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfEssayTypeDAO.class);
	// property constants
	public static final String TYPE_NAME = "typeName";
	public static final String PARENT_MENU = "parentMenu";
	public static final String ESSAY_NUM = "essayNum";

	public void save(ConfEssayType transientInstance) {
		log.debug("saving ConfEssayType instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfEssayType persistentInstance) {
		log.debug("deleting ConfEssayType instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfEssayType findById( java.lang.String id) {
        log.debug("getting ConfEssayType instance with id: " + id);
        try {
        	String queryString = "from ConfEssayType where id = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfEssayType)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfEssayType> findByExample(ConfEssayType instance) {
		log.debug("finding ConfEssayType instance by example");
		try {
			List<ConfEssayType> results = (List<ConfEssayType>) getSession()
					.createCriteria(
							"com.centling.conference.essaytype.DAO.ConfEssayType")
					.add(create(instance))
					.addOrder(Order.asc("showOrder")).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfEssayType> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfEssayType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfEssayType as model where model."
					+ propertyName + "= ? order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfEssayType> findByTypeName(Object typeName) {
		return findByProperty(TYPE_NAME, typeName);
	}

	public List<ConfEssayType> findByParentMenu(Object parentMenu) {
		return findByProperty(PARENT_MENU, parentMenu);
	}

	public List<ConfEssayType> findByEssayNum(Object essayNum) {
		return findByProperty(ESSAY_NUM, essayNum);
	}

	public List<ConfEssayType> findAll() {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "from ConfEssayType order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConfEssayType> findParentMenu(String meetingId) {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "from ConfEssayType as cet where cet.meetingId='" + meetingId + "' and cet.parentMenu = '0' order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConfEssayType> findMenu2(String meetingId) {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "from ConfEssayType as cet where cet.meetingId='" + meetingId + "' and cet.parentMenu <> '0' order by showOrder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfEssayType merge(ConfEssayType detachedInstance) {
		log.debug("merging ConfEssayType instance");
		try {
			ConfEssayType result = (ConfEssayType) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfEssayType instance) {
		log.debug("attaching dirty ConfEssayType instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfEssayType instance) {
		log.debug("attaching clean ConfEssayType instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfEssayType> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "from ConfEssayType cet where cet.meetingId=? and cet.parentMenu = '0' order by cet.showOrder";
	         Query queryObject = getSession().createQuery(queryString).setString(0, meetingId).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfEssayType> retrieve2(PageBean pageBean, String meetingId,ConfEssayType confEssayType) {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "from ConfEssayType cet where cet.meetingId=:meetingId and cet.parentMenu <> '0' ";
	        if (confEssayType!=null) {
	        	if (StringUtils.isNotEmpty(confEssayType.getTypeName())) {
	        		queryString += " and typeName like :typeName ";
	        	}
	        	if (StringUtils.isNotEmpty(confEssayType.getParentMenu())) {
	        		queryString += " and parentMenu=:parentMenu ";
	        	}
	        }
	        queryString += "order by cet.parentMenu,cet.showOrder";
			Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId);
			if (confEssayType!=null) {
	        	if (StringUtils.isNotEmpty(confEssayType.getTypeName())) {
	        		queryObject.setString("typeName", "%"+confEssayType.getTypeName()+"%");
	        	}
	        	if (StringUtils.isNotEmpty(confEssayType.getParentMenu())) {
	        		queryObject.setString("parentMenu", confEssayType.getParentMenu());
	        	}
	        }
			queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count(String meetingId,String flag,ConfEssayType confEssayType) {
		log.debug("finding all ConfEssayType instances");
		try {
			String queryString = "select count(*) from ConfEssayType where meetingId=:meetingId ";
			if (StringUtils.equals("0", flag)) {
				queryString += " and parentMenu='0'";
			} else {
				queryString += " and parentMenu<>'0'";
			}
			if (confEssayType!=null) {
	        	if (StringUtils.isNotEmpty(confEssayType.getTypeName())) {
	        		queryString += " and typeName like :typeName ";
	        	}
	        	if (StringUtils.isNotEmpty(confEssayType.getParentMenu())) {
	        		queryString += " and parentMenu=:parentMenu ";
	        	}
	        }
			Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId);
			if (confEssayType!=null) {
	        	if (StringUtils.isNotEmpty(confEssayType.getTypeName())) {
	        		queryObject.setString("typeName", "%"+confEssayType.getTypeName()+"%");
	        	}
	        	if (StringUtils.isNotEmpty(confEssayType.getParentMenu())) {
	        		queryObject.setString("parentMenu", confEssayType.getParentMenu());
	        	}
	        }
			return (Long)(queryObject.uniqueResult());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void delete(String id) {
        log.debug("deleting ConfEssayType instance");
        try {
        	String sql = "delete from ConfEssayType where id in ( :id )";
        	Query queryObject= getSession().createQuery(sql);
        	queryObject.setParameterList("id", id.split(","));
        	queryObject.executeUpdate();
	        
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	
	public void update(ConfEssayType instance) {
		log.debug("update ConfEssayType instance");
		try {
			getSession().clear();
			getSession().update(instance);
			
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public List<Map<String, String>> getEssayName(String essayId) {
		log.debug("finding all EssayName");
		try {
			String queryString = "select a.essay_title, a.essay_title_en,b.type_name,b.id,b.type_name_en from conf_essay a left join conf_essay_type b on a.essay_type=b.id where a.id=?";
	        return getSession().createSQLQuery(queryString).setString(0, essayId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Map<String, Object>> findNews() {
		log.debug("finding ConfEssayType News");
		try {
			String queryString = "SELECT * FROM conf_essay a WHERE EXISTS (SELECT 1 FROM conf_essay_type WHERE a.essay_type=id AND parent_menu='40281281487990db0148799da9eb0005') order by show_order";
			Query queryObject = getSession().createSQLQuery(queryString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Map<String, Object>> findGuide() {
		log.debug("finding ConfEssayType Guide");
		try {
			String queryString = "SELECT * FROM conf_essay a WHERE EXISTS (SELECT 1 FROM conf_essay_type WHERE a.essay_type=id AND parent_menu in ('8ada99c148bcd6b40148bf63e79a0002','40281281487990db0148799c21fb0002')) order by essay_type,show_order";
			Query queryObject = getSession().createSQLQuery(queryString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}