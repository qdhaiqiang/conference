package com.centling.conference.travel.entity;

/**
 * 1.票务统计应该设置所有字段为查询条件：
 	姓名 
	用户类型 
	启程出行方式 
	启程航班号 
	启程出发地 
	启程目的地 
	启程出发时间 
	启程到达时间 
	返程出行方式 
	返程航班号 
	返程出发地 
	返程目的地 
	返程出发时间 
	返程到达时间 
	是否需要送关
 * 2.酒店查询字段：
	姓名、
	用户类型、
	房间类型、
	入住日期、
	退房日期、
	费用类型
 * @author centling
 *
 */
public class SearchParams {
	
	private String cname;
	private String userType;
	private String typeCome;
	private String numberCome;
	private String startPlaceCome;
	private String endPlaceCome;
	private String startTimeCome;
	private String endTimeCome;
	private String typeGo;
	private String numberGo;
	private String startPlaceGo;
	private String endPlaceGo;
	private String startTimeGo;
	private String endTimeGo;
	private String checkpoint;
	private String messageSend;
	
	private String roomType; 
	private String checkInDate; 
	private String checkOutDate; 
	private String organizerPay;
	
	
	public SearchParams() {
		super();
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getTypeCome() {
		return typeCome;
	}
	public void setTypeCome(String typeCome) {
		this.typeCome = typeCome;
	}
	public String getNumberCome() {
		return numberCome;
	}
	public void setNumberCome(String numberCome) {
		this.numberCome = numberCome;
	}
	public String getStartPlaceCome() {
		return startPlaceCome;
	}
	public void setStartPlaceCome(String startPlaceCome) {
		this.startPlaceCome = startPlaceCome;
	}
	public String getEndPlaceCome() {
		return endPlaceCome;
	}
	public void setEndPlaceCome(String endPlaceCome) {
		this.endPlaceCome = endPlaceCome;
	}
	public String getStartTimeCome() {
		return startTimeCome;
	}
	public void setStartTimeCome(String startTimeCome) {
		this.startTimeCome = startTimeCome;
	}
	public String getEndTimeCome() {
		return endTimeCome;
	}
	public void setEndTimeCome(String endTimeCome) {
		this.endTimeCome = endTimeCome;
	}
	public String getTypeGo() {
		return typeGo;
	}
	public void setTypeGo(String typeGo) {
		this.typeGo = typeGo;
	}
	public String getNumberGo() {
		return numberGo;
	}
	public void setNumberGo(String numberGo) {
		this.numberGo = numberGo;
	}
	public String getStartPlaceGo() {
		return startPlaceGo;
	}
	public void setStartPlaceGo(String startPlaceGo) {
		this.startPlaceGo = startPlaceGo;
	}
	public String getEndPlaceGo() {
		return endPlaceGo;
	}
	public void setEndPlaceGo(String endPlaceGo) {
		this.endPlaceGo = endPlaceGo;
	}
	public String getStartTimeGo() {
		return startTimeGo;
	}
	public void setStartTimeGo(String startTimeGo) {
		this.startTimeGo = startTimeGo;
	}
	public String getEndTimeGo() {
		return endTimeGo;
	}
	public void setEndTimeGo(String endTimeGo) {
		this.endTimeGo = endTimeGo;
	}
	public String getCheckpoint() {
		return checkpoint;
	}
	public void setCheckpoint(String checkpoint) {
		this.checkpoint = checkpoint;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getOrganizerPay() {
		return organizerPay;
	}
	public void setOrganizerPay(String organizerPay) {
		this.organizerPay = organizerPay;
	}
	public String getMessageSend() {
		return messageSend;
	}
	public void setMessageSend(String messageSend) {
		this.messageSend = messageSend;
	}
}