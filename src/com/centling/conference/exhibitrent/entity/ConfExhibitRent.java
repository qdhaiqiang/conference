package com.centling.conference.exhibitrent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibitRent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_rent")
public class ConfExhibitRent implements java.io.Serializable {

	// Fields

	private String rentId;
	private String meetingId;
	private String exhibitorId;
	private String furnitureId;
	private Float rentMount;
	private Float rentCharge;
	private Float rentChargeEn;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfExhibitRent() {
	}

	/** full constructor */
	public ConfExhibitRent(String meetingId, String exhibitorId,
			String furnitureId, Float rentMount, Float rentCharge,
			Float rentChargeEn, String memo) {
		this.meetingId = meetingId;
		this.exhibitorId = exhibitorId;
		this.furnitureId = furnitureId;
		this.rentMount = rentMount;
		this.rentCharge = rentCharge;
		this.rentChargeEn = rentChargeEn;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "rent_id", unique = true, nullable = false, length = 40)
	public String getRentId() {
		return this.rentId;
	}

	public void setRentId(String rentId) {
		this.rentId = rentId;
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

	@Column(name = "furniture_id", length = 40)
	public String getFurnitureId() {
		return this.furnitureId;
	}

	public void setFurnitureId(String furnitureId) {
		this.furnitureId = furnitureId;
	}

	@Column(name = "rent_mount", precision = 12, scale = 0)
	public Float getRentMount() {
		return this.rentMount;
	}

	public void setRentMount(Float rentMount) {
		this.rentMount = rentMount;
	}

	@Column(name = "rent_charge", precision = 12, scale = 0)
	public Float getRentCharge() {
		return this.rentCharge;
	}

	public void setRentCharge(Float rentCharge) {
		this.rentCharge = rentCharge;
	}

	@Column(name = "rent_charge_en", precision = 12, scale = 0)
	public Float getRentChargeEn() {
		return this.rentChargeEn;
	}

	public void setRentChargeEn(Float rentChargeEn) {
		this.rentChargeEn = rentChargeEn;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}