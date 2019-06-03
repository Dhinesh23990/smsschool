package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;

public class CourseVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	

}