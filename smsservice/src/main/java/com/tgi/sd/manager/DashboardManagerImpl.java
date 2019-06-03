package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.AttendanceDAO;
import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.dao.BloodGroupDAO;
import com.tgi.sd.dao.CasteDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.CourseDAO;
import com.tgi.sd.dao.DashboardDAO;
import com.tgi.sd.dao.EducationQualificationDAO;
import com.tgi.sd.dao.GenderDAO;
import com.tgi.sd.dao.LanguageDAO;
import com.tgi.sd.dao.MediumDAO;
import com.tgi.sd.dao.MotherTongueDAO;
import com.tgi.sd.dao.NationalityDAO;
import com.tgi.sd.dao.ReligionDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.SmsDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.SubjectDAO;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.domain.CountVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.domain.SmsVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class DashboardManagerImpl implements DashboardManager{
	
	private static Logger logger = Logger.getLogger(DashboardManagerImpl.class);
	
	private DashboardDAO dashboardDAO;

	private GenderDAO genderDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	private BatchDAO batchDAO;
	
	private MediumDAO mediumDAO;
	
	private CourseDAO courseDAO;
	
	private SchoolDAO schoolDAO;
	
	private  SmsDAO smsDAO;
	
	private BloodGroupDAO bloodGroupDAO;
	
	private CountryDAO countryDAO;
	
	private EducationQualificationDAO educationQualificationDAO;
	
	private LanguageDAO languageDAO;
	
	private MotherTongueDAO motherTongueDAO;
	
	private NationalityDAO nationalityDAO;
	
	private ReligionDAO religionDAO;
	
	private CasteDAO casteDAO;
	
	private SubjectDAO subjectDAO;
	
	private AttendanceDAO attendanceDAO;
	
	private StudentDAO studentDAO;
	
	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}
	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	public CasteDAO getCasteDAO() {
		return casteDAO;
	}
	public void setCasteDAO(CasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	
	public ReligionDAO getReligionDAO() {
		return religionDAO;
	}
	public void setReligionDAO(ReligionDAO religionDAO) {
		this.religionDAO = religionDAO;
	}
	
	public NationalityDAO getNationalityDAO() {
		return nationalityDAO;
	}
	public void setNationalityDAO(NationalityDAO nationalityDAO) {
		this.nationalityDAO = nationalityDAO;
	}
	
	public MotherTongueDAO getMotherTongueDAO() {
		return motherTongueDAO;
	}
	public void setMotherTongueDAO(MotherTongueDAO motherTongueDAO) {
		this.motherTongueDAO = motherTongueDAO;
	}
	
	public LanguageDAO getLanguageDAO() {
		return languageDAO;
	}
	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}
	
	public EducationQualificationDAO getEducationQualificationDAO() {
		return educationQualificationDAO;
	}
	public void setEducationQualificationDAO(EducationQualificationDAO educationQualificationDAO) {
		this.educationQualificationDAO = educationQualificationDAO;
	}
	
	public CountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	public BloodGroupDAO getBloodGroupDAO() {
		return bloodGroupDAO;
	}
	public void setBloodGroupDAO(BloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	public MediumDAO getMediumDAO() {
		return mediumDAO;
	}
	public void setMediumDAO(MediumDAO mediumDAO) {
		this.mediumDAO = mediumDAO;
	}
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public GenderDAO getGenderDAO() {
		return genderDAO;
	}
	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}
	
	public SchoolDAO getSchoolDAO() {
		return schoolDAO;
	}
	public void setSchoolDAO(SchoolDAO schoolDAO) {
		this.schoolDAO = schoolDAO;
	}
	
	public DashboardDAO getDashboardDAO() {
		return dashboardDAO;
	}
	public void setDashboardDAO(DashboardDAO dashboardDAO) {
		this.dashboardDAO = dashboardDAO;
	}
	
	public SmsDAO getSmsDAO() {
		return smsDAO;
	}
	
	public void setSmsDAO(SmsDAO smsDAO) {
		this.smsDAO = smsDAO;
	}
	
	public AttendanceDAO getAttendanceDAO() {
		return attendanceDAO;
	}

	public void setAttendanceDAO(AttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Override
	public Map<String, Object> getAllDashboardCount(String schoolId) throws SMSBusinessException  {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllDashboardCount starts");
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<CountVO> countList = new ArrayList<CountVO>();
		List<AttendanceVO> attendanceList = null;
		
		SchoolVO schoolVO = schoolDAO.getSchoolById(schoolId);
		Date d1 = new Date();
		int data =  (int)( (schoolVO.getSmsEndDate().getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		schoolVO.setSmsTotalSentCount(schoolVO.getSmsTotalCount() - schoolVO.getSmsBalanceCount());
		List<SmsLogVO> smsLogList = smsDAO.getSendTodaySmsCount(schoolVO.getId(), new Date());
		int countTodaySmsCount = 0;
		for(SmsLogVO  smsLogVO : smsLogList){
			countTodaySmsCount = countTodaySmsCount + smsLogVO.getSmsCount();
		} 
	
		schoolVO.setSmsTodaySentCount(countTodaySmsCount);
		schoolVO.setSmsValidityDays(data);
		
		long studentCount = dashboardDAO.getAllStudentCount(schoolId);
		long teacherCount = dashboardDAO.getAllTeacherCount(schoolId);
		long adminCount = dashboardDAO.getAllAdminCount(schoolId);
		long batchCount = batchDAO.getBatchCountBySchoolId(schoolId);
		long classCount = classDAO.getClassCountBySchoolId(schoolId);
		long mediumCount = mediumDAO.getMediumCountBySchoolId(schoolId);
		long sectionCount = sectionDAO.getSectionCountBySchoolId(schoolId);
		long courseCount = courseDAO.getCourseCountBySchoolId(schoolId);
		
		//Today Present And Absent Students
		
		attendanceList =  (List<AttendanceVO>) attendanceDAO.getAttendanceByClassAndSection(schoolId,d1,null,null,null);
		long attendanceListCount = attendanceList.size();
		long presentStudents = 0,absentStudents = 0,boysPresentStudents=0,girlsPresentStudents=0;
		long boysAbsentStudents=0,girlsAbsentStudents=0;
		if(attendanceListCount > 0){
			for(AttendanceVO attendanceVO :attendanceList){
				StudentVO studentVO =  studentDAO.getStudentByStudentId(attendanceVO.getStudentId(),schoolId);
				GenderVO genderVO = genderDAO.getGenderById(studentVO.getGender());
				if(attendanceVO.getFullDay()){
					presentStudents = presentStudents +1;
					if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_MALE)){
						boysPresentStudents =boysPresentStudents+1;
					}
					if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_FEMALE)){
						girlsPresentStudents = girlsPresentStudents+1;
					}
					
				}else{
					if(attendanceVO.getMorning() && attendanceVO.getAfternoon()){
						presentStudents = presentStudents +1;
						if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_MALE)){
							boysPresentStudents =boysPresentStudents+1;
						}
						if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_FEMALE)){
							girlsPresentStudents = girlsPresentStudents+1;
						}
					}else{
						absentStudents = absentStudents+1;
						if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_MALE)){
							boysAbsentStudents =boysAbsentStudents+1;
						}
						if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_FEMALE)){
							girlsAbsentStudents = girlsAbsentStudents+1;
						}
					}
				}
			}
		}
		
		long totalBoysCount = 0;
		long totalgirlsCount = 0;
		List<GenderVO> genderlist = genderDAO.getAllGenderBySchoolId(schoolId, null, null);
		for(GenderVO genderVO : genderlist){
			if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_MALE)){
				totalBoysCount = dashboardDAO.getAllGenderCount(genderVO.getId(),schoolId);
			}
			if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_FEMALE)){
				totalgirlsCount = dashboardDAO.getAllGenderCount(genderVO.getId(),schoolId);
			}
		}
		
		
		List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, null, null);
		String maleId="",femaleId="";
		for(ClassVO classVO : classList){
			
			for(GenderVO genderVO : genderlist){
				if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_MALE)){
					maleId = genderVO.getId();
				}
				if(genderVO.getGender().equalsIgnoreCase(SMSConstants.DASHBOARD_FEMALE)){
					femaleId = genderVO.getId();
				}
			}
			List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
			for(SectionVO sectionVO : sectionList){
				CountVO countVO = new CountVO();
				long boys_count = dashboardDAO.getAllBoysCountByClassSection(classVO.getId(),sectionVO.getId(),maleId,schoolId);
				long girls_count = dashboardDAO.getAllBoysCountByClassSection(classVO.getId(),sectionVO.getId(),femaleId,schoolId);
				long total_count = dashboardDAO.getAllBoysCountByClassSection(classVO.getId(),sectionVO.getId()," ",schoolId);
				
				countVO.setBoysCount(boys_count);
				countVO.setGirlssCount(girls_count);
				countVO.setTotalCount(total_count);
				countVO.setClassName(classVO.getClassName());
				countVO.setSection(sectionVO.getSectionName());
				countVO.setSchoolId(schoolId);
				countList.add(countVO);
			}
		}
		responseObjectsMap.put("SchoolVO", schoolVO);
		responseObjectsMap.put("StudentCount", studentCount);
		responseObjectsMap.put("TeacherCount", teacherCount);
		responseObjectsMap.put("AdminCount", adminCount);
		responseObjectsMap.put("parentCount", studentCount);
		responseObjectsMap.put("totalBoysCount", totalBoysCount);
		responseObjectsMap.put("totalgirlsCount", totalgirlsCount);
		responseObjectsMap.put("BatchCount", batchCount);
		responseObjectsMap.put("ClassCount", classCount);
		responseObjectsMap.put("MediumCount", mediumCount);
		responseObjectsMap.put("SectionCount", sectionCount);
		responseObjectsMap.put("CourseCount", courseCount);
		responseObjectsMap.put("genderClassAndSectionCount", countList);
		responseObjectsMap.put("PresentStudents", presentStudents);
		responseObjectsMap.put("AbsentStudents", absentStudents);
		responseObjectsMap.put("BoysPresentStudents", boysPresentStudents);
		responseObjectsMap.put("GirlsPresentStudents", girlsPresentStudents);
		responseObjectsMap.put("BoysAbsentStudents", boysAbsentStudents);
		responseObjectsMap.put("GirlsAbsentStudents", girlsAbsentStudents);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllDashboardCount starts");
		}
		
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllSuperAdminDashboardCount() throws SMSBusinessException  {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSuperAdminDashboardCount starts");
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		long schoolCount = dashboardDAO.getAllSchoolCount();
		long activeSchoolCount = dashboardDAO.getAllActiveSchoolCount(SMSConstants.SCHOOL_ACTIVE_STATUS);
		long inactiveSchoolCount = dashboardDAO.getAllActiveSchoolCount(SMSConstants.SCHOOL_INACTIVE_STATUS);
		long studentCount = dashboardDAO.getAllStudentCount(null);
		long teacherCount = dashboardDAO.getAllTeacherCount(null);
		long adminCount = dashboardDAO.getAllAdminCount(null);
		//long parentCount = dashboardDAO.getAllParentCount();

		responseObjectsMap.put("SchoolCount", schoolCount);
		responseObjectsMap.put("ActiveSchoolCount", activeSchoolCount);
		responseObjectsMap.put("InactiveSchoolCount", inactiveSchoolCount);
		responseObjectsMap.put("StudentCount", studentCount);
		responseObjectsMap.put("TeacherCount", teacherCount);
		responseObjectsMap.put("AdminCount", adminCount);
		responseObjectsMap.put("parentCount", studentCount);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSuperAdminDashboardCount starts");
		}
		
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllConfigurationBySchoolId(String schoolId) throws SMSBusinessException  {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllConfigurationBySchoolId starts");
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<ClassVO> classVOs = classDAO.getAllClassBySchoolId(schoolId, null, null);
		List<BatchVO> batchVOs = batchDAO.getAllBatchBySchoolId(schoolId, null, null);
		List<CourseVO> courseVOs = courseDAO.getAllCourseBySchoolId(schoolId, null, null);
		List<GenderVO> genderVOs = genderDAO.getAllGenderBySchoolId(schoolId, null, null);
		List<CountryVO> countryVOs = countryDAO.getAllCountryBySchoolId(schoolId, null, null);
		List<SectionVO> sectionVOs = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
		List<BloodGroupVO> bloodGroupVOs = bloodGroupDAO.getAllBloodGroupBySchoolId(schoolId, null, null);
		List<EducationalVO> educationalVOs = educationQualificationDAO.getAllEducationBySchoolId(schoolId, null, null);
		List<MotherTongueVO> motherTongueVOs = motherTongueDAO.getAllMotherTongueBySchoolId(schoolId, null, null);
		List<NationalityVO> nationalityVOs = nationalityDAO.getAllNationalityBySchoolId(schoolId, null, null);
		List<LanguageVO> languageVOs = languageDAO.getAllLanguageBySchoolId(schoolId, null, null);
		List<ReligionVO> religionVOs = religionDAO.getAllReligionBySchoolId(schoolId, null, null);
		List<SubjectVO> subjectVOs = subjectDAO.getAllSubjectBySchoolId(schoolId, null, null);
		List<MediumVO> mediumVOs = mediumDAO.getAllMediumBySchoolId(schoolId, null, null);
		List<CasteVO> casteVOs = casteDAO.getAllCasteBySchoolId(schoolId, null, null);
		
		responseObjectsMap.put("BatchVOs", batchVOs);
		responseObjectsMap.put("GenderVOs", genderVOs);
		responseObjectsMap.put("SectionVOs", sectionVOs);
		responseObjectsMap.put("ClassVOs", classVOs);
		responseObjectsMap.put("BloodGroupVOs", bloodGroupVOs);
		responseObjectsMap.put("CourseVOs", courseVOs);
		responseObjectsMap.put("CountryVOs", countryVOs);
		responseObjectsMap.put("EducationalVOs", educationalVOs);
		responseObjectsMap.put("LanguageVOs", languageVOs);
		responseObjectsMap.put("MediumVOs", mediumVOs);
		responseObjectsMap.put("MotherTongueVOs", motherTongueVOs);
		responseObjectsMap.put("NationalityVOs", nationalityVOs);
		responseObjectsMap.put("ReligionVOs", religionVOs);
		responseObjectsMap.put("CasteVOs", casteVOs);
		responseObjectsMap.put("SubjectVOs", subjectVOs);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllConfigurationBySchoolId starts");
		}
		
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllSMSCount() throws SMSBusinessException  {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSMSCount starts");
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<SmsVO> smsVOList = dashboardDAO.getAllSmsCount();
		
		for(SmsVO smsVO : smsVOList){
			Date d1 = new Date();
			int data =  (int)((smsVO.getSmsEndDate().getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
			//((SmsVO) smsVO).setSmsTotalSentCount(((SmsVO) smsVO).getSmsTotalCount() - ((SmsVO) smsVO).getSmsBalanceCount());
			List<SmsLogVO> smsLogList = smsDAO.getSendTodaySmsCount(null, new Date());
			int countTodaySmsCount = 0;
			for(SmsLogVO  smsLogVO : smsLogList){
				countTodaySmsCount = countTodaySmsCount + smsLogVO.getSmsCount();
			}
			
			List<SmsLogVO> smsAllLogList = smsDAO.getSendTodaySmsCount(null, null);
			int counAllSmsCount = 0;
			for(SmsLogVO  smsLogVO : smsAllLogList){
				counAllSmsCount = counAllSmsCount + smsLogVO.getSmsCount();
			} 
			smsVO.setSmsBalanceCount(smsVO.getSmsTotalCount() - counAllSmsCount);
			smsVO.setSmsTotalSentCount(counAllSmsCount);
			smsVO.setSmsTodaySentCount(countTodaySmsCount);
			smsVO.setSmsValidityDays(data);
		
		}
		
		responseObjectsMap.put("SmsVO", smsVOList);
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSMSCount starts");
		}
		
		return responseObjectsMap;
	}
	

}
