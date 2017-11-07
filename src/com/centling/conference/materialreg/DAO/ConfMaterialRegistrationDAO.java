package com.centling.conference.materialreg.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.materialreg.entity.ConfMaterialRegistration;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfMaterialRegistration entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.centling.conference.materialreg.entity.ConfMaterialRegistration
 * @author MyEclipse Persistence Tools
 */
@Repository("ConfMaterialRegistrationDAO")
public class ConfMaterialRegistrationDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfMaterialRegistrationDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String QUANTITY = "quantity";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String DETAIL = "detail";

	public void save(ConfMaterialRegistration transientInstance) {
		log.debug("saving ConfMaterialRegistration instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfMaterialRegistration persistentInstance) {
		log.debug("deleting ConfMaterialRegistration instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfMaterialRegistration findById(java.lang.String id) {
		log.debug("getting ConfMaterialRegistration instance with id: " + id);
		try {
			ConfMaterialRegistration instance = (ConfMaterialRegistration) getSession()
					.get("com.centling.conference.materialreg.entity.ConfMaterialRegistration",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfMaterialRegistration> findByExample(
			ConfMaterialRegistration instance) {
		log.debug("finding ConfMaterialRegistration instance by example");
		try {
			List<ConfMaterialRegistration> results = (List<ConfMaterialRegistration>) getSession()
					.createCriteria(
							"com.centling.conference.materialreg.entity.ConfMaterialRegistration")
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
		log.debug("finding ConfMaterialRegistration instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfMaterialRegistration as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfMaterialRegistration> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfMaterialRegistration> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<ConfMaterialRegistration> findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List<ConfMaterialRegistration> findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List<ConfMaterialRegistration> findByChargePerson(Object chargePerson) {
		return findByProperty(CHARGE_PERSON, chargePerson);
	}

	public List<ConfMaterialRegistration> findByDetail(Object detail) {
		return findByProperty(DETAIL, detail);
	}

	public List findAll() {
		log.debug("finding all ConfMaterialRegistration instances");
		try {
			String queryString = "from ConfMaterialRegistration";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfMaterialRegistration merge(
			ConfMaterialRegistration detachedInstance) {
		log.debug("merging ConfMaterialRegistration instance");
		try {
			ConfMaterialRegistration result = (ConfMaterialRegistration) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfMaterialRegistration instance) {
		log.debug("attaching dirty ConfMaterialRegistration instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfMaterialRegistration instance) {
		log.debug("attaching clean ConfMaterialRegistration instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfMaterialRegistration> retrieve(PageBean pageBean) {
		log.debug("finding all ConfMaterialRegistration instances");
		try {
			String queryString = "from ConfMaterialRegistration cel order by cel.id";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows());
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count() {
		log.debug("finding all ConfMaterialRegistration instances");
		try {
			String queryString = "select count(*) from ConfMaterialRegistration";
	        return (Long)(getSession().createQuery(queryString).uniqueResult());
		} catch (RuntimeException re) {
			log.error("count all failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String id) {
		log.debug("delete ConfMaterialRegistration instances id= "+id);
		int results = 0;
		try {
			String queryString = " DELETE FROM ConfMaterialRegistration where id IN ( :id)";
			Query query = getSession().createQuery(queryString).setParameterList("id", id.split(","));
			results = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete from conf_material_registration id= "+id+" failed", re);
			throw re;
		}
		return results;
	}
}