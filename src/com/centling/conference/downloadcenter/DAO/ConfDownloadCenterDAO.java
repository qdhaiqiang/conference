package com.centling.conference.downloadcenter.DAO;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.meetinguser.DAO.ConfMeetingUserDAO;
/**
 * 资源下载中心DAO
 * @author lizzh
 *
 */
@Repository("confDownloadCenterDAO")
public class ConfDownloadCenterDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfMeetingUserDAO.class);
	/**
	 * 查询待下载列表
	 * @param pageBean
	 * @param meetingId 会议ID
	 * @return
	 */
	public List<Map<String, Object>> retrieve(PageBean pageBean, String meetingId) {
		log.debug("finding resource Pagition");
		try {
			String queryString = "select b.cname,a.name,a.value from conf_dyn_value a left join conf_user b on a.user_id=b.id where type='address' and a.value is not null and a.value!='' and a.meeting_id=?";
			Query queryObject = getSession().createSQLQuery(queryString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			queryObject.setString(0, meetingId).setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
   		 		.setMaxResults(pageBean.getRows());
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Long count(String meetingId) {
		log.debug("finding resource Pagition");
		try {
			String queryString = "select count(1) from conf_dyn_value a left join conf_user b on a.user_id=b.id where type='address' and a.value is not null and a.value!='' and a.meeting_id=?";
			Query queryObject = getSession().createSQLQuery(queryString).setString(0, meetingId);
			return ((BigInteger)queryObject.uniqueResult()).longValue();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}
