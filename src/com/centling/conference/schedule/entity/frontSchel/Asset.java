package com.centling.conference.schedule.entity.frontSchel;

public class Asset {

	private String media;
	private String credit;
	private String caption;
	
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	@Override
	public String toString() {
		return "Asset [media=" + media + ", credit=" + credit + ", caption="
				+ caption + "]";
	}
	
	
	
}
