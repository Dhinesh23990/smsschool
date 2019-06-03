package com.tgi.sd.domain.config;

import com.tgi.sd.common.domain.GenericDomainObject;


public class ExamSessionVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String examSessionName;
	private String startTime;
	private String endTime;
	private String schoolId;
	
	
	public String getExamSessionName() {
		return examSessionName;
	}
	public void setExamSessionName(String examSessionName) {
		this.examSessionName = examSessionName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	

	
}