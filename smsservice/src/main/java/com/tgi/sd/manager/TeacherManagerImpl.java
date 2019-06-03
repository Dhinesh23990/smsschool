/**
 * 
 */
package com.tgi.sd.manager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.AttendanceDAO;
import com.tgi.sd.dao.BatchConfigurationDAO;
import com.tgi.sd.dao.BloodGroupDAO;
import com.tgi.sd.dao.CasteDAO;
import com.tgi.sd.dao.CityDAO;
import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.GenderDAO;
import com.tgi.sd.dao.MotherTongueDAO;
import com.tgi.sd.dao.NationalityDAO;
import com.tgi.sd.dao.ReligionDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.StateDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

/**
 * @author SGSAuthour
 *
 */
public class TeacherManagerImpl implements TeacherManager {

	private static Logger logger = Logger.getLogger(TeacherManagerImpl.class);

	private TeacherDAO teacherDAO;

	private UserManager userManager;
	
	private AttendanceDAO attendanceDAO;
	
	private StudentDAO studentDAO;
	
	private CityDAO cityDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	private GenderDAO genderDAO;
	
	private BloodGroupDAO bloodGroupDAO;
	
	private ReligionDAO religionDAO;
	
	private CasteDAO casteDAO;
	
	private NationalityDAO nationalityDAO;
	
	private MotherTongueDAO motherTongueDAO;
	
	private SchoolDAO schoolDAO;
	
	private BatchConfigurationDAO batchConfigurationDAO;
	
	public AttendanceDAO getAttendanceDAO() {
		return attendanceDAO;
	}

	public void setAttendanceDAO(AttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}
	
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
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
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public TeacherVO saveTeacher(TeacherVO teacher) throws SMSBusinessException {
		teacher.setId(UUID.randomUUID().toString());

		if (logger.isDebugEnabled()) {
			logger.debug("Start saveTeacher :::" + teacher.getStaffName());
		}
		
		TeacherVO CheckEmployeeNoTeacherVO = teacherDAO.getTeacherEmployeeNumber(teacher.getEmployeeNumber(),teacher.getSchoolId(),null);
		if(CheckEmployeeNoTeacherVO != null){
			throw new SMSBusinessException(SMSConstants.TEACHER_EMPLOYEENO_ALREADY_EXISTS);
		}
		
		long countOfOccurences = teacherDAO.getTeacherCountByNameAndInitial(teacher.getStaffName(),teacher.getInitial(),teacher.getSchoolId(),null);
		
		if(countOfOccurences>0){
    		throw new SMSBusinessException(SMSConstants.TEACHER_ALREADY_EXISTS);
			}else{
				GregorianCalendar calendar = new GregorianCalendar();
				calendar.setTime(teacher.getDateOfJoining());
				String year = String.valueOf(calendar.get(Calendar.YEAR));
				SchoolVO schoolVO = schoolDAO.getSchoolById(teacher.getSchoolId());
				//long teacherCount = teacherDAO.getAllTeacherCountByDateOfJoiningYear(teacher.getDateOfJoining(),teacher.getSchoolId());
				long teacherCount = teacherDAO.getTeacherCountBySchoolId(teacher.getSchoolId());
				int number = (int) teacherCount+1;
 				String str = String.format("%04d", number);
 				String substring = year.substring(Math.max(year.length() - 2, 0));
 				String teacherId = schoolVO.getSchoolCode()+substring+"T"+str;
 				teacher.setStaffId(teacherId);
				teacher = teacherDAO.saveTeacher(teacher);
			}
		/*String password = RandomStringUtils.randomAlphanumeric(7);

		UserVO user = SMSUtil.populateUserVO(teacher.getStaffName(), teacher.getEmailId(), password , SMSConstants.TEACHER, teacher.getSchoolId());

		userManager.saveUser(user);
		teacher.setUserOid(user.getId());
		updateTeacher(teacher);
		StringBuffer message = new StringBuffer();
		//message.append(SystemProperties.getProperty(SMSConstants.SMS_PARENT_REG_MESSAGE));
		message.append("").append("LoginId :");
		message.append(teacher.getEmailId()).append("");
		message.append("Password :");
		message.append(password);
		SMS.sendSMS(teacher.getMobileNumber(), message.toString());*/

		if (logger.isDebugEnabled()) {
			logger.debug("End saveTeacher :::" + teacher.getUserOid());
		}
		return teacher;
	}

	public TeacherVO getTeacher(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeacher Id:::" + id);
		}

		TeacherVO teacher = teacherDAO.getTeacher(id);

		if (logger.isDebugEnabled()) {
			logger.debug("End getTeacher :::" + teacher.getId());
		}
		return teacher;
	}

	public TeacherVO viewTeacher(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewTeacher Id:::" + id);
		}

		TeacherVO teacherVO = teacherDAO.getTeacher(id);
		
		if(StringUtils.isNotBlank(teacherVO.getCountry())){
			CountryVO countryVO = countryDAO.getCountryById(teacherVO.getCountry());
			teacherVO.setCountryName(countryVO.getCountryName());
		}
		if(StringUtils.isNotBlank(teacherVO.getState())){
			StateVO stateVO = stateDAO.getStateById(teacherVO.getState());
			teacherVO.setStateName(stateVO.getStateName());
		}
		if(StringUtils.isNotBlank(teacherVO.getCity())){
			CityVO cityVO = cityDAO.getCityById(teacherVO.getCity());
			teacherVO.setCityName(cityVO.getCityName());
		}
		if(StringUtils.isNotBlank(teacherVO.getGender())){
			GenderVO genderVO = genderDAO.getGenderById(teacherVO.getGender());
			teacherVO.setGenderName(genderVO.getGender());
		}
		if(StringUtils.isNotBlank(teacherVO.getBlooGroup())){
			BloodGroupVO bloodGroupVO = bloodGroupDAO.getBloodGroupById(teacherVO.getBlooGroup());
			teacherVO.setBloodGroupName(bloodGroupVO.getBloodgroupName());
		}
		if(StringUtils.isNotBlank(teacherVO.getReligion())){
			ReligionVO religionVO = religionDAO.getReligionById(teacherVO.getReligion());
			teacherVO.setReligionName(religionVO.getReligionName());
		}
		if(StringUtils.isNotBlank(teacherVO.getCaste())){
			CasteVO casteVO = casteDAO.getCasteById(teacherVO.getCaste());
			teacherVO.setCasteName(casteVO.getCasteName());
		}
		if(StringUtils.isNotBlank(teacherVO.getNationality())){
			NationalityVO nationalityVO = nationalityDAO.getNationalityById(teacherVO.getNationality());
			teacherVO.setNationalityName(nationalityVO.getNationalityName());
		}
		if(StringUtils.isNotBlank(teacherVO.getMotherTongue())){
			MotherTongueVO motherTongueVO = motherTongueDAO.getMotherTongueById(teacherVO.getMotherTongue());
			teacherVO.setMohterTongueName(motherTongueVO.getMotherTongueName());
		}
		
		BatchConfigurationVO batchConfiguration = batchConfigurationDAO.getBatchConfigByStaffId(teacherVO.getId(),teacherVO.getSchoolId());	
		if(batchConfiguration!=null){
			
			List<StudentVO> studentList =  studentDAO.getAllStudentVOByBatchCode(batchConfiguration.getBatchId(),batchConfiguration.getClassId(),
					batchConfiguration.getSectionId(),batchConfiguration.getSchoolId());
			int studentCount = studentList.size();
			teacherVO.setTotalStudentCount(studentCount);
			Date d1 = new Date();
			int presentStudents = 0,absentstudents =0;
			for(StudentVO studentVO : studentList){
				List<AttendanceVO> attendanceList =  (List<AttendanceVO>) attendanceDAO.getStudentsByStudentIdAndDate(studentVO.getSchoolId(),
						studentVO.getStudentId(),d1,null);
				if(attendanceList != null){
					for(AttendanceVO attendanceVO :attendanceList){
						if(attendanceVO.getFullDay() || (attendanceVO.getAfternoon() && attendanceVO.getMorning())){
							presentStudents = presentStudents+1;
						}else{
							absentstudents = absentstudents +1;
						}
						
					}
				}
			}
			//double studentpercenteage = (presentStudents/studentCount)*100;
			//teacherVO.setStudentAttendancePercentage(studentpercenteage);
			teacherVO.setPresentStudents(presentStudents);
			teacherVO.setAbsentStudents(absentstudents);
			
		}

		if (logger.isDebugEnabled()) {
			logger.debug("End viewTeacher :::" + teacherVO.getId());
		}
		return teacherVO;
	}
	
	
	@Override
	public TeacherVO updateTeacher(TeacherVO teacher) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateTeacher :::" + teacher.getStaffName());
		}

		teacher = teacherDAO.updateTeacher(teacher);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateTeacher :::" + teacher.getId());
		}
		return teacher;
	}
	
	/*
	@Override
	public TeacherVO updateTeacher(TeacherVO teacher) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateTeacher :::" + teacher.getStaffName());
		}
		TeacherVO CheckEmployeeNoTeacherVO = teacherDAO.getTeacherEmployeeNumber(teacher.getEmployeeNumber(),teacher.getSchoolId(),teacher.getId());
		if(CheckEmployeeNoTeacherVO != null){
			throw new SMSBusinessException(SMSConstants.TEACHER_EMPLOYEENO_ALREADY_EXISTS);
		}
		
		long countOfOccurences = teacherDAO.getTeacherCountByNameAndInitial(teacher.getStaffName(),teacher.getInitial(),teacher.getSchoolId(),teacher.getId());
		
		if(countOfOccurences>0){
    		throw new SMSBusinessException(SMSConstants.TEACHER_ALREADY_EXISTS);
		}else{
			teacher = teacherDAO.updateTeacher(teacher);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateTeacher :::" + teacher.getId());
		}
		return teacher;
	}
	*/
	
	@Override
	public List<TeacherVO> getAllTeacherByGender(String schoolId,String gender) throws SMSBusinessException {
		List<TeacherVO> teacherList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacherByGender");
		}
		teacherList = teacherDAO.getAllTeacherByGender(schoolId,gender);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacherByGender");
		}
		return teacherList;
	}

	@Override
	public Map<String, Object> getAllTeacher(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException {
		List<TeacherVO> teacherList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacher");
		}
		teacherList = teacherDAO.getAllTeacher(schoolId,pageIndex,pageSize);
		
		
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
			
		List<ReligionVO> religionList = religionDAO.getAllReligionBySchoolId(schoolId, 0, 0);
		Map<String,String> relMap = religionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getReligionName()));
		
		List<CasteVO> casteList = casteDAO.getAllCasteBySchoolId(schoolId, 0, 0);
		Map<String,String> casteMap = casteList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getCasteName()));
		
		List<NationalityVO> natList = nationalityDAO.getAllNationalityBySchoolId(schoolId, 0, 0);
		Map<String,String> natMap = natList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getNationalityName()));
		
		List<MotherTongueVO> motherList = motherTongueDAO.getAllMotherTongueBySchoolId(schoolId, 0, 0);
		Map<String,String> motherMap = motherList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getMotherTongueName()));		
		
		for(TeacherVO teacherVO : teacherList){
			
			if(StringUtils.isNotBlank(teacherVO.getCountry())){
				teacherVO.setCountryName(countryMap.get(teacherVO.getCountry()));
			}
			if(StringUtils.isNotBlank(teacherVO.getState())){
				teacherVO.setStateName(stateMap.get(teacherVO.getState()));
			}
			if(StringUtils.isNotBlank(teacherVO.getCity())){
				teacherVO.setCityName(cityMap.get(teacherVO.getCity()));
			}
			if(StringUtils.isNotBlank(teacherVO.getGender())){
				teacherVO.setGenderName(genderMap.get(teacherVO.getGender()));
			}
			if(StringUtils.isNotBlank(teacherVO.getBlooGroup())){
				teacherVO.setBloodGroupName(bgMap.get(teacherVO.getBlooGroup()));
			}
			if(StringUtils.isNotBlank(teacherVO.getReligion())){
				teacherVO.setReligionName(relMap.get(teacherVO.getReligion()));
			}
			if(StringUtils.isNotBlank(teacherVO.getCaste())){
				teacherVO.setCasteName(casteMap.get(teacherVO.getCaste()));
			}
			if(StringUtils.isNotBlank(teacherVO.getNationality())){
				teacherVO.setNationalityName(natMap.get(teacherVO.getNationality()));
			}
			if(StringUtils.isNotBlank(teacherVO.getMotherTongue())){
				teacherVO.setMohterTongueName(motherMap.get(teacherVO.getMotherTongue()));
			}			
		}
		
		long totalRecords = teacherDAO.getTeacherCountBySchoolId(schoolId);
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("teacherList", teacherList);

		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacher");
		}
		return responseObjectsMap;
	}
	
	@Override
	public boolean deleteTeacher(String teacherId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Starts");
		}
		
		boolean isDeleted = teacherDAO.deleteTeacher(teacherId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Ends");
		}
		return isDeleted;
	}
}
