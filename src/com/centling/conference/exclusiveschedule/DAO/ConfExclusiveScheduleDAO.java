package com.centling.conference.exclusiveschedule.DAO;


import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.event.entity.ConfEvent;
import com.centling.conference.exclusiveschedule.entity.ConfVInfo;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.schedule.entity.ConfSchedule;

/**
 * 专属日程DAO
 * @author lizzh
 *
 */
@Repository("confExclusiveScheduleDAO")
public class ConfExclusiveScheduleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfMeetingUserDAO.class);
	/**
	 * 查询嘉宾人员
	 * @param meetingId 会议ID
	 * @param assignId  指派人员ID
	 * @return
	 */
	public String getGuestId(String meetingId, String assignId){
		log.debug("finding guest_id");
		String sql = "SELECT a.guest_id FROM conf_assignment a INNER JOIN conf_sys_user b ON a.user_id = b.id INNER JOIN conf_user c ON c.email = b.login_name where a.meeting_id = ? and b.id = ?";
		Query queryObject = getSession().createSQLQuery(sql);
		queryObject.setString(0, meetingId);
		queryObject.setString(1, assignId);
		return (String)queryObject.uniqueResult();
	}
	/**
	 * 查询日程信息
	 * @param guestId
	 * @return
	 */
	public List<ConfSchedule> getScheduleInfo(String guestId, String meetingId){
		log.debug("finding schedule info");
		String sql = "select * from conf_schedule a where a.id in (select schedule_id from conf_schedule_user where user_id=? and meeting_id = ?)";
		Query queryObject = getSession().createSQLQuery(sql).addEntity(ConfSchedule.class);
		queryObject.setString(0, guestId);
		queryObject.setString(1, meetingId);
		return queryObject.list();
	}
	/**
	 * 事件定制
	 * @param guestId
	 * @param meetingId
	 * @return
	 */
	public List<ConfEvent> getEventInfo(String guestId, String meetingId ){
		log.debug("finding schedule info");
		String sql = "select * from conf_event a where a.id in (select event_id from conf_event_user where user_id=? and meeting_id = ?)";
		Query queryObject = getSession().createSQLQuery(sql).addEntity(ConfEvent.class);
		queryObject.setString(0, guestId);
		queryObject.setString(1, meetingId);
		return queryObject.list();
	}
	/**
	 * 查询个人信息
	 * @param guestId
	 * @param meetingId
	 * @return
	 */
	public ConfVInfo getVsInfo(String guestId, String meetingId){
		log.debug("finding vsInfo");
		try {
            String queryString = "from ConfVInfo as model where model.userId=? and model.meetingId=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, guestId);
            queryObject.setParameter(1, meetingId);
            return (ConfVInfo) queryObject.uniqueResult();
        } catch (RuntimeException re) {
            log.error("find vInfo failed", re);
            throw re;
        }
	}
	
    /**
     * 专属日程初始页面
     * @param pageBean page信息
     * @param meetingId 会议ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Map> retrive(PageBean pageBean, String meetingId, ConfGuest user) {
        log.debug("finding all ConfVAssignment instances");
        try {
            Query query = null;
            String queryString = "select * from conf_v_assignment where meetingId = ? and guestType not in ('7','8','9','18','19','20') ";
            if (user.getCname() != null && !user.getCname().equals("")) {
                queryString += " and (cname like :cname or ename like :ename ) ";
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                queryString += " and guestType in (:guestType)";
            }
            query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            if (user.getCname() != null && !user.getCname().equals("")) {
                query.setString("cname", "%"+user.getCname()+"%");
                query.setString("ename", "%"+user.getCname()+"%");
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                query.setParameterList("guestType", user.getUserType().split(","));
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
     * 查找指派列表中的数量
     *
     */
    public String count(String meetingId, ConfGuest user) {
        log.debug("finding count ConfVAssignment instance");
        try {
            Query query = null;
            String queryString = "select count(*) from conf_v_assignment where meetingId = ? and guestType not in ('7','8','9','18','19','20') ";
            if (user.getCname() != null && !user.getCname().equals("")) {
                queryString += " and (cname like :cname or ename like :ename )";
            }
            if (user.getUserType() != null && !user.getUserType().equals("")) {
                queryString += " and guestType in (:guestType)";
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
    
}
