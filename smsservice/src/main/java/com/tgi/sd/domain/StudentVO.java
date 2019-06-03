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

public class StudentVO  extends GenericDomainObject {

	private static final long serialVersionUID = -3006094917117374169L;

	private String studentName;
	private String studentId;
	private String surName;

	private String gender;

	private Date dob;
	private String bloodGroup;
	private String religion;
	private String caste;
	private String nationality;  		
	private String mohterTongue;
	private String physicallyChallenged;
	private String disabilityDetail;
	//Contact Information
	private String mobileNumber;
	private String emergencyContactNumber;
	private String emailId;
	private String alternateEmailId;
	private String countryId;
	private String stateId;
	private String cityId;
	private String zipcode;
	private String addressLine1;
	private String addressLine2;
	
	
	private String admissionNumber;
	private String FileNo;

	private Date admissionDate;

	private String medium;	

	private String classId;
	private String section;
	private String batchId;
	private String course;
	private String firstLanguage;
	private String secondLanguage;	
	private String identificationMark1;
	private String identificationMark2;
	
	//Previous School
	private String previousSchool;
	private String lastStudiedCourse;
	private String lastExaminationMark;
	private String ReasonForChange;	
	private String yearOfLastStudiedSchool;
	private String reasonForGap;	
	private String schoolId;
	
	//attendance Information
	private long attendancePercenatge;
	private int presentDays;
	private int totalDays;
	
	//Parent Information
	private String parentName;
	private String parentId;
	private String relation;
	private String parentOccupation;
	private String parentGender;
	private String parentQualification;
	private String parentMobileNumber1;
	private String parentMobileNumber2;
	private Date parentDob;
	private String parentAnnualIncome;
	private String parentEmailId;
	private String parentAlternateEmailId;
	private String motherName;
	private String motherOccupation;
	private String motherAnnualIncome;
	private String guardianName;
	private String guardianOccupation;
	private String guardianAnnualIncome;
	private List<BlobDocumentMappingVO> studentImage =  new ArrayList<BlobDocumentMappingVO>();
	
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
	@Transient
	private String mediumName;
	@Transient
	private String className;
	@Transient
	private String sectionName;
	@Transient
	private String courseName;
	@Transient
	private String batchName;
	@Transient
	private String firstLanguageName;
	@Transient
	private String secondLanguageName;
	@Transient
	private String parentGenderName;
	@Transient
	private String schoolName;
	
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
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

	/**
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * @param studentName the studentName to set
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * @return the surName
	 */
	public String getSurName() {
		return surName;
	}

	/**
	 * @param surName the surName to set
	 */
	public void setSurName(String surName) {
		this.surName = surName;
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
	 * @return the bloodGroup
	 */
	public String getBloodGroup() {
		return bloodGroup;
	}

	/**
	 * @param bloodGroup the bloodGroup to set
	 */
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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
	 * @return the mohterTongue
	 */
	public String getMohterTongue() {
		return mohterTongue;
	}

	/**
	 * @param mohterTongue the mohterTongue to set
	 */
	public void setMohterTongue(String mohterTongue) {
		this.mohterTongue = mohterTongue;
	}

	/**
	 * @return the physicallyChallenged
	 */
	public String getPhysicallyChallenged() {
		return physicallyChallenged;
	}

	/**
	 * @param physicallyChallenged the physicallyChallenged to set
	 */
	public void setPhysicallyChallenged(String physicallyChallenged) {
		this.physicallyChallenged = physicallyChallenged;
	}

	public String getDisabilityDetail() {
		return disabilityDetail;
	}

	public void setDisabilityDetail(String disabilityDetail) {
		this.disabilityDetail = disabilityDetail;
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
	 * @return the emergencyContactNumbe
	 */
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	/**
	 * @param emergencyContactNumbe the emergencyContactNumbe to set
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

	public long getAttendancePercenatge() {
		return attendancePercenatge;
	}

	public void setAttendancePercenatge(long attendancePercenatge) {
		this.attendancePercenatge = attendancePercenatge;
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

	/**
	 * @return the admissionNumber
	 */
	public String getAdmissionNumber() {
		return admissionNumber;
	}

	/**
	 * @param admissionNumber the admissionNumber to set
	 */
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	/**
	 * @return the fileNo
	 */
	public String getFileNo() {
		return FileNo;
	}

	/**
	 * @param fileNo the fileNo to set
	 */
	public void setFileNo(String fileNo) {
		FileNo = fileNo;
	}

	/**
	 * @return the admissionDate
	 */
	public Date getAdmissionDate() {
		return admissionDate;
	}

	/**
	 * @param admissionDate the admissionDate to set
	 */
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	/**
	 * @return the medium
	 */
	public String getMedium() {
		return medium;
	}

	/**
	 * @param medium the medium to set
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * @return the clas
	 */
	

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}

	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * @return the firstLanguage
	 */
	public String getFirstLanguage() {
		return firstLanguage;
	}

	/**
	 * @param firstLanguage the firstLanguage to set
	 */
	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguage = firstLanguage;
	}

	/**
	 * @return the secondLanguage
	 */
	public String getSecondLanguage() {
		return secondLanguage;
	}

	/**
	 * @param secondLanguage the secondLanguage to set
	 */
	public void setSecondLanguage(String secondLanguage) {
		this.secondLanguage = secondLanguage;
	}

	/**
	 * @return the identificationMark1
	 */
	public String getIdentificationMark1() {
		return identificationMark1;
	}

	/**
	 * @param identificationMark1 the identificationMark1 to set
	 */
	public void setIdentificationMark1(String identificationMark1) {
		this.identificationMark1 = identificationMark1;
	}

	/**
	 * @return the identificationMark2
	 */
	public String getIdentificationMark2() {
		return identificationMark2;
	}

	/**
	 * @param identificationMark2 the identificationMark2 to set
	 */
	public void setIdentificationMark2(String identificationMark2) {
		this.identificationMark2 = identificationMark2;
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
	 * @return the lastStudiedCourse
	 */
	public String getLastStudiedCourse() {
		return lastStudiedCourse;
	}

	/**
	 * @param lastStudiedCourse the lastStudiedCourse to set
	 */
	public void setLastStudiedCourse(String lastStudiedCourse) {
		this.lastStudiedCourse = lastStudiedCourse;
	}

	/**
	 * @return the lastExaminationMark
	 */
	public String getLastExaminationMark() {
		return lastExaminationMark;
	}

	/**
	 * @param lastExaminationMark the lastExaminationMark to set
	 */
	public void setLastExaminationMark(String lastExaminationMark) {
		this.lastExaminationMark = lastExaminationMark;
	}

	/**
	 * @return the reasonForChange
	 */
	public String getReasonForChange() {
		return ReasonForChange;
	}

	/**
	 * @param reasonForChange the reasonForChange to set
	 */
	public void setReasonForChange(String reasonForChange) {
		ReasonForChange = reasonForChange;
	}

	/**
	 * @return the yearOfLastStudiedSchool
	 */
	public String getYearOfLastStudiedSchool() {
		return yearOfLastStudiedSchool;
	}

	/**
	 * @param yearOfLastStudiedSchool the yearOfLastStudiedSchool to set
	 */
	public void setYearOfLastStudiedSchool(String yearOfLastStudiedSchool) {
		this.yearOfLastStudiedSchool = yearOfLastStudiedSchool;
	}

	/**
	 * @return the reasonForGap
	 */
	public String getReasonForGap() {
		return reasonForGap;
	}

	/**
	 * @param reasonForGap the reasonForGap to set
	 */
	public void setReasonForGap(String reasonForGap) {
		this.reasonForGap = reasonForGap;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getParentOccupation() {
		return parentOccupation;
	}

	public void setParentOccupation(String parentOccupation) {
		this.parentOccupation = parentOccupation;
	}

	public String getParentGender() {
		return parentGender;
	}

	public void setParentGender(String parentGender) {
		this.parentGender = parentGender;
	}

	public String getParentMobileNumber1() {
		return parentMobileNumber1;
	}

	public void setParentMobileNumber1(String parentMobileNumber1) {
		this.parentMobileNumber1 = parentMobileNumber1;
	}

	public String getParentMobileNumber2() {
		return parentMobileNumber2;
	}

	public void setParentMobileNumber2(String parentMobileNumber2) {
		this.parentMobileNumber2 = parentMobileNumber2;
	}

	public Date getParentDob() {
		return parentDob;
	}

	public void setParentDob(Date parentDob) {
		this.parentDob = parentDob;
	}

	

	public String getParentEmailId() {
		return parentEmailId;
	}

	public void setParentEmailId(String parentEmailId) {
		this.parentEmailId = parentEmailId;
	}

	public String getParentAlternateEmailId() {
		return parentAlternateEmailId;
	}

	public void setParentAlternateEmailId(String parentAlternateEmailId) {
		this.parentAlternateEmailId = parentAlternateEmailId;
	}

	public String getParentQualification() {
		return parentQualification;
	}

	public void setParentQualification(String parentQualification) {
		this.parentQualification = parentQualification;
	}

	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
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

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getFirstLanguageName() {
		return firstLanguageName;
	}

	public void setFirstLanguageName(String firstLanguageName) {
		this.firstLanguageName = firstLanguageName;
	}

	public String getSecondLanguageName() {
		return secondLanguageName;
	}

	public void setSecondLanguageName(String secondLanguageName) {
		this.secondLanguageName = secondLanguageName;
	}

	public String getParentGenderName() {
		return parentGenderName;
	}

	public void setParentGenderName(String parentGenderName) {
		this.parentGenderName = parentGenderName;
	}

	public String getParentAnnualIncome() {
		return parentAnnualIncome;
	}

	public void setParentAnnualIncome(String parentAnnualIncome) {
		this.parentAnnualIncome = parentAnnualIncome;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherOccupation() {
		return motherOccupation;
	}

	public void setMotherOccupation(String motherOccupation) {
		this.motherOccupation = motherOccupation;
	}

	public String getMotherAnnualIncome() {
		return motherAnnualIncome;
	}

	public void setMotherAnnualIncome(String motherAnnualIncome) {
		this.motherAnnualIncome = motherAnnualIncome;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianOccupation() {
		return guardianOccupation;
	}

	public void setGuardianOccupation(String guardianOccupation) {
		this.guardianOccupation = guardianOccupation;
	}

	public String getGuardianAnnualIncome() {
		return guardianAnnualIncome;
	}

	public void setGuardianAnnualIncome(String guardianAnnualIncome) {
		this.guardianAnnualIncome = guardianAnnualIncome;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<BlobDocumentMappingVO> getStudentImage() {
		return studentImage;
	}

	public void setStudentImage(List<BlobDocumentMappingVO> studentImage) {
		this.studentImage = studentImage;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	
}
