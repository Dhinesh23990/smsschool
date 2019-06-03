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
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.manager.EducationQualificationManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/education")
public class EducationalQualificationService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CourseService.class);
	
	private EducationQualificationManager educationQualificationManager;
	
	
	public EducationQualificationManager getEducationQualificationManager() {
		return educationQualificationManager;
	}

	public void setEducationQualificationManager(EducationQualificationManager educationQualificationManager) {
		this.educationQualificationManager = educationQualificationManager;
	}

	@RequestMapping(value = "/saveEducation", method = RequestMethod.POST)
	public ResponseVO saveEducation(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveEducation Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		EducationalVO educationalVO = null;
		try{
			educationalVO = (EducationalVO) parseObjectFromRequest(requestVO,EducationalVO.class);
			if(null != educationalVO) {
				educationalVO = educationQualificationManager.saveEducation(educationalVO);
			}
			
			responseObjectsMap.put("EducationalVO",educationalVO);
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
	
	@RequestMapping(value = "/getEducationById", method = RequestMethod.GET)
	public ResponseVO getEducationById(@RequestParam String educationId){
		if(logger.isDebugEnabled()) {
			logger.debug("getEducationById Starts");
		}
		ResponseVO responseVO = null;
		EducationalVO educationalVO = null;
		try{
			educationalVO = educationQualificationManager.getEducationById(educationId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("EducationalVO", educationalVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getEducationById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllEducationBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllEducationBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllEducaationBySchoolId Starts");
		}
		ResponseVO responseVO = null;
		
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = educationQualificationManager.getAllEducationBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllEducaationBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateEducation", method = RequestMethod.POST)
	public ResponseVO updateEducation(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateEducation Starts");
		}
		ResponseVO responseVO = null;
		EducationalVO educationalVO = null;
		try {
			educationalVO = (EducationalVO) parseObjectFromRequest(requestVO, EducationalVO.class);
			if (null != educationalVO) {
				educationalVO = educationQualificationManager.updateEducation(educationalVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("EducationalVO", educationalVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateEducation Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteEducation", method = RequestMethod.DELETE)
	public ResponseVO deleteCourse(@RequestParam String educationId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteEducation Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = educationQualificationManager.deleteEducation(educationId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteEducation Ends");
		}
		return responseVO;
	}
	
}
