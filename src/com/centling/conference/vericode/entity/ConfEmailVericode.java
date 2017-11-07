package com.centling.conference.vericode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEmailVericode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_email_vericode")
public class ConfEmailVericode implements java.io.Serializable {

	// Fields

	private String id;
	private String email;
	private String veriCode;

	// Constructors

	/** default constructor */
	public ConfEmailVericode() {
	}

	/** full constructor */
	public ConfEmailVericode(String email, String veriCode) {
		this.email = email;
		this.veriCode = veriCode;
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

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "veri_code", length = 20)
	public String getVeriCode() {
		return this.veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

}