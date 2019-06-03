/**
 * 
 */
package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tgi.sd.dao.AdminDAO;
import com.tgi.sd.dao.BatchConfigurationDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.ParentDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.SmsDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.domain.ComposeSMS;
import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.SmsHistoryVO;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.BulkSMS;
import com.tgi.sd.util.SMS;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtility;

/**
 * @author SGSAuthour
 *
 */
@Component
public class SmsManagerImpl implements SmsManager {
	
	private static Logger logger = Logger.getLogger(SmsManagerImpl.class);

	
	SmsDAO smsDAO;
	
	StudentDAO studentDAO;
	
	ParentDAO parentDAO;
	
	UserDAO userDAO;
	
	TeacherDAO teacherDAO;
	
	AdminDAO adminDAO;
	
	private BatchConfigurationDAO batchConfigurationDAO;
	
	private SchoolDAO schoolDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	public SmsDAO getSmsDAO() {
		return smsDAO;
	}
	
	public void setSmsDAO(SmsDAO smsDAO) {
		this.smsDAO = smsDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	public ParentDAO getParentDAO() {
		return parentDAO;
	}
	
	public void setParentDAO(ParentDAO parentDAO) {
		this.parentDAO = parentDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public void setUserDAO(UserDAO studentDAO) {
		this.userDAO = userDAO;
	}
	
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}
	
	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
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
	
	public static String otpUserName = SMSUtility.getResourceValue("otp.user.name");
	public static String otpUserPassword = SMSUtility.getResourceValue("otp.user.password"); 
	public static String otpSenderId = SMSUtility.getResourceValue("otp.user.senderId");
	public static String otpService = SMSUtility.getResourceValue("otp.user.service"); 

	@Override
	public Map<String, Object> getAllSmsLog(String schoolId,int pageIndex,int pageSize) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsLog");
		}
		 List<SmsLogVO>  smsLogList =  smsDAO.getAllSmsLog(schoolId,pageIndex,pageSize);
		 
		 long totalRecords = smsDAO.getSmsLogCountBySchoolId(schoolId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
	    	responseObjectsMap.put("smsLogList", smsLogList);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsLog");
		}
		return responseObjectsMap;	
	}

	@Override
	public Map<String, Object> getAllSmsHistoryBySmsLogId(String smsLogId,String status,int pageIndex, int pageSize) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsHistoryBySmsLogId");
		}
		 List<SmsHistoryVO>  smsLogList =  smsDAO.getAllSmsHistory(smsLogId,status,pageIndex,pageSize);
		 
		 long totalRecords = smsDAO.getSmsHistoryBySchoolId(smsLogId,status);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
	    	responseObjectsMap.put("smsLogList", smsLogList);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsHistoryBySmsLogId");
		}
		return responseObjectsMap;	
		}

	@Override
	public SmsLogVO sendSMS(ComposeSMS composeSMS) throws SMSBusinessException {
		
		if(logger.isDebugEnabled()){
			logger.debug(composeSMS.getDistributionType());
		}
		List<String> sendToList = new ArrayList<>();
		SmsLogVO smsLogVO = new SmsLogVO();
		smsLogVO.setId(UUID.randomUUID().toString());

		String templateType = composeSMS.getTemplateType();
		String message = composeSMS.getMessage();
		String gender = composeSMS.getGender();
		smsLogVO.setSmsDescription(message);
		if(!composeSMS.getSendTo().isEmpty()){
			sendToList = composeSMS.getSendTo();
		}
		/*if(StringUtils.isBlank(composeSMS.getSendTo())){
			sendToList = Arrays.asList(composeSMS.getSendTo().split(","));
		}*/
		boolean isParent = false;
		if(sendToList.contains(SMSConstants.SCHOOL_PARENT1) || sendToList.contains(SMSConstants.SCHOOL_PARENT2)){
			isParent = true;
		}
		StringBuffer mobNumber = new StringBuffer();
		smsLogVO.setSendTo(sendToList);
		//((StringBuffer) smsLogVO.setSendTo).append(composeSMS.getSendTo()).append(",");
		smsLogVO.setSmsType(composeSMS.getTemplateType());
		smsLogVO.setSendOn(GregorianCalendar.getInstance().getTime());
		smsLogVO.setSmsCount(1);
		
		if(composeSMS.getDistributionType().equals(SMSConstants.STUDENT_WISE)){

			sendSMSForStudentWise(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.CLASS_WISE)){

			sendSMSForClassWise(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.SECTION_WISE)){
			
			sendSMSForSectionWise(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.ALL_STUDENTS)){
			
			sendSMSForAllStudent(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.INDIVIDUAL_STUDENTS)){
			
			if(!composeSMS.getReceiverIds().isEmpty()){
				sendSMSForIndividualStudentWise(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
			}else{
					throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
			}
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.ALL_TEACHERS)){
			sendSMSForAllTeachers(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.INDIVIDUAL_TEACHERS)){
			
			if(!composeSMS.getReceiverIds().isEmpty()){
				sendSMSForIndividualTeachers(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
			}else{
					throw new SMSBusinessException(SMSConstants.TEACHER_IDS_ARE_EMPTY);
			}
		}
		
		if(composeSMS.getDistributionType().equals(SMSConstants.ALL_ADMIN)){
			sendSMSForAllAdmin(composeSMS, sendToList, smsLogVO, templateType, message, gender, isParent, mobNumber);
		}
		return smsLogVO;
	}

	private void sendSMSForIndividualTeachers(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			List<String> receiverIds = composeSMS.getReceiverIds();
				List<TeacherVO> teacherList = teacherDAO.getAllTeacherByIds(receiverIds);
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				int findcountlength = 0;
				
				for (TeacherVO teacherVO : teacherList) {
					if(schoolVO.getSmsBalanceCount() > findcountlength){
						SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
						smsHistoryVO.setId(UUID.randomUUID().toString()); 
						smsHistoryVO.setSmsLogId(smsLogVO.getId());
						smsHistoryVO.setMessage(message);
						smsHistoryVO.setStudentName(teacherVO.getStaffName());
						smsHistoryVO.setStudentId(teacherVO.getStaffId());
						smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
						mobNumber.append(teacherVO.getMobileNumber()).append(",");
						smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());	
						String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
						smsResponse = smsResponse.replaceAll("\\s+","");
						if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
						}else{
							findcountlength++;
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
						}
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
				}
				
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);			
						}
					}
				}
				smsLogVO.setSchoolId(composeSMS.getSchoolId());
				smsLogVO.setSmsCount(findcountlength);
				
				schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
				schoolDAO.updateSchool(schoolVO);
				smsDAO.saveSmsLog(smsLogVO);
		}
	private void sendSMSForAllTeachers(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			//if(templateType.equals("USER_DEFINED"))
			//{
				
				List<TeacherVO> teacherList = new ArrayList<>();
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				if(StringUtils.isNotBlank(gender)){
					teacherList =  teacherDAO.getAllTeacherByGender(composeSMS.getSchoolId(),gender);
				}else{
					teacherList = teacherDAO.getAllTeacher(composeSMS.getSchoolId(),null,null);
				}
				
				if(teacherList.size() == 0){
					throw new SMSBusinessException(SMSConstants.TEACHER_IDS_ARE_EMPTY);
				}
				
				for (TeacherVO teacherVO : teacherList) {
					if(schoolVO.getSmsBalanceCount() > findcountlength){
						SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
						smsHistoryVO.setId(UUID.randomUUID().toString()); 
						smsHistoryVO.setSmsLogId(smsLogVO.getId());
						smsHistoryVO.setMessage(message);
						smsHistoryVO.setStudentName(teacherVO.getStaffName());
						smsHistoryVO.setStudentId(teacherVO.getStaffId());
						smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
						mobNumber.append(teacherVO.getMobileNumber()).append(",");
						smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());
						String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
						smsResponse = smsResponse.replaceAll("\\s+","");
						if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
						}else{
							findcountlength++;
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
						}
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
				}
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);		
						}
					}
				}
				smsLogVO.setSchoolId(composeSMS.getSchoolId());
				schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
				schoolDAO.updateSchool(schoolVO);
				smsLogVO.setSmsCount(findcountlength);
				
			smsDAO.saveSmsLog(smsLogVO);
		}
	
	private void sendSMSForAllStudent(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			//if(templateType.equals("USER_DEFINED"))
			//{
				List<StudentVO> studentList = new ArrayList<>();
				
				if(StringUtils.isNotBlank(gender)){
					studentList =  studentDAO.getAllStudentByGender(composeSMS.getSchoolId(),gender);
				}else{
					studentList =  studentDAO.getAllStudent(composeSMS.getSchoolId(),null,null);
				}
				
				if(studentList.size() == 0){
					throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
				}
				
				/*if(studentList.isEmpty()){
					throw new SMSBusinessException(SMSConstants.STUDENT_DETAILS_EMPTY);
				}*/
				//send otp to mobile number
				
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				for (StudentVO studentVO : studentList) {
					if(composeSMS.getSendTo().contains(SMSConstants.SCHOOL_STUDENT)){
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(StringUtils.isNotBlank(studentVO.getMobileNumber())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getStudentName());
								smsHistoryVO.setStudentId(studentVO.getStudentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								if(StringUtils.isNotBlank(studentVO.getClassId())){
									ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
									smsHistoryVO.setStandard(classVO.getClassName());
								}
								if(StringUtils.isNotBlank(studentVO.getSection())){
									SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
									smsHistoryVO.setSection(sectionVO.getSectionName());
								}
								mobNumber.append(studentVO.getMobileNumber()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getMobileNumber());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getMobileNumber(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
					}
					if(composeSMS.getSendTo().contains(SMSConstants.SCHOOL_PARENT1))
					{
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(StringUtils.isNotBlank(studentVO.getParentMobileNumber1()))
							{
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber1()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber1());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber1(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
					}
					if(composeSMS.getSendTo().contains(SMSConstants.SCHOOL_PARENT2))
					{
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(StringUtils.isNotBlank(studentVO.getParentMobileNumber2()))
							{
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber2()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber2());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber2(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
					}
				}
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
						
				}
				
				
				if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<TeacherVO> teacherList = new ArrayList<>();
					teacherList = teacherDAO.getAllTeacher(composeSMS.getSchoolId(),null,null);
					for (TeacherVO teacherVO : teacherList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(teacherVO.getStaffName());
							smsHistoryVO.setStudentId(teacherVO.getStaffId());
							smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
							mobNumber.append(teacherVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
			smsLogVO.setSchoolId(composeSMS.getSchoolId());		
			smsLogVO.setSmsCount(findcountlength);
			SchoolVO getSchoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
			getSchoolVO.setSmsBalanceCount(getSchoolVO.getSmsBalanceCount() - findcountlength);
			schoolDAO.updateSchool(getSchoolVO);
			smsDAO.saveSmsLog(smsLogVO);
		}
	
	private void sendSMSForAllAdmin(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			//if(templateType.equals("USER_DEFINED"))
			//{
				List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
				if(adminList.size() == 0){
					throw new SMSBusinessException(SMSConstants.ALL_ADMIN_EMPTY);
				}
			
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				for (AdminVO adminVO : adminList) {
					if(schoolVO.getSmsBalanceCount() > findcountlength){
						SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
						smsHistoryVO.setId(UUID.randomUUID().toString()); 
						smsHistoryVO.setSmsLogId(smsLogVO.getId());
						smsHistoryVO.setMessage(message);
						smsHistoryVO.setStudentName(adminVO.getFirstName());
						smsHistoryVO.setStudentId(adminVO.getAdminId());
						smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
						mobNumber.append(adminVO.getMobileNumber()).append(",");
						smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
						String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
						smsResponse = smsResponse.replaceAll("\\s+","");
						if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
						}else{
							findcountlength++;
							smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
						}
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
				}
			smsLogVO.setSchoolId(composeSMS.getSchoolId());
			smsLogVO.setSmsCount(findcountlength);
			schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
			schoolDAO.updateSchool(schoolVO);
			smsDAO.saveSmsLog(smsLogVO);
		}
	
	private void sendSMSForIndividualStudentWise(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			//if(templateType.equals("USER_DEFINED"))
			//{
				//List<String> receiverIds = Arrays.asList(composeSMS.getReceiverIds().split(","));
			//	List<String> receiverIds = new ArrayList<String>();
			//	List<String> receiverIds = new ArrayList<String>(Arrays.asList(composeSMS.getReceiverIds().split(",")));
				//List<String> receiverIds = composeSMS.getReceiverIds();
				
				List<StudentVO> studentList = studentDAO.getAllStudentsByIds(composeSMS.getReceiverIds());
				
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				List<String> batchIds = new ArrayList<String>();
				List<String> sectionIds = new ArrayList<String>();
				List<String> classIds = new ArrayList<String>();
				for (StudentVO studentVO : studentList) {
					
						if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
							batchIds.add(studentVO.getBatchId());
							sectionIds.add(studentVO.getSection());
							classIds.add(studentVO.getClassId());
						}
						if(sendToList.contains(SMSConstants.SCHOOL_STUDENT)){
							if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(studentVO.getStudentName());
							smsHistoryVO.setStudentId(studentVO.getStudentId());
							smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
							if(StringUtils.isNotBlank(studentVO.getClassId())){
								ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
								smsHistoryVO.setStandard(classVO.getClassName());
							}
							if(StringUtils.isNotBlank(studentVO.getSection())){
								SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
								smsHistoryVO.setSection(sectionVO.getSectionName());
							}
						
							mobNumber.append(studentVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(studentVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
					
					if(isParent){
						//parentVO = parentDAO.getParentByStudentId(studentVO.getId());
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT1) && StringUtils.isNotBlank(studentVO.getParentMobileNumber1())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber1()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber1());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber1(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT2) && StringUtils.isNotBlank(studentVO.getParentMobileNumber2())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber2()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber2());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber2(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
					}
					
				}
				if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<BatchConfigurationVO> batchConfigurationVOs = batchConfigurationDAO
							.getAllClassTeacherByIds(composeSMS.getSchoolId(),batchIds,sectionIds,classIds);
					List<String> teacherIds = new ArrayList<String>();
					for(BatchConfigurationVO batchConfigurationVO : batchConfigurationVOs){
						teacherIds.add(batchConfigurationVO.getClassTeacherId());
					}
					
					List<TeacherVO> teacherList = teacherDAO.getAllTeacherByIds(teacherIds);
					for (TeacherVO teacherVO : teacherList) {	
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(teacherVO.getStaffName());
							smsHistoryVO.setStudentId(teacherVO.getStaffId());
							smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
							mobNumber.append(teacherVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
				
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
			smsLogVO.setSchoolId(composeSMS.getSchoolId());
			smsLogVO.setSmsCount(findcountlength);
			schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
			schoolDAO.updateSchool(schoolVO);
			smsDAO.saveSmsLog(smsLogVO);
	}
	
	private void sendSMSForClassWise(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
		//	if(templateType.equals("USER_DEFINED")){
				//List<StudentVO> studentList = studentDAO.getAllStudentByClassAndGender(composeSMS.getStandard(), composeSMS.getGender());
				List<StudentVO> studentList = studentDAO.getAllStudentByListClassAndGender(composeSMS.getSchoolId(), composeSMS.getClassName(), composeSMS.getGender());
				if(studentList.size() == 0){
					throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
				}
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				List<String> batchIds = new ArrayList<String>();
				List<String> sectionIds = new ArrayList<String>();
				List<String> classIds = new ArrayList<String>();
				for (StudentVO studentVO : studentList) {
					if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
						batchIds.add(studentVO.getBatchId());
						sectionIds.add(studentVO.getSection());
						classIds.add(studentVO.getClassId());
					}
					if(sendToList.contains(SMSConstants.SCHOOL_STUDENT)){
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(studentVO.getStudentName());
							smsHistoryVO.setStudentId(studentVO.getStudentId());
							smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
							if(StringUtils.isNotBlank(studentVO.getClassId())){
								ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
								smsHistoryVO.setStandard(classVO.getClassName());
							}
							if(StringUtils.isNotBlank(studentVO.getSection())){
								SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
								smsHistoryVO.setSection(sectionVO.getSectionName());
							}
							mobNumber.append(studentVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(studentVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
					
					if(isParent){
							//parentVO = parentDAO.getParentByStudentId(studentVO.getId());
						if(schoolVO.getSmsBalanceCount() > findcountlength){	
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT1) && StringUtils.isNotBlank(studentVO.getParentMobileNumber1())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber1()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber1());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber1(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT2) && StringUtils.isNotBlank(studentVO.getParentMobileNumber2())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber2()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber2());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber2(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
					}
				}
				
				if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<BatchConfigurationVO> batchConfigurationVOs = batchConfigurationDAO
							.getAllClassTeacherByIds(composeSMS.getSchoolId(),batchIds,sectionIds,classIds);
					List<String> teacherIds = new ArrayList<String>();
					for(BatchConfigurationVO batchConfigurationVO : batchConfigurationVOs){
						teacherIds.add(batchConfigurationVO.getClassTeacherId());
					}
					
					List<TeacherVO> teacherList = teacherDAO.getAllTeacherByIds(teacherIds);
					for (TeacherVO teacherVO : teacherList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(teacherVO.getStaffName());
							smsHistoryVO.setStudentId(teacherVO.getStaffId());
							smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
							mobNumber.append(teacherVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
			smsLogVO.setSchoolId(composeSMS.getSchoolId());
			smsLogVO.setSmsCount(findcountlength);
			schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
			schoolDAO.updateSchool(schoolVO);
			smsDAO.saveSmsLog(smsLogVO);
	}

	private void sendSMSForSectionWise(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
			//if(templateType.equals("USER_DEFINED"))
			//{
				//List<StudentVO> studentList = studentDAO.getAllStudentBySectionAndGender(composeSMS.getSection(), composeSMS.getGender());
				List<StudentVO> studentList = studentDAO.getAllStudentByListSectionAndGender(composeSMS.getSchoolId(),composeSMS.getSectionList(), composeSMS.getStandard(), composeSMS.getGender());
				if(studentList.size() == 0){
					throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
				}
				int findcountlength = 0;
				SchoolVO schoolVO = schoolDAO.getSchoolById(composeSMS.getSchoolId());
				List<String> batchIds = new ArrayList<String>();
				List<String> sectionIds = new ArrayList<String>();
				List<String> classIds = new ArrayList<String>();
				for (StudentVO studentVO : studentList) {	
					if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
						batchIds.add(studentVO.getBatchId());
						sectionIds.add(studentVO.getSection());
						classIds.add(studentVO.getClassId());
					}
					if(sendToList.contains(SMSConstants.SCHOOL_STUDENT)){
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(studentVO.getStudentName());
							smsHistoryVO.setStudentId(studentVO.getStudentId());
							smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
							if(StringUtils.isNotBlank(studentVO.getClassId())){
								ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
								smsHistoryVO.setStandard(classVO.getClassName());
							}
							if(StringUtils.isNotBlank(studentVO.getSection())){
								SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
								smsHistoryVO.setSection(sectionVO.getSectionName());
							}
							mobNumber.append(studentVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(studentVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
					
					if(isParent){
						//parentVO = parentDAO.getParentByStudentId(studentVO.getId());
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT1) && StringUtils.isNotBlank(studentVO.getParentMobileNumber1())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber1()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber1());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber1(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							if(sendToList.contains(SMSConstants.SCHOOL_PARENT2) && StringUtils.isNotBlank(studentVO.getParentMobileNumber2())){
								SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
								smsHistoryVO.setId(UUID.randomUUID().toString()); 
								smsHistoryVO.setSmsLogId(smsLogVO.getId());
								smsHistoryVO.setMessage(message);
								smsHistoryVO.setStudentName(studentVO.getParentName());
								smsHistoryVO.setStudentId(studentVO.getParentId());
								smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
								mobNumber.append(studentVO.getParentMobileNumber2()).append(",");
								smsHistoryVO.setMobileNumber(studentVO.getParentMobileNumber2());
								String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, studentVO.getParentMobileNumber2(), message);
								smsResponse = smsResponse.replaceAll("\\s+","");
								if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
								}else{
									findcountlength++;
									smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
								}
								smsDAO.saveSmsHistory(smsHistoryVO);
							}
						}
						
					}
				}
				
				if(sendToList.contains(SMSConstants.SCHOOL_TEACHER)){
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					List<BatchConfigurationVO> batchConfigurationVOs = batchConfigurationDAO
							.getAllClassTeacherByIds(composeSMS.getSchoolId(),batchIds,sectionIds,classIds);
					List<String> teacherIds = new ArrayList<String>();
					for(BatchConfigurationVO batchConfigurationVO : batchConfigurationVOs){
						teacherIds.add(batchConfigurationVO.getClassTeacherId());
					}
					
					List<TeacherVO> teacherList = teacherDAO.getAllTeacherByIds(teacherIds);
					for (TeacherVO teacherVO : teacherList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(teacherVO.getStaffName());
							smsHistoryVO.setStudentId(teacherVO.getStaffId());
							smsHistoryVO.setAdmissionNumber(teacherVO.getEmployeeNumber());
							mobNumber.append(teacherVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(teacherVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, teacherVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
				
				if(sendToList.contains(SMSConstants.SCHOOL_ADMIN)){
					List<AdminVO> adminList =  adminDAO.getAllAdmin(composeSMS.getSchoolId(),null,null);
					for (AdminVO adminVO : adminList) {
						if(schoolVO.getSmsBalanceCount() > findcountlength){
							SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
							smsHistoryVO.setId(UUID.randomUUID().toString()); 
							smsHistoryVO.setSmsLogId(smsLogVO.getId());
							smsHistoryVO.setMessage(message);
							smsHistoryVO.setStudentName(adminVO.getFirstName());
							smsHistoryVO.setAdmissionNumber(adminVO.getEmployeeNumber());
							smsHistoryVO.setStudentId(adminVO.getAdminId());
							mobNumber.append(adminVO.getMobileNumber()).append(",");
							smsHistoryVO.setMobileNumber(adminVO.getMobileNumber());
							String smsResponse = BulkSMS.SMSSender(otpUserName, otpUserPassword, otpSenderId, otpService, adminVO.getMobileNumber(), message);
							smsResponse = smsResponse.replaceAll("\\s+","");
							if(smsResponse.equals(null) || smsResponse.equals("") || smsResponse == ""){
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_UNDELIVERED);
							}else{
								findcountlength++;
								smsHistoryVO.setStatus(SMSConstants.SEND_SMS_DELIVERED);
							}
							smsDAO.saveSmsHistory(smsHistoryVO);
						}
					}
				}
			smsLogVO.setSchoolId(composeSMS.getSchoolId());
			smsLogVO.setSmsCount(findcountlength);
			schoolVO.setSmsBalanceCount(schoolVO.getSmsBalanceCount() - findcountlength);
			schoolDAO.updateSchool(schoolVO);
			smsDAO.saveSmsLog(smsLogVO);
	}
	private void sendSMSForStudentWise(ComposeSMS composeSMS, List<String> sendToList, SmsLogVO smsLogVO,
			String templateType, String message, String gender, boolean isParent, StringBuffer mobNumber) throws SMSBusinessException {
		//	if(templateType.equals("USER_DEFINED")){
				List<StudentVO> studentList = studentDAO.getAllStudentByGender(composeSMS.getSchoolId(),gender);
				for (StudentVO studentVO : studentList) {
					
					SmsHistoryVO smsHistoryVO = new SmsHistoryVO();
					smsHistoryVO.setId(UUID.randomUUID().toString()); 
					smsHistoryVO.setSmsLogId(smsLogVO.getId());
					smsHistoryVO.setMessage(message);
					smsHistoryVO.setStudentName(studentVO.getStudentName());
					smsHistoryVO.setStudentId(studentVO.getId());
					smsHistoryVO.setAdmissionNumber(studentVO.getAdmissionNumber());
					ParentVO parentVO = null;
					if(sendToList.contains(SMSConstants.SCHOOL_STUDENT)){
						mobNumber.append(studentVO.getMobileNumber()).append(",");
						smsHistoryVO.setMobileNumber(studentVO.getMobileNumber());
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
					
					if(isParent){
						parentVO = parentDAO.getParentByStudentId(studentVO.getId());
					}
					if(parentVO!= null && sendToList.contains(SMSConstants.SCHOOL_PARENT1) && StringUtils.isNotBlank(parentVO.getParentMobileNumber1())){
						mobNumber.append(parentVO.getParentMobileNumber1()).append(",");
						smsHistoryVO.setMobileNumber(parentVO.getParentMobileNumber1());
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
					if(parentVO!= null && sendToList.contains(SMSConstants.SCHOOL_PARENT2) && StringUtils.isNotBlank(parentVO.getParentMobileNumber2())){
						mobNumber.append(parentVO.getParentMobileNumber2()).append(",");
						smsHistoryVO.setMobileNumber(parentVO.getParentMobileNumber2());
						smsDAO.saveSmsHistory(smsHistoryVO);
					}
						SMS.sendSMS(StringUtils.substring(mobNumber.toString(), 0, mobNumber.toString().length() - 1), message);
					
				}
		//	}
			smsDAO.saveSmsLog(smsLogVO);
		}
}
