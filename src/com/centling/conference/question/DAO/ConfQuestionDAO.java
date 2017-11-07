package com.centling.conference.question.DAO;

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
import com.centling.conference.question.entity.ConfQuestion;

/**
 * A data access object (DAO) providing persistence and search support for ConfQuestion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions.
 * Each of these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.question.entity.ConfQuestion
 * @author MyEclipse Persistence Tools
 */

@Repository("confQuestionDAO")
public class ConfQuestionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfQuestionDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String GUEST_ID = "guestId";
	public static final String USER_ID = "userId";
	public static final String CONTENT = "content";
	public static final String TIME = "time";
	public static final String STATE = "state";

	public void save(ConfQuestion transientInstance) {
		log.debug("saving ConfQuestion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfQuestion persistentInstance) {
		log.debug("deleting ConfQuestion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfQuestion findById(java.lang.String id) {
		log.debug("getting ConfQuestion instance with id: " + id);
		try {
			ConfQuestion instance = (ConfQuestion) getSession().get("com.centling.conference.question.entity.ConfQuestion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfQuestion> findByExample(ConfQuestion instance) {
		log.debug("finding ConfQuestion instance by example");
		try {
			List<ConfQuestion> results = (List<ConfQuestion>) getSession().createCriteria("com.centling.conference.question.entity.ConfQuestion")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfQuestion instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfQuestion as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfQuestion> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfQuestion> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<ConfQuestion> findByGuestId(Object guestId) {
		return findByProperty(GUEST_ID, guestId);
	}

	public List<ConfQuestion> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfQuestion> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<ConfQuestion> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List<ConfQuestion> findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findAll() {
		log.debug("finding all ConfQuestion instances");
		try {
			String queryString = "from ConfQuestion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfQuestion merge(ConfQuestion detachedInstance) {
		log.debug("merging ConfQuestion instance");
		try {
			ConfQuestion result = (ConfQuestion) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfQuestion instance) {
		log.debug("attaching dirty ConfQuestion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfQuestion instance) {
		log.debug("attaching clean ConfQuestion instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据会议id和日程id查询现场提问
	 * @param meetingId
	 * @param scheduleId
	 */
	@SuppressWarnings("unchecked")
	public List<ConfQuestion> findQuestions(String meetingId, String scheduleId){
		log.debug("select all questions according to meetingId and scheduleId");
		try{
			
			String queryString = "from ConfQuestion cq where cq.meetingId = ? ";
			
			if(scheduleId!=null && !scheduleId.equals("")){
				queryString+="and cq.scheduleId = ?";
			}
			
			Query query = getSession().createQuery(queryString);
			
			query.setParameter(0, meetingId);
			if(scheduleId!=null && !scheduleId.equals("")){
				 query.setParameter(1, scheduleId);
			}
			return query.list();
			
		}catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
		
	}
	
	/**
	 * 根据会议id和日程id查询现场提问
	 * @param meetingId
	 * @param scheduleId
	 */
	@SuppressWarnings("unchecked")
	public String findCount(String meetingId, String scheduleId){
		log.debug("count numbers for pagination");
		try{
			
			String queryString = "select count(1) from conf_question cq where cq.meeting_id = ? ";
			
			if(scheduleId!=null && !scheduleId.equals("")){
				queryString+="and cq.schedule_id = ? ";
			}
			
			queryString+="order by time desc";
			Query query = getSession().createSQLQuery(queryString);
			
			query.setParameter(0, meetingId);
			if(scheduleId!=null && !scheduleId.equals("")){
				 query.setParameter(1, scheduleId);
			}
			return query.uniqueResult().toString();
			
		}catch (RuntimeException re) {
			log.error("select failed", re);
			throw re;
		}
		
	}
	
	//更新为上墙问题或者取消上墙
    public void updateToWallQuestion(String id, String time, String state) {
        log.debug("update ConfQuestion instance wallTime");
        try {
            String sql = "update conf_question set state =:state, wall_time =:time where id =:id ";
            getSession().createSQLQuery(sql).setParameter("state", state).setParameter("time", time).setParameter("id", id).executeUpdate();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }
    
    //查找上墙问题
    public List<ConfQuestion> findMeetingWallQ(String meetingId, String scheduleId) {
        log.debug("finding ConfQuestion instance by example");
        try {
            String queryString = "from ConfQuestion cq where cq.meetingId = :meetingId and cq.scheduleId =:scheduleId and state = '4' order by cq.wallTime desc";
            List<ConfQuestion> results = getSession().createQuery(queryString)
                    .setParameter("meetingId", meetingId).setParameter("scheduleId", scheduleId).setMaxResults(1).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }
	
}