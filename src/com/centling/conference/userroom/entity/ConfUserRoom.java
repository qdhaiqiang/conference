package com.centling.conference.userroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfUserRoom entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_user_room")
public class ConfUserRoom implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String userType;
	private String roomType;
	private String organizerPay;
	private String remarks;

	// Constructors

	/** default constructor */
	public ConfUserRoom() {
	}

	/** full constructor */
	public ConfUserRoom(String meetingId, String userType, String roomType, String organizerPay, String remarks) {
		this.meetingId = meetingId;
		this.userType = userType;
		this.roomType = roomType;
		this.organizerPay = organizerPay;
		this.remarks = remarks;
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

	@Column(name = "user_type", length = 20)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "room_type", length = 20)
	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Column(name = "organizer_pay", length = 1)
	public String getOrganizerPay() {
		return this.organizerPay;
	}

	public void setOrganizerPay(String organizerPay) {
		this.organizerPay = organizerPay;
	}

	@Column(name = "remarks", length = 100)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}