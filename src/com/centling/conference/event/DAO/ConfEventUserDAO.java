package com.centling.conference.event.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.event.entity.ConfEventUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEventUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.event.entity.ConfEventUser
 * @author MyEclipse Persistence Tools
 */

@Repository("confEventUserDAO")
public class ConfEventUserDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfEventUserDAO.class);
    // property constants
    public static final String UPDATE_DATE = "updateDate";

    public void save(ConfEventUser transientInstance) {
        log.debug("saving ConfEventUser instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfEventUser persistentInstance) {
        log.debug("deleting ConfEventUser instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfEventUser findById(java.lang.String id) {
        log.debug("getting ConfEventUser instance with id: " + id);
        try {
            ConfEventUser instance = (ConfEventUser) getSession().get(
                    "com.centling.conference.event.entity.ConfEventUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfEventUser> findByExample(ConfEventUser instance) {
        log.debug("finding ConfEventUser instance by example");
        try {
            List<ConfEventUser> results = (List<ConfEventUser>) getSession()
                    .createCriteria(
                            "com.centling.conference.event.entity.ConfEventUser")
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
        log.debug("finding ConfEventUser instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ConfEventUser as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<ConfEventUser> findByUpdateDate(Object updateDate) {
        return findByProperty(UPDATE_DATE, updateDate);
    }

    public List findAll() {
        log.debug("finding all ConfEventUser instances");
        try {
            String queryString = "from ConfEventUser";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfEventUser merge(ConfEventUser detachedInstance) {
        log.debug("merging ConfEventUser instance");
        try {
            ConfEventUser result = (ConfEventUser) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfEventUser instance) {
        log.debug("attaching dirty ConfEventUser instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfEventUser instance) {
        log.debug("attaching clean ConfEventUser instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void deleteByEventId(String eventId) {
        log.debug("deleting ConfEventUser instance");
        try {
            String queryString = "delete from conf_event_user where event_id = :eventId";
            Query queryObject = getSession().createSQLQuery(queryString).setString("eventId", eventId);
            log.debug("delete successful");
            queryObject.executeUpdate();
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    //查找已经选择该事件的嘉宾
    public List<Map> findSelectedUserByEventId(String eventId) {
        String queryString = "select GROUP_CONCAT(ceu.user_id SEPARATOR ',') as selectedIds from conf_event_user ceu where event_id =:eventId ";
        return getSession().createSQLQuery(queryString).setString("eventId", eventId)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    
    //查找已经选择该事件的嘉宾人数
    public String findSelectedUserCount(String eventId) {
        String queryString = "select count(*) as userCount from conf_event_user ceu where ceu.event_id =:eventId ";
        return getSession().createSQLQuery(queryString).setString("eventId", eventId).uniqueResult().toString();
    }

    //查找选中事件的嘉宾
   public List<Map> findUserByEventId (String meetingId, String eventId) {
        log.debug("finding ConfEventUser instance");
           try {
               String query = "SELECT ceu.user_id as userId, cu.cname as name, cmu.user_type as userType " +
                    "FROM conf_event_user ceu INNER JOIN conf_user cu ON ceu.user_id = cu.id " +
                    "LEFT JOIN conf_meeting_user cmu ON cmu.user_id = ceu.user_id " +
                    "where cmu.meeting_id = :meetingId and ceu.event_id = :eventId";
               log.debug("find successful");
               return getSession().createSQLQuery(query).setString("meetingId", meetingId).setString("eventId", eventId)
                       .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
           } catch (RuntimeException re) {
               log.error("find failed", re);
               throw re;
           }
    }
}