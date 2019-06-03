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

public class AttendanceVO extends GenericDomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8559953399436900264L;
	
	//@NotNull(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
	private String studentId;
	
	//@NotNull(message = ErrorConstants.DOB_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.DOB_IS_REQUIRED)
	private Date day;
	private Boolean fullDay = Boolean.FALSE;
	private Boolean morning = Boolean.FALSE;
	private Boolean afternoon = Boolean.FALSE;
	//@NotNull(message = ErrorConstants.ADMISSIONNUMBER_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.ADMISSIONNUMBER_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.ADMISSIONNUMBER_INVALID_LENGTH)	
	private String note;
	private long attendancePercenatge;
	private int presentDays;
	private int totalDays;

	//@NotNull(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
	private String standard;
	
	//@NotNull(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.STUDENTNAME_IS_REQUIRED)
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
