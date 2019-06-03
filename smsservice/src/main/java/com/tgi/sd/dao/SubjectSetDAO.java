package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.SubjectSetVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface SubjectSetDAO {

	SubjectSetVO saveSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException;

	SubjectSetVO getSubjectSetById(String subjectSetId) throws SMSBusinessException;

	List<SubjectSetVO> getAllSubjectSetBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getSubjectSetCountBySchoolId(String schoolId) throws SMSBusinessException;

	SubjectSetVO updateSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException;

	boolean deleteSubjectSet(String subjectSetId) throws SMSBusinessException;

}
