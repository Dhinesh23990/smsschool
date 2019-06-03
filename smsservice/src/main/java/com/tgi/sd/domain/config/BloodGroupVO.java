package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;

public class BloodGroupVO extends GenericDomainObject  {

	private static final long serialVersionUID = 1L;
	
	private String bloodgroupName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBloodgroupName() {
		return bloodgroupName;
	}

	public void setBloodgroupName(String bloodgroupName) {
		this.bloodgroupName = bloodgroupName;
	}

	
	

	}