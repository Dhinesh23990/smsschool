package com.tgi.sd.domain;

import java.util.Date;
import org.springframework.data.annotation.Transient;
import com.tgi.sd.common.domain.GenericDomainObject;

public class UserVO  extends GenericDomainObject {
	
	private static final long serialVersionUID = 339520655678716126L; 
	
	private String userName;
	private String password;
	private String customerId;
	private Date lastLogin;
	private String contactEmail;
	private String roleId;
	private String userStatusCode;
	private String firstName;
	private String lastName;
	private String schoolId;
	
	//private List<BlobDocumentMappingVO> userImage =  new ArrayList<BlobDocumentMappingVO>();
	@Transient
	transient TokenVO tokenVO;
	@Transient
	transient RoleVO roleVO;
	@Transient
	transient String imageFileNames;
	@Transient
	String address1;
	@Transient
	String address2;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public TokenVO getTokenVO() {
		return tokenVO;
	}
	public void setTokenVO(TokenVO tokenVO) {
		this.tokenVO = tokenVO;
	}
	public RoleVO getRoleVO() {
		return roleVO;
	}
	public void setRoleVO(RoleVO roleVO) {
		this.roleVO = roleVO;
	}
	public String getImageFileNames() {
		return imageFileNames;
	}
	public void setImageFileNames(String imageFileNames) {
		this.imageFileNames = imageFileNames;
	}
	public String getUserStatusCode() {
		return userStatusCode;
	}

	public void setUserStatusCode(String userStatusCode) {
		this.userStatusCode = userStatusCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

    

	/*
	public List<BlobDocumentMappingVO> getUserImage() {
		return userImage;
	}

	public void setUserImage(List<BlobDocumentMappingVO> userImage) {
		this.userImage = userImage;
	}
	*/
}
