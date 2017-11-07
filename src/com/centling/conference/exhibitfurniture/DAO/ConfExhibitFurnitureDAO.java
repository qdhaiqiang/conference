package com.centling.conference.exhibitfurniture.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibitFurniture entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture
 * @author MyEclipse Persistence Tools
 */

@Repository("confExhibitFurnitureDAO")
public class ConfExhibitFurnitureDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitFurnitureDAO.class);
	// property constants
	public static final String MEETING_ID = "meetingId";
	public static final String FURNITURE_CODE = "furnitureCode";
	public static final String FURNITURE_NAME = "furnitureName";
	public static final String FURNITURE_NAME_EN = "furnitureNameEn";
	public static final String FURNITURE_DESC = "furnitureDesc";
	public static final String FURNITURE_DESC_EN = "furnitureDescEn";
	public static final String FURNITURE_TOTAL_MOUNT = "furnitureTotalMount";
	public static final String FURNITURE_RENT_MOUNT = "furnitureRentMount";
	public static final String FURNITURE_RENT_TYPE = "furnitureRentType";
	public static final String FURNITURE_PRICE = "furniturePrice";
	public static final String FURNITURE_PRICE_EN = "furniturePriceEn";
	public static final String MEMO = "memo";

	public void save(ConfExhibitFurniture transientInstance) {
		log.debug("saving ConfExhibitFurniture instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibitFurniture persistentInstance) {
		log.debug("deleting ConfExhibitFurniture instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibitFurniture findById(java.lang.String id) {
		log.debug("getting ConfExhibitFurniture instance with id: " + id);
		try {
			ConfExhibitFurniture instance = (ConfExhibitFurniture) getSession()
					.get("com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibitFurniture> findByExample(
			ConfExhibitFurniture instance) {
		log.debug("finding ConfExhibitFurniture instance by example");
		try {
			List<ConfExhibitFurniture> results = (List<ConfExhibitFurniture>) getSession()
					.createCriteria(
							"com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture")
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
		log.debug("finding ConfExhibitFurniture instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibitFurniture as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibitFurniture> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfExhibitFurniture> findByFurnitureCode(Object furnitureCode) {
		return findByProperty(FURNITURE_CODE, furnitureCode);
	}

	public List<ConfExhibitFurniture> findByFurnitureName(Object furnitureName) {
		return findByProperty(FURNITURE_NAME, furnitureName);
	}

	public List<ConfExhibitFurniture> findByFurnitureNameEn(
			Object furnitureNameEn) {
		return findByProperty(FURNITURE_NAME_EN, furnitureNameEn);
	}

	public List<ConfExhibitFurniture> findByFurnitureDesc(Object furnitureDesc) {
		return findByProperty(FURNITURE_DESC, furnitureDesc);
	}

	public List<ConfExhibitFurniture> findByFurnitureDescEn(
			Object furnitureDescEn) {
		return findByProperty(FURNITURE_DESC_EN, furnitureDescEn);
	}

	public List<ConfExhibitFurniture> findByFurnitureTotalMount(
			Object furnitureTotalMount) {
		return findByProperty(FURNITURE_TOTAL_MOUNT, furnitureTotalMount);
	}

	public List<ConfExhibitFurniture> findByFurnitureRentMount(
			Object furnitureRentMount) {
		return findByProperty(FURNITURE_RENT_MOUNT, furnitureRentMount);
	}

	public List<ConfExhibitFurniture> findByFurnitureRentType(
			Object furnitureRentType) {
		return findByProperty(FURNITURE_RENT_TYPE, furnitureRentType);
	}

	public List<ConfExhibitFurniture> findByFurniturePrice(Object furniturePrice) {
		return findByProperty(FURNITURE_PRICE, furniturePrice);
	}

	public List<ConfExhibitFurniture> findByFurniturePriceEn(
			Object furniturePriceEn) {
		return findByProperty(FURNITURE_PRICE_EN, furniturePriceEn);
	}

	public List<ConfExhibitFurniture> findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all ConfExhibitFurniture instances");
		try {
			String queryString = "from ConfExhibitFurniture";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibitFurniture merge(ConfExhibitFurniture detachedInstance) {
		log.debug("merging ConfExhibitFurniture instance");
		try {
			ConfExhibitFurniture result = (ConfExhibitFurniture) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibitFurniture instance) {
		log.debug("attaching dirty ConfExhibitFurniture instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibitFurniture instance) {
		log.debug("attaching clean ConfExhibitFurniture instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<ConfExhibitFurniture> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding all ConfExhibitFurniture instances");
        try {
            String queryString = "from ConfExhibitFurniture where meetingId=?";
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
		log.debug("finding all ConfExhibitFurniture count");
        try {
            String queryString = "select count(1) from ConfExhibitFurniture where meetingId = ?";
            return (Long)(getSession().createQuery(queryString).setString(0, meetingId).uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
	
	public void update(ConfExhibitFurniture confExhibitFurniture) {
		log.debug("updating ConfExhibitFurniture instance");
		try {
			getSession().update(confExhibitFurniture);
			log.debug("update successful");
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
	}
}