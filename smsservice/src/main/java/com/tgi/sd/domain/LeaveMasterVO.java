package com.tgi.sd.domain;

import com.tgi.sd.common.domain.GenericDomainObject;


public class LeaveMasterVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String leaveName;
	private String leaveShortName;
	private Integer leaveCount;
	private String leaveType;
	private String leaveFreq;
	private boolean carryNextCycle;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getLeaveShortName() {
		return leaveShortName;
	}

	public void setLeaveShortName(String leaveShortName) {
		this.leaveShortName = leaveShortName;
	}

	public Integer getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(Integer leaveCount) {
		this.leaveCount = leaveCount;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveFreq() {
		return leaveFreq;
	}

	public void setLeaveFreq(String leaveFreq) {
		this.leaveFreq = leaveFreq;
	}

	public boolean isCarryNextCycle() {
		return carryNextCycle;
	}

	public void setCarryNextCycle(boolean carryNextCycle) {
		this.carryNextCycle = carryNextCycle;
	}

	
}