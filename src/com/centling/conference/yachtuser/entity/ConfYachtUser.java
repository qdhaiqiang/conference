package com.centling.conference.yachtuser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfYachtUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_yacht_user")
public class ConfYachtUser implements java.io.Serializable {

	// Fields

	private String id;
	private String cname;
	private String password;
	private String sex;
	private String nation;
	private String certValue;
	private String birth;
	private String company;
	private String position;
	private String mobile;
	private String email;
	private String address;
	private String certPic1;
	private String certPic2;
	private String arriveNum;
	private String arriveTime;
	private String pickLocation;
	private String userType;
	private String entrance;
	private String buyInfo;
	private String pushInfo;
	private String veriCode;
	private String createTime;
	private String updateTime;

	// Constructors

	/** default constructor */
	public ConfYachtUser() {
	}

	/** full constructor */
	public ConfYachtUser(String cname, String sex, String nation, String certValue, String birth, String company, String position, String mobile, String email, String address, String certPic1, String certPic2, String arriveNum, String arriveTime, String pickLocation,
			String userType, String entrance, String buyInfo, String pushInfo) {
		this.cname = cname;
		this.sex = sex;
		this.nation = nation;
		this.certValue = certValue;
		this.birth = birth;
		this.company = company;
		this.position = position;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.certPic1 = certPic1;
		this.certPic2 = certPic2;
		this.arriveNum = arriveNum;
		this.arriveTime = arriveTime;
		this.pickLocation = pickLocation;
		this.userType = userType;
		this.entrance = entrance;
		this.buyInfo = buyInfo;
		this.pushInfo = pushInfo;
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

	@Column(name = "cname", length = 100)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "sex", length = 1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "nation", length = 5)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "cert_value", length = 50)
	public String getCertValue() {
		return this.certValue;
	}

	public void setCertValue(String certValue) {
		this.certValue = certValue;
	}

	@Column(name = "birth", length = 10)
	public String getBirth() {
		return this.birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Column(name = "company", length = 200)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "position", length = 200)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "mobile", length = 50)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "cert_pic1", length = 500)
	public String getCertPic1() {
		return this.certPic1;
	}

	public void setCertPic1(String certPic1) {
		this.certPic1 = certPic1;
	}

	@Column(name = "cert_pic2", length = 500)
	public String getCertPic2() {
		return this.certPic2;
	}

	public void setCertPic2(String certPic2) {
		this.certPic2 = certPic2;
	}

	@Column(name = "arrive_num", length = 100)
	public String getArriveNum() {
		return this.arriveNum;
	}

	public void setArriveNum(String arriveNum) {
		this.arriveNum = arriveNum;
	}

	@Column(name = "arrive_time", length = 50)
	public String getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	@Column(name = "pick_location", length = 50)
	public String getPickLocation() {
		return this.pickLocation;
	}

	public void setPickLocation(String pickLocation) {
		this.pickLocation = pickLocation;
	}

	@Column(name = "user_type", length = 1)
	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name = "entrance", length = 1)
	public String getEntrance() {
		return this.entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	@Column(name = "buy_info", length = 1)
	public String getBuyInfo() {
		return this.buyInfo;
	}

	public void setBuyInfo(String buyInfo) {
		this.buyInfo = buyInfo;
	}

	@Column(name = "push_info", length = 1)
	public String getPushInfo() {
		return this.pushInfo;
	}

	public void setPushInfo(String pushInfo) {
		this.pushInfo = pushInfo;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="veri_code")
	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name="update_time")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}