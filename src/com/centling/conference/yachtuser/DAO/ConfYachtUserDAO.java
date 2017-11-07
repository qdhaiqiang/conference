package com.centling.conference.yachtuser.DAO;

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
import com.centling.conference.yachtuser.entity.ConfYachtUser;

/**
 * A data access object (DAO) providing persistence and search support for ConfYachtUser entities. Transaction control of the save(), update() and delete() operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.yachtuser.entity.ConfYachtUser
 * @author MyEclipse Persistence Tools
 */
@Repository("confYachtUserDAO")
public class ConfYachtUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ConfYachtUserDAO.class);
	// property constants
	public static final String CNAME = "cname";
	public static final String SEX = "sex";
	public static final String NATION = "nation";
	public static final String CERT_VALUE = "certValue";
	public static final String BITRH = "bitrh";
	public static final String COMPANY = "company";
	public static final String POSITION = "position";
	public static final String MOBILE = "mobile";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String CERT_PIC1 = "certPic1";
	public static final String CERT_PIC2 = "certPic2";
	public static final String ARRIVE_NUM = "arriveNum";
	public static final String ARRIVE_TIME = "arriveTime";
	public static final String PICK_LOCATION = "pickLocation";
	public static final String USER_TYPE = "userType";
	public static final String ENTRANCE = "entrance";
	public static final String BUY_INFO = "buyInfo";
	public static final String PUSH_INFO = "pushInfo";

	public void save(ConfYachtUser transientInstance) {
		log.debug("saving ConfYachtUser instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfYachtUser persistentInstance) {
		log.debug("deleting ConfYachtUser instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	/**
	 * 更新ConfYachtUser信息
	 * @param user
	 * @return
	 */
	public boolean update(ConfYachtUser user){
		boolean isOK = false;
		
		log.debug("edit ConYachtUser info with user: " + user);
		try {
			getSession().update(user);
			isOK = true;
		} catch (RuntimeException re) {
			log.error("ConfYachtUser update fail", re);
			throw re;
		}
		return isOK;
	}
	public ConfYachtUser findById(java.lang.String id) {
		log.debug("getting ConfYachtUser instance with id: " + id);
		try {
			ConfYachtUser instance = (ConfYachtUser) getSession().get("com.centling.conference.yachtuser.entity.ConfYachtUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 查看邮箱是否存在
	 * @param id
	 * @return
	 */
	public boolean emailIsExist(java.lang.String email) {
		boolean isOK = false;
		log.debug("getting ConfYachtUser instance with email: " + email);
		try {
			String queryString = "from ConfYachtUser where email=:email";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("email", email);
			if(queryObject.list().size() > 0){
				isOK = true;
			}
		} catch (RuntimeException re) {
			log.error("emailIsExist fail", re);
			throw re;
		}
		return isOK;
	}
	/**
	 * 查看邮箱密码是否正确
	 * @param id
	 * @return
	 */
	public boolean loginIn(java.lang.String email,java.lang.String password) {
		boolean isOK = false;
		log.debug("check can or not login in by email and password: " + email + password);
		try {
			String queryString = "from ConfYachtUser where email=:email";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("email", email);
			ConfYachtUser user = (ConfYachtUser) queryObject.list().get(0);

			if(password.equals(user.getPassword())||password==user.getPassword()){
				isOK = true;
			}
		} catch (RuntimeException re) {
			log.error("loginIn Check fail", re);
			throw re;
		}
		return isOK;
	}
	public List<ConfYachtUser> findByExample(ConfYachtUser instance) {
		log.debug("finding ConfYachtUser instance by example");
		try {
			List<ConfYachtUser> results = (List<ConfYachtUser>) getSession().createCriteria("com.centling.conference.yachtuser.entity.ConfYachtUser").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ConfYachtUser instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from ConfYachtUser as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfYachtUser> findByCname(Object cname) {
		return findByProperty(CNAME, cname);
	}

	public List<ConfYachtUser> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List<ConfYachtUser> findByNation(Object nation) {
		return findByProperty(NATION, nation);
	}

	public List<ConfYachtUser> findByCertValue(Object certValue) {
		return findByProperty(CERT_VALUE, certValue);
	}

	public List<ConfYachtUser> findByBitrh(Object bitrh) {
		return findByProperty(BITRH, bitrh);
	}

	public List<ConfYachtUser> findByCompany(Object company) {
		return findByProperty(COMPANY, company);
	}

	public List<ConfYachtUser> findByPosition(Object position) {
		return findByProperty(POSITION, position);
	}

	public List<ConfYachtUser> findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List<ConfYachtUser> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<ConfYachtUser> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<ConfYachtUser> findByCertPic1(Object certPic1) {
		return findByProperty(CERT_PIC1, certPic1);
	}

	public List<ConfYachtUser> findByCertPic2(Object certPic2) {
		return findByProperty(CERT_PIC2, certPic2);
	}

	public List<ConfYachtUser> findByArriveNum(Object arriveNum) {
		return findByProperty(ARRIVE_NUM, arriveNum);
	}

	public List<ConfYachtUser> findByArriveTime(Object arriveTime) {
		return findByProperty(ARRIVE_TIME, arriveTime);
	}

	public List<ConfYachtUser> findByPickLocation(Object pickLocation) {
		return findByProperty(PICK_LOCATION, pickLocation);
	}

	public List<ConfYachtUser> findByUserType(Object userType) {
		return findByProperty(USER_TYPE, userType);
	}

	public List<ConfYachtUser> findByEntrance(Object entrance) {
		return findByProperty(ENTRANCE, entrance);
	}

	public List<ConfYachtUser> findByBuyInfo(Object buyInfo) {
		return findByProperty(BUY_INFO, buyInfo);
	}

	public List<ConfYachtUser> findByPushInfo(Object pushInfo) {
		return findByProperty(PUSH_INFO, pushInfo);
	}

	public List findAll() {
		log.debug("finding all ConfYachtUser instances");
		try {
			String queryString = "from ConfYachtUser";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfYachtUser merge(ConfYachtUser detachedInstance) {
		log.debug("merging ConfYachtUser instance");
		try {
			ConfYachtUser result = (ConfYachtUser) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfYachtUser instance) {
		log.debug("attaching dirty ConfYachtUser instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfYachtUser instance) {
		log.debug("attaching clean ConfYachtUser instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List<Map<String, Object>> retrieve(PageBean pageBean, String cname, String userType) {
		log.debug("finding yatchUser Pagition");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer();
			queryString.append("select cname,case sex when '1' then '男' when '2' then '女' end as sex,b.name as nation,cert_value as certValue,")
				.append("birth,company,position,mobile,email,address,arrive_num as arriveNum,arrive_time as arriveTime,pick_location as pickLocation,")
				.append("case user_type when '1' then '买家' when '2' then '嘉宾' when '3' then '游客' end as userType,veri_code as veriCode ")
				.append("from conf_yacht_user a left join conf_dict b on a.nation=b.code where b.category_id='2' ");
			if (StringUtils.isNotEmpty(cname)) {
				queryString.append("and cname like :cname ");
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryString.append("and user_type = :userType ");
			}
			queryString.append("order by cname");
			queryObject = getSession().createSQLQuery(queryString.toString());
			if (StringUtils.isNotEmpty(cname)) {
				queryObject.setString("cname", "%"+cname+"%");
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryObject.setString("userType", userType);
			}
			queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
   		 		.setMaxResults(pageBean.getRows())
   		 		.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Long count(String cname, String userType) {
		log.debug("finding ConfYachtUser count");
		try {
			String queryString = "select count(1) from ConfYachtUser where 1=1 ";
			if (StringUtils.isNotEmpty(cname)) {
				queryString+="and cname like :cname ";
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryString+="and user_type = :userType ";
			}
			Query queryObject = getSession().createQuery(queryString);
			if (StringUtils.isNotEmpty(cname)) {
				queryObject.setString("cname", "%"+cname+"%");
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryObject.setString("userType", userType);
			}
			return (Long)queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List<Object[]> exportUser(String cname, String userType) {
		log.debug("finding ConfYachtUser export");
		try {
			Query queryObject = null;
			StringBuffer queryString = new StringBuffer();
			queryString.append("select cname,case sex when '1' then '男' when '2' then '女' end as sex,b.name as nation,cert_value as certValue,")
				.append("birth,company,position,mobile,email,address,arrive_num as arriveNum,arrive_time as arriveTime,pick_location as pickLocation,")
				.append("case user_type when '1' then '买家' when '2' then '嘉宾' when '3' then '游客' end as userType,veri_code as veriCode,")
				.append("cert_pic1 as certPic1,cert_pic2 as certPic2 ")
				.append("from conf_yacht_user a left join conf_dict b on a.nation=b.code where b.category_id='2' ");
			if (StringUtils.isNotEmpty(cname)) {
				queryString.append("and cname like :cname ");
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryString.append("and user_type = :userType ");
			}
			queryString.append("order by cname");
			queryObject = getSession().createSQLQuery(queryString.toString());
			if (StringUtils.isNotEmpty(cname)) {
				queryObject.setString("cname", "%"+cname+"%");
			}
			if (StringUtils.isNotEmpty(userType)) {
				queryObject.setString("userType", userType);
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}