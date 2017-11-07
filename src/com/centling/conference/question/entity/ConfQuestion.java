package com.centling.conference.question.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_question")
public class ConfQuestion implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String scheduleId;
	private String guestId;
	private String userId;
	private String nickname;
	private String content;
	private String time;
	private String state;
	private String wallTime;
	private String contentEn;

	// Constructors

	/** default constructor */
	public ConfQuestion() {
	}

	/** full constructor */
	public ConfQuestion(String meetingId, String scheduleId, String guestId, String userId, String content, String time, String state,String wallTime, String contentEn) {
		this.meetingId = meetingId;
		this.scheduleId = scheduleId;
		this.guestId = guestId;
		this.userId = userId;
		this.content = content;
		this.time = time;
		this.state = state;
		this.wallTime = wallTime;
		this.contentEn = contentEn;
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

	@Column(name = "schedule_id", length = 40)
	public String getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Column(name = "guest_id", length = 40)
	public String getGuestId() {
		return this.guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	@Column(name = "user_id", length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	} 
	
	@Column(name = "nickname", length = 20)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	@Column(name = "content", length = 200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "time", length = 20)
	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Column(name = "state", length = 1)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="wall_time",length = 20)
    public String getWallTime() {
        return wallTime;
    }

    public void setWallTime(String wallTime) {
        this.wallTime = wallTime;
    }

    @Column(name="content_en", length = 200)
    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }
    

}