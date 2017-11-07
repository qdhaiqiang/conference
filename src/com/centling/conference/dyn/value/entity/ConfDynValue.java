package com.centling.conference.dyn.value.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfDynValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_dyn_value")
public class ConfDynValue implements java.io.Serializable {

	// Fields

	private String id;
	private String fieldId;
	private String name;
	private String type;
	private String meetingId;
	private String userType;
	private String userId;
	private String required;
	private String description;
	private String options;
	private String value;
	private Integer orderNum;

	// Constructors

	/** default constructor */
	public ConfDynValue() {
	}

	/** full constructor */
	public ConfDynValue(String fieldId, String name, String type,
			String meetingId, String userType, String userId, String required,
			String description, String options, String value) {
		this.fieldId = fieldId;
		this.name = name;
		this.type = type;
		this.meetingId = meetingId;
		this.userType = userType;
		this.userId = userId;
		this.required = required;
		this.description = description;
		this.options = options;
		this.value = value;
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

	@Column(name = "field_id", length = 40)
	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "user_type", length = 2)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "user_id", length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "required", length = 1)
	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "options", length = 65535)
	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Column(name = "value", length = 500)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="order_num")
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}