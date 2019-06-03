package com.tgi.sd.domain;

import java.io.Serializable;


public class BulkImportStudentDetailVO  extends AuditFields implements BaseVO,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String admissionNo;
	private String dateOfJoinig;
	private String studentName;
	private String surName;
	private String gender;
	private String dateOfBirth;
	private String batch;
	private String className;
	private String section;
	private String medium;
	private String course;
	private String classId;
	private String schoolId;
	private String sectionId;
	private String parentName1;
	private String parentName2;
	private String mobileNumber;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getDateOfJoinig() {
		return dateOfJoinig;
	}
	public void setDateOfJoinig(String dateOfJoinig) {
		this.dateOfJoinig = dateOfJoinig;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentName1() {
		return parentName1;
	}
	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}
	public String getParentName2() {
		return parentName2;
	}
	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
}
