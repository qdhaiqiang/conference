package com.centling.conference.checkin.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.checkin.entity.ConfSigninUser;
import com.centling.conference.user.entity.ConfUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfSigninUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.checkin.entity.ConfSigninUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confSigninUserDAO")
public class ConfSigninUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfSigninUserDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String MAIL_TICKET = "mailTicket";
	public static final String GIFT = "gift";
	public static final String RESTAURANT = "restaurant";
	public static final String ROOM_NUM = "roomNum";
	public static final String OPERATOR = "operator";
	public static final String SIGNIN_TIME = "signinTime";
	public static final String SIGNIN_TIME_COPY = "signinTimeCopy";
	public static final String IFDOCUMENT = "ifdocument";
	public static final String IFRECEIPT = "ifreceipt";
	public static final String POSTSCRIPT = "postscript";

	public void save(ConfSigninUser transientInstance) {
		log.debug("saving ConfSigninUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSigninUser persistentInstance) {
		log.debug("deleting ConfSigninUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSigninUser findById(java.lang.String id) {
		log.debug("getting ConfSigninUser instance with id: " + id);
		try {
			ConfSigninUser instance = (ConfSigninUser) getSession()
					.get("com.centling.conference.checkin.entity.ConfSigninUser",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSigninUser> findByExample(ConfSigninUser instance) {
		log.debug("finding ConfSigninUser instance by example");
		try {
			List<ConfSigninUser> results = (List<ConfSigninUser>) getSession()
					.createCriteria(
							"com.centling.conference.checkin.entity.ConfSigninUser")
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
		log.debug("finding ConfSigninUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfSigninUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSigninUser> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfSigninUser> findByMailTicket(Object mailTicket) {
		return findByProperty(MAIL_TICKET, mailTicket);
	}

	public List<ConfSigninUser> findByGift(Object gift) {
		return findByProperty(GIFT, gift);
	}

	public List<ConfSigninUser> findByRestaurant(Object restaurant) {
		return findByProperty(RESTAURANT, restaurant);
	}

	public List<ConfSigninUser> findByRoomNum(Object roomNum) {
		return findByProperty(ROOM_NUM, roomNum);
	}

	public List<ConfSigninUser> findByOperator(Object operator) {
		return findByProperty(OPERATOR, operator);
	}

	public List<ConfSigninUser> findBySigninTime(Object signinTime) {
		return findByProperty(SIGNIN_TIME, signinTime);
	}

	public List<ConfSigninUser> findBySigninTimeCopy(Object signinTimeCopy) {
		return findByProperty(SIGNIN_TIME_COPY, signinTimeCopy);
	}

	public List<ConfSigninUser> findByIfdocument(Object ifdocument) {
		return findByProperty(IFDOCUMENT, ifdocument);
	}

	public List<ConfSigninUser> findByIfreceipt(Object ifreceipt) {
		return findByProperty(IFRECEIPT, ifreceipt);
	}

	public List<ConfSigninUser> findByPostscript(Object postscript) {
		return findByProperty(POSTSCRIPT, postscript);
	}

	public List findAll() {
		log.debug("finding all ConfSigninUser instances");
		try {
			String queryString = "from ConfSigninUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfSigninUser merge(ConfSigninUser detachedInstance) {
		log.debug("merging ConfSigninUser instance");
		try {
			ConfSigninUser result = (ConfSigninUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSigninUser instance) {
		log.debug("attaching dirty ConfSigninUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSigninUser instance) {
		log.debug("attaching clean ConfSigninUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	public List findBySigninUserbyIDandMeeting(String userId, String meetingId) {
		log.debug("finding ConfSigninUser instance with userId and meetingId: "
				);
		try {
			String queryString = "from ConfSigninUser as model where model.confUser.id"
					+  "= ?" +" and model."+ MEETING_ID + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, userId);
			queryObject.setParameter(1, meetingId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
	public List findByUserId(String userId) {
		log.debug("finding ConfSigninUser instance with userId: "
				);
		try {
			String queryString = "from ConfSigninUser as model where model.confUser.id"
					+  "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<ConfSigninUser> findAllByMeetingId(PageBean pageBean, String username, String meetingId){
		log.debug("finding all ConfSigninUser instances");
		
		try {
			String queryString = "from ConfSigninUser as model where model.meetingId=?";
			 if (!StringUtils.isEmpty(username)) {
	                queryString += " and (model.confUser.cname like :cname or model.confUser.ename like :ename )";
	            }
	            
	            Query queryObject = getSession().createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
	            queryObject.setString(0, meetingId);
	            if (!StringUtils.isEmpty(username)) {
	            	queryObject.setString("cname", "%"+username+"%");
	            	queryObject.setString("ename", "%"+username+"%");
	            	
	            }

			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		/*try {
			Query query = null;
            String queryString = "from ConfSigninUser as model where model.meetingId=?";
            if (!StringUtils.isEmpty(cname) || !StringUtils.isEmpty(ename)) {
            	System.out.println("9999999999999999");
            	queryString += " and (model.confUser.cname like :cname or model.confUser.ename like :ename )";
            }
            
            query = getSession().createQuery(queryString).setString(0, meetingId);
            
            if (!StringUtils.isEmpty(cname) || !StringUtils.isEmpty(ename)) {
            	System.out.println("9999999999sssssssssssss999999");
            	query.setString("cname", "%"+cname+"%");
                query.setString("ename", "%"+ename+"%");
            }
             
            return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows()).list();
             //return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }*/
	}
	
	public Long count(String meetingId) {
		log.debug("finding all ConfSigninUser count");
        try {
            String queryString = "select count(1) from ConfSigninUser where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
	
	 public void update(ConfSigninUser instance) {
	        log.debug("update ConfSigninUser instance");
	        try {
	            getSession().update(instance);
	            log.debug("attach successful");
	        } catch (RuntimeException re) {
	            log.error("attach failed", re);
	            throw re;
	        }
	    }
	
}