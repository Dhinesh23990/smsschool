package com.tgi.sd.domain.fms;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.StudentVO;


public class PaymentVO extends GenericDomainObject{

	private static final long serialVersionUID = 1L;
	
	private String feeConfigurationId;
	private String paymentType;
	private String amount;
	private String paymentStatus;
	private String status;
	private String term;	
	private String batchId;
	private String classId;
	private String sectionId;
	private String studentId;
	private String schoolId;

	private List<FeeTypeVO> feeTypes = new ArrayList<FeeTypeVO>();
	@Transient
	private StudentVO studentVO = new StudentVO();
	
	
	public String getSchoolId() {
		return schoolId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public List<FeeTypeVO> getFeeTypes() {
		return feeTypes;
	}
	public void setFeeTypes(List<FeeTypeVO> feeTypes) {
		this.feeTypes = feeTypes;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	public StudentVO getStudentVO() {
		return studentVO;
	}
	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getFeeConfigurationId() {
		return feeConfigurationId;
	}
	public void setFeeConfigurationId(String feeConfigurationId) {
		this.feeConfigurationId = feeConfigurationId;
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

	
	
}