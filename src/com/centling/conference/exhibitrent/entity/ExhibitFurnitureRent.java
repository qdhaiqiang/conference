package com.centling.conference.exhibitrent.entity;

/**
 * 在加载用户租用额外家具时，将家具表和租用表合并
 * @author Alisa
 *
 */
public class ExhibitFurnitureRent {
	//家具表
	private String furnitureId;
	private String meetingId;
	private String furnitureCode;
	private String furnitureName;
	private String furnitureNameEn;
	private String furnitureDesc;
	private String furnitureDescEn;
	private Float furnitureTotalMount;
	private Float furnitureRentMount;
	private Integer furnitureRentType;
	private Float furniturePrice;
	private Float furniturePriceEn;
	private String memo;
	
	//家具租用表
	private String rentId;
	private String exhibitorId;
	private Float rentMount;
	private Float rentCharge;
	private Float rentChargeEn;
	private String rentMemo;
	
	
	public ExhibitFurnitureRent() {
		super();
	}
	
	public ExhibitFurnitureRent(String furnitureId, String meetingId, String furnitureCode, String furnitureName,String furnitureDesc,
			Float furnitureTotalMount, Float furnitureRentMount, Float furniturePrice, String memo,
			String furnitureNameEn,String furnitureDescEn,Integer furnitureRentType,Float furniturePriceEn) {
		super();
		this.furnitureId = furnitureId;
		this.meetingId = meetingId;
		this.furnitureCode = furnitureCode;
		this.furnitureName = furnitureName;
		this.furnitureDesc = furnitureDesc;
		this.furnitureTotalMount = furnitureTotalMount;
		this.furnitureRentMount = furnitureRentMount;
		this.furniturePrice = furniturePrice;
		this.memo = memo;
		this.furnitureNameEn = furnitureNameEn;
		this.furnitureDescEn = furnitureDescEn;
		this.furnitureRentType = furnitureRentType;
		this.furniturePriceEn = furniturePriceEn;
	}

	public String getFurnitureId() {
		return furnitureId;
	}

	public void setFurnitureId(String furnitureId) {
		this.furnitureId = furnitureId;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getFurnitureCode() {
		return furnitureCode;
	}

	public void setFurnitureCode(String furnitureCode) {
		this.furnitureCode = furnitureCode;
	}

	public String getFurnitureName() {
		return furnitureName;
	}

	public void setFurnitureName(String furnitureName) {
		this.furnitureName = furnitureName;
	}

	public String getFurnitureNameEn() {
		return furnitureNameEn;
	}

	public void setFurnitureNameEn(String furnitureNameEn) {
		this.furnitureNameEn = furnitureNameEn;
	}

	public String getFurnitureDesc() {
		return furnitureDesc;
	}

	public void setFurnitureDesc(String furnitureDesc) {
		this.furnitureDesc = furnitureDesc;
	}

	public String getFurnitureDescEn() {
		return furnitureDescEn;
	}

	public void setFurnitureDescEn(String furnitureDescEn) {
		this.furnitureDescEn = furnitureDescEn;
	}

	public Float getFurnitureTotalMount() {
		return furnitureTotalMount;
	}

	public void setFurnitureTotalMount(Float furnitureTotalMount) {
		this.furnitureTotalMount = furnitureTotalMount;
	}

	public Float getFurnitureRentMount() {
		return furnitureRentMount;
	}

	public void setFurnitureRentMount(Float furnitureRentMount) {
		this.furnitureRentMount = furnitureRentMount;
	}

	public Integer getFurnitureRentType() {
		return furnitureRentType;
	}

	public void setFurnitureRentType(Integer furnitureRentType) {
		this.furnitureRentType = furnitureRentType;
	}

	public Float getFurniturePrice() {
		return furniturePrice;
	}

	public void setFurniturePrice(Float furniturePrice) {
		this.furniturePrice = furniturePrice;
	}

	public Float getFurniturePriceEn() {
		return furniturePriceEn;
	}

	public void setFurniturePriceEn(Float furniturePriceEn) {
		this.furniturePriceEn = furniturePriceEn;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getRentId() {
		return rentId;
	}

	public void setRentId(String rentId) {
		this.rentId = rentId;
	}

	public String getExhibitorId() {
		return exhibitorId;
	}

	public void setExhibitorId(String exhibitorId) {
		this.exhibitorId = exhibitorId;
	}

	public Float getRentMount() {
		return rentMount;
	}

	public void setRentMount(Float rentMount) {
		this.rentMount = rentMount;
	}

	public Float getRentCharge() {
		return rentCharge;
	}

	public void setRentCharge(Float rentCharge) {
		this.rentCharge = rentCharge;
	}

	public Float getRentChargeEn() {
		return rentChargeEn;
	}

	public void setRentChargeEn(Float rentChargeEn) {
		this.rentChargeEn = rentChargeEn;
	}

	public String getRentMemo() {
		return rentMemo;
	}

	public void setRentMemo(String rentMemo) {
		this.rentMemo = rentMemo;
	}
	
}
