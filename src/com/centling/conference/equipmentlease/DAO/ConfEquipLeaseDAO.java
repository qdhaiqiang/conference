package com.centling.conference.equipmentlease.DAO;

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
import com.centling.conference.equipmentlease.entity.ConfEquipLease;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEquipLease entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.equipmentlease.entity.ConfEquipLease
 * @author MyEclipse Persistence Tools
 */
@Repository("confEquipLeaseDAO")
public class ConfEquipLeaseDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfEquipLeaseDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String EQUIP_NAME = "equipName";
	public static final String EQUIP_TYPE = "equipType";
	public static final String SUPPLIER = "supplier";
	public static final String AMOUNT = "amount";
	public static final String DATE_START = "dateStart";
	public static final String DATE_END = "dateEnd";
	public static final String PRINCIPAL = "principal";
	public static final String PRINCIPAL_TEL = "principalTel";
	public static final String INSERT_TIME = "insertTime";
	public static final String INSERT_USER = "insertUser";
	public static final String OTHER = "other";

	public void save(ConfEquipLease transientInstance) {
		log.debug("saving ConfEquipLease instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfEquipLease persistentInstance) {
		log.debug("deleting ConfEquipLease instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfEquipLease findById(java.lang.String id) {
		log.debug("getting ConfEquipLease instance with id: " + id);
		try {
			ConfEquipLease instance = (ConfEquipLease) getSession()
					.get("com.centling.conference.equipmentlease.entity.ConfEquipLease",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfEquipLease> findByExample(ConfEquipLease instance) {
		log.debug("finding ConfEquipLease instance by example");
		try {
			List<ConfEquipLease> results = (List<ConfEquipLease>) getSession()
					.createCriteria(
							"com.centling.conference.equipmentlease.entity.ConfEquipLease")
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
		log.debug("finding ConfEquipLease instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfEquipLease as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfEquipLease> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfEquipLease> findByEquipName(Object equipName) {
		return findByProperty(EQUIP_NAME, equipName);
	}

	public List<ConfEquipLease> findByEquipType(Object equipType) {
		return findByProperty(EQUIP_TYPE, equipType);
	}

	public List<ConfEquipLease> findBySupplier(Object supplier) {
		return findByProperty(SUPPLIER, supplier);
	}

	public List<ConfEquipLease> findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List<ConfEquipLease> findByDateStart(Object dateStart) {
		return findByProperty(DATE_START, dateStart);
	}

	public List<ConfEquipLease> findByDateEnd(Object dateEnd) {
		return findByProperty(DATE_END, dateEnd);
	}

	public List<ConfEquipLease> findByPrincipal(Object principal) {
		return findByProperty(PRINCIPAL, principal);
	}

	public List<ConfEquipLease> findByPrincipalTel(Object principalTel) {
		return findByProperty(PRINCIPAL_TEL, principalTel);
	}

	public List<ConfEquipLease> findByInsertTime(Object insertTime) {
		return findByProperty(INSERT_TIME, insertTime);
	}

	public List<ConfEquipLease> findByInsertUser(Object insertUser) {
		return findByProperty(INSERT_USER, insertUser);
	}

	public List<ConfEquipLease> findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all ConfEquipLease instances");
		try {
			String queryString = "from ConfEquipLease";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfEquipLease merge(ConfEquipLease detachedInstance) {
		log.debug("merging ConfEquipLease instance");
		try {
			ConfEquipLease result = (ConfEquipLease) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfEquipLease instance) {
		log.debug("attaching dirty ConfEquipLease instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfEquipLease instance) {
		log.debug("attaching clean ConfEquipLease instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConfEquipLease> retrieve(PageBean pageBean, String meetingId, ConfEquipLease equip) {
		log.debug("finding all ConfEquipLease instances");
		try {
            List<ConfEquipLease> list = null;
            Query query = null;
			String queryString = "from ConfEquipLease as model where model.meetingId = ? ";
			 
			List<String> param = new ArrayList<String>();
            if (equip.getSupplier() != null && !equip.getSupplier().equals("")) {
            	queryString += " and (model.supplier like ? )";
            	param.add("%"+equip.getSupplier()+"%");
            }
            if (equip.getPrincipal() != null && !equip.getPrincipal().equals("")) {
            	queryString += " and (model.principal like ?) ";
            	param.add("%"+equip.getPrincipal()+"%");
            }
            if (equip.getEquipType() != null && !equip.getEquipType().equals("")) {
            	queryString += " and model.equipType in (:equipType) ";
            }
            
            query = getSession().createQuery(queryString).setString(0, meetingId);
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
            
            if (equip.getEquipType() != null && !equip.getEquipType().equals("")) {
            	query.setParameterList("equipType", equip.getEquipType().split(","));
            }
            
            list = query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
	                    .setMaxResults(pageBean.getRows()).list();
            
            return list;
			 
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public String count(String meetingId, ConfEquipLease equip) {
		log.debug("finding count ConfEquipLease instances");
		try {
			Query query = null;
			String queryString = "select count(*) from conf_equip_lease cel where cel.meeting_id = ?";
	        List<String> param = new ArrayList<String>();
            if (equip.getSupplier() != null && !equip.getSupplier().equals("")) {
            	queryString += " and (cel.supplier like ? )";
            	param.add("%"+equip.getSupplier()+"%");
            }
            if (equip.getPrincipal() != null && !equip.getPrincipal().equals("")) {
            	queryString += " and (cel.principal like ?) ";
            	param.add("%"+equip.getPrincipal()+"%");
            }

            if (equip.getEquipType() != null && !equip.getEquipType().equals("")) {
            	queryString += " and cel.equip_type = ? ";
            	param.add(equip.getEquipType());
            }

            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
            
            return query.uniqueResult().toString();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int deleteByIds(String ids) {
		log.debug("delete ConfEquipLease instances");
		try {
			String sql = "delete from ConfEquipLease where id in ( :id)";
        	Query query = getSession().createQuery(sql);
        	query.setParameterList("id", ids.split(","));
        	int row = query.executeUpdate();
        	return row;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}