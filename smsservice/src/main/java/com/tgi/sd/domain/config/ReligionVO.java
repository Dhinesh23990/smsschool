package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class ReligionVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String religionName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

}