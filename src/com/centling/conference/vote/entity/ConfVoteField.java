package com.centling.conference.vote.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfVoteField entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_vote_field")
public class ConfVoteField implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String scheduleId;
	private String type;
	private String name;
	private String description;
	private String options;
	private Integer orderNum;
	private String required;
	private String isShow;

	// Constructors

	/** default constructor */
	public ConfVoteField() {
	}

	/** full constructor */
	public ConfVoteField(String meetingId, String scheduleId, String type,
			String name, String description, String options, Integer orderNum,
			String required, String isShow) {
		this.meetingId = meetingId;
		this.scheduleId = scheduleId;
		this.type = type;
		this.name = name;
		this.description = description;
		this.options = options;
		this.orderNum = orderNum;
		this.required = required;
		this.isShow = isShow;
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

	@Column(name = "schedule_id", length = 40)
	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(name = "type", length = 10)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "name", length = 4000)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 4000)
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

	@Column(name = "order_num")
	public Integer getOrderNum() {
		return this.orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	@Column(name = "required", length = 10)
	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	@Column(name = "is_show", length = 10)
	public String getIsShow() {
		return this.isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}