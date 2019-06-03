/**
 * 
 */
package com.tgi.sd.domain;

import com.tgi.sd.common.domain.GenericDomainObject;


public class CountVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private long boysCount;	
	private long girlssCount;	
	private long totalCount;	
 
	private String className;
	private String section;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	public long getBoysCount() {
		return boysCount;
	}

	public void setBoysCount(long boysCount) {
		this.boysCount = boysCount;
	}

	public long getGirlssCount() {
		return girlssCount;
	}

	public void setGirlssCount(long girlssCount) {
		this.girlssCount = girlssCount;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	

}
