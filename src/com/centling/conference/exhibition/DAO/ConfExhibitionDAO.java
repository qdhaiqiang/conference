package com.centling.conference.exhibition.DAO;

import static org.hibernate.criterion.Example.create;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.exhibition.entity.ConfExhibition;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibition entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibition.entity.ConfExhibition
 * @author MyEclipse Persistence Tools
 */
@Repository("confExhibitionDAO")
public class ConfExhibitionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitionDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EXHIBITION_NAME = "exhibitionName";
	public static final String EXHIBITION_TYPE = "exhibitionType";
	public static final String EXHIBITION_IMAGE = "exhibitionImage";
	public static final String EXHIBITION_PRICE = "exhibitionPrice";
	public static final String EXHIBITION_SERVICE_CHARGE = "exhibitionServiceCharge";

	public void save(ConfExhibition transientInstance) {
		log.debug("saving ConfExhibition instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibition persistentInstance) {
		log.debug("deleting ConfExhibition instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibition findById(java.lang.String id) {
		log.debug("getting ConfExhibition instance with id: " + id);
		try {
			ConfExhibition instance = (ConfExhibition) getSession().get(
					"com.centling.conference.exhibition.entity.ConfExhibition",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibition> findByExample(ConfExhibition instance) {
		log.debug("finding ConfExhibition instance by example");
		try {
			List<ConfExhibition> results = (List<ConfExhibition>) getSession()
					.createCriteria(
							"com.centling.conference.exhibition.entity.ConfExhibition")
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
		log.debug("finding ConfExhibition instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibition as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<ConfExhibition> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfExhibition> findByExhibitionName(Object exhibitionName) {
		return findByProperty(EXHIBITION_NAME, exhibitionName);
	}

	public List<ConfExhibition> findByExhibitionType(Object exhibitionType) {
		return findByProperty(EXHIBITION_TYPE, exhibitionType);
	}

	public List<ConfExhibition> findByExhibitionImage(Object exhibitionImage) {
		return findByProperty(EXHIBITION_IMAGE, exhibitionImage);
	}

	public List<ConfExhibition> findByExhibitionPrice(Object exhibitionPrice) {
		return findByProperty(EXHIBITION_PRICE, exhibitionPrice);
	}

	public List<ConfExhibition> findByExhibitionServiceCharge(
			Object exhibitionServiceCharge) {
		return findByProperty(EXHIBITION_SERVICE_CHARGE,
				exhibitionServiceCharge);
	}

	public List findAll() {
		log.debug("finding all ConfExhibition instances");
		try {
			String queryString = "from ConfExhibition";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibition merge(ConfExhibition detachedInstance) {
		log.debug("merging ConfExhibition instance");
		try {
			ConfExhibition result = (ConfExhibition) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibition instance) {
		log.debug("attaching dirty ConfExhibition instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibition instance) {
		log.debug("attaching clean ConfExhibition instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public void deleteById(String id) {
		log.debug("delete ConfExhibition instances id= "+id);
		try {
			String queryString = "delete from conf_exhibition where exhibition_id = ?";
			getSession().createSQLQuery(queryString).setString(0, id).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete from ConfExhibition id= "+id+" failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConfExhibition> retrieve(PageBean pageBean, ConfExhibition confExhibition, String meetingId) {
		log.debug("finding all ConfExhibition instances");
		try {
			String queryString = "from ConfExhibition as model where model.meetingId=? ";
			List<String> param = new ArrayList<String>();
			if (confExhibition.getExhibitionName() != "" && confExhibition.getExhibitionName() != null) {
				queryString += " and model.exhibitionName like ? ";
				param.add("%"+confExhibition.getExhibitionName()+"%");
			}
			if (confExhibition.getExhibitionType() != "" && confExhibition.getExhibitionType() != null) {
				queryString += " and model.exhibitionType = ? ";
				param.add(confExhibition.getExhibitionType());
			}
			queryString += " order by model.exhibitionId";

			Query query = getSession().createQuery(queryString).setString(0, meetingId);
			for (int i = 0; i < param.size(); i++) {
				query.setString(i + 1, param.get(i));
			}

			return query.setFirstResult((pageBean.getPage() - 1) * pageBean.getRows()).setMaxResults(pageBean.getRows()).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public String count(String meetingId, ConfExhibition confExhibition) {
		log.debug("finding all ConfExhibition instances");
		try {
			String queryString = "select count(*) from ConfExhibition as e where e.meetingId= ? ";
			List<String> param = new ArrayList<String>();
			if (confExhibition.getExhibitionName() != "" && confExhibition.getExhibitionName() != null) {
				queryString += " and e.exhibitionName like ? ";
				param.add("%"+confExhibition.getExhibitionName()+"%");
			}
			if (confExhibition.getExhibitionType() != "" && confExhibition.getExhibitionType() != null) {
				queryString += " and e.exhibitionType = ? ";
				param.add(confExhibition.getExhibitionType());
			}
			queryString += " order by e.exhibitionId";

			Query query = getSession().createQuery(queryString).setString(0, meetingId);
			for (int i = 0; i < param.size(); i++) {
				query.setString(i + 1, param.get(i));
			}
			
			
			return query.uniqueResult().toString();
		} catch (RuntimeException re) {
			log.error("count all failed", re);
			throw re;
		}
	}
	
	public List statisticResult(String meetingId){
		log.debug("statisticResult ConfExhibition instances meetingId= "+meetingId);
		try {
			Query query = null;
			StringBuffer queryString = new StringBuffer();
			queryString.append(" SELECT a.exhibition_id AS exhibitionId,a.exhibition_name AS exhibitionName,COUNT(b.parent_id) AS rentAmount,b.booth_state AS rentState ");
			queryString.append(" FROM conf_exhibition a,conf_exhibit_booth b ");
			queryString.append(" WHERE a.exhibition_id = b.parent_id AND b.meeting_id = ? ");
			queryString.append(" GROUP BY b.parent_id,b.booth_state ORDER BY a.exhibition_id ");
			queryString.append("");
			query = getSession().createSQLQuery(queryString.toString()).setString(0, meetingId);
			//Transformers.aliasToBean(ExhibitStatisticResult.class),ALIAS_TO_ENTITY_MAP,aliasToBean(ExhibitBoothStatistic.class)
			return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
				.list();
		} catch (RuntimeException re) {
			log.error("statisticResult ConfExhibition instances meetingId= "+meetingId+" failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String ids){
		log.debug("delete ConfExhibition instances ids = "+ids);
		try {
			String queryString = "  DELETE FROM ConfExhibition WHERE exhibitionId IN ( :ids) ";
			Query query = getSession().createQuery(queryString).setParameterList("ids", ids.split(","));
			return query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete from ConfExhibition,ids = "+ids+" failed", re);
			throw re;
		}
	}
	
}