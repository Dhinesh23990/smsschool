 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface EducationQualificationManager {

	EducationalVO saveEducation(EducationalVO educationalVO) throws SMSBusinessException;

	EducationalVO getEducationById(String educationId) throws SMSBusinessException;

	EducationalVO updateEducation(EducationalVO educationalVO) throws SMSBusinessException;

	boolean deleteEducation(String educationId) throws SMSBusinessException;

	long getCourseCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllEducationBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
