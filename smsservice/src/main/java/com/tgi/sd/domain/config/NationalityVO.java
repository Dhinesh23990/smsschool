package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class NationalityVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String nationalityName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	
	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

}