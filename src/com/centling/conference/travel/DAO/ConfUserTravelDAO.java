package com.centling.conference.travel.DAO;

import static org.hibernate.criterion.Example.create;

import java.math.BigInteger;
import java.util.ArrayList;
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
import com.centling.conference.travel.entity.ConfUserTravel;
import com.centling.conference.travel.entity.SearchParams;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfUserTravel entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.travel.entity.ConfUserTravel
 * @author MyEclipse Persistence Tools
 */

@Repository("confUserTravelDAO")
public class ConfUserTravelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfUserTravelDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String MEETING_ID = "meetingId";
	public static final String TYPE_COME = "typeCome";
	public static final String NUMBER_COME = "numberCome";
	public static final String START_PLACE_COME = "startPlaceCome";
	public static final String END_PLACE_COME = "endPlaceCome";
	public static final String START_TIME_COME = "startTimeCome";
	public static final String END_TIME_COME = "endTimeCome";
	public static final String REMARK_COME = "remarkCome";
	public static final String TYPE_GO = "typeGo";
	public static final String NUMBER_GO = "numberGo";
	public static final String START_PLACE_GO = "startPlaceGo";
	public static final String END_PLACE_GO = "endPlaceGo";
	public static final String START_TIME_GO = "startTimeGo";
	public static final String END_TIME_GO = "endTimeGo";
	public static final String REMARK_GO = "remarkGo";

	public void save(ConfUserTravel transientInstance) {
		log.debug("saving ConfUserTravel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfUserTravel persistentInstance) {
		log.debug("deleting ConfUserTravel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfUserTravel findById(java.lang.String id) {
		log.debug("getting ConfUserTravel instance with id: " + id);
		try {
			ConfUserTravel instance = (ConfUserTravel) getSession().get(
					"com.centling.conference.travel.entity.ConfUserTravel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfUserTravel> findByExample(ConfUserTravel instance) {
		log.debug("finding ConfUserTravel instance by example");
		try {
			List<ConfUserTravel> results = (List<ConfUserTravel>) getSession()
					.createCriteria(
							"com.centling.conference.travel.entity.ConfUserTravel")
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
		log.debug("finding ConfUserTravel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfUserTravel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfUserTravel> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<ConfUserTravel> findByMeetingId(Object meetingId) {
		return findByProperty(MEETING_ID, meetingId);
	}

	public List<ConfUserTravel> findByTypeCome(Object typeCome) {
		return findByProperty(TYPE_COME, typeCome);
	}

	public List<ConfUserTravel> findByNumberCome(Object numberCome) {
		return findByProperty(NUMBER_COME, numberCome);
	}

	public List<ConfUserTravel> findByStartPlaceCome(Object startPlaceCome) {
		return findByProperty(START_PLACE_COME, startPlaceCome);
	}

	public List<ConfUserTravel> findByEndPlaceCome(Object endPlaceCome) {
		return findByProperty(END_PLACE_COME, endPlaceCome);
	}

	public List<ConfUserTravel> findByStartTimeCome(Object startTimeCome) {
		return findByProperty(START_TIME_COME, startTimeCome);
	}

	public List<ConfUserTravel> findByEndTimeCome(Object endTimeCome) {
		return findByProperty(END_TIME_COME, endTimeCome);
	}

	public List<ConfUserTravel> findByRemarkCome(Object remarkCome) {
		return findByProperty(REMARK_COME, remarkCome);
	}

	public List<ConfUserTravel> findByTypeGo(Object typeGo) {
		return findByProperty(TYPE_GO, typeGo);
	}

	public List<ConfUserTravel> findByNumberGo(Object numberGo) {
		return findByProperty(NUMBER_GO, numberGo);
	}

	public List<ConfUserTravel> findByStartPlaceGo(Object startPlaceGo) {
		return findByProperty(START_PLACE_GO, startPlaceGo);
	}

	public List<ConfUserTravel> findByEndPlaceGo(Object endPlaceGo) {
		return findByProperty(END_PLACE_GO, endPlaceGo);
	}

	public List<ConfUserTravel> findByStartTimeGo(Object startTimeGo) {
		return findByProperty(START_TIME_GO, startTimeGo);
	}

	public List<ConfUserTravel> findByEndTimeGo(Object endTimeGo) {
		return findByProperty(END_TIME_GO, endTimeGo);
	}

	public List<ConfUserTravel> findByRemarkGo(Object remarkGo) {
		return findByProperty(REMARK_GO, remarkGo);
	}

	public List findAll() {
		log.debug("finding all ConfUserTravel instances");
		try {
			String queryString = "from ConfUserTravel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfUserTravel merge(ConfUserTravel detachedInstance) {
		log.debug("merging ConfUserTravel instance");
		try {
			ConfUserTravel result = (ConfUserTravel) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfUserTravel instance) {
		log.debug("attaching dirty ConfUserTravel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfUserTravel instance) {
		log.debug("attaching clean ConfUserTravel instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> retrieve(String email,String cname,String meetingId) {
		log.debug("finding user info  ConfUserEx");
		try {
			String queryString = "select t.userType, t.userId, t.cname,t.certValue,t.departurePlace,t.departureDate,t.departureTimePeriod," +
					"t.returnPlace, t.returnDate, t.returnTimePeriod,t.mobile from conf_v_info t where t.meetingId=:meetingId ";
			if (StringUtils.isNotEmpty(email)) {
				queryString+="and email=:email ";
			}
			if (StringUtils.isNotEmpty(cname)) {
				queryString+="and cname=:cname";
			}
			Query queryObject = getSession().createSQLQuery(queryString).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			queryObject.setParameter("meetingId", meetingId);
			if (StringUtils.isNotEmpty(email)) {
				queryObject.setParameter("email", email);
			}
			if (StringUtils.isNotEmpty(cname)) {
				queryObject.setParameter("cname", cname);
			}
			
			return queryObject.list();
            
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Object[]> getTravelStatic(String meetingId,SearchParams searchParams) {
		log.debug("finding Travel statistics");
		try {
			
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select b.cname, c.user_type as userType,case a.type_come when '1' then '飞机' when '2' then '高铁' end as typeCome,a.number_come as numberCome,a.start_place_come as startPlaceCome, ");
			queryString.append(" a.end_place_come as endPlaceCome,a.start_time_come as startTimeCome,a.end_time_come as endTimeCome,case a.type_go when '1' then '飞机' when '2' then '高铁' end as typeGo, ");
			queryString.append(" a.number_go as numberGo,a.start_place_go as startPlaceGo,a.end_place_go as endPlaceGo,a.start_time_go as startTimeGo,a.end_time_go as endTimeGo,b.checkpoint ");
			queryObject = selectUserSql(queryObject,queryString, meetingId, searchParams);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Map<String, Object>> retrieve(PageBean pageBean, String meetingId,SearchParams searchParams) {
		log.debug("finding Travel statistics Pagition");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select a.id,b.cname, c.user_type as userType,a.type_come as typeCome,a.number_come as numberCome,a.start_place_come as startPlaceCome, ");
			queryString.append(" a.end_place_come as endPlaceCome,a.start_time_come as startTimeCome,a.end_time_come as endTimeCome,a.type_go as typeGo, a.email_send as emailSend,a.message_send as messageSend,");
			queryString.append(" a.number_go as numberGo,a.start_place_go as startPlaceGo,a.end_place_go as endPlaceGo,a.start_time_go as startTimeGo,a.end_time_go as endTimeGo,b.checkpoint ");
			
			queryObject = selectUserSql(queryObject,queryString, meetingId, searchParams);
			queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
   		 		.setMaxResults(pageBean.getRows())
   		 		.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Long count(String meetingId,SearchParams searchParams) {
		log.debug("finding Travel statistics Count");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer(" select count(1) ");
			queryObject = selectUserSql(queryObject,queryString, meetingId, searchParams);
			return ((BigInteger)queryObject.uniqueResult()).longValue();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Query selectUserSql(Query query,StringBuffer queryString,String meetingId,SearchParams searchParams){
		queryString.append(" from conf_user_travel a left join conf_v_info b on a.user_id=b.userId left join conf_meeting_user c on a.meeting_id=c.meeting_id and b.userId=c.user_id ");
		
		List<String> param = new ArrayList<String>();
		queryString.append(" where c.meeting_id=? ");
		param.add(meetingId);
		if(searchParams.getCname() !=null && !searchParams.getCname().equals("")){
			queryString.append(" AND b.cname LIKE ? ");
			param.add("%"+searchParams.getCname()+"%");
		}
		if(searchParams.getUserType() !=null && !searchParams.getUserType().equals("")){
			queryString.append(" AND b.userType LIKE ? ");
			param.add("%"+searchParams.getUserType()+"%");
		}
		if(searchParams.getOrganizerPay() !=null && !searchParams.getOrganizerPay().equals("")){
			queryString.append(" AND c.organizer_pay=? ");
			param.add(searchParams.getOrganizerPay());
		}
		if(searchParams.getCheckpoint() !=null && !searchParams.getCheckpoint().equals("")){
		    queryString.append(" AND b.checkpoint=? ");
		    param.add(searchParams.getCheckpoint());
		}
		if (StringUtils.isNotEmpty(searchParams.getMessageSend())) {
			queryString.append(" AND (a.message_send=? or a.email_send=?) ");
		    param.add(searchParams.getMessageSend());
		    param.add(searchParams.getMessageSend());
		}
		//启程
		if(searchParams.getTypeCome() !=null && !searchParams.getTypeCome().equals("")){
			queryString.append(" AND a.type_come=? ");
			param.add(searchParams.getTypeCome());
		}
		if(searchParams.getNumberCome() !=null && !searchParams.getNumberCome().equals("")){
			queryString.append(" AND a.number_come LIKE ? ");
			param.add("%"+searchParams.getNumberCome()+"%");
		}
		if(searchParams.getStartPlaceCome() !=null && !searchParams.getStartPlaceCome().equals("")){
			queryString.append(" AND a.start_place_come LIKE ? ");
			param.add("%"+searchParams.getStartPlaceCome()+"%");
		}
		if(searchParams.getEndPlaceCome() !=null && !searchParams.getEndPlaceCome().equals("")){
			queryString.append(" AND a.end_place_come LIKE ? ");
			param.add("%"+searchParams.getEndPlaceCome()+"%");
		}
		if(searchParams.getStartTimeCome() !=null && !searchParams.getStartTimeCome().equals("")){
			queryString.append(" AND TO_DAYS(a.start_time_come)>=TO_DAYS(?) ");
			param.add(searchParams.getStartTimeCome());
		}
		if(searchParams.getEndTimeCome() !=null && !searchParams.getEndTimeCome().equals("")){
			queryString.append(" AND TO_DAYS(a.end_time_come)<=TO_DAYS(?) ");
			param.add(searchParams.getEndTimeCome());
		}
		
		//返程
		if(searchParams.getTypeGo() !=null && !searchParams.getTypeGo().equals("")){
			queryString.append(" AND a.type_go=? ");
			param.add(searchParams.getTypeGo());
		}
		if(searchParams.getNumberGo() !=null && !searchParams.getNumberGo().equals("")){
			queryString.append(" AND a.number_go LIKE ? ");
			param.add("%"+searchParams.getNumberGo()+"%");
		}
		if(searchParams.getStartPlaceGo() !=null && !searchParams.getStartPlaceGo().equals("")){
			queryString.append(" AND a.start_place_go LIKE ? ");
			param.add("%"+searchParams.getStartPlaceGo()+"%");
		}
		if(searchParams.getEndPlaceGo() !=null && !searchParams.getEndPlaceGo().equals("")){
			queryString.append(" AND a.end_place_go LIKE ? ");
			param.add("%"+searchParams.getEndPlaceGo()+"%");
		}
		if(searchParams.getStartTimeGo() !=null && !searchParams.getStartTimeGo().equals("")){
			queryString.append(" AND TO_DAYS(a.start_time_go)>=TO_DAYS(?) ");
			param.add(searchParams.getStartTimeGo());
		}
		if(searchParams.getEndTimeGo() !=null && !searchParams.getEndTimeGo().equals("")){
			queryString.append(" AND TO_DAYS(a.end_time_go)<=TO_DAYS(?) ");
			param.add(searchParams.getEndTimeGo());
		}
		query = getSession().createSQLQuery(queryString.toString());
		for(int i=0;i<param.size();i++){
            query.setString(i, param.get(i));
        }
		
		return query;
	}

	public List<Map<String, Object>> getEndPlaceCome(String meetingId) {
		log.debug("finding Distinct EndPlaceCome");
		try {
			String queryString = "select distinct end_place_come from conf_user_travel where meeting_id=?";
			return getSession().createSQLQuery(queryString).setString(0, meetingId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find Distinct EndPlaceCome failed", re);
			throw re;
		}
	}

	public List<Map<String, Object>> getStartPlaceGo(String meetingId) {
		log.debug("finding Distinct StartPlaceGo");
		try {
			String queryString = "select distinct start_place_go from conf_user_travel where meeting_id=?";
			return getSession().createSQLQuery(queryString).setString(0, meetingId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find Distinct StartPlaceGo failed", re);
			throw re;
		}
	}

	public void updateSendStatus(String travelId,String noticeEmail, String noticeMsg) {
		log.debug("Update SendStatus");
		try {
			String updateString = StringUtils.EMPTY;
			if (StringUtils.isNotEmpty(noticeEmail) && StringUtils.isNotEmpty(noticeMsg)) {
				updateString = "update conf_user_travel set email_send=1,message_send=1 where id=:id";
			} else if (StringUtils.isNotEmpty(noticeEmail) && StringUtils.isEmpty(noticeMsg)) {
				updateString = "update conf_user_travel set email_send=1 where id=:id";
			} else if (StringUtils.isNotEmpty(noticeMsg) && StringUtils.isEmpty(noticeEmail)) {
				updateString = "update conf_user_travel set message_send=1 where id=:id";
			}
			getSession().createSQLQuery(updateString).setString("id", travelId).executeUpdate();
			log.debug("Update send status successful");
		} catch (RuntimeException re) {
			log.error("Update send status failed", re);
			throw re;
		}
	}
}