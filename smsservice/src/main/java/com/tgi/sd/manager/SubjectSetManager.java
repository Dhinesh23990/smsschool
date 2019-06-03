 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.SubjectSetVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface SubjectSetManager {

	SubjectSetVO saveSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException;

	SubjectSetVO getSubjectSetById(String subjectSetId) throws SMSBusinessException;

	SubjectSetVO updateSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException;

	boolean deleteSubjectSet(String subjectSetId) throws SMSBusinessException;

	long getSubjectSetCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllSubjectSetBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
