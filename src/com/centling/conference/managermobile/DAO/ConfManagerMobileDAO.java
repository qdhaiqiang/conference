package com.centling.conference.managermobile.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.managermobile.entity.ConfManagerMobile;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfManagerMobile entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.managermobile.entity.ConfManagerMobile
 * @author MyEclipse Persistence Tools
 */
@Repository("confManagerMobileDAO")
public class ConfManagerMobileDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfManagerMobileDAO.class);
    // property constants
    public static final String MEETING_ID = "meetingId";
    public static final String MANAGER_NAME = "managerName";
    public static final String MANAGER_MOBILE = "managerMobile";
    public static final String MANAGER_POSITION = "managerPosition";
    public static final String REMARK = "remark";

    public void save(ConfManagerMobile transientInstance) {
        log.debug("saving ConfManagerMobile instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfManagerMobile persistentInstance) {
        log.debug("deleting ConfManagerMobile instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfManagerMobile findById(java.lang.String id) {
        log.debug("getting ConfManagerMobile instance with id: " + id);
        try {
            ConfManagerMobile instance = (ConfManagerMobile) getSession()
                    .get("com.centling.conference.managermobile.entity.ConfManagerMobile",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfManagerMobile> findByExample(ConfManagerMobile instance) {
        log.debug("finding ConfManagerMobile instance by example");
        try {
            List<ConfManagerMobile> results = (List<ConfManagerMobile>) getSession()
                    .createCriteria(
                            "com.centling.conference.managermobile.entity.ConfManagerMobile")
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
        log.debug("finding ConfManagerMobile instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ConfManagerMobile as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<ConfManagerMobile> findByMeetingId(Object meetingId) {
        return findByProperty(MEETING_ID, meetingId);
    }

    public List<ConfManagerMobile> findByManagerName(Object managerName) {
        return findByProperty(MANAGER_NAME, managerName);
    }

    public List<ConfManagerMobile> findByManagerMobile(Object managerMobile) {
        return findByProperty(MANAGER_MOBILE, managerMobile);
    }

    public List<ConfManagerMobile> findByManagerPosition(Object managerPosition) {
        return findByProperty(MANAGER_POSITION, managerPosition);
    }
    
    public List<ConfManagerMobile> findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List findAll() {
        log.debug("finding all ConfManagerMobile instances");
        try {
            String queryString = "from ConfManagerMobile";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfManagerMobile merge(ConfManagerMobile detachedInstance) {
        log.debug("merging ConfManagerMobile instance");
        try {
            ConfManagerMobile result = (ConfManagerMobile) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfManagerMobile instance) {
        log.debug("attaching dirty ConfManagerMobile instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfManagerMobile instance) {
        log.debug("attaching clean ConfManagerMobile instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
    public List<ConfManagerMobile> retrieve(PageBean pageBean, String meetingId) {
        log.debug("finding all ConfEquipLease instances");
        try {
            List<ConfManagerMobile> list = null;
            Query query = null;
            String queryString = "from ConfManagerMobile as model where model.meetingId = ? ";
             
            query = getSession().createQuery(queryString).setString(0, meetingId);
            list = query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                        .setMaxResults(pageBean.getRows()).list();
            return list;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public String count(String meetingId) {
        log.debug("finding count ConfManagerMobile instances");
        try {
            Query query = null;
            String queryString = "select count(*) from conf_manager_mobile cmm where cmm.meeting_id = ?";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            return query.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public int deleteByIds(String ids) {
        log.debug("delete ConfManagerMobile instances");
        try {
            String sql = "delete from ConfManagerMobile model where model.id in ( :id)";
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