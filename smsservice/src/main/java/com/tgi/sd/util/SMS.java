package com.tgi.sd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class SMS {
			
	private static String userId ;
	private static String password ;
	private static String senderId ;

	public static List<String> sendToSmsList = null;
	String[] smsSendToArr = {"Parent", "Parent2", "Student","Admin"};
	public SMS() {
		sendToSmsList = Arrays.asList(smsSendToArr);
	}

	public static void sendSMS(String recipientNo, String message) {
		try{
			//TODO code chages
			//userId = SystemProperties.getProperty(SMSConstants.SMS_GATEWAY_USERID);
			//password = SystemProperties.getProperty(SMSConstants.SMS_GATEWAY_PASSWORD);
			//senderId = SystemProperties.getProperty(SMSConstants.SMS_GATEWAY_SENDERID);
			String httpsURL = "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=" + userId + ":" + password
					+ "&senderID=" + senderId + "&receipientno=" + recipientNo + "&dcs=0&msgtxt=" + message + "&state=4";
			URL myurl = new URL(httpsURL);
			HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
			InputStream ins = con.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
	
			String inputLine;
	
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
			}
	
			in.close();
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*	String strUrl =  "http://api.mVaayoo.com/mvaayooapi/MessageCompose?user=krish.manic@gmail.com:jeyam123&senderID=TEST SMS&receipientno=9994712582&dcs=0&msgtxt=This is Test message&state=4 "; 
	WebRequest request = HttpWebRequest.Create(strUrl);
	HttpWebResponse response = (HttpWebResponse)request.EndGetResponse(); 
	Stream s = (Stream)response.GetResponseStream();
	StreamReader readStream = new StreamReader( s );
	string dataString = readStream.ReadToEnd();
	response.Close();
	s.Close();
	readStream.Close();*/
}
