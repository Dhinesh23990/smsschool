package com.tgi.sd.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpTestService {

	@Test
	public void saveTestDetails(){
		
		try {
			WebConversation wc = new WebConversation();

			String url = "http://localhost:8080/FlynnService/testCreateNewService";
			WebRequest request = null;
	
			String requestVo = "{\"RequestVO\":{\"testVO\":{\"name\":\"Almaz by Momo\",\"address\":\"main road\",\"pincode\":\"625014\"}}}";
			
			request = new PostMethodWebRequest(url);
			request.setParameter("RequestVO", requestVo);
			
			WebResponse response = wc.getResponse(request);
			String responseString = response.getText();
			assertNotNull(responseString);
			System.out.println(responseString);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception: " + e);
		}
	}
	
}
