package com.tgi.sd.service.base;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tgi.sd.common.domain.DomainObject;
import com.tgi.sd.domain.BaseVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.exception.SMSBusinessException;

public class SMSBaseService {
	
	
	public DomainObject parseObjectFromRequest(String requestString,Class<?> className) throws SMSBusinessException {
		DomainObject retVal = null;
		
		try {
			Gson gson = new Gson();
			gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();

			JSONObject ja = new JSONObject(requestString);
			JSONArray array = ja.getJSONObject("SMSRequest").names();

			if (array == null || array.length() == 0) {
				throw new SMSBusinessException("Request Object is empty");
			}
			
			for (int i = 0; i < array.length(); i++) {
				
				String name = (String) array.get(i);
				String value = ja.getJSONObject("SMSRequest").getString(name);
				
				retVal = (DomainObject) gson.fromJson(value,className);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new SMSBusinessException("While parsing request error",e);
		}
		
		return retVal;
	}	
	
	@SuppressWarnings("rawtypes")
	public ResponseVO createSuccessResponseVO(final Map<String, Object> resObjects) {
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
	
	public ResponseVO createErrorResponseVO(String errMsg){
		ResponseVO responseVO = new ResponseVO();
		responseVO.setStatusFlag(ResponseVO.Error);
		responseVO.setErrorMsg(errMsg);
		return responseVO;
	}

}
