package com.centling.conference.exhibitfurniture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibitExpressneeds entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_expressneeds")
public class ConfExhibitExpressneeds implements java.io.Serializable {

	// Fields

	private String expressneedsId;
	private String meetingId;
	private String exhibitorId;
	private String expressNeed;
	private String expressFile;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfExhibitExpressneeds() {
	}

	/** full constructor */
	public ConfExhibitExpressneeds(String meetingId, String exhibitorId,
			String expressNeed, String expressFile, String memo) {
		this.meetingId = meetingId;
		this.exhibitorId = exhibitorId;
		this.expressNeed = expressNeed;
		this.expressFile = expressFile;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "expressneeds_id", unique = true, nullable = false, length = 40)
	public String getExpressneedsId() {
		return this.expressneedsId;
	}

	public void setExpressneedsId(String expressneedsId) {
		this.expressneedsId = expressneedsId;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "exhibitor_id", length = 40)
	public String getExhibitorId() {
		return this.exhibitorId;
	}

	public void setExhibitorId(String exhibitorId) {
		this.exhibitorId = exhibitorId;
	}

	@Column(name = "express_need", length = 2)
	public String getExpressNeed() {
		return this.expressNeed;
	}

	public void setExpressNeed(String expressNeed) {
		this.expressNeed = expressNeed;
	}

	@Column(name = "express_file", length = 500)
	public String getExpressFile() {
		return this.expressFile;
	}

	public void setExpressFile(String expressFile) {
		this.expressFile = expressFile;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}