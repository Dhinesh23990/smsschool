 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface SchoolManager {

	SchoolVO saveSchool(SchoolVO schoolVO) throws SMSBusinessException;

	SchoolVO getSchoolById(String schoolId) throws SMSBusinessException;

	SchoolVO updateSchool(SchoolVO schoolVO) throws SMSBusinessException;

	boolean deleteSchool(String schoolId) throws SMSBusinessException;

	long getAllSchoolCount() throws SMSBusinessException;

	Map<String, Object> getAllSchool(int pageIndex,
			int pageSize,String status) throws SMSBusinessException;

	boolean updateSchoolStatus(String schoolId, String status) throws SMSBusinessException;
	
	
}
