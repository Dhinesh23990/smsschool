/**
 * 
 */
package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

/**
 * @author SGSAuthour
 *
 */
public class StaffAttendanceSummaryVO {

	private String id;
	private String staffId;
	private Date day;
	private Boolean fullDay;
	private Boolean morning;
	private Boolean afternoon;
	private String dayAfter;
	private String note;	
	private String selectLeaveMasterId;
	private String standard;
	private String admissionNumber;
	private String staffName;
 
	private String section;
	private String schoolId;
	
	private double attendancePercenatge;
	private double presentDays;
	private double totalDays;
	
	private List<LeaveMasterVO> leaveMasterVO = new ArrayList<LeaveMasterVO>();

	@Transient
	private String leaveMasterShortName;
	
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
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	public String getDayAfter() {
		return dayAfter;
	}

	public void setDayAfter(String dayAfter) {
		this.dayAfter = dayAfter;
	}

	public List<LeaveMasterVO> getLeaveMasterVO() {
		return leaveMasterVO;
	}

	public void setLeaveMasterVO(List<LeaveMasterVO> leaveMasterVO) {
		this.leaveMasterVO = leaveMasterVO;
	}

	public String getSelectLeaveMasterId() {
		return selectLeaveMasterId;
	}

	public void setSelectLeaveMasterId(String selectLeaveMasterId) {
		this.selectLeaveMasterId = selectLeaveMasterId;
	}

	public String getLeaveMasterShortName() {
		return leaveMasterShortName;
	}

	public void setLeaveMasterShortName(String leaveMasterShortName) {
		this.leaveMasterShortName = leaveMasterShortName;
	}

	public double getAttendancePercenatge() {
		return attendancePercenatge;
	}

	public void setAttendancePercenatge(double attendancePercenatge) {
		this.attendancePercenatge = attendancePercenatge;
	}

	public double getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(double presentDays) {
		this.presentDays = presentDays;
	}

	public double getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(double totalDays) {
		this.totalDays = totalDays;
	}

}
