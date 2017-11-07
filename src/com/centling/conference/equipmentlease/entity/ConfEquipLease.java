package com.centling.conference.equipmentlease.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEquipLease entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_equip_lease")
public class ConfEquipLease implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String equipName;
	private String equipType;
	private String supplier;
	private String amount;
	private String dateStart;
	private String dateEnd;
	private String principal;
	private String principalTel;
	private String insertTime;
	private String insertUser;
	private String other;

	// Constructors

	/** default constructor */
	public ConfEquipLease() {
	}

	/** full constructor */
	public ConfEquipLease(String meetingId, String equipName, String equipType,
			String supplier, String amount, String dateStart, String dateEnd,
			String principal, String principalTel, String insertTime,
			String insertUser, String other) {
		this.meetingId = meetingId;
		this.equipName = equipName;
		this.equipType = equipType;
		this.supplier = supplier;
		this.amount = amount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.principal = principal;
		this.principalTel = principalTel;
		this.insertTime = insertTime;
		this.insertUser = insertUser;
		this.other = other;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 40)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "equip_name", length = 50)
	public String getEquipName() {
		return this.equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	@Column(name = "equip_type", length = 50)
	public String getEquipType() {
		return this.equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	@Column(name = "supplier", length = 50)
	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Column(name = "amount", length = 50)
	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Column(name = "date_start", length = 10)
	public String getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	@Column(name = "date_end", length = 10)
	public String getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Column(name = "principal", length = 50)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "principal_tel", length = 50)
	public String getPrincipalTel() {
		return this.principalTel;
	}

	public void setPrincipalTel(String principalTel) {
		this.principalTel = principalTel;
	}

	@Column(name = "insert_time", length = 30)
	public String getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "insert_user", length = 50)
	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	@Column(name = "other", length = 1024)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}