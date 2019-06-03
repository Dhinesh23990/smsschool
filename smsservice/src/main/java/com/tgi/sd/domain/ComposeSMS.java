package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.List;

public class ComposeSMS {

	private String distributionType;
	private String templateType;
	private String gender;
	private String standard;
	private List<String> className = new ArrayList<>();
	private String section;
	private List<String> sectionList = new ArrayList<>();
	private String message;
	private List<String> sendTo = new ArrayList<>();
	private List<String> receiverIds = new ArrayList<>();
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the distributionType
	 */
	public String getDistributionType() {
		return distributionType;
	}
	/**
	 * @param distributionType the distributionType to set
	 */
	public void setDistributionType(String distributionType) {
		this.distributionType = distributionType;
	}
	/**
	 * @return the templateType
	 */
	public String getTemplateType() {
		return templateType;
	}
	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}
	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}
	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the sendTo
	 */
	
	
	public List<String> getSendTo() {
		return sendTo;
	}

	public List<String> getReceiverIds() {
		return receiverIds;
	}

	public void setReceiverIds(List<String> receiverIds) {
		this.receiverIds = receiverIds;
	}

	public void setSendTo(List<String> sendTo) {
		this.sendTo = sendTo;
	}

	public List<String> getClassName() {
		return className;
	}

	public void setClassName(List<String> className) {
		this.className = className;
	}

	public List<String> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<String> sectionList) {
		this.sectionList = sectionList;
	}
	
	

}
