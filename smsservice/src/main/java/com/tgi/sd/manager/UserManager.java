 package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface UserManager {
	
	public void saveUser(UserVO userVO) throws SMSBusinessException;
	public UserVO getById(String id) throws SMSBusinessException;
	public UserVO getByCriteria(Map<String, String> criteriaMap) throws SMSBusinessException;
	public UserVO authentication(Map<String, String> criteriaMap) throws SMSBusinessException;
	public List<UserVO> getAllUserForEntity(String entityId,int pageNo, int pageSize) throws SMSBusinessException;
	public long getAllPromotionCountEntity(String entityId) throws SMSBusinessException;
	public void update(UserVO userVO) throws SMSBusinessException;
	public void delete(UserVO userVO) throws SMSBusinessException;
	public void signUp(UserVO userVO) throws SMSBusinessException;
	public UserVO changePassword(String userId, String oldPassword,
			String newPassword, String confirmPassword) throws SMSBusinessException;
}
