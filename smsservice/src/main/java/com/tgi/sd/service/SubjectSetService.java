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
import com.tgi.sd.domain.config.SubjectSetVO;
import com.tgi.sd.manager.SubjectSetManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/subjectSet")
public class SubjectSetService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(SubjectSetService.class);
	
	private SubjectSetManager subjectSetManager;
	
	
	public SubjectSetManager getSubjectSetManager() {
		return subjectSetManager;
	}

	public void setSubjectSetManager(SubjectSetManager subjectSetManager) {
		this.subjectSetManager = subjectSetManager;
	}

	@RequestMapping(value = "/saveSubjectSet", method = RequestMethod.POST)
	public ResponseVO saveSubjectSet(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet Starts");
		}
		ResponseVO responseVO = null;
		SubjectSetVO subjectSetVO = null;
		try{
			subjectSetVO = (SubjectSetVO) parseObjectFromRequest(requestVO,SubjectSetVO.class);
			if(null != subjectSetVO) {
				subjectSetVO = subjectSetManager.saveSubjectSet(subjectSetVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectSetVO",subjectSetVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getSubjectSetById", method = RequestMethod.GET)
	public ResponseVO getSubjectSetById(@RequestParam String subjectSetId){
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById Starts");
		}
		ResponseVO responseVO = null;
		SubjectSetVO subjectSetVO = null;
		try{
			subjectSetVO = subjectSetManager.getSubjectSetById(subjectSetId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SubjectSetVO", subjectSetVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllSubjectSetBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllSubjectSetBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = subjectSetManager.getAllSubjectSetBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSubjectSet", method = RequestMethod.POST)
	public ResponseVO updateSubjectSet(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Starts");
		}
		ResponseVO responseVO = null;
		try {
			SubjectSetVO subjectSetVO = (SubjectSetVO) parseObjectFromRequest(requestVO, SubjectSetVO.class);
			if (null != subjectSetVO) {
				subjectSetVO = subjectSetManager.updateSubjectSet(subjectSetVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("SubjectSetVO", subjectSetVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteSubjectSet", method = RequestMethod.DELETE)
	public ResponseVO deleteSubjectSet(@RequestParam String subjectSetId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = subjectSetManager.deleteSubjectSet(subjectSetId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Ends");
		}
		return responseVO;
	}
	
}
