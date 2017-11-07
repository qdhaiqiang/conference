package com.centling.conference.schedulelog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfSchedulelogUserAssign entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_schedulelog_user_assign" )
public class ConfSchedulelogUserAssign implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String schduleId;
	private String speechTopic;
	private Integer speechOrder;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfSchedulelogUserAssign() {
	}

	/** full constructor */
	public ConfSchedulelogUserAssign(String userId, String schduleId,
			String speechTopic, Integer speechOrder, String memo) {
		this.userId = userId;
		this.schduleId = schduleId;
		this.speechTopic = speechTopic;
		this.speechOrder = speechOrder;
		this.memo = memo;
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

	@Column(name = "user_id", length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "schdule_id", length = 40)
	public String getSchduleId() {
		return this.schduleId;
	}

	public void setSchduleId(String schduleId) {
		this.schduleId = schduleId;
	}

	@Column(name = "speech_topic", length = 500)
	public String getSpeechTopic() {
		return this.speechTopic;
	}

	public void setSpeechTopic(String speechTopic) {
		this.speechTopic = speechTopic;
	}

	@Column(name = "speech_order")
	public Integer getSpeechOrder() {
		return this.speechOrder;
	}

	public void setSpeechOrder(Integer speechOrder) {
		this.speechOrder = speechOrder;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}