package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class EducationalVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String educationName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	
}