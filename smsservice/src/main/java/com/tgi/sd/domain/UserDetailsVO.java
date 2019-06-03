package com.tgi.sd.domain;

import java.util.Date;

public class UserDetailsVO 
{
	private String id;
	
	private String firstName;

	private String lastName;

	private String userName;
	
	private String roleName;
	
	private String customerId;
	
	private String contactEmail;
	
	private String token;

	private Date tokenExpirationDt;
	
	private String userType;
	
	private String candidateId;
	
	private String interViewerId;
	
	private String jobId;
	
	private String isDepartmentHead;
	
	private RoleVO roleVO;
	private RoleAccessVO jobPermissions;
	private RoleAccessVO interviewerPermissions;
	private RoleAccessVO candidatePermissions;
	private RoleAccessVO feedbackPermissions;
	private RoleAccessVO historyPermissions;
	
	private String profileImage;
	
	private String companyLogo;
	
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tokenExpirationDt
	 */
	public Date getTokenExpirationDt() {
		return tokenExpirationDt;
	}

	/**
	 * @param tokenExpirationDt the tokenExpirationDt to set
	 */
	public void setTokenExpirationDt(Date tokenExpirationDt) {
		this.tokenExpirationDt = tokenExpirationDt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getInterViewerId() {
		return interViewerId;
	}

	public void setInterViewerId(String interViewerId) {
		this.interViewerId = interViewerId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public RoleVO getRoleVO() {
		return roleVO;
	}

	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}

	public RoleAccessVO getJobPermissions() {
		return jobPermissions;
	}

	public void setJobPermissions(RoleAccessVO jobPermissions) {
		this.jobPermissions = jobPermissions;
	}

	public RoleAccessVO getInterviewerPermissions() {
		return interviewerPermissions;
	}

	public void setInterviewerPermissions(RoleAccessVO interviewerPermissions) {
		this.interviewerPermissions = interviewerPermissions;
	}

	public RoleAccessVO getCandidatePermissions() {
		return candidatePermissions;
	}

	public void setCandidatePermissions(RoleAccessVO candidatePermissions) {
		this.candidatePermissions = candidatePermissions;
	}

	public RoleAccessVO getFeedbackPermissions() {
		return feedbackPermissions;
	}

	public void setFeedbackPermissions(RoleAccessVO feedbackPermissions) {
		this.feedbackPermissions = feedbackPermissions;
	}

	public RoleAccessVO getHistoryPermissions() {
		return historyPermissions;
	}

	public void setHistoryPermissions(RoleAccessVO historyPermissions) {
		this.historyPermissions = historyPermissions;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getIsDepartmentHead() {
		return isDepartmentHead;
	}

	public void setIsDepartmentHead(String isDepartmentHead) {
		this.isDepartmentHead = isDepartmentHead;
	}
	
	
	
}
