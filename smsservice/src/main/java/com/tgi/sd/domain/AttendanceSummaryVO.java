/**
 * 
 */
package com.tgi.sd.domain;

import java.util.Date;

import com.tgi.sd.common.domain.GenericDomainObject;

/**
 * @author SGSAuthour
 *
 */
public class AttendanceSummaryVO extends GenericDomainObject {

	private String studentId;
 
	private Date day;
	private Boolean fullDay;
	private Boolean morning;
	private Boolean afternoon;
 	
	private String note;
	private long attendancePercenatge;
	private int presentDays;
	private int totalDays;
 
	private String standard;
	private String admissionNumber;
	private String studentName;
 
	private String section;
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
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Boolean getFullDay() {
		return fullDay;
	}
	public void setFullDay(Boolean fullDay) {
		this.fullDay = fullDay;
	}
	public Boolean getMorning() {
		return morning;
	}
	public void setMorning(Boolean morning) {
		this.morning = morning;
	}
	public Boolean getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(Boolean afternoon) {
		this.afternoon = afternoon;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public long getAttendancePercenatge() {
		return attendancePercenatge;
	}

	public void setAttendancePercenatge(long attendancePercenatge) {
		this.attendancePercenatge = attendancePercenatge;
	}

	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	public int getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(int presentDays) {
		this.presentDays = presentDays;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}
	

}
