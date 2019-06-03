package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class CasteVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String casteName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

}