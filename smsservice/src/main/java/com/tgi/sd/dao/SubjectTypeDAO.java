package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.SubjectTypeVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface SubjectTypeDAO {

	SubjectTypeVO saveSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException;

	SubjectTypeVO getSubjectTypeById(String subjectTypeId) throws SMSBusinessException;

	List<SubjectTypeVO> getAllSubjectTypeBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getSubjectTypeCountBySchoolId(String schoolId) throws SMSBusinessException;

	SubjectTypeVO updateSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException;

	boolean deleteSubjectType(String subjectTypeId) throws SMSBusinessException;

	SubjectTypeVO getSubjectTypeByName(String subjectTypeName, String id,
			String schoolId) throws SMSBusinessException;

}
