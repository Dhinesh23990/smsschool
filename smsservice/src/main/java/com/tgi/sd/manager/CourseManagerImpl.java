package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CourseDAO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class CourseManagerImpl implements CourseManager{
	
	private static Logger logger = Logger.getLogger(CourseManagerImpl.class);
	
	private CourseDAO courseDAO;
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	@Override
	public CourseVO saveCourse(CourseVO courseVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse starts");
		}
		courseVO.setId(UUID.randomUUID().toString());
		CourseVO course = courseDAO.getCourseByName(courseVO.getCourseName(),null,courseVO.getSchoolId());	
		if(course!=null){
			throw new SMSBusinessException(SMSConstants.COURSE_ALREADY_EXIST);
		}	
		courseVO = courseDAO.saveCourse(courseVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse ends");
		}
		return courseVO;
		
	}
	@Override
	public CourseVO getCourseById(String courseId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById Starts");
		}
		
		CourseVO courseVO = courseDAO.getCourseById(courseId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById Ends");
		}
		
		return courseVO;
	}
	
	@Override
	public Map<String, Object> getAllCourseBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = courseDAO.getCourseCountBySchoolId(schoolId);
		List<CourseVO> courseVOs = courseDAO.getAllCourseBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("courseVOs", courseVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getCourseCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseCountBySchoolId Starts");
		}

		long cnt = courseDAO.getCourseCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public CourseVO updateCourse(CourseVO courseVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCourse Starts");
		}
		
		CourseVO course = courseDAO.getCourseByName(courseVO.getCourseName(),courseVO.getId(),courseVO.getSchoolId());	
		if(course!=null){
			throw new SMSBusinessException(SMSConstants.COURSE_ALREADY_EXIST);
		}	
		courseVO = courseDAO.updateCourse(courseVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCourse Ends");
		}
		return courseVO;
	}
	
	@Override
	public boolean deleteCourse(String courseId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCourse Starts");
		}
		
		boolean isDeleted = courseDAO.deleteCourse(courseId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCourse Ends");
		}
		return isDeleted;
	}
	
}
