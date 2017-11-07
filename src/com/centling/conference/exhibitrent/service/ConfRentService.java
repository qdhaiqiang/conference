package com.centling.conference.exhibitrent.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.exhibitfurniture.DAO.ConfExhibitFurnitureDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;
import com.centling.conference.exhibitrent.DAO.ConfExhibitRentDAO;
import com.centling.conference.exhibitrent.entity.ConfExhibitRent;
import com.centling.conference.exhibitrent.entity.ExhibitFurnitureRent;
import com.centling.conference.user.entity.ConfUser;

@Service("confRentService")
public class ConfRentService {
	private static final Logger log = LoggerFactory.getLogger(ConfRentService.class);

	public List<ConfExhibitRent> findAllRent(String meetingId,String userId){
		List<ConfExhibitRent> rentList= confRentDAO.findAllRent(meetingId,userId);
		return rentList;
	}
	
	public List<ExhibitFurnitureRent> mergeFurnitureRent(List<ConfExhibitFurniture> furnitures,List<ConfExhibitRent> rents){
		List<ExhibitFurnitureRent> list = new ArrayList<ExhibitFurnitureRent>();
		if(furnitures != null && furnitures.size() > 0){
			for(int i=0;i<furnitures.size();i++){
				//String furnitureId, String meetingId, String furnitureCode, String furnitureName, String furnitureDesc,
				//Integer furnitureTotalMount, Integer furnitureRentMount, Float furniturePrice, String memo
				ExhibitFurnitureRent furnitureRent = new ExhibitFurnitureRent(
						furnitures.get(i).getFurnitureId(),furnitures.get(i).getMeetingId(),furnitures.get(i).getFurnitureCode(),
						furnitures.get(i).getFurnitureName(),furnitures.get(i).getFurnitureDesc(),furnitures.get(i).getFurnitureTotalMount(),furnitures.get(i).getFurnitureRentMount(),
						furnitures.get(i).getFurniturePrice(),furnitures.get(i).getMemo(),
						furnitures.get(i).getFurnitureNameEn(),furnitures.get(i).getFurnitureDescEn(),furnitures.get(i).getFurnitureRentType(),furnitures.get(i).getFurniturePriceEn());
				
				for(int j=0;j<rents.size();j++){
					if(furnitures.get(i).getFurnitureId().equals(rents.get(j).getFurnitureId())){
						//rentId; exhibitorId; rentMount; rentCharge; rentMemo;
						furnitureRent.setRentId(rents.get(j).getRentId());
						furnitureRent.setExhibitorId(rents.get(j).getExhibitorId());
						furnitureRent.setRentMount(rents.get(j).getRentMount());
						furnitureRent.setRentCharge(rents.get(j).getRentCharge());
						furnitureRent.setRentMemo(rents.get(j).getMemo());
						break;
					}
				}//end for rents list
				list.add(furnitureRent);
			}//end for furniture list
		}
		return list;
	}
	
	@Resource
	private ConfExhibitRentDAO confRentDAO;
}
