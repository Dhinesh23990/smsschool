package com.tgi.sd.domain;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class LeavePeriodVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String periodName;
	private String leaveMasterId;
	private Integer leaveCount;
	private String startDate;
	private String endDate;
	private String schoolId;
	
	@Transient
	private String leaveMasterName;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getLeaveMasterId() {
		return leaveMasterId;
	}

	public void setLeaveMasterId(String leaveMasterId) {
		this.leaveMasterId = leaveMasterId;
	}

	public Integer getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getLeaveMasterName() {
		return leaveMasterName;
	}

	public void setLeaveMasterName(String leaveMasterName) {
		this.leaveMasterName = leaveMasterName;
	}

}