 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.SubjectTypeVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface SubjectTypeManager {

	SubjectTypeVO saveSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException;

	SubjectTypeVO getSubjectTypeById(String subjectTypeId) throws SMSBusinessException;

	SubjectTypeVO updateSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException;

	boolean deleteSubjectType(String subjectTypeId) throws SMSBusinessException;

	long getSubjectTypeCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllSubjectTypeBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
