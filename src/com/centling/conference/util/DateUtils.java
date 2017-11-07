package com.centling.conference.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static String getDateFromString(String strDate) {
        if (strDate.length() == 19 && 
                strDate.substring(2, 3).equals("/") &&
                strDate.substring(5, 6).equals("/") &&
                strDate.substring(10, 11).equals(" ")) {
            
            String day = strDate.substring(0, 2);
            String month = strDate.substring(3, 5);
            String year = strDate.substring(6, 10);
            String HH = strDate.substring(11, 13);
            String mm = strDate.substring(14, 16);
            String ss = strDate.substring(17, 19);
            return year + "/" + month + "/" + day + " " +
                    HH + ":" + mm + ":" + ss;
        }
        return "";
    }
    
    public static String format(Date date, String pattern) {
    	 SimpleDateFormat sFormat = new SimpleDateFormat(pattern);
    	 return sFormat.format(date);
    }
    
    public static String format(String date, String oldPattern, String newPattern) {
    	SimpleDateFormat oldFormat = new SimpleDateFormat(oldPattern);
    	SimpleDateFormat newFormat = new SimpleDateFormat(newPattern);
    	try {
			return newFormat.format(oldFormat.parse(date));
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
    	return "";
    }
}
