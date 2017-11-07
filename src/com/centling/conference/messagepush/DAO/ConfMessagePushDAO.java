package com.centling.conference.messagepush.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.messagepush.entity.ConfMessagePush;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfMessagePush entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.messagepush.entity.ConfMessagePush
 * @author MyEclipse Persistence Tools
 */
@Repository("confMessagePushDAO")
public class ConfMessagePushDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfMessagePushDAO.class);
	// property constants
	public static final String STATE = "state";
	public static final String USER_ID = "userId";
	public static final String BIRTH = "birth";
	public static final String CONTENT = "content";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String MEETING_ID = "meetingId";

	public void save(ConfMessagePush transientInstance) {
		log.debug("saving ConfMessagePush instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfMessagePush persistentInstance) {
		log.debug("deleting ConfMessagePush instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfMessagePush findById(java.lang.String id) {
		log.debug("getting ConfMessagePush instance with id: " + id);
		try {
			ConfMessagePush instance = (ConfMessagePush) getSession().get("com.centling.conference.messagepush.entity.ConfMessagePush", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfMessagePush> findByExample(ConfMessagePush instance) {
		log.debug("finding ConfMessagePush instance by example");
		try {
			List<ConfMessagePush> results = (List<ConfMessagePush>) getSession().createCriteria("com.centling.conference.messagepush.entity.ConfMessagePush").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfMessagePush instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfMessagePush as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfMessagePush> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List<ConfMessagePush> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfMessagePush> findByBirth(Object birth) {
		return findByProperty(BIRTH, birth);
	}

	public List<ConfMessagePush> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<ConfMessagePush> findByMessageType(Object messageType) {
		return findByProperty(MESSAGE_TYPE, messageType);
	}

	public List<ConfMessagePush> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List findAll() {
		log.debug("finding all ConfMessagePush instances");
		try {
			String queryString = "from ConfMessagePush";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfMessagePush merge(ConfMessagePush detachedInstance) {
		log.debug("merging ConfMessagePush instance");
		try {
			ConfMessagePush result = (ConfMessagePush) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfMessagePush instance) {
		log.debug("attaching dirty ConfMessagePush instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfMessagePush instance) {
		log.debug("attaching clean ConfMessagePush instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConfMessagePush> retrieve(PageBean pageBean, ConfMessagePush confMessagePush, String meetingId) {
		log.debug("find all messagePush with userId");
		String queryString = "from ConfMessagePush as c where meetingId = ? ";
		List<String> param = new ArrayList<String>();
		if(confMessagePush.getUserId()!=""&&confMessagePush.getUserId()!=null){
			queryString += " and c.userId = ? ";
			param.add(confMessagePush.getUserId());
		}
		if(confMessagePush.getState()!=null&&confMessagePush.getState()!=""){
			queryString += " and c.state = ? ";
			param.add(confMessagePush.getState());
		}
		queryString += " order by c.birth desc ";
		Query query = getSession().createQuery(queryString).setParameter(0, meetingId);
		for(int i = 0; i < param.size(); i++){
			query.setParameter(i+1, param.get(i));
		}
		try{
			return query.list();
		} catch (RuntimeException re){
			log.error("find list failed");
			throw re;
		}
	}

	public String count(PageBean pageBean, ConfMessagePush confMessagePush, String meetingId) {
		String queryString = " select count(*) from ConfMessagePush as c where meetingId = ? ";
		List<String> param = new ArrayList<String>();
		if(confMessagePush.getUserId()!=null&&confMessagePush.getUserId()!=""){
			queryString += " and c.userId = ? ";
			param.add(confMessagePush.getUserId());
		}
		if(confMessagePush.getState()!=null&&confMessagePush.getState()!=""){
			queryString += " and c.state = ? ";
			param.add(confMessagePush.getState());
		}
		queryString += " order by c.birth desc ";
		Query query = getSession().createQuery(queryString).setParameter(0, meetingId);
		for(int i = 0; i<param.size(); i++){
			query.setParameter(i+1, param.get(i));
		}
		try{
			return query.uniqueResult().toString();
		} catch (RuntimeException re){
			log.error("count all failed");
			throw re;
		}
	}
}