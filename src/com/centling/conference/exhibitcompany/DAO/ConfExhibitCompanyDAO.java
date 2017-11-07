package com.centling.conference.exhibitcompany.DAO;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.centling.conference.base.BaseHibernateDAO;
import com.centling.conference.exhibitcompany.entity.ConfExhibitCompany;

/**
 * A data access object (DAO) providing persistence and search support for
 * ConfExhibitCompany entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.centling.conference.exhibitcompany.entity.ConfExhibitCompany
 * @author MyEclipse Persistence Tools
 */
@Repository("confExhibitCompanyDAO")
public class ConfExhibitCompanyDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ConfExhibitCompanyDAO.class);
	// property constants
	public static final String LOGIN_EMAIL = "loginEmail";
	public static final String LOGIN_PASSWORD = "loginPassword";
	public static final String COMPANY_NAME_CN = "companyNameCn";
	public static final String COMPANY_NAME_EN = "companyNameEn";
	public static final String CONTACT_PERSON = "contactPerson";
	public static final String CONTACT_JOB = "contactJob";
	public static final String CONTACT_EMAIL = "contactEmail";
	public static final String CONTACT_TEL = "contactTel";
	public static final String CONTACT_FAX = "contactFax";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String COMPANY_TEL = "companyTel";
	public static final String COMPANY_FAX = "companyFax";
	public static final String COMPANY_EMAIL = "companyEmail";
	public static final String COMPANY_WEBSITE = "companyWebsite";
	public static final String COMPANY_EXHIBIT_TYPE = "companyExhibitType";
	public static final String COMPANY_INTRODUCTION = "companyIntroduction";

	public void save(ConfExhibitCompany transientInstance) {
		log.debug("saving ConfExhibitCompany instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ConfExhibitCompany persistentInstance) {
		log.debug("deleting ConfExhibitCompany instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ConfExhibitCompany findById(java.lang.String id) {
		log.debug("getting ConfExhibitCompany instance with id: " + id);
		try {
			ConfExhibitCompany instance = (ConfExhibitCompany) getSession()
					.get("com.centling.conference.exhibitcompany.entity.ConfExhibitCompany",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ConfExhibitCompany> findByExample(ConfExhibitCompany instance) {
		log.debug("finding ConfExhibitCompany instance by example");
		try {
			List<ConfExhibitCompany> results = (List<ConfExhibitCompany>) getSession()
					.createCriteria(
							"com.centling.conference.exhibitcompany.entity.ConfExhibitCompany")
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
		log.debug("finding ConfExhibitCompany instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ConfExhibitCompany as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ConfExhibitCompany> findByLoginEmail(Object loginEmail) {
		return findByProperty(LOGIN_EMAIL, loginEmail);
	}

	public List<ConfExhibitCompany> findByLoginPassword(Object loginPassword) {
		return findByProperty(LOGIN_PASSWORD, loginPassword);
	}

	public List<ConfExhibitCompany> findByCompanyNameCn(Object companyNameCn) {
		return findByProperty(COMPANY_NAME_CN, companyNameCn);
	}

	public List<ConfExhibitCompany> findByCompanyNameEn(Object companyNameEn) {
		return findByProperty(COMPANY_NAME_EN, companyNameEn);
	}

	public List<ConfExhibitCompany> findByContactPerson(Object contactPerson) {
		return findByProperty(CONTACT_PERSON, contactPerson);
	}

	public List<ConfExhibitCompany> findByContactJob(Object contactJob) {
		return findByProperty(CONTACT_JOB, contactJob);
	}

	public List<ConfExhibitCompany> findByContactEmail(Object contactEmail) {
		return findByProperty(CONTACT_EMAIL, contactEmail);
	}

	public List<ConfExhibitCompany> findByContactTel(Object contactTel) {
		return findByProperty(CONTACT_TEL, contactTel);
	}

	public List<ConfExhibitCompany> findByContactFax(Object contactFax) {
		return findByProperty(CONTACT_FAX, contactFax);
	}

	public List<ConfExhibitCompany> findByCompanyAddress(Object companyAddress) {
		return findByProperty(COMPANY_ADDRESS, companyAddress);
	}

	public List<ConfExhibitCompany> findByCompanyTel(Object companyTel) {
		return findByProperty(COMPANY_TEL, companyTel);
	}

	public List<ConfExhibitCompany> findByCompanyFax(Object companyFax) {
		return findByProperty(COMPANY_FAX, companyFax);
	}

	public List<ConfExhibitCompany> findByCompanyEmail(Object companyEmail) {
		return findByProperty(COMPANY_EMAIL, companyEmail);
	}

	public List<ConfExhibitCompany> findByCompanyWebsite(Object companyWebsite) {
		return findByProperty(COMPANY_WEBSITE, companyWebsite);
	}

	public List<ConfExhibitCompany> findByCompanyExhibitType(
			Object companyExhibitType) {
		return findByProperty(COMPANY_EXHIBIT_TYPE, companyExhibitType);
	}

	public List<ConfExhibitCompany> findByCompanyIntroduction(
			Object companyIntroduction) {
		return findByProperty(COMPANY_INTRODUCTION, companyIntroduction);
	}

	public List findAll() {
		log.debug("finding all ConfExhibitCompany instances");
		try {
			String queryString = "from ConfExhibitCompany";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ConfExhibitCompany merge(ConfExhibitCompany detachedInstance) {
		log.debug("merging ConfExhibitCompany instance");
		try {
			ConfExhibitCompany result = (ConfExhibitCompany) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ConfExhibitCompany instance) {
		log.debug("attaching dirty ConfExhibitCompany instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ConfExhibitCompany instance) {
		log.debug("attaching clean ConfExhibitCompany instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}