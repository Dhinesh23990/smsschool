package com.tgi.sd.domain.config;

import java.util.List;

import com.tgi.sd.common.domain.GenericDomainObject;


public class SubjectSetVO extends GenericDomainObject {

	private static final long serialVersionUID = 1L;
	
	private String subjectSetName;
	private List<SubjectVO> subjectList;
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

	public String getSubjectSetName() {
		return subjectSetName;
	}

	public void setSubjectSetName(String subjectSetName) {
		this.subjectSetName = subjectSetName;
	}

	public List<SubjectVO> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<SubjectVO> subjectList) {
		this.subjectList = subjectList;
	}

	
}