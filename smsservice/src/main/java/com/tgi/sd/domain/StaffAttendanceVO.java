package com.tgi.sd.domain;

import java.util.Date;
import com.tgi.sd.common.domain.GenericDomainObject;


public class StaffAttendanceVO extends GenericDomainObject {

	
	private static final long serialVersionUID = 8559953399436900264L;

	private String staffId;
	private Date day;
	private Boolean fullDay = Boolean.FALSE;
	private Boolean morning = Boolean.FALSE;
	private Boolean afternoon = Boolean.FALSE;
	private String note;
	private String dayAfter;
	private String standard;
	private String admissionNumber;
	private String selectLeaveMasterId;
	
	private double attendancePercenatge;
	private int presentDays;
	private int totalDays;
	
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
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
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

	public String getSelectLeaveMasterId() {
		return selectLeaveMasterId;
	}

	public void setSelectLeaveMasterId(String selectLeaveMasterId) {
		this.selectLeaveMasterId = selectLeaveMasterId;
	}

	public String getDayAfter() {
		return dayAfter;
	}

	public void setDayAfter(String dayAfter) {
		this.dayAfter = dayAfter;
	}

	public String getAdmissionNumber() {
		return admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public double getAttendancePercenatge() {
		return attendancePercenatge;
	}

	public void setAttendancePercenatge(double attendancePercenatge) {
		this.attendancePercenatge = attendancePercenatge;
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
