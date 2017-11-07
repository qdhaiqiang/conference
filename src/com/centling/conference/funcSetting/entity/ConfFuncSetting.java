package com.centling.conference.funcSetting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfFuncSetting entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_func_setting")
public class ConfFuncSetting implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String funcName;
	private String funcEname;
	private String funcDes;
	private String funcEdes;
	private Integer required;
	private Integer status;
	private Integer showOrder;
	private String icon;
	private String refUrl;
	private String createDate;
	private String updateDate;

	// Constructors

	/** default constructor */
	public ConfFuncSetting() {
	}

	/** full constructor */
	public ConfFuncSetting(String meetingId, String funcName, String funcEname,
			String funcDes, String funcEdes, Integer required, Integer status,
			Integer showOrder, String icon, String refUrl, String createDate,
			String updateDate) {
		this.meetingId = meetingId;
		this.funcName = funcName;
		this.funcEname = funcEname;
		this.funcDes = funcDes;
		this.funcEdes = funcEdes;
		this.required = required;
		this.status = status;
		this.showOrder = showOrder;
		this.icon = icon;
		this.refUrl = refUrl;
		this.createDate = createDate;
		this.updateDate = updateDate;
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

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "func_name", length = 50)
	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@Column(name = "func_ename", length = 50)
	public String getFuncEname() {
		return this.funcEname;
	}

	public void setFuncEname(String funcEname) {
		this.funcEname = funcEname;
	}

	@Column(name = "func_des", length = 65535)
	public String getFuncDes() {
		return this.funcDes;
	}

	public void setFuncDes(String funcDes) {
		this.funcDes = funcDes;
	}

	@Column(name = "func_edes", length = 65535)
	public String getFuncEdes() {
		return this.funcEdes;
	}

	public void setFuncEdes(String funcEdes) {
		this.funcEdes = funcEdes;
	}

	@Column(name = "required")
	public Integer getRequired() {
		return this.required;
	}

	public void setRequired(Integer required) {
		this.required = required;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "show_order")
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	@Column(name = "icon", length = 200)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "ref_url", length = 500)
	public String getRefUrl() {
		return this.refUrl;
	}

	public void setRefUrl(String refUrl) {
		this.refUrl = refUrl;
	}

	@Column(name = "create_date", length = 20)
	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Column(name = "update_date", length = 20)
	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}