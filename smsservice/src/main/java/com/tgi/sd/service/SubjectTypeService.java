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
import com.tgi.sd.domain.config.SubjectTypeVO;
import com.tgi.sd.manager.SubjectTypeManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/subjectType")
public class SubjectTypeService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(SubjectTypeService.class);
	
	private SubjectTypeManager subjectTypeManager;
	
	
	public SubjectTypeManager getSubjectTypeManager() {
		return subjectTypeManager;
	}

	public void setSubjectTypeManager(SubjectTypeManager subjectTypeManager) {
		this.subjectTypeManager = subjectTypeManager;
	}

	@RequestMapping(value = "/saveSubjectType", method = RequestMethod.POST)
	public ResponseVO saveSubjectType(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubjectType Starts");
		}
		ResponseVO responseVO = null;
		SubjectTypeVO subjectTypeVO = null;
		try{
			subjectTypeVO = (SubjectTypeVO) parseObjectFromRequest(requestVO,SubjectTypeVO.class);
			if(null != subjectTypeVO) {
				subjectTypeVO = subjectTypeManager.saveSubjectType(subjectTypeVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectTypeVO",subjectTypeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubjectType Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getSubjectTypeById", method = RequestMethod.GET)
	public ResponseVO getSubjectTypeById(@RequestParam String subjectTypeId){
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById Starts");
		}
		ResponseVO responseVO = null;
		SubjectTypeVO subjectTypeVO = null;
		try{
			subjectTypeVO = subjectTypeManager.getSubjectTypeById(subjectTypeId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectTypeVO", subjectTypeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllSubjectTypeBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllSubjectTypeBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = subjectTypeManager.getAllSubjectTypeBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSubjectType", method = RequestMethod.POST)
	public ResponseVO updateSubjectType(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Starts");
		}
		ResponseVO responseVO = null;
		try {
			SubjectTypeVO subjectTypeVO = (SubjectTypeVO) parseObjectFromRequest(requestVO, SubjectTypeVO.class);
			if (null != subjectTypeVO) {
				subjectTypeVO = subjectTypeManager.updateSubjectType(subjectTypeVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("SubjectTypeVO", subjectTypeVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteSubjectType", method = RequestMethod.DELETE)
	public ResponseVO deleteSubjectType(@RequestParam String subjectTypeId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = subjectTypeManager.deleteSubjectType(subjectTypeId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Ends");
		}
		return responseVO;
	}
	
}
