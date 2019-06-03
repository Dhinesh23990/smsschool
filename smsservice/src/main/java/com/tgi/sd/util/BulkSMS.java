package com.tgi.sd.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
public final class BulkSMS {
final static String URL_HOST="https://www.smsgatewayhub.com";
public static String SMSSender(String apikey,String channel,String senderid,String route,String mobile,String message)
{
String strTemp = "";
try {
// Construct The Post Data
String data = "/api/mt/SendSMS?"+
"APIKey=" + URLEncoder.encode(apikey, "UTF-8") +
"&senderid=" + URLEncoder.encode(senderid, "UTF-8") +
"&channel=" + URLEncoder.encode(channel, "UTF-8") +
"&DCS=" + URLEncoder.encode("0", "UTF-8") +
"&flashsms=" + URLEncoder.encode("0", "UTF-8") +
"&number=" + URLEncoder.encode(mobile, "UTF-8") +
"&text=" + URLEncoder.encode(message, "UTF-8") +
"&route=" + URLEncoder.encode(route, "UTF-8");
URL url = new URL(URL_HOST+data);
BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
String line;
while ((line = br.readLine()) != null) {
strTemp += line;
}
} catch (Exception ex) {
ex.printStackTrace();
}
return strTemp;
}
public static void main(String[] args) {
String response = SMSSender("lWBebFJQ4UiM30LUVgDk4A","1","TESTIN", "TRANS", "9486556611", "jjjjjjjjjjjjdefault msg test");
System.out.println(response);
}
}