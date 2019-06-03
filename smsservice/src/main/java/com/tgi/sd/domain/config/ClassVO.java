package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class ClassVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String className;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	

}