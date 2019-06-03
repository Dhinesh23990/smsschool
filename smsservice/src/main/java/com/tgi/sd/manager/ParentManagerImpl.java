/**
 * 
 */
package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.ParentDAO;
import com.tgi.sd.dao.SchoolDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.dao.UserDAO;
import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMS;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtil;

/**
 * @author SGSAuthour
 *
 */
public class ParentManagerImpl implements ParentManager {
	
	private static Logger logger = Logger.getLogger(ParentManagerImpl.class);

	ParentDAO parentDAO;
	UserDAO userDAO;
	StudentDAO studentDAO;
	SchoolDAO schoolDAO;
	
	UserManager userManager;
	

	public ParentDAO getParentDAO() {
		return parentDAO;
	}

	public void setParentDAO(ParentDAO parentDAO) {
		this.parentDAO = parentDAO;
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

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public SchoolDAO getSchoolDAO() {
		return schoolDAO;
	}

	public void setSchoolDAO(SchoolDAO schoolDAO) {
		this.schoolDAO = schoolDAO;
	}

	public ParentVO saveParent(ParentVO parent) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveParent :::" + parent.getParentName());
		}
		parent.setId(UUID.randomUUID().toString());
		ParentVO parentVO = parentDAO.saveParent(parent);
		String password = RandomStringUtils.randomAlphanumeric(7);
	//	UserVO user = SMSUtil.populateUserVO(parent.getParentName(),parent.getEmailId(),password,SMSConstants.PARENT);
		
	//	userManager.saveUser(user);
	//	parent.setUserOid(user.getId());
		updateParent(parent);
		StringBuffer message = new StringBuffer();
		//TODO
		//message.append(SystemProperties.getProperty(Constants.SMS_PARENT_REG_MESSAGE));
		message.append("").append("LoginId%20");
		message.append(parent.getEmailId()).append("");
		message.append("Password%20");
		message.append(password);
		SMS.sendSMS(parent.getParentMobileNumber1(), message.toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveParent :::" + parent.getUserOid());
		}
		return parentVO;
	}

	public ParentVO getParent(String id) throws SMSBusinessException {
		return parentDAO.getParent(id);
	}

	@Override
	public ParentVO updateParent(ParentVO parent) throws SMSBusinessException {
		if(StringUtils.isBlank(parent.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		return parentDAO.updateParent(parent);

	}

	@Override
	public List<ParentVO> getAllParent() throws SMSBusinessException {
		return parentDAO.getAllParent();
	}

	@Override
	public ParentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException {
		return parentDAO.getParentByMobileNo(mobileNo);
	}
	
	@Override
	public Map<String, Object> registerParentByMobileNo(String mobileNo) throws SMSBusinessException{
		Map<String,Object> responseObjectMap = new HashMap<String, Object>();
		StudentVO studentVO = studentDAO.getParentByMobileNo(mobileNo);
		if(studentVO ==null) {
			throw new SMSBusinessException(ErrorConstants.MOBILENO_NOT_REGISTER_WITH_STUDENT);
		}
		UserVO userVO = userDAO.getUserByUserName(mobileNo, SMSConstants.PARENT, null);
		if(userVO != null) {
			throw new SMSBusinessException(ErrorConstants.MOBILENO_ALREADY_REGISTERED);
		}
		UserVO user = SMSUtil.populateUserVO(studentVO);
		userManager.saveUser(user);
		userDAO.saveToken(user.getTokenVO());
		responseObjectMap.put("userDetails", user);
		return responseObjectMap;
		
	};
	
	@Override
	public Map<String, Object> getSchoolListByParentMobileNo(String mobileNo) throws SMSBusinessException {
		Map<String,Object> responseObjectMap = new HashMap<String, Object>();
		List<String> schoolIds = studentDAO.getSchoolIdsByParentMobileNo(mobileNo);
		if(!schoolIds.isEmpty()) {
			List<SchoolVO> schoolList = schoolDAO.getSchoolByIds(schoolIds);
			responseObjectMap.put("schoolList", schoolList);
		}
		return responseObjectMap;
	}
}
