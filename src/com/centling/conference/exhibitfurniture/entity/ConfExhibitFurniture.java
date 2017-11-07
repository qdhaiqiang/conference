package com.centling.conference.exhibitfurniture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfExhibitFurniture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_exhibit_furniture")
public class ConfExhibitFurniture implements java.io.Serializable {

	// Fields

	private String furnitureId;
	private String meetingId;
	private String furnitureCode;
	private String furnitureName;
	private String furnitureNameEn;
	private String furnitureDesc;
	private String furnitureDescEn;
	private float furnitureTotalMount;
	private float furnitureRentMount;
	private Integer furnitureRentType;
	private float furniturePrice;
	private float furniturePriceEn;
	private String memo;

	// Constructors

	/** default constructor */
	public ConfExhibitFurniture() {
	}

	/** full constructor */
	public ConfExhibitFurniture(String meetingId, String furnitureCode,
			String furnitureName, String furnitureNameEn, String furnitureDesc,
			String furnitureDescEn, float furnitureTotalMount,
			float furnitureRentMount, Integer furnitureRentType,
			float furniturePrice, float furniturePriceEn, String memo) {
		this.meetingId = meetingId;
		this.furnitureCode = furnitureCode;
		this.furnitureName = furnitureName;
		this.furnitureNameEn = furnitureNameEn;
		this.furnitureDesc = furnitureDesc;
		this.furnitureDescEn = furnitureDescEn;
		this.furnitureTotalMount = furnitureTotalMount;
		this.furnitureRentMount = furnitureRentMount;
		this.furnitureRentType = furnitureRentType;
		this.furniturePrice = furniturePrice;
		this.furniturePriceEn = furniturePriceEn;
		this.memo = memo;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "furniture_id", unique = true, nullable = false, length = 40)
	public String getFurnitureId() {
		return this.furnitureId;
	}

	public void setFurnitureId(String furnitureId) {
		this.furnitureId = furnitureId;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "furniture_code", length = 10)
	public String getFurnitureCode() {
		return this.furnitureCode;
	}

	public void setFurnitureCode(String furnitureCode) {
		this.furnitureCode = furnitureCode;
	}

	@Column(name = "furniture_name", length = 200)
	public String getFurnitureName() {
		return this.furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	@Column(name = "furniture_name_en", length = 200)
	public String getFurnitureNameEn() {
		return this.furnitureNameEn;
	}

	public void setFurnitureNameEn(String furnitureNameEn) {
		this.furnitureNameEn = furnitureNameEn;
	}

	@Column(name = "furniture_desc", length = 500)
	public String getFurnitureDesc() {
		return this.furnitureDesc;
	}

	public void setFurnitureDesc(String furnitureDesc) {
		this.furnitureDesc = furnitureDesc;
	}

	@Column(name = "furniture_desc_en", length = 500)
	public String getFurnitureDescEn() {
		return this.furnitureDescEn;
	}

	public void setFurnitureDescEn(String furnitureDescEn) {
		this.furnitureDescEn = furnitureDescEn;
	}

	@Column(name = "furniture_total_mount", precision = 12, scale = 0)
	public float getFurnitureTotalMount() {
		return this.furnitureTotalMount;
	}

	public void setFurnitureTotalMount(Float furnitureTotalMount) {
		this.furnitureTotalMount = furnitureTotalMount;
	}

	@Column(name = "furniture_rent_mount", precision = 12, scale = 0)
	public Float getFurnitureRentMount() {
		return this.furnitureRentMount;
	}

	public void setFurnitureRentMount(float furnitureRentMount) {
		this.furnitureRentMount = furnitureRentMount;
	}

	@Column(name = "furniture_rent_type")
	public Integer getFurnitureRentType() {
		return this.furnitureRentType;
	}

	public void setFurnitureRentType(Integer furnitureRentType) {
		this.furnitureRentType = furnitureRentType;
	}

	@Column(name = "furniture_price", precision = 12, scale = 0)
	public float getFurniturePrice() {
		return this.furniturePrice;
	}

	public void setFurniturePrice(float furniturePrice) {
		this.furniturePrice = furniturePrice;
	}

	@Column(name = "furniture_price_en", precision = 12, scale = 0)
	public float getFurniturePriceEn() {
		return this.furniturePriceEn;
	}

	public void setFurniturePriceEn(float furniturePriceEn) {
		this.furniturePriceEn = furniturePriceEn;
	}

	@Column(name = "memo", length = 50)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}