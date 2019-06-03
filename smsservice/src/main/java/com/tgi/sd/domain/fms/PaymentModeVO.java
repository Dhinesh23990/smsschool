package com.tgi.sd.domain.fms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.StudentVO;


public class PaymentModeVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String feeConfigurationId;
	private String payName;
	private String schoolId;
	private String studentId;
	private String paymentType;
	private String amount;
	private String pending;
	private String status;
	private String term;
	private String classId;
	private String batchId;
	private String bankName;
	private String chequeNo;
	private String ddNo;
	private String cardTransactionId;
	private String paymentDate;
	
	private List<PaymentModeTypeVO> feeTypes;
	
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
	public List<PaymentModeTypeVO> getFeeTypes() {
		return feeTypes;
	}
	public void setFeeTypes(List<PaymentModeTypeVO> feeTypes) {
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
	public String getPending() {
		return pending;
	}
	public void setPending(String pending) {
		this.pending = pending;
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
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
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
	public String getCardTransactionId() {
		return cardTransactionId;
	}
	public void setCardTransactionId(String cardTransactionId) {
		this.cardTransactionId = cardTransactionId;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getChequeNo() {
		return chequeNo;
	}
	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	public String getDdNo() {
		return ddNo;
	}
	public void setDdNo(String ddNo) {
		this.ddNo = ddNo;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
    
	
	
}