package com.centling.conference.vote.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.vote.entity.ConfVoteField;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfVoteField entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.vote.entity.ConfVoteField
 * @author MyEclipse Persistence Tools
 */
@Repository("confVoteFieldDAO")
public class ConfVoteFieldDAO extends BaseHibernateDAO {

	private static final Logger log = LoggerFactory
			.getLogger(ConfVoteFieldDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String SCHEDULE_ID = "scheduleId";
	public static final String TYPE = "type";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String OPTIONS = "options";
	public static final String ORDER_NUM = "orderNum";
	public static final String REQUIRED = "required";
	public static final String IS_SHOW = "isShow";

	public void save(ConfVoteField transientInstance) {
		log.debug("saving ConfVoteField instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfVoteField persistentInstance) {
		log.debug("deleting ConfVoteField instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfVoteField findById(java.lang.String id) {
		log.debug("getting ConfVoteField instance with id: " + id);
		try {
			ConfVoteField instance = (ConfVoteField) getSession().get(
					"com.centling.conference.vote.entity.ConfVoteField", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfVoteField> findByExample(ConfVoteField instance) {
		log.debug("finding ConfVoteField instance by example");
		try {
			List<ConfVoteField> results = (List<ConfVoteField>) getSession()
					.createCriteria(
							"com.centling.conference.vote.entity.ConfVoteField")
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
		log.debug("finding ConfVoteField instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfVoteField as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfVoteField> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfVoteField> findByScheduleId(Object scheduleId) {
		return findByProperty(SCHEDULE_ID, scheduleId);
	}

	public List<ConfVoteField> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ConfVoteField> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfVoteField> findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List<ConfVoteField> findByOptions(Object options) {
		return findByProperty(OPTIONS, options);
	}

	public List<ConfVoteField> findByOrderNum(Object orderNum) {
		return findByProperty(ORDER_NUM, orderNum);
	}

	public List<ConfVoteField> findByRequired(Object required) {
		return findByProperty(REQUIRED, required);
	}

	public List<ConfVoteField> findByIsShow(Object isShow) {
		return findByProperty(IS_SHOW, isShow);
	}

	public List findAll() {
		log.debug("finding all ConfVoteField instances");
		try {
			String queryString = "from ConfVoteField";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfVoteField merge(ConfVoteField detachedInstance) {
		log.debug("merging ConfVoteField instance");
		try {
			ConfVoteField result = (ConfVoteField) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfVoteField instance) {
		log.debug("attaching dirty ConfVoteField instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfVoteField instance) {
		log.debug("attaching clean ConfVoteField instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List findVoteField(PageBean pageBean,String scheduleId,String meetingid){
		log.debug("finding ConfVoteField instances");
		try {
			String queryString = " from ConfVoteField as model where model.meetingId=? ";
			List<String> params = new ArrayList<String>();
			params.add(meetingid);
			if(scheduleId != null && !scheduleId.replace(" ", "").equals("")){
				queryString += " AND model.scheduleId=? ";
				params.add(scheduleId);
			}
			queryString += " ORDER BY model.scheduleId ASC,model.orderNum ASC ";
			Query queryObject = getSession().createQuery(queryString);
			for(int i=0;i<params.size();i++){
				queryObject.setParameter(i, params.get(i));
			}
			
			return queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows()).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String count(String scheduleId,String meetingid){
		String queryString = " SELECT COUNT(*) FROM conf_vote_field where meeting_id=? ";
		List<String> params = new ArrayList<String>();
		params.add(meetingid);
		if(scheduleId != null && !scheduleId.replace(" ", "").equals("")){
			queryString += " AND schedule_id=? ";
			params.add(scheduleId);
		}
		Query queryObject = getSession().createSQLQuery(queryString);
		for(int i=0;i<params.size();i++){
			queryObject.setString(i, params.get(i));
		}
		return queryObject.uniqueResult().toString();
	}
	
	public List findVotesBySchduelId(String schduelId,String meetingId){
		log.debug("finding ConfVoteField instances");
		try {
			String queryString = " from ConfVoteField as model where model.meetingId = ? AND model.scheduleId = ? AND model.isShow='true' ORDER BY model.orderNum ASC ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, meetingId).setParameter(1, schduelId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String voteIds){
		log.debug("deleting ConfVoteField instance");
		int results = 0;
		try {
			String sql = " DELETE FROM ConfVoteField WHERE id IN ( :id) ";
			Query query = getSession().createQuery(sql).setParameterList("id", voteIds.split(","));
			results = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return results;
	}
	
   public Long findVotesCountBySchduelId(String schduelId,String meetingId){
        log.debug("finding ConfVoteField instances");
        try {
            String queryString = "select count(*) from ConfVoteField as model where model.meetingId = ? AND model.scheduleId = ? AND model.isShow='true'";
            Query queryObject = getSession().createQuery(queryString).setParameter(0, meetingId).setParameter(1, schduelId);
            return (Long)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
   
   //修改在会议墙上显示与否的状态
   public int updateMeetingWallState(String id,  String isShow){
       log.debug("updating ConfVoteField instance");
       int results = 0;
       try {
           String sql = " UPDATE conf_vote_field SET is_show =:isShow WHERE id =:id ";
           Query query = getSession().createSQLQuery(sql).setParameter("isShow", isShow).setParameter("id", id);
           results = query.executeUpdate();
           log.debug("update successful");
       } catch (RuntimeException re) {
           log.error("update failed", re);
           throw re;
       }
       return results;
   }

}