package com.centling.conference.material.DAO;

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
import com.centling.conference.material.entity.ConfMaterialSchedule;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfMaterialSchedule entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.material.entity.ConfMaterialSchedule
 * @author MyEclipse Persistence Tools
 */

@Repository("confMaterialScheduleDAO")
public class ConfMaterialScheduleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfMaterialScheduleDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String SCHDUEL_ID = "schduelId";
	public static final String MATERIAL_ID = "materialId";
	public static final String MATERIAL_TYPE = "materialType";
	public static final String MATERIAL_NAME = "materialName";
	public static final String MATERIAL_NUM = "materialNum";
	public static final String MATERIAL_LEADER = "materialLeader";
	public static final String LEADER_MOBILE = "leaderMobile";
	public static final String MEMO = "memo";

	public void save(ConfMaterialSchedule transientInstance) {
		log.debug("saving ConfMaterialSchedule instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfMaterialSchedule persistentInstance) {
		log.debug("deleting ConfMaterialSchedule instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfMaterialSchedule findById(java.lang.String id) {
		log.debug("getting ConfMaterialSchedule instance with id: " + id);
		try {
			ConfMaterialSchedule instance = (ConfMaterialSchedule) getSession()
					.get("com.centling.conference.material.entity.ConfMaterialSchedule",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfMaterialSchedule> findByExample(
			ConfMaterialSchedule instance) {
		log.debug("finding ConfMaterialSchedule instance by example");
		try {
			List<ConfMaterialSchedule> results = (List<ConfMaterialSchedule>) getSession()
					.createCriteria(
							"com.centling.conference.material.entity.ConfMaterialSchedule")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List<ConfMaterialSchedule> findMaterialSchedule(PageBean pageBean,ConfMaterialSchedule instance) {
		log.debug("finding ConfMaterialSchedule instance by example");
		try{
			StringBuffer querySB = new StringBuffer(" from ConfMaterialSchedule as model where model.meetingId = ? ");
			
			List<String> param = new ArrayList<String>();
			if(instance.getSchduelId() != null && instance.getSchduelId().trim().length() > 0){
				querySB.append(" AND model.schduelId = ? ");
				param.add(instance.getSchduelId());
			}
			if(instance.getMaterialType() != null && instance.getMaterialType().trim().length() > 0){
				querySB.append(" AND model.materialType = ? ");
				param.add(instance.getMaterialType());
			}
			if(instance.getMaterialName() != null && instance.getMaterialName().replace(" ", "").length() > 0){
				querySB.append(" AND model.materialName LIKE ? ");
				param.add("%"+instance.getMaterialName().replace(" ", "")+"%");
			}
			if(instance.getMaterialLeader() != null && instance.getMaterialLeader().length() > 0){
				//名字，如果是英文名字或其他，有可能有空格，在此不检验
				querySB.append(" AND model.materialLeader LIKE ? ");
				param.add("%"+instance.getMaterialLeader()+"%");
			}
			
			Query query =getSession().createQuery(querySB.toString()).setParameter(0, instance.getMeetingId());
			for(int i=0;i<param.size();i++){
				query.setParameter(i+1, param.get(i));
	        }
			List<ConfMaterialSchedule> list = (List<ConfMaterialSchedule>)query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
	                    .setMaxResults(pageBean.getRows()).list();
			return list;
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public String count(PageBean pageBean,ConfMaterialSchedule instance){
		log.debug("finding ConfMaterialSchedule instance by example");
		try{
			StringBuffer querySB = new StringBuffer(" SELECT COUNT(*) ");
			querySB.append(" FROM conf_material_schedule  WHERE meeting_id = ? ");
			
			List<String> param = new ArrayList<String>();
			if(instance.getSchduelId() != null && instance.getSchduelId().trim().length() > 0){
				querySB.append(" AND schduel_id = ? ");
				param.add(instance.getSchduelId());
			}
			if(instance.getMaterialType() != null && instance.getMaterialType().trim().length() > 0){
				querySB.append(" AND material_type = ? ");
				param.add(instance.getMaterialType());
			}
			if(instance.getMaterialName() != null && instance.getMaterialName().replace(" ", "").length() > 0){
				querySB.append(" AND material_name LIKE ? ");
				param.add("%"+instance.getMaterialName().replace(" ", "")+"%");
			}
			if(instance.getMaterialLeader() != null && instance.getMaterialLeader().length() > 0){
				//名字，如果是英文名字或其他，有可能有空格，在此不检验
				querySB.append(" AND material_leader LIKE ? ");
				param.add("%"+instance.getMaterialLeader()+"%");
			}
			
			Query query = getSession().createSQLQuery(querySB.toString()).setString(0, instance.getMeetingId());
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
            return query.uniqueResult().toString();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String materialIds){
		log.debug("deleting ConfMaterialSchedule instance");
		int results = 0;
		try {
			String sql = " DELETE FROM ConfMaterialSchedule WHERE id IN ( :id) ";
			Query query = getSession().createQuery(sql).setParameterList("id", materialIds.split(","));
			results = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return results;
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfMaterialSchedule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfMaterialSchedule as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfMaterialSchedule> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfMaterialSchedule> findBySchduelId(Object schduelId) {
		return findByProperty(SCHDUEL_ID, schduelId);
	}

	public List<ConfMaterialSchedule> findByMaterialId(Object materialId) {
		return findByProperty(MATERIAL_ID, materialId);
	}

	public List<ConfMaterialSchedule> findByMaterialType(Object materialType) {
		return findByProperty(MATERIAL_TYPE, materialType);
	}

	public List<ConfMaterialSchedule> findByMaterialName(Object materialName) {
		return findByProperty(MATERIAL_NAME, materialName);
	}

	public List<ConfMaterialSchedule> findByMaterialNum(Object materialNum) {
		return findByProperty(MATERIAL_NUM, materialNum);
	}

	public List<ConfMaterialSchedule> findByMaterialLeader(Object materialLeader) {
		return findByProperty(MATERIAL_LEADER, materialLeader);
	}

	public List<ConfMaterialSchedule> findByLeaderMobile(Object leaderMobile) {
		return findByProperty(LEADER_MOBILE, leaderMobile);
	}

	public List<ConfMaterialSchedule> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfMaterialSchedule instances");
		try {
			String queryString = "from ConfMaterialSchedule";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfMaterialSchedule merge(ConfMaterialSchedule detachedInstance) {
		log.debug("merging ConfMaterialSchedule instance");
		try {
			ConfMaterialSchedule result = (ConfMaterialSchedule) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfMaterialSchedule instance) {
		log.debug("attaching dirty ConfMaterialSchedule instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfMaterialSchedule instance) {
		log.debug("attaching clean ConfMaterialSchedule instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int updateStateByIds(String materialIds){
		log.debug("deleting ConfMaterialSchedule instance");
		int results = 0;
		try {
			String sql = " UPDATE ConfMaterialSchedule SET memo = 'true' WHERE id IN ( :id) ";
			Query query = getSession().createQuery(sql).setParameterList("id", materialIds.split(","));
			results = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return results;
	}
	
	/*
	 UPDATE conf_material_schedule SET memo = 'false'
WHERE id IN ('077c096948f7f8340148f8142f240000','8ada99c148b027390148b08caf8f0003'); 
	 */
	
	public Long countBymaterialIds(String id){
		log.debug("finding all ConfMaterialRegistration instances");
		try {
			String queryString = " SELECT COUNT(*) FROM ConfMaterialSchedule WHERE materialId IN (:ids) ";
	        return (Long)(getSession().createQuery(queryString)
	        		.setParameterList("ids", id.split(","))
	        		.uniqueResult());
		} catch (RuntimeException re) {
			log.error("count all failed", re);
			throw re;
		}
	}
}