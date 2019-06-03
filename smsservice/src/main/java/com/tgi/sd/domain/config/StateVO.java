package com.tgi.sd.domain.config;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;

public class StateVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String stateName;
	private String countryId;
	private String schoolId;
	
	@Transient
	private String countryName;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	
}