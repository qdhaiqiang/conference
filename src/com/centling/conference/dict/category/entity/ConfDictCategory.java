package com.centling.conference.dict.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfDictCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_dict_category")
public class ConfDictCategory implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public ConfDictCategory() {
	}

	/** full constructor */
	public ConfDictCategory(String code, String name) {
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

}