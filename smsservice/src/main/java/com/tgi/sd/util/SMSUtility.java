package com.tgi.sd.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReader;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.sd.domain.MailVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public class SMSUtility {
	
	public static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
	
	public static final SimpleDateFormat formatWithMins = new SimpleDateFormat("HH:mm");
	
	public static final SimpleDateFormat DAYTIMEFORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	public static String getDynamicValue() {
		char[] chars = "54321ABCDEFGHIJKLMN12345".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static boolean netIsAvailable() {                                                                                                                                                                                                 
	    try {                                                                                                                                                                                                                                 
	        //final URL url = new URL("http://www.google.com");
	    	final URL url = new URL(getResourceValue("netAvalabilityUrl"));
	        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
	        conn.connect();                                                                                                                                                                                                                   
	        return true;                                                                                                                                                                                                                      
	    } catch (MalformedURLException e) {                                                                                                                                                                                                   
	        throw new RuntimeException(e);                                                                                                                                                                                                    
	    } catch (IOException e) {                                                                                                                                                                                                             
	        return false;                                                                                                                                                                                                                     
	    }                                                                                                                                                                                                                                     
	}
	
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
			SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy");
			currrentDateStr = simpledate.format(new Date());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return currrentDateStr;
		
	}
	
	public static Date convertStringtoDate(String requestDate) {
		Date date = null;
		try {
			//SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy");
			date = simpledate.parse(requestDate);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
		
	}
	
	public static Date convertStringtoDateTime(String requestDate) {
		Date date = null;
		try {
			//SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat simpledate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			date = simpledate.parse(requestDate);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return date;
		
	}
	
	public static String getResourceValue(String key) {
		String value = null;
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("sms", Locale.getDefault());
			if (null != resourceBundle)
				value = resourceBundle.getString(key);
		}
		catch (Exception e) {
			System.out.println("Error resource bundle " + e.getMessage());
		}
		
		return value;
	}
	
	public static ResourceBundle getResourceBundle() {
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = ResourceBundle.getBundle("sms", Locale.getDefault());
		}
		catch (Exception e) {
			System.out.println("Error resource bundle " + e.getMessage());
		}
		
		return resourceBundle;
	}
	
	public static boolean tokenExpired(Date date){
		Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        cal2.setTime(new Date());
        if(cal1.after(cal2) || cal1.equals(cal2)){
           return false;
        }
		return true;
		
	}
	
	public static Date refreshTokenExpDate() {
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.DAY_OF_MONTH, Integer.parseInt(getResourceValue("tokenExpireDays")));
		return cal1.getTime();
	}
	
	public static String prepareMailContent(String candidateuserName,
			String candidatePassWord, String purpose_content,String headerMsg) {
		StringBuilder email = new StringBuilder();
	//	int index = candidateuserName.indexOf('@');
		//String name = candidateuserName.substring(0,index);
		email.append("<html><head>"
				+"<title>"+headerMsg+"</title>"
				+"</head> <body>"
				+"<table width='100%' cellspacing='0' cellpadding='0'>"
				+"<tbody><tr>"
    			+"<td style='color:#797d7f;font-size:30px;font-weight:bold;text-align:center;padding-bottom:7px;font-family:cursive'> Schools Diary </td>"
    			+"</tr><tr><td style='vertical-align:top;'>"
    			+"<table width='100%' cellspacing='0' cellpadding='0'><tbody>"
    			+"<tr style='background-color:#eee'>"
    			+"<td style='padding:15px'>"
    			+"<div style='text-align:center;font-size:40px;font-weight:bold;color:#979a9a;padding:2px 0px 0px 0px'>"
    			+"Hi!</div>"
    			+"<div style='text-align:center;font-size:35px;font-weight:bold;color:#196f3d'>"+candidateuserName+"</div>"
    			+"<div style='text-align:center;font-size:20px;font-weight:bold;color:#626567;position:relative'>"
    			+"<span style='background-color:#eee;padding:0px 5px;top:10px;position:relative'>Your SchoolsDiary account details</span></div>"
    			+"<div style='text-align:center;font-size:25px;font-weight:bold;color:#797d7f;padding-top: 5px;width:58%;margin-left:150px'>"
    			+purpose_content+"</div>"
    			+"</td></tr>"
    			+"<tr><td style='padding:15px'>"
    			+"<div style='text-align:center;font-size:16px;font-weight:bold;color:#797d7f;padding-bottom:7px'>Here are your login details</div>"
    			+"<div style='padding:12px;text-align:center'>"
    			+"<span style='padding:10px 25px 10px 25px;font-size:18px;font-weight:bold;background-color:#d0d3d4;margin-right:8px;color:#626567'>UserName</span>"
    			+"<span style='padding:10px 180px 10px 180px;font-size:18px;font-weight:bold;background-color:#eee;color:#626567'>"+candidateuserName+"</span></div>"
    			+"<div style='padding:12px;text-align:center'>"
    			+"<span style='padding:10px 27px 10px 27px;font-size:18px;font-weight:bold;background-color:#d0d3d4;margin-right:8px;color:#626567'>Password</span>"
    			+"<span style='padding:10px 180px 10px 180px;font-size:18px;font-weight:bold;background-color:#eee;color:#626567'>"+candidatePassWord+"</span></div>"
    			+"</td></tr>"
    			+"<tr style='background-color:#eee'><td>"
    			+"<div style='text-align:center;color:#424949;font-size:13px;font-weight:bold;padding:5px 0px;font-family:cursive'>"
    			+"2017 @ Tech Global India</div></td></tr>"
    			+"</tbody></table></td></tr>"
    			+"</tbody></table></body></html>");	
		return email.toString();
	}

	public static MailVO prepareMailVO(UserVO userVO,String mailType) {
		MailVO mailVO = new MailVO();
		mailVO.setId(UUID.randomUUID().toString());
		mailVO.setFromAddress(getResourceValue("mail.fromAddress"));
		mailVO.setToAddress(userVO.getContactEmail());
		if(getResourceValue("mail.CC") != null) {
			mailVO.setCcAddress(getResourceValue("mail.CC"));
		}
		mailVO.setMailType(mailType);
		mailVO.setStatus("CREATED");
		mailVO.setSubject(getResourceValue("mail.subject"));
		mailVO.setMailContent(prepareMailContent(userVO.getContactEmail(),
					"test123",getResourceValue("mail.loginUrl"),"headervalue"));
		
		return mailVO;
	}
	
	public static  TokenVO createTokenDetails(String id) {
		TokenVO tokenVO = new TokenVO();
		tokenVO.setId(id);
		tokenVO.setToken(UUID.randomUUID().toString());
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, 10);
		tokenVO.setTokenExpire(cal.getTime());
		//tokenVO.setTokenExpire(refreshTokenExpDate());
		return tokenVO;
		
	}
	
	/*public static <T> List<T> parseExcelFileToBeans(final MultipartFile xlsFile,
			final InputStream xmlStream) throws Exception {
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(xmlStream);
		final List<T> result = new ArrayList<>();
		final Map<String, Object> beans = new HashMap<>();
		beans.put("result", result);
		ReaderConfig.getInstance().setSkipErrors( true );
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes( true );
		xlsReader.read(xlsFile.getInputStream(), beans);
		return result;
	}*/
	
	public static Date convertDateHoursMinute(Date availableDate, int hoursOfDay,
			int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(availableDate);
		cal.set(Calendar.HOUR_OF_DAY, hoursOfDay);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
	public static Date getFormattedDate(String date) throws ParseException{
		Date dt1 = format.parse(date);
		return dt1;

	}
	
	public static Date formattedDate(Date date){
		Date dt1;
		try {
			dt1 = format.parse(format.format(date));
			System.out.println("testing date: "+dt1 + new Date());
			return dt1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return date;
		}
		
	}
	
	public static Date getFormattedDateHour(Date date) {
		try {
			Date dt1 = formatWithMins.parse(formatWithMins.format(date));
			System.out.println("testing date: "+dt1 + new Date());
			return dt1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return date;
		}
	}
	
	public static Date getEODTimeDate(String date) throws ParseException{
		
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(date));
		
	    c.set(Calendar.HOUR_OF_DAY,23);
	    c.set(Calendar.MINUTE,59);
	    c.set(Calendar.SECOND,59);
	    return c.getTime();
	}
	/**
	 * Save image to given URL
	 * @param imageUrl
	 * @param destinationFile
	 * @throws SMSBusinessException
	 */
	public static void saveImage(String imageUrl, String destinationFile) throws SMSBusinessException {

		InputStream is = null;
		OutputStream os = null;

		try {
			URL url = new URL(imageUrl);
			is = url.openStream();
			os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			throw new SMSBusinessException("While Saving the image error occured");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (Exception e) {
				System.out.println(" Stream Close Error"+e.getMessage());
			}
		}
	}

	/**
	 * Get Random number
	 * @param chars
	 * @param count
	 * @return
	 */
	public static String getRandomGeneratedNumber(char[] chars,int count){
		
//		char[] chars = "12345ABCDE".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < count ; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();

	}
	
	/**
	 * Get the Image File name extension
	 * @param fileName
	 * @return
	 */
	
	public static String getFileExtension(String fileName){
		String fileExtn = null;
		if (fileName != null) {
			int indx = fileName.lastIndexOf(".");
			if (indx > 0)
				fileExtn = fileName.substring(indx);
			else
				fileExtn = ".png";
		}
		return fileExtn;
	}
	
	/**
	 * Get Video File Extension
	 * @param fileName
	 * @return
	 */
	public static String getVideoFileExtension(String fileName){
		String fileExtn = null;
		if (fileName != null) {
			int indx = fileName.lastIndexOf(".");
			if (indx > 0)
				fileExtn = fileName.substring(indx);
			else
				fileExtn = ".mpg";
		}
		return fileExtn;
	}
	
	public static void writeImageFile(MultipartFile file,String imagePath) throws SMSBusinessException {
		BufferedOutputStream stream = null;
		try {
			byte[] bytes = file.getBytes();
			stream = new BufferedOutputStream(
					new FileOutputStream(new File(imagePath)));
			stream.write(bytes);
			stream.flush();
			
		} catch (Exception e) {
			throw new SMSBusinessException("Failed to upload Image",e);
		}finally {
			if(stream != null){
				try {
					stream.close();
				} catch (IOException e) {
					throw new SMSBusinessException("File Close method fails");
				}
			}
		}
	}
	
	
	public static String getAppenedFileNames(String imgPath) throws SMSBusinessException {
		StringBuilder sb = new StringBuilder();
		try {
			File file1 = new File(imgPath);
			if (null != file1) {
				File[] files = file1.listFiles();
				if (null != files) {
					for(int i=0;i<files.length;i++){
						sb.append(files[i].getName());
						if(i != files.length -1){
							sb.append("|");
						}
					}
				}
			}
		} catch (Exception e) {
			throw new SMSBusinessException(e.getMessage());
		}
		return sb.toString();
	}


	/*private static long getTimeInMilliSeconds(String time) {
		String[] stArray = time.split(":");
		Calendar cal = Calendar.getInstance();
		Date offerDate = new Date();
		cal.setTime(offerDate);

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stArray[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(stArray[1]));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}*/
	
	
	public static Date getCurrentDayEndTime(Date offerDate) {
		String time = "23:59";
		String[] stArray = time.split(":");
		Calendar cal = Calendar.getInstance();
		cal.setTime(offerDate);

		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stArray[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(stArray[1]));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return getFormattedDateHour(cal.getTime());
	}

	public static Date getFormattedDateTime(String time) {
		Date dateStartTime = null;
		try {
			dateStartTime = formatWithMins.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStartTime;
	}
	
	public static String getCurrentDayText(int weekDay){
		String day = null;
		switch (weekDay) {
		case 0:
			day = "Sunday";
			break;

		case 1:
			day = "Monday";
			break;

		case 2:
			day = "Tuesday";
			break;

		case 3:
			day = "Wednesday";
			break;

		case 4:
			day = "Thursday";
			break;

		case 5:
			day = "Friday";
			break;

		case 6:
			day = "Saturday";
			break;
		}

		return day;
	}

	public static <T> List<T> parseExcelFileToBeans(final MultipartFile xlsFile,
			final InputStream xmlStream) throws Exception {
		final XLSReader xlsReader = ReaderBuilder.buildFromXML(xmlStream);
		final List<T> result = new ArrayList<>();
		final Map<String, Object> beans = new HashMap<>();
		beans.put("result", result);
		ReaderConfig.getInstance().setSkipErrors( true );
		ReaderConfig.getInstance().setUseDefaultValuesForPrimitiveTypes( true );
		xlsReader.read(xlsFile.getInputStream(), beans);
		return result;
	}

	 public static Date getDate(String dateStr, String format) {
	        final DateFormat formatter = new SimpleDateFormat(format);
	        try {
	            return formatter.parse(dateStr);
	        } catch (ParseException e) {                
	            return null;
	        }
	 }
}
