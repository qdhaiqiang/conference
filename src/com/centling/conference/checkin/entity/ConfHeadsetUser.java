package com.centling.conference.checkin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfHeadsetUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_headset_user", catalog = "conference")
public class ConfHeadsetUser implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String meetingId;
	private String scheduleId;
	private String email;
	private String phone;
	private String cname;
	private String ename;
	private String userType;

	// Constructors

	/** default constructor */
	public ConfHeadsetUser() {
	}

	/** minimal constructor */
	public ConfHeadsetUser(String userId) {
		this.userId = userId;
	}

	/** full constructor */
	public ConfHeadsetUser(String userId, String meetingId, String scheduleId,
			String email, String phone,String ename, String cname, String userType) {
		this.userId = userId;
		this.meetingId = meetingId;
		this.scheduleId = scheduleId;
		this.email = email;
		this.phone = phone;
		this.cname = cname;
		this.ename = ename;
		this.userType = userType;
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

	@Column(name = "user_id", nullable = false, length = 100)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "meeting_id", length = 50)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "schedule_id", length = 50)
	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "cname", length = 50)
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	@Column(name = "ename", length = 50)
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	
	
}