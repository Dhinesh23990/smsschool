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
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;

/*
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
*/

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.AdminDAO;
import com.tgi.sd.dao.BloodGroupDAO;
import com.tgi.sd.dao.CasteDAO;
import com.tgi.sd.dao.CityDAO;
import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.GenderDAO;
import com.tgi.sd.dao.MailDAO;
import com.tgi.sd.dao.MotherTongueDAO;
import com.tgi.sd.dao.NationalityDAO;
import com.tgi.sd.dao.ReligionDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.StateDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.domain.MailVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtil;
import com.tgi.sd.util.SMSUtility;

/**
 * @author SGSAuthour
 *
 */
public class AdminManagerImpl implements AdminManager {
	
	private static Logger logger = Logger.getLogger(AdminManagerImpl.class);

	AdminDAO adminDAO;
	
	private CityDAO cityDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	private GenderDAO genderDAO;
	
	private BloodGroupDAO bloodGroupDAO;
	
	private ReligionDAO religionDAO;
	
	private CasteDAO casteDAO;
	
	private NationalityDAO nationalityDAO;
	
	private SchoolDAO schoolDAO;
	
	private MotherTongueDAO motherTongueDAO;
	
	private TeacherDAO teacherDAO;
	
	private UserManager userManager;
	
	private UserDAO userDAO;
	
	private MailDAO mailDAO;
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	public MailDAO getMailDAO() {
		return mailDAO;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
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
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public AdminVO saveAdmin(AdminVO admin) throws SMSBusinessException {
		admin.setId(UUID.randomUUID().toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAdmin :::" + admin.getFirstName());
		}
		
		long countOfOccurences = adminDAO.getAdminCountByName(admin.getFirstName(),admin.getInitial(),admin.getSchoolId(),null);
		long countOfAdminUserName = adminDAO.getAdminCountByUserName(admin.getUserName(),admin.getSchoolId(),null);
		AdminVO CheckEmployeeNoAdminVO = adminDAO.getAdminEmployeeNumber(admin.getEmployeeNumber(),admin.getSchoolId(),null);
		AdminVO CheckEmailIdAdminVO = adminDAO.getAdminEmailId(admin.getEmailId(),null);
		
		if(countOfAdminUserName>0)
    		throw new SMSBusinessException(ErrorConstants.USERNAME_ALREADY_EXISTS);
		
		if(countOfOccurences>0)
    		throw new SMSBusinessException(ErrorConstants.ADMIN_ALREADY_EXISTS);
	
		if(CheckEmployeeNoAdminVO != null)
			throw new SMSBusinessException(SMSConstants.ADMIN_EMPLOYEENO_ALREADY_EXISTS);
	
		if(CheckEmailIdAdminVO != null)
			throw new SMSBusinessException(SMSConstants.EMAILID_ALREADY_EXISTS);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(admin.getDateOfJoining());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		SchoolVO schoolVO = schoolDAO.getSchoolById(admin.getSchoolId());
		//long adminCount = adminDAO.getAllAdminCountByDateOfJoiningYear(admin.getDateOfJoining(),admin.getSchoolId());
		long adminCount = adminDAO.getAdminCountBySchoolId(admin.getSchoolId());
		int number = (int) adminCount+1;
		String str = String.format("%04d", number);
		String substring = year.substring(Math.max(year.length() - 2, 0));
		String adminId = schoolVO.getSchoolCode()+substring+"A"+str;
		admin.setAdminId(adminId);
		admin = adminDAO.saveAdmin(admin);
		
		/*
		String password = RandomStringUtils.randomAlphanumeric(7);
		UserVO user = SMSUtil.populateUserVO(admin.getUserName(), admin.getEmailId(), password, SMSConstants.SCHOOLADMIN, admin.getSchoolId());
		user.setCustomerId(admin.getId());
		TokenVO token = SMSUtility.createTokenDetails(user.getId());
		user.setUserStatusCode(SMSConstants.SCHOOL_ACTIVE_STATUS);
		userManager.saveUser(user);
		userDAO.saveToken(token);
		
	
		String emailContent = SMSUtility.prepareMailContent(admin.getUserName(),password,SMSConstants.EMAIL_PURPOSE_LOGIN_CONTENT,SMSConstants.EMAIL_HEADER_CONTENT);
		
		final String fromMail = SMSConstants.EMAIL_ID;
		final String mailPassword = SMSConstants.EMAIL_PASSWORD;
		Date date = new Date();
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", SMSConstants.EMAIL_STARTTLS_ENABLE);
		props.put("mail.smtp.auth", SMSConstants.EMAIL_STARTTLS_ENABLE);
		props.put("mail.smtp.host", SMSConstants.EMAIL_SMTP_HOST);
		props.put("mail.smtp.port", SMSConstants.EMAIL_SMTP_PORT);
		
		String subject = SMSConstants.EMAIL_HEADER_CONTENT;
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMail,
							mailPassword);
				}
			});
		
		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.TO,	
					InternetAddress.parse(admin.getEmailId()));
			message.setSubject(subject);
			message.setContent(emailContent,"text/html");
			Transport.send(message);
			MailVO mailVO = new MailVO();
			mailVO.setId(UUID.randomUUID().toString());
			mailVO.setFromAddress(SMSConstants.EMAIL_ID);
			mailVO.setToAddress(admin.getEmailId());
			mailVO.setSubject(SMSConstants.EMAIL_HEADER_CONTENT);
			mailVO.setMailContent(emailContent);
			mailVO.setMailType(SMSConstants.MAIL_TYPE);
			mailVO.setStatus(SMSConstants.SEND_SMS_SENDING);
			mailVO.setSentOn(date.toString());
			mailDAO.saveMail(mailVO);	
		}
		catch (MessagingException e) {
			logger.error(e.getMessage(),e);
			MailVO mailVO = new MailVO();
			mailVO.setId(UUID.randomUUID().toString());
			mailVO.setFromAddress(SMSConstants.EMAIL_ID);
			mailVO.setToAddress(admin.getEmailId());
			mailVO.setSubject(SMSConstants.EMAIL_HEADER_CONTENT);
			mailVO.setMailContent(emailContent);
			mailVO.setMailType(SMSConstants.MAIL_TYPE);
			mailVO.setStatus(SMSConstants.SEND_SMS_QUEUE);
			mailVO.setSentOn(date.toString());
			mailVO.setErrorMailReport(e.toString());
			mailDAO.saveMail(mailVO);
		}
		
		
		admin.setUserOid(user.getId());
		updateAdmin(admin);
		*/
			
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAdmin :::" + admin.getId());
		}
		return admin;
	}

	public AdminVO getAdmin(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAdmin Id:::" + id);
		}
		
		AdminVO admin =  adminDAO.getAdmin(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAdmin :::" + admin.getId());
		}
		return admin;
	}
	
	public AdminVO viewAdmin(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewAdmin Id:::" + id);
		}
		
		AdminVO adminVO =  adminDAO.getAdmin(id);
		
		if(StringUtils.isNotBlank(adminVO.getComCountryId())){
			CountryVO countryVO = countryDAO.getCountryById(adminVO.getComCountryId());
			adminVO.setCountryName(countryVO.getCountryName());
		}
		if(StringUtils.isNotBlank(adminVO.getStateId())){
			StateVO stateVO = stateDAO.getStateById(adminVO.getStateId());
			adminVO.setStateName(stateVO.getStateName());
		}
		if(StringUtils.isNotBlank(adminVO.getCityId())){
			CityVO cityVO = cityDAO.getCityById(adminVO.getCityId());
			adminVO.setCityName(cityVO.getCityName());
		}
		if(StringUtils.isNotBlank(adminVO.getGenderId())){
			GenderVO genderVO = genderDAO.getGenderById(adminVO.getGenderId());
			adminVO.setGenderName(genderVO.getGender());
		}
		if(StringUtils.isNotBlank(adminVO.getBlooGroupId())){
			BloodGroupVO bloodGroupVO = bloodGroupDAO.getBloodGroupById(adminVO.getBlooGroupId());
			adminVO.setBloodGroupName(bloodGroupVO.getBloodgroupName());
		}
		if(StringUtils.isNotBlank(adminVO.getReligionId())){
			ReligionVO religionVO = religionDAO.getReligionById(adminVO.getReligionId());
			adminVO.setReligionName(religionVO.getReligionName());
		}
		if(StringUtils.isNotBlank(adminVO.getCasteId())){
			CasteVO casteVO = casteDAO.getCasteById(adminVO.getCasteId());
			adminVO.setCasteName(casteVO.getCasteName());
		}
		if(StringUtils.isNotBlank(adminVO.getNationalityId())){
			NationalityVO nationalityVO = nationalityDAO.getNationalityById(adminVO.getNationalityId());
			adminVO.setNationalityName(nationalityVO.getNationalityName());
		}
		if(StringUtils.isNotBlank(adminVO.getMotherTongueId())){
			MotherTongueVO motherTongueVO = motherTongueDAO.getMotherTongueById(adminVO.getMotherTongueId());
			adminVO.setMohterTongueName(motherTongueVO.getMotherTongueName());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End viewAdmin :::" + adminVO.getId());
		}
		return adminVO;
	}
	
	@Override
	public AdminVO updateAdmin(AdminVO admin) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAdmin :::" + admin.getFirstName());
		}
		if(StringUtils.isBlank(admin.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		
		admin = adminDAO.updateAdmin(admin);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAdmin :::" + admin.getId());
		}
		return admin;
	}
	
	/*
	@Override
	public AdminVO updateAdmin(AdminVO admin) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAdmin :::" + admin.getFirstName());
		}
		if(StringUtils.isBlank(admin.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		long countOfOccurences = adminDAO.getAdminCountByName(admin.getFirstName(),admin.getInitial(),admin.getSchoolId(),admin.getId());
		long countOfAdminUserName = adminDAO.getAdminCountByUserName(admin.getUserName(),admin.getSchoolId(),admin.getId());
		
		AdminVO CheckEmployeeNoAdminVO = adminDAO.getAdminEmployeeNumber(admin.getEmployeeNumber(),admin.getSchoolId(),admin.getId());
		if(CheckEmployeeNoAdminVO != null){
			throw new SMSBusinessException(SMSConstants.ADMIN_EMPLOYEENO_ALREADY_EXISTS);
		}
		
		if(countOfOccurences>0)
    		throw new SMSBusinessException(ErrorConstants.ADMIN_ALREADY_EXISTS);
		
		if(countOfAdminUserName>0)
    		throw new SMSBusinessException(ErrorConstants.USERNAME_ALREADY_EXISTS);
		
		AdminVO CheckEmailIdAdminVO = adminDAO.getAdminEmailId(admin.getEmailId(),admin.getId());
		if(CheckEmailIdAdminVO != null){
			throw new SMSBusinessException(SMSConstants.EMAILID_ALREADY_EXISTS);
		}
		
		admin = adminDAO.updateAdmin(admin);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAdmin :::" + admin.getId());
		}
		return admin;
	}
	*/

	@Override
	public  Map<String, Object> getAllAdmin(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException {
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAdmin");
		}
		adminList =  adminDAO.getAllAdmin(schoolId,pageIndex,pageSize);
		
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
		
		for(AdminVO adminVO : adminList){
			
			if(StringUtils.isNotBlank(adminVO.getCountryId())){
				adminVO.setCountryName(countryMap.get(adminVO.getCountryId()));
			}
			if(StringUtils.isNotBlank(adminVO.getStateId())){
				adminVO.setStateName(stateMap.get(adminVO.getStateId()));
			}
			if(StringUtils.isNotBlank(adminVO.getCityId())){
				adminVO.setCityName(cityMap.get(adminVO.getCityId()));
			}
			if(StringUtils.isNotBlank(adminVO.getGenderId())){
				adminVO.setGenderName(genderMap.get(adminVO.getGenderId()));
			}
			if(StringUtils.isNotBlank(adminVO.getBlooGroupId())){
				adminVO.setBloodGroupName(bgMap.get(adminVO.getBlooGroupId()));
			}
			if(StringUtils.isNotBlank(adminVO.getReligionId())){
				adminVO.setReligionName(relMap.get(adminVO.getReligionId()));
			}
			if(StringUtils.isNotBlank(adminVO.getCasteId())){
				adminVO.setCasteName(casteMap.get(adminVO.getCasteId()));
			}
			if(StringUtils.isNotBlank(adminVO.getNationalityId())){
				adminVO.setNationalityName(natMap.get(adminVO.getNationalityId()));
			}
			if(StringUtils.isNotBlank(adminVO.getMotherTongueId())){
				adminVO.setMohterTongueName(motherMap.get(adminVO.getMotherTongueId()));
			}			
			
		}
		
		long totalRecords = adminDAO.getAdminCountBySchoolId(schoolId);
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("adminList", adminList);
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllAdmin");
		}
		return responseObjectsMap;	
	}
	
	/*@Override
	public List<AdminVO> getAdminBySearchString(String searchString,int skip, int limit) throws SMSBusinessException {
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAdminBySearchString");
		}
		adminList =  adminDAO.getAdminBySearchString(searchString, skip, limit);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAdminBySearchString");
		}
		return adminList;	
	}
	
	@Override
	public List<AdminVO> getALLAdminsByClassAndSection(String standard,String section) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLAdminsByClassAndSection");
		}
		
		List<AdminVO> adminList =  adminDAO.getAdminsByClassAndSection(standard,section); 
 
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLAdminsByClassAndSection");
		}
		return adminList;	
	}
	*/
	@Override
	public boolean deleteAdmin(String adminId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAdmin Starts");
		}
		
		AdminVO admin =  adminDAO.getAdmin(adminId);
		UserVO userVO = userDAO.getUserById(admin.getUserOid());
		userDAO.deleteUser(userVO);
		boolean isDeleted = adminDAO.deleteAdmin(adminId);
		
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteAdmin Ends");
		}
		return isDeleted;
	}
}
