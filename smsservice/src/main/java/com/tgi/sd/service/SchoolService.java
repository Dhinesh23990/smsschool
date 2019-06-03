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
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.manager.SchoolManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/school")
public class SchoolService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(SchoolService.class);
	
	private SchoolManager schoolManager;
	
	
	public SchoolManager getSchoolManager() {
		return schoolManager;
	}

	public void setSchoolManager(SchoolManager schoolManager) {
		this.schoolManager = schoolManager;
	}

	@RequestMapping(value = "/saveSchool", method = RequestMethod.POST)
	public ResponseVO saveSchool(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveSchool Starts");
		}
		ResponseVO responseVO = null;
		SchoolVO schoolVO = null;
		try{
			schoolVO = (SchoolVO) parseObjectFromRequest(requestVO,SchoolVO.class);
			if(null != schoolVO) {
				schoolVO = schoolManager.saveSchool(schoolVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SchoolVO",schoolVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveSchool Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getSchoolById", method = RequestMethod.GET)
	public ResponseVO getSchoolById(@RequestParam String schoolId){
		if(logger.isDebugEnabled()) {
			logger.debug("getSchoolById Starts");
		}
		ResponseVO responseVO = null;
		SchoolVO schoolVO = null;
		try{
			schoolVO = schoolManager.getSchoolById(schoolId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SchoolVO", schoolVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getSchoolById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllSchool",method=RequestMethod.GET)
	public ResponseVO getAllSchool(int pageIndex, int pageSize, String status)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllSchool Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = schoolManager.getAllSchool(pageIndex, pageSize, status);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllSchool Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSchool", method = RequestMethod.POST)
	public ResponseVO updateSchool(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSchool Starts");
		}
		ResponseVO responseVO = null;
		try {
			SchoolVO schoolVO = (SchoolVO) parseObjectFromRequest(requestVO, SchoolVO.class);
			if (null != schoolVO) {
				schoolVO = schoolManager.updateSchool(schoolVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("SchoolVO", schoolVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateSchool Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSchoolStatus", method = RequestMethod.POST)
	public ResponseVO updateSchoolStatus(@RequestParam String schoolId,String status)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSchoolStatus Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isStatusUpdated = false;
		try {
			isStatusUpdated = schoolManager.updateSchoolStatus(schoolId,status);
			responseObjectsMap.put("isStatusUpdated", isStatusUpdated);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSchool Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/deleteSchool", method = RequestMethod.DELETE)
	public ResponseVO deleteSchool(@RequestParam String schoolId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSchool Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = schoolManager.deleteSchool(schoolId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSchool Ends");
		}
		return responseVO;
	}
	
}
