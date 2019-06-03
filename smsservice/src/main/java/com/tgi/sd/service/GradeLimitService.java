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
import com.tgi.sd.domain.config.GradeLimitVO;
import com.tgi.sd.manager.GradeLimitManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/gradeLimit")
public class GradeLimitService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(GradeLimitService.class);
	
	private GradeLimitManager gradeLimitManager;
	
	
	public GradeLimitManager getGradeLimitManager() {
		return gradeLimitManager;
	}

	public void setGradeLimitManager(GradeLimitManager gradeLimitManager) {
		this.gradeLimitManager = gradeLimitManager;
	}

	@RequestMapping(value = "/saveGradeLimit", method = RequestMethod.POST)
	public ResponseVO saveGradeLimit(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit Starts");
		}
		ResponseVO responseVO = null;
		GradeLimitVO gradeLimitVO = null;
		try{
			gradeLimitVO = (GradeLimitVO) parseObjectFromRequest(requestVO,GradeLimitVO.class);
			if(null != gradeLimitVO) {
				gradeLimitVO = gradeLimitManager.saveGradeLimit(gradeLimitVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GradeLimitVO",gradeLimitVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getGradeLimitById", method = RequestMethod.GET)
	public ResponseVO getGradeLimitById(@RequestParam String gradeLimitId){
		if(logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById Starts");
		}
		ResponseVO responseVO = null;
		GradeLimitVO gradeLimitVO = null;
		try{
			gradeLimitVO = gradeLimitManager.getGradeLimitById(gradeLimitId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GradeLimitVO", gradeLimitVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllGradeLimitBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllGradeLimitBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = gradeLimitManager.getAllGradeLimitBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateGradeLimit", method = RequestMethod.POST)
	public ResponseVO updateGradeLimit(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Starts");
		}
		ResponseVO responseVO = null;
		try {
			GradeLimitVO gradeLimitVO = (GradeLimitVO) parseObjectFromRequest(requestVO, GradeLimitVO.class);
			if (null != gradeLimitVO) {
				gradeLimitVO = gradeLimitManager.updateGradeLimit(gradeLimitVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("GradeLimitVO", gradeLimitVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteGradeLimit", method = RequestMethod.DELETE)
	public ResponseVO deleteGradeLimit(@RequestParam String gradeLimitId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = gradeLimitManager.deleteGradeLimit(gradeLimitId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Ends");
		}
		return responseVO;
	}
	
}
