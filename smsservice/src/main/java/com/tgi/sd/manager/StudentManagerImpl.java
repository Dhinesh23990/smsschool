/**
 * 
 */
package com.tgi.sd.manager;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.tgi.sd.dao.BatchConfigurationDAO;
import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.dao.BloodGroupDAO;
import com.tgi.sd.dao.CasteDAO;
import com.tgi.sd.dao.CityDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.CourseDAO;
import com.tgi.sd.dao.GenderDAO;
import com.tgi.sd.dao.LanguageDAO;
import com.tgi.sd.dao.MediumDAO;
import com.tgi.sd.dao.MotherTongueDAO;
import com.tgi.sd.dao.NationalityDAO;
import com.tgi.sd.dao.ReligionDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.StateDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.domain.BulkImportStudentDetailVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtility;

/**
 * @author SGSAuthour
 *
 */
public class StudentManagerImpl implements StudentManager {
	
	private static Logger logger = Logger.getLogger(StudentManagerImpl.class);

	StudentDAO studentDAO;
	
	private CityDAO cityDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	private GenderDAO genderDAO;
	
	private BloodGroupDAO bloodGroupDAO;
	
	private ReligionDAO religionDAO;
	
	private CasteDAO casteDAO;
	
	private NationalityDAO nationalityDAO;
	
	private MotherTongueDAO motherTongueDAO;
	
	private MediumDAO mediumDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	private CourseDAO courseDAO;
	
	private LanguageDAO languageDAO;
	
	private BatchDAO batchDAO;
	
	private SchoolDAO schoolDAO;
	
	private BatchConfigurationDAO batchConfigurationDAO;
	
	private TeacherDAO teacherDAO;
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	public LanguageDAO getLanguageDAO() {
		return languageDAO;
	}
	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
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
	
	public MediumDAO getMediumDAO() {
		return mediumDAO;
	}
	public void setMediumDAO(MediumDAO mediumDAO) {
		this.mediumDAO = mediumDAO;
	}
	
	public MotherTongueDAO getMotherTongueDAO() {
		return motherTongueDAO;
	}
	public void setMotherTongueDAO(MotherTongueDAO motherTongueDAO) {
		this.motherTongueDAO = motherTongueDAO;
	}
	
	public NationalityDAO getNationalityDAO() {
		return nationalityDAO;
	}
	public void setNationalityDAO(NationalityDAO nationalityDAO) {
		this.nationalityDAO = nationalityDAO;
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
	
	public BloodGroupDAO getBloodGroupDAO() {
		return bloodGroupDAO;
	}
	public void setBloodGroupDAO(BloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}
	
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	public CountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	public StateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
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
	
	public BatchConfigurationDAO getBatchConfigurationDAO() {
		return batchConfigurationDAO;
	}
	public void setBatchConfigurationDAO(BatchConfigurationDAO batchConfigurationDAO) {
		this.batchConfigurationDAO = batchConfigurationDAO;
	}
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	public StudentVO saveStudent(StudentVO student) throws SMSBusinessException {
		
		student.setId(UUID.randomUUID().toString());
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStudent :::" + student.getStudentName());
		}
			StudentVO CheckAdmissionNoSchoolVO = studentDAO.getStudentAdmissionNumber(student.getAdmissionNumber(),student.getSchoolId(),null);
			if(CheckAdmissionNoSchoolVO != null){
				throw new SMSBusinessException(SMSConstants.STUDENT_ADMISSIONNO_EXITS);
			}
			long countOfOccurences = studentDAO.getStudentCountByName(student.getStudentName(),student.getSurName(),student.getSchoolId(),null);
			if(countOfOccurences>0){
	    		throw new SMSBusinessException(SMSConstants.STUDENT_ALREADY_EXITS);
 			}else{
 				SchoolVO schoolVO = schoolDAO.getSchoolById(student.getSchoolId());
 				BatchVO batchVO = batchDAO.getBatchById(student.getBatchId());
 				long studentCount = studentDAO.getAllStudentCountByBatchCode(student.getBatchId(),null,student.getSchoolId());
 				int number;
 				number = (int) studentCount+1;
 				String str = String.format("%04d", number);
 				String studentId,parentId;
 				String substring = batchVO.getBatchCode().substring(Math.max(batchVO.getBatchCode().length() - 2, 0));
 				studentId = schoolVO.getSchoolCode()+substring+"S"+str;
 				parentId = schoolVO.getSchoolCode()+substring+"P"+str;
 				student.setStudentId(studentId);
 				student.setParentId(parentId);
 				student = studentDAO.saveStudent(student);
 			}
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStudent :::" + student.getId());
		}
		return student;
	}

	public StudentVO getStudent(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudent Id:::" + id);
		}
		
		StudentVO student =  studentDAO.getStudent(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudent :::" + student.getId());
		}
		return student;
	}

	public StudentVO viewStudent(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewStudent Id:::" + id);
		}
		
		StudentVO studentVO =  studentDAO.getStudent(id);
		
		if(StringUtils.isNotBlank(studentVO.getCountryId())){
			CountryVO countryVO = countryDAO.getCountryById(studentVO.getCountryId());
			studentVO.setCountryName(countryVO.getCountryName());
		}
		if(StringUtils.isNotBlank(studentVO.getStateId())){
			StateVO stateVO = stateDAO.getStateById(studentVO.getStateId());
			studentVO.setStateName(stateVO.getStateName());
		}
		if(StringUtils.isNotBlank(studentVO.getCityId())){
			CityVO cityVO = cityDAO.getCityById(studentVO.getCityId());
			studentVO.setCityName(cityVO.getCityName());
		}
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		if(StringUtils.isNotBlank(studentVO.getGender())){
			GenderVO genderVO = genderDAO.getGenderById(studentVO.getGender());
			studentVO.setGenderName(genderVO.getGender());
		}
		if(StringUtils.isNotBlank(studentVO.getBloodGroup())){
			BloodGroupVO bloodGroupVO = bloodGroupDAO.getBloodGroupById(studentVO.getBloodGroup());
			studentVO.setBloodGroupName(bloodGroupVO.getBloodgroupName());
		}
		if(StringUtils.isNotBlank(studentVO.getReligion())){
			ReligionVO religionVO = religionDAO.getReligionById(studentVO.getReligion());
			studentVO.setReligionName(religionVO.getReligionName());
		}
		if(StringUtils.isNotBlank(studentVO.getCaste())){
			CasteVO casteVO = casteDAO.getCasteById(studentVO.getCaste());
			studentVO.setCasteName(casteVO.getCasteName());
		}
		if(StringUtils.isNotBlank(studentVO.getNationality())){
			NationalityVO nationalityVO = nationalityDAO.getNationalityById(studentVO.getNationality());
			studentVO.setNationalityName(nationalityVO.getNationalityName());
		}
		if(StringUtils.isNotBlank(studentVO.getMohterTongue())){
			MotherTongueVO motherTongueVO = motherTongueDAO.getMotherTongueById(studentVO.getMohterTongue());
			studentVO.setMohterTongueName(motherTongueVO.getMotherTongueName());
		}
		if(StringUtils.isNotBlank(studentVO.getMedium())){
			MediumVO mediumVO = mediumDAO.getMediumById(studentVO.getMedium());
			studentVO.setMediumName(mediumVO.getMediumName());
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		if(StringUtils.isNotBlank(studentVO.getCourse())){
			CourseVO courseVO = courseDAO.getCourseById(studentVO.getCourse());
			studentVO.setCourseName(courseVO.getCourseName());
		}
		if(StringUtils.isNotBlank(studentVO.getFirstLanguage())){
			LanguageVO languageVO = languageDAO.getLanguageById(studentVO.getFirstLanguage());
			studentVO.setFirstLanguageName(languageVO.getLanguageName());
		}
		if(StringUtils.isNotBlank(studentVO.getSecondLanguage())){
			LanguageVO sndLanguageVO = languageDAO.getLanguageById(studentVO.getSecondLanguage());
			studentVO.setSecondLanguageName(sndLanguageVO.getLanguageName());
		}
		if(StringUtils.isNotBlank(studentVO.getParentGender())){
			GenderVO parentGenderVO = genderDAO.getGenderById(studentVO.getParentGender());
			studentVO.setParentGenderName(parentGenderVO.getGender());
		}
		
		if(StringUtils.isNotBlank(studentVO.getSchoolId())){
			SchoolVO schoolVO = schoolDAO.getSchoolById(studentVO.getSchoolId());
			studentVO.setSchoolName(schoolVO.getSchoolName());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End viewStudent :::" + studentVO.getId());
		}
		return studentVO;
	}

	
	@Override
	public StudentVO updateStudent(StudentVO student) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStudent :::" + student.getStudentName());
		}

		student = studentDAO.updateStudent(student);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStudent :::" + student.getId());
		}
		return student;
	}
	
	/*
	@Override
	public StudentVO updateStudent(StudentVO student) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStudent :::" + student.getStudentName());
		}
		if(StringUtils.isBlank(student.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		
		StudentVO CheckAdmissionNoSchoolVO = studentDAO.getStudentAdmissionNumber(student.getAdmissionNumber(),student.getSchoolId(),student.getId());
		if(CheckAdmissionNoSchoolVO != null){
			throw new SMSBusinessException(SMSConstants.STUDENT_ADMISSIONNO_EXITS);
		}
		long countOfOccurences = studentDAO.getStudentCountByName(student.getStudentName(),student.getSurName(),student.getSchoolId(),student.getId());
		if(countOfOccurences>0){
    		throw new SMSBusinessException(SMSConstants.STUDENT_ALREADY_EXITS);
			}else{
				student = studentDAO.updateStudent(student);
			}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStudent :::" + student.getId());
		}
		return student;
	}
	*/
	
	@SuppressWarnings("unused")
	@Override
	public BulkImportStudentDetailVO updateBulkImportStudent(BulkImportStudentDetailVO bulkImportStudentDetailVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateBulkImportStudent :::");
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		ClassVO classVO =null;
    	SectionVO sectionVO = null;
    	BatchVO batch = null;
    	MediumVO mediumVO = null;
    	CourseVO courseVO = null;
    	GenderVO genderVO = null;
    	StudentVO studentVO = new StudentVO();
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getClassId())){
			classVO = classDAO.getClassById(bulkImportStudentDetailVO.getClassId());
		}
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getSectionId())){
    		sectionVO = sectionDAO.getSectionById(bulkImportStudentDetailVO.getSectionId());
		}
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getBatch())){
    		batch = batchDAO.getBatchByBatchName(bulkImportStudentDetailVO.getBatch(),bulkImportStudentDetailVO.getSchoolId());
    	}
    	
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getMedium())){
    		mediumVO = mediumDAO.getMediumByMediumName(bulkImportStudentDetailVO.getMedium(),bulkImportStudentDetailVO.getSchoolId());
    	}
    	
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getCourse())){
			courseVO = courseDAO.getCourseByCourseName(bulkImportStudentDetailVO.getCourse(),bulkImportStudentDetailVO.getSchoolId());
    	}
    	
    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getGender())){
    		genderVO = genderDAO.getGenderByName(bulkImportStudentDetailVO.getGender(),null,bulkImportStudentDetailVO.getSchoolId());
    	}
    	
    	if(mediumVO != null && batch != null && sectionVO != null && classVO != null && genderVO != null){
	    	if(bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()) &&
	    		bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()) &&
	    		batch.getBatchCode().equalsIgnoreCase(bulkImportStudentDetailVO.getBatch()) && 
	    		bulkImportStudentDetailVO.getMedium().equalsIgnoreCase(mediumVO.getMediumName())){
	    		if(courseVO == null)
	    			bulkImportStudentDetailVO.setCourse(null);
		    		
    			studentVO.setClassId(classVO.getId());
    			studentVO.setSection(sectionVO.getId());
    			studentVO.setMedium(mediumVO.getId());
    			studentVO.setGender(genderVO.getId());
    			studentVO.setBatchId(batch.getId());
    			studentVO.setSchoolId(bulkImportStudentDetailVO.getSchoolId());
    			if(courseVO != null)
    				studentVO.setCourse(courseVO.getId());
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getStudentName()))
    				studentVO.setStudentName(bulkImportStudentDetailVO.getStudentName());
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getSurName()))
    				studentVO.setSurName(bulkImportStudentDetailVO.getSurName());
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getAdmissionNo()))
    				studentVO.setAdmissionNumber(bulkImportStudentDetailVO.getAdmissionNo());
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getDateOfBirth())){
    				Date date = null;
					try {
						date = formatter.parse(bulkImportStudentDetailVO.getDateOfBirth());
						studentVO.setDob(date);
					} catch (ParseException e) {
						studentVO.setDob(date);
					}
    				
    			}
    				
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getDateOfJoinig())){
    				Date date = null;
					try {
						date = formatter.parse(bulkImportStudentDetailVO.getDateOfJoinig());
						studentVO.setAdmissionDate(date);
					} catch (ParseException e) {
						studentVO.setAdmissionDate(date);
					}
					
    			}
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getParentName1()))
    				studentVO.setParentName(bulkImportStudentDetailVO.getParentName1());
    			
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getParentName2()))
    				studentVO.setMotherName(bulkImportStudentDetailVO.getParentName2());
    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getMobileNumber()))
    				studentVO.setParentMobileNumber1(bulkImportStudentDetailVO.getMobileNumber());
    			try {
    				saveStudent(studentVO);
    				bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_COMPLETED);
    			}catch (SMSBusinessException e){
    				bulkImportStudentDetailVO.setStatus(e.getMessage());
    			}
	    	}
	    	else{
	    		if(!bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()))
	    			bulkImportStudentDetailVO.setClassName(null);
	    		if(!bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()))
	    			bulkImportStudentDetailVO.setSection(null);
	    		if(batch == null)
	    			bulkImportStudentDetailVO.setBatch(null);
	    		if(mediumVO == null)
	    			bulkImportStudentDetailVO.setMedium(null);
	    		if(courseVO == null)
	    			bulkImportStudentDetailVO.setCourse(null);
	    		bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_FAILURE);
	    	}
    	}else{
    		if(!bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()))
    			bulkImportStudentDetailVO.setClassName(null);
    		if(!bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()))
    			bulkImportStudentDetailVO.setSection(null);
    		if(batch == null)
    			bulkImportStudentDetailVO.setBatch(null);
    		if(mediumVO == null)
    			bulkImportStudentDetailVO.setMedium(null);
    		if(courseVO == null)
    			bulkImportStudentDetailVO.setCourse(null);
    			bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_FAILURE);
    	}
			bulkImportStudentDetailVO = studentDAO.updateBulkImportStudent(bulkImportStudentDetailVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateBulkImportStudent :::");
		}
		return bulkImportStudentDetailVO;
	}

	@Override
	public Map<String, Object> getAllStudent(String schoolId,int pageIndex,int pageSize) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStudent");
		}
		studentList =  studentDAO.getAllStudent(schoolId,pageIndex,pageSize);
		
		List<CountryVO> countryList = countryDAO.getAllCountryBySchoolId(schoolId,0,0);
		Map<String,String> countryMap = countryList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getCountryName()));
		
		List<StateVO> stateList = stateDAO.getAllStateBySchoolId(schoolId, 0, 0);
		Map<String,String> stateMap = stateList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getStateName()));
		
		List<CityVO> cityList = cityDAO.getAllCityBySchoolId(schoolId, 0, 0);
		Map<String,String> cityMap = cityList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getCityName()));
		
		List<GenderVO> genderList = genderDAO.getAllGenderBySchoolId(schoolId, 0, 0);
		Map<String,String> genderMap = genderList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getGender()));
		
		List<BloodGroupVO> bgList = bloodGroupDAO.getAllBloodGroupBySchoolId(schoolId, 0, 0);
		Map<String,String> bgMap = bgList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBloodgroupName()));
		
		List<BatchVO> batchList = batchDAO.getAllBatchBySchoolId(schoolId, 0, 0);
		Map<String,String> batchMap = batchList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBatchName()));
		
		List<ReligionVO> religionList = religionDAO.getAllReligionBySchoolId(schoolId, 0, 0);
		Map<String,String> relMap = religionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getReligionName()));
		
		List<CasteVO> casteList = casteDAO.getAllCasteBySchoolId(schoolId, 0, 0);
		Map<String,String> casteMap = casteList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getCasteName()));
		
		List<NationalityVO> natList = nationalityDAO.getAllNationalityBySchoolId(schoolId, 0, 0);
		Map<String,String> natMap = natList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getNationalityName()));
		
		List<MotherTongueVO> motherList = motherTongueDAO.getAllMotherTongueBySchoolId(schoolId, 0, 0);
		Map<String,String> motherMap = motherList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getMotherTongueName()));
		
		List<MediumVO> mediumList = mediumDAO.getAllMediumBySchoolId(schoolId, 0, 0);
		Map<String,String> mediumMap = mediumList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getMediumName()));
		
		List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, 0, 0);
		Map<String,String> classMap = classList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getClassName()));
		
		List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, 0, 0);
		Map<String,String> sectionMap = sectionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getSectionName()));
		
		List<CourseVO> courseList = courseDAO.getAllCourseBySchoolId(schoolId, 0, 0);
		Map<String,String> courseMap = courseList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getCourseName()));
		
		List<LanguageVO> languageList = languageDAO.getAllLanguageBySchoolId(schoolId, 0, 0);
		Map<String,String> languageMap = languageList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getLanguageName()));
		
		
		for(StudentVO studentVO : studentList){
			
			if(StringUtils.isNotBlank(studentVO.getCountryId())){
				studentVO.setCountryName(countryMap.get(studentVO.getCountryId()));
			}
			if(StringUtils.isNotBlank(studentVO.getStateId())){
				studentVO.setStateName(stateMap.get(studentVO.getStateId()));
			}
			if(StringUtils.isNotBlank(studentVO.getCityId())){
				studentVO.setCityName(cityMap.get(studentVO.getCityId()));
			}
			if(StringUtils.isNotBlank(studentVO.getGender())){
				studentVO.setGenderName(genderMap.get(studentVO.getGender()));
			}
			if(StringUtils.isNotBlank(studentVO.getBloodGroup())){
				studentVO.setBloodGroupName(bgMap.get(studentVO.getBloodGroup()));
			}
			if(StringUtils.isNotBlank(studentVO.getBatchId())){
				studentVO.setBatchName(batchMap.get(studentVO.getBatchId()));
			}
			if(StringUtils.isNotBlank(studentVO.getReligion())){
				studentVO.setReligionName(relMap.get(studentVO.getReligion()));
			}
			if(StringUtils.isNotBlank(studentVO.getCaste())){
				studentVO.setCasteName(casteMap.get(studentVO.getCaste()));
			}
			if(StringUtils.isNotBlank(studentVO.getNationality())){
				studentVO.setNationalityName(natMap.get(studentVO.getNationality()));
			}
			if(StringUtils.isNotBlank(studentVO.getMohterTongue())){
				studentVO.setMohterTongueName(motherMap.get(studentVO.getMohterTongue()));
			}
			if(StringUtils.isNotBlank(studentVO.getMedium())){
				studentVO.setMediumName(mediumMap.get(studentVO.getMedium()));
			}
			if(StringUtils.isNotBlank(studentVO.getClassId())){
				studentVO.setClassName(classMap.get(studentVO.getClassId()));
			}
			if(StringUtils.isNotBlank(studentVO.getSection())){
				studentVO.setSectionName(sectionMap.get(studentVO.getSection()));
			}
			if(StringUtils.isNotBlank(studentVO.getCourse())){
				studentVO.setCourseName(courseMap.get(studentVO.getCourse()));
			}
			if(StringUtils.isNotBlank(studentVO.getFirstLanguage())){
				studentVO.setFirstLanguageName(languageMap.get(studentVO.getFirstLanguage()));
			}
			if(StringUtils.isNotBlank(studentVO.getSecondLanguage())){
				studentVO.setSecondLanguageName(languageMap.get(studentVO.getSecondLanguage()));
			}
			if(StringUtils.isNotBlank(studentVO.getParentGender())){
				studentVO.setParentGenderName(genderMap.get(studentVO.getParentGender()));
			}
		}
		long totalRecords = studentDAO.getStudentCountBySchoolId(schoolId);
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("studentList", studentList);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStudent");
		}
		return responseObjectsMap;	
	}
	
	@Override
	public List<StudentVO> getStudentBySearchString(String searchString,int skip, int limit) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentBySearchString");
		}
		studentList =  studentDAO.getStudentBySearchString(searchString, skip, limit);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentBySearchString");
		}
		return studentList;	
	}
	
	@Override
	public StudentVO getStudentByIdOrNo(String schoolId,String studentId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentByIdOrNo");
		}
		StudentVO studentVO =  studentDAO.getStudentByIdOrNo(schoolId,studentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentByIdOrNo");
		}
		return studentVO;	
	}
	
	@Override
	public List<StudentVO> getALLStudentsByClassAndSection(String schoolId,String standard,String section) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLStudentsByClassAndSection");
		}
		
		List<StudentVO> studentList =  studentDAO.getStudentsByClassAndSection(schoolId,standard,section); 
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLStudentsByClassAndSection");
		}
		return studentList;	
	}
	
	@Override
	public List<StudentVO> getALLStudentsByClassAndSectionAndGender(String schoolId,String standard,String section
			, String gender) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLStudentsByClassAndSectionAndGender");
		}
		
		List<String> sectionList = new ArrayList<>();
		sectionList.add(section);
		List<StudentVO> studentList =  studentDAO.getAllStudentByListSectionAndGender(schoolId,sectionList,standard,gender); 
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLStudentsByClassAndSectionAndGender");
		}
		return studentList;	
	}
	
	@Override
	public List<BulkImportStudentDetailVO> getUploadStudentsByClassAndSection(String schoolId,String classId,
			String sectionId,String batchId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getUploadStudentsByClassAndSection");
		}
		
		List<BulkImportStudentDetailVO> studentList =  studentDAO.getUploadStudentsByClassAndSection(schoolId,classId,sectionId,batchId); 
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getUploadStudentsByClassAndSection");
		}
		return studentList;	
	}
	
	@Override
	public boolean deleteStudent(String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteStudent Starts");
		}
		
		boolean isDeleted = studentDAO.deleteStudent(studentId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteStudent Ends");
		}
		return isDeleted;
	}
	
	@SuppressWarnings("unused")
	@Override
	public Map<String, Object> bulkImportStudentDetails(String schoolId, String classId,String sectionId, MultipartFile[] file) throws SMSBusinessException, Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("bulkImportStudentDetails() starts");
		}
		int successCount = 0;
		int failureCount = 0;
		List<BulkImportStudentDetailVO> failureList = new ArrayList<BulkImportStudentDetailVO>();
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		if(file[0] != null && !file[0].isEmpty()) {
			InputStream xmlStream = this.getClass().getResourceAsStream("/StudentXLSMapping.xml");
			List<BulkImportStudentDetailVO> candidateList = SMSUtility.parseExcelFileToBeans(file[0], xmlStream);
			if(candidateList.size() > 0){
			    for(BulkImportStudentDetailVO bulkImportStudentDetailVO :candidateList){
			    	/*try {
			    		bulkImportStudentDetailVO.setSchoolId(schoolId);
			    		bulkImportStudentDetailVO.setClassId(classId);
			    		bulkImportStudentDetailVO.setSectionId(sectionId);
			    		studentDAO.saveBulkImportStudent(bulkImportStudentDetailVO);
			    		successCount++;
			    	} catch (Throwable e) {
			    		//bulkImportStudentDetailVO.setErrorMsg(e.getMessage());
			    		bulkImportStudentDetailVO.setSchoolId(schoolId);
			    		bulkImportStudentDetailVO.setClassId(classId);
			    		bulkImportStudentDetailVO.setSectionId(sectionId);
			    		failureList.add(bulkImportStudentDetailVO);
			    		logger.error(e.getMessage());
			    		failureCount++;
			    	}*/
			    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			    	ClassVO classVO =null;
			    	SectionVO sectionVO = null;
			    	BatchVO batch = null;
			    	MediumVO mediumVO = null;
			    	CourseVO courseVO = null;
			    	StudentVO studentVO = new StudentVO();
			    	GenderVO genderVO = null;
			    	bulkImportStudentDetailVO.setSchoolId(schoolId);
		    		bulkImportStudentDetailVO.setClassId(classId);
		    		bulkImportStudentDetailVO.setSectionId(sectionId);
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getClassId())){
						classVO = classDAO.getClassById(bulkImportStudentDetailVO.getClassId());
					}
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getSectionId())){
			    		sectionVO = sectionDAO.getSectionById(bulkImportStudentDetailVO.getSectionId());
					}
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getBatch())){
			    		batch = batchDAO.getBatchByBatchName(bulkImportStudentDetailVO.getBatch(),bulkImportStudentDetailVO.getSchoolId());
			    	}
			    	
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getMedium())){
			    		mediumVO = mediumDAO.getMediumByMediumName(bulkImportStudentDetailVO.getMedium(),bulkImportStudentDetailVO.getSchoolId());
			    	}
			    	
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getCourse())){
		    			courseVO = courseDAO.getCourseByCourseName(bulkImportStudentDetailVO.getCourse(),bulkImportStudentDetailVO.getSchoolId());
			    	}
			    	
			    	if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getGender())){
			    		genderVO = genderDAO.getGenderByName(bulkImportStudentDetailVO.getGender(),null,bulkImportStudentDetailVO.getSchoolId());
			    	}
			    	
			    	if(mediumVO != null && batch != null && sectionVO != null && classVO != null){
				    	if(bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()) &&
				    		bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()) &&
				    		batch.getBatchCode().equalsIgnoreCase(bulkImportStudentDetailVO.getBatch()) && 
				    		bulkImportStudentDetailVO.getMedium().equalsIgnoreCase(mediumVO.getMediumName())){
				    		
				    		if(courseVO == null)
				    			bulkImportStudentDetailVO.setCourse(null);
					    		
				    		successCount++;
				    		studentVO.setClassId(classVO.getId());
			    			studentVO.setSection(sectionVO.getId());
			    			studentVO.setMedium(mediumVO.getId());
			    			studentVO.setGender(genderVO.getId());
			    			studentVO.setBatchId(batch.getId());
			    			studentVO.setSchoolId(bulkImportStudentDetailVO.getSchoolId());
			    			if(courseVO != null)
			    				studentVO.setCourse(courseVO.getId());
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getStudentName()))
			    				studentVO.setStudentName(bulkImportStudentDetailVO.getStudentName());
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getSurName()))
			    				studentVO.setSurName(bulkImportStudentDetailVO.getSurName());
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getAdmissionNo()))
			    				studentVO.setAdmissionNumber(bulkImportStudentDetailVO.getAdmissionNo());
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getDateOfBirth())){
			    				Date date = null;
								try {
									date = formatter.parse(bulkImportStudentDetailVO.getDateOfBirth());
									studentVO.setDob(date);
								} catch (ParseException e) {
									studentVO.setDob(date);
								}
			    				
			    			}
			    				
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getDateOfJoinig())){
			    				Date date = null;
								try {
									date = formatter.parse(bulkImportStudentDetailVO.getDateOfJoinig());
									studentVO.setAdmissionDate(date);
								} catch (ParseException e) {
									studentVO.setAdmissionDate(date);
								}
								
			    			}
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getParentName1()))
			    				studentVO.setParentName(bulkImportStudentDetailVO.getParentName1());
			    			
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getParentName2()))
			    				studentVO.setMotherName(bulkImportStudentDetailVO.getParentName2());
			    			if(StringUtils.isNotBlank(bulkImportStudentDetailVO.getMobileNumber()))
			    				studentVO.setParentMobileNumber1(bulkImportStudentDetailVO.getMobileNumber());
			    			try {
			    				saveStudent(studentVO);
			    				bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_COMPLETED);
			    			}catch (SMSBusinessException e){
			    				bulkImportStudentDetailVO.setStatus(e.getMessage());
			    			}
				    	}
				    	else{
				    		if(!bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()))
				    			bulkImportStudentDetailVO.setClassName(null);
				    		if(!bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()))
				    			bulkImportStudentDetailVO.setSection(null);
				    		if(batch == null)
				    			bulkImportStudentDetailVO.setBatch(null);
				    		if(mediumVO == null)
				    			bulkImportStudentDetailVO.setMedium(null);
				    		if(courseVO == null)
				    			bulkImportStudentDetailVO.setCourse(null);
				    		bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_FAILURE);
				    		failureList.add(bulkImportStudentDetailVO);
				    		failureCount++;
				    	}
			    	}else{
			    		if(!bulkImportStudentDetailVO.getClassName().equalsIgnoreCase(classVO.getClassName()))
			    			bulkImportStudentDetailVO.setClassName(null);
			    		if(!bulkImportStudentDetailVO.getSection().equalsIgnoreCase(sectionVO.getSectionName()))
			    			bulkImportStudentDetailVO.setSection(null);
			    		if(batch == null)
			    			bulkImportStudentDetailVO.setBatch(null);
			    		if(mediumVO == null)
			    			bulkImportStudentDetailVO.setMedium(null);
			    		if(courseVO == null)
			    			bulkImportStudentDetailVO.setCourse(null);
			    		
			    		bulkImportStudentDetailVO.setStatus(SMSConstants.FILEUPLOAD_FAILURE);
			    		failureList.add(bulkImportStudentDetailVO);
			    		failureCount++;
			    	}
			    	studentDAO.saveBulkImportStudent(bulkImportStudentDetailVO);
			    }
			    responseObjectMap.put("successCount", successCount);
			    responseObjectMap.put("failureCount", failureCount);
			    responseObjectMap.put("failureList", failureList);
			}else{
				throw new SMSBusinessException("file is empty");
			}
		} else {
			logger.error("file is empty");
			throw new SMSBusinessException("file is empty");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("bulkImportStudentDetails() ends");
		}
		return responseObjectMap;
	}
	@Override
	public List<StudentVO> getStudentsByMobileNoAndSchoolId(String schoolId, String mobileNo)
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByMobileNoAndSchoolId");
		}
		
		List<StudentVO> studentList =  studentDAO.getStudentsByMobileNoAndSchoolId(schoolId,mobileNo);
		SchoolVO schoolVO = null;
		if(StringUtils.isNotBlank(schoolId)){
			schoolVO = schoolDAO.getSchoolById(schoolId);
		}
 		for(StudentVO studentVO : studentList) {
			studentVO.setSchoolName(schoolVO.getSchoolName());
			if(StringUtils.isNotBlank(studentVO.getClassId())){
				ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
				studentVO.setClassName(classVO.getClassName());
			}
			if(StringUtils.isNotBlank(studentVO.getSection())){
				SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
				studentVO.setSectionName(sectionVO.getSectionName());
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByMobileNoAndSchoolId");
		}
		return studentList;	
	}
	
	@Override
	public List<TeacherVO> getStudentsAllocateTeacher(String studentId,String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsAllocateTeacher");
		}
		 
		SchoolVO schoolVO = schoolDAO.getSchoolById(schoolId);
		if(schoolVO == null) {
			throw new SMSBusinessException(ErrorConstants.SCHOOL_INFORMATION_NOT_FOUND);
		}
		StudentVO studentVO =  studentDAO.getStudentByStudentId(studentId,schoolId);
		if(studentVO == null) {
			throw new SMSBusinessException(ErrorConstants.STUDENT_INFORMATION_NOT_FOUND);
		}
		studentVO.setSchoolName(schoolVO.getSchoolName());
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		//BatchConfigurationVO batchConfigurationVO = batchConfigurationDAO.getTeacher(studentVO.getClassName(),studentVO.getSection(),studentVO.getSchoolName());
		List<String> teacherIds = batchConfigurationDAO.getAllTeacherIdsByClassAndSectionId(studentVO.getClassId(),studentVO.getSection(),studentVO.getSchoolId(),studentVO.getBatchId());
		List<TeacherVO> teacherVOs = teacherDAO.getAllTeacherByIds(teacherIds);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsAllocateTeacher");
		}
		return  teacherVOs;
		
	}

	@Override
	public StudentVO getviewStudent(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewStudent Id:::" + id);
		}
		
		StudentVO studentVO =  studentDAO.getStudentView(id);
		
		if(StringUtils.isNotBlank(studentVO.getCountryId())){
			CountryVO countryVO = countryDAO.getCountryById(studentVO.getCountryId());
			studentVO.setCountryName(countryVO.getCountryName());
		}
		if(StringUtils.isNotBlank(studentVO.getStateId())){
			StateVO stateVO = stateDAO.getStateById(studentVO.getStateId());
			studentVO.setStateName(stateVO.getStateName());
		}
		if(StringUtils.isNotBlank(studentVO.getCityId())){
			CityVO cityVO = cityDAO.getCityById(studentVO.getCityId());
			studentVO.setCityName(cityVO.getCityName());
		}
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		if(StringUtils.isNotBlank(studentVO.getGender())){
			GenderVO genderVO = genderDAO.getGenderById(studentVO.getGender());
			studentVO.setGenderName(genderVO.getGender());
		}
		if(StringUtils.isNotBlank(studentVO.getBloodGroup())){
			BloodGroupVO bloodGroupVO = bloodGroupDAO.getBloodGroupById(studentVO.getBloodGroup());
			studentVO.setBloodGroupName(bloodGroupVO.getBloodgroupName());
		}
		if(StringUtils.isNotBlank(studentVO.getReligion())){
			ReligionVO religionVO = religionDAO.getReligionById(studentVO.getReligion());
			studentVO.setReligionName(religionVO.getReligionName());
		}
		if(StringUtils.isNotBlank(studentVO.getCaste())){
			CasteVO casteVO = casteDAO.getCasteById(studentVO.getCaste());
			studentVO.setCasteName(casteVO.getCasteName());
		}
		if(StringUtils.isNotBlank(studentVO.getNationality())){
			NationalityVO nationalityVO = nationalityDAO.getNationalityById(studentVO.getNationality());
			studentVO.setNationalityName(nationalityVO.getNationalityName());
		}
		if(StringUtils.isNotBlank(studentVO.getMohterTongue())){
			MotherTongueVO motherTongueVO = motherTongueDAO.getMotherTongueById(studentVO.getMohterTongue());
			studentVO.setMohterTongueName(motherTongueVO.getMotherTongueName());
		}
		if(StringUtils.isNotBlank(studentVO.getMedium())){
			MediumVO mediumVO = mediumDAO.getMediumById(studentVO.getMedium());
			studentVO.setMediumName(mediumVO.getMediumName());
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		if(StringUtils.isNotBlank(studentVO.getCourse())){
			CourseVO courseVO = courseDAO.getCourseById(studentVO.getCourse());
			studentVO.setCourseName(courseVO.getCourseName());
		}
		if(StringUtils.isNotBlank(studentVO.getFirstLanguage())){
			LanguageVO languageVO = languageDAO.getLanguageById(studentVO.getFirstLanguage());
			studentVO.setFirstLanguageName(languageVO.getLanguageName());
		}
		if(StringUtils.isNotBlank(studentVO.getSecondLanguage())){
			LanguageVO sndLanguageVO = languageDAO.getLanguageById(studentVO.getSecondLanguage());
			studentVO.setSecondLanguageName(sndLanguageVO.getLanguageName());
		}
		if(StringUtils.isNotBlank(studentVO.getParentGender())){
			GenderVO parentGenderVO = genderDAO.getGenderById(studentVO.getParentGender());
			studentVO.setParentGenderName(parentGenderVO.getGender());
		}
		
		if(StringUtils.isNotBlank(studentVO.getSchoolId())){
			SchoolVO schoolVO = schoolDAO.getSchoolById(studentVO.getSchoolId());
			studentVO.setSchoolName(schoolVO.getSchoolName());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End viewStudent :::" + studentVO.getId());
		}
		return studentVO;
	}
	
}
