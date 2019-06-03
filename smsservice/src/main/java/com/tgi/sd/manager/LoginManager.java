package com.tgi.sd.manager;

import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LoginManager {
	UserVO getUserByUserName(String userName,String roleId,String emailAddress)  throws SMSBusinessException;
	
	void updateUser(UserVO userVO) throws SMSBusinessException;
}
