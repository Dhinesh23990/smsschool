package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface EducationQualificationDAO {

	EducationalVO saveEducation(EducationalVO educationalVO) throws SMSBusinessException;

	EducationalVO getEducationById(String educationId) throws SMSBusinessException;

	List<EducationalVO> getAllEducationBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getEducationCountBySchoolId(String schoolId) throws SMSBusinessException;

	EducationalVO updateEducation(EducationalVO educationalVO) throws SMSBusinessException;

	boolean deleteEducation(String educationId) throws SMSBusinessException;

	EducationalVO getEducationByName(String educationName, String id, String schoolId) throws SMSBusinessException;

}
