 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface ClassManager {

	ClassVO saveClass(ClassVO classVO) throws SMSBusinessException;

	ClassVO getClassById(String classId) throws SMSBusinessException;

	ClassVO updateClass(ClassVO classVO) throws SMSBusinessException;

	boolean deleteClass(String classId) throws SMSBusinessException;

	long getClassCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllClassBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
