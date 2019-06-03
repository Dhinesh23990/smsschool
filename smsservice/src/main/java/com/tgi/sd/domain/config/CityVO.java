package com.tgi.sd.domain.config;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class CityVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String cityName;
	private String stateId;
	private String countryId;
	private String schoolId;

	@Transient
	private String countryName;
	
	@Transient
	private String stateName;
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
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

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	

}