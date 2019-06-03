package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tgi.sd.exception.ResponseErrors;

public class ResponseVO {
	
	public static final String Ok = "Ok";

	public static final String Error = "Error";
	
	private List<ResponseErrors> errors;
	
	private String statusFlag;
	
	private String errorMsg;
	
	private Map<String, Object> responseVO = new HashMap<String, Object>();
	
	public final Map<String, Object> getResponseVO() {
		return responseVO;
	}

	public final void setResponseVO(
			final Map<String, Object> responseVO) {
		this.responseVO = responseVO;
	}	

	public final void addObject(final Object obj, final String key) {
		responseVO.put(key, obj);
	}

	public final Object getObject(final String key) {
		return this.responseVO.get(key);
	}
	
	public void addAllObject(Map<String, Object> mapObj) {
		this.responseVO.putAll(mapObj);
	}
	
	public final void clearParamMap(final String key) {
		responseVO.remove(key);
	}
	
	public final void setErrors(final List<ResponseErrors> errors) {
		this.errors = errors;
	}

	public final List<ResponseErrors> getErrors() {
		if (errors == null) {
			errors = new ArrayList<ResponseErrors>();
		}

		return errors;
	}
	
	public final void setStatusFlag(final String statusFlag) {
		this.statusFlag = statusFlag;
	}

	public final String getStatusFlag() {
		return statusFlag;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
