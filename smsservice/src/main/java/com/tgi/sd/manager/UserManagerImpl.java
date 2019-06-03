package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;

public class UserManagerImpl implements UserManager{
	
	private static Logger logger = Logger.getLogger(UserManagerImpl.class);
	private UserDAO userDAO;
	
	@Override
	public void saveUser(UserVO userVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl create() starts");
		}
		
		userDAO.saveUser(userVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl create() ends");
		}
		
	}
	@Override
	public UserVO getById(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getById() starts");
		}
		
		UserVO userVO = null;
		userVO = userDAO.getUserById(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getById() ends");
		}
		
		return userVO;
	}
	
	@Override
	public UserVO changePassword(String userId, String oldPassword,
	String newPassword, String confirmPassword) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getById() starts");
		}
		
		UserVO userVO = null;
		userVO = userDAO.getUserById(userId);
		if(!userVO.getPassword().equals(oldPassword)){
			throw new SMSBusinessException(ErrorConstants.OLD_PASSWORD_IS_WRONG);
		}else if(userVO.getPassword().equals(newPassword)){
			throw new SMSBusinessException(ErrorConstants.OLD_AND_NEW_PASSWORD_SAME);
		}else{
			userVO.setPassword(newPassword);
			userDAO.updateUser(userVO);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getById() ends");
		}
		
		return userVO;
	}
	
	@Override
	public UserVO getByCriteria(Map<String, String> criteriaMap) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getByCriteria() starts");
		}
		
		UserVO userVO = null;
		
		String socialType = null;
		if (null != criteriaMap.get("socialType"))
			socialType = criteriaMap.get("socialType");
		
		String socialId = null;
		if (null != criteriaMap.get("socialId"))	
			socialId = criteriaMap.get("socialId");
		
	
		List<UserVO> userLst = userDAO.getUserByCriteria(socialId,socialType);
		if(null != userLst && userLst.size() > 0 )
			userVO = userLst.get(0);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl getByCriteria() ends");
		}
		
		return userVO;
	}
	
	@Override
	public UserVO authentication(Map<String, String> criteriaMap) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("authenticatoin starts");
		}
		UserVO userVO = null;
		String username = criteriaMap.get("userName");
		String password = criteriaMap.get("password");
			
		List<UserVO> userLst = userDAO.getUserByIdPwd(username,password);
			
		if (null != userLst && userLst.size() > 0)
			userVO = userLst.get(0);
		if (logger.isDebugEnabled()) {
			logger.debug("authenticatoin ends");
		}
			
		return userVO;
	}
	
	@Override
	public List<UserVO> getAllUserForEntity(String entityId, int pageNo,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("User List starts");
		}
				
		List<UserVO> userLst = userDAO.getAllUserList(entityId, pageNo, pageSize);
		
		if (logger.isDebugEnabled()) {

			logger.debug("User List starts");
		}
		return userLst;
	}
	
	@Override
	public long getAllPromotionCountEntity(String entityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("User count starts");
		}

		long cnt = userDAO.getAllListCountEntity(entityId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("User count ends");
		}
		return cnt;
	}
		
	@Override
	public void update(UserVO userVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("User update() starts");
		}
		
			userDAO.updateUser(userVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("User update() ends");
		}
	}
	@Override
	public void delete(UserVO userVO) throws SMSBusinessException {
	

		if (logger.isDebugEnabled()) {
			logger.debug("User delete() starts");
		}
		
			userDAO.deleteUser(userVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("User delete() ends");
		}
		
	}
	
	@Override
	public void signUp(UserVO userVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl signUp() starts");
		}
		
		userDAO.saveUser(userVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("UserManagerImpl signUp() ends");
		}
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	

}
