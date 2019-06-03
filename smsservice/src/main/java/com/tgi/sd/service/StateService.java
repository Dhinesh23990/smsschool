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
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.manager.StateManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/state")
public class StateService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(StateService.class);
	
	private StateManager stateManager;
	
	
	public StateManager getStateManager() {
		return stateManager;
	}

	public void setStateManager(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@RequestMapping(value = "/saveState", method = RequestMethod.POST)
	public ResponseVO saveState(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveState Starts");
		}
		ResponseVO responseVO = null;
		StateVO stateVO = null;
		try{
			stateVO = (StateVO) parseObjectFromRequest(requestVO,StateVO.class);
			if(null != stateVO) {
				stateVO = stateManager.saveState(stateVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("StateVO",stateVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveState Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getStateById", method = RequestMethod.GET)
	public ResponseVO getStateById(@RequestParam String stateId){
		if(logger.isDebugEnabled()) {
			logger.debug("getStateById Starts");
		}
		ResponseVO responseVO = null;
		StateVO stateVO = null;
		try{
			stateVO = stateManager.getStateById(stateId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("StateVO", stateVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getStateById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllStateBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllStateBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllStateBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = stateManager.getAllStateBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllStateBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	
	@RequestMapping(value="/getAllStateByCountryName",method=RequestMethod.GET)
	public ResponseVO getAllStateByCountryName(@RequestParam String countryName)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = stateManager.getAllStateByCountryName(countryName);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	public ResponseVO updateState(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateState Starts");
		}
		ResponseVO responseVO = null;
		try {
			StateVO stateVO = (StateVO) parseObjectFromRequest(requestVO, StateVO.class);
			if (null != stateVO) {
				stateVO = stateManager.updateState(stateVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("StateVO", stateVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateState Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteState", method = RequestMethod.DELETE)
	public ResponseVO deleteState(@RequestParam String stateId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteState Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = stateManager.deleteState(stateId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteState Ends");
		}
		return responseVO;
	}
	
}
