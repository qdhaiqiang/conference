package com.centling.conference.dict.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_dict")
public class ConfDict implements java.io.Serializable {

	// Fields
	@JsonIgnore
	private String id;
	@JsonIgnore
	private String categoryId;
	private String code;
	private String name;
	private String showOrder;

	// Constructors

	/** default constructor */
	public ConfDict() {
	}

	/** full constructor */
	public ConfDict(String categoryId, String code, String name) {
		this.categoryId = categoryId;
		this.code = code;
		this.name = name;
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

	@Column(name = "category_id", length = 40)
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "code", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="show_order")
	public String getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}
}