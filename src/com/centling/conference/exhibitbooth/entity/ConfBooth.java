package com.centling.conference.exhibitbooth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfBooth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_booth")
public class ConfBooth implements java.io.Serializable {

	// Fields

	private String boothId;
	private String parentId;
	private String meetingId;
	private String exhibitorId;
	private String boothCompany;
	private String boothName;
	private String boothLintelName;
	private String boothState;

	// Constructors

	/** default constructor */
	public ConfBooth() {
	}

	/** full constructor */
	public ConfBooth(String meetingId,String parentId, String exhibitorId, String boothCompany,
			String boothName, String boothLintelName, String boothState) {
		this.parentId = parentId;
		this.meetingId = meetingId;
		this.exhibitorId = exhibitorId;
		this.boothCompany = boothCompany;
		this.boothName = boothName;
		this.boothLintelName = boothLintelName;
		this.boothState = boothState;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "booth_id", unique = true, nullable = false, length = 40)
	public String getBoothId() {
		return this.boothId;
	}

	public void setBoothId(String boothId) {
		this.boothId = boothId;
	}

	@Column(name = "parent_id", length = 40)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	@Column(name = "booth_company", length = 50)
	public String getBoothCompany() {
		return this.boothCompany;
	}

	public void setBoothCompany(String boothCompany) {
		this.boothCompany = boothCompany;
	}

	@Column(name = "booth_name", length = 40)
	public String getBoothName() {
		return this.boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	@Column(name = "booth_lintel_name", length = 40)
	public String getBoothLintelName() {
		return this.boothLintelName;
	}

	public void setBoothLintelName(String boothLintelName) {
		this.boothLintelName = boothLintelName;
	}

	@Column(name = "booth_state")
	public String getBoothState() {
		return this.boothState;
	}

	public void setBoothState(String boothState) {
		this.boothState = boothState;
	}

}