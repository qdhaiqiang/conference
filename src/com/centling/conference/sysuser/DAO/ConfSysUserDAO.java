package com.centling.conference.sysuser.DAO;
// default package

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
import com.centling.conference.meetinguser.entity.ConfGuest;
import com.centling.conference.sysuser.entity.ConfSysUser;

/**
 	* A data access object (DAO) providing persistence and search support for ConfSysUser entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .ConfSysUser
  * @author MyEclipse Persistence Tools 
 */
@Repository("confSysUserDAO")
public class ConfSysUserDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfSysUserDAO.class);
		//property constants
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PASSWORD = "loginPassword";
	public static final String MAIL = "mail";
	public static final String STATUS = "status";
	public static final String CREATE_TIME = "createTime";
	public static final String CREATE_REAL_TIME = "createRealTime";
	public static final String ACTIVE_TIME = "activeTime";
	public static final String ACTIVE_REAL_TIME = "activeRealTime";



    
    public void save(ConfSysUser transientInstance) {
        log.debug("saving ConfSysUser instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfSysUser persistentInstance) {
        log.debug("deleting ConfSysUser instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfSysUser findById( java.lang.String id) {
        log.debug("getting ConfSysUser instance with id: " + id);
        try {
            ConfSysUser instance = (ConfSysUser) getSession()
                    .get("com.centling.conference.sysuser.entity.ConfSysUser", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfSysUser> findByExample(ConfSysUser instance) {
        log.debug("finding ConfSysUser instance by example");
        try {
            List<ConfSysUser> results = (List<ConfSysUser>) getSession()
                    .createCriteria("com.centling.conference.sysuser.entity.ConfSysUser")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding ConfSysUser instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfSysUser as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfSysUser> findByLoginName(Object loginName
	) {
		return findByProperty(LOGIN_NAME, loginName
		);
	}
	
	public List<ConfSysUser> findByLoginPassword(Object loginPassword
	) {
		return findByProperty(LOGIN_PASSWORD, loginPassword
		);
	}
	
	public List<ConfSysUser> findByMail(Object mail
	) {
		return findByProperty(MAIL, mail
		);
	}
	
	public List<ConfSysUser> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	
	public List<ConfSysUser> findByCreateTime(Object createTime
	) {
		return findByProperty(CREATE_TIME, createTime
		);
	}
	
	public List<ConfSysUser> findByCreateRealTime(Object createRealTime
	) {
		return findByProperty(CREATE_REAL_TIME, createRealTime
		);
	}
	
	public List<ConfSysUser> findByActiveTime(Object activeTime
	) {
		return findByProperty(ACTIVE_TIME, activeTime
		);
	}
	
	public List<ConfSysUser> findByActiveRealTime(Object activeRealTime
	) {
		return findByProperty(ACTIVE_REAL_TIME, activeRealTime
		);
	}
	

	public List<ConfSysUser> findAll() {
		log.debug("finding all ConfSysUser instances");
		try {
			String queryString = "from ConfSysUser";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfSysUser merge(ConfSysUser detachedInstance) {
        log.debug("merging ConfSysUser instance");
        try {
            ConfSysUser result = (ConfSysUser) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfSysUser instance) {
        log.debug("attaching dirty ConfSysUser instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfSysUser instance) {
        log.debug("attaching clean ConfSysUser instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public List<ConfSysUser> retrieve(PageBean pageBean,ConfSysUser confSysUser) {
		log.debug("finding all ConfSysUser instances");
        try {
            String queryString = "from ConfSysUser where 1=1 ";
            if (StringUtils.isNotEmpty(confSysUser.getName())) {
            	queryString += " and name like :name ";
            }
            if (StringUtils.isNotEmpty(confSysUser.getMail())) {
            	queryString += " and mail like :mail ";
            }
            queryString += "order by loginName";
            Query queryObject = getSession().createQuery(queryString);
            if (StringUtils.isNotEmpty(confSysUser.getName())) {
            	 queryObject.setString("name", "%"+confSysUser.getName()+"%");
            }
            if (StringUtils.isNotEmpty(confSysUser.getMail())) {
            	queryObject.setString("mail", "%"+confSysUser.getMail()+"%");
            }
            
            queryObject.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
            		 .setMaxResults(pageBean.getRows());
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}

	public Long count(ConfSysUser confSysUser) {
		log.debug("finding all ConfSysUser count");
        try {
            String queryString = "select count(1) from ConfSysUser where 1=1 ";
            if (StringUtils.isNotEmpty(confSysUser.getName())) {
            	queryString += " and name like :name ";
            }
            if (StringUtils.isNotEmpty(confSysUser.getMail())) {
            	queryString += " and mail like :mail";
            }
            Query queryObject = getSession().createQuery(queryString);
            if (StringUtils.isNotEmpty(confSysUser.getName())) {
           	 queryObject.setString("name", "%"+confSysUser.getName()+"%");
            }
            if (StringUtils.isNotEmpty(confSysUser.getMail())) {
           	 queryObject.setString("mail", "%"+confSysUser.getMail()+"%");
            }
            return (Long)(queryObject.uniqueResult());
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
	}
	
	public void updatePwd(ConfSysUser confSysUser){
		log.debug("update confSysUser password");
		 try {
			   String updateSql = "update conf_sys_user set login_password=? where id=?";
			   Query query = getSession().createSQLQuery(updateSql);
			   query.setString(0, confSysUser.getLoginPassword());
			   query.setString(1, confSysUser.getId());
			   query.executeUpdate();
			   log.debug("update confSysUser password successful");
		   } catch (RuntimeException re) {
			   log.error("update confSysUser password failed", re);
	           throw re;
		   }
	}
	
	/**
	 * 批量导入用户
	 * @param userIds 待导入的用户ID
	 */
	public void importUser(String userIds) {
		log.debug("importUser from confUser");
		try {
			   String insertSql = "insert into conf_sys_user(id,login_name,login_password,name,mail,company) " + 
					  "select uuid(),a.email,a.password,a.cname,a.email,a.company " +
					  "from conf_user a where a.id in (:id)";
			   getSession().createSQLQuery(insertSql).setParameterList("id", userIds.split(",")).executeUpdate();
			   log.debug("importUser from confUser successful");
		  } catch (RuntimeException re) {
			   log.error("importUser from confUser failed", re);
	           throw re;
		  }
	}
	
	  
    //通过会议ID 和用户类型 查询user信息(人员指派用) 
    public List<Map> findUserByUserType(String meetingId, String userTypes,  ConfGuest user) {
        log.debug("finding all ConfMeetingUser instance");
        try {
            String queryString = "select a.meeting_id as meetingId,c.id as assignId,a.user_type AS userType,b.cname as cname,b.sex as sex,b.mobile as mobile,b.email as email, b.company as company, " +
                        "GROUP_CONCAT(e.name SEPARATOR ',') as roleName " + 
                        "from  conf_sys_user c " + 
                        "inner join conf_user b on b.email=c.login_name " + 
                        "left join conf_meeting_user a on a.user_id=b.id " + 
                        "left join conf_role_user d on d.user_id=c.id " + 
                        "left join conf_sys_role e on d.role_id=e.id " + 
                        "where a.meeting_id =:meetingId and a.user_type in (:userType) ";
//                        "and not exists (select * from conf_assignment ca where c.id = ca.user_id) ";
            //添加检索条件
            if (user!=null && !StringUtils.isEmpty(user.getCname())) {
                queryString+=" and (b.cname like :cname or b.ename like :ename)";
            }
            queryString += " group by c.id ";
            Query query = getSession().createSQLQuery(queryString);
            query.setString("meetingId", meetingId);
            if (user!=null && !StringUtils.isEmpty(user.getCname())) {
                query.setString("cname", "%"+user.getCname()+"%");
                query.setString("ename", "%"+user.getCname()+"%");
            }
            if (!selectedVal(user.getUserType()).equals("")) {
                query.setParameterList("userType", selectedVal(user.getUserType()).split(","));
            } else {
                query.setParameterList("userType", userTypes.split(","));
            }
            return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

	public List<ConfSysUser> findSysUserByIdList(String personInChargeId) {
		log.debug("finding ConfSysUser by userIds");
        try {
            String queryString = "from ConfSysUser where id in (:idList) ";
            return getSession().createQuery(queryString).setParameterList("idList", personInChargeId.split(",")).list();
        } catch (RuntimeException re) {
            log.error("find ConfSysUser failed", re);
            throw re;
        }
	}
	
	/**
	 * 通过工作人员id找到该人员的具体用户类型和姓名等信息
	 * @param sysUserId
	 * @return
	 */
	public List<Map> findUserAndUserType (String sysUserId) {
        log.debug("finding ConfSysUser and userType by sysUserId:"+sysUserId);
           try {
        	  /* SELECT a.login_name,b.cname,b.email,b.mobile,c.user_type
        	   FROM conf_sys_user a
        	   LEFT JOIN conf_user b ON a.login_name = b.email
        	   LEFT JOIN conf_meeting_user c ON b.id = c.user_id
        	   WHERE a.id='1c1dd88e-48a9-11e4-9f20-00163e000fbb';*/
               String queryString = " SELECT a.login_name,b.cname,b.email,b.mobile,c.user_type FROM conf_sys_user a " +
            		   " LEFT JOIN conf_user b ON a.login_name = b.email "+
            		   "LEFT JOIN conf_meeting_user c ON b.id = c.user_id "+
            		   " WHERE a.id=? ";
               Query query = getSession().createSQLQuery(queryString).setString(0, sysUserId);              
               log.debug("find successful");
               return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
           } catch (RuntimeException re) {
               log.error("find failed", re);
               throw re;
           }
    
	}
	
    //判断选择型检索条件是否有被选中
    public String selectedVal(String val){
        if (val == null || val.equals("") || val.length() == 0) {
            return "";
        } else {
            if (val.startsWith(",")){
                return val.substring(1, val.length());
            } else if (val.equals(",")) {
                return "";
            } else {
                return val;
            }
        }
    }
    
}