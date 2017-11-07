package com.centling.conference.exclusiveschedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ConfVInfoId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_v_info")
public class ConfVInfo implements java.io.Serializable {

	// Fields

	private String meetingId;
	private String userId;
	private String userType;
	private String cname;
	private String ename;
	private String sex;
	private String birth;
	private String nation;
	private String religion;
	private String dietTaboo;
	private String medlHis;
	private String foodAllergies;
	private String address;
	private String postcode;
	private String tele;
	private String mobile;
	private String email;
	private String fax;
	private String contactPerson;
	private String spouseInfo;
	private String entourageInfo;
	private String certType;
	private String certValue;
	private String entryType;
	private String entryPlace;
	private String entryDate;
	private String entryValidity;
	private String entryNum;
	private String entryEndmtDate;
	private String entryEndmtValidity;
	private String company;
	private String compnyType;
	private String industry;
	private String workContent;
	private String superviserName;
	private String superviserPhone;
	private String position;
	private String selfIntro;
	private String selfIntroEn;
	private String useRealname;
	private String useAlias;
	private String ualias;
	private String useOtherPtitle;
	private String positionTitle;
	private String remark;
	private String needInvite;
	private String needVisa;
	private String needFaxInvite;
	private String needChinaVisaService;
	private String departureRegion;
	private String inFromMacau;
	private String departurePlace;
	private String departureDate;
	private String departureTimePeriod;
	private String returnPlace;
	private String returnDate;
	private String returnTimePeriod;
	private String checkpoint;
	private String normalRoomService;
	private String normalRoomExtra;
	private String specialDemands;
	private String cultureRoute;
	private String speechesTitle;
	private String pptneed;
	private String bringSpouse;
	private String spouseEmail;
	private String bringEntourage;
	private String entourageNum;
	private String entourageEmail;
	private String importInfo;
	private String interviewGuest;
	private String sameContactPerson;
	private String attendConference;
	private String activities;
	private String arrivedLeave;
	private String roundTrip;
	private String otherNeeds;
	private String exhibitsType;
	private String standIntro;
	private String mainProduct;
	private String quantity;
	private String special;
	private String advertise;
	private String sponsor;
	private String video;
	private String channels;
	private String anticipation;
	private String purposes;
	private String feelings;
	private String careAbout;
	private String opitions;
	private String suggestions;
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
	private String restaurant;
	private String roomType;
	private String roomNum;
	private String checkInDate;
	private String checkOutDate;

	// Constructors

	/** default constructor */
	public ConfVInfo() {
	}

	/** full constructor */
	public ConfVInfo(String meetingId, String userId, String userType, String cname, String ename, String sex, String birth, String nation, String religion, String dietTaboo, String medlHis, String foodAllergies, String address, String postcode, String tele, String mobile,
			String email, String fax, String contactPerson, String spouseInfo, String entourageInfo, String certType, String certValue, String entryType, String entryPlace, String entryDate, String entryValidity, String entryNum, String entryEndmtDate, String entryEndmtValidity,
			String company, String compnyType, String industry, String workContent, String superviserName, String superviserPhone, String position, String selfIntro, String selfIntroEn, String useRealname, String useAlias, String ualias, String useOtherPtitle,
			String positionTitle, String remark, String needInvite, String needVisa, String needFaxInvite, String needChinaVisaService, String departureRegion, String inFromMacau, String departurePlace, String departureDate, String departureTimePeriod, String returnPlace,
			String returnDate, String returnTimePeriod, String checkpoint, String normalRoomService, String normalRoomExtra, String specialDemands, String cultureRoute, String speechesTitle, String pptneed, String bringSpouse, String spouseEmail, String bringEntourage,
			String entourageNum, String entourageEmail, String importInfo, String interviewGuest, String sameContactPerson, String attendConference, String activities, String arrivedLeave, String roundTrip, String otherNeeds, String exhibitsType, String standIntro,
			String mainProduct, String quantity, String special, String advertise, String sponsor, String video, String channels, String anticipation, String purposes, String feelings, String careAbout, String opitions, String suggestions, String typeCome, String numberCome,
			String startPlaceCome, String endPlaceCome, String startTimeCome, String endTimeCome, String typeGo, String numberGo, String startPlaceGo, String endPlaceGo, String startTimeGo, String endTimeGo, String restaurant, String roomType, String roomNum, String checkInDate,
			String checkOutDate) {
		this.meetingId = meetingId;
		this.userId = userId;
		this.userType = userType;
		this.cname = cname;
		this.ename = ename;
		this.sex = sex;
		this.birth = birth;
		this.nation = nation;
		this.religion = religion;
		this.dietTaboo = dietTaboo;
		this.medlHis = medlHis;
		this.foodAllergies = foodAllergies;
		this.address = address;
		this.postcode = postcode;
		this.tele = tele;
		this.mobile = mobile;
		this.email = email;
		this.fax = fax;
		this.contactPerson = contactPerson;
		this.spouseInfo = spouseInfo;
		this.entourageInfo = entourageInfo;
		this.certType = certType;
		this.certValue = certValue;
		this.entryType = entryType;
		this.entryPlace = entryPlace;
		this.entryDate = entryDate;
		this.entryValidity = entryValidity;
		this.entryNum = entryNum;
		this.entryEndmtDate = entryEndmtDate;
		this.entryEndmtValidity = entryEndmtValidity;
		this.company = company;
		this.compnyType = compnyType;
		this.industry = industry;
		this.workContent = workContent;
		this.superviserName = superviserName;
		this.superviserPhone = superviserPhone;
		this.position = position;
		this.selfIntro = selfIntro;
		this.selfIntroEn = selfIntroEn;
		this.useRealname = useRealname;
		this.useAlias = useAlias;
		this.ualias = ualias;
		this.useOtherPtitle = useOtherPtitle;
		this.positionTitle = positionTitle;
		this.remark = remark;
		this.needInvite = needInvite;
		this.needVisa = needVisa;
		this.needFaxInvite = needFaxInvite;
		this.needChinaVisaService = needChinaVisaService;
		this.departureRegion = departureRegion;
		this.inFromMacau = inFromMacau;
		this.departurePlace = departurePlace;
		this.departureDate = departureDate;
		this.departureTimePeriod = departureTimePeriod;
		this.returnPlace = returnPlace;
		this.returnDate = returnDate;
		this.returnTimePeriod = returnTimePeriod;
		this.checkpoint = checkpoint;
		this.normalRoomService = normalRoomService;
		this.normalRoomExtra = normalRoomExtra;
		this.specialDemands = specialDemands;
		this.cultureRoute = cultureRoute;
		this.speechesTitle = speechesTitle;
		this.pptneed = pptneed;
		this.bringSpouse = bringSpouse;
		this.spouseEmail = spouseEmail;
		this.bringEntourage = bringEntourage;
		this.entourageNum = entourageNum;
		this.entourageEmail = entourageEmail;
		this.importInfo = importInfo;
		this.interviewGuest = interviewGuest;
		this.sameContactPerson = sameContactPerson;
		this.attendConference = attendConference;
		this.activities = activities;
		this.arrivedLeave = arrivedLeave;
		this.roundTrip = roundTrip;
		this.otherNeeds = otherNeeds;
		this.exhibitsType = exhibitsType;
		this.standIntro = standIntro;
		this.mainProduct = mainProduct;
		this.quantity = quantity;
		this.special = special;
		this.advertise = advertise;
		this.sponsor = sponsor;
		this.video = video;
		this.channels = channels;
		this.anticipation = anticipation;
		this.purposes = purposes;
		this.feelings = feelings;
		this.careAbout = careAbout;
		this.opitions = opitions;
		this.suggestions = suggestions;
		this.typeCome = typeCome;
		this.numberCome = numberCome;
		this.startPlaceCome = startPlaceCome;
		this.endPlaceCome = endPlaceCome;
		this.startTimeCome = startTimeCome;
		this.endTimeCome = endTimeCome;
		this.typeGo = typeGo;
		this.numberGo = numberGo;
		this.startPlaceGo = startPlaceGo;
		this.endPlaceGo = endPlaceGo;
		this.startTimeGo = startTimeGo;
		this.endTimeGo = endTimeGo;
		this.restaurant = restaurant;
		this.roomType = roomType;
		this.roomNum = roomNum;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	// Property accessors
	@Id
	@Column(name = "meetingId", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Id
	@Column(name = "userId", length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "userType", length = 2)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "cname", length = 50)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "ename", length = 50)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "birth", length = 10)
	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Column(name = "nation", length = 3)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "religion", length = 50)
	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	@Column(name = "dietTaboo", length = 100)
	public String getDietTaboo() {
		return this.dietTaboo;
	}

	public void setDietTaboo(String dietTaboo) {
		this.dietTaboo = dietTaboo;
	}

	@Column(name = "medlHis", length = 200)
	public String getMedlHis() {
		return this.medlHis;
	}

	public void setMedlHis(String medlHis) {
		this.medlHis = medlHis;
	}

	@Column(name = "foodAllergies", length = 200)
	public String getFoodAllergies() {
		return this.foodAllergies;
	}

	public void setFoodAllergies(String foodAllergies) {
		this.foodAllergies = foodAllergies;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "postcode", length = 50)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "tele", length = 50)
	public String getTele() {
		return this.tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	@Column(name = "mobile", length = 50)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 80)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fax", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "contactPerson", length = 2000)
	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Column(name = "spouseInfo", length = 65535)
	public String getSpouseInfo() {
		return this.spouseInfo;
	}

	public void setSpouseInfo(String spouseInfo) {
		this.spouseInfo = spouseInfo;
	}

	@Column(name = "entourageInfo", length = 65535)
	public String getEntourageInfo() {
		return this.entourageInfo;
	}

	public void setEntourageInfo(String entourageInfo) {
		this.entourageInfo = entourageInfo;
	}

	@Column(name = "certType", length = 1)
	public String getCertType() {
		return this.certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	@Column(name = "certValue", length = 50)
	public String getCertValue() {
		return this.certValue;
	}

	public void setCertValue(String certValue) {
		this.certValue = certValue;
	}

	@Column(name = "entryType", length = 1)
	public String getEntryType() {
		return this.entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Column(name = "entryPlace", length = 200)
	public String getEntryPlace() {
		return this.entryPlace;
	}

	public void setEntryPlace(String entryPlace) {
		this.entryPlace = entryPlace;
	}

	@Column(name = "entryDate", length = 20)
	public String getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "entryValidity", length = 20)
	public String getEntryValidity() {
		return this.entryValidity;
	}

	public void setEntryValidity(String entryValidity) {
		this.entryValidity = entryValidity;
	}

	@Column(name = "entryNum", length = 50)
	public String getEntryNum() {
		return this.entryNum;
	}

	public void setEntryNum(String entryNum) {
		this.entryNum = entryNum;
	}

	@Column(name = "entryEndmtDate", length = 20)
	public String getEntryEndmtDate() {
		return this.entryEndmtDate;
	}

	public void setEntryEndmtDate(String entryEndmtDate) {
		this.entryEndmtDate = entryEndmtDate;
	}

	@Column(name = "entryEndmtValidity", length = 20)
	public String getEntryEndmtValidity() {
		return this.entryEndmtValidity;
	}

	public void setEntryEndmtValidity(String entryEndmtValidity) {
		this.entryEndmtValidity = entryEndmtValidity;
	}

	@Column(name = "company", length = 50)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "compnyType", length = 2)
	public String getCompnyType() {
		return this.compnyType;
	}

	public void setCompnyType(String compnyType) {
		this.compnyType = compnyType;
	}

	@Column(name = "industry", length = 2)
	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	@Column(name = "workContent", length = 200)
	public String getWorkContent() {
		return this.workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	@Column(name = "superviserName", length = 200)
	public String getSuperviserName() {
		return this.superviserName;
	}

	public void setSuperviserName(String superviserName) {
		this.superviserName = superviserName;
	}

	@Column(name = "superviserPhone", length = 200)
	public String getSuperviserPhone() {
		return this.superviserPhone;
	}

	public void setSuperviserPhone(String superviserPhone) {
		this.superviserPhone = superviserPhone;
	}

	@Column(name = "position", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "selfIntro", length = 1024)
	public String getSelfIntro() {
		return this.selfIntro;
	}

	public void setSelfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}

	@Column(name = "selfIntroEn", length = 1024)
	public String getSelfIntroEn() {
		return this.selfIntroEn;
	}

	public void setSelfIntroEn(String selfIntroEn) {
		this.selfIntroEn = selfIntroEn;
	}

	@Column(name = "useRealname", length = 1)
	public String getUseRealname() {
		return this.useRealname;
	}

	public void setUseRealname(String useRealname) {
		this.useRealname = useRealname;
	}

	@Column(name = "useAlias", length = 1)
	public String getUseAlias() {
		return this.useAlias;
	}

	public void setUseAlias(String useAlias) {
		this.useAlias = useAlias;
	}

	@Column(name = "ualias", length = 200)
	public String getUalias() {
		return this.ualias;
	}

	public void setUalias(String ualias) {
		this.ualias = ualias;
	}

	@Column(name = "useOtherPtitle", length = 1)
	public String getUseOtherPtitle() {
		return this.useOtherPtitle;
	}

	public void setUseOtherPtitle(String useOtherPtitle) {
		this.useOtherPtitle = useOtherPtitle;
	}

	@Column(name = "positionTitle", length = 100)
	public String getPositionTitle() {
		return this.positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	@Column(name = "remark", length = 800)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "needInvite", length = 65535)
	public String getNeedInvite() {
		return this.needInvite;
	}

	public void setNeedInvite(String needInvite) {
		this.needInvite = needInvite;
	}

	@Column(name = "needVisa", length = 65535)
	public String getNeedVisa() {
		return this.needVisa;
	}

	public void setNeedVisa(String needVisa) {
		this.needVisa = needVisa;
	}

	@Column(name = "needFaxInvite", length = 65535)
	public String getNeedFaxInvite() {
		return this.needFaxInvite;
	}

	public void setNeedFaxInvite(String needFaxInvite) {
		this.needFaxInvite = needFaxInvite;
	}

	@Column(name = "needChinaVisaService", length = 65535)
	public String getNeedChinaVisaService() {
		return this.needChinaVisaService;
	}

	public void setNeedChinaVisaService(String needChinaVisaService) {
		this.needChinaVisaService = needChinaVisaService;
	}

	@Column(name = "departureRegion", length = 65535)
	public String getDepartureRegion() {
		return this.departureRegion;
	}

	public void setDepartureRegion(String departureRegion) {
		this.departureRegion = departureRegion;
	}

	@Column(name = "inFromMacau", length = 65535)
	public String getInFromMacau() {
		return this.inFromMacau;
	}

	public void setInFromMacau(String inFromMacau) {
		this.inFromMacau = inFromMacau;
	}

	@Column(name = "departurePlace", length = 65535)
	public String getDeparturePlace() {
		return this.departurePlace;
	}

	public void setDeparturePlace(String departurePlace) {
		this.departurePlace = departurePlace;
	}

	@Column(name = "departureDate", length = 65535)
	public String getDepartureDate() {
		return this.departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	@Column(name = "departureTimePeriod", length = 65535)
	public String getDepartureTimePeriod() {
		return this.departureTimePeriod;
	}

	public void setDepartureTimePeriod(String departureTimePeriod) {
		this.departureTimePeriod = departureTimePeriod;
	}

	@Column(name = "returnPlace", length = 65535)
	public String getReturnPlace() {
		return this.returnPlace;
	}

	public void setReturnPlace(String returnPlace) {
		this.returnPlace = returnPlace;
	}

	@Column(name = "returnDate", length = 65535)
	public String getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@Column(name = "returnTimePeriod", length = 65535)
	public String getReturnTimePeriod() {
		return this.returnTimePeriod;
	}

	public void setReturnTimePeriod(String returnTimePeriod) {
		this.returnTimePeriod = returnTimePeriod;
	}

	@Column(name = "checkpoint", length = 65535)
	public String getCheckpoint() {
		return this.checkpoint;
	}

	public void setCheckpoint(String checkpoint) {
		this.checkpoint = checkpoint;
	}

	@Column(name = "normalRoomService", length = 65535)
	public String getNormalRoomService() {
		return this.normalRoomService;
	}

	public void setNormalRoomService(String normalRoomService) {
		this.normalRoomService = normalRoomService;
	}

	@Column(name = "normalRoomExtra", length = 65535)
	public String getNormalRoomExtra() {
		return this.normalRoomExtra;
	}

	public void setNormalRoomExtra(String normalRoomExtra) {
		this.normalRoomExtra = normalRoomExtra;
	}

	@Column(name = "specialDemands", length = 65535)
	public String getSpecialDemands() {
		return this.specialDemands;
	}

	public void setSpecialDemands(String specialDemands) {
		this.specialDemands = specialDemands;
	}

	@Column(name = "cultureRoute", length = 65535)
	public String getCultureRoute() {
		return this.cultureRoute;
	}

	public void setCultureRoute(String cultureRoute) {
		this.cultureRoute = cultureRoute;
	}

	@Column(name = "speechesTitle", length = 65535)
	public String getSpeechesTitle() {
		return this.speechesTitle;
	}

	public void setSpeechesTitle(String speechesTitle) {
		this.speechesTitle = speechesTitle;
	}

	@Column(name = "PPTNeed", length = 65535)
	public String getPptneed() {
		return this.pptneed;
	}

	public void setPptneed(String pptneed) {
		this.pptneed = pptneed;
	}

	@Column(name = "bringSpouse", length = 65535)
	public String getBringSpouse() {
		return this.bringSpouse;
	}

	public void setBringSpouse(String bringSpouse) {
		this.bringSpouse = bringSpouse;
	}

	@Column(name = "spouseEmail", length = 65535)
	public String getSpouseEmail() {
		return this.spouseEmail;
	}

	public void setSpouseEmail(String spouseEmail) {
		this.spouseEmail = spouseEmail;
	}

	@Column(name = "bringEntourage", length = 65535)
	public String getBringEntourage() {
		return this.bringEntourage;
	}

	public void setBringEntourage(String bringEntourage) {
		this.bringEntourage = bringEntourage;
	}

	@Column(name = "entourageNum", length = 65535)
	public String getEntourageNum() {
		return this.entourageNum;
	}

	public void setEntourageNum(String entourageNum) {
		this.entourageNum = entourageNum;
	}

	@Column(name = "entourageEmail", length = 65535)
	public String getEntourageEmail() {
		return this.entourageEmail;
	}

	public void setEntourageEmail(String entourageEmail) {
		this.entourageEmail = entourageEmail;
	}

	@Column(name = "importInfo", length = 65535)
	public String getImportInfo() {
		return this.importInfo;
	}

	public void setImportInfo(String importInfo) {
		this.importInfo = importInfo;
	}

	@Column(name = "interviewGuest", length = 65535)
	public String getInterviewGuest() {
		return this.interviewGuest;
	}

	public void setInterviewGuest(String interviewGuest) {
		this.interviewGuest = interviewGuest;
	}

	@Column(name = "sameContactPerson", length = 65535)
	public String getSameContactPerson() {
		return this.sameContactPerson;
	}

	public void setSameContactPerson(String sameContactPerson) {
		this.sameContactPerson = sameContactPerson;
	}

	@Column(name = "attendConference", length = 65535)
	public String getAttendConference() {
		return this.attendConference;
	}

	public void setAttendConference(String attendConference) {
		this.attendConference = attendConference;
	}

	@Column(name = "activities", length = 65535)
	public String getActivities() {
		return this.activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	@Column(name = "arrivedLeave", length = 65535)
	public String getArrivedLeave() {
		return this.arrivedLeave;
	}

	public void setArrivedLeave(String arrivedLeave) {
		this.arrivedLeave = arrivedLeave;
	}

	@Column(name = "roundTrip", length = 65535)
	public String getRoundTrip() {
		return this.roundTrip;
	}

	public void setRoundTrip(String roundTrip) {
		this.roundTrip = roundTrip;
	}

	@Column(name = "otherNeeds", length = 65535)
	public String getOtherNeeds() {
		return this.otherNeeds;
	}

	public void setOtherNeeds(String otherNeeds) {
		this.otherNeeds = otherNeeds;
	}

	@Column(name = "exhibitsType", length = 65535)
	public String getExhibitsType() {
		return this.exhibitsType;
	}

	public void setExhibitsType(String exhibitsType) {
		this.exhibitsType = exhibitsType;
	}

	@Column(name = "standIntro", length = 65535)
	public String getStandIntro() {
		return this.standIntro;
	}

	public void setStandIntro(String standIntro) {
		this.standIntro = standIntro;
	}

	@Column(name = "mainProduct", length = 65535)
	public String getMainProduct() {
		return this.mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	@Column(name = "quantity", length = 65535)
	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Column(name = "special", length = 65535)
	public String getSpecial() {
		return this.special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	@Column(name = "advertise", length = 65535)
	public String getAdvertise() {
		return this.advertise;
	}

	public void setAdvertise(String advertise) {
		this.advertise = advertise;
	}

	@Column(name = "sponsor", length = 65535)
	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	@Column(name = "video", length = 65535)
	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Column(name = "channels", length = 65535)
	public String getChannels() {
		return this.channels;
	}

	public void setChannels(String channels) {
		this.channels = channels;
	}

	@Column(name = "anticipation", length = 65535)
	public String getAnticipation() {
		return this.anticipation;
	}

	public void setAnticipation(String anticipation) {
		this.anticipation = anticipation;
	}

	@Column(name = "purposes", length = 65535)
	public String getPurposes() {
		return this.purposes;
	}

	public void setPurposes(String purposes) {
		this.purposes = purposes;
	}

	@Column(name = "feelings", length = 65535)
	public String getFeelings() {
		return this.feelings;
	}

	public void setFeelings(String feelings) {
		this.feelings = feelings;
	}

	@Column(name = "careAbout", length = 65535)
	public String getCareAbout() {
		return this.careAbout;
	}

	public void setCareAbout(String careAbout) {
		this.careAbout = careAbout;
	}

	@Column(name = "opitions", length = 65535)
	public String getOpitions() {
		return this.opitions;
	}

	public void setOpitions(String opitions) {
		this.opitions = opitions;
	}

	@Column(name = "suggestions", length = 65535)
	public String getSuggestions() {
		return this.suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	@Column(name = "typeCome", length = 1)
	public String getTypeCome() {
		return this.typeCome;
	}

	public void setTypeCome(String typeCome) {
		this.typeCome = typeCome;
	}

	@Column(name = "numberCome", length = 20)
	public String getNumberCome() {
		return this.numberCome;
	}

	public void setNumberCome(String numberCome) {
		this.numberCome = numberCome;
	}

	@Column(name = "startPlaceCome", length = 200)
	public String getStartPlaceCome() {
		return this.startPlaceCome;
	}

	public void setStartPlaceCome(String startPlaceCome) {
		this.startPlaceCome = startPlaceCome;
	}

	@Column(name = "endPlaceCome", length = 200)
	public String getEndPlaceCome() {
		return this.endPlaceCome;
	}

	public void setEndPlaceCome(String endPlaceCome) {
		this.endPlaceCome = endPlaceCome;
	}

	@Column(name = "startTimeCome", length = 20)
	public String getStartTimeCome() {
		return this.startTimeCome;
	}

	public void setStartTimeCome(String startTimeCome) {
		this.startTimeCome = startTimeCome;
	}

	@Column(name = "endTimeCome", length = 20)
	public String getEndTimeCome() {
		return this.endTimeCome;
	}

	public void setEndTimeCome(String endTimeCome) {
		this.endTimeCome = endTimeCome;
	}

	@Column(name = "typeGo", length = 1)
	public String getTypeGo() {
		return this.typeGo;
	}

	public void setTypeGo(String typeGo) {
		this.typeGo = typeGo;
	}

	@Column(name = "numberGo", length = 20)
	public String getNumberGo() {
		return this.numberGo;
	}

	public void setNumberGo(String numberGo) {
		this.numberGo = numberGo;
	}

	@Column(name = "startPlaceGo", length = 200)
	public String getStartPlaceGo() {
		return this.startPlaceGo;
	}

	public void setStartPlaceGo(String startPlaceGo) {
		this.startPlaceGo = startPlaceGo;
	}

	@Column(name = "endPlaceGo", length = 200)
	public String getEndPlaceGo() {
		return this.endPlaceGo;
	}

	public void setEndPlaceGo(String endPlaceGo) {
		this.endPlaceGo = endPlaceGo;
	}

	@Column(name = "startTimeGo", length = 20)
	public String getStartTimeGo() {
		return this.startTimeGo;
	}

	public void setStartTimeGo(String startTimeGo) {
		this.startTimeGo = startTimeGo;
	}

	@Column(name = "endTimeGo", length = 20)
	public String getEndTimeGo() {
		return this.endTimeGo;
	}

	public void setEndTimeGo(String endTimeGo) {
		this.endTimeGo = endTimeGo;
	}

	@Column(name = "restaurant", length = 100)
	public String getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	@Column(name = "roomType", length = 1)
	public String getRoomType() {
		return this.roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Column(name = "roomNum", length = 50)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "checkInDate", length = 20)
	public String getCheckInDate() {
		return this.checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	@Column(name = "checkOutDate", length = 20)
	public String getCheckOutDate() {
		return this.checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConfVInfo))
			return false;
		ConfVInfo castOther = (ConfVInfo) other;

		return ((this.getMeetingId() == castOther.getMeetingId()) || (this.getMeetingId() != null && castOther.getMeetingId() != null && this.getMeetingId().equals(castOther.getMeetingId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null && castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())))
				&& ((this.getUserType() == castOther.getUserType()) || (this.getUserType() != null && castOther.getUserType() != null && this.getUserType().equals(castOther.getUserType())))
				&& ((this.getCname() == castOther.getCname()) || (this.getCname() != null && castOther.getCname() != null && this.getCname().equals(castOther.getCname())))
				&& ((this.getEname() == castOther.getEname()) || (this.getEname() != null && castOther.getEname() != null && this.getEname().equals(castOther.getEname())))
				&& ((this.getSex() == castOther.getSex()) || (this.getSex() != null && castOther.getSex() != null && this.getSex().equals(castOther.getSex())))
				&& ((this.getBirth() == castOther.getBirth()) || (this.getBirth() != null && castOther.getBirth() != null && this.getBirth().equals(castOther.getBirth())))
				&& ((this.getNation() == castOther.getNation()) || (this.getNation() != null && castOther.getNation() != null && this.getNation().equals(castOther.getNation())))
				&& ((this.getReligion() == castOther.getReligion()) || (this.getReligion() != null && castOther.getReligion() != null && this.getReligion().equals(castOther.getReligion())))
				&& ((this.getDietTaboo() == castOther.getDietTaboo()) || (this.getDietTaboo() != null && castOther.getDietTaboo() != null && this.getDietTaboo().equals(castOther.getDietTaboo())))
				&& ((this.getMedlHis() == castOther.getMedlHis()) || (this.getMedlHis() != null && castOther.getMedlHis() != null && this.getMedlHis().equals(castOther.getMedlHis())))
				&& ((this.getFoodAllergies() == castOther.getFoodAllergies()) || (this.getFoodAllergies() != null && castOther.getFoodAllergies() != null && this.getFoodAllergies().equals(castOther.getFoodAllergies())))
				&& ((this.getAddress() == castOther.getAddress()) || (this.getAddress() != null && castOther.getAddress() != null && this.getAddress().equals(castOther.getAddress())))
				&& ((this.getPostcode() == castOther.getPostcode()) || (this.getPostcode() != null && castOther.getPostcode() != null && this.getPostcode().equals(castOther.getPostcode())))
				&& ((this.getTele() == castOther.getTele()) || (this.getTele() != null && castOther.getTele() != null && this.getTele().equals(castOther.getTele())))
				&& ((this.getMobile() == castOther.getMobile()) || (this.getMobile() != null && castOther.getMobile() != null && this.getMobile().equals(castOther.getMobile())))
				&& ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null && castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())))
				&& ((this.getFax() == castOther.getFax()) || (this.getFax() != null && castOther.getFax() != null && this.getFax().equals(castOther.getFax())))
				&& ((this.getContactPerson() == castOther.getContactPerson()) || (this.getContactPerson() != null && castOther.getContactPerson() != null && this.getContactPerson().equals(castOther.getContactPerson())))
				&& ((this.getSpouseInfo() == castOther.getSpouseInfo()) || (this.getSpouseInfo() != null && castOther.getSpouseInfo() != null && this.getSpouseInfo().equals(castOther.getSpouseInfo())))
				&& ((this.getEntourageInfo() == castOther.getEntourageInfo()) || (this.getEntourageInfo() != null && castOther.getEntourageInfo() != null && this.getEntourageInfo().equals(castOther.getEntourageInfo())))
				&& ((this.getCertType() == castOther.getCertType()) || (this.getCertType() != null && castOther.getCertType() != null && this.getCertType().equals(castOther.getCertType())))
				&& ((this.getCertValue() == castOther.getCertValue()) || (this.getCertValue() != null && castOther.getCertValue() != null && this.getCertValue().equals(castOther.getCertValue())))
				&& ((this.getEntryType() == castOther.getEntryType()) || (this.getEntryType() != null && castOther.getEntryType() != null && this.getEntryType().equals(castOther.getEntryType())))
				&& ((this.getEntryPlace() == castOther.getEntryPlace()) || (this.getEntryPlace() != null && castOther.getEntryPlace() != null && this.getEntryPlace().equals(castOther.getEntryPlace())))
				&& ((this.getEntryDate() == castOther.getEntryDate()) || (this.getEntryDate() != null && castOther.getEntryDate() != null && this.getEntryDate().equals(castOther.getEntryDate())))
				&& ((this.getEntryValidity() == castOther.getEntryValidity()) || (this.getEntryValidity() != null && castOther.getEntryValidity() != null && this.getEntryValidity().equals(castOther.getEntryValidity())))
				&& ((this.getEntryNum() == castOther.getEntryNum()) || (this.getEntryNum() != null && castOther.getEntryNum() != null && this.getEntryNum().equals(castOther.getEntryNum())))
				&& ((this.getEntryEndmtDate() == castOther.getEntryEndmtDate()) || (this.getEntryEndmtDate() != null && castOther.getEntryEndmtDate() != null && this.getEntryEndmtDate().equals(castOther.getEntryEndmtDate())))
				&& ((this.getEntryEndmtValidity() == castOther.getEntryEndmtValidity()) || (this.getEntryEndmtValidity() != null && castOther.getEntryEndmtValidity() != null && this.getEntryEndmtValidity().equals(castOther.getEntryEndmtValidity())))
				&& ((this.getCompany() == castOther.getCompany()) || (this.getCompany() != null && castOther.getCompany() != null && this.getCompany().equals(castOther.getCompany())))
				&& ((this.getCompnyType() == castOther.getCompnyType()) || (this.getCompnyType() != null && castOther.getCompnyType() != null && this.getCompnyType().equals(castOther.getCompnyType())))
				&& ((this.getIndustry() == castOther.getIndustry()) || (this.getIndustry() != null && castOther.getIndustry() != null && this.getIndustry().equals(castOther.getIndustry())))
				&& ((this.getWorkContent() == castOther.getWorkContent()) || (this.getWorkContent() != null && castOther.getWorkContent() != null && this.getWorkContent().equals(castOther.getWorkContent())))
				&& ((this.getSuperviserName() == castOther.getSuperviserName()) || (this.getSuperviserName() != null && castOther.getSuperviserName() != null && this.getSuperviserName().equals(castOther.getSuperviserName())))
				&& ((this.getSuperviserPhone() == castOther.getSuperviserPhone()) || (this.getSuperviserPhone() != null && castOther.getSuperviserPhone() != null && this.getSuperviserPhone().equals(castOther.getSuperviserPhone())))
				&& ((this.getPosition() == castOther.getPosition()) || (this.getPosition() != null && castOther.getPosition() != null && this.getPosition().equals(castOther.getPosition())))
				&& ((this.getSelfIntro() == castOther.getSelfIntro()) || (this.getSelfIntro() != null && castOther.getSelfIntro() != null && this.getSelfIntro().equals(castOther.getSelfIntro())))
				&& ((this.getSelfIntroEn() == castOther.getSelfIntroEn()) || (this.getSelfIntroEn() != null && castOther.getSelfIntroEn() != null && this.getSelfIntroEn().equals(castOther.getSelfIntroEn())))
				&& ((this.getUseRealname() == castOther.getUseRealname()) || (this.getUseRealname() != null && castOther.getUseRealname() != null && this.getUseRealname().equals(castOther.getUseRealname())))
				&& ((this.getUseAlias() == castOther.getUseAlias()) || (this.getUseAlias() != null && castOther.getUseAlias() != null && this.getUseAlias().equals(castOther.getUseAlias())))
				&& ((this.getUalias() == castOther.getUalias()) || (this.getUalias() != null && castOther.getUalias() != null && this.getUalias().equals(castOther.getUalias())))
				&& ((this.getUseOtherPtitle() == castOther.getUseOtherPtitle()) || (this.getUseOtherPtitle() != null && castOther.getUseOtherPtitle() != null && this.getUseOtherPtitle().equals(castOther.getUseOtherPtitle())))
				&& ((this.getPositionTitle() == castOther.getPositionTitle()) || (this.getPositionTitle() != null && castOther.getPositionTitle() != null && this.getPositionTitle().equals(castOther.getPositionTitle())))
				&& ((this.getRemark() == castOther.getRemark()) || (this.getRemark() != null && castOther.getRemark() != null && this.getRemark().equals(castOther.getRemark())))
				&& ((this.getNeedInvite() == castOther.getNeedInvite()) || (this.getNeedInvite() != null && castOther.getNeedInvite() != null && this.getNeedInvite().equals(castOther.getNeedInvite())))
				&& ((this.getNeedVisa() == castOther.getNeedVisa()) || (this.getNeedVisa() != null && castOther.getNeedVisa() != null && this.getNeedVisa().equals(castOther.getNeedVisa())))
				&& ((this.getNeedFaxInvite() == castOther.getNeedFaxInvite()) || (this.getNeedFaxInvite() != null && castOther.getNeedFaxInvite() != null && this.getNeedFaxInvite().equals(castOther.getNeedFaxInvite())))
				&& ((this.getNeedChinaVisaService() == castOther.getNeedChinaVisaService()) || (this.getNeedChinaVisaService() != null && castOther.getNeedChinaVisaService() != null && this.getNeedChinaVisaService().equals(castOther.getNeedChinaVisaService())))
				&& ((this.getDepartureRegion() == castOther.getDepartureRegion()) || (this.getDepartureRegion() != null && castOther.getDepartureRegion() != null && this.getDepartureRegion().equals(castOther.getDepartureRegion())))
				&& ((this.getInFromMacau() == castOther.getInFromMacau()) || (this.getInFromMacau() != null && castOther.getInFromMacau() != null && this.getInFromMacau().equals(castOther.getInFromMacau())))
				&& ((this.getDeparturePlace() == castOther.getDeparturePlace()) || (this.getDeparturePlace() != null && castOther.getDeparturePlace() != null && this.getDeparturePlace().equals(castOther.getDeparturePlace())))
				&& ((this.getDepartureDate() == castOther.getDepartureDate()) || (this.getDepartureDate() != null && castOther.getDepartureDate() != null && this.getDepartureDate().equals(castOther.getDepartureDate())))
				&& ((this.getDepartureTimePeriod() == castOther.getDepartureTimePeriod()) || (this.getDepartureTimePeriod() != null && castOther.getDepartureTimePeriod() != null && this.getDepartureTimePeriod().equals(castOther.getDepartureTimePeriod())))
				&& ((this.getReturnPlace() == castOther.getReturnPlace()) || (this.getReturnPlace() != null && castOther.getReturnPlace() != null && this.getReturnPlace().equals(castOther.getReturnPlace())))
				&& ((this.getReturnDate() == castOther.getReturnDate()) || (this.getReturnDate() != null && castOther.getReturnDate() != null && this.getReturnDate().equals(castOther.getReturnDate())))
				&& ((this.getReturnTimePeriod() == castOther.getReturnTimePeriod()) || (this.getReturnTimePeriod() != null && castOther.getReturnTimePeriod() != null && this.getReturnTimePeriod().equals(castOther.getReturnTimePeriod())))
				&& ((this.getCheckpoint() == castOther.getCheckpoint()) || (this.getCheckpoint() != null && castOther.getCheckpoint() != null && this.getCheckpoint().equals(castOther.getCheckpoint())))
				&& ((this.getNormalRoomService() == castOther.getNormalRoomService()) || (this.getNormalRoomService() != null && castOther.getNormalRoomService() != null && this.getNormalRoomService().equals(castOther.getNormalRoomService())))
				&& ((this.getNormalRoomExtra() == castOther.getNormalRoomExtra()) || (this.getNormalRoomExtra() != null && castOther.getNormalRoomExtra() != null && this.getNormalRoomExtra().equals(castOther.getNormalRoomExtra())))
				&& ((this.getSpecialDemands() == castOther.getSpecialDemands()) || (this.getSpecialDemands() != null && castOther.getSpecialDemands() != null && this.getSpecialDemands().equals(castOther.getSpecialDemands())))
				&& ((this.getCultureRoute() == castOther.getCultureRoute()) || (this.getCultureRoute() != null && castOther.getCultureRoute() != null && this.getCultureRoute().equals(castOther.getCultureRoute())))
				&& ((this.getSpeechesTitle() == castOther.getSpeechesTitle()) || (this.getSpeechesTitle() != null && castOther.getSpeechesTitle() != null && this.getSpeechesTitle().equals(castOther.getSpeechesTitle())))
				&& ((this.getPptneed() == castOther.getPptneed()) || (this.getPptneed() != null && castOther.getPptneed() != null && this.getPptneed().equals(castOther.getPptneed())))
				&& ((this.getBringSpouse() == castOther.getBringSpouse()) || (this.getBringSpouse() != null && castOther.getBringSpouse() != null && this.getBringSpouse().equals(castOther.getBringSpouse())))
				&& ((this.getSpouseEmail() == castOther.getSpouseEmail()) || (this.getSpouseEmail() != null && castOther.getSpouseEmail() != null && this.getSpouseEmail().equals(castOther.getSpouseEmail())))
				&& ((this.getBringEntourage() == castOther.getBringEntourage()) || (this.getBringEntourage() != null && castOther.getBringEntourage() != null && this.getBringEntourage().equals(castOther.getBringEntourage())))
				&& ((this.getEntourageNum() == castOther.getEntourageNum()) || (this.getEntourageNum() != null && castOther.getEntourageNum() != null && this.getEntourageNum().equals(castOther.getEntourageNum())))
				&& ((this.getEntourageEmail() == castOther.getEntourageEmail()) || (this.getEntourageEmail() != null && castOther.getEntourageEmail() != null && this.getEntourageEmail().equals(castOther.getEntourageEmail())))
				&& ((this.getImportInfo() == castOther.getImportInfo()) || (this.getImportInfo() != null && castOther.getImportInfo() != null && this.getImportInfo().equals(castOther.getImportInfo())))
				&& ((this.getInterviewGuest() == castOther.getInterviewGuest()) || (this.getInterviewGuest() != null && castOther.getInterviewGuest() != null && this.getInterviewGuest().equals(castOther.getInterviewGuest())))
				&& ((this.getSameContactPerson() == castOther.getSameContactPerson()) || (this.getSameContactPerson() != null && castOther.getSameContactPerson() != null && this.getSameContactPerson().equals(castOther.getSameContactPerson())))
				&& ((this.getAttendConference() == castOther.getAttendConference()) || (this.getAttendConference() != null && castOther.getAttendConference() != null && this.getAttendConference().equals(castOther.getAttendConference())))
				&& ((this.getActivities() == castOther.getActivities()) || (this.getActivities() != null && castOther.getActivities() != null && this.getActivities().equals(castOther.getActivities())))
				&& ((this.getArrivedLeave() == castOther.getArrivedLeave()) || (this.getArrivedLeave() != null && castOther.getArrivedLeave() != null && this.getArrivedLeave().equals(castOther.getArrivedLeave())))
				&& ((this.getRoundTrip() == castOther.getRoundTrip()) || (this.getRoundTrip() != null && castOther.getRoundTrip() != null && this.getRoundTrip().equals(castOther.getRoundTrip())))
				&& ((this.getOtherNeeds() == castOther.getOtherNeeds()) || (this.getOtherNeeds() != null && castOther.getOtherNeeds() != null && this.getOtherNeeds().equals(castOther.getOtherNeeds())))
				&& ((this.getExhibitsType() == castOther.getExhibitsType()) || (this.getExhibitsType() != null && castOther.getExhibitsType() != null && this.getExhibitsType().equals(castOther.getExhibitsType())))
				&& ((this.getStandIntro() == castOther.getStandIntro()) || (this.getStandIntro() != null && castOther.getStandIntro() != null && this.getStandIntro().equals(castOther.getStandIntro())))
				&& ((this.getMainProduct() == castOther.getMainProduct()) || (this.getMainProduct() != null && castOther.getMainProduct() != null && this.getMainProduct().equals(castOther.getMainProduct())))
				&& ((this.getQuantity() == castOther.getQuantity()) || (this.getQuantity() != null && castOther.getQuantity() != null && this.getQuantity().equals(castOther.getQuantity())))
				&& ((this.getSpecial() == castOther.getSpecial()) || (this.getSpecial() != null && castOther.getSpecial() != null && this.getSpecial().equals(castOther.getSpecial())))
				&& ((this.getAdvertise() == castOther.getAdvertise()) || (this.getAdvertise() != null && castOther.getAdvertise() != null && this.getAdvertise().equals(castOther.getAdvertise())))
				&& ((this.getSponsor() == castOther.getSponsor()) || (this.getSponsor() != null && castOther.getSponsor() != null && this.getSponsor().equals(castOther.getSponsor())))
				&& ((this.getVideo() == castOther.getVideo()) || (this.getVideo() != null && castOther.getVideo() != null && this.getVideo().equals(castOther.getVideo())))
				&& ((this.getChannels() == castOther.getChannels()) || (this.getChannels() != null && castOther.getChannels() != null && this.getChannels().equals(castOther.getChannels())))
				&& ((this.getAnticipation() == castOther.getAnticipation()) || (this.getAnticipation() != null && castOther.getAnticipation() != null && this.getAnticipation().equals(castOther.getAnticipation())))
				&& ((this.getPurposes() == castOther.getPurposes()) || (this.getPurposes() != null && castOther.getPurposes() != null && this.getPurposes().equals(castOther.getPurposes())))
				&& ((this.getFeelings() == castOther.getFeelings()) || (this.getFeelings() != null && castOther.getFeelings() != null && this.getFeelings().equals(castOther.getFeelings())))
				&& ((this.getCareAbout() == castOther.getCareAbout()) || (this.getCareAbout() != null && castOther.getCareAbout() != null && this.getCareAbout().equals(castOther.getCareAbout())))
				&& ((this.getOpitions() == castOther.getOpitions()) || (this.getOpitions() != null && castOther.getOpitions() != null && this.getOpitions().equals(castOther.getOpitions())))
				&& ((this.getSuggestions() == castOther.getSuggestions()) || (this.getSuggestions() != null && castOther.getSuggestions() != null && this.getSuggestions().equals(castOther.getSuggestions())))
				&& ((this.getTypeCome() == castOther.getTypeCome()) || (this.getTypeCome() != null && castOther.getTypeCome() != null && this.getTypeCome().equals(castOther.getTypeCome())))
				&& ((this.getNumberCome() == castOther.getNumberCome()) || (this.getNumberCome() != null && castOther.getNumberCome() != null && this.getNumberCome().equals(castOther.getNumberCome())))
				&& ((this.getStartPlaceCome() == castOther.getStartPlaceCome()) || (this.getStartPlaceCome() != null && castOther.getStartPlaceCome() != null && this.getStartPlaceCome().equals(castOther.getStartPlaceCome())))
				&& ((this.getEndPlaceCome() == castOther.getEndPlaceCome()) || (this.getEndPlaceCome() != null && castOther.getEndPlaceCome() != null && this.getEndPlaceCome().equals(castOther.getEndPlaceCome())))
				&& ((this.getStartTimeCome() == castOther.getStartTimeCome()) || (this.getStartTimeCome() != null && castOther.getStartTimeCome() != null && this.getStartTimeCome().equals(castOther.getStartTimeCome())))
				&& ((this.getEndTimeCome() == castOther.getEndTimeCome()) || (this.getEndTimeCome() != null && castOther.getEndTimeCome() != null && this.getEndTimeCome().equals(castOther.getEndTimeCome())))
				&& ((this.getTypeGo() == castOther.getTypeGo()) || (this.getTypeGo() != null && castOther.getTypeGo() != null && this.getTypeGo().equals(castOther.getTypeGo())))
				&& ((this.getNumberGo() == castOther.getNumberGo()) || (this.getNumberGo() != null && castOther.getNumberGo() != null && this.getNumberGo().equals(castOther.getNumberGo())))
				&& ((this.getStartPlaceGo() == castOther.getStartPlaceGo()) || (this.getStartPlaceGo() != null && castOther.getStartPlaceGo() != null && this.getStartPlaceGo().equals(castOther.getStartPlaceGo())))
				&& ((this.getEndPlaceGo() == castOther.getEndPlaceGo()) || (this.getEndPlaceGo() != null && castOther.getEndPlaceGo() != null && this.getEndPlaceGo().equals(castOther.getEndPlaceGo())))
				&& ((this.getStartTimeGo() == castOther.getStartTimeGo()) || (this.getStartTimeGo() != null && castOther.getStartTimeGo() != null && this.getStartTimeGo().equals(castOther.getStartTimeGo())))
				&& ((this.getEndTimeGo() == castOther.getEndTimeGo()) || (this.getEndTimeGo() != null && castOther.getEndTimeGo() != null && this.getEndTimeGo().equals(castOther.getEndTimeGo())))
				&& ((this.getRestaurant() == castOther.getRestaurant()) || (this.getRestaurant() != null && castOther.getRestaurant() != null && this.getRestaurant().equals(castOther.getRestaurant())))
				&& ((this.getRoomType() == castOther.getRoomType()) || (this.getRoomType() != null && castOther.getRoomType() != null && this.getRoomType().equals(castOther.getRoomType())))
				&& ((this.getRoomNum() == castOther.getRoomNum()) || (this.getRoomNum() != null && castOther.getRoomNum() != null && this.getRoomNum().equals(castOther.getRoomNum())))
				&& ((this.getCheckInDate() == castOther.getCheckInDate()) || (this.getCheckInDate() != null && castOther.getCheckInDate() != null && this.getCheckInDate().equals(castOther.getCheckInDate())))
				&& ((this.getCheckOutDate() == castOther.getCheckOutDate()) || (this.getCheckOutDate() != null && castOther.getCheckOutDate() != null && this.getCheckOutDate().equals(castOther.getCheckOutDate())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMeetingId() == null ? 0 : this.getMeetingId().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37 * result + (getUserType() == null ? 0 : this.getUserType().hashCode());
		result = 37 * result + (getCname() == null ? 0 : this.getCname().hashCode());
		result = 37 * result + (getEname() == null ? 0 : this.getEname().hashCode());
		result = 37 * result + (getSex() == null ? 0 : this.getSex().hashCode());
		result = 37 * result + (getBirth() == null ? 0 : this.getBirth().hashCode());
		result = 37 * result + (getNation() == null ? 0 : this.getNation().hashCode());
		result = 37 * result + (getReligion() == null ? 0 : this.getReligion().hashCode());
		result = 37 * result + (getDietTaboo() == null ? 0 : this.getDietTaboo().hashCode());
		result = 37 * result + (getMedlHis() == null ? 0 : this.getMedlHis().hashCode());
		result = 37 * result + (getFoodAllergies() == null ? 0 : this.getFoodAllergies().hashCode());
		result = 37 * result + (getAddress() == null ? 0 : this.getAddress().hashCode());
		result = 37 * result + (getPostcode() == null ? 0 : this.getPostcode().hashCode());
		result = 37 * result + (getTele() == null ? 0 : this.getTele().hashCode());
		result = 37 * result + (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
		result = 37 * result + (getFax() == null ? 0 : this.getFax().hashCode());
		result = 37 * result + (getContactPerson() == null ? 0 : this.getContactPerson().hashCode());
		result = 37 * result + (getSpouseInfo() == null ? 0 : this.getSpouseInfo().hashCode());
		result = 37 * result + (getEntourageInfo() == null ? 0 : this.getEntourageInfo().hashCode());
		result = 37 * result + (getCertType() == null ? 0 : this.getCertType().hashCode());
		result = 37 * result + (getCertValue() == null ? 0 : this.getCertValue().hashCode());
		result = 37 * result + (getEntryType() == null ? 0 : this.getEntryType().hashCode());
		result = 37 * result + (getEntryPlace() == null ? 0 : this.getEntryPlace().hashCode());
		result = 37 * result + (getEntryDate() == null ? 0 : this.getEntryDate().hashCode());
		result = 37 * result + (getEntryValidity() == null ? 0 : this.getEntryValidity().hashCode());
		result = 37 * result + (getEntryNum() == null ? 0 : this.getEntryNum().hashCode());
		result = 37 * result + (getEntryEndmtDate() == null ? 0 : this.getEntryEndmtDate().hashCode());
		result = 37 * result + (getEntryEndmtValidity() == null ? 0 : this.getEntryEndmtValidity().hashCode());
		result = 37 * result + (getCompany() == null ? 0 : this.getCompany().hashCode());
		result = 37 * result + (getCompnyType() == null ? 0 : this.getCompnyType().hashCode());
		result = 37 * result + (getIndustry() == null ? 0 : this.getIndustry().hashCode());
		result = 37 * result + (getWorkContent() == null ? 0 : this.getWorkContent().hashCode());
		result = 37 * result + (getSuperviserName() == null ? 0 : this.getSuperviserName().hashCode());
		result = 37 * result + (getSuperviserPhone() == null ? 0 : this.getSuperviserPhone().hashCode());
		result = 37 * result + (getPosition() == null ? 0 : this.getPosition().hashCode());
		result = 37 * result + (getSelfIntro() == null ? 0 : this.getSelfIntro().hashCode());
		result = 37 * result + (getSelfIntroEn() == null ? 0 : this.getSelfIntroEn().hashCode());
		result = 37 * result + (getUseRealname() == null ? 0 : this.getUseRealname().hashCode());
		result = 37 * result + (getUseAlias() == null ? 0 : this.getUseAlias().hashCode());
		result = 37 * result + (getUalias() == null ? 0 : this.getUalias().hashCode());
		result = 37 * result + (getUseOtherPtitle() == null ? 0 : this.getUseOtherPtitle().hashCode());
		result = 37 * result + (getPositionTitle() == null ? 0 : this.getPositionTitle().hashCode());
		result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result + (getNeedInvite() == null ? 0 : this.getNeedInvite().hashCode());
		result = 37 * result + (getNeedVisa() == null ? 0 : this.getNeedVisa().hashCode());
		result = 37 * result + (getNeedFaxInvite() == null ? 0 : this.getNeedFaxInvite().hashCode());
		result = 37 * result + (getNeedChinaVisaService() == null ? 0 : this.getNeedChinaVisaService().hashCode());
		result = 37 * result + (getDepartureRegion() == null ? 0 : this.getDepartureRegion().hashCode());
		result = 37 * result + (getInFromMacau() == null ? 0 : this.getInFromMacau().hashCode());
		result = 37 * result + (getDeparturePlace() == null ? 0 : this.getDeparturePlace().hashCode());
		result = 37 * result + (getDepartureDate() == null ? 0 : this.getDepartureDate().hashCode());
		result = 37 * result + (getDepartureTimePeriod() == null ? 0 : this.getDepartureTimePeriod().hashCode());
		result = 37 * result + (getReturnPlace() == null ? 0 : this.getReturnPlace().hashCode());
		result = 37 * result + (getReturnDate() == null ? 0 : this.getReturnDate().hashCode());
		result = 37 * result + (getReturnTimePeriod() == null ? 0 : this.getReturnTimePeriod().hashCode());
		result = 37 * result + (getCheckpoint() == null ? 0 : this.getCheckpoint().hashCode());
		result = 37 * result + (getNormalRoomService() == null ? 0 : this.getNormalRoomService().hashCode());
		result = 37 * result + (getNormalRoomExtra() == null ? 0 : this.getNormalRoomExtra().hashCode());
		result = 37 * result + (getSpecialDemands() == null ? 0 : this.getSpecialDemands().hashCode());
		result = 37 * result + (getCultureRoute() == null ? 0 : this.getCultureRoute().hashCode());
		result = 37 * result + (getSpeechesTitle() == null ? 0 : this.getSpeechesTitle().hashCode());
		result = 37 * result + (getPptneed() == null ? 0 : this.getPptneed().hashCode());
		result = 37 * result + (getBringSpouse() == null ? 0 : this.getBringSpouse().hashCode());
		result = 37 * result + (getSpouseEmail() == null ? 0 : this.getSpouseEmail().hashCode());
		result = 37 * result + (getBringEntourage() == null ? 0 : this.getBringEntourage().hashCode());
		result = 37 * result + (getEntourageNum() == null ? 0 : this.getEntourageNum().hashCode());
		result = 37 * result + (getEntourageEmail() == null ? 0 : this.getEntourageEmail().hashCode());
		result = 37 * result + (getImportInfo() == null ? 0 : this.getImportInfo().hashCode());
		result = 37 * result + (getInterviewGuest() == null ? 0 : this.getInterviewGuest().hashCode());
		result = 37 * result + (getSameContactPerson() == null ? 0 : this.getSameContactPerson().hashCode());
		result = 37 * result + (getAttendConference() == null ? 0 : this.getAttendConference().hashCode());
		result = 37 * result + (getActivities() == null ? 0 : this.getActivities().hashCode());
		result = 37 * result + (getArrivedLeave() == null ? 0 : this.getArrivedLeave().hashCode());
		result = 37 * result + (getRoundTrip() == null ? 0 : this.getRoundTrip().hashCode());
		result = 37 * result + (getOtherNeeds() == null ? 0 : this.getOtherNeeds().hashCode());
		result = 37 * result + (getExhibitsType() == null ? 0 : this.getExhibitsType().hashCode());
		result = 37 * result + (getStandIntro() == null ? 0 : this.getStandIntro().hashCode());
		result = 37 * result + (getMainProduct() == null ? 0 : this.getMainProduct().hashCode());
		result = 37 * result + (getQuantity() == null ? 0 : this.getQuantity().hashCode());
		result = 37 * result + (getSpecial() == null ? 0 : this.getSpecial().hashCode());
		result = 37 * result + (getAdvertise() == null ? 0 : this.getAdvertise().hashCode());
		result = 37 * result + (getSponsor() == null ? 0 : this.getSponsor().hashCode());
		result = 37 * result + (getVideo() == null ? 0 : this.getVideo().hashCode());
		result = 37 * result + (getChannels() == null ? 0 : this.getChannels().hashCode());
		result = 37 * result + (getAnticipation() == null ? 0 : this.getAnticipation().hashCode());
		result = 37 * result + (getPurposes() == null ? 0 : this.getPurposes().hashCode());
		result = 37 * result + (getFeelings() == null ? 0 : this.getFeelings().hashCode());
		result = 37 * result + (getCareAbout() == null ? 0 : this.getCareAbout().hashCode());
		result = 37 * result + (getOpitions() == null ? 0 : this.getOpitions().hashCode());
		result = 37 * result + (getSuggestions() == null ? 0 : this.getSuggestions().hashCode());
		result = 37 * result + (getTypeCome() == null ? 0 : this.getTypeCome().hashCode());
		result = 37 * result + (getNumberCome() == null ? 0 : this.getNumberCome().hashCode());
		result = 37 * result + (getStartPlaceCome() == null ? 0 : this.getStartPlaceCome().hashCode());
		result = 37 * result + (getEndPlaceCome() == null ? 0 : this.getEndPlaceCome().hashCode());
		result = 37 * result + (getStartTimeCome() == null ? 0 : this.getStartTimeCome().hashCode());
		result = 37 * result + (getEndTimeCome() == null ? 0 : this.getEndTimeCome().hashCode());
		result = 37 * result + (getTypeGo() == null ? 0 : this.getTypeGo().hashCode());
		result = 37 * result + (getNumberGo() == null ? 0 : this.getNumberGo().hashCode());
		result = 37 * result + (getStartPlaceGo() == null ? 0 : this.getStartPlaceGo().hashCode());
		result = 37 * result + (getEndPlaceGo() == null ? 0 : this.getEndPlaceGo().hashCode());
		result = 37 * result + (getStartTimeGo() == null ? 0 : this.getStartTimeGo().hashCode());
		result = 37 * result + (getEndTimeGo() == null ? 0 : this.getEndTimeGo().hashCode());
		result = 37 * result + (getRestaurant() == null ? 0 : this.getRestaurant().hashCode());
		result = 37 * result + (getRoomType() == null ? 0 : this.getRoomType().hashCode());
		result = 37 * result + (getRoomNum() == null ? 0 : this.getRoomNum().hashCode());
		result = 37 * result + (getCheckInDate() == null ? 0 : this.getCheckInDate().hashCode());
		result = 37 * result + (getCheckOutDate() == null ? 0 : this.getCheckOutDate().hashCode());
		return result;
	}

}