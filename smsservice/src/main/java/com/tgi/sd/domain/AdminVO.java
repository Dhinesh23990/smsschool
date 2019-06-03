/**
 * 
 */
package com.tgi.sd.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.tgi.sd.common.domain.GenericDomainObject;
import com.tgi.sd.domain.fileUpload.BlobDocumentMappingVO;

/**
 * @author SGSAuthour
 *
 */
public class AdminVO extends GenericDomainObject {
	
	private static final long serialVersionUID = -3668605650448730341L;
	private String userOid;
	private String adminId;
	//Personal information
	private String firstName;
	private String userName;
	private String initial;
	private String genderId;
	private Date dob;
    private String blooGroupId;
    private String religionId;                   
    private String casteId;
    private String nationalityId;
    private String motherTongueId;
    private String aadharCardNo;
    private String panCardNo;
    private boolean includeStudentMessage;
    
    //Contact Information
    private String mobileNumber;
    private String emergencyContactNumber;
    private String emailId;
    private String alternateEmailId;

    //Permanent Address
    private String addressLine1;
    private String addressLine2;
    private String countryId;
    private String stateId;
    private String cityId;
    private String pincodeId;
    
	//Communication  Address
	private String comAddressLine1;
	private String comAddressLine2;
	private String comCountryId;
	private String comStateId;
	private String comCityId;
	private String comPinCodeId;	
  
	//Academin information
    private String employeeNumber;
    private String fileNumber;
    private Date dateOfJoining;
    private String educationalQualification;
    private String designation;
    private String experience;
    private String salary;
    private String previousSchool;
    private String reasonForChange;
    
    private String schoolId;
    
    private String status;
    
    private List<BlobDocumentMappingVO> adminImage =  new ArrayList<BlobDocumentMappingVO>();
    
    @Transient
	private String countryName;
	@Transient
	private String stateName;
	@Transient
	private String cityName;
	@Transient
	private String genderName;
	@Transient
	private String bloodGroupName;
	@Transient
	private String religionName;
	@Transient
	private String casteName;
	@Transient
	private String nationalityName;
	@Transient
	private String mohterTongueName;

	public String getUserOid() {
		return userOid;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBlooGroupId() {
		return blooGroupId;
	}

	public void setBlooGroupId(String blooGroupId) {
		this.blooGroupId = blooGroupId;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public String getCasteId() {
		return casteId;
	}

	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}

	public String getNationalityId() {
		return nationalityId;
	}

	public void setNationalityId(String nationalityId) {
		this.nationalityId = nationalityId;
	}

	public String getMotherTongueId() {
		return motherTongueId;
	}

	public void setMotherTongueId(String motherTongueId) {
		this.motherTongueId = motherTongueId;
	}

	public String getAadharCardNo() {
		return aadharCardNo;
	}

	public void setAadharCardNo(String aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}

	public String getPanCardNo() {
		return panCardNo;
	}

	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}

	public boolean isIncludeStudentMessage() {
		return includeStudentMessage;
	}

	public void setIncludeStudentMessage(boolean includeStudentMessage) {
		this.includeStudentMessage = includeStudentMessage;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getPincodeId() {
		return pincodeId;
	}

	public void setPincodeId(String pincodeId) {
		this.pincodeId = pincodeId;
	}

	public String getComAddressLine1() {
		return comAddressLine1;
	}

	public void setComAddressLine1(String comAddressLine1) {
		this.comAddressLine1 = comAddressLine1;
	}

	public String getComAddressLine2() {
		return comAddressLine2;
	}

	public void setComAddressLine2(String comAddressLine2) {
		this.comAddressLine2 = comAddressLine2;
	}

	public String getComCountryId() {
		return comCountryId;
	}

	public void setComCountryId(String comCountryId) {
		this.comCountryId = comCountryId;
	}

	public String getComStateId() {
		return comStateId;
	}

	public void setComStateId(String comStateId) {
		this.comStateId = comStateId;
	}

	public String getComCityId() {
		return comCityId;
	}

	public void setComCityId(String comCityId) {
		this.comCityId = comCityId;
	}

	public String getComPinCodeId() {
		return comPinCodeId;
	}

	public void setComPinCodeId(String comPinCodeId) {
		this.comPinCodeId = comPinCodeId;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPreviousSchool() {
		return previousSchool;
	}

	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}

	public String getReasonForChange() {
		return reasonForChange;
	}

	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getGenderName() {
		return genderName;
	}

	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}

	public String getBloodGroupName() {
		return bloodGroupName;
	}

	public void setBloodGroupName(String bloodGroupName) {
		this.bloodGroupName = bloodGroupName;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public String getNationalityName() {
		return nationalityName;
	}

	public void setNationalityName(String nationalityName) {
		this.nationalityName = nationalityName;
	}

	public String getMohterTongueName() {
		return mohterTongueName;
	}

	public void setMohterTongueName(String mohterTongueName) {
		this.mohterTongueName = mohterTongueName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BlobDocumentMappingVO> getAdminImage() {
		return adminImage;
	}

	public void setAdminImage(List<BlobDocumentMappingVO> adminImage) {
		this.adminImage = adminImage;
	}
    
}
