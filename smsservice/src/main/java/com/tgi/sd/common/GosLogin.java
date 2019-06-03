package com.tgi.sd.common;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;









//import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class GosLogin {
	
	
	public static String loginToGOS(String url, String userName,String password, String encPassword,String messageType) {
		String retStr = null;
		try {
			String msg = createSOAPMessage(userName,password,encPassword,messageType);
			System.out.println(msg);
			
			DefaultHttpClient httpclient = new DefaultHttpClient();
	        HttpContext context = new BasicHttpContext();
	        HttpPost httppost = new HttpPost(url); 
	        
	        StringEntity stringentity = new StringEntity(msg,"UTF-8");
	        httppost.setEntity(stringentity);
	        httppost.addHeader("Content-Type", "text/xml; charset=UTF-8");
            httppost.addHeader("SOAPAction",url);
	        
	        HttpResponse response = httpclient.execute(httppost, context);
	        if (response != null) {
	        	System.out.println(response.toString());
		        StatusLine status = response.getStatusLine();
		        if (status.getStatusCode() == 200) {
			        HttpEntity entity=response.getEntity();
			        if (entity == null) {
			        	System.out.println("Response found...");	
			        	retStr = "No response found.";
			        }
			        else {
			        	String eStr = EntityUtils.toString(entity);
			        	System.out.println("Response string : " + eStr);
			        	retStr = getResponseMessage(eStr,messageType);
			        	System.out.println("Response : " + retStr);
			        }
		        }
	        }
	        else {
	        	System.out.println("Response is null");
	        	retStr = "Response is null.";
	        }
	        
		}
		catch (Exception e) {
			System.out.println("Exception :" + e.getMessage());
			retStr = "Could not login." + e.getMessage();
		}
        
		return retStr;
	}
	
	
	/*
	 * messageType=LOGIN, USERINFO
	 */
    private static String createSOAPMessage(String userName,String password, String encPassword,String messageType) throws Exception {
        //String serverURI = "http://iowebapi.gabrielny.com/api/Internal.php?wsdl";
    	
    	MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPHeader header = envelope.getHeader();
        SOAPBody soapBody = envelope.getBody();
        SOAPFactory soapFactory = SOAPFactory.newInstance();
        Name bodyName = null;
        if (messageType.equals("LOGIN"))
        	bodyName = soapFactory.createName("loginCheck","","http://iowebapi.gabrielny.com/api");
        else
        	bodyName = soapFactory.createName("getLDAPUserData","","http://iowebapi.gabrielny.com/api");
        SOAPBodyElement soapBodyElem = soapBody.addBodyElement(bodyName);
        //SOAPElement soapBodyElem = soapBody.addChildElement("Create", "ns1");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("loginArray");
        //String jsonArr = "{'timeA':'0.86871400 1453442916','userName':'webapi_svc','timeB':'1453442916','password':'0nlin3$ervice','timeC':'1453442916'}";
        long mstime = System.currentTimeMillis();
		long seconds = mstime / 1000;
		String jsonArr = null;
		if (messageType.equals("LOGIN"))
			jsonArr = "{\"timeA\":\"0.0 " + seconds + "\",\"userName\":\"" + userName + "\",\"timeB\":\"" + seconds + "\",\"password\":\"" + password + "\",\"timeC\":\"" + seconds + "\"}";
		else
			jsonArr = "{\"timeA\":\"0.0 " + seconds + "\",\"userName\":\"" + userName + "\",\"timeB\":\"" + seconds + "\"}";
		
        String encStr = icarusEncode(jsonArr,encPassword);
        soapBodyElem1.addTextNode(encStr);
        
      
        soapMessage.saveChanges();

        /* Print the request message */
        //System.out.print("Request SOAP Message:");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        soapMessage.writeTo(out);
        String strMsg = new String(out.toByteArray());
        
        
        return strMsg;
    }
    
    /*
     * Returns SUCCESS-<userid>
     */
    public static String getResponseMessage(String str, String messageType) {
    	String resp = null;
		try {		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(str));
			Document d1 = builder.parse(is);
			NodeList nList = d1.getElementsByTagName("Result");
			for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            resp = nNode.getTextContent();
			}
			
			if (messageType.equals("USERINFO")) {
				JSONObject jobj = new JSONObject(resp);
				if (null != jobj) {
					String uid = jobj.getJSONObject("userAccountControl").getString("0");
					System.out.println("----" + uid);
					resp = "SUCCESS-" + uid;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return resp;
    }
    
    
    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
     }
     private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
     }
     
     public static String icarusEncode(String str, String key) {
    	  String hashStr = "";
    	  key = getSha256(key);
    	  int strLen = str.length();
    	  int keyLen = key.length();
    	  int j = 0;
    	  for (int i = 0; i < strLen; i++) {
    	   //String ordStr = ord(substr($string, $i, 1));
    	   int ordStr = (int) str.charAt(i);
    	   if (j == keyLen) {
    		   j = 0;
    	   }
    	   int ordKey = (int) key.charAt(j);
    	   j++;
    	   
    	   //hash .= strrev(base_convert(dechex(ordStr + ordKey), 16, 36));
    	   //base 64 conv
    	   //String decToHex = Integer.toHexString(ordStr + ordKey);
    	   
//    	   byte[] encodedBytes = Base64.encodeBase64(decToHex.getBytes());
//    	   String encVal = new String(encodedBytes);
    	   
    	   String encVal = Integer.toString(ordStr + ordKey, 36);
    	   
    	   hashStr = hashStr + new StringBuffer(encVal).reverse().toString();
    	  }
    	  System.out.println(hashStr);
    	  return hashStr;
    	 }
}

