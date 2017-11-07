package com.centling.conference.exhibitcompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibitCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_company")
public class ConfExhibitCompany implements java.io.Serializable {

	// Fields

	private String companyId;
	private String loginEmail;
	private String loginPassword;
	private String companyNameCn;
	private String companyNameEn;
	private String contactPerson;
	private String contactJob;
	private String contactEmail;
	private String contactTel;
	private String contactFax;
	private String companyAddress;
	private String companyTel;
	private String companyFax;
	private String companyEmail;
	private String companyWebsite;
	private String companyExhibitType;
	private String companyIntroduction;

	// Constructors

	/** default constructor */
	public ConfExhibitCompany() {
	}

	/** minimal constructor */
	public ConfExhibitCompany(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/** full constructor */
	public ConfExhibitCompany(String loginEmail, String loginPassword,
			String companyNameCn, String companyNameEn, String contactPerson,
			String contactJob, String contactEmail, String contactTel,
			String contactFax, String companyAddress, String companyTel,
			String companyFax, String companyEmail, String companyWebsite,
			String companyExhibitType, String companyIntroduction) {
		this.loginEmail = loginEmail;
		this.loginPassword = loginPassword;
		this.companyNameCn = companyNameCn;
		this.companyNameEn = companyNameEn;
		this.contactPerson = contactPerson;
		this.contactJob = contactJob;
		this.contactEmail = contactEmail;
		this.contactTel = contactTel;
		this.contactFax = contactFax;
		this.companyAddress = companyAddress;
		this.companyTel = companyTel;
		this.companyFax = companyFax;
		this.companyEmail = companyEmail;
		this.companyWebsite = companyWebsite;
		this.companyExhibitType = companyExhibitType;
		this.companyIntroduction = companyIntroduction;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "company_id", unique = true, nullable = false, length = 40)
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "login_email", length = 80)
	public String getLoginEmail() {
		return this.loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	@Column(name = "login_password", length = 50)
	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name = "company_name_cn", length = 500)
	public String getCompanyNameCn() {
		return this.companyNameCn;
	}

	public void setCompanyNameCn(String companyNameCn) {
		this.companyNameCn = companyNameCn;
	}

	@Column(name = "company_name_en", length = 1000)
	public String getCompanyNameEn() {
		return this.companyNameEn;
	}

	public void setCompanyNameEn(String companyNameEn) {
		this.companyNameEn = companyNameEn;
	}

	@Column(name = "contact_person", length = 200)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "contact_job", length = 10)
	public String getContactJob() {
		return this.contactJob;
	}

	public void setContactJob(String contactJob) {
		this.contactJob = contactJob;
	}

	@Column(name = "contact_email", length = 100)
	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	@Column(name = "contact_tel", length = 50)
	public String getContactTel() {
		return this.contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	@Column(name = "contact_fax", length = 50)
	public String getContactFax() {
		return this.contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	@Column(name = "company_address", length = 500)
	public String getCompanyAddress() {
		return this.companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	@Column(name = "company_tel", length = 50)
	public String getCompanyTel() {
		return this.companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	@Column(name = "company_fax", length = 50)
	public String getCompanyFax() {
		return this.companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	@Column(name = "company_email", length = 100)
	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Column(name = "company_website", length = 500)
	public String getCompanyWebsite() {
		return this.companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	@Column(name = "company_exhibit_type", length = 500)
	public String getCompanyExhibitType() {
		return this.companyExhibitType;
	}

	public void setCompanyExhibitType(String companyExhibitType) {
		this.companyExhibitType = companyExhibitType;
	}

	@Column(name = "company_introduction", length = 200)
	public String getCompanyIntroduction() {
		return this.companyIntroduction;
	}

	public void setCompanyIntroduction(String companyIntroduction) {
		this.companyIntroduction = companyIntroduction;
	}

}