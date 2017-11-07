package com.centling.conference.sms.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.materialreg.entity.ConfMaterialRegistration;
import com.centling.conference.sms.entity.ConfSmsTemplate;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfSmsTemplate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.sms.entity.ConfSmsTemplate
 * @author MyEclipse Persistence Tools
 */
@Repository("confSmsTemplateDAO")
public class ConfSmsTemplateDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfSmsTemplateDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String USER_TYPE = "userType";
	public static final String CONTENT = "content";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String NAME = "name";

	public void save(ConfSmsTemplate transientInstance) {
		log.debug("saving ConfSmsTemplate instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSmsTemplate persistentInstance) {
		log.debug("deleting ConfSmsTemplate instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSmsTemplate findById(java.lang.String id) {
		log.debug("getting ConfSmsTemplate instance with id: " + id);
		try {
			ConfSmsTemplate instance = (ConfSmsTemplate) getSession().get(
					"com.centling.conference.sms.entity.ConfSmsTemplate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSmsTemplate> findByExample(ConfSmsTemplate instance) {
		log.debug("finding ConfSmsTemplate instance by example");
		try {
			List<ConfSmsTemplate> results = (List<ConfSmsTemplate>) getSession()
					.createCriteria(
							"com.centling.conference.sms.entity.ConfSmsTemplate")
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
		log.debug("finding ConfSmsTemplate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfSmsTemplate as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSmsTemplate> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfSmsTemplate> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<ConfSmsTemplate> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<ConfSmsTemplate> findByMessageType(Object messageType) {
		return findByProperty(MESSAGE_TYPE, messageType);
	}

	public List<ConfSmsTemplate> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all ConfSmsTemplate instances");
		try {
			String queryString = "from ConfSmsTemplate";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfSmsTemplate merge(ConfSmsTemplate detachedInstance) {
		log.debug("merging ConfSmsTemplate instance");
		try {
			ConfSmsTemplate result = (ConfSmsTemplate) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSmsTemplate instance) {
		log.debug("attaching dirty ConfSmsTemplate instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSmsTemplate instance) {
		log.debug("attaching clean ConfSmsTemplate instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public Long count(String meetingId) {
		log.debug("finding all ConfSmsTemplate instances");
		try {
			String queryString = "select count(*) from ConfSmsTemplate where meetingId=:meetingId";
	        return (Long)(getSession().createQuery(queryString).setString("meetingId", meetingId).uniqueResult());
		} catch (RuntimeException re) {
			log.error("count all failed", re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<ConfMaterialRegistration> retrieve(PageBean pageBean,String meetingId) {
		log.debug("finding all ConfSmsTemplate instances");
		try {
			String queryString = "from ConfSmsTemplate cel where meetingId=:meetingId  order by cel.id";
	         Query queryObject = getSession().createQuery(queryString).setString("meetingId", meetingId).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public void deleteById(String id) {
		log.debug("delete ConfSmsTemplate instances id= "+id);
		try {
			String queryString = "delete from conf_sms_template where id = ?";
			getSession().createSQLQuery(queryString).setString(0, id).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete from ConfSmsTemplate id= "+id+" failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findByUserTypeAndName(String name, String userType) {
		log.debug("findByUserTypeAndName");
		try {
			String querySql = "SELECT content FROM conf_sms_template WHERE (user_type LIKE ? OR user_type LIKE ? OR user_type LIKE ? OR user_type=?) AND MESSAGE_TYPE=?";
			return getSession().createSQLQuery(querySql).setString(0, userType+",%")
					.setString(1, "%,"+userType).setString(2, "%,"+userType+",%").setString(3, userType).setString(4, name).list();
		} catch (RuntimeException re) {
			log.error("findByUserTypeAndName failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findByUserName(String name) {
	    log.debug("findByUserTypeAndName");
	    try {
	        String querySql = "SELECT content FROM conf_sms_template WHERE MESSAGE_TYPE=?";
	        return getSession().createSQLQuery(querySql).setString(0, "%"+name+"%").list();
	    } catch (RuntimeException re) {
	        log.error("findByUserTypeAndName failed", re);
	        throw re;
	    }
	}
}