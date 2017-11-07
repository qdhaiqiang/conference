package com.centling.conference.schedule.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.schedule.entity.ConfSchedule;

/**
 	* A data access object (DAO) providing persistence and search support for ConfSchedule entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.schedule.entity.ConfSchedule
  * @author MyEclipse Persistence Tools 
 */
@Repository("confScheduleDAO")
public class ConfScheduleDAO extends BaseHibernateDAO  {

	private static final Logger log = LoggerFactory
			.getLogger(ConfScheduleDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String TITLE = "title";
	public static final String TITLE_EN = "titleEn";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String INTRO = "intro";
	public static final String INTRO_EN = "introEn";
	public static final String MEDIA_URL = "mediaUrl";

	public void save(ConfSchedule transientInstance) {
		log.debug("saving ConfSchedule instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfSchedule persistentInstance) {
		log.debug("deleting ConfSchedule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfSchedule findById(java.lang.String id) {
		log.debug("getting ConfSchedule instance with id: " + id);
		try {
			ConfSchedule instance = (ConfSchedule) getSession().get(
					"com.centling.conference.schedule.entity.ConfSchedule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfSchedule> findByExample(ConfSchedule instance) {
		log.debug("finding ConfSchedule instance by example");
		try {
			List<ConfSchedule> results = (List<ConfSchedule>) getSession()
					.createCriteria("com.centling.conference.schedule.entity.ConfSchedule").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<ConfSchedule> findByProperty(String propertyName, Object value) {
		log.debug("finding ConfSchedule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfSchedule as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfSchedule> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfSchedule> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<ConfSchedule> findByTitleEn(Object titleEn) {
		return findByProperty(TITLE_EN, titleEn);
	}

	public List<ConfSchedule> findByStartTime(Object startTime) {
		return findByProperty(START_TIME, startTime);
	}

	public List<ConfSchedule> findByEndTime(Object endTime) {
		return findByProperty(END_TIME, endTime);
	}

	public List<ConfSchedule> findByIntro(Object intro) {
		return findByProperty(INTRO, intro);
	}

	public List<ConfSchedule> findByIntroEn(Object introEn) {
		return findByProperty(INTRO_EN, introEn);
	}

	public List<ConfSchedule> findByMediaUrl(Object mediaUrl) {
		return findByProperty(MEDIA_URL, mediaUrl);
	}

	public List findAll() {
		log.debug("finding all ConfSchedule instances");
		try {
			String queryString = "from ConfSchedule";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfSchedule merge(ConfSchedule detachedInstance) {
		log.debug("merging ConfSchedule instance");
		try {
			ConfSchedule result = (ConfSchedule) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfSchedule instance) {
		log.debug("attaching dirty ConfSchedule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfSchedule instance) {
		log.debug("attaching clean ConfSchedule instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	@SuppressWarnings("unchecked")
	public List<ConfSchedule> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfSchedule instances");
        try {
            String queryString = "from ConfSchedule where meetingId=? order by startTime";
             Query queryObject = getSession().createQuery(queryString).setString(0, meetingId)
            		 .setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public Long count(String meetingId) {
		log.debug("finding all ConfSchedule count");
        try {
            String queryString = "select count(1) from ConfSchedule where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public void update(ConfSchedule confSchedule) {
		log.debug("updating ConfSchedule instance");
		try {
			getSession().update(confSchedule);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}

	public List<ConfSchedule> findBySchedules(String scheduleIds) {
		log.debug("finding scheuleList by scheduleIds");
        try {
            String queryString = "from ConfSchedule where id in(?)";
            return getSession().createQuery(queryString).setString(0, scheduleIds).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public List<ConfSchedule> findGeneralByMeetingId(String meetingId) {
		log.debug("finding scheuleList by meetingId");
        try {
            String queryString = "from ConfSchedule where meetingId=? and scheduleType='1'";
            return getSession().createQuery(queryString).setString(0, meetingId).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
}