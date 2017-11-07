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
@Repository("myScheduleDAO")
public class MyScheduleDAO extends BaseHibernateDAO  {

	private static final Logger log = LoggerFactory
			.getLogger(MyScheduleDAO.class);

	public List findMeetingSchedualByUser(String meetingId,String userId) {
		log.debug("findMeetingSchedualinstances");
		StringBuffer buff = new StringBuffer();
		try {
			buff.append("select sch.location,sch.title,sch.title_en,sch.start_time,sch.end_time,sch.intro,sch.intro_en ");
			buff.append("from conf_Schedule_user t left join conf_schedule as sch ");
			buff.append("on t.schedule_id = sch.id ");
			buff.append("and t.meeting_id = sch.meeting_id ");
			buff.append("where t.user_id = :userId  ");
			buff.append("and t.meeting_id = :mettingId ");
			buff.append("order by sch.id  ");
			Query query = getSession().createSQLQuery(buff.toString());
			query.setString("mettingId", meetingId);
			query.setString("userId", userId);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find findMeetingSchedual failed", re);
			throw re;
		}
	}
}