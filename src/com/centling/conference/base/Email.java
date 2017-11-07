package com.centling.conference.base;

import org.apache.commons.lang3.StringUtils;

public class Email {
	private String[] to;  //发送地址
	private String[] cc;  //抄送地址
	private String subject;      //邮件主题
	private String text;      //邮件内容
	private String[] inlineImg;  //邮件内嵌图片
	private String[] attachName; //附件名称
	
	public String[] getTo() {
		return to;
	}
	
	public void setTo(String[] to) {
		this.to = to;
	}
	
	public String[] getCc() {
		return cc;
	}
	
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		if (StringUtils.isNotEmpty(text)) {
			this.text = text + "<div style='height:100px; width:400px; margin-bottom: -500px;'></div>";
		} else {
			this.text = text;
		}
	}
	
	public String[] getInlineImg() {
		return inlineImg;
	}
	
	public void setInlineImg(String[] inlineImg) {
		this.inlineImg = inlineImg;
	}
	
	public String[] getAttachName() {
		return attachName;
	}
	
	public void setAttachName(String[] attachName) {
		this.attachName = attachName;
	}
	
}