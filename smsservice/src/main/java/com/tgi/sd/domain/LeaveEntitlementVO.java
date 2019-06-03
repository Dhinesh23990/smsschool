package com.tgi.sd.domain;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class LeaveEntitlementVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String leaveMasterId;
	private String staffId;
	private Integer leaveCount;
	private String leavePeriodId;
	private String schoolId;
	
	@Transient
	private String leaveMasterName;
	@Transient
	private String leavePeriodName;
	@Transient
	private String staffName;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getLeaveMasterId() {
		return leaveMasterId;
	}

	public void setLeaveMasterId(String leaveMasterId) {
		this.leaveMasterId = leaveMasterId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public String getLeavePeriodId() {
		return leavePeriodId;
	}

	public void setLeavePeriodId(String leavePeriodId) {
		this.leavePeriodId = leavePeriodId;
	}

	public String getLeaveMasterName() {
		return leaveMasterName;
	}

	public void setLeaveMasterName(String leaveMasterName) {
		this.leaveMasterName = leaveMasterName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getLeavePeriodName() {
		return leavePeriodName;
	}

	public void setLeavePeriodName(String leavePeriodName) {
		this.leavePeriodName = leavePeriodName;
	}
	
	

}