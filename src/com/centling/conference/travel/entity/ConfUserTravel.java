package com.centling.conference.travel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfUserTravel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_user_travel")
public class ConfUserTravel implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String meetingId;
	private String typeCome;
	private String numberCome;
	private String startPlaceCome;
	private String endPlaceCome;
	private String startTimeCome;
	private String endTimeCome;
	private String remarkCome;
	private String typeGo;
	private String numberGo;
	private String startPlaceGo;
	private String endPlaceGo;
	private String startTimeGo;
	private String endTimeGo;
	private String remarkGo;
	private String emailSend;
	private String messageSend;

	// Constructors

	/** default constructor */
	public ConfUserTravel() {
	}

	/** full constructor */
	public ConfUserTravel(String userId, String meetingId, String typeCome,
			String numberCome, String startPlaceCome, String endPlaceCome,
			String startTimeCome, String endTimeCome, String remarkCome,
			String typeGo, String numberGo, String startPlaceGo,
			String endPlaceGo, String startTimeGo, String endTimeGo,
			String remarkGo) {
		this.userId = userId;
		this.meetingId = meetingId;
		this.typeCome = typeCome;
		this.numberCome = numberCome;
		this.startPlaceCome = startPlaceCome;
		this.endPlaceCome = endPlaceCome;
		this.startTimeCome = startTimeCome;
		this.endTimeCome = endTimeCome;
		this.remarkCome = remarkCome;
		this.typeGo = typeGo;
		this.numberGo = numberGo;
		this.startPlaceGo = startPlaceGo;
		this.endPlaceGo = endPlaceGo;
		this.startTimeGo = startTimeGo;
		this.endTimeGo = endTimeGo;
		this.remarkGo = remarkGo;
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

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "type_come", length = 1)
	public String getTypeCome() {
		return this.typeCome;
	}

	public void setTypeCome(String typeCome) {
		this.typeCome = typeCome;
	}

	@Column(name = "number_come", length = 20)
	public String getNumberCome() {
		return this.numberCome;
	}

	public void setNumberCome(String numberCome) {
		this.numberCome = numberCome;
	}

	@Column(name = "start_place_come", length = 200)
	public String getStartPlaceCome() {
		return this.startPlaceCome;
	}

	public void setStartPlaceCome(String startPlaceCome) {
		this.startPlaceCome = startPlaceCome;
	}

	@Column(name = "end_place_come", length = 200)
	public String getEndPlaceCome() {
		return this.endPlaceCome;
	}

	public void setEndPlaceCome(String endPlaceCome) {
		this.endPlaceCome = endPlaceCome;
	}

	@Column(name = "start_time_come", length = 20)
	public String getStartTimeCome() {
		return this.startTimeCome;
	}

	public void setStartTimeCome(String startTimeCome) {
		this.startTimeCome = startTimeCome;
	}

	@Column(name = "end_time_come", length = 20)
	public String getEndTimeCome() {
		return this.endTimeCome;
	}

	public void setEndTimeCome(String endTimeCome) {
		this.endTimeCome = endTimeCome;
	}

	@Column(name = "remark_come", length = 1024)
	public String getRemarkCome() {
		return this.remarkCome;
	}

	public void setRemarkCome(String remarkCome) {
		this.remarkCome = remarkCome;
	}

	@Column(name = "type_go", length = 1)
	public String getTypeGo() {
		return this.typeGo;
	}

	public void setTypeGo(String typeGo) {
		this.typeGo = typeGo;
	}

	@Column(name = "number_go", length = 20)
	public String getNumberGo() {
		return this.numberGo;
	}

	public void setNumberGo(String numberGo) {
		this.numberGo = numberGo;
	}

	@Column(name = "start_place_go", length = 200)
	public String getStartPlaceGo() {
		return this.startPlaceGo;
	}

	public void setStartPlaceGo(String startPlaceGo) {
		this.startPlaceGo = startPlaceGo;
	}

	@Column(name = "end_place_go", length = 200)
	public String getEndPlaceGo() {
		return this.endPlaceGo;
	}

	public void setEndPlaceGo(String endPlaceGo) {
		this.endPlaceGo = endPlaceGo;
	}

	@Column(name = "start_time_go", length = 20)
	public String getStartTimeGo() {
		return this.startTimeGo;
	}

	public void setStartTimeGo(String startTimeGo) {
		this.startTimeGo = startTimeGo;
	}

	@Column(name = "end_time_go", length = 20)
	public String getEndTimeGo() {
		return this.endTimeGo;
	}

	public void setEndTimeGo(String endTimeGo) {
		this.endTimeGo = endTimeGo;
	}

	@Column(name = "remark_go", length = 1024)
	public String getRemarkGo() {
		return this.remarkGo;
	}

	public void setRemarkGo(String remarkGo) {
		this.remarkGo = remarkGo;
	}

	@Column(name="email_send")
	public String getEmailSend() {
		return emailSend;
	}

	public void setEmailSend(String emailSend) {
		this.emailSend = emailSend;
	}

	@Column(name="message_send")
	public String getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}
}