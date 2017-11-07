package com.centling.conference.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author tiaobug 公共方法的调用
 * 
 */

@Component
public class CommonsMethod {

	private static final long serialVersionUID = -2407547567212022750L;
	private static SimpleDateFormat yMd = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat sdf1 = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	/**
	 * @return 返回现在时间，精确到秒，不带文字 例如：20120524104858
	 */
	public static String getNowCorrect2Second() {
		return sdf1.format(new Date());
	}

	public static Integer getNowYear() {
		String now = getNowCorrect2Second();
		int nowYear = Integer.valueOf(now.substring(0, 4));
		return nowYear;
	}

	/**
	 * @return 返回现在时间，精确到毫秒秒，不带文字 例如：20120524104858205
	 */
	public static String getNowCorrect2Millisecond() {
		return sdf2.format(new Date());
	}

	/**
	 * @return 返回现在时间，精确到秒，带文字 例如：2012年05月24日10时48分58秒
	 */
	public static String getNowCorrect2SecondWithWord() {
		String now = getNowCorrect2Second();
		return now.substring(0, 4) + "年" + now.substring(4, 6) + "月"
				+ now.substring(6, 8) + "日" + now.substring(8, 10) + "时"
				+ now.substring(10, 12) + "分" + now.substring(12, 14) + "秒";
	}

	/**
	 * @return 返回现在时间，精确到秒，带文字 例如：2012-05-24 10:48:58
	 */
	public static String getNowCorrect2SecondWithEnglishWord() {
		String now = getNowCorrect2Second();
		return now.substring(0, 4) + "-" + now.substring(4, 6) + "-"
				+ now.substring(6, 8) + "  " + now.substring(8, 10) + ":"
				+ now.substring(10, 12) + ":" + now.substring(12, 14) + " ";
	}

	/**
	 * @return 返回现在时间，精确到毫秒秒，带文字 例如：2012年05月24日10时48分58秒205毫秒
	 */
	public static String getNowCorrect2MillisecondWithWord() {
		String now = getNowCorrect2Millisecond();
		return now.substring(0, 4) + "年" + now.substring(4, 6) + "月"
				+ now.substring(6, 8) + "日" + now.substring(8, 10) + "时"
				+ now.substring(10, 12) + "分" + now.substring(12, 14) + "秒"
				+ now.substring(14, 17) + "毫秒";
	}

	/**
	 * @return 返回现在日期，精确到天 例如：20120524
	 */
	public static String getToday() {
		return yMd.format(new Date());
	}

	/**
	 * @return 返回现在日期，精确到天,带文字 例如：2012年05月24日
	 */
	public static String getTodayWithWord() {
		String today = getToday();
		return today.substring(0, 4) + "年" + today.substring(4, 6) + "月"
				+ today.substring(6, 8) + "日";
	}

	/**
	 * @return 返回现在日期，精确到天,带文字 例如：2012-05-24
	 */
	public static String getTodayDBWithWord() {
		String today = getToday();
		return today.substring(0, 4) + "-" + today.substring(4, 6) + "-"
				+ today.substring(6, 8);
	}


	/**
	 * @return 得到上传文件的路径（如:DOP_CMS/...）
	 */
	public static String getHttpPath(String rootPath, String fileName)
			throws Exception {
		int index = rootPath.lastIndexOf("\\");
		return rootPath.substring(index) + "/UploadFiles/" + fileName;
	}

	/**
	 * @return 将数据库中的路径拼成(http://ip:8080/DOP_CMS/....)传给前台显示
	 */
	public static String getRealPath(String path) throws Exception {
		return "http://" + getServerIp() + ":" + getPort() + path;
	}

	/**
	 * @return 将路径拆分（如:DOP_CMS/...）传到后台保存
	 */
	public static String getVirtualPath(String path) throws Exception {
		String str = "http://" + getServerIp() + ":" + getPort();
		String virtualPath = path.substring(str.length(), path.length());
		return virtualPath;
	}

	/**
	 * @return 得到服务端的IP地址
	 */
	public static String getServerIp() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress().toString();
		return ip;
	}

	/**
	 * @return 得到运行程序的端口号（如:8080）
	 */
	public static String getPort() throws Exception {
		int port = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getLocalPort();
		return String.valueOf(port);
	}

	/**
	 * @return 得到工程的物理路径(例如:D:/Workspaces/DOP_CMS/WebRoot/)
	 */
	public static String getProjectPath() {
		String pathString = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		pathString = pathString.replace("file:/", "");
		pathString = pathString.replace("WEB-INF/classes/", "");
		pathString = pathString.replace("%20", " ");
		return pathString;
	}

	/**
	 * @return 得到工程的物理路径(例如:D:/Workspaces/DOP_CMS/WebRoot/)
	 */
	public static String getWebRootPath() {
		String rootPath = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getServletContext().getRealPath(
				"");
		return rootPath;
	}

	/**
	 * cds 获得tomcat系统ROOT 路径
	 */
	public static String getTomcatRootPath() {
		File file = new File(getWebRootPath());
		return file.getParent();
	}

	/**
	 * @return 根据时间产生随即的一个文件名称
	 */
	public static String generateFileByDate() {
		String currentTime = sdf2.format(new Date());
		String randomNumber = String.valueOf((int) (Math.random() * 1000));
		String fileName = currentTime + randomNumber;
		return fileName;
	}

	/**
	 * @return 客户端机器的IP
	 */
	public static String getRemoteIP() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * @return 过滤html标签的纯文本
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		try {
			String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script>]*?>[/s/S]*?<//script>
			// }
			String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style>]*?>[/s/S]*?<//style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签
			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签
			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签
			textStr = htmlStr.replace("&nbsp;", "");
		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		return textStr;
	}

	public static String changeISO8859_12UTF8(String noEncodeString) {
		try {
			return new String(noEncodeString.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
