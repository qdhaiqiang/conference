package com.centling.conference.meeting.DAO;

import static org.hibernate.criterion.Example.create;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.meeting.entity.ConfVMeetingIndex;
import com.centling.conference.meeting.entity.ConfVMeetingIndexId;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfVMeetingIndex entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.meeting.entity.ConfVMeetingIndex
 * @author MyEclipse Persistence Tools
 */
@Repository("confVMeetingIndexDAO")
public class ConfVMeetingIndexDAO extends BaseHibernateDAO {
    private static final Logger log = LoggerFactory
            .getLogger(ConfVMeetingIndexDAO.class);

    // property constants

    public void save(ConfVMeetingIndex transientInstance) {
        log.debug("saving ConfVMeetingIndex instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(ConfVMeetingIndex persistentInstance) {
        log.debug("deleting ConfVMeetingIndex instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public ConfVMeetingIndex findById(
            com.centling.conference.meeting.entity.ConfVMeetingIndexId id) {
        log.debug("getting ConfVMeetingIndex instance with id: " + id);
        try {
            ConfVMeetingIndex instance = (ConfVMeetingIndex) getSession().get(
                    "com.centling.conference.meeting.entity.ConfVMeetingIndex",
                    id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<ConfVMeetingIndex> findByExample(ConfVMeetingIndex instance) {
        log.debug("finding ConfVMeetingIndex instance by example");
        try {
            List<ConfVMeetingIndex> results = (List<ConfVMeetingIndex>) getSession()
                    .createCriteria(
                            "com.centling.conference.meeting.entity.ConfVMeetingIndex")
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
        log.debug("finding ConfVMeetingIndex instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from ConfVMeetingIndex as model where model."
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
        log.debug("finding all ConfVMeetingIndex instances");
        try {
            String queryString = "from ConfVMeetingIndex";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public ConfVMeetingIndex merge(ConfVMeetingIndex detachedInstance) {
        log.debug("merging ConfVMeetingIndex instance");
        try {
            ConfVMeetingIndex result = (ConfVMeetingIndex) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfVMeetingIndex instance) {
        log.debug("attaching dirty ConfVMeetingIndex instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(ConfVMeetingIndex instance) {
        log.debug("attaching clean ConfVMeetingIndex instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ConfVMeetingIndexId> retrieve(PageBean pageBean, String sysDate) {
        log.debug("finding all conf_v_meeting_index instances");
        try {
            String queryString = "SELECT id,name,start_time as startTime, end_time as endTime, city, num" +
            		" from conf_v_meeting_index where date_format(end_time,'%Y/%m/%d %H:%i:%s') >= ?";
            List<Object[]> list = getSession().createSQLQuery(queryString)
            .setString(0, sysDate).setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            .setMaxResults(pageBean.getRows()).list();
            List<ConfVMeetingIndexId> resultList = new ArrayList<ConfVMeetingIndexId>();
            for (Object object[] : list) {
            	ConfVMeetingIndexId confVMeetingIndexId = new ConfVMeetingIndexId();
            	confVMeetingIndexId.setId(object[0].toString());
            	confVMeetingIndexId.setName(object[1].toString());
            	confVMeetingIndexId.setStartTime(object[2].toString());
            	confVMeetingIndexId.setEndTime(object[3].toString());
            	confVMeetingIndexId.setCity(object[4].toString());
            	confVMeetingIndexId.setNum(Long.parseLong(object[5].toString()));
            	resultList.add(confVMeetingIndexId);
			}
            return resultList;
        } catch (Exception re) {
            log.error("find all failed", re);
        }
		return null;
    }
    
    public Long count(String sysDate) {
        log.debug("finding all LcdEquipment instances");
        try {
            String queryString = "select count(1) from conf_meeting where date_format(end_time,'%Y/%m/%d %H:%i:%s') >= ?";
            return ((BigInteger)(getSession().createSQLQuery(queryString).setString(0, sysDate).uniqueResult()))
            		.longValue();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }
}