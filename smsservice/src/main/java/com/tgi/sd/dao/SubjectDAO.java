package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface SubjectDAO {

	SubjectVO saveSubject(SubjectVO subjectVO) throws SMSBusinessException;

	SubjectVO getSubjectById(String subjectId) throws SMSBusinessException;

	List<SubjectVO> getAllSubjectBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getSubjectCountBySchoolId(String schoolId) throws SMSBusinessException;

	SubjectVO updateSubject(SubjectVO subjectVO) throws SMSBusinessException;

	boolean deleteSubject(String subjectId) throws SMSBusinessException;

	SubjectVO getSubjectByName(String subjectName, String id, String schoolId)
			throws SMSBusinessException;

}
