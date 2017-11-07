package com.centling.conference.meetinguser.entity;

import java.util.List;
import java.util.Map;

public class ConfGuest {

    //个人信息
    private String userId;
    private String cname;
    private String lastName;
    private String firstName;
    private String ename;
    private String sex;
    private String birth;
    private String nation;
    private String certType;
    private String certValue;
    private String certExpiryDate;
    private String certPic1;
    private String certPic2;
    private String email;
    private String tele;
    private String mobile;
    private String fax;
    private String address;
    private String postcode;
    private String photo;
    private String medlHis;
    private String religion;
    private String selfIntro;
    private String industry;
    private String companyType;
    private String company;
    private String position;
    private String visaNeed;
    private String visaCity;
    private String contactPerson;
    private String vegetarian;
    private String password;
    private String mailChecked;
    private String dietTaboo;
    private String etiquette;
    private String officialLang;
    //20140902新增字段
    private String entryType;
    private String entryPlace;
    private String entryDate;
    private String entryValidity;
    private String entryNum;
    private String entryEndmtDate;
    private String entryEndmtValidity;
    private String entryPic1;
    private String entryPic2;
    private String selfIntroEn;
    private String foodAllergies;
    private String useRealname;
    private String useAlias;
    private String ualias;
    private String useOtherPtitle;
    private String positionTitle;
    private String remark;
    private String workContent;
    private String superviserName;
    private String superviserPhone;
    private String updateDate;
    private String cardType;

    //会议信息
    private String meetingId;
    private String meetingName;
    private String meetingNameEn;
    private String startTime;
    private String endTime;
    private String city;
    private String locationId;
    private String bannerPic;
    private String url;
    private String meetingIntro;
    private String meetingIntroEn;
    private String meetingStatus;

    //用户会议关联信息
    private String meetingUserId;
    private String userType;
    private String approveState;
    private String remindFlag;

    //检索条件
    private String sname;
    private String semail;
    private String suserType;
    private String sapproveState;

    //日程信息
    private List<Map<String, String>> schList;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCname() {
        return cname;
    }
    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEname() {
        return ename;
    }
    public void setEname(String ename) {
        this.ename = ename;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public String getCertType() {
        return certType;
    }
    public void setCertType(String certType) {
        this.certType = certType;
    }
    public String getCertValue() {
        return certValue;
    }
    public void setCertValue(String certValue) {
        this.certValue = certValue;
    }
    public String getCertExpiryDate() {
        return certExpiryDate;
    }
    public void setCertExpiryDate(String certExpiryDate) {
        this.certExpiryDate = certExpiryDate;
    }
    public String getCertPic1() {
        return certPic1;
    }
    public void setCertPic1(String certPic1) {
        this.certPic1 = certPic1;
    }
    public String getCertPic2() {
        return certPic2;
    }
    public void setCertPic2(String certPic2) {
        this.certPic2 = certPic2;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTele() {
        return tele;
    }
    public void setTele(String tele) {
        this.tele = tele;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getMedlHis() {
        return medlHis;
    }
    public void setMedlHis(String medlHis) {
        this.medlHis = medlHis;
    }
    public String getReligion() {
        return religion;
    }
    public void setReligion(String religion) {
        this.religion = religion;
    }
    public String getSelfIntro() {
        return selfIntro;
    }
    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getVisaNeed() {
        return visaNeed;
    }
    public void setVisaNeed(String visaNeed) {
        this.visaNeed = visaNeed;
    }
    public String getVisaCity() {
        return visaCity;
    }
    public void setVisaCity(String visaCity) {
        this.visaCity = visaCity;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getVegetarian() {
        return vegetarian;
    }
    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }
    public String getApproveState() {
        return approveState;
    }
    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMailChecked() {
        return mailChecked;
    }
    public void setMailChecked(String mailChecked) {
        this.mailChecked = mailChecked;
    }

    public String getDietTaboo() {

        return dietTaboo;
    }

    public void setDietTaboo(String dietTaboo) {

        this.dietTaboo = dietTaboo;
    }

    public String getEtiquette() {

        return etiquette;
    }

    public void setEtiquette(String etiquette) {

        this.etiquette = etiquette;
    }

    public String getOfficialLang() {

        return officialLang;
    }

    public void setOfficialLang(String officialLang) {

        this.officialLang = officialLang;
    }
    public String getMeetingId() {
        return meetingId;
    }
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
    public String getMeetingName() {
        return meetingName;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getLocationId() {
        return locationId;
    }
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    public String getBannerPic() {
        return bannerPic;
    }
    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMeetingIntro() {
        return meetingIntro;
    }
    public void setMeetingIntro(String meetingIntro) {
        this.meetingIntro = meetingIntro;
    }
    public String getMeetingStatus() {
        return meetingStatus;
    }
    public void setMeetingStatus(String meetingStatus) {
        this.meetingStatus = meetingStatus;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getMeetingUserId() {
        return meetingUserId;
    }
    public void setMeetingUserId(String meetingUserId) {
        this.meetingUserId = meetingUserId;
    }

    public List<Map<String, String>> getSchList() {

        return schList;
    }

    public void setSchList(List<Map<String, String>> schList) {

        this.schList = schList;
    }

    public String getMeetingNameEn() {

        return meetingNameEn;
    }

    public void setMeetingNameEn(String meetingNameEn) {

        this.meetingNameEn = meetingNameEn;
    }

    public String getMeetingIntroEn() {

        return meetingIntroEn;
    }

    public void setMeetingIntroEn(String meetingIntroEn) {

        this.meetingIntroEn = meetingIntroEn;
    }

    public String getEntryType() {

        return entryType;
    }

    public void setEntryType(String entryType) {

        this.entryType = entryType;
    }

    public String getEntryPlace() {

        return entryPlace;
    }

    public void setEntryPlace(String entryPlace) {

        this.entryPlace = entryPlace;
    }

    public String getEntryDate() {

        return entryDate;
    }

    public void setEntryDate(String entryDate) {

        this.entryDate = entryDate;
    }

    public String getEntryValidity() {

        return entryValidity;
    }

    public void setEntryValidity(String entryValidity) {

        this.entryValidity = entryValidity;
    }

    public String getEntryNum() {

        return entryNum;
    }

    public void setEntryNum(String entryNum) {

        this.entryNum = entryNum;
    }

    public String getEntryEndmtDate() {

        return entryEndmtDate;
    }

    public void setEntryEndmtDate(String entryEndmtDate) {

        this.entryEndmtDate = entryEndmtDate;
    }

    public String getEntryEndmtValidity() {

        return entryEndmtValidity;
    }

    public void setEntryEndmtValidity(String entryEndmtValidity) {

        this.entryEndmtValidity = entryEndmtValidity;
    }

    public String getEntryPic1() {

        return entryPic1;
    }

    public void setEntryPic1(String entryPic1) {

        this.entryPic1 = entryPic1;
    }

    public String getEntryPic2() {

        return entryPic2;
    }

    public void setEntryPic2(String entryPic2) {

        this.entryPic2 = entryPic2;
    }
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

    public String getUseRealname() {

        return useRealname;
    }

    public void setUseRealname(String useRealname) {

        this.useRealname = useRealname;
    }

    public String getUseAlias() {

        return useAlias;
    }

    public void setUseAlias(String useAlias) {

        this.useAlias = useAlias;
    }

    public String getUalias() {

        return ualias;
    }

    public void setUalias(String ualias) {

        this.ualias = ualias;
    }

    public String getUseOtherPtitle() {

        return useOtherPtitle;
    }

    public void setUseOtherPtitle(String useOtherPtitle) {

        this.useOtherPtitle = useOtherPtitle;
    }

    public String getPositionTitle() {

        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {

        this.positionTitle = positionTitle;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    public String getFoodAllergies() {

        return foodAllergies;
    }

    public void setFoodAllergies(String foodAllergies) {

        this.foodAllergies = foodAllergies;
    }

    public String getSelfIntroEn() {

        return selfIntroEn;
    }

    public void setSelfIntroEn(String selfIntroEn) {

        this.selfIntroEn = selfIntroEn;
    }

    public String getWorkContent() {

        return workContent;
    }

    public void setWorkContent(String workContent) {

        this.workContent = workContent;
    }

    public String getSuperviserName() {

        return superviserName;
    }

    public void setSuperviserName(String superviserName) {

        this.superviserName = superviserName;
    }

    public String getSuperviserPhone() {

        return superviserPhone;
    }

    public void setSuperviserPhone(String superviserPhone) {

        this.superviserPhone = superviserPhone;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getSname() {

        return sname;
    }

    public void setSname(String sname) {

        this.sname = sname;
    }

    public String getSemail() {

        return semail;
    }

    public void setSemail(String semail) {

        this.semail = semail;
    }

	public String getSuserType() {
		return suserType;
	}

	public void setSuserType(String suserType) {
		this.suserType = suserType;
	}

	public String getSapproveState() {
		return sapproveState;
	}

	public void setSapproveState(String sapproveState) {
		this.sapproveState = sapproveState;
	}
	
    public String getUpdateDate() {
        return updateDate;
    }
    
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getRemindFlag() {
        return remindFlag;
    }
    public void setRemindFlag(String remindFlag) {
        this.remindFlag = remindFlag;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
