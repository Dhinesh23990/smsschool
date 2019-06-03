package com.tgi.sd.common.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Jegatheesan
 * 
 * */

public class JMRuntimeException extends Throwable {
	
	private static final long serialVersionUID = 1L;

	private String errorConstant;

	Map<String, String> exceptionMap = new HashMap<String, String>();
	
	public JMRuntimeException(final String msg) {
		super(msg);
		this.errorConstant = msg;
	}
	
	public JMRuntimeException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.errorConstant = msg;

	}

	
	public JMRuntimeException(final Throwable cause) {
		super("", cause);

	}
	
	public String getErrorConstant() {
		return errorConstant;
	}

	/**
	 * @param errorConstant
	 *            the errorConstant to set
	 */
	public void setErrorConstant(String errorConstant) {
		this.errorConstant = errorConstant;
	}
	
	public String getShortMessage() {
		return errorConstant;
	}
	
	public Map<String, String> getExceptionMap() {
		return exceptionMap;
	}
	
	public void addRuntimeDataToExceptionMap(String key, String value) {
		exceptionMap.put(key, value);
	}
	
	public String getErrorStackTrace() {
		String errorStackStr = "";

//		if (getCause() != null) {
//
//			String[] causes = ExceptionUtils.getRootCauseStackTrace(getCause());
//
//			if (causes != null && causes.length > 0) {
//
//				for (String errorCause : causes) {
//					errorStackStr = errorStackStr
//							+ "<br>---------------------------------------------------------------<br>"
//							+ errorCause
//							+ "<br>"
//							+ "-------------------------------------------------------------------<br>";
//				}
//
//			}
//		} else {
//			errorStackStr = getShortMessage();
//		}

		return errorStackStr;
	}
}
