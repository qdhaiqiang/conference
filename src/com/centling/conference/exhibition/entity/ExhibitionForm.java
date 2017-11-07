package com.centling.conference.exhibition.entity;

import java.util.List;
import com.centling.conference.exhibit.entity.ConfExhibit;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;
import com.centling.conference.exhibitrent.entity.ExhibitFurnitureRent;

public class ExhibitionForm {

	private List<ConfBooth> booths;
	private List<ConfExhibit> exhibits;
	private List<ExhibitFurnitureRent> furnitures;
	private ConfExhibitExpressneeds expressNeeds;
	
	public List<ConfBooth> getBooths() {
		return booths;
	}
	public void setBooths(List<ConfBooth> booths) {
		this.booths = booths;
	}
	public List<ConfExhibit> getExhibits() {
		return exhibits;
	}
	public void setExhibits(List<ConfExhibit> exhibits) {
		this.exhibits = exhibits;
	}
	public List<ExhibitFurnitureRent> getFurnitures() {
		return furnitures;
	}
	public void setFurnitures(List<ExhibitFurnitureRent> furnitures) {
		this.furnitures = furnitures;
	}
	public ConfExhibitExpressneeds getExpressNeeds() {
		return expressNeeds;
	}
	public void setExpressNeeds(ConfExhibitExpressneeds expressNeeds) {
		this.expressNeeds = expressNeeds;
	}
	
}
