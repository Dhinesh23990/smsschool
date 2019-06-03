package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.manager.CourseManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/course")
public class CourseService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CourseService.class);
	
	private CourseManager courseManager;
	
	
	public CourseManager getCourseManager() {
		return courseManager;
	}

	public void setCourseManager(CourseManager courseManager) {
		this.courseManager = courseManager;
	}

	@RequestMapping(value = "/saveCourse", method = RequestMethod.POST)
	public ResponseVO saveCourse(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveCourse Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		CourseVO courseVO = null;
		try{
			courseVO = (CourseVO) parseObjectFromRequest(requestVO,CourseVO.class);
			if(null != courseVO) {
				courseVO = courseManager.saveCourse(courseVO);
			}
			
			responseObjectsMap.put("CourseVO",courseVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveCourse Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getCourseById", method = RequestMethod.GET)
	public ResponseVO getCourseById(@RequestParam String courseId){
		if(logger.isDebugEnabled()) {
			logger.debug("getCourseById Starts");
		}
		ResponseVO responseVO = null;
		CourseVO courseVO = null;
		try{
			courseVO = courseManager.getCourseById(courseId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CourseVO", courseVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getCourseById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllCourseBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllCourseBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Starts");
		}
		ResponseVO responseVO = null;
		
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = courseManager.getAllCourseBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateCourse", method = RequestMethod.POST)
	public ResponseVO updateCourse(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateCourse Starts");
		}
		ResponseVO responseVO = null;
		CourseVO courseVO = null;
		try {
			courseVO = (CourseVO) parseObjectFromRequest(requestVO, CourseVO.class);
			if (null != courseVO) {
				courseVO = courseManager.updateCourse(courseVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("CourseVO", courseVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateCourse Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.DELETE)
	public ResponseVO deleteCourse(@RequestParam String courseId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCourse Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = courseManager.deleteCourse(courseId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCourse Ends");
		}
		return responseVO;
	}
	
}
