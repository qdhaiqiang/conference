package com.centling.conference.meeting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


/**
 * ConfMeeting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="conf_meeting"
    
)

public class ConfMeeting  implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String nameEn;
	private String startTime;
	private String endTime;
	private String city;
	private String locationId;
	private String bannerPic;
	private String url;
	private String meetingIntro;
	private String meetingIntroEn;
	private String meetingStatus;

	// Constructors

	/** default constructor */
	public ConfMeeting() {
	}

	/** full constructor */
	public ConfMeeting(String name, String nameEn, String startTime,
			String endTime, String city, String locationId, String bannerPic,
			String url, String meetingIntro, String meetingIntroEn,
			String meetingStatus) {
		this.name = name;
		this.nameEn = nameEn;
		this.startTime = startTime;
		this.endTime = endTime;
		this.city = city;
		this.locationId = locationId;
		this.bannerPic = bannerPic;
		this.url = url;
		this.meetingIntro = meetingIntro;
		this.meetingIntroEn = meetingIntroEn;
		this.meetingStatus = meetingStatus;
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

	@Column(name = "name", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_en", length = 200)
	public String getNameEn() {
		return this.nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	@Column(name = "start_time", length = 20)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 20)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "city", length = 3)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "location_id", length = 40)
	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	@Column(name = "banner_pic", length = 200)
	public String getBannerPic() {
		return this.bannerPic;
	}

	public void setBannerPic(String bannerPic) {
		this.bannerPic = bannerPic;
	}

	@Column(name = "url", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "meeting_intro", length = 500)
	public String getMeetingIntro() {
		return this.meetingIntro;
	}

	public void setMeetingIntro(String meetingIntro) {
		this.meetingIntro = meetingIntro;
	}

	@Column(name = "meeting_intro_en", length = 500)
	public String getMeetingIntroEn() {
		return this.meetingIntroEn;
	}

	public void setMeetingIntroEn(String meetingIntroEn) {
		this.meetingIntroEn = meetingIntroEn;
	}

	@Column(name = "meeting_status", length = 1)
	public String getMeetingStatus() {
		return this.meetingStatus;
	}

	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}

}