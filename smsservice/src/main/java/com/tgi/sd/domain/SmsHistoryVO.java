package com.tgi.sd.domain;

import com.tgi.sd.common.domain.GenericDomainObject;

public class SmsHistoryVO extends GenericDomainObject {


	private static final long serialVersionUID = 6634489642107985179L;

	
	//@NotNull(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
   // @Length(max=50,message = ErrorConstants.STAFFNAME_INVALID_LENGTH)
	private String studentId;
	
	private String admissionNumber;
	
	private String studentName;
	
	private String standard;
	
	private String section;
	
	private String mobileNumber;
	
	private String message;
	
	private String smsLogId;
	
	private String status;

	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the admissionNumber
	 */
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	/**
	 * @param admissionNumber the admissionNumber to set
	 */
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getSmsLogId() {
		return smsLogId;
	}

	public void setSmsLogId(String smsLogId) {
		this.smsLogId = smsLogId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

}
