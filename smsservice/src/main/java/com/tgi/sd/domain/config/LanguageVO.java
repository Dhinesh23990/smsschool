package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;

public class LanguageVO extends GenericDomainObject  {

	private static final long serialVersionUID = 1L;
	
	private String languageName;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}