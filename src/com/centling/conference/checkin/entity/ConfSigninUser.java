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
 * ConfSigninUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_signin_user")
public class ConfSigninUser implements java.io.Serializable {

	// Fields

	private String id;
	private ConfUser confUser;
	private String meetingId;
	private Integer mailTicket;
	private Integer gift;
	private String restaurant;
	private String roomNum;
	private String operator;
	private String signinTime;
	private String signinTimeCopy;
	private Integer ifdocument;
	private Integer ifreceipt;
	private String postscript;
	private String userType;

	// Constructors

	/** default constructor */
	public ConfSigninUser() {
	}

	/** minimal constructor */
	public ConfSigninUser(ConfUser confUser) {
		this.confUser = confUser;
	}

	/** full constructor */
	public ConfSigninUser(ConfUser confUser, String meetingId,
			Integer mailTicket, Integer gift, String restaurant,
			String roomNum, String operator, String signinTime,
			String signinTimeCopy, Integer ifdocument, Integer ifreceipt,
			String postscript,String userType) {
		this.confUser = confUser;
		this.meetingId = meetingId;
		this.mailTicket = mailTicket;
		this.gift = gift;
		this.restaurant = restaurant;
		this.roomNum = roomNum;
		this.operator = operator;
		this.signinTime = signinTime;
		this.signinTimeCopy = signinTimeCopy;
		this.ifdocument = ifdocument;
		this.ifreceipt = ifreceipt;
		this.postscript = postscript;
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

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "mail_ticket")
	public Integer getMailTicket() {
		return this.mailTicket;
	}

	public void setMailTicket(Integer mailTicket) {
		this.mailTicket = mailTicket;
	}

	@Column(name = "gift")
	public Integer getGift() {
		return this.gift;
	}

	public void setGift(Integer gift) {
		this.gift = gift;
	}

	@Column(name = "restaurant", length = 100)
	public String getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	@Column(name = "room_num", length = 50)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "operator", length = 50)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "signin_time", length = 50)
	public String getSigninTime() {
		return this.signinTime;
	}

	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}

	@Column(name = "signin_time_copy", length = 50)
	public String getSigninTimeCopy() {
		return this.signinTimeCopy;
	}

	public void setSigninTimeCopy(String signinTimeCopy) {
		this.signinTimeCopy = signinTimeCopy;
	}

	@Column(name = "ifdocument")
	public Integer getIfdocument() {
		return this.ifdocument;
	}

	public void setIfdocument(Integer ifdocument) {
		this.ifdocument = ifdocument;
	}

	@Column(name = "ifreceipt")
	public Integer getIfreceipt() {
		return this.ifreceipt;
	}

	public void setIfreceipt(Integer ifreceipt) {
		this.ifreceipt = ifreceipt;
	}

	@Column(name = "postscript", length = 100)
	public String getPostscript() {
		return this.postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	
	
	@Column(name = "user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
    
	
	
	

}