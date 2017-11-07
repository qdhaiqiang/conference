package com.centling.conference.meeting.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.dict.entity.ConfDict;
import com.centling.conference.meeting.entity.ConfMeeting;

/**
 	* A data access object (DAO) providing persistence and search support for ConfMeeting entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.meeting.entity.ConfMeeting
  * @author MyEclipse Persistence Tools 
 */
@Repository("confMeetingDAO")
public class ConfMeetingDAO extends BaseHibernateDAO  {

	private static final Logger log = LoggerFactory
			.getLogger(ConfMeetingDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String NAME_EN = "nameEn";
	public static final String START_TIME = "startTime";
	public static final String END_TIME = "endTime";
	public static final String CITY = "city";
	public static final String LOCATION_ID = "locationId";
	public static final String BANNER_PIC = "bannerPic";
	public static final String URL = "url";
	public static final String MEETING_INTRO = "meetingIntro";
	public static final String MEETING_INTRO_EN = "meetingIntroEn";
	public static final String MEETING_STATUS = "meetingStatus";

	public void save(ConfMeeting transientInstance) {
		log.debug("saving ConfMeeting instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfMeeting persistentInstance) {
		log.debug("deleting ConfMeeting instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfMeeting findById(java.lang.String id) {
		log.debug("getting ConfMeeting instance with id: " + id);
		try {
			ConfMeeting instance = (ConfMeeting) getSession().get(
					"com.centling.conference.meeting.entity.ConfMeeting", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfMeeting> findByExample(ConfMeeting instance) {
		log.debug("finding ConfMeeting instance by example");
		try {
			List<ConfMeeting> results = (List<ConfMeeting>) getSession()
					.createCriteria("com.centling.conference.meeting.entity.ConfMeeting").add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfMeeting instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfMeeting as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfMeeting> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfMeeting> findByNameEn(Object nameEn) {
		return findByProperty(NAME_EN, nameEn);
	}

	public List<ConfMeeting> findByStartTime(Object startTime) {
		return findByProperty(START_TIME, startTime);
	}

	public List<ConfMeeting> findByEndTime(Object endTime) {
		return findByProperty(END_TIME, endTime);
	}

	public List<ConfMeeting> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<ConfMeeting> findByLocationId(Object locationId) {
		return findByProperty(LOCATION_ID, locationId);
	}

	public List<ConfMeeting> findByBannerPic(Object bannerPic) {
		return findByProperty(BANNER_PIC, bannerPic);
	}

	public List<ConfMeeting> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<ConfMeeting> findByMeetingIntro(Object meetingIntro) {
		return findByProperty(MEETING_INTRO, meetingIntro);
	}

	public List<ConfMeeting> findByMeetingIntroEn(Object meetingIntroEn) {
		return findByProperty(MEETING_INTRO_EN, meetingIntroEn);
	}

	public List<ConfMeeting> findByMeetingStatus(Object meetingStatus) {
		return findByProperty(MEETING_STATUS, meetingStatus);
	}

	public List findAll() {
		log.debug("finding all ConfMeeting instances");
		try {
			String queryString = "from ConfMeeting";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfMeeting merge(ConfMeeting detachedInstance) {
		log.debug("merging ConfMeeting instance");
		try {
			ConfMeeting result = (ConfMeeting) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfMeeting instance) {
		log.debug("attaching dirty ConfMeeting instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfMeeting instance) {
		log.debug("attaching clean ConfMeeting instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

    
    @SuppressWarnings("unchecked")
    public List<ConfMeeting> retrieve(PageBean pageBean) {
        log.debug("finding all ConfMeeting instance");
        try {
            String queryString = "from ConfMeeting";
             Query queryObject = getSession().createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
             return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public Long count() {
        log.debug("finding all ConfMeeting instance");
        try {
            String queryString = "select count(*) from ConfMeeting";
            return (Long)(getSession().createQuery(queryString).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public void update(ConfMeeting instance) {
        log.debug("finding all ConfMeeting instance");
        try {
            getSession().update(instance);
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    /**
     * 根据会议ID查询未创建动态表单的用户类型
     * @param meetingId 会议ID
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<ConfDict> findDictByMeetingId(String meetingId) {
		log.debug("finding Dict by meetingId");
		String querySql = "SELECT a.code, a.name FROM conf_dict a " +
				"LEFT JOIN conf_dict_category b ON a.category_id=b.id WHERE b.code='user_type' " + 
				"AND NOT EXISTS (SELECT 1 FROM conf_dyn_form WHERE a.code=user_type AND meeting_id=?)";
		try {
			List<Object[]> list = getSession().createSQLQuery(querySql).setParameter(0, meetingId).list();
			List<ConfDict> confDictList = new ArrayList<ConfDict>();
			for (Object[] objects : list) {
				ConfDict confDict = new ConfDict();
				confDict.setCode(objects[0].toString());
				confDict.setName(objects[1].toString().substring(3));
				confDictList.add(confDict);
			}
			return confDictList;
		} catch (RuntimeException re) {
			log.error("finding failed",re);
			throw re;
		}
	}
}