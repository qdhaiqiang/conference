package com.centling.conference.zzz.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student")
public class Student implements java.io.Serializable {

	// Fields

	private String id;
	private GradeClass gradeClass;
	private String studentName;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(GradeClass gradeClass, String studentName) {
		this.gradeClass = gradeClass;
		this.studentName = studentName;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "grade_class")
	public GradeClass getGradeClass() {
		return this.gradeClass;
	}

	public void setGradeClass(GradeClass gradeClass) {
		this.gradeClass = gradeClass;
	}

	@Column(name = "student_name", length = 50)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}