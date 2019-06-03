package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class ExamVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String examName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	
}