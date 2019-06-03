package com.tgi.sd.domain.config;

import java.io.Serializable;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.AuditFields;
import com.tgi.sd.domain.BaseVO;

public class CountryVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;

	private String countryName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}