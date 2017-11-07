package com.centling.conference.checkin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.centling.conference.user.entity.ConfUser;

/**
 * ConfCurrcheckinUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_currcheckin_user")
public class ConfCurrcheckinUser implements java.io.Serializable {

	// Fields

	private String id;
	private ConfUser confUser;
	private String meetingId;
	private Integer checkState;
	private String checkTime;
	private String checkTimeCopy;
	private String operator;
	private String scheduleId;
	private Integer state;
	private Integer isheadset;
	private String userType;

	// Constructors

	/** default constructor */
	public ConfCurrcheckinUser() {
	}

	/** minimal constructor */
	public ConfCurrcheckinUser(ConfUser confUser) {
		this.confUser = confUser;
	}

	/** full constructor */
	public ConfCurrcheckinUser(ConfUser confUser, String meetingId,
			Integer checkState, String checkTime, String checkTimeCopy,
			String operator, String scheduleId, Integer state, Integer isheadset, String userType) {
		this.confUser = confUser;
		this.meetingId = meetingId;
		this.checkState = checkState;
		this.checkTime = checkTime;
		this.checkTimeCopy = checkTimeCopy;
		this.operator = operator;
		this.scheduleId = scheduleId;
		this.state = state;
		this.isheadset = isheadset;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conf_user", nullable = false)
	public ConfUser getConfUser() {
		return this.confUser;
	}

	public void setConfUser(ConfUser confUser) {
		this.confUser = confUser;
	}

	@Column(name = "meeting_id", length = 50)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "check_state")
	public Integer getCheckState() {
		return this.checkState;
	}

	public void setCheckState(Integer checkState) {
		this.checkState = checkState;
	}

	@Column(name = "check_time", length = 50)
	public String getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "check_time_copy", length = 50)
	public String getCheckTimeCopy() {
		return this.checkTimeCopy;
	}

	public void setCheckTimeCopy(String checkTimeCopy) {
		this.checkTimeCopy = checkTimeCopy;
	}

	@Column(name = "operator", length = 50)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "schedule_id", length = 50)
	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "isheadset")
	public Integer getIsheadset() {
		return this.isheadset;
	}

	public void setIsheadset(Integer isheadset) {
		this.isheadset = isheadset;
	}

	
	@Column(name = "user_type")	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	

}