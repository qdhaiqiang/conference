package com.centling.conference.zzz.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * GradeClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "grade_class")
public class GradeClass implements java.io.Serializable {

	// Fields

	private String id;
	private String className;

	// Constructors

	/** default constructor */
	public GradeClass() {
	}

	/** full constructor */
	public GradeClass(String className) {
		this.className = className;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "class_name", length = 400)
	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	

}