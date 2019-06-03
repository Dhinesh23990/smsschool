package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.domain.TokenVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface UserDAO {
	public void saveUser(UserVO userVO) throws SMSBusinessException;
	public void saveToken(TokenVO tokenVO) throws SMSBusinessException;
	public UserVO getUserById(String id);
	public List<UserVO> getUserByCriteria(String socialId, String socialType) throws SMSBusinessException;
	public List<UserVO> getUserByIdPwd(String userName, String password) throws SMSBusinessException;
	public List<UserVO> getAllUserList(String userId, int pageNo, int pageSize) throws SMSBusinessException;
	public long getAllListCountEntity(String entityId) throws SMSBusinessException;
	public void updateUser(UserVO userVO) throws SMSBusinessException;
	public void deleteUser(UserVO userVO) throws SMSBusinessException;
	public UserVO getUserBySocialId(String id) throws SMSBusinessException;
//	public UserVO signUp(UserVO userVO);
	public UserVO getUserByUserName(String userName, String roleId, String emailAddress) throws SMSBusinessException;
	public UserVO getUserByToken(String token);
	public void refreshToken(String id);
	public List<RoleVO> getAllRole();
	public List<UserVO> getAllUsersBySchoolId();
	
 }
