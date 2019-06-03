package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class MediumVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String mediumName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

}