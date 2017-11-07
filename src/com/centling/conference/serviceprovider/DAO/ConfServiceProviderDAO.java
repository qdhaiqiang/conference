package com.centling.conference.serviceprovider.DAO;

import static org.hibernate.criterion.Example.create;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.base.page.PageBean;
import com.centling.conference.serviceprovider.entity.ConfServiceProvider;

/**
 	* A data access object (DAO) providing persistence and search support for ConfServiceProvider entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.centling.conference.serviceprovider.entity.ConfServiceProvider
  * @author MyEclipse Persistence Tools 
 */

@Repository("confServiceProviderDAO")
public class ConfServiceProviderDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ConfServiceProviderDAO.class);
		//property constants
	public static final String MEETING_ID = "meetingId";
	public static final String PROVIDER_NAME = "providerName";
	public static final String SERVICE = "service";
	public static final String MANAGER = "manager";
	public static final String MANAGER_CONTACT = "managerContact";
	public static final String REMARKS = "remarks";



    
    public void save(ConfServiceProvider transientInstance) {
        log.debug("saving ConfServiceProvider instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ConfServiceProvider persistentInstance) {
        log.debug("deleting ConfServiceProvider instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ConfServiceProvider findById( java.lang.String id) {
        log.debug("getting ConfServiceProvider instance with id: " + id);
        try {
            ConfServiceProvider instance = (ConfServiceProvider) getSession()
                    .get("com.centling.conference.serviceprovider.entity.ConfServiceProvider", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<ConfServiceProvider> findByExample(ConfServiceProvider instance) {
        log.debug("finding ConfServiceProvider instance by example");
        try {
            List<ConfServiceProvider> results = (List<ConfServiceProvider>) getSession()
                    .createCriteria("com.centling.conference.serviceprovider.entity.ConfServiceProvider")
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
      log.debug("finding ConfServiceProvider instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ConfServiceProvider as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<ConfServiceProvider> findByMeetingId(Object meetingId
	) {
		return findByProperty(MEETING_ID, meetingId
		);
	}
	
	public List<ConfServiceProvider> findByProviderName(Object providerName
	) {
		return findByProperty(PROVIDER_NAME, providerName
		);
	}
	
	public List<ConfServiceProvider> findByService(Object service
	) {
		return findByProperty(SERVICE, service
		);
	}
	
	public List<ConfServiceProvider> findByManager(Object manager
	) {
		return findByProperty(MANAGER, manager
		);
	}
	
	public List<ConfServiceProvider> findByManagerContact(Object managerContact
	) {
		return findByProperty(MANAGER_CONTACT, managerContact
		);
	}
	
	public List<ConfServiceProvider> findByRemarks(Object remarks
	) {
		return findByProperty(REMARKS, remarks
		);
	}
	

	public List findAll() {
		log.debug("finding all ConfServiceProvider instances");
		try {
			String queryString = "from ConfServiceProvider";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ConfServiceProvider merge(ConfServiceProvider detachedInstance) {
        log.debug("merging ConfServiceProvider instance");
        try {
            ConfServiceProvider result = (ConfServiceProvider) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ConfServiceProvider instance) {
        log.debug("attaching dirty ConfServiceProvider instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ConfServiceProvider instance) {
        log.debug("attaching clean ConfServiceProvider instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
	@SuppressWarnings("unchecked")
	public List<ConfServiceProvider> retrieve(PageBean pageBean, String meetingId,ConfServiceProvider provider) {
		log.debug("finding all ConfServiceProvider instances");
		try {
			String queryString = "from ConfServiceProvider as model where model.meetingId=?";
          
            List<String> param = new ArrayList<String>();
            if (provider.getProviderName() != null && !provider.getProviderName().equals("")) {
            	queryString += " and model.providerName like ? ";
            	param.add("%"+provider.getProviderName()+"%");
            }
            
            if (provider.getService() != null && !provider.getService().equals("")) {
            	queryString += " and model.service like ? ";
            	param.add("%"+provider.getService()+"%");
            }
            
            if (provider.getManager() != null && !provider.getManager().equals("")) {
            	queryString += " and model.manager like ? ";
            	param.add("%"+provider.getManager()+"%");
            }
            
            queryString += " order by model.id";
            
            Query query = getSession().createQuery(queryString).setString(0, meetingId);
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
            
			return query.setFirstResult((pageBean.getPage()-1)*pageBean.getRows())
		            .setMaxResults(pageBean.getRows()).list();
			
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Long count(String meetingId,ConfServiceProvider provider) {
		log.debug("get count of ConfEquipLease instances");
		try {
			String queryString = "select count(*) from conf_service_provider t where t.meeting_id=?";
			
            List<String> param = new ArrayList<String>();
            if (provider.getProviderName() != null && !provider.getProviderName().equals("")) {
            	queryString += " and t.provider_name like ? ";
            	param.add("%"+provider.getProviderName()+"%");
            }
            
            if (provider.getService() != null && !provider.getService().equals("")) {
            	queryString += " and t.service like ? ";
            	param.add("%"+provider.getService()+"%");
            }
            
            if (provider.getManager() != null && !provider.getManager().equals("")) {
            	queryString += " and t.manager like ? ";
            	param.add("%"+provider.getManager()+"%");
            }
            
            Query query = getSession().createSQLQuery(queryString).setString(0, meetingId);
            for(int i=0;i<param.size();i++){
            	query.setString(i+1, param.get(i));
            }
			
	        return Long.parseLong((query.uniqueResult().toString()));
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int deleteById(String ids) {
		log.debug("delete ConfServiceProvider instance");
		try {
			String queryString = "delete from ConfServiceProvider where id in (:id)";
			Query query = getSession().createQuery(queryString);
        	query.setParameterList("id", ids.split(","));
        	int row = query.executeUpdate();
        	return row;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
}