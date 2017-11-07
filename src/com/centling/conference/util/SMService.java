package com.centling.conference.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.centling.conference.base.Constant;

public class SMService {
	/**
	 * Send SMS send short message through 3rd party service to the numbers, 
	 * the username and password of the 3rd party service is stored in the constant file named SMSUSERNAME and SMSPASSWORD
	 *  And also please add 【组委会】 at the last of each message according to the service supplier.
	 * @param url The URL of the 3rd party service
	 * @param text The content of the message
	 * @param numberList The message will be sent to
	 * @return Result of the HTTP session
	 * */
	public static String sendSMS(String url, String text, ArrayList<String> numberList) throws Exception {
		String numberString="";
		for(String oneNumber : numberList){
			numberString=oneNumber+":";
		}
		numberString = numberString.substring(0, numberString.length()-1);

		String result = sendSMS(url,text,numberString);
		return result;
	}
	/**
	 * Send SMS send short message through 3rd party service to the number,
	 * 	the username and password of the 3rd party service is stored in the constant file 
	 * named SMSUSERNAME and SMSPASSWORD. And also please add 【组委会】 at the last of each message
	 * according to the service supplier.
	 * @param url The URL of the 3rd party service
	 * @param text The content of the message
	 * @param number The message will be sent to
	 * @return Result of the HTTP session 
	 * */
	public static String sendSMS(String url, String text, String number) throws Exception {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("user", Constant.SMSUSERNAME));
        nvps.add(new BasicNameValuePair("password", Md5.getMD5Str(Constant.SMSPASSWORD)));
        nvps.add(new BasicNameValuePair("tele",number));
        nvps.add(new BasicNameValuePair("msg",text));
        nvps.add(new BasicNameValuePair("extno","1"));
        nvps.add(new BasicNameValuePair("addseqno","1"));
		CloseableHttpClient httpClient= HttpClientBuilder.create().build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps,"GBK"));
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return EntityUtils.toString(httpResponse.getEntity(),"GBK");
	}
}