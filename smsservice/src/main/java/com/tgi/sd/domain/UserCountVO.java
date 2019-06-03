package com.tgi.sd.domain;

import java.io.Serializable;

public class UserCountVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3833835659155541602L;

	private String id;
	
	private String studentCount;

	private String adminCount;

	private String teacherCount;

	private String parentCount;
	
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the studentCount
	 */
	public String getStudentCount() {
		return studentCount;
	}

	/**
	 * @param studentCount
	 *            the studentCount to set
	 */
	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}

	/**
	 * @return the adminCount
	 */
	public String getAdminCount() {
		return adminCount;
	}

	/**
	 * @param adminCount
	 *            the adminCount to set
	 */
	public void setAdminCount(String adminCount) {
		this.adminCount = adminCount;
	}

	/**
	 * @return the teacherCount
	 */
	public String getTeacherCount() {
		return teacherCount;
	}

	/**
	 * @param teacherCount
	 *            the teacherCount to set
	 */
	public void setTeacherCount(String teacherCount) {
		this.teacherCount = teacherCount;
	}

	/**
	 * @return the parentCount
	 */
	public String getParentCount() {
		return parentCount;
	}

	/**
	 * @param parentCount
	 *            the parentCount to set
	 */
	public void setParentCount(String parentCount) {
		this.parentCount = parentCount;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
