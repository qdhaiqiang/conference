package com.centling.conference.util.exportWord;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Word工具类 
 * @author lenovo
 *
 */
public class WordUtil {
	private Configuration configuration = null;
	
	public WordUtil() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}
	
	/**
	 * 创建word文件
	 * @param dataMap 填充数据
	 * @param fileName 文件名
	 */
	public void createDoc(Map<String, Object> dataMap,String filePath, String templateName, String fileName) {
		configuration.setClassForTemplateLoading(getClass(), "/wordtemplate");
		Template template = null;
		Writer out = null;
		try {
			template = configuration.getTemplate(templateName);
			File outFile = new File(filePath + File.separator + fileName);
			if (!outFile.getParentFile().exists()) {
				outFile.getParentFile().mkdirs();
			}
			out= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
			template.process(dataMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 创建word文件
	 * @param dataMap 填充数据
	 * @param fileName 文件名
	 */
	public void createDoc(Map<String, Object> dataMap,String templateName,OutputStream sos) {
		configuration.setClassForTemplateLoading(getClass(), "/wordtemplate");
		Template template = null;
		Writer out = null;
		try {
			template = configuration.getTemplate(templateName);
			out= new BufferedWriter(new OutputStreamWriter(sos,"UTF-8"));
			template.process(dataMap, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}