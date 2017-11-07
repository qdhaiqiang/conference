package com.centling.conference.exhibit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit")
public class ConfExhibit implements java.io.Serializable {

	// Fields

	private String exhibitId;
	private String meetingId;
	private String exhibitorId;
	private String exhibitCompany;
	private String exhibitName;
	private String exhibitSpecifications;
	private Integer exhibitMount;
	private Float exhibitPrice;
	private String exhibitImage;
	private String exhibitIntro;
	private String exhibitOther;

	// Constructors

	/** default constructor */
	public ConfExhibit() {
	}

	/** full constructor */
	public ConfExhibit(String meetingId,String exhibitorId, String exhibitCompany,
			String exhibitName, String exhibitSpecifications,
			Integer exhibitMount, Float exhibitPrice, String exhibitImage,
			String exhibitIntro, String exhibitOther) {
		this.meetingId = meetingId;
		this.exhibitorId = exhibitorId;
		this.exhibitCompany = exhibitCompany;
		this.exhibitName = exhibitName;
		this.exhibitSpecifications = exhibitSpecifications;
		this.exhibitMount = exhibitMount;
		this.exhibitPrice = exhibitPrice;
		this.exhibitImage = exhibitImage;
		this.exhibitIntro = exhibitIntro;
		this.exhibitOther = exhibitOther;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "exhibit_id", unique = true, nullable = false, length = 40)
	public String getExhibitId() {
		return this.exhibitId;
	}

	public void setExhibitId(String exhibitId) {
		this.exhibitId = exhibitId;
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

	@Column(name = "exhibit_company", length = 50)
	public String getExhibitCompany() {
		return this.exhibitCompany;
	}

	public void setExhibitCompany(String exhibitCompany) {
		this.exhibitCompany = exhibitCompany;
	}

	@Column(name = "exhibit_name", length = 200)
	public String getExhibitName() {
		return this.exhibitName;
	}

	public void setExhibitName(String exhibitName) {
		this.exhibitName = exhibitName;
	}

	@Column(name = "exhibit_specifications", length = 40)
	public String getExhibitSpecifications() {
		return this.exhibitSpecifications;
	}

	public void setExhibitSpecifications(String exhibitSpecifications) {
		this.exhibitSpecifications = exhibitSpecifications;
	}

	@Column(name = "exhibit_mount")
	public Integer getExhibitMount() {
		return this.exhibitMount;
	}

	public void setExhibitMount(Integer exhibitMount) {
		this.exhibitMount = exhibitMount;
	}

	@Column(name = "exhibit_price", precision = 12, scale = 0)
	public Float getExhibitPrice() {
		return this.exhibitPrice;
	}

	public void setExhibitPrice(Float exhibitPrice) {
		this.exhibitPrice = exhibitPrice;
	}

	@Column(name = "exhibit_image", length = 200)
	public String getExhibitImage() {
		return this.exhibitImage;
	}

	public void setExhibitImage(String exhibitImage) {
		this.exhibitImage = exhibitImage;
	}

	@Column(name = "exhibit_intro", length = 65535)
	public String getExhibitIntro() {
		return this.exhibitIntro;
	}

	public void setExhibitIntro(String exhibitIntro) {
		this.exhibitIntro = exhibitIntro;
	}

	@Column(name = "exhibit_other", length = 1024)
	public String getExhibitOther() {
		return this.exhibitOther;
	}

	public void setExhibitOther(String exhibitOther) {
		this.exhibitOther = exhibitOther;
	}

}