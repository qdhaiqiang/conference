package com.centling.conference.checkin.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.checkin.entity.ConfHeadsetUser;
import com.centling.conference.meetinguser.entity.ConfGuest;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfHeadsetUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.checkin.entity.ConfHeadsetUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confHeadsetUserDAO")
public class ConfHeadsetUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfHeadsetUserDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String MEETING_ID = "meetingId";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";

	public void save(ConfHeadsetUser transientInstance) {
		log.debug("saving ConfHeadsetUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfHeadsetUser persistentInstance) {
		log.debug("deleting ConfHeadsetUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfHeadsetUser findById(java.lang.String id) {
		log.debug("getting ConfHeadsetUser instance with id: " + id);
		try {
			ConfHeadsetUser instance = (ConfHeadsetUser) getSession().get(
					"com.centling.conference.checkin.entity.ConfHeadsetUser",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfHeadsetUser> findByExample(ConfHeadsetUser instance) {
		log.debug("finding ConfHeadsetUser instance by example");
		try {
			List<ConfHeadsetUser> results = (List<ConfHeadsetUser>) getSession()
					.createCriteria(
							"com.centling.conference.checkin.entity.ConfHeadsetUser")
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
		log.debug("finding ConfHeadsetUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfHeadsetUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfHeadsetUser> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfHeadsetUser> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfHeadsetUser> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<ConfHeadsetUser> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<ConfHeadsetUser> findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findAll() {
		log.debug("finding all ConfHeadsetUser instances");
		try {
			String queryString = "from ConfHeadsetUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfHeadsetUser merge(ConfHeadsetUser detachedInstance) {
		log.debug("merging ConfHeadsetUser instance");
		try {
			ConfHeadsetUser result = (ConfHeadsetUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfHeadsetUser instance) {
		log.debug("attaching dirty ConfHeadsetUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfHeadsetUser instance) {
		log.debug("attaching clean ConfHeadsetUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	
	 public List findByUserIdAndMeetingId(String userId, String meetingId, String scheduleId) {
	        log.debug("finding ConfHeadsetUser instance with userId: " + userId
	              +", meetingId" +meetingId);
	        try {
	           String queryString = "from ConfHeadsetUser as model where model.userId= ? and model.meetingId = ? and model.scheduleId = ?";
	           Query queryObject = getSession().createQuery(queryString);
	  		 queryObject.setParameter(0, userId);
	  		 queryObject.setParameter(1, meetingId);
	  		queryObject.setParameter(2, scheduleId);
	  		 
	  		 return queryObject.list();
	        } catch (RuntimeException re) {
	           log.error("find by property name failed", re);
	           throw re;
	        }
	  	}	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List retrieve(PageBean pageBean,String meetingId,String scheduleId, ConfGuest user) {
		log.debug("finding all ConfHeadsetUser instances");
		try {
			String queryString = "from ConfHeadsetUser as model where model.meetingId = ?";
			
			if(scheduleId != null && !scheduleId.equals("")){
				 queryString += " and model.scheduleId = ?";
			}
			 if (user.getCname() != null && !user.getCname().equals("")) {
	                queryString += " and (model.cname like :cname or model.ename like :ename )";
	            }
	            if (user.getUserType() != null && !user.getUserType().equals("")) {
	                queryString += " and model.userType in (:userType)";
	            }
	            Query queryObject = getSession().createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
	            queryObject.setString(0, meetingId);
	            if(scheduleId != null && !scheduleId.equals("")){
	            queryObject.setString(1, scheduleId);
	            }
	            if (user.getCname() != null && !user.getCname().equals("")) {
	            	queryObject.setString("cname", "%"+user.getCname()+"%");
	            	queryObject.setString("ename", "%"+user.getCname()+"%");
	            }
	            if (user.getUserType() != null && !user.getUserType().equals("")) {
	            	queryObject.setParameterList("userType", user.getUserType().split(","));
	            }
			
			
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count(String meetingId,ConfGuest user, String scheduleId) {
		log.debug("finding all ConfHeadsetUser instances");
		try {
			String queryString = "select count(*) from ConfHeadsetUser as model where model.meetingId =?";
			if(scheduleId != null && !scheduleId.equals("")){
				 queryString += " and model.scheduleId = ?";
			}
			 if (user.getCname() != null && !user.getCname().equals("")) {
	                queryString += " and (model.cname like :cname or model.ename like :ename )";
	            }
	            if (user.getUserType() != null && !user.getUserType().equals("")) {
	                queryString += " and model.userType in (:userType)";
	            }
	            Query queryObject = getSession().createQuery(queryString);
	            queryObject.setString(0, meetingId);
	            if (user.getCname() != null && !user.getCname().equals("")) {
	            	queryObject.setString("cname", "%"+user.getCname()+"%");
	            	queryObject.setString("ename", "%"+user.getCname()+"%");
	            }
	            if (user.getUserType() != null && !user.getUserType().equals("")) {
	            	queryObject.setParameterList("userType", user.getUserType().split(","));
	            }
			 
			 queryObject.setString(0, meetingId);
			 if(scheduleId != null && !scheduleId.equals("")){
			 queryObject.setString(1, scheduleId);
			 }
	        return (Long)(queryObject.uniqueResult());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}