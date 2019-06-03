package com.tgi.sd.domain.config;

import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;


public class BatchConfigurationVO extends GenericDomainObject {


	private static final long serialVersionUID = 1L;
	
	private String batchId;
	private String mediumId;
	private String sectionId;
	private String courseId;
	private String classId;
	private String classTeacherId;
	private String subjectSetId;
	private boolean gradeSystem;
	private String schoolId;
	
	@Transient
	private String batchName;
	@Transient
	private String mediumName;
	@Transient
	private String sectionName;
	@Transient
	private String courseName;
	@Transient
	private String className;
	@Transient
	private String classTeacherName;
	

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getMediumId() {
		return mediumId;
	}

	public void setMediumId(String mediumId) {
		this.mediumId = mediumId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getClassTeacherId() {
		return classTeacherId;
	}

	public void setClassTeacherId(String classTeacherId) {
		this.classTeacherId = classTeacherId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSubjectSetId() {
		return subjectSetId;
	}

	public void setSubjectSetId(String subjectSetId) {
		this.subjectSetId = subjectSetId;
	}

	public boolean isGradeSystem() {
		return gradeSystem;
	}

	public void setGradeSystem(boolean gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassTeacherName() {
		return classTeacherName;
	}

	public void setClassTeacherName(String classTeacherName) {
		this.classTeacherName = classTeacherName;
	}
	
}