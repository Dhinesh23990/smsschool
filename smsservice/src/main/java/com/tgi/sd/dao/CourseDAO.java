package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface CourseDAO {

	CourseVO saveCourse(CourseVO courseVO) throws SMSBusinessException;

	CourseVO getCourseById(String courseId) throws SMSBusinessException;

	List<CourseVO> getAllCourseBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getCourseCountBySchoolId(String schoolId) throws SMSBusinessException;

	CourseVO updateCourse(CourseVO courseVO) throws SMSBusinessException;

	boolean deleteCourse(String courseId) throws SMSBusinessException;

	CourseVO getCourseByName(String courseName, String id, String schoolId) throws SMSBusinessException;

	CourseVO getCourseByCourseName(String course, String schoolId) throws SMSBusinessException;

}
