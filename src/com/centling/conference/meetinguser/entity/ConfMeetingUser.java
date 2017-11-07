package com.centling.conference.meetinguser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfMeetingUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_meeting_user")
public class ConfMeetingUser implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String userId;
	private String userType;
	private String approveState;
	private String roomType;
	private String organizerPay;
	private String checkInDate;
	private String checkOutDate;
	private String remindFlag;

	// Constructors

	/** default constructor */
	public ConfMeetingUser() {
	}

	/** full constructor */
	public ConfMeetingUser(String meetingId, String userId, String userType,
			String approveState, String remindFlag) {
		this.meetingId = meetingId;
		this.userId = userId;
		this.userType = userType;
		this.approveState = approveState;
		this.remindFlag = remindFlag;
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

	@Column(name = "user_id", length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_type", length = 1)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "approve_state", length = 1)
	public String getApproveState() {
		return this.approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}
	
	@Column(name = "room_type", length = 20, updatable=false)
	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Column(name = "organizer_pay", length = 1, updatable=false)
	public String getOrganizerPay() {
		return this.organizerPay;
	}

	public void setOrganizerPay(String organizerPay) {
		this.organizerPay = organizerPay;
	}

	@Column(name = "check_in_date", length = 20, updatable=false)
	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	@Column(name = "check_out_date", length = 20, updatable=false)
	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	@Column(name = "remind_flag", updatable=false)
    public String getRemindFlag() {
        return remindFlag;
    }

    public void setRemindFlag(String remindFlag) {
        this.remindFlag = remindFlag;
    }
	
	
}