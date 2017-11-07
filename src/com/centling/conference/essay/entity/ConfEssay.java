package com.centling.conference.essay.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfEssay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_essay")
public class ConfEssay implements java.io.Serializable {

	// Fields

	private String id;
	private String meetingId;
	private String essayTitle;
	private String essayTitleEn;
	private String createTime;
	private String createRealTime;
	private String lastUpdateTime;
	private String lastUpdateRealTime;
	private String author;
	private String realAuthor;
	private String viewCount;
	private String essayMenu;
	private String essayType;
	private String essayContent;
	private String essayContentEn;
	private String essayAbstract;
	private String articleSummary;
	private Integer articleState;
	private Integer showOrder;

	// Constructors

	/** default constructor */
	public ConfEssay() {
	}

	/** full constructor */
	public ConfEssay(String essayTitle, String createTime,
			String createRealTime, String lastUpdateTime,
			String lastUpdateRealTime, String author, String realAuthor,
			String viewCount, String essayMenu, String essayType,
			String essayContent, String essayAbstract, String articleSummary,
			Integer articleState) {
		this.essayTitle = essayTitle;
		this.createTime = createTime;
		this.createRealTime = createRealTime;
		this.lastUpdateTime = lastUpdateTime;
		this.lastUpdateRealTime = lastUpdateRealTime;
		this.author = author;
		this.realAuthor = realAuthor;
		this.viewCount = viewCount;
		this.essayMenu = essayMenu;
		this.essayType = essayType;
		this.essayContent = essayContent;
		this.essayAbstract = essayAbstract;
		this.articleSummary = articleSummary;
		this.articleState = articleState;
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

	@Column(name = "essay_title", length = 400)
	public String getEssayTitle() {
		return this.essayTitle;
	}

	public void setEssayTitle(String essayTitle) {
		this.essayTitle = essayTitle;
	}

	@Column(name = "create_time", length = 40)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "create_real_time", length = 40)
	public String getCreateRealTime() {
		return this.createRealTime;
	}

	public void setCreateRealTime(String createRealTime) {
		this.createRealTime = createRealTime;
	}

	@Column(name = "last_update_time", length = 40)
	public String getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Column(name = "last_update_real_time", length = 40)
	public String getLastUpdateRealTime() {
		return this.lastUpdateRealTime;
	}

	public void setLastUpdateRealTime(String lastUpdateRealTime) {
		this.lastUpdateRealTime = lastUpdateRealTime;
	}

	@Column(name = "author", length = 40)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "real_author", length = 40)
	public String getRealAuthor() {
		return this.realAuthor;
	}

	public void setRealAuthor(String realAuthor) {
		this.realAuthor = realAuthor;
	}

	@Column(name = "view_count", length = 400)
	public String getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	@Column(name = "essay_menu", length = 40)
	public String getEssayMenu() {
		return this.essayMenu;
	}

	public void setEssayMenu(String essayMenu) {
		this.essayMenu = essayMenu;
	}

	@Column(name = "essay_type", length = 40)
	public String getEssayType() {
		return this.essayType;
	}

	public void setEssayType(String essayType) {
		this.essayType = essayType;
	}

	@Column(name = "essay_content", length = 65535)
	public String getEssayContent() {
		return this.essayContent;
	}

	public void setEssayContent(String essayContent) {
		this.essayContent = essayContent;
	}

	@Column(name = "essay_abstract", length = 65535)
	public String getEssayAbstract() {
		return this.essayAbstract;
	}

	public void setEssayAbstract(String essayAbstract) {
		this.essayAbstract = essayAbstract;
	}

	@Column(name = "article_summary", length = 65535)
	public String getArticleSummary() {
		return this.articleSummary;
	}

	public void setArticleSummary(String articleSummary) {
		this.articleSummary = articleSummary;
	}

	@Column(name = "article_state")
	public Integer getArticleState() {
		return this.articleState;
	}

	public void setArticleState(Integer articleState) {
		this.articleState = articleState;
	}

	@Column(name = "meeting_id")
	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	@Column(name = "essay_title_en")
	public String getEssayTitleEn() {
		return essayTitleEn;
	}

	public void setEssayTitleEn(String essayTitleEn) {
		this.essayTitleEn = essayTitleEn;
	}

	@Column(name = "essay_content_en")
	public String getEssayContentEn() {
		return essayContentEn;
	}

	public void setEssayContentEn(String essayContentEn) {
		this.essayContentEn = essayContentEn;
	}

	@Column(name = "show_order")
	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
}