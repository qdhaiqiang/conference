package com.centling.conference.userentourage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfUserEntourage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_user_entourage")
public class ConfUserEntourage implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String userId;
	private String entourageId;
	private String type;

	// Constructors

	/** default constructor */
	public ConfUserEntourage() {
	}

	/** full constructor */
	public ConfUserEntourage(String userId, String entourageId, String type) {
		this.userId = userId;
		this.entourageId = entourageId;
		this.type = type;
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

	@Column(name = "entourage_id", length = 40)
	public String getEntourageId() {
		return this.entourageId;
	}

	public void setEntourageId(String entourageId) {
		this.entourageId = entourageId;
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
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
}