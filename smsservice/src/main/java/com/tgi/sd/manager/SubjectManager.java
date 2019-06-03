 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface SubjectManager {

	SubjectVO saveSubject(SubjectVO subjectVO) throws SMSBusinessException;

	SubjectVO getSubjectById(String subjectId) throws SMSBusinessException;

	SubjectVO updateSubject(SubjectVO subjectVO) throws SMSBusinessException;

	boolean deleteSubject(String subjectId) throws SMSBusinessException;

	long getSubjectCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllSubjectBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
