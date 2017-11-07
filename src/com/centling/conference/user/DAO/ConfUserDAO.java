package com.centling.conference.user.DAO;

import static org.hibernate.criterion.Example.create;

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
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.sysuser.entity.ConfSysUser;
import com.centling.conference.user.entity.ConfUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.centling.conference.user.entity.ConfUser
 * @author MyEclipse Persistence Tools
 */
/**
 * @author lenovo
 *
 */
@Repository("confUserDAO")
public class ConfUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfUserDAO.class);
	// property constants
	public static final String CNAME = "cname";
	public static final String LAST_NAME = "lastName";
	public static final String ENAME = "ename";
	public static final String SEX = "sex";
	public static final String BIRTH = "birth";
	public static final String NATION = "nation";
	public static final String CERT_TYPE = "certType";
	public static final String CERT_VALUE = "certValue";
	public static final String CERT_EXPIRY_DATE = "certExpiryDate";
	public static final String CERT_PIC1 = "certPic1";
	public static final String CERT_PIC2 = "certPic2";
	public static final String EMAIL = "email";
	public static final String TELE = "tele";
	public static final String MOBILE = "mobile";
	public static final String FAX = "fax";
	public static final String ADDRESS = "address";
	public static final String POSTCODE = "postcode";
	public static final String PHOTO = "photo";
	public static final String MEDL_HIS = "medlHis";
	public static final String RELIGION = "religion";
	public static final String SELF_INTRO = "selfIntro";
	public static final String INDUSTRY = "industry";
	public static final String COMPANY = "company";
	public static final String POSITION = "position";
	public static final String VISA_NEED = "visaNeed";
	public static final String VISA_CITY = "visaCity";
	public static final String CONTACT_PERSON = "contactPerson";
	public static final String VEGETARIAN = "vegetarian";
	public static final String APPROVE_STATE = "approveState";
	public static final String PASSWORD = "password";
	public static final String MAIL_CHECKED = "mailChecked";
	public static final String DIET_TABOO = "dietTaboo";
	public static final String ETIQUETTE = "etiquette";
	public static final String OFFICIAL_LANG = "officialLang";
	public static final String FOOD_ALLERGIES = "foodAllergies";
	public static final String SELF_INTRO_EN = "selfIntroEn";
	public static final String WORK_CONTENT = "workContent";
	public static final String SUPERVISERNAME = "supervisername";
	public static final String SUPERVISERPHONE = "superviserphone";

	public void save(ConfUser transientInstance) {
		log.debug("saving ConfUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfUser persistentInstance) {
		log.debug("deleting ConfUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfUser findById(java.lang.String id) {
		log.debug("getting ConfUser instance with id: " + id);
		try {
			ConfUser instance = (ConfUser) getSession().get(
					"com.centling.conference.user.entity.ConfUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfUser> findByExample(ConfUser instance) {
		log.debug("finding ConfUser instance by example");
		try {
			List<ConfUser> results = (List<ConfUser>) getSession()
					.createCriteria(
							"com.centling.conference.user.entity.ConfUser")
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
		log.debug("finding ConfUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ConfUser as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfUser> findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List<ConfUser> findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List<ConfUser> findByEname(Object ename) {
		return findByProperty(ENAME, ename);
	}

	public List<ConfUser> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List<ConfUser> findByBirth(Object birth) {
		return findByProperty(BIRTH, birth);
	}

	public List<ConfUser> findByNation(Object nation) {
		return findByProperty(NATION, nation);
	}

	public List<ConfUser> findByCertType(Object certType) {
		return findByProperty(CERT_TYPE, certType);
	}

	public List<ConfUser> findByCertValue(Object certValue) {
		return findByProperty(CERT_VALUE, certValue);
	}

	public List<ConfUser> findByCertExpiryDate(Object certExpiryDate) {
		return findByProperty(CERT_EXPIRY_DATE, certExpiryDate);
	}

	public List<ConfUser> findByCertPic1(Object certPic1) {
		return findByProperty(CERT_PIC1, certPic1);
	}

	public List<ConfUser> findByCertPic2(Object certPic2) {
		return findByProperty(CERT_PIC2, certPic2);
	}

	public List<ConfUser> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<ConfUser> findByTele(Object tele) {
		return findByProperty(TELE, tele);
	}

	public List<ConfUser> findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List<ConfUser> findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	public List<ConfUser> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<ConfUser> findByPostcode(Object postcode) {
		return findByProperty(POSTCODE, postcode);
	}

	public List<ConfUser> findByPhoto(Object photo) {
		return findByProperty(PHOTO, photo);
	}

	public List<ConfUser> findByMedlHis(Object medlHis) {
		return findByProperty(MEDL_HIS, medlHis);
	}

	public List<ConfUser> findByReligion(Object religion) {
		return findByProperty(RELIGION, religion);
	}

	public List<ConfUser> findBySelfIntro(Object selfIntro) {
		return findByProperty(SELF_INTRO, selfIntro);
	}

	public List<ConfUser> findByIndustry(Object industry) {
		return findByProperty(INDUSTRY, industry);
	}

	public List<ConfUser> findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List<ConfUser> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<ConfUser> findByVisaNeed(Object visaNeed) {
		return findByProperty(VISA_NEED, visaNeed);
	}

	public List<ConfUser> findByVisaCity(Object visaCity) {
		return findByProperty(VISA_CITY, visaCity);
	}

	public List<ConfUser> findByContactPerson(Object contactPerson) {
		return findByProperty(CONTACT_PERSON, contactPerson);
	}

	public List<ConfUser> findByVegetarian(Object vegetarian) {
		return findByProperty(VEGETARIAN, vegetarian);
	}

	public List<ConfUser> findByApproveState(Object approveState) {
		return findByProperty(APPROVE_STATE, approveState);
	}

	public List<ConfUser> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<ConfUser> findByMailChecked(Object mailChecked) {
		return findByProperty(MAIL_CHECKED, mailChecked);
	}

	public List<ConfUser> findByDietTaboo(Object dietTaboo) {
		return findByProperty(DIET_TABOO, dietTaboo);
	}
	
	public List<ConfUser> findByEtiquette(Object etiquette) {
		return findByProperty(ETIQUETTE, etiquette);
	}
	
	public List<ConfUser> findByOfficialLang(Object officialLang) {
		return findByProperty(OFFICIAL_LANG, officialLang);
	}
	
	public List<ConfUser> findByFoodAllergies(Object foodAllergies) {
		return findByProperty(FOOD_ALLERGIES, foodAllergies);
	}
	
	public List<ConfUser> findBySelfIntroEn(Object selfIntroEn) {
		return findByProperty(SELF_INTRO_EN, selfIntroEn);
	}

	public List<ConfUser> findByWorkContent(Object workContent) {
		return findByProperty(WORK_CONTENT, workContent);
	}

	public List<ConfUser> findBySupervisername(Object supervisername) {
		return findByProperty(SUPERVISERNAME, supervisername);
	}

	public List<ConfUser> findBySuperviserphone(Object superviserphone) {
		return findByProperty(SUPERVISERPHONE, superviserphone);
	}
	
	public List findAll() {
		log.debug("finding all ConfUser instances");
		try {
			String queryString = "from ConfUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Map> findUsers(PageBean pageBean, String meetingId, ConfGuest user) {
		log.debug("finding all ConfUser instances");
		try {
            String queryString = "select a.id as id, a.cname as cname, a.sex as sex, b.user_type as userType, a.mobile as mobile, a.email as email from conf_user a " +
            		"left join conf_meeting_user b on a.id = b.user_id where b.meeting_id = :meetingId ";
            //添加检索条件
            if (user!=null && StringUtils.isEmpty(user.getUserType()) && !StringUtils.isEmpty(user.getCname())) {
              queryString += "  and (a.cname like :cname)";
            }
            if (user!=null && !StringUtils.isEmpty(user.getUserType())) {
              queryString += "  and (b.user_type = :usertype)";
            }
            Query query = getSession().createSQLQuery(queryString);
            query.setString("meetingId", meetingId);
            if (user != null && StringUtils.isEmpty(user.getUserType()) && !StringUtils.isEmpty(user.getCname())) {
               query.setString("cname", "%" + user.getCname() + "%");
            }
            if (user != null && !StringUtils.isEmpty(user.getUserType())) {
               query.setString("usertype", user.getUserType());
            }
            query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
   		 		.setMaxResults(pageBean.getRows());
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List<Map> findUsersById(PageBean pageBean, String userIds, String meetingId, ConfGuest user) {
		log.debug("finding AssignedUserByScheduleId instances");
		try {
			String queryString = "select a.id as assignId, a.cname as cname, a.sex as sex, b.user_type as userType, a.mobile as mobile, a.email as email from conf_user a " +
            		"left join conf_meeting_user b on a.id = b.user_id "
					+"left join conf_sys_user c on a.email = c.mail "
            		+" where c.id in (:id) and b.meeting_id = :meetingId ";
			
			//添加检索条件
            if (user!=null && StringUtils.isEmpty(user.getUserType()) && !StringUtils.isEmpty(user.getCname())) {
              queryString += "  and (a.cname like :cname)";
            }
            if (user!=null && !StringUtils.isEmpty(user.getUserType())) {
              queryString += "  and (b.user_type = :usertype)";
            }
            
			Query query = getSession().createSQLQuery(queryString);
			query.setString("meetingId", meetingId); 
			if (!StringUtils.isEmpty(userIds)) {
				query.setParameterList("id", userIds.split(","));
			} else {
				query.setString("id", "");
			}
			
			if (user != null && StringUtils.isEmpty(user.getUserType()) && !StringUtils.isEmpty(user.getCname())) {
               query.setString("cname", "%" + user.getCname() + "%");
            }
            if (user != null && !StringUtils.isEmpty(user.getUserType())) {
               query.setString("usertype", user.getUserType());
            }
            query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
		 		.setMaxResults(pageBean.getRows());
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	

	public ConfUser merge(ConfUser detachedInstance) {
		log.debug("merging ConfUser instance");
		try {
			ConfUser result = (ConfUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfUser instance) {
		log.debug("attaching dirty ConfUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfUser instance) {
		log.debug("attaching clean ConfUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
   public void update(ConfUser instance) {
        log.debug("update dirty ConfUser instance");
        try {
            getSession().update(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
   
   public void updateApprove(ConfGuest instance) {
       log.debug("update dirty ConfUser instance");
       try {
           String stringQuery = "update conf_user set approve_state = ? where id = ?";
           Query query = getSession().createSQLQuery(stringQuery);
           query.setString(0, instance.getApproveState());
           query.setString(1, instance.getUserId());
           query.executeUpdate();
           log.debug("attach successful");
       } catch (RuntimeException re) {
           log.error("attach failed", re);
           throw re;
       }
   }
   
   public void updatePass(ConfUser confUser) {
	   log.debug("update dirty ConfUser instance");
	   try {
		   String updateSql = "update conf_user set password=? where email=?";
		   Query query = getSession().createSQLQuery(updateSql);
		   query.setString(0, confUser.getPassword());
		   query.setString(1, confUser.getEmail());
		   query.executeUpdate();
		   log.debug("attach successful");
	   } catch (RuntimeException re) {
		   log.error("attach failed", re);
           throw re;
	   }
   }
   
   public void deleteById(String userId) {
       log.debug("deleting ConfUser instance");
       try {
           String query = "delete from conf_user where id = ?";
           getSession().createSQLQuery(query).setString(0, userId).executeUpdate();
           log.debug("delete successful");
       } catch (RuntimeException re) {
           log.error("delete failed", re);
           throw re;
       }
   }

	public List<Object[]> findUserByEmail(String meetingId,String email) {
		String query = "SELECT a.id,a.cname,a.ename FROM conf_user a LEFT JOIN conf_meeting_user b ON a.id=b.user_id " +  
				"WHERE b.user_type IN (1,12,2,13,3,14) AND a.email=? and b.meeting_Id=?";
		return getSession().createSQLQuery(query).setString(0, email).setString(1, meetingId).list();
	}

	public List<Map<String, Object>> getUnimportUser(ConfSysUser confSysUser, String meetingId) {
		log.debug("getUnimportUser");
		try {
			String queryStr = "select a.id,a.cname,a.sex,b.user_type as userType,a.mobile,a.email from conf_user a " + 
					"right join conf_meeting_user b on a.id=b.user_id "+
					"where b.meeting_id=:meetingId and b.user_type in (7,8,9,18,19,20) " + 
					"and not exists(select 1 from conf_sys_user where login_name=a.email)";
			if (confSysUser!=null && !StringUtils.isEmpty(confSysUser.getLoginName())) {
				queryStr+=" and a.cname like :cname ";
			}
			if (confSysUser!=null && !StringUtils.isEmpty(confSysUser.getMail())) {
				queryStr+=" and a.email like :email ";
			}
			Query query = getSession().createSQLQuery(queryStr);
			query.setString("meetingId", meetingId);
			if (confSysUser!=null && !StringUtils.isEmpty(confSysUser.getLoginName())) {
				query.setString("cname", "%"+confSysUser.getLoginName()+"%");
			}
			if (confSysUser!=null && !StringUtils.isEmpty(confSysUser.getMail())) {
				query.setString("email", "%"+confSysUser.getMail()+"%");
			}
            log.debug("getUnimportUser successful");
            return (List<Map<String, Object>>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		} catch (RuntimeException re) {
            log.error("getUnimportUser failed", re);
            throw re;
		}
	}
	
	public String findNameByUserId (String userId) {
	    log.debug("finding ConfUser instance");
	       try {
	           String query = "select cname from conf_user where id = ?";
	           log.debug("find successful");
	           return getSession().createSQLQuery(query).setString(0, userId).uniqueResult().toString();
	       } catch (RuntimeException re) {
	           log.error("find failed", re);
	           throw re;
	       }
	}
	public String findTelByUserId(String userId){
		log.debug("finding ConfUser instance");
		try{
			String query = "select mobile from conf_user where id= ?";
			log.debug("find successful");
			return getSession().createSQLQuery(query).setString(0, userId).uniqueResult().toString();
		}catch (RuntimeException re){
			log.error("find failed",re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> retrive(PageBean pageBean, ConfUser confUser, String meetingId, String userType) {
		log.debug("find all confusers in meeting "+meetingId+" and other conditions");
		String queryString = "select conf_user.id,conf_user.cname,conf_user.sex,conf_user.company,conf_meeting_user.user_type,conf_user.mobile,conf_user.email from conf_user left join conf_meeting_user on conf_user.id = conf_meeting_user.user_id  where conf_meeting_user.meeting_id=? ";
		List<String> param = new ArrayList<String>();
		if(confUser.getCname()!=""&&confUser.getCname()!=null){
			queryString += " and conf_user.cname like ? ";
			param.add("%"+confUser.getCname()+"%");
		}
		if(confUser.getCompany()!=""&&confUser.getCompany()!=null){
			queryString += " and conf_user.company like ? ";
			param.add("%"+confUser.getCompany()+"%");
		}
		if(userType!=null&&userType!=""){
			queryString += " and conf_meeting_user.user_type in (:id)";
		}
		queryString+=" order by conf_user.id";
		Query query = getSession().createSQLQuery(queryString).setString(0, meetingId);
		for(int i = 0; i < param.size(); i++){
			query.setString(i+1, param.get(i));
		}
		if(userType!=null&&userType!=""){
			query.setParameterList("id", userType.split(","));
		}
		try{
			return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows()).setMaxResults(pageBean.getRows()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		}catch (RuntimeException re){
			log.error("find all failed",re);
			throw re;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> all(String meetingId){
		String queryString ="select conf_user.id,conf_user.cname,conf_user.sex,conf_user.company,conf_meeting_user.user_type from conf_user left join conf_meeting_user on conf_user.id = conf_meeting_user.user_id order by conf_user.id";
		Query query = getSession().createSQLQuery(queryString);
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	public String count(String meetingId, ConfGuest confUser) {
		log.debug("count all confUsers in meeting "+meetingId+"and other conditions ");
		String queryString = " select count(*) from conf_user left join conf_meeting_user on conf_user.id = conf_meeting_user.user_id where conf_meeting_user.meeting_id=?";
		List<String> param = new ArrayList<String>();
		if(confUser.getCname()!=""&&confUser.getCname()!=null){
			queryString += " and conf_user.cname like ? ";
			param.add("%"+confUser.getCname()+"%");
		}
		if(confUser.getUserType() != null && confUser.getUserType() != ""){
			queryString += " and conf_meeting_user.user_type = :user_type";
		}
		queryString+=" order by conf_user.id";
		Query query = getSession().createSQLQuery(queryString).setString(0, meetingId);
		for(int i = 0; i < param.size(); i++){
			query.setString(i+1, param.get(i));
		}
		if(confUser.getUserType() != null && confUser.getUserType() != ""){
			query.setString("user_type", confUser.getUserType());
		}
		
		try{
			return query.uniqueResult().toString();
		}catch(RuntimeException re){
			log.error("count all failed");
			throw re;
		}
		
	}
	
	public String count(String meetingId, String userIds, ConfGuest confUser) {
		log.debug("count confUsers in meeting " + meetingId + " and userid in userids and other conditions ");
		String queryString = " select count(*) from conf_user a " +
				"left join conf_meeting_user b on a.id = b.user_id " +
				"left join conf_sys_user c on a.email = c.mail " +
				"where b.meeting_id=? and c.id in (:id)";
		List<String> param = new ArrayList<String>();
		if(confUser.getCname()!=""&&confUser.getCname()!=null){
			queryString += " and a.cname like ? ";
			param.add("%"+confUser.getCname()+"%");
		}
		if(confUser.getUserType() != null && confUser.getUserType() != ""){
			queryString += " and b.user_type = :user_type";
		}
		queryString += " order by a.id";
		Query query = getSession().createSQLQuery(queryString).setString(0, meetingId);
		for(int i = 0; i < param.size(); i++){
			query.setString(i+1, param.get(i));
		}
		if(confUser.getUserType() != null && confUser.getUserType() != ""){
			query.setString("user_type", confUser.getUserType());
		}
		if (!StringUtils.isEmpty(userIds)) {
			query.setParameterList("id", userIds.split(","));
		} else {
			query.setString("id", "");
		}
		
		try{
			return query.uniqueResult().toString();
		}catch(RuntimeException re){
			log.error("count all failed");
			throw re;
		}
		
	}
	
	public String count(String meetingId, ConfUser confUser, String userType) {
		log.debug("count all confUsers in meeting "+meetingId+"and other conditions ");
		String queryString = " select count(*) from conf_user left join conf_meeting_user on conf_user.id = conf_meeting_user.user_id where conf_meeting_user.meeting_id=?";
		List<String> param = new ArrayList<String>();
		if(confUser.getCname()!=""&&confUser.getCname()!=null){
			queryString += " and conf_user.cname like ? ";
			param.add("%"+confUser.getCname()+"%");
		}
		if(confUser.getCompany()!=""&&confUser.getCompany()!=null){
			queryString += " and conf_user.company like ? ";
			param.add("%"+confUser.getCompany()+"%");
		}
		if(userType!=null&&userType!=""){
			queryString += " and conf_meeting_user.user_type in (:id)";
		}
		queryString+=" order by conf_user.id";
		Query query = getSession().createSQLQuery(queryString).setString(0, meetingId);
		for(int i = 0; i < param.size(); i++){
			query.setString(i+1, param.get(i));
		}
		if(userType!=null&&userType!=""){
			query.setParameterList("id", userType.split(","));
		}
		
		try{
			return query.uniqueResult().toString();
		}catch(RuntimeException re){
			log.error("count all failed");
			throw re;
		}
		
	}
	
   public List<Map> findUserAndUserType (String userId) {
        log.debug("finding ConfUser instance");
           try {
               String query = "select cu.id,cu.cname,cu.email,cu.mobile,cmu.user_type " +
               		"from conf_user cu , conf_meeting_user cmu where cu.id = cmu.user_id and cu.id = ?";
               log.debug("find successful");
               return getSession().createSQLQuery(query).setString(0, userId)
                       .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
           } catch (RuntimeException re) {
               log.error("find failed", re);
               throw re;
           }
    }

   public void deleteByMeetingId(String meetingId) {
	   log.debug("delete ConfUser by meetingId");
       try {
           String query = "delete from conf_v_info where meetingId=? ";
           getSession().createSQLQuery(query).setString(0, meetingId).executeUpdate();
       } catch (RuntimeException re) {
           log.error("delete failed", re);
           throw re;
       }
	}
	
	public void insertDataByMeetingId(String meetingId) {
		log.debug("delete ConfUser by meetingId");
		try {
			String query = "insert into conf_v_info select * from conf_v_info_v where meetingId=? ";
			getSession().createSQLQuery(query).setString(0, meetingId).executeUpdate();
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
 
	/**
	 * 根据日程id查询演讲嘉宾
	 * @param scheduleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findSpeakersByScheduleId(String scheduleId){
		log.debug("finding ConfUser By ScheduleId");
        try {
            String queryString = "select cu.id,cu.cname,cmu.user_type,cu.ename,cu.sex,cu.email,cu.mobile from conf_user cu " +
            		"left join conf_meeting_user cmu on cu.id = cmu.user_id " +
            		"left join conf_schedule_user csu on cu.id = csu.user_id " +
            		"where cmu.user_type in (1,2,12,13) " +
            		"and csu.schedule_id = :scheduleId " +
            		"and cu.id not in (select user_id from conf_schedulelog_user_assign where schdule_id = :scheduleId)";
            Query query = getSession().createSQLQuery(queryString);
            query.setParameter("scheduleId", scheduleId);
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            
        } catch (RuntimeException re) {
            log.error("find failed", re);
            throw re;
        }
	}

	public List<ConfSysUser> getUserByIdList(String userIds) {
		log.debug("finding ConfUser By idList");
        try {
            String queryStr = "from ConfUser where id in (:idList)";
            return getSession().createQuery(queryStr).setParameterList("idList", userIds.split(",")).list();
        } catch (RuntimeException re) {
            log.error("find failed", re);
            throw re;
        }
	}
    //查找用户身份卡信息（导出用户身份卡信息用）
   public List<Object[]> findAllForExportQR (String meetingId) {
       log.debug("finding ConfUser instance");
       try {
           String query = "select cu.id as id,cu.cname as name, cu.card_type as cardType, cu.photo as photo, 1, cmu.user_type as userType " +
                       " from conf_user cu inner join conf_meeting_user cmu on cu.id = cmu.user_id where cmu.meeting_id = :meetingId";
           log.debug("find successful");
           return getSession().createSQLQuery(query).setString("meetingId", meetingId).list();
       } catch (RuntimeException re) {
           log.error("find failed", re);
           throw re;
       }
   }

   public void updateMobile(ConfGuest instance) {
	   log.debug("update confvinfo mobile by userid");
		try {
			String query = "update conf_v_info set mobile=? where userId=? ";
			getSession().createSQLQuery(query).setString(0, instance.getMobile()).setString(1, instance.getUserId()).executeUpdate();
		} catch (RuntimeException re) {
			log.error("update failed", re);
			throw re;
		}
   }
}