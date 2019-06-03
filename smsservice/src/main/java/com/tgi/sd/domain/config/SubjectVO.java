package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class SubjectVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String subjectName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}


	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	
}