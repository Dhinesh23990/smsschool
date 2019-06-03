package com.tgi.sd.domain;

import java.io.Serializable;
import java.util.Date;

public class FilterVO extends AuditFields implements BaseVO,Serializable {

	private static final long serialVersionUID = 1L;
	private String eventName;
	private String schoolId;
	private Date eventStartDate;
	private Date eventEndDate;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public Date getEventStartDate() {
		return eventStartDate;
	}
	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}
	public Date getEventEndDate() {
		return eventEndDate;
	}
	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}
	
}