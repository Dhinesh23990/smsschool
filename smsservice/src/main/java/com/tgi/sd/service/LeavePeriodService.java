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
import com.tgi.sd.manager.LeavePeriodManager;
import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/leavePeriod")
public class LeavePeriodService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(LeavePeriodService.class);
	
	private LeavePeriodManager leavePeriodManager;
	
	
	public LeavePeriodManager getLeavePeriodManager() {
		return leavePeriodManager;
	}

	public void setLeavePeriodManager(LeavePeriodManager leavePeriodManager) {
		this.leavePeriodManager = leavePeriodManager;
	}

	@RequestMapping(value = "/saveLeavePeriod", method = RequestMethod.POST)
	public ResponseVO saveLeavePeriod(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod Starts");
		}
		ResponseVO responseVO = null;
		LeavePeriodVO leavePeriodVO = null;
		try{
			leavePeriodVO = (LeavePeriodVO) parseObjectFromRequest(requestVO,LeavePeriodVO.class);
			if(null != leavePeriodVO) {
				leavePeriodVO = leavePeriodManager.saveLeavePeriod(leavePeriodVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeavePeriodVO",leavePeriodVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getLeavePeriodById", method = RequestMethod.GET)
	public ResponseVO getLeavePeriodById(@RequestParam String leavePeriodId){
		if(logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById Starts");
		}
		ResponseVO responseVO = null;
		LeavePeriodVO leavePeriodVO = null;
		try{
			leavePeriodVO = leavePeriodManager.getLeavePeriodById(leavePeriodId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeavePeriodVO", leavePeriodVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllLeavePeriodBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllLeavePeriodBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = leavePeriodManager.getAllLeavePeriodBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value="/getAllLeavePeriodByMasterId",method=RequestMethod.GET)
	public ResponseVO getAllLeavePeriodByMasterId(@RequestParam String schoolId, @RequestParam String leaveMasterId)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodByMasterId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = leavePeriodManager.getAllLeavePeriodByMasterId(schoolId, leaveMasterId);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodByMasterId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateLeavePeriod", method = RequestMethod.POST)
	public ResponseVO updateLeavePeriod(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Starts");
		}
		ResponseVO responseVO = null;
		try {
			LeavePeriodVO leavePeriodVO = (LeavePeriodVO) parseObjectFromRequest(requestVO, LeavePeriodVO.class);
			if (null != leavePeriodVO) {
				leavePeriodVO = leavePeriodManager.updateLeavePeriod(leavePeriodVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("LeavePeriodVO", leavePeriodVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteLeavePeriod", method = RequestMethod.DELETE)
	public ResponseVO deleteLeavePeriod(@RequestParam String leavePeriodId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = leavePeriodManager.deleteLeavePeriod(leavePeriodId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Ends");
		}
		return responseVO;
	}
	
}
