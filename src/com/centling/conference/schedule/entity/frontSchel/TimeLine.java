package com.centling.conference.schedule.entity.frontSchel;

import java.util.Set;

public class TimeLine {

	private String headline;
	private String type;
	private String text;
	private String startDate;
	private Set<Date> date;
	public Set<Date> getDate() {
		return date;
	}
	public void setDate(Set<Date> date) {
		this.date = date;
	}
	private Set<Era> eras;
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Set<Era> getEras() {
		return eras;
	}
	public void setEras(Set<Era> eras) {
		this.eras = eras;
	}
	@Override
	public String toString() {
		return "TimeLine [headline=" + headline + ", type=" + type + ", text="
				+ text + ", startDate=" + startDate + "]";
	}
	
	
}
