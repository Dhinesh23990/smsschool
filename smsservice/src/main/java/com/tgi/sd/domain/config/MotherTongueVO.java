package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class MotherTongueVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String motherTongueName;
	private String schoolId;

	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getMotherTongueName() {
		return motherTongueName;
	}

	public void setMotherTongueName(String motherTongueName) {
		this.motherTongueName = motherTongueName;
	}

}