package com.centling.conference.exhibitbooth.DAO;

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
import com.centling.conference.exhibitbooth.entity.ConfBooth;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfBooth entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.booth.entity.ConfBooth
 * @author MyEclipse Persistence Tools
 */
@Repository("confBoothDAO")
public class ConfBoothDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfBoothDAO.class);
	// property constants
	public static final String PARENT_ID = "parentId";
	public static final String MEETING_ID = "meetingId";
	public static final String EXHIBITOR_ID = "exhibitorId";
	public static final String BOOTH_COMPANY = "boothCompany";
	public static final String BOOTH_NAME = "boothName";
	public static final String BOOTH_LINTEL_NAME = "boothLintelName";
	public static final String BOOTH_STATE = "boothState";

	public void save(ConfBooth transientInstance) {
		log.debug("saving ConfBooth instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfBooth persistentInstance) {
		log.debug("deleting ConfBooth instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfBooth findById( java.lang.String id) {
        log.debug("getting ConfBooth instance with boothId: " + id);
        try {
        	String queryString = "from ConfBooth where boothId = '" + id + "'";
	         Query queryObject = getSession().createQuery(queryString);
	        return (ConfBooth)queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

	public List<ConfBooth> findByExample(ConfBooth instance) {
		log.debug("finding ConfBooth instance by example");
		try {
			List<ConfBooth> results = (List<ConfBooth>) getSession()
					.createCriteria(
							"com.centling.conference.booth.entity.ConfBooth")
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
		log.debug("finding ConfBooth instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfBooth as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfBooth> findByParentId(Object parentId) {
		return findByProperty(PARENT_ID, parentId);
	}

	public List<ConfBooth> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}
	
	public List<ConfBooth> findByExhibitorId(Object exhibitorId) {
		return findByProperty(EXHIBITOR_ID, exhibitorId);
	}

	public List<ConfBooth> findByBoothCompany(Object boothCompany) {
		return findByProperty(BOOTH_COMPANY, boothCompany);
	}

	public List<ConfBooth> findByBoothName(Object boothName) {
		return findByProperty(BOOTH_NAME, boothName);
	}

	public List<ConfBooth> findByBoothLintelName(Object boothLintelName) {
		return findByProperty(BOOTH_LINTEL_NAME, boothLintelName);
	}

	public List<ConfBooth> findByBoothState(Object boothState) {
		return findByProperty(BOOTH_STATE, boothState);
	}

	public List findAll() {
		log.debug("finding all ConfBooth instances");
		try {
			String queryString = "from ConfBooth";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfBooth merge(ConfBooth detachedInstance) {
		log.debug("merging ConfBooth instance");
		try {
			ConfBooth result = (ConfBooth) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfBooth instance) {
		log.debug("attaching dirty ConfBooth instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfBooth instance) {
		log.debug("attaching clean ConfBooth instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<ConfBooth> findAvailableBooths(String meetingId,String exhibitorId) {
		log.debug("finding ConfBooth instance with meetingId:" + meetingId
				+ ", userId: null or " + exhibitorId);
		try {
			//meetingId exhibitorId
			//(exhibitor_id ='22' OR exhibitor_id IS NULL)
			String queryString = "from ConfBooth as model where model.meetingId = ? AND (model.exhibitorId = ? OR model.boothState = 1)";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, meetingId);
			queryObject.setParameter(1, exhibitorId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property meetingId,userId  failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ConfBooth> retrieve(PageBean pageBean,ConfBooth confBooth, String meetingId) {
		log.debug("finding all ConfBooth instances");
		try {
			String queryString = "from ConfBooth as model where model.meetingId=? ";
			List<String> param = new ArrayList<String>();
			if (confBooth.getBoothCompany() != "" && confBooth.getBoothCompany() != null) {
				queryString += " and model.boothCompany like ? ";
				param.add("%"+confBooth.getBoothCompany()+"%");
			}
			if (confBooth.getBoothState() != "" && confBooth.getBoothState() != null) {
				queryString += " and model.boothState = ? ";
				param.add(confBooth.getBoothState());
			}
			if (confBooth.getParentId()!=""&&confBooth.getParentId()!=null){
				queryString += " and model.parentId = ? ";
				param.add(confBooth.getParentId());
			}
			queryString += " order by model.boothId";

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
	
	public String count(String meetingId, ConfBooth confBooth) {
		log.debug("finding all ConfBooth instances");
		try {
			String queryString = "select count(*) from ConfBooth as e where e.meetingId= ? ";
			List<String> param = new ArrayList<String>();
			if (confBooth.getBoothCompany() != "" && confBooth.getBoothCompany() != null) {
				queryString += " and e.boothCompany like ? ";
				param.add("%"+confBooth.getBoothCompany()+"%");
			}
			if (String.valueOf(confBooth.getBoothState()) != "" && confBooth.getBoothState() != null) {
				queryString += " and e.boothState = ? ";
				param.add(confBooth.getBoothState());
			}
			if (confBooth.getParentId()!=""&&confBooth.getParentId()!=null){
				queryString += " and e.parentId = ? ";
				param.add(confBooth.getParentId());
			}
			queryString += " order by e.boothId";

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
	
	public void deleteById(String id) {
		log.debug("delete ConfExhibition instances id= "+id);
		try {
			String queryString = "delete from conf_exhibit_booth where booth_id = ?";
			getSession().createSQLQuery(queryString).setString(0, id).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete from conf_exhibit_booth booth_id= "+id+" failed", re);
			throw re;
		}
	}
	

	/**
	 * 查找展位根据parentIds
	 */
	public String countBoothByParents(String ids){
		log.debug("finding all ConfBooth instances by parentIds:"+ids);
		try {
			String queryString = " SELECT COUNT(*) FROM conf_exhibition a LEFT JOIN conf_exhibit_booth b ON a.exhibition_id=b.parent_id WHERE b.parent_id IN (?) ";
			Query query = getSession().createSQLQuery(queryString).setString(0, ids);
			return query.uniqueResult().toString();
		} catch (RuntimeException re) {
			log.error("finding all failed", re);
			throw re;
		}
	}
}