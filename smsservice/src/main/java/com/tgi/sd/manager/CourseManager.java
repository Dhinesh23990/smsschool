 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface CourseManager {

	CourseVO saveCourse(CourseVO courseVO) throws SMSBusinessException;

	CourseVO getCourseById(String courseId) throws SMSBusinessException;

	CourseVO updateCourse(CourseVO courseVO) throws SMSBusinessException;

	boolean deleteCourse(String courseId) throws SMSBusinessException;

	long getCourseCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllCourseBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
