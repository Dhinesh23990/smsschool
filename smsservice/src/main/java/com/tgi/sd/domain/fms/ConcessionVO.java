package com.tgi.sd.domain.fms;

import com.tgi.sd.common.domain.GenericDomainObject;


public class ConcessionVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String concessionName;
	private String schoolId;
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getConcessionName() {
		return concessionName;
	}
	public void setConcessionName(String concessionName) {
		this.concessionName = concessionName;
	}

	
}