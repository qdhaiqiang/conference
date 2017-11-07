package com.centling.conference.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ConfUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_user")
public class ConfUser implements java.io.Serializable {

    // Fields

    private String id;
    private String cname;
    private String firstName;
    private String lastName;
    private String ename;
    private String sex;
    private String birth;
    private String nation;
    private String certType;
    private String certValue;
    private String certExpiryDate;
    private String certPlace;
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
    private String approveState;
    private String useRealname;
    private String useAlias;
    private String ualias;
    private String useOtherPtitle;
    private String positionTitle;
    private String remark;
    private String password;
    private String mailChecked;
    private String dietTaboo;
    private String etiquette;
    private String officialLang;
    private String entryEndmtDate;
    private String entryType;
    private String entryPlace;
    private String entryDate;
    private String entryValidity;
    private String entryNum;
    private String entryEndmtDate_1;
    private String entryEndmtValidity;
    private String entryPic1;
    private String entryPic2;
    private String foodAllergies;
    private String selfIntroEn;
    private String workContent;
    private String superviserName;
    private String superviserPhone;
    private String registerDate;
    private String updateDate;
    private String cardType;

    // Constructors

    /** default constructor */
    public ConfUser() {
    }

    /** full constructor */
    public ConfUser(String cname, String firstName, String lastName,
            String ename, String sex, String birth, String nation,
            String certType, String certValue, String certExpiryDate,
            String certPlace, String certPic1, String certPic2, String email,
            String tele, String mobile, String fax, String address,
            String postcode, String photo, String medlHis, String religion,
            String selfIntro, String industry, String companyType,
            String company, String position, String visaNeed, String visaCity,
            String contactPerson, String vegetarian, String approveState,
            String useRealname, String useAlias, String ualias,
            String useOtherPtitle, String positionTitle, String remark,
            String password, String mailChecked, String dietTaboo,
            String etiquette, String officialLang, String entryEndmtDate,
            String entryType, String entryPlace, String entryDate,
            String entryValidity, String entryNum, String entryEndmtDate_1,
            String entryEndmtValidity, String entryPic1, String entryPic2,
            String foodAllergies, String selfIntroEn, String workContent,
            String superviserName, String superviserPhone, String registerDate,
            String updateDate,String cardType) {
        this.cname = cname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ename = ename;
        this.sex = sex;
        this.birth = birth;
        this.nation = nation;
        this.certType = certType;
        this.certValue = certValue;
        this.certExpiryDate = certExpiryDate;
        this.certPlace = certPlace;
        this.certPic1 = certPic1;
        this.certPic2 = certPic2;
        this.email = email;
        this.tele = tele;
        this.mobile = mobile;
        this.fax = fax;
        this.address = address;
        this.postcode = postcode;
        this.photo = photo;
        this.medlHis = medlHis;
        this.religion = religion;
        this.selfIntro = selfIntro;
        this.industry = industry;
        this.companyType = companyType;
        this.company = company;
        this.position = position;
        this.visaNeed = visaNeed;
        this.visaCity = visaCity;
        this.contactPerson = contactPerson;
        this.vegetarian = vegetarian;
        this.approveState = approveState;
        this.useRealname = useRealname;
        this.useAlias = useAlias;
        this.ualias = ualias;
        this.useOtherPtitle = useOtherPtitle;
        this.positionTitle = positionTitle;
        this.remark = remark;
        this.password = password;
        this.mailChecked = mailChecked;
        this.dietTaboo = dietTaboo;
        this.etiquette = etiquette;
        this.officialLang = officialLang;
        this.entryEndmtDate = entryEndmtDate;
        this.entryType = entryType;
        this.entryPlace = entryPlace;
        this.entryDate = entryDate;
        this.entryValidity = entryValidity;
        this.entryNum = entryNum;
        this.entryEndmtDate_1 = entryEndmtDate_1;
        this.entryEndmtValidity = entryEndmtValidity;
        this.entryPic1 = entryPic1;
        this.entryPic2 = entryPic2;
        this.foodAllergies = foodAllergies;
        this.selfIntroEn = selfIntroEn;
        this.workContent = workContent;
        this.superviserName = superviserName;
        this.superviserPhone = superviserPhone;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
        this.cardType = cardType;
    }

    // Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false, length = 40)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "cname", length = 50)
    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 50)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Column(name = "cert_type", length = 1)
    public String getCertType() {
        return this.certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    @Column(name = "cert_value", length = 50)
    public String getCertValue() {
        return this.certValue;
    }

    public void setCertValue(String certValue) {
        this.certValue = certValue;
    }

    @Column(name = "cert_expiry_date", length = 10)
    public String getCertExpiryDate() {
        return this.certExpiryDate;
    }

    public void setCertExpiryDate(String certExpiryDate) {
        this.certExpiryDate = certExpiryDate;
    }

    @Column(name = "cert_place", length = 200)
    public String getCertPlace() {
        return this.certPlace;
    }

    public void setCertPlace(String certPlace) {
        this.certPlace = certPlace;
    }

    @Column(name = "cert_pic1", length = 200)
    public String getCertPic1() {
        return this.certPic1;
    }

    public void setCertPic1(String certPic1) {
        this.certPic1 = certPic1;
    }

    @Column(name = "cert_pic2", length = 200)
    public String getCertPic2() {
        return this.certPic2;
    }

    public void setCertPic2(String certPic2) {
        this.certPic2 = certPic2;
    }

    @Column(name = "email", length = 80)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Column(name = "fax", length = 50)
    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    @Column(name = "photo", length = 200)
    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "medl_his", length = 200)
    public String getMedlHis() {
        return this.medlHis;
    }

    public void setMedlHis(String medlHis) {
        this.medlHis = medlHis;
    }

    @Column(name = "religion", length = 50)
    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    @Column(name = "self_intro", length = 1024)
    public String getSelfIntro() {
        return this.selfIntro;
    }

    public void setSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }

    @Column(name = "industry", length = 2)
    public String getIndustry() {
        return this.industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Column(name = "company_type", length = 2)
    public String getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Column(name = "company", length = 50)
    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Column(name = "position", length = 50)
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name = "visa_need", length = 1)
    public String getVisaNeed() {
        return this.visaNeed;
    }

    public void setVisaNeed(String visaNeed) {
        this.visaNeed = visaNeed;
    }

    @Column(name = "visa_city", length = 50)
    public String getVisaCity() {
        return this.visaCity;
    }

    public void setVisaCity(String visaCity) {
        this.visaCity = visaCity;
    }

    @Column(name = "contact_person", length = 2000)
    public String getContactPerson() {
        return this.contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Column(name = "vegetarian", length = 1)
    public String getVegetarian() {
        return this.vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Column(name = "approve_state", length = 1)
    public String getApproveState() {
        return this.approveState;
    }

    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }

    @Column(name = "use_realname", length = 1)
    public String getUseRealname() {
        return this.useRealname;
    }

    public void setUseRealname(String useRealname) {
        this.useRealname = useRealname;
    }

    @Column(name = "use_alias", length = 1)
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

    @Column(name = "use_other_ptitle", length = 1)
    public String getUseOtherPtitle() {
        return this.useOtherPtitle;
    }

    public void setUseOtherPtitle(String useOtherPtitle) {
        this.useOtherPtitle = useOtherPtitle;
    }

    @Column(name = "position_title", length = 100)
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

    @Column(name = "password", length = 50, updatable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "mail_checked", length = 1)
    public String getMailChecked() {
        return this.mailChecked;
    }

    public void setMailChecked(String mailChecked) {
        this.mailChecked = mailChecked;
    }

    @Column(name = "diet_taboo", length = 100)
    public String getDietTaboo() {
        return this.dietTaboo;
    }

    public void setDietTaboo(String dietTaboo) {
        this.dietTaboo = dietTaboo;
    }

    @Column(name = "etiquette", length = 200)
    public String getEtiquette() {
        return this.etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    @Column(name = "official_lang", length = 20)
    public String getOfficialLang() {
        return this.officialLang;
    }

    public void setOfficialLang(String officialLang) {
        this.officialLang = officialLang;
    }

    @Column(name = "entryEndmtDate", length = 10)
    public String getEntryEndmtDate() {
        return this.entryEndmtDate;
    }

    public void setEntryEndmtDate(String entryEndmtDate) {
        this.entryEndmtDate = entryEndmtDate;
    }

    @Column(name = "entry_type", length = 1)
    public String getEntryType() {
        return this.entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    @Column(name = "entry_place", length = 200)
    public String getEntryPlace() {
        return this.entryPlace;
    }

    public void setEntryPlace(String entryPlace) {
        this.entryPlace = entryPlace;
    }

    @Column(name = "entry_date", length = 20)
    public String getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    @Column(name = "entry_validity", length = 20)
    public String getEntryValidity() {
        return this.entryValidity;
    }

    public void setEntryValidity(String entryValidity) {
        this.entryValidity = entryValidity;
    }

    @Column(name = "entry_num", length = 50)
    public String getEntryNum() {
        return this.entryNum;
    }

    public void setEntryNum(String entryNum) {
        this.entryNum = entryNum;
    }

    @Column(name = "entry_endmt_date", length = 20)
    public String getEntryEndmtDate_1() {
        return this.entryEndmtDate_1;
    }

    public void setEntryEndmtDate_1(String entryEndmtDate_1) {
        this.entryEndmtDate_1 = entryEndmtDate_1;
    }

    @Column(name = "entry_endmt_validity", length = 20)
    public String getEntryEndmtValidity() {
        return this.entryEndmtValidity;
    }

    public void setEntryEndmtValidity(String entryEndmtValidity) {
        this.entryEndmtValidity = entryEndmtValidity;
    }

    @Column(name = "entry_pic1", length = 200)
    public String getEntryPic1() {
        return this.entryPic1;
    }

    public void setEntryPic1(String entryPic1) {
        this.entryPic1 = entryPic1;
    }

    @Column(name = "entry_pic2", length = 200)
    public String getEntryPic2() {
        return this.entryPic2;
    }

    public void setEntryPic2(String entryPic2) {
        this.entryPic2 = entryPic2;
    }

    @Column(name = "food_allergies", length = 200)
    public String getFoodAllergies() {
        return this.foodAllergies;
    }

    public void setFoodAllergies(String foodAllergies) {
        this.foodAllergies = foodAllergies;
    }

    @Column(name = "self_intro_en", length = 1024)
    public String getSelfIntroEn() {
        return this.selfIntroEn;
    }

    public void setSelfIntroEn(String selfIntroEn) {
        this.selfIntroEn = selfIntroEn;
    }

    @Column(name = "work_content", length = 200)
    public String getWorkContent() {
        return this.workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    @Column(name = "superviser_name", length = 200)
    public String getSuperviserName() {
        return this.superviserName;
    }

    public void setSuperviserName(String superviserName) {
        this.superviserName = superviserName;
    }

    @Column(name = "superviser_phone", length = 200)
    public String getSuperviserPhone() {
        return this.superviserPhone;
    }

    public void setSuperviserPhone(String superviserPhone) {
        this.superviserPhone = superviserPhone;
    }

    @Column(name = "register_date", length = 20)
    public String getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "update_date", length = 20)
    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    
    @Column(name = "card_type", length = 1)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    

}