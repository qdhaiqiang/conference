package com.centling.conference.event.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.event.entity.ConfEvent;
import com.centling.conference.meetinguser.entity.ConfGuest;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfEvent entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.event.entity.ConfEvent
 * @author MyEclipse Persistence Tools
 */

@Repository("confEventDAO")
public class ConfEventDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfEventDAO.class);
    // property constants
    public static final String MEETING_ID = "meetingId";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String REMARK = "remark";
    public static final String UPDATE_DATE = "updateDate";

    public void save(ConfEvent transientInstance) {
        log.debug("saving ConfEvent instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfEvent persistentInstance) {
        log.debug("deleting ConfEvent instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfEvent findById(java.lang.String id) {
        log.debug("getting ConfEvent instance with id: " + id);
        try {
            ConfEvent instance = (ConfEvent) getSession().get(
                    "com.centling.conference.event.entity.ConfEvent", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfEvent> findByExample(ConfEvent instance) {
        log.debug("finding ConfEvent instance by example");
        try {
            List<ConfEvent> results = (List<ConfEvent>) getSession()
                    .createCriteria(
                            "com.centling.conference.event.entity.ConfEvent")
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
        log.debug("finding ConfEvent instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from ConfEvent as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<ConfEvent> findByMeetingId(Object meetingId) {
        return findByProperty(MEETING_ID, meetingId);
    }

    public List<ConfEvent> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List<ConfEvent> findByType(Object type) {
        return findByProperty(TYPE, type);
    }

    public List<ConfEvent> findByRemark(Object remark) {
        return findByProperty(REMARK, remark);
    }

    public List<ConfEvent> findByUpdateDate(Object updateDate) {
        return findByProperty(UPDATE_DATE, updateDate);
    }

    public List findAll() {
        log.debug("finding all ConfEvent instances");
        try {
            String queryString = "from ConfEvent";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfEvent merge(ConfEvent detachedInstance) {
        log.debug("merging ConfEvent instance");
        try {
            ConfEvent result = (ConfEvent) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfEvent instance) {
        log.debug("attaching dirty ConfEvent instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfEvent instance) {
        log.debug("attaching clean ConfEvent instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    //初期化
    @SuppressWarnings("unchecked")
    public List<ConfEvent> retrieve(PageBean pageBean, String meetingId) {
        log.debug("finding all ConfEvent instances");
        try {
            List<ConfEvent> list = null;
            Query query = null;
            String queryString = "from ConfEvent as model where model.meetingId = ? ";
             
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
        log.debug("finding count ConfEvent instances");
        try {
            Query query = null;
            String queryString = "select count(*) from conf_event where meeting_id = ?";
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
            String sql = "delete from ConfEvent model where model.id in ( :id)";
            Query query = getSession().createQuery(sql);
            query.setParameterList("id", ids.split(","));
            int row = query.executeUpdate();
            return row;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    //通过会议ID 和用户类型 查询user信息(事件定制用) 
    public List<Map> findUserByUserType(String meetingId, String assignUserTypes, ConfGuest user) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            String queryString = "select cu.id as userId,cmu.meeting_id as meetingId, cmu.user_type as userType," +
            		    " cu.cname as cname, cu.ename as ename, cu.sex as sex, cu.email as email, cu.mobile as mobile " +
            		    " from conf_meeting_user as cmu left join conf_user cu on cmu.user_id = cu.id " +
                        " where cmu.meeting_id =:meetingId and cmu.user_type in (:userType) ";
            //添加检索条件
            if (user!=null && !StringUtils.isEmpty(user.getCname())) {
                queryString+=" and (cu.cname like :cname or cu.ename like :ename)";
            }
            if (user!=null && !StringUtils.isEmpty(user.getEmail())) {
                queryString+=" and cu.email like :email ";
            }
            Query query = getSession().createSQLQuery(queryString);
            query.setString("meetingId", meetingId);
            if (user!=null && !StringUtils.isEmpty(user.getCname())) {
                query.setString("cname", "%"+user.getCname()+"%");
                query.setString("ename", "%"+user.getCname()+"%");
            }
            if (user!=null && !StringUtils.isEmpty(user.getEmail())) {
                query.setString("email", "%"+user.getEmail()+"%");
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                query.setParameterList("userType", user.getUserType().split(","));
            } else {
                query.setParameterList("userType", assignUserTypes.split(","));
            }
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    } 
    
}