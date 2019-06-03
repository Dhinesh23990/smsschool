package com.tgi.sd.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

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
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.dao.LookupDAO;
import com.tgi.sd.dao.MailDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.MailVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserDetailsVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.manager.LoginManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtil;
import com.tgi.sd.util.SMSUtility;

@RestController
@RequestMapping("/rest/loginService")
public class LoginService extends SMSBaseService {

	private Logger logger = Logger.getLogger(LoginService.class);

	private LoginManager loginManager;
	
	public LoginManager getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	
	@RequestMapping(value = "/login", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody ResponseVO login(@RequestParam String userName,
			@RequestParam String password, @RequestParam String roleId) throws JSONException {

		if (logger.isDebugEnabled()) {
			logger.debug("getUser() starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		UserVO userVO = null;
		UserDetailsVO userDetailsVO = null;
		String emailAddress = null;
		try {
			userVO = loginManager.getUserByUserName(userName,roleId,emailAddress);
		//	String existPass = SMSUtil.getMD5(password);
			if (userVO != null
					&& userVO.getPassword().equals(password)) {
				if (userVO.getUserStatusCode().equals(SMSConstants.SCHOOL_ACTIVE_STATUS)) {
					userDetailsVO = prepareUserDetails(userVO);
					responseObjectsMap.put("userDetailsVO", userDetailsVO);
					responseVO = createSuccessResponseVO(responseObjectsMap);
				} else {
					responseVO = createErrorResponseVO(ErrorConstants.STATUS_INACTIVE);
				}

			} else {
				responseVO = createErrorResponseVO(ErrorConstants.INVALID_USER);
			}

		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUser()  ends");
		}

		return responseVO;
	}

	private UserDetailsVO prepareUserDetails(UserVO userVO)
			throws SMSBusinessException {
		UserDetailsVO userDetailsVO = null;
		try {
			userDetailsVO = new UserDetailsVO();
			TokenVO tokenVO = userVO.getTokenVO();
			RoleVO roleVO = userVO.getRoleVO();
			userDetailsVO.setId(userVO.getId());
			userDetailsVO.setRoleVO(roleVO);
			userDetailsVO.setUserName(userVO.getUserName());
			userDetailsVO.setFirstName("Test");
			userDetailsVO.setLastName("Test");
			userDetailsVO.setContactEmail(userVO.getContactEmail());
			userDetailsVO.setCustomerId(userVO.getCustomerId());
			userDetailsVO.setRoleName(roleVO != null ? roleVO.getRoleName():"");
			userDetailsVO.setToken(tokenVO.getToken());
			userDetailsVO.setSchoolId(userVO.getSchoolId());
			userDetailsVO.setTokenExpirationDt(tokenVO.getTokenExpire());
		} catch (Exception e) {
			throw new SMSBusinessException(e.getMessage());
		}
		return userDetailsVO;
	}
	
	@RequestMapping(value = "/forgetpassword", method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody ResponseVO forgetpassword(@RequestParam String emailAddress, @RequestParam String roleId) throws JSONException {

		if (logger.isDebugEnabled()) {
			logger.debug("getUser() starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		UserVO userVO = null;
		String userName = null;
		try {
			userVO = loginManager.getUserByUserName(userName,roleId,emailAddress);
		//	String existPass = SMSUtil.getMD5(password);
			if (userVO != null) {
				if (userVO.getUserStatusCode().equals(SMSConstants.SCHOOL_ACTIVE_STATUS)) {
					String password = RandomStringUtils.randomAlphanumeric(7);
					String existPass = SMSUtil.getMD5(password);
					userVO.setPassword(existPass);
					
					String emailContent = SMSUtility.prepareMailContent(userVO.getUserName(),password,SMSConstants.EMAIL_PURPOSE_FORGET_CONTENT,
							SMSConstants.EMAIL_FORGET_HEADER_CONTENT);
					
					final String fromMail = SMSConstants.EMAIL_ID;
					final String mailPassword = SMSConstants.EMAIL_PASSWORD;
					Date date = new Date();
					Properties props = new Properties();
					props.put("mail.smtp.starttls.enable", SMSConstants.EMAIL_STARTTLS_ENABLE);
					props.put("mail.smtp.auth", SMSConstants.EMAIL_STARTTLS_ENABLE);
					props.put("mail.smtp.host", SMSConstants.EMAIL_SMTP_HOST);
					props.put("mail.smtp.port", SMSConstants.EMAIL_SMTP_PORT);
					
					/*
					String subject = SMSConstants.EMAIL_FORGET_HEADER_CONTENT;
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
								InternetAddress.parse(userVO.getContactEmail()));
						message.setSubject(subject);
						message.setContent(emailContent,"text/html");
						Transport.send(message);
						MailVO mailVO = new MailVO();
						mailVO.setId(UUID.randomUUID().toString());
						mailVO.setFromAddress(SMSConstants.EMAIL_ID);
						mailVO.setToAddress(userVO.getContactEmail());
						mailVO.setSubject(SMSConstants.EMAIL_FORGET_SUBJECT_CONTENT);
						mailVO.setMailContent(emailContent);
						mailVO.setMailType(SMSConstants.MAIL_FORGET_TYPE);
						mailVO.setStatus(SMSConstants.SEND_SMS_SENDING);
						mailVO.setSentOn(date.toString());
						mailDAO.saveMail(mailVO);	
					}
					catch (MessagingException e) {
						logger.error(e.getMessage(),e);
						MailVO mailVO = new MailVO();
						mailVO.setId(UUID.randomUUID().toString());
						mailVO.setFromAddress(SMSConstants.EMAIL_ID);
						mailVO.setToAddress(userVO.getContactEmail());
						mailVO.setSubject(SMSConstants.EMAIL_FORGET_SUBJECT_CONTENT);
						mailVO.setMailContent(emailContent);
						mailVO.setMailType(SMSConstants.MAIL_FORGET_TYPE);
						mailVO.setStatus(SMSConstants.SEND_SMS_QUEUE);
						mailVO.setSentOn(date.toString());
						mailVO.setErrorMailReport(e.toString());
						mailDAO.saveMail(mailVO);
					}
					*/
					loginManager.updateUser(userVO);
					responseObjectsMap.put("Status", SMSConstants.SUCCESS_MSG_RESET_EMAIL);
					responseVO = createSuccessResponseVO(responseObjectsMap);
					
				} else {
					responseVO = createErrorResponseVO(ErrorConstants.STATUS_INACTIVE);
				}

			} else {
				responseVO = createErrorResponseVO(ErrorConstants.INVALID_USER);
			}

		} catch (Throwable e) {
			logger.error(e.getMessage(),e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getUser()  ends");
		}

		return responseVO;
	}
}
