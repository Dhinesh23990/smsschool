package com.tgi.sd.manager;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.LookupDAO;
import com.tgi.sd.dao.MailDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public class LoginManagerImpl implements LoginManager{
	private static Logger logger = Logger.getLogger(LoginManagerImpl.class);
	
	private UserDAO userDAO;
	private MailDAO mailDAO;
	private LookupDAO lookupDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public LookupDAO getLookupDAO() {
		return lookupDAO;
	}

	public void setLookupDAO(LookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}
	
	public MailDAO getMailDAO() {
		return mailDAO;
	}

	public void setMailDAO(MailDAO mailDAO) {
		this.mailDAO = mailDAO;
	}
	
	@Override
	public UserVO getUserByUserName(String userName,String roleId,String emailAddress)  throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByUserName start");
		}
		
		UserVO userVO = userDAO.getUserByUserName(userName,roleId,emailAddress);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getUserByUserName end");
		}
		
		return userVO;
	}
	
	
	
	@Override
	public void updateUser(UserVO userVO)  throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("updateUser start");
		}
		
		userDAO.updateUser(userVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateUser end");
		}
		
	}
	
}
