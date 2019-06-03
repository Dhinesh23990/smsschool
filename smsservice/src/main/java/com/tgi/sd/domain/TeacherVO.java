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

public class TeacherVO extends GenericDomainObject {

	private static final long serialVersionUID = -3668605650448730341L;
	
	private String userOid;

	//@NotNull(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.STAFFNAME_IS_REQUIRED)
    //@Length(max=50,message = ErrorConstants.STAFFNAME_INVALID_LENGTH)
	private String staffName;
	private String staffId;
	private String initial;
	//@NotNull(message = ErrorConstants.GENDER_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.GENDER_IS_REQUIRED)
	private String gender;
	//@NotNull(message = ErrorConstants.DOB_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.DOB_IS_REQUIRED)
	private Date dob;
    private String blooGroup;
    private String religion;                   
    private String caste;
    private String nationality;
    private String motherTongue;
    private String aadharCardNo;
    private String panCardNo;
	//@NotNull(message = ErrorConstants.MOBILENUMBER_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.MOBILENUMBER_IS_REQUIRED)
    //@Length(max=20,message = ErrorConstants.MOBILENUMBER_INVALID_LENGTH)
    private String mobileNumber;
    private String emergencyContactNumber;
    private String emailId;
    private String alternateEmailId;

    //	Permanent Address
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String state;
    private String city;
    private String pincode;
    
	//Communication  Address
	private String comAddressLine1;
	private String comAddressLine2;
	private String comCountry;
	private String comState;
	private String comCity;
	private String comPinCode;	
  
    private String employeeNumber;
    private String fileNumber;
	//@NotNull(message = ErrorConstants.DATE_OF_JOINING_IS_REQUIRED)
    //@NotBlank(message = ErrorConstants.DATE_OF_JOINING_IS_REQUIRED)
    private Date dateOfJoining;
    private String specializedIn;
    private String educationalQualification;
    private String designation;
    private String experience;
    private String salary;
    private String previousSchool;
    private String reasonForChange;
    
  //attendance Information
  	private double attendancePercenatge;
  	private int presentDays;
  	private int totalDays;
  	private int totalStudentCount;
  	private int presentStudents;
  	private int absentStudents;
  	private double studentAttendancePercentage;
    private String schoolId;
    private List<BlobDocumentMappingVO> staffImage =  new ArrayList<BlobDocumentMappingVO>();
    
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

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the initial
	 */
	public String getInitial() {
		return initial;
	}
	/**
	 * @param initial the initial to set
	 */
	public void setInitial(String initial) {
		this.initial = initial;
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
	 * @return the blooGroup
	 */
	public String getBlooGroup() {
		return blooGroup;
	}
	/**
	 * @param blooGroup the blooGroup to set
	 */
	public void setBlooGroup(String blooGroup) {
		this.blooGroup = blooGroup;
	}
	/**
	 * @return the religion
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion the religion to set
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return the caste
	 */
	public String getCaste() {
		return caste;
	}
	/**
	 * @param caste the caste to set
	 */
	public void setCaste(String caste) {
		this.caste = caste;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	/**
	 * @return the motherTongue
	 */
	public String getMotherTongue() {
		return motherTongue;
	}
	/**
	 * @param motherTongue the motherTongue to set
	 */
	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}
	/**
	 * @return the aadharCardNo
	 */
	public String getAadharCardNo() {
		return aadharCardNo;
	}
	/**
	 * @param aadharCardNo the aadharCardNo to set
	 */
	public void setAadharCardNo(String aadharCardNo) {
		this.aadharCardNo = aadharCardNo;
	}
	/**
	 * @return the panCardNo
	 */
	public String getPanCardNo() {
		return panCardNo;
	}
	/**
	 * @param panCardNo the panCardNo to set
	 */
	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	/**
	 * @return the emergencyContactNumber
	 */
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	/**
	 * @param emergencyContactNumber the emergencyContactNumber to set
	 */
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
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
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	 * @return the employeeNumber
	 */
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param employeeNumber the employeeNumber to set
	 */
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	/**
	 * @return the fileNumber
	 */
	public String getFileNumber() {
		return fileNumber;
	}
	/**
	 * @param fileNumber the fileNumber to set
	 */
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	/**
	 * @return the specializedIn
	 */
	public String getSpecializedIn() {
		return specializedIn;
	}
	/**
	 * @param specializedIn the specializedIn to set
	 */
	public void setSpecializedIn(String specializedIn) {
		this.specializedIn = specializedIn;
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
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the experience
	 */
	public String getExperience() {
		return experience;
	}
	/**
	 * @param experience the experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}
	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	/**
	 * @return the previousSchool
	 */
	public String getPreviousSchool() {
		return previousSchool;
	}
	/**
	 * @param previousSchool the previousSchool to set
	 */
	public void setPreviousSchool(String previousSchool) {
		this.previousSchool = previousSchool;
	}
	/**
	 * @return the reasonForChange
	 */
	public String getReasonForChange() {
		return reasonForChange;
	}
	/**
	 * @param reasonForChange the reasonForChange to set
	 */
	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
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

	public double getAttendancePercenatge() {
		return attendancePercenatge;
	}

	public void setAttendancePercenatge(double attendancePercenatge) {
		this.attendancePercenatge = attendancePercenatge;
	}

	public List<BlobDocumentMappingVO> getStaffImage() {
		return staffImage;
	}

	public void setStaffImage(List<BlobDocumentMappingVO> staffImage) {
		this.staffImage = staffImage;
	}

	public int getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(int presentDays) {
		this.presentDays = presentDays;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getTotalStudentCount() {
		return totalStudentCount;
	}

	public void setTotalStudentCount(int totalStudentCount) {
		this.totalStudentCount = totalStudentCount;
	}

	public int getPresentStudents() {
		return presentStudents;
	}

	public void setPresentStudents(int presentStudents) {
		this.presentStudents = presentStudents;
	}

	public int getAbsentStudents() {
		return absentStudents;
	}

	public void setAbsentStudents(int absentStudents) {
		this.absentStudents = absentStudents;
	}

	public double getStudentAttendancePercentage() {
		return studentAttendancePercentage;
	}

	public void setStudentAttendancePercentage(double studentAttendancePercentage) {
		this.studentAttendancePercentage = studentAttendancePercentage;
	}




}
