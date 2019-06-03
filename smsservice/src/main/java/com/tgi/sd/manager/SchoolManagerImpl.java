package com.tgi.sd.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.MailDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.MailVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtil;
import com.tgi.sd.util.SMSUtility;

public class SchoolManagerImpl implements SchoolManager{
	
	private static Logger logger = Logger.getLogger(SchoolManagerImpl.class);
	
	private SchoolDAO schoolDAO;
	
	private UserManager userManager;
	
	private UserDAO userDAO;
	
	private MailDAO mailDAO;
	
	public SchoolDAO getSchoolDAO() {
		return schoolDAO;
	}
	public void setSchoolDAO(SchoolDAO schoolDAO) {
		this.schoolDAO = schoolDAO;
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
	
	public MailDAO getMailDAO() {
		return mailDAO;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}
	
	@Override
	public SchoolVO saveSchool(SchoolVO schoolVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveSchool starts");
		}
		SchoolVO school = schoolDAO.getSchoolByName(schoolVO.getSchoolName(), null);
		if(school != null) {
			throw new SMSBusinessException(SMSConstants.SCHOOL_ALREADY_EXISTS);
		}
		UserVO userVO = schoolDAO.getSchoolByUserName(schoolVO.getUserName(), null);
		if(userVO != null) {
			throw new SMSBusinessException(SMSConstants.USERNAME_ALREADY_EXISTS);
		}
		
		UserVO userVOEmail = schoolDAO.getSchoolByEmailId(schoolVO.getContactEmailId(), null);
		if(userVOEmail != null) {
			throw new SMSBusinessException(SMSConstants.EMAILID_ALREADY_EXISTS);
		}
		schoolVO.setId(UUID.randomUUID().toString());
		schoolVO.setStatus(SMSConstants.SCHOOL_ACTIVE_STATUS);
		schoolVO.setSmsBalanceCount(schoolVO.getSmsTotalCount());
		
		UserVO user = SMSUtil.populateUserVO(schoolVO.getUserName(), schoolVO.getContactEmailId(), schoolVO.getPassword() , 
				SMSConstants.SCHOOLADMIN , schoolVO.getId());
		String password = schoolVO.getPassword();
		schoolVO.setPassword(null);
		schoolVO = schoolDAO.saveSchool(schoolVO);
		user.setCustomerId(schoolVO.getId());
		TokenVO token = SMSUtility.createTokenDetails(user.getId());
		user.setUserStatusCode(SMSConstants.SCHOOL_ACTIVE_STATUS);
		userManager.saveUser(user);
		userDAO.saveToken(token);
		schoolVO.setUserOid(user.getId());
		schoolDAO.updateSchool(schoolVO);
		
		String emailContent = SMSUtility.prepareMailContent(schoolVO.getUserName(),password,SMSConstants.EMAIL_PURPOSE_LOGIN_CONTENT,
				SMSConstants.EMAIL_HEADER_CONTENT);
		
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
					InternetAddress.parse(schoolVO.getContactEmailId()));
			message.setSubject(subject);
			message.setContent(emailContent,"text/html");
			Transport.send(message);
			MailVO mailVO = new MailVO();
			mailVO.setId(UUID.randomUUID().toString());
			mailVO.setFromAddress(SMSConstants.EMAIL_ID);
			mailVO.setToAddress(schoolVO.getContactEmailId());
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
			mailVO.setToAddress(schoolVO.getContactEmailId());
			mailVO.setSubject(SMSConstants.EMAIL_HEADER_CONTENT);
			mailVO.setMailContent(emailContent);
			mailVO.setMailType(SMSConstants.MAIL_TYPE);
			mailVO.setStatus(SMSConstants.SEND_SMS_QUEUE);
			mailVO.setSentOn(date.toString());
			mailVO.setErrorMailReport(e.toString());
			mailDAO.saveMail(mailVO);
		}
		/*StringBuffer message = new StringBuffer();
		//message.append(SystemProperties.getProperty(SMSConstants.SMS_PARENT_REG_MESSAGE));
		message.append("").append("LoginId :");
		message.append(teacher.getEmailId()).append("");
		message.append("Password :");
		message.append(password);*/
	//	SMS.sendSMS(teacher.getMobileNumber(), message.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("saveSchool ends");
		}
		return schoolVO;
		
	}
	@Override
	public SchoolVO getSchoolById(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolById Starts");
		}
		
		SchoolVO schoolVO = schoolDAO.getSchoolById(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolById Ends");
		}
		
		return schoolVO;
	}
	
	@Override
	public Map<String, Object> getAllSchool(int pageIndex,int pageSize,String status) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchool Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = schoolDAO.getAllSchoolCount();
		List<SchoolVO> schoolVOs = schoolDAO.getAllSchool(pageIndex, pageSize, status);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("schoolVOs", schoolVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllSchool Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getAllSchoolCount() throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchoolCount Starts");
		}

		long cnt = schoolDAO.getAllSchoolCount();
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchoolCount Ends");
		}
		return cnt;
	}
		
	@Override
	public SchoolVO updateSchool(SchoolVO schoolVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchool Starts");
		}
		
		SchoolVO school = schoolDAO.getSchoolByName(schoolVO.getSchoolName(), schoolVO.getId());
		if(school != null) {
			throw new SMSBusinessException(SMSConstants.SCHOOL_ALREADY_EXISTS);
		}
		UserVO user = schoolDAO.getSchoolByUserName(schoolVO.getUserName(), schoolVO.getId());
		if(user != null) {
			throw new SMSBusinessException(SMSConstants.USERNAME_ALREADY_EXISTS);
		}
		
		UserVO userVOEmail = schoolDAO.getSchoolByEmailId(schoolVO.getContactEmailId(), schoolVO.getId());
		if(userVOEmail != null) {
			throw new SMSBusinessException(SMSConstants.EMAILID_ALREADY_EXISTS);
		}
		schoolVO = schoolDAO.updateSchool(schoolVO);
		UserVO userVO = userDAO.getUserById(schoolVO.getUserOid());
		userVO.setContactEmail(schoolVO.getContactEmailId());
		userVO.setUserName(schoolVO.getUserName());
		userDAO.updateUser(userVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchool Ends");
		}
		return schoolVO;
	}
	
	@Override
	public boolean updateSchoolStatus(String schoolId, String status) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchoolStatus Starts");
		}
		boolean checkAllStatus = false;
		boolean isStatusUpdated = schoolDAO.updateSchoolStatus(schoolId, status);
		boolean isStatusAdminUpdated = schoolDAO.updateAdminStatus(schoolId, status);
		boolean isStatusUserUpdated = schoolDAO.updateUserStatus(schoolId, status);
		if(isStatusUpdated && isStatusAdminUpdated && isStatusUserUpdated)
			checkAllStatus = true;
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchoolStatus Ends");
		}
		return checkAllStatus;
	}
	
	@Override
	public boolean deleteSchool(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSchool Starts");
		}
		
		boolean isDeleted = schoolDAO.deleteSchool(schoolId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSchool Ends");
		}
		return isDeleted;
	}
	
}
