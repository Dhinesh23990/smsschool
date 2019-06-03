package com.tgi.sd.domain.config;

import java.util.List;
import com.tgi.sd.common.domain.GenericDomainObject;

public class GradeLimitVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String gradeName;
	private double minMarks;
	private double maxMarks;
	private String status;
	private String schoolId;
	private List<String> classIds;
	
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public double getMinMarks() {
		return minMarks;
	}
	public void setMinMarks(double minMarks) {
		this.minMarks = minMarks;
	}
	public double getMaxMarks() {
		return maxMarks;
	}
	public void setMaxMarks(double maxMarks) {
		this.maxMarks = maxMarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public List<String> getClassIds() {
		return classIds;
	}
	public void setClassIds(List<String> classIds) {
		this.classIds = classIds;
	}

	
	
}