package com.tgi.sd.common.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.RequestVO;
import com.tgi.sd.domain.ResponseVO;

/**
 * 
 * @author Jegatheesan
 * 
 * */

public class BaseService {
	
	
	public GenericDomainObject parseVoFromRequest(String requestString,Class className) {
		GenericDomainObject retVal = null;
		
		try {
			Gson gson = new Gson();
			
			JSONObject ja = new JSONObject(requestString);
			JSONArray array = ja.getJSONObject("RequestVO").names();

			if (array == null || array.length() == 0) {
				return null;
			}
			
			for (int i = 0; i < array.length(); i++) {
				
				String name = array.get(i).toString();
				String value = ja.getJSONObject("RequestVO").getString(name);
				
				retVal = (GenericDomainObject) gson.fromJson(value,className);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return retVal;
	}	
	
	public ResponseVO createServiceResponse(final Map<String, Object> resObjects) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatusFlag(ResponseVO.Ok);
		Set<String> keys = resObjects.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object obj = resObjects.get(key);
			responseVO.addObject(obj, key);
		}

		return responseVO;
	}
	
	public ResponseVO createServiceResponseError(final Map<String, Object> resObjects,String msg) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatusFlag(ResponseVO.Error);
		responseVO.addObject(msg,"ErrorMessage");
		Set<String> keys = resObjects.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Object obj = resObjects.get(key);
			responseVO.addObject(obj, key);
		}

		return responseVO;
	}

}
