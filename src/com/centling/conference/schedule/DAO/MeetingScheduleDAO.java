package com.centling.conference.schedule.DAO;


import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;

/**
 	会议议程
 */
@Repository("meetingScheduleDAO")
public class MeetingScheduleDAO extends BaseHibernateDAO  {

	private static final Logger log = LoggerFactory
			.getLogger(MeetingScheduleDAO.class);

	public List findMeetingSchedual(String meetingId) {
		log.debug("findMeetingSchedualinstances");
		StringBuffer buff = new StringBuffer();
		try {
			buff.append("select sch.location,sch.title,sch.title_en,sch.start_time,sch.end_time,sch.intro,sch.intro_en ");
			buff.append("from conf_schedule as sch ");
			buff.append("where sch.schedule_type='1' and sch.meeting_id = :mettingId ");
			buff.append("order by sch.start_time,sch.id ");
			Query query = getSession().createSQLQuery(buff.toString());
			query.setString("mettingId", meetingId);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find findMeetingSchedual failed", re);
			throw re;
		}
	}
}