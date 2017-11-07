package com.centling.conference.userroom.DAO;

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
import com.centling.conference.serviceprovider.entity.ConfServiceProvider;
import com.centling.conference.userroom.entity.ConfUserRoom;

/**
 * A data access object (DAO) providing persistence and search support for ConfUserRoom entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions.
 * Each of these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.userroom.entity.ConfUserRoom
 * @author MyEclipse Persistence Tools
 */

@Repository("confUserRoomDAO")
public class ConfUserRoomDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfUserRoomDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String USER_TYPE = "userType";
	public static final String ROOM_TYPE = "roomType";
	public static final String ORGANIZER_PAY = "organizerPay";
	public static final String REMARKS = "remarks";

	public void save(ConfUserRoom transientInstance) {
		log.debug("saving ConfUserRoom instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfUserRoom persistentInstance) {
		log.debug("deleting ConfUserRoom instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfUserRoom findById(java.lang.String id) {
		log.debug("getting ConfUserRoom instance with id: " + id);
		try {
			ConfUserRoom instance = (ConfUserRoom) getSession().get("com.centling.conference.userroom.entity.ConfUserRoom", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfUserRoom> findByExample(ConfUserRoom instance) {
		log.debug("finding ConfUserRoom instance by example");
		try {
			List<ConfUserRoom> results = (List<ConfUserRoom>) getSession().createCriteria("com.centling.conference.userroom.entity.ConfUserRoom")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfUserRoom instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfUserRoom as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfUserRoom> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfUserRoom> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<ConfUserRoom> findByRoomType(Object roomType) {
		return findByProperty(ROOM_TYPE, roomType);
	}

	public List<ConfUserRoom> findByOrganizerPay(Object organizerPay) {
		return findByProperty(ORGANIZER_PAY, organizerPay);
	}

	public List<ConfUserRoom> findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all ConfUserRoom instances");
		try {
			String queryString = "from ConfUserRoom";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfUserRoom merge(ConfUserRoom detachedInstance) {
		log.debug("merging ConfUserRoom instance");
		try {
			ConfUserRoom result = (ConfUserRoom) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfUserRoom instance) {
		log.debug("attaching dirty ConfUserRoom instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfUserRoom instance) {
		log.debug("attaching clean ConfUserRoom instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	   /**
     * 根据会议ID查询未分配房型的用户类型
     * @param meetingId 会议ID
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<ConfDict> findShowDictByMeetingId(String meetingId) {
		log.debug("finding Dict by meetingId");
		String querySql = "SELECT a.code, a.name FROM conf_dict a " +
				"LEFT JOIN conf_dict_category b ON a.category_id=b.id WHERE b.code='user_type' " + 
				"AND NOT EXISTS (SELECT 1 FROM conf_user_room WHERE a.code=user_type AND meeting_id=?)";
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
	
	public int deleteById(String ids) {
		log.debug("delete ConfUserRoom instance");
		try {
			String queryString = "delete from ConfUserRoom where id in (:id)";
			Query query = getSession().createQuery(queryString);
        	query.setParameterList("id", ids.split(","));
        	int row = query.executeUpdate();
        	return row;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfUserRoom> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfUserRoom instances");
		try {
			String queryString = "from ConfUserRoom as model where model.meetingId=?";
          
            queryString += " order by model.id";
            
            Query query = getSession().createQuery(queryString);
            
			return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
		            .setMaxResults(pageBean.getRows()).list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}