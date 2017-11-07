package com.centling.conference.schedule.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.schedule.entity.ConfScheduleUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfScheduleUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.schedule.ConfScheduleUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confScheduleUserDAO")
public class ConfScheduleUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfScheduleUserDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String MEETING_ID = "meetingId";

	public void save(ConfScheduleUser transientInstance) {
		log.debug("saving ConfScheduleUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfScheduleUser persistentInstance) {
		log.debug("deleting ConfScheduleUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfScheduleUser findById(java.lang.String id) {
		log.debug("getting ConfScheduleUser instance with id: " + id);
		try {
			ConfScheduleUser instance = (ConfScheduleUser) getSession().get(
					"ConfScheduleUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfScheduleUser> findByExample(ConfScheduleUser instance) {
		log.debug("finding ConfScheduleUser instance by example");
		try {
			List<ConfScheduleUser> results = (List<ConfScheduleUser>) getSession()
					.createCriteria("ConfScheduleUser").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfScheduleUser instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfScheduleUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
//审核通过才能签入
	public List findByMeetingIdAndSchedule(String userId,String meetingId, String scheduleId) {
		log.debug("getting ConfScheduleUser instance with id: " + scheduleId);
		try {
			String queryString = "from ConfScheduleUser model where model.userId = ? and model.meetingId = ? and model.scheduleId = ?";
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


	
	
	
	
	
	public List<ConfScheduleUser> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfScheduleUser> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<ConfScheduleUser> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List findAll() {
		log.debug("finding all ConfScheduleUser instances");
		try {
			String queryString = "from ConfScheduleUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfScheduleUser merge(ConfScheduleUser detachedInstance) {
		log.debug("merging ConfScheduleUser instance");
		try {
			ConfScheduleUser result = (ConfScheduleUser) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfScheduleUser instance) {
		log.debug("attaching dirty ConfScheduleUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfScheduleUser instance) {
		log.debug("attaching clean ConfScheduleUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
   public void deleteByPK(String userId, String schId) {
        log.debug("deleting ConfScheduleUser instance");
        try {
            String queryString = "delete from conf_schedule_user where user_id = ? and schedule_id = ?";
            getSession().createSQLQuery(queryString).setString(0, userId).setString(1, schId).executeUpdate();
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

	public void deleteSchedule(String userId, String meetingId) {
		log.debug("delete confSchedule");
        try {
            String queryString = "delete from conf_schedule_user where user_id = ? and meeting_id=?";
            getSession().createSQLQuery(queryString).setString(0, userId).setString(1, meetingId).executeUpdate();
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
	}
	
	public List<ConfScheduleUser> findByUserAndMeeting(String userId,String meetingId) {
		log.debug("finding ConfScheduleUser instance with userId: " + userId + ", meetingId: " + meetingId);
		try {
			String queryString = "from ConfScheduleUser as model where model.userId = ? and model.meetingId = ? ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, userId);
			queryObject.setParameter(1, meetingId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	
	} 
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findByUserIdAndMeetingId(String userId,String meetingId) {
		log.debug("finding ConfScheduleUser instance with userId: " + userId + ", meetingId: " + meetingId);
		try {
			String queryString = "select t.schedule_id scheduleId, t.user_id, s.title,s.title_en as titleEn from conf_Schedule_user t left join conf_schedule s on t.schedule_id = s.id where t.user_id = ? and t.meeting_id = ? ";
			Query queryObject = getSession().createSQLQuery(queryString);
			queryObject.setParameter(0, userId);
			queryObject.setParameter(1, meetingId);
			return queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	
	} 
	
	//会议墙，查看会场里演讲嘉宾的人数
   public String findSpeakerCountBySchduelId(String meetingId, String scheduleId) {
        log.debug("finding ConfScheduleUser count instance");
        try {
            String queryString = "select count(*) from conf_schedulelog_user_assign csu inner join conf_meeting_user cmu on csu.user_id = cmu.user_id  " +
            		"where cmu.user_type in (1,2,12,13) and cmu.meeting_id = :meetingId and csu.schdule_id = :scheduleId ";
            Query queryObject = getSession().createSQLQuery(queryString);
            queryObject.setParameter("meetingId", meetingId);
            queryObject.setParameter("scheduleId", scheduleId);
            return queryObject.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    } 
   
   //会议墙，检索会场里演讲嘉宾信息
   public List<Map> findSpeakerBySchduelId(String meetingId, String scheduleId) {
       log.debug("finding ConfScheduleUser count instance");
       try {
           String queryString = "SELECT cu.id, cu.cname as cname, cu.ename as ename, cu.photo as photo, cu.self_intro as selfIntro, cu.self_intro_en as selfIntroEn, d.name as nation  " +
           		"FROM conf_schedulelog_user_assign csu INNER JOIN conf_meeting_user cmu ON csu.user_id = cmu.user_id left JOIN conf_user cu ON cu.id = csu.user_id LEFT JOIN conf_dict d ON d.code=cu.nation " +
           		"WHERE cmu.user_type IN (1,2,12,13) AND cmu.meeting_id = :meetingId AND csu.schdule_id = :scheduleId and d.category_id='2' " +
           		"GROUP BY cu.id ";
           Query queryObject = getSession().createSQLQuery(queryString);
           queryObject.setParameter("meetingId", meetingId);
           queryObject.setParameter("scheduleId", scheduleId);
           return queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
       } catch (RuntimeException re) {
           log.error("find by property name failed", re);
           throw re;
       }
   } 
	
}