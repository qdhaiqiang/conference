package com.centling.conference.exhibitcompany.entity;

public class ExhibitBoothStatistic {

	private String exhibitionId;
	private String exhibitionName;
	private String rentAmount;
	private String rentState;
	
	public ExhibitBoothStatistic() {
		super();
	}
	public ExhibitBoothStatistic(String exhibitionId, String exhibitionName,
			String rentAmount, String rentState) {
		super();
		this.exhibitionId = exhibitionId;
		this.exhibitionName = exhibitionName;
		this.rentAmount = rentAmount;
		this.rentState = rentState;
	}
	public String getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(String exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	public String getExhibitionName() {
		return exhibitionName;
	}
	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}
	public String getRentAmount() {
		return rentAmount;
	}
	public void setRentAmount(String rentAmount) {
		this.rentAmount = rentAmount;
	}
	public String getRentState() {
		return rentState;
	}
	public void setRentState(String rentState) {
		this.rentState = rentState;
	}
	
}
