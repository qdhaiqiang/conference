package com.centling.conference.materialreg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfMaterialRegistration entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_material_registration")
public class ConfMaterialRegistration implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String name;
	private String type;
	private Integer quantity;
	private String chargePerson;
	private String detail;

	// Constructors

	/** default constructor */
	public ConfMaterialRegistration() {
	}

	/** minimal constructor */
	public ConfMaterialRegistration(String meetingId, String name,
			Integer quantity) {
		this.meetingId = meetingId;
		this.name = name;
		this.quantity = quantity;
	}

	/** full constructor */
	public ConfMaterialRegistration(String meetingId, String name, String type,
			Integer quantity, String chargePerson, String detail) {
		this.meetingId = meetingId;
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.chargePerson = chargePerson;
		this.detail = detail;
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

	@Column(name = "meeting_id", nullable = false, length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "name", nullable = false, length = 65535)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 65535)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "quantity", scale = 0)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "charge_person", length = 65535)
	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	@Column(name = "detail", length = 65535)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}