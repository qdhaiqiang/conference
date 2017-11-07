package com.centling.conference.messagepush.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfMessagePush entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_message_push")
public class ConfMessagePush implements java.io.Serializable {

	// Fields

	private String id;
	private String state;
	private String userId;
	private String birth;
	private String content;
	private String messageType;
	private String meetingId;

	// Constructors

	/** default constructor */
	public ConfMessagePush() {
	}

	/** full constructor */
	public ConfMessagePush(String state, String userId, String birth, String content, String messageType, String meetingId) {
		this.state = state;
		this.userId = userId;
		this.birth = birth;
		this.content = content;
		this.messageType = messageType;
		this.meetingId = meetingId;
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

	@Column(name = "state", nullable = false, length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "user_id", nullable = false, length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "birth", nullable = false, length = 40)
	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
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

	@Column(name = "meeting_id", nullable = false, length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

}