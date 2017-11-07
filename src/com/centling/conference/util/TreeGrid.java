package com.centling.conference.util;

import java.util.Map;

public class TreeGrid {
	private String id; // 菜单主键
	private String text; // 菜单标题
 	private String parentId; // 父菜单主键
 	private String parentText; // 父菜单标题
 	private String code; // 图标
 	private String src; // url地址
 	private String note;
	private Map<String,String> attributes;// 其他参数
 	private String  operations;// 其他参数
 	private String state = "open";// 是否展开(open,closed)
 	private String order;//排序
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentText() {
		return parentText;
	}
	public void setParentText(String parentText) {
		this.parentText = parentText;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public String getOperations() {
		return operations;
	}
	public void setOperations(String operations) {
		this.operations = operations;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}