package com.centling.conference.schedulelog.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign;
import com.centling.conference.user.entity.ConfUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfSchedulelogUserAssign entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign
 * @author MyEclipse Persistence Tools
 */

@Repository("confSchedulelogUserAssignDAO")
public class ConfSchedulelogUserAssignDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfSchedulelogUserAssignDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String SCHDULE_ID = "schduleId";
	public static final String SPEECH_TOPIC = "speechTopic";
	public static final String SPEECH_ORDER = "speechOrder";
	public static final String MEMO = "memo";

	public void save(ConfSchedulelogUserAssign transientInstance) {
		log.debug("saving ConfSchedulelogUserAssign instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSchedulelogUserAssign persistentInstance) {
		log.debug("deleting ConfSchedulelogUserAssign instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSchedulelogUserAssign findById(java.lang.String id) {
		log.debug("getting ConfSchedulelogUserAssign instance with id: " + id);
		try {
			ConfSchedulelogUserAssign instance = (ConfSchedulelogUserAssign) getSession()
					.get("com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSchedulelogUserAssign> findByExample(
			ConfSchedulelogUserAssign instance) {
		log.debug("finding ConfSchedulelogUserAssign instance by example");
		try {
			List<ConfSchedulelogUserAssign> results = (List<ConfSchedulelogUserAssign>) getSession()
					.createCriteria(
							"com.centling.conference.schedulelog.entity.ConfSchedulelogUserAssign")
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
		log.debug("finding ConfSchedulelogUserAssign instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfSchedulelogUserAssign as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSchedulelogUserAssign> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfSchedulelogUserAssign> findBySchduleId(Object schduleId) {
		return findByProperty(SCHDULE_ID, schduleId);
	}

	public List<ConfSchedulelogUserAssign> findBySpeechTopic(Object speechTopic) {
		return findByProperty(SPEECH_TOPIC, speechTopic);
	}

	public List<ConfSchedulelogUserAssign> findBySpeechOrder(Object speechOrder) {
		return findByProperty(SPEECH_ORDER, speechOrder);
	}

	public List<ConfSchedulelogUserAssign> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfSchedulelogUserAssign instances");
		try {
			String queryString = "from ConfSchedulelogUserAssign";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfSchedulelogUserAssign merge(
			ConfSchedulelogUserAssign detachedInstance) {
		log.debug("merging ConfSchedulelogUserAssign instance");
		try {
			ConfSchedulelogUserAssign result = (ConfSchedulelogUserAssign) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSchedulelogUserAssign instance) {
		log.debug("attaching dirty ConfSchedulelogUserAssign instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSchedulelogUserAssign instance) {
		log.debug("attaching clean ConfSchedulelogUserAssign instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findUsers(PageBean pageBean,String userName,String userEmail,String scheduleId){
		log.debug("finding ConfSchedulelogUserAssign instances by userName,email and schedule");
		try {
			String queryString = " SELECT a.*,b.email,b.cname FROM conf_schedulelog_user_assign a LEFT JOIN conf_user b ON a.user_id = b.id ";
			List<String> param = new ArrayList<String>();
			
			if(userName != null && !userName.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " b.cname LIKE ? ";
				param.add("%"+userName+"%");
			}
			if(userEmail != null && !userEmail.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " b.email LIKE ? ";
				param.add("%"+userEmail+"%");
			}
			if(scheduleId != null && !scheduleId.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " a.schdule_id = ? ";
				param.add(scheduleId);
			}
			queryString += " ORDER BY a.speech_order ASC ";
			Query queryObject = getSession().createSQLQuery(queryString);
			for(int i=0;i<param.size();i++){
				queryObject.setString(i, param.get(i));
	        }
			return queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
		} catch (RuntimeException re) {
			log.error("finding ConfSchedulelogUserAssign instances by userName,email and schedule failed", re);
			throw re;
		}
	}
	
	public String count(String userName,String userEmail,String scheduleId){

		log.debug("finding counts ConfSchedulelogUserAssign instances by userName,email and schedule");
		try {
			String queryString = " SELECT COUNT(*) FROM conf_schedulelog_user_assign a LEFT JOIN conf_user b ON a.user_id = b.id ";
			List<String> param = new ArrayList<String>();
			
			if(userName != null && !userName.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " b.cname LIKE ? ";
				param.add("%"+userName+"%");
			}
			if(userEmail != null && !userEmail.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " b.email LIKE ? ";
				param.add("%"+userEmail+"%");
			}
			if(scheduleId != null && !scheduleId.replace(" ", "").equals("")){
				if(param.size() == 0){
					queryString += " WHERE ";
				}else{
					queryString += " AND ";
				}
				queryString += " a.schdule_id = ? ";
				param.add(scheduleId);
			}
			Query queryObject = getSession().createSQLQuery(queryString);
			for(int i=0;i<param.size();i++){
				queryObject.setString(i, param.get(i));
	        }
			return queryObject.uniqueResult().toString();
		} catch (RuntimeException re) {
			log.error("finding counts ConfSchedulelogUserAssign instances by userName,email and schedule failed", re);
			throw re;
		}
	
	}
	
	/**
	 * 查询主讲嘉宾。
	 * @param scheduleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> findMobileSpeakersByScheduleId(String scheduleId){
		
		log.debug("finding ConfSchedulelogUserAssign by scheduleId(Moblie)");
		try {
			String queryString = " SELECT a.user_id,a.speech_topic,a.speech_order,b.sex,b.self_intro,b.self_intro_en, b.email,b.cname " +
					"FROM conf_schedulelog_user_assign a LEFT JOIN conf_user b ON a.user_id = b.id " +
					"WHERE a.schdule_id = ? ORDER BY a.speech_order ASC";
			
			Query query = getSession().createSQLQuery(queryString);
			query.setParameter(0, scheduleId);
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("finding ConfSchedulelogUserAssign instances by schedule failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据日程ID查询演讲嘉宾列表
	 * @param scheduleId 日程ID
	 */
	public List<ConfUser> findSpeakerByScheduleId(String scheduleId) {
		log.debug("finding Speaker by scheduleId");
		try {
			String queryString = "SELECT a.* FROM conf_user a LEFT JOIN conf_schedulelog_user_assign b ON a.id=b.user_id WHERE b.schdule_id=?";
			Query query = getSession().createSQLQuery(queryString).addEntity(ConfUser.class);
			query.setParameter(0, scheduleId);
			return query.list();
		} catch (RuntimeException re) {
			log.error("finding ConfSchedulelogUserAssign instances by schedule failed", re);
			throw re;
		}
	}
}