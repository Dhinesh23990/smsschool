package com.tgi.sd.domain.fms;

import org.springframework.data.annotation.Transient;

import com.tgi.sd.common.domain.GenericDomainObject;


public class PaymentModeTypeVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private int recId;
	private String paymentModeId;
	private String feeTypeId;
	private String feeType;
	private String recurringPerYear;
	private String remDuration;
	private Boolean checked = false;
	private double amount;
	private String schoolId;
	
	@Transient
	private double balanceAmount;
	
	
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	public String getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
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
	public String getPaymentModeId() {
		return paymentModeId;
	}
	public void setPaymentModeId(String paymentModeId) {
		this.paymentModeId = paymentModeId;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
}