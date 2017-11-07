package com.centling.conference.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfArticle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_article")
public class ConfArticle implements java.io.Serializable {

	// Fields

	private String id;
	private String title;
	private String context;

	// Constructors

	/** default constructor */
	public ConfArticle() {
	}

	/** full constructor */
	public ConfArticle(String title, String context) {
		this.title = title;
		this.context = context;
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

	@Column(name = "title", length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "context", length = 65535)
	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}