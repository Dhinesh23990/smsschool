package com.tgi.sd.service;

public class FileUploadError {
	
	private String Filename;
	
	private String errorType;

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	

}
