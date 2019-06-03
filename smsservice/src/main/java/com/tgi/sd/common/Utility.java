package com.tgi.sd.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.common.Utility;

public class Utility {
	public static final String OFFLINE = "OFFLINE";
	private static Logger logger = Logger.getLogger(Utility.class);
	
/*	public static boolean netIsAvailable() {                                                                                                                                                                                                 
	    try {                                                                                                                                                                                                                                 
	        //final URL url = new URL("http://www.google.com");
	    	final URL url = new URL(com.tgi.sd.manager.Utility.getResourceValue("netAvalabilityUrl"));
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } catch (MalformedURLException e) {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } catch (IOException e) {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}*/
	
	public static boolean internetIsAvailable(String loginURL) {                                                                                                                                                                                                 
	    try {                                                                                                                                                                                                                                 
	        final URL url = new URL(loginURL);
	    	final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } catch (Throwable e) {                                                                                                                                                                                                   
	    	return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}
	
	public static String MD5Converter(String text) {
		
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());
			byte[] digest = md.digest();
			
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return sb.toString();
		
	}
	
	public static long convertDateIntoSeconds(String dateStr) {
		long  seconds = 0l;
		if(dateStr!=null) {
			try {
				SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
				Date date = simpledate.parse(dateStr);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				//System.out.println("milli Seconds : " +calendar.getTimeInMillis());
				seconds= calendar.getTimeInMillis()/1000;
				//System.out.println("seconds" +seconds);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return seconds;
		
	}
	
	public static String convertCurrentDateIntoString() {
		String currrentDateStr = null;
		try {
			SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
			currrentDateStr = simpledate.format(new Date());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return currrentDateStr;
		
	}
	
	public static String convertSystemTimeToGMTDate(Date date) {
		String inputDate = DateFormatUtils.format(date, "MM/dd/yyyy HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(inputDate,
				DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
		ZonedDateTime systemDefaultZoned = localDateTime.atZone(ZoneId.systemDefault());
		ZonedDateTime convertDateTime = systemDefaultZoned.withZoneSameInstant(ZoneId.of("GMT"));
		String date2 = convertDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"));
		logger.info(date2);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		String convertdate = format.format(convertDateTime);
		logger.info(convertStringtoDateTime(convertdate));
		//return convertStringtoDateTime(convertdate);
		return convertdate;
	}
	
	public static Date convertStringtoDateTime(String requestDate) {
		Date date = null;
		try {
			SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			date = simpledate.parse(requestDate);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return date;
		
	}
	
}
