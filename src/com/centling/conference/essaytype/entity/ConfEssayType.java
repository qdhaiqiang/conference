package com.centling.conference.essaytype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEssayType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_essay_type")
public class ConfEssayType implements java.io.Serializable {

	// Fields

	private String id;
	private String typeName;
	private String parentMenu;
	private Integer essayNum;
	private String typeNameEn;
	private Integer showOrder;
	private String meetingId;
	private Integer infoType;

	// Constructors

	/** default constructor */
	public ConfEssayType() {
	}

	/** full constructor */
	public ConfEssayType(String typeName, String parentMenu, Integer essayNum,
			String typeNameEn, Integer showOrder, String meetingId,
			Integer infoType) {
		this.typeName = typeName;
		this.parentMenu = parentMenu;
		this.essayNum = essayNum;
		this.typeNameEn = typeNameEn;
		this.showOrder = showOrder;
		this.meetingId = meetingId;
		this.infoType = infoType;
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

	@Column(name = "type_name", length = 40)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "parent_menu", length = 40)
	public String getParentMenu() {
		return this.parentMenu;
	}

	public void setParentMenu(String parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Column(name = "essay_num")
	public Integer getEssayNum() {
		return this.essayNum;
	}

	public void setEssayNum(Integer essayNum) {
		this.essayNum = essayNum;
	}

	@Column(name = "type_name_en", length = 200)
	public String getTypeNameEn() {
		return this.typeNameEn;
	}

	public void setTypeNameEn(String typeNameEn) {
		this.typeNameEn = typeNameEn;
	}

	@Column(name = "show_order")
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	@Column(name = "meeting_id", length = 40)
	public String getMeetingId() {
		return this.meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "info_type")
	public Integer getInfoType() {
		return this.infoType;
	}

	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}

}