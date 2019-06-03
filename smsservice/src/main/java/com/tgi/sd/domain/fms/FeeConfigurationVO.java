package com.tgi.sd.domain.fms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class FeeConfigurationVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String batchId;
	private String classId;
	private String sectionId;
	private String term;
	private String feeTypeId;
	private String feeComponentId;
	private double amount;
	private Date feeStartDate;
	private Date feeReminderDate;
	private String feeCategory;
	private String schoolId;
	private String studentId;
	
	private List<FeeConfigurationTypeVO> feeTypes;
	
	@Transient
	private String feeTypeName;
	
	@Transient
	private String batchName;
	
	@Transient
	private String feeComponentName;
	
	@Transient
	private String className;
	
	@Transient
	private String sectionName;
	
	@Transient
	private Double pendingAmount = 0.0;	
	
	
	@Transient
	private Double paidAmount = 0.0;
	
	@Transient
	private String status;


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getFeeTypeId() {
		return feeTypeId;
	}
	public void setFeeTypeId(String feeTypeId) {
		this.feeTypeId = feeTypeId;
	}
	public String getFeeComponentId() {
		return feeComponentId;
	}
	public void setFeeComponentId(String feeComponentId) {
		this.feeComponentId = feeComponentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getFeeStartDate() {
		return feeStartDate;
	}
	public void setFeeStartDate(Date feeStartDate) {
		this.feeStartDate = feeStartDate;
	}
	public Date getFeeReminderDate() {
		return feeReminderDate;
	}
	public void setFeeReminderDate(Date feeReminderDate) {
		this.feeReminderDate = feeReminderDate;
	}
	public String getFeeCategory() {
		return feeCategory;
	}
	public void setFeeCategory(String feeCategory) {
		this.feeCategory = feeCategory;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getFeeTypeName() {
		return feeTypeName;
	}
	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}
	public String getFeeComponentName() {
		return feeComponentName;
	}
	public void setFeeComponentName(String feeComponentName) {
		this.feeComponentName = feeComponentName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public List<FeeConfigurationTypeVO> getFeeTypes() {
		return feeTypes;
	}
	public void setFeeTypes(List<FeeConfigurationTypeVO> feeTypes) {
		this.feeTypes = feeTypes;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Double getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
	
}