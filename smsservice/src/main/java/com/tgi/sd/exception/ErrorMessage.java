package com.tgi.sd.exception;

public class ErrorMessage {
	
	private String errorCode;
	
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ErrorMessage(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public ErrorMessage() {
	}

	@Override
	public String toString() {
		return "ErrorMessage [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}

	
	

	

}
