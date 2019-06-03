package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.List;
import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;


public class NotificationVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String subject;
	private String message;
	private String schoolId;
	/*private Date startDate;
	private Date endDate;*/
	private List<String> classIds;
	private List<String> studentIds;
	private List<BlobDocumentMappingVO> notificationImage =  new ArrayList<BlobDocumentMappingVO>();

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/*public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
*/
	public List<String> getClassIds() {
		return classIds;
	}

	public void setClassIds(List<String> classIds) {
		this.classIds = classIds;
	}

	public List<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}

	public List<BlobDocumentMappingVO> getNotificationImage() {
		return notificationImage;
	}

	public void setNotificationImage(List<BlobDocumentMappingVO> notificationImage) {
		this.notificationImage = notificationImage;
	}

	
}