package com.centling.conference.exhibition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibition")
public class ConfExhibition implements java.io.Serializable {

	// Fields

	private String exhibitionId;
	private String meetingId;
	private String exhibitionName;
	private String exhibitionType;
	private String exhibitionImage;
	private Float exhibitionPrice;
	private Float exhibitionServiceCharge;

	// Constructors

	/** default constructor */
	public ConfExhibition() {
	}

	/** full constructor */
	public ConfExhibition(String meetingId,String exhibitionName, String exhibitionType,
			String exhibitionImage, Float exhibitionPrice,
			Float exhibitionServiceCharge) {
		this.meetingId = meetingId;
		this.exhibitionName = exhibitionName;
		this.exhibitionType = exhibitionType;
		this.exhibitionImage = exhibitionImage;
		this.exhibitionPrice = exhibitionPrice;
		this.exhibitionServiceCharge = exhibitionServiceCharge;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "exhibition_id", unique = true, nullable = false, length = 40)
	public String getExhibitionId() {
		return this.exhibitionId;
	}

	public void setExhibitionId(String exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	
	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "exhibition_name", length = 40)
	public String getExhibitionName() {
		return this.exhibitionName;
	}

	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}

	@Column(name = "exhibition_type", length = 2)
	public String getExhibitionType() {
		return this.exhibitionType;
	}

	public void setExhibitionType(String exhibitionType) {
		this.exhibitionType = exhibitionType;
	}

	@Column(name = "exhibition_image", length = 200)
	public String getExhibitionImage() {
		return this.exhibitionImage;
	}

	public void setExhibitionImage(String exhibitionImage) {
		this.exhibitionImage = exhibitionImage;
	}

	@Column(name = "exhibition_price", precision = 12, scale = 0)
	public Float getExhibitionPrice() {
		return this.exhibitionPrice;
	}

	public void setExhibitionPrice(Float exhibitionPrice) {
		this.exhibitionPrice = exhibitionPrice;
	}

	@Column(name = "exhibition_service_charge", precision = 12, scale = 0)
	public Float getExhibitionServiceCharge() {
		return this.exhibitionServiceCharge;
	}

	public void setExhibitionServiceCharge(Float exhibitionServiceCharge) {
		this.exhibitionServiceCharge = exhibitionServiceCharge;
	}

}