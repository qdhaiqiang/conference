package com.centling.conference.assignment.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.assignment.entity.ConfVAssignment;
import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.meetinguser.entity.ConfMeetingUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfVAssignment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @see com.centling.conference.assignment.entity.ConfVAssignment
 * @author MyEclipse Persistence Tools
 */
@Repository("confVAssignmentDAO")
public class ConfVAssignmentDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfVAssignmentDAO.class);

    // property constants

    public void save(ConfVAssignment transientInstance) {
        log.debug("saving ConfVAssignment instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfVAssignment persistentInstance) {
        log.debug("deleting ConfVAssignment instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfVAssignment findById(
            com.centling.conference.assignment.entity.ConfVAssignment id) {
        log.debug("getting ConfVAssignment instance with id: " + id);
        try {
            ConfVAssignment instance = (ConfVAssignment) getSession()
                    .get("com.centling.conference.assignment.entity.ConfVAssignment",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfVAssignment> findByExample(ConfVAssignment instance) {
        log.debug("finding ConfVAssignment instance by example");
        try {
            List<ConfVAssignment> results = (List<ConfVAssignment>) getSession()
                    .createCriteria(
                            "com.centling.conference.assignment.entity.ConfVAssignment")
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
        log.debug("finding ConfVAssignment instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ConfVAssignment as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all ConfVAssignment instances");
        try {
            String queryString = "from ConfVAssignment";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfVAssignment merge(ConfVAssignment detachedInstance) {
        log.debug("merging ConfVAssignment instance");
        try {
            ConfVAssignment result = (ConfVAssignment) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfVAssignment instance) {
        log.debug("attaching dirty ConfVAssignment instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfVAssignment instance) {
        log.debug("attaching clean ConfVAssignment instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    /**
     * 人员指派信息页面初始化数据取得
     * @param pageBean page信息
     * @param meetingId 会议ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> retrive(PageBean pageBean, String meetingId, ConfGuest user) {
        log.debug("finding all ConfVAssignment instances");
        try {
            Query query = null;
            String queryString = "select * from conf_v_assignment where meetingId = ?";
            if (user.getCname() != null && !user.getCname().equals("")) {
                queryString += " and (cname like :cname or ename like :ename )";
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                queryString += " and guestType in (:guestType)";
            } else {
                queryString += " and assignId is not null ";
            }
            if (user.getRemindFlag() != null && !user.getRemindFlag().equals("")) {
                queryString += " and remindFlag = :flag ";
            }
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            if (user.getCname() != null && !user.getCname().equals("")) {
                query.setString("cname", "%"+user.getCname()+"%");
                query.setString("ename", "%"+user.getCname()+"%");
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                query.setParameterList("guestType", user.getUserType().split(","));
            }
            if (user.getRemindFlag() != null && !user.getRemindFlag().equals("")) {
                query.setString("flag", user.getRemindFlag());
            }
            return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }
    
    /**
     * 查找会议的指派人员数量
     *
     */
    public String count(String meetingId, ConfGuest user) {
        log.debug("finding count ConfVAssignment instance");
        try {
            Query query = null;
            String queryString = "select count(*) from conf_v_assignment where meetingId = ?";
            if (user.getCname() != null && !user.getCname().equals("")) {
                queryString += " and (cname like :cname or ename like :ename )";
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                queryString += " and guestType in (:guestType)";
            } else {
                queryString += " and assignId is not null ";
            }
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            if (user.getCname() != null && !user.getCname().equals("")) {
                query.setString("cname", "%"+user.getCname()+"%");
                query.setString("ename", "%"+user.getCname()+"%");
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                query.setParameterList("guestType", user.getUserType().split(","));
            }
            return query.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 查找特定嘉宾的指派人员数量
     *
     */
    public List<Map> findAssignedUsers(String meetingId, String guestId) {
        log.debug("finding conf_v_assignment ConfVAssignment instance");
        try {
            Query query = null;
            String queryString = "select * from conf_v_assignment where meetingId = ? and guestId = ?";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId).setString(1, guestId);
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public List<Map> findByUserType (String meetingId, String userType) {
        log.debug("finding conf_v_assignment ConfVAssignment instance");
        try {
            Query query = null;
            String queryString = "select * from conf_v_assignment where meetingId = ? and assginType = ?";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId).setString(1, userType);
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    /**
     * 人员指派信息页面初始化数据取得
     * @param pageBean page信息
     * @param meetingId 会议ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> retriveOther(PageBean pageBean, String meetingId) {
        log.debug("finding all ConfVAssignment instances");
        try {
            Query query = null;
            String queryString = "select cs.meeting_id as meetingId,cs.title as title, cs.id as scheduleId, " +
                    "assign.user_id as assignId, assign.user_name as assignName " +
                    "FROM conf_assignment_other assign " +
                    "RIGHT JOIN conf_schedule cs ON assign.schedule_id = cs.id " +
                    "where cs.meeting_id = ?";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
                    .setMaxResults(pageBean.getRows())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
        } catch (Exception re) {
            log.error("find all failed", re);
        }
        return null;
    }
    
    /**
     * 查找会议的指派人员数量
     *
     */
    public String countOther(String meetingId) {
        log.debug("finding count ConfVAssignment instance");
        try {
            Query query = null;
            String queryString = "select count(*) FROM conf_assignment_other assign " +
                    "RIGHT JOIN conf_schedule cs ON assign.schedule_id = cs.id " +
                    "where cs.meeting_id = ?";
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            return query.uniqueResult().toString();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
}