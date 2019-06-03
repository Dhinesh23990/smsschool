package com.tgi.sd.domain;

import com.tgi.sd.common.domain.GenericDomainObject;

public class LookupVO extends GenericDomainObject {
	
	
	private static final long serialVersionUID = 1284752510325203198L;
	
	private String typeId;
	private String type;
	private String name;
	private String value;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
