package com.centling.conference.sms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfSmsTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_sms_template")
public class ConfSmsTemplate implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String userType;
	private String content;
	private String messageType;
	private String name;

	// Constructors

	/** default constructor */
	public ConfSmsTemplate() {
	}

	/** full constructor */
	public ConfSmsTemplate(String meetingId, String userType, String content,
			String messageType, String name) {
		this.meetingId = meetingId;
		this.userType = userType;
		this.content = content;
		this.messageType = messageType;
		this.name = name;
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

	@Column(name = "user_type", nullable = false, length = 50)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "content", nullable = false, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "message_type", nullable = false, length = 40)
	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}