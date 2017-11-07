package com.centling.conference.exhibitfurniture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibitExpress entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_express")
public class ConfExhibitExpress implements java.io.Serializable {

	// Fields

	private String expressId;
	private String meetingId;
	private String expressCompany;
	private String expressCompanyEn;
	private String expressOrder;
	private String expressOrderEn;
	private String expressTele;
	private String expressFax;
	private String expressMobile;
	private String expressAddress;
	private String expressEmail;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfExhibitExpress() {
	}

	/** full constructor */
	public ConfExhibitExpress(String meetingId, String expressCompany,
			String expressOrder, String memo) {
		this.meetingId = meetingId;
		this.expressCompany = expressCompany;
		this.expressOrder = expressOrder;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "express_id", unique = true, nullable = false, length = 40)
	public String getExpressId() {
		return this.expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "express_company", length = 50)
	public String getExpressCompany() {
		return this.expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	@Column(name = "express_order", length = 50)
	public String getExpressOrder() {
		return this.expressOrder;
	}

	public void setExpressOrder(String expressOrder) {
		this.expressOrder = expressOrder;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "express_company_en", length = 200)
	public String getExpressCompanyEn() {
		return expressCompanyEn;
	}

	public void setExpressCompanyEn(String expressCompanyEn) {
		this.expressCompanyEn = expressCompanyEn;
	}

	@Column(name = "express_order_en", length = 200)
	public String getExpressOrderEn() {
		return expressOrderEn;
	}

	public void setExpressOrderEn(String expressOrderEn) {
		this.expressOrderEn = expressOrderEn;
	}
	
	@Column(name = "express_tele", length = 50)
	public String getExpressTele() {
		return expressTele;
	}

	public void setExpressTele(String expressTele) {
		this.expressTele = expressTele;
	}

	@Column(name = "express_fax", length = 50)
	public String getExpressFax() {
		return expressFax;
	}

	public void setExpressFax(String expressFax) {
		this.expressFax = expressFax;
	}
	
	@Column(name = "express_mobile", length = 50)
	public String getExpressMobile() {
		return expressMobile;
	}

	public void setExpressMobile(String expressMobile) {
		this.expressMobile = expressMobile;
	}

	@Column(name = "express_address", length = 200)
	public String getExpressAddress() {
		return expressAddress;
	}
	
	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}

	@Column(name = "express_email", length = 50)
	public String getExpressEmail() {
		return expressEmail;
	}

	public void setExpressEmail(String expressEmail) {
		this.expressEmail = expressEmail;
	}
}