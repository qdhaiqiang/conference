package com.centling.conference.sysrole.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_role_user")
public class ConfRoleUser implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String userId;

	// Constructors

	/** default constructor */
	public ConfRoleUser() {
	}

	/** full constructor */
	public ConfRoleUser(String roleId, String userId) {
		this.roleId = roleId;
		this.userId = userId;
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

	@Column(name = "role_id", nullable = false, length = 40)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "user_id", nullable = false, length = 40)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}