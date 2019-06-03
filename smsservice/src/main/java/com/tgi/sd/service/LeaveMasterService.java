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
import com.tgi.sd.manager.LeaveMasterManager;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/leaveMaster")
public class LeaveMasterService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(LeaveMasterService.class);
	
	private LeaveMasterManager leaveMasterManager;
	
	
	public LeaveMasterManager getLeaveMasterManager() {
		return leaveMasterManager;
	}

	public void setLeaveMasterManager(LeaveMasterManager leaveMasterManager) {
		this.leaveMasterManager = leaveMasterManager;
	}

	@RequestMapping(value = "/saveLeaveMaster", method = RequestMethod.POST)
	public ResponseVO saveLeaveMaster(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster Starts");
		}
		ResponseVO responseVO = null;
		LeaveMasterVO leaveMasterVO = null;
		try{
			leaveMasterVO = (LeaveMasterVO) parseObjectFromRequest(requestVO,LeaveMasterVO.class);
			if(null != leaveMasterVO) {
				leaveMasterVO = leaveMasterManager.saveLeaveMaster(leaveMasterVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeaveMasterVO",leaveMasterVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getLeaveMasterById", method = RequestMethod.GET)
	public ResponseVO getLeaveMasterById(@RequestParam String leaveMasterId){
		if(logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById Starts");
		}
		ResponseVO responseVO = null;
		LeaveMasterVO leaveMasterVO = null;
		try{
			leaveMasterVO = leaveMasterManager.getLeaveMasterById(leaveMasterId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeaveMasterVO", leaveMasterVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllLeaveMasterBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllLeaveMasterBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = leaveMasterManager.getAllLeaveMasterBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateLeaveMaster", method = RequestMethod.POST)
	public ResponseVO updateLeaveMaster(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Starts");
		}
		ResponseVO responseVO = null;
		try {
			LeaveMasterVO leaveMasterVO = (LeaveMasterVO) parseObjectFromRequest(requestVO, LeaveMasterVO.class);
			if (null != leaveMasterVO) {
				leaveMasterVO = leaveMasterManager.updateLeaveMaster(leaveMasterVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("LeaveMasterVO", leaveMasterVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteLeaveMaster", method = RequestMethod.DELETE)
	public ResponseVO deleteLeaveMaster(@RequestParam String leaveMasterId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = leaveMasterManager.deleteLeaveMaster(leaveMasterId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Ends");
		}
		return responseVO;
	}
	
}
