package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class SectionVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;

	private String sectionName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	}