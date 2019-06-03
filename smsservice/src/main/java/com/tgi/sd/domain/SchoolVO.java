/**
 * 
 */
package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;

/**
 * @author SGSAuthour
 *
 */

public class SchoolVO  extends GenericDomainObject {

	private static final long serialVersionUID = -3006094917117374169L;
	
	private String userOid;
	private String schoolName;
	private String userName;
	private String password;
	private String schoolCode;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String contactEmailId;
	private String contactNumber;
	private String status;
	private int smsTotalCount;
	private int smsBalanceCount;
	private int smsValidityDays;
	private int smsTotalSentCount;
	private int smsTodaySentCount;
	private Date smsStartDate;
	private Date smsEndDate;
	private List<BlobDocumentMappingVO> schoolImage =  new ArrayList<BlobDocumentMappingVO>();
	private String description;
	
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolCode() {
		return schoolCode;
	}
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContactEmailId() {
		return contactEmailId;
	}
	public void setContactEmailId(String contactEmailId) {
		this.contactEmailId = contactEmailId;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUserOid() {
		return userOid;
	}
	public void setUserOid(String userOid) {
		this.userOid = userOid;
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
	
	public int getSmsValidityDays() {
		return smsValidityDays;
	}
	public void setSmsValidityDays(int smsValidityDays) {
		this.smsValidityDays = smsValidityDays;
	}
	public Date getSmsStartDate() {
		return smsStartDate;
	}
	public void setSmsStartDate(Date smsStartDate) {
		this.smsStartDate = smsStartDate;
	}
	public Date getSmsEndDate() {
		return smsEndDate;
	}
	public void setSmsEndDate(Date smsEndDate) {
		this.smsEndDate = smsEndDate;
	}
	public int getSmsTotalCount() {
		return smsTotalCount;
	}
	public void setSmsTotalCount(int smsTotalCount) {
		this.smsTotalCount = smsTotalCount;
	}
	public int getSmsBalanceCount() {
		return smsBalanceCount;
	}
	public void setSmsBalanceCount(int smsBalanceCount) {
		this.smsBalanceCount = smsBalanceCount;
	}
	public int getSmsTotalSentCount() {
		return smsTotalSentCount;
	}
	public void setSmsTotalSentCount(int smsTotalSentCount) {
		this.smsTotalSentCount = smsTotalSentCount;
	}
	public int getSmsTodaySentCount() {
		return smsTodaySentCount;
	}
	public void setSmsTodaySentCount(int smsTodaySentCount) {
		this.smsTodaySentCount = smsTodaySentCount;
	}
	public List<BlobDocumentMappingVO> getSchoolImage() {
		return schoolImage;
	}
	public void setSchoolImage(List<BlobDocumentMappingVO> schoolImage) {
		this.schoolImage = schoolImage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
