package com.centling.conference.exhibitfurniture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.centling.conference.base.Constant;
import com.centling.conference.exhibit.DAO.ConfExhibitDAO;
import com.centling.conference.exhibit.entity.ConfExhibit;
import com.centling.conference.exhibitbooth.DAO.ConfBoothDAO;
import com.centling.conference.exhibitbooth.entity.ConfBooth;
import com.centling.conference.exhibitcompany.DAO.ConfExhibitCompanyDAO;
import com.centling.conference.exhibitcompany.entity.ConfExhibitCompany;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitExpressneedsDAO;
import com.centling.conference.exhibitfurniture.DAO.ConfExhibitFurnitureDAO;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitExpressneeds;
import com.centling.conference.exhibitfurniture.entity.ConfExhibitFurniture;
import com.centling.conference.exhibition.entity.ExhibitionForm;
import com.centling.conference.exhibitrent.DAO.ConfExhibitRentDAO;
import com.centling.conference.exhibitrent.entity.ConfExhibitRent;
import com.centling.conference.exhibitrent.entity.ExhibitFurnitureRent;
import com.centling.conference.util.MyException;

@Service("saveExhibitApplicationService")
public class SaveExhibitApplicationService {
	private static final Logger log = LoggerFactory.getLogger(SaveExhibitApplicationService.class);

	@Resource
	private ConfBoothDAO confBoothDAO;
	@Resource
	private ConfExhibitRentDAO confRentDAO;
	@Resource
	private ConfExhibitFurnitureDAO confExhibitFurnitureDAO;
	@Resource
	private ConfExhibitDAO confExhibitDAO;
	@Resource
	private ConfExhibitExpressneedsDAO confExhibitExpressneedsDAO;
	@Resource
	private ConfExhibitCompanyDAO confExhibitCompanyDAO;
	
	public Map<String, String> saveExhibitionForm(ExhibitionForm exhibitionForm,ConfExhibitCompany company,String meetingId) throws MyException{
		String userid = company.getCompanyId();
		log.info("save ExhibitionForm by exhibition CompanyId:"+userid+",meetingId :"+meetingId);
		Map<String, String> map = new HashMap<String, String>();
		//根据id找到confUser，设置industry,company和companytype字段，并保存
		this.saveExhibitCompany(company);
		//存储用户选中的展位(包括楣板名称)
		this.saveConfBooth(exhibitionForm.getBooths(),company);
		//额外家具租用保存
		this.saveConfFurnitureRent(exhibitionForm.getFurnitures(), userid, meetingId);
		//存储展品信息，展品有id,update;无id,save
		this.saveExhibits(exhibitionForm.getExhibits(), company, meetingId);
		//保存额外物流意向信息
		this.saveExpressneeds(exhibitionForm.getExpressNeeds(), meetingId, userid);
		
		log.info("success, save ExhibitionForm by exhibition userid:"+userid+",meetingId :"+meetingId);
		map.put("status", "1");
		map.put("info", "sucess,保存成功。");
		return map;
	}
	
	public void saveExhibitCompany(ConfExhibitCompany company){
		if(company != null && company.getCompanyId() != null && company.getCompanyId().length() > 0){
			log.info("save confUser's Company and Industry by CompanyId:"+company.getCompanyId());
			ConfExhibitCompany confCompany = confExhibitCompanyDAO.findById(company.getCompanyId());
			company.setLoginEmail(confCompany.getLoginEmail());
			company.setLoginPassword(confCompany.getLoginPassword());
			confExhibitCompanyDAO.merge(company);
			log.info("success, save confUser's Company by CompanyId:"+company.getCompanyId());
		}
		
	}
	
	/**
	 * 存储用户选中的展位
	 * @param booths 页面传来的选择展位的数据
	 */
	public String saveConfBooth(List<ConfBooth> booths,ConfExhibitCompany company) throws MyException{
		log.info("saving ConfBooth,by userid:"+company.getCompanyId()+", instance begin");
		if (booths != null) {
			for (int i = 0; i < booths.size(); i++) {
				String boothId = booths.get(i).getBoothId();
				//当页面选中是state有值，0之前已申请的现在不需重新申请，1之前未申请的现在申请；当未选中时state为null;
				String BoothState= booths.get(i).getBoothState();
				
				//BoothId == null：该展位已经被别的用户选取
				if (boothId != null) {
					ConfBooth booth = confBoothDAO.findById(boothId);
					boolean isNeedSave = false;
					//查询出来的booth在提交之前，（在页面加载数据很久后才提交）有可能被别的展商申请了
					if(BoothState == null && booth.getExhibitorId()!=null && booth.getExhibitorId().equals(company.getCompanyId()) && Integer.valueOf(booth.getBoothState()) == Constant.STATE_USE){
						//未选择或者取消选中，只有该展位之前是被该展商选中的状态才需要改变数据库，否则之前就未被选在此不需要修改
						log.info("update, unUse the ConfBooth,delete by boothId"+boothId+", begin");
						
						isNeedSave = true;
						booth.setBoothCompany(null);
						booth.setExhibitorId(null);
						booth.setBoothLintelName(null);
						booth.setBoothState(Constant.STATE_UNUSE+"");
					}else if(BoothState != null && Integer.valueOf(BoothState) == Constant.STATE_UNUSE){
						//新选中的展号（之前已申请的无需再次申请），并且此时没有其他展商申请了此展位
						log.info("update, use the ConfBooth,save by boothId"+boothId+", begin");
						if(Integer.valueOf(booth.getBoothState()) == Constant.STATE_UNUSE){
							isNeedSave = true;
							booth.setExhibitorId(company.getCompanyId());
							booth.setBoothCompany(company.getCompanyNameCn());
							booth.setBoothLintelName(booths.get(i).getBoothLintelName());
							booth.setBoothState(Constant.STATE_USE+"");
						}else{
							//此展位已被申请，此时需要在页面上给用户提示
							MyException exception = new MyException(booth.getBoothName()+"已被其他展商申请。");
							throw exception;
						}
					}
					if(isNeedSave){
						confBoothDAO.save(booth);
						log.info("update the ConfBooth, by boothId"+boothId+", end");
					}
				}//end if
			}//end for
		}
		return null;
	}
	
	
	/**
	 * 家具保存时，有几个步骤；
		1.遍历前台传来的参数，a:rentid不为空但是数量为0；b:rentid不空数量不为0；c:rentid为空但是数量不为0；d:rentid为空数量为0
		2.每次当a,b,c情况发生时处理;d情况不处理
		3.b处理方式：首先根据furnitureid找出ConfExhibitFurniture AA表中总价和总租数目，根据rentid找出ConfExhibitRent BB表租量和金额；
			更新ConfExhibitFurniture：AA表总价-BB金额+页面金额，AA表租量-BB租量+页面租量；同时更新ConfExhibitRent
		4.a处理方式：首先根据furnitureid找出ConfExhibitFurniture AA表中总价和总租数目，根据rentid找出ConfExhibitRent BB表租量和金额；
			更新ConfExhibitFurniture：AA表总价-BB金额+0，AA表租量-BB租量+0；同时删除ConfExhibitRent
		5.c处理方式：首先根据furnitureid找出ConfExhibitFurniture AA表中总价和总租数目，
			更新ConfExhibitFurniture：AA表总价-0+页面金额，AA表租量-0+页面租量；同时保存新增ConfExhibitRent
	 * @param furnitures
	 * @param userId
	 * @param meetingId
	 */
	public void saveConfFurnitureRent(List<ExhibitFurnitureRent> furnitures,String userId,String meetingId){
		log.info("saving Exhibitorid:"+userId+",ConfExhibitFurnitureRent instance begin");
		for(int i=0;i<furnitures.size();i++){
			//封装的两张表家具与租用的实体
			ExhibitFurnitureRent furnitureRent = furnitures.get(i);
			//家具
			ConfExhibitFurniture furniture = null;
			
			if(furnitureRent.getFurnitureId() != null){
				//查找到额外家具的信息，包括总租量和总金额等
				furniture = confExhibitFurnitureDAO.findById(furnitureRent.getFurnitureId());
				if(furniture != null){
					float rentTotalMount = (furniture.getFurnitureRentMount()==null) ? 0: furniture.getFurnitureRentMount();
					float rentTotalMountNow = 0;
					float hasRentMount = 0;
					float nowRentMount = (furnitureRent.getRentMount()==null) ? 0: furnitureRent.getRentMount();//页面上传来要预定的数量
					ConfExhibitRent exhibitRent = new ConfExhibitRent();
					if(nowRentMount == 0 && (furnitureRent.getRentId() == null || furnitureRent.getRentId().length() == 0)){
						break;
					}
					//判断属于那种情况，分情况处理
					if(furnitureRent.getRentId() != null && furnitureRent.getRentId().length() > 0){
						//a:rentid不为空但是数量为0；b:rentid不空数量不为0
						exhibitRent = confRentDAO.findById(furnitureRent.getRentId());
						hasRentMount = exhibitRent.getRentMount();
						//更新总租量
						rentTotalMountNow = rentTotalMount - hasRentMount + nowRentMount;
						if(nowRentMount == 0){
							//先删除
							log.info(" delete ConfExhibitRent by Exhibitorid:"+userId+",and RentId:"+furnitureRent.getRentId()+",hasRentMount:"+hasRentMount+",nowRentMount:"+nowRentMount);
							confRentDAO.delete(exhibitRent);
							log.info(" delete ConfExhibitRent sucess.");
							
						}else{
							//先更新
							exhibitRent.setRentMount(nowRentMount);
							exhibitRent.setRentCharge(furnitureRent.getRentCharge());
							log.info("update ConfExhibitRent by Exhibitorid:"+userId+",and RentId:"+furnitureRent.getRentId()+",hasRentMount:"+hasRentMount+",nowRentMount:"+nowRentMount);
							confRentDAO.merge(exhibitRent);
							log.info("update ConfExhibitRent sucess.");
						}
					}else{
						//c:rentid为空但是数量不为0
						//更新总租量
						rentTotalMountNow = rentTotalMount - hasRentMount + nowRentMount;
						exhibitRent.setExhibitorId(userId);
						exhibitRent.setFurnitureId(furnitureRent.getFurnitureId());
						exhibitRent.setMeetingId(meetingId);
						exhibitRent.setRentCharge(furnitureRent.getRentCharge());
						exhibitRent.setRentMount(nowRentMount);
						log.info("save ConfExhibitRent by Exhibitorid:"+userId+",and RentId:null,hasRentMount:"+hasRentMount+",nowRentMount:"+nowRentMount);
						confRentDAO.merge(exhibitRent);
						log.info("save ConfExhibitRent by Exhibitorid:"+userId+", sucess.");
						
					}
					
					//再保存
					furniture.setFurnitureRentMount(rentTotalMountNow);
					log.info("save confExhibitFurniture by FurnitureId:"+furnitureRent.getFurnitureId()+",update old rentTotalMount:"+rentTotalMount+" to new rentTotalMount:"+rentTotalMountNow+",for Exhibitorid:"+userId+",hasRentMount:"+hasRentMount+",nowRentMount:"+nowRentMount);
					confExhibitFurnitureDAO.merge(furniture);
					log.info("save confExhibitFurniture  by FurnitureId:"+furnitureRent.getFurnitureId()+",sucess.");
					
				}
			}//end if (FurnitureId() != null)
		}//end for
		log.info("saving Exhibitorid:"+userId+", success.");
	}
	
	public void saveExhibits(List<ConfExhibit> exhibits,ConfExhibitCompany company,String meetingId){
		log.info(" save Exhibits by Exhibitorid:"+company.getCompanyId()+",and meetingId"+meetingId);
		if (exhibits != null) {
			for (int i = 0; i < exhibits.size(); i++) {
				String exhibitId = exhibits.get(i).getExhibitId();
				ConfExhibit tmpExhibit = exhibits.get(i);
				ConfExhibit exhibit = null;
				if (exhibitId != null && exhibitId.length() > 0) {
					exhibit = confExhibitDAO.findById(exhibitId);
				} else if (tmpExhibit.getExhibitName() != null && tmpExhibit.getExhibitName().replace(" ", "").length() > 0) {
					exhibit = new ConfExhibit();
					exhibit.setExhibitorId(company.getCompanyId());
					exhibit.setExhibitCompany(company.getCompanyNameCn());
				}
				if (exhibit != null) {
					exhibit.setMeetingId(meetingId);
					exhibit.setExhibitName(tmpExhibit.getExhibitName());
					exhibit.setExhibitSpecifications(tmpExhibit.getExhibitSpecifications());
					exhibit.setExhibitMount(tmpExhibit.getExhibitMount());
					exhibit.setExhibitPrice(tmpExhibit.getExhibitPrice());
					exhibit.setExhibitImage(tmpExhibit.getExhibitImage());
					exhibit.setExhibitIntro(tmpExhibit.getExhibitIntro());
					exhibit.setExhibitOther(tmpExhibit.getExhibitOther());
					log.info("save or update exhibit by data index:"+i+", and exhibitName is:"+tmpExhibit.getExhibitName());
					confExhibitDAO.merge(exhibit);
					log.info("save the "+i+"'th data sucess.");
				}
			}
			log.info(" save Exhibits by Exhibitorid:"+company.getCompanyId()+",success.");
		}
		log.info(" save Exhibits by Exhibitorid:"+company.getCompanyId()+", no datas.");
	}
	

	/**
	 * 保存或者更新，展商的物流需求
	 * @param confExpressneeds
	 * @param meetingId
	 * @param exhibitorId
	 */
	public void saveExpressneeds(ConfExhibitExpressneeds confExpressneeds,String meetingId,String exhibitorId){
		log.info(" save or update Expressneeds by exhibitorId:"+exhibitorId+",meetingId:"+meetingId);
		if(confExpressneeds != null){
			if(confExpressneeds.getExpressneedsId() != null && confExpressneeds.getExpressneedsId().length() > 0){
				log.info(",and by ExpressneedsId"+confExpressneeds.getExpressneedsId());
			}
			confExpressneeds.setExhibitorId(exhibitorId);
			confExpressneeds.setMeetingId(meetingId);
			
			confExhibitExpressneedsDAO.merge(confExpressneeds);
		}
		
		log.info("save or update Expressneeds by exhibitorId:"+exhibitorId+",success.");
	}
	
}
