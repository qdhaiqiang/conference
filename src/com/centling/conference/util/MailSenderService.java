package com.centling.conference.util;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.centling.conference.base.Email;

/**
 * 邮件发送工具类：使用Spring配置
 * @author amosc
 *
 */
public class MailSenderService {
	private static Logger log = LoggerFactory.getLogger(MailSenderService.class);
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private SimpleMailMessage simpleMailMessage;
	private String templateName;

	private static MailSenderService mailSenderService;

	public static synchronized MailSenderService getInstance() {
		if (mailSenderService == null) {
//			ApplicationContext ctx = new FileSystemXmlApplicationContext(
//					"src/applicationContext-mail.xml");
			ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mail.xml");
			 mailSenderService = (MailSenderService) ctx.getBean("mailSenderService");
		}
		return mailSenderService;
	}
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	/**
	 * 发送velocity模板邮件
	 * @param email 邮件
	 * @param model velocity 数据
	 * 如果model为null，使用正常html样式，否则使用velocity模板
	 * @throws Exception 调用方处理异常
	 */
	public void sendMail(Email email, Map<String, Object> model) throws Exception {
		mailSender = this.getMailSender();
		try {			
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
			// 设置收件人，用数组发送至多个邮箱
			messageHelper.setTo(email.getTo());
			messageHelper.setFrom(simpleMailMessage.getFrom());
			if (email.getCc() != null && email.getCc().length > 0) {
				// 设置抄送人
				messageHelper.setCc(email.getCc());
			}
			if (email.getSubject() != null && email.getSubject().length() > 0) {
				// 邮件主题
				messageHelper.setSubject(email.getSubject());
			}
			if (model != null) {
				String result = VelocityEngineUtils.mergeTemplateIntoString(this.getVelocityEngine(), 
						this.getTemplateName(), "UTF-8", model);
				messageHelper.setText(result, true);
			}
			else {
				if (email.getText() != null && email.getText().length() > 0) {
					messageHelper.setText(email.getText(), true);// true 表示启动HTML格式的邮件
				}
			}

			if (email.getInlineImg() != null && email.getInlineImg().length > 0) {
				// 嵌入的图片，和文本显示在一起
				for (int i = 0; i < email.getInlineImg().length; i++) {
					File imgFile = new File(email.getInlineImg()[i]);
					if (imgFile.exists()) {
						FileSystemResource img = new FileSystemResource(imgFile);
						messageHelper.addInline(
								"aaa", img);
					}
				}
			}
			
			if (email.getAttachName() != null && email.getAttachName().length > 0) {
				// 附件形式的文件
				for (int j = 0; j < email.getAttachName().length; j++) {
					File aFile = new File(email.getAttachName()[j]);
					if (aFile.exists()) {
						FileSystemResource file = new FileSystemResource(aFile);
						// 这里的方法调用和图片是不同的。
						messageHelper.addAttachment(aFile.getName(), file);
					}
				}
			}
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 发送html样式邮件
	 * @param email 邮件
	 * @throws Exception 调用方处理异常
	 */
	public void sendMail(Email email) throws Exception {
		sendMail(email, null);
	}
	
	/**
	 * 在指定时间发送html样式邮件
	 * @param email
	 * @param date
	 */
	public void sendMailAtAssignedTime(Email email, Date date) {
		Timer timer = new Timer();
		SendMailTask task = new SendMailTask();
		task.setEmail(email);
		task.setModel(null);
		timer.schedule(task, date);
	}
	
	/**
	 * 在指定时间发送velocity模板邮件
	 * @param email
	 * @param model
	 * @param date
	 */
	public void sendMailAtAssignedTime(Email email, Map<String, Object> model, Date date) {
		Timer timer = new Timer();
		SendMailTask task = new SendMailTask();
		task.setEmail(email);
		task.setModel(model);
		timer.schedule(task, date);
	}
	
	/**
	 * 发送邮件任务
	 * @author amosc
	 *
	 */
	public class SendMailTask extends TimerTask {

		private Email email;
		private Map<String, Object> model;
		
		public Email getEmail() {
			return email;
		}

		public void setEmail(Email email) {
			this.email = email;
		}

		public Map<String, Object> getModel() {
			return model;
		}

		public void setModel(Map<String, Object> model) {
			this.model = model;
		}
		
		@Override
		public void run() {
			try {
				if (model == null)
					sendMail(email);
				else
					sendMail(email, model);
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
