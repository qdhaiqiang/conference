package com.centling.conference.sysmenu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * ConfSysMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "conf_sys_menu")
public class ConfSysMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String icon;
	private String pid;
	private String name;
	private short level;
	private String menuUrl;
	private Integer showOrder;

	// Constructors

	/** default constructor */
	public ConfSysMenu() {
	}

	/** full constructor */
	public ConfSysMenu(String icon, String pid, String name, short level,
			String menuUrl, Integer showOrder) {
		this.icon = icon;
		this.pid = pid;
		this.name = name;
		this.level = level;
		this.menuUrl = menuUrl;
		this.showOrder = showOrder;
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

	@Column(name = "icon", nullable = false, length = 50)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "pid", nullable = false, length = 40)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "level", nullable = false)
	public short getLevel() {
		return this.level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	@Column(name = "menu_url", nullable = false, length = 200)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "show_order", nullable = false)
	public Integer getShowOrder() {
		return this.showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

}