package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface SchoolDAO {

	SchoolVO saveSchool(SchoolVO schoolVO) throws SMSBusinessException;

	SchoolVO getSchoolById(String schoolId) throws SMSBusinessException;

	List<SchoolVO> getAllSchool(int pageIndex, int pageSize, String status) throws SMSBusinessException;

	long getAllSchoolCount() throws SMSBusinessException;

	SchoolVO updateSchool(SchoolVO schoolVO) throws SMSBusinessException;

	boolean deleteSchool(String schoolId) throws SMSBusinessException;

	SchoolVO getSchoolByName(String schoolName, String id) throws SMSBusinessException;

	boolean updateSchoolStatus(String schoolId, String status) throws SMSBusinessException;

	boolean updateAdminStatus(String schoolId, String status) throws SMSBusinessException;
	
	boolean updateUserStatus(String schoolId, String status) throws SMSBusinessException;

	UserVO getSchoolByUserName(String userName, String id) throws SMSBusinessException;

	UserVO getSchoolByEmailId(String contactEmailId, String id) throws SMSBusinessException;

	List<SchoolVO> getSchoolByIds(List<String> schoolIds) throws SMSBusinessException;
}
