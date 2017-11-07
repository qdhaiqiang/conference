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
import com.centling.conference.checkin.entity.ConfCurrcheckinUser;
import com.centling.conference.meetinguser.entity.ConfGuest;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfCurrcheckinUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.checkin.entity.ConfCurrcheckinUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confCurrcheckinUserDAO")
public class ConfCurrcheckinUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfCurrcheckinUserDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String CHECK_STATE = "checkState";
	public static final String CHECK_TIME = "checkTime";
	public static final String CHECK_TIME_COPY = "checkTimeCopy";
	public static final String OPERATOR = "operator";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String STATE = "state";
	public static final String ISHEADSET = "isheadset";

	public void save(ConfCurrcheckinUser transientInstance) {
		log.debug("saving ConfCurrcheckinUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfCurrcheckinUser persistentInstance) {
		log.debug("deleting ConfCurrcheckinUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfCurrcheckinUser findById(java.lang.String id) {
		log.debug("getting ConfCurrcheckinUser instance with id: " + id);
		try {
			ConfCurrcheckinUser instance = (ConfCurrcheckinUser) getSession()
					.get("com.centling.conference.checkin.entity.ConfCurrcheckinUser",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfCurrcheckinUser> findByExample(ConfCurrcheckinUser instance) {
		log.debug("finding ConfCurrcheckinUser instance by example");
		try {
			List<ConfCurrcheckinUser> results = (List<ConfCurrcheckinUser>) getSession()
					.createCriteria(
							"com.centling.conference.checkin.entity.ConfCurrcheckinUser")
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
		log.debug("finding ConfCurrcheckinUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfCurrcheckinUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfCurrcheckinUser> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List findByUserId(Object meetingId,Object userId, Object scheduleId) {
		log.debug("finding ConfCurrcheckinUser instance with Conf_user.id: "
				 + ", value: " + userId);
		try {
			String queryString = "from ConfCurrcheckinUser as model where model.confUser.id"
				+ "= ? and model.meetingId = ? and model.scheduleId = ?";
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

	public List<ConfCurrcheckinUser> findByCheckState(Object checkState) {
		return findByProperty(CHECK_STATE, checkState);
	}

	public List<ConfCurrcheckinUser> findByCheckTime(Object checkTime) {
		return findByProperty(CHECK_TIME, checkTime);
	}

	public List<ConfCurrcheckinUser> findByCheckTimeCopy(Object checkTimeCopy) {
		return findByProperty(CHECK_TIME_COPY, checkTimeCopy);
	}

	public List<ConfCurrcheckinUser> findByOperator(Object operator) {
		return findByProperty(OPERATOR, operator);
	}

	public List<ConfCurrcheckinUser> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<ConfCurrcheckinUser> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<ConfCurrcheckinUser> findByIsheadset(Object isheadset) {
		return findByProperty(ISHEADSET, isheadset);
	}

	public List findAll() {
		log.debug("finding all ConfCurrcheckinUser instances");
		try {
			String queryString = "from ConfCurrcheckinUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfCurrcheckinUser merge(ConfCurrcheckinUser detachedInstance) {
		log.debug("merging ConfCurrcheckinUser instance");
		try {
			ConfCurrcheckinUser result = (ConfCurrcheckinUser) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfCurrcheckinUser instance) {
		log.debug("attaching dirty ConfCurrcheckinUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfCurrcheckinUser instance) {
		log.debug("attaching clean ConfCurrcheckinUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List retrieve(PageBean pageBean,String meetingId,String scheduleId, ConfGuest user) {
		log.debug("finding all ConfCurrcheckinUser instances");
		try {
			String queryString = "from ConfCurrcheckinUser as model where model.meetingId = ?";
			
			if(scheduleId != null && !scheduleId.equals("")){
				 queryString += " and model.scheduleId = ?";
			}
			 if (user.getCname() != null && !user.getCname().equals("")) {
	                queryString += " and (model.confUser.cname like :cname or model.confUser.ename like :ename )";
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
		log.debug("finding all ConfCurrcheckinUser instances");
		try {
			String queryString = "select count(*) from ConfCurrcheckinUser as model where model.meetingId =?";
			if(scheduleId != null && !scheduleId.equals("")){
				 queryString += " and model.scheduleId = ?";
			}
			 if (user.getCname() != null && !user.getCname().equals("")) {
	                queryString += " and (model.confUser.cname like :cname or model.confUser.ename like :ename )";
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
	
	
	public Long count() {
		log.debug("finding all ConfCurrcheckinUser instances");
		try {
			String queryString = "select count(*) from ConfCurrcheckinUser";
	        return (Long)(getSession().createQuery(queryString).uniqueResult());
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<ConfCurrcheckinUser> retrieveAudience(String meetingId,String scheduleId, String[] userType){
		log.debug("finding all ConfCurrcheckinUser instances by userType");
		
		try {
            String queryString = "from ConfCurrcheckinUser where meetingId=? and scheduleId=? and userType!=? and userType!=? and userType!=? and userType!=? and userType!=? and userType!=?";
            queryString += "order by checkTime";
            Query queryObject = getSession().createQuery(queryString)
            		 .setString(0, meetingId)
            		 .setString(1, scheduleId)
            		 .setString(2, userType[0])
            		 .setString(3, userType[1])
            		 .setString(4, userType[2])
            		 .setString(5, userType[3])
            		 .setString(6, userType[4])
            		 .setString(7, userType[5])
            		 .setFirstResult(0)
            		 .setMaxResults(6);
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
	
	
	public List<ConfCurrcheckinUser> retrieveGuest(String meetingId,String scheduleId, String[] userType){
		log.debug("finding all ConfCurrcheckinUser instances by userType");
		
		try {
            String queryString = "from ConfCurrcheckinUser where meetingId=? and scheduleId=? and (userType=? or userType=? or userType=? or userType=? or userType=? or userType=?)";
            queryString += "order by checkTime";
            Query queryObject = getSession().createQuery(queryString)
            		 .setString(0, meetingId)
            		 .setString(1, scheduleId)
            		 .setString(2, userType[0])
            		 .setString(3, userType[1])
            		 .setString(4, userType[2])
            		 .setString(5, userType[3])
            		 .setString(6, userType[4])
            		 .setString(7, userType[5])
            		 .setFirstResult(0)
            		 .setMaxResults(2);
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
	

	 public void update(ConfCurrcheckinUser instance) {
	        log.debug("update ConfCurrcheckinUser instance");
	        try {
	            getSession().update(instance);
	            log.debug("attach successful");
	        } catch (RuntimeException re) {
	            log.error("attach failed", re);
	            throw re;
	        }
	    }
	
}