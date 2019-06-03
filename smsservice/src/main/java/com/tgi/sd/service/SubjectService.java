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
import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.manager.SubjectManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/subject")
public class SubjectService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(SubjectService.class);
	
	private SubjectManager subjectManager;
	
	
	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}

	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
	public ResponseVO saveSubject(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubject Starts");
		}
		ResponseVO responseVO = null;
		SubjectVO subjectVO = null;
		try{
			subjectVO = (SubjectVO) parseObjectFromRequest(requestVO,SubjectVO.class);
			if(null != subjectVO) {
				subjectVO = subjectManager.saveSubject(subjectVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectVO",subjectVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubject Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getSubjectById", method = RequestMethod.GET)
	public ResponseVO getSubjectById(@RequestParam String subjectId){
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectById Starts");
		}
		ResponseVO responseVO = null;
		SubjectVO subjectVO = null;
		try{
			subjectVO = subjectManager.getSubjectById(subjectId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectVO", subjectVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllSubjectBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllSubjectBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = subjectManager.getAllSubjectBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSubject", method = RequestMethod.POST)
	public ResponseVO updateSubject(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubject Starts");
		}
		ResponseVO responseVO = null;
		try {
			SubjectVO subjectVO = (SubjectVO) parseObjectFromRequest(requestVO, SubjectVO.class);
			if (null != subjectVO) {
				subjectVO = subjectManager.updateSubject(subjectVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("SubjectVO", subjectVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubject Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteSubject", method = RequestMethod.DELETE)
	public ResponseVO deleteSubject(@RequestParam String subjectId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubject Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = subjectManager.deleteSubject(subjectId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubject Ends");
		}
		return responseVO;
	}
	
}
