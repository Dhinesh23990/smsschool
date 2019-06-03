package com.tgi.sd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.manager.TeacherManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class TeacherService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(TeacherService.class);

	TeacherManager teacherManager;
	
	public TeacherManager getTeacherManager() {
		return teacherManager;
	}

	public void setTeacherManager(TeacherManager teacherManager) {
		this.teacherManager = teacherManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_TEACHER, method = RequestMethod.POST)
	public ResponseVO saveTeacher(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveTeacher :::");
		}
		
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			TeacherVO teacherVO = (TeacherVO) parseObjectFromRequest(requestVO,TeacherVO.class);
			if(null != teacherVO) {
			teacherVO = teacherManager.saveTeacher(teacherVO);
			responseObjectMap.put("teacherVO", teacherVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveTeacher :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_TEACHER, method = RequestMethod.GET)
	public ResponseVO getTeacher(@RequestParam String teacherId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeacher Id:::" + teacherId);
		}ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			TeacherVO teacherVO =  teacherManager.getTeacher(teacherId);
			responseObjectMap.put("teacherVO", teacherVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getTeacher :::" + teacherId);
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_ALL_TEACHER_BY_GENDER, method = RequestMethod.GET)
	public ResponseVO getAllTeacherByGender(@RequestParam String schoolId,@RequestParam String gender) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacherByGender Id:::");
		}
		List<TeacherVO> teacherList = null;
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			teacherList =  teacherManager.getAllTeacherByGender(schoolId,gender);
			responseObjectMap.put("teacherList", teacherList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacherByGender :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.VIEW_TEACHER, method = RequestMethod.GET)
	public ResponseVO viewTeacher(@RequestParam String teacherId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewTeacher Id:::" + teacherId);
		}ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			TeacherVO teacherVO =  teacherManager.viewTeacher(teacherId);
			responseObjectMap.put("teacherVO", teacherVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End viewTeacher :::" + teacherId);
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.UPDATE_TEACHER, method = RequestMethod.POST)
	public ResponseVO updateTeacher(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateTeacher :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			TeacherVO teacherVO = (TeacherVO) parseObjectFromRequest(requestVO,TeacherVO.class);
			teacherVO = teacherManager.updateTeacher(teacherVO);
			responseObjectMap.put("teacherVO", teacherVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateTeacher :::");
		}
		return responseVO;	
	}	
	
	@RequestMapping(value = EndPointConstants.GET_ALL_TEACHER, method = RequestMethod.GET)
	public ResponseVO getAllTeacher(@RequestParam String schoolId,int pageIndex, int pageSize) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacher");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap =  teacherManager.getAllTeacher(schoolId,pageIndex,pageSize);
		//	responseObjectMap.put("teacherList", teacherList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacher");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.DELETE_TEACHER, method = RequestMethod.DELETE)
	public ResponseVO deleteTeacher(@RequestParam String teacherId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = teacherManager.deleteTeacher(teacherId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Ends");
		}
		return responseVO;
	}
}
