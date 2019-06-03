package com.tgi.sd.domain.fms;

import com.tgi.sd.common.domain.GenericDomainObject;


public class FeeTypeVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String feeType;
	private String recurringPerYear;
	private String remDuration;
	private Boolean checked = false;
	private double amount;
	private String schoolId;
	
	
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getRecurringPerYear() {
		return recurringPerYear;
	}
	public void setRecurringPerYear(String recurringPerYear) {
		this.recurringPerYear = recurringPerYear;
	}
	public String getRemDuration() {
		return remDuration;
	}
	public void setRemDuration(String remDuration) {
		this.remDuration = remDuration;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}