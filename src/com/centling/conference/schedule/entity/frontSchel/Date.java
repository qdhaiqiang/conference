package com.centling.conference.schedule.entity.frontSchel;

public class Date {

	private String startDate;
	private String endDate;
	private String headline;
	private String text;
	private Asset asset;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	@Override
	public String toString() {
		return "Date [startDate=" + startDate + ", endDate=" + endDate
				+ ", headline=" + headline + ", text=" + text + "]";
	}
	
	
}
