package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class SubjectTypeVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String subjectTypeName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSubjectTypeName() {
		return subjectTypeName;
	}

	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}

}