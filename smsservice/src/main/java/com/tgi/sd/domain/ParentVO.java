/**
 * 
 */
package com.tgi.sd.domain;

import java.util.Date;
import com.tgi.sd.common.domain.GenericDomainObject;


/**
 * @author SGSAuthour
 *
 */

public class ParentVO extends GenericDomainObject {

	private static final long serialVersionUID = -1868014841002602133L;

	
	private String studentId;
	private String userOid;
	
	//Personal Information
	//@NotNull(message = ErrorConstants.PARENTNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.PARENTNAME_IS_REQUIRED)
    //@Length(max=50,message = ErrorConstants.PARENTNAME_INVALID_LENGTH)
	private String parentName;
	private String relation;
	private String gender;
	private Date dob;
	private String occupation;
	private String educationalQualification;
	private String annualIncome;
	private String aadharNumber;
	private String panNumber;
	
	//Contact Information
	//@NotNull(message = ErrorConstants.MOBILENUMBER_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.MOBILENUMBER_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.MOBILENUMBER_INVALID_LENGTH)
	private String parentMobileNumber1;
	private String parentMobileNumber2;
	private String emailId;
	private String alternateEmailId;
	
	//Permanent Address
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String state;
	private String city;
	private String pinCode;
	
	//Communication  Address
	private String comAddressLine1;
	private String comAddressLine2;
	private String comCountry;
	private String comState;
	private String comCity;
	private String comPinCode;	

	private String profileImage;
	private String schoolId;

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
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
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the educationalQualification
	 */
	public String getEducationalQualification() {
		return educationalQualification;
	}

	/**
	 * @param educationalQualification the educationalQualification to set
	 */
	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	/**
	 * @return the annualIncome
	 */
	public String getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome the annualIncome to set
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	/**
	 * @return the aadharNumber
	 */
	public String getAadharNumber() {
		return aadharNumber;
	}

	/**
	 * @param aadharNumber the aadharNumber to set
	 */
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	/**
	 * @return the panNumber
	 */
	public String getPanNumber() {
		return panNumber;
	}

	/**
	 * @param panNumber the panNumber to set
	 */
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	/**
	 * @return the parentMobileNumber1
	 */
	public String getParentMobileNumber1() {
		return parentMobileNumber1;
	}

	/**
	 * @param parentMobileNumber1 the parentMobileNumber1 to set
	 */
	public void setParentMobileNumber1(String parentMobileNumber1) {
		this.parentMobileNumber1 = parentMobileNumber1;
	}

	/**
	 * @return the parentMobileNumber2
	 */
	public String getParentMobileNumber2() {
		return parentMobileNumber2;
	}

	/**
	 * @param parentMobileNumber2 the parentMobileNumber2 to set
	 */
	public void setParentMobileNumber2(String parentMobileNumber2) {
		this.parentMobileNumber2 = parentMobileNumber2;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the alternateEmailId
	 */
	public String getAlternateEmailId() {
		return alternateEmailId;
	}

	/**
	 * @param alternateEmailId the alternateEmailId to set
	 */
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the pinCode
	 */
	public String getPinCode() {
		return pinCode;
	}

	/**
	 * @param pinCode the pinCode to set
	 */
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	/**
	 * @return the comAddressLine1
	 */
	public String getComAddressLine1() {
		return comAddressLine1;
	}

	/**
	 * @param comAddressLine1 the comAddressLine1 to set
	 */
	public void setComAddressLine1(String comAddressLine1) {
		this.comAddressLine1 = comAddressLine1;
	}

	/**
	 * @return the comAddressLine2
	 */
	public String getComAddressLine2() {
		return comAddressLine2;
	}

	/**
	 * @param comAddressLine2 the comAddressLine2 to set
	 */
	public void setComAddressLine2(String comAddressLine2) {
		this.comAddressLine2 = comAddressLine2;
	}

	/**
	 * @return the comCountry
	 */
	public String getComCountry() {
		return comCountry;
	}

	/**
	 * @param comCountry the comCountry to set
	 */
	public void setComCountry(String comCountry) {
		this.comCountry = comCountry;
	}

	/**
	 * @return the comState
	 */
	public String getComState() {
		return comState;
	}

	/**
	 * @param comState the comState to set
	 */
	public void setComState(String comState) {
		this.comState = comState;
	}

	/**
	 * @return the comCity
	 */
	public String getComCity() {
		return comCity;
	}

	/**
	 * @param comCity the comCity to set
	 */
	public void setComCity(String comCity) {
		this.comCity = comCity;
	}

	/**
	 * @return the comPinCode
	 */
	public String getComPinCode() {
		return comPinCode;
	}

	/**
	 * @param comPinCode the comPinCode to set
	 */
	public void setComPinCode(String comPinCode) {
		this.comPinCode = comPinCode;
	}

	/**
	 * @return the profileImage
	 */
	public String getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the userOid
	 */
	public String getUserOid() {
		return userOid;
	}

	/**
	 * @param userOid the userOid to set
	 */
	public void setUserOid(String userOid) {
		this.userOid = userOid;
	}
}
