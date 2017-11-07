package com.centling.conference.emailtemplate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEmailTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_email_template")
public class ConfEmailTemplate implements java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String meetingId;
	private String name;
	private String userType;
	private String content;
	private String templateName;

	// Constructors

	/** default constructor */
	public ConfEmailTemplate() {
	}

	/** full constructor */
	public ConfEmailTemplate(String name, String content) {
		this.name = name;
		this.content = content;
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

	@Column(name = "name", length = 40)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "title", length = 1000)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "user_type", length = 50)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Column(name="template_name", length=200)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name="meeting_id", length=50)
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}
}