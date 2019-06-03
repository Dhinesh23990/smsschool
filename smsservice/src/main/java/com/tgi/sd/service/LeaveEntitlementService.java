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
import com.tgi.sd.manager.LeaveEntitlementManager;
import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/leaveEntitlement")
public class LeaveEntitlementService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(LeaveEntitlementService.class);
	
	private LeaveEntitlementManager leaveEntitlementManager;
	
	
	public LeaveEntitlementManager getLeaveEntitlementManager() {
		return leaveEntitlementManager;
	}

	public void setLeaveEntitlementManager(LeaveEntitlementManager leaveEntitlementManager) {
		this.leaveEntitlementManager = leaveEntitlementManager;
	}

	@RequestMapping(value = "/saveLeaveEntitlement", method = RequestMethod.POST)
	public ResponseVO saveLeaveEntitlement(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement Starts");
		}
		ResponseVO responseVO = null;
		LeaveEntitlementVO leaveEntitlementVO = null;
		try{
			leaveEntitlementVO = (LeaveEntitlementVO) parseObjectFromRequest(requestVO,LeaveEntitlementVO.class);
			if(null != leaveEntitlementVO) {
				leaveEntitlementVO = leaveEntitlementManager.saveLeaveEntitlement(leaveEntitlementVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeaveEntitlementVO",leaveEntitlementVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getLeaveEntitlementById", method = RequestMethod.GET)
	public ResponseVO getLeaveEntitlementById(@RequestParam String leaveEntitlementId){
		if(logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById Starts");
		}
		ResponseVO responseVO = null;
		LeaveEntitlementVO leaveEntitlementVO = null;
		try{
			leaveEntitlementVO = leaveEntitlementManager.getLeaveEntitlementById(leaveEntitlementId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LeaveEntitlementVO", leaveEntitlementVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllLeaveEntitlementBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllLeaveEntitlementBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = leaveEntitlementManager.getAllLeaveEntitlementBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateLeaveEntitlement", method = RequestMethod.POST)
	public ResponseVO updateLeaveEntitlement(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Starts");
		}
		ResponseVO responseVO = null;
		try {
			LeaveEntitlementVO leaveEntitlementVO = (LeaveEntitlementVO) parseObjectFromRequest(requestVO, LeaveEntitlementVO.class);
			if (null != leaveEntitlementVO) {
				leaveEntitlementVO = leaveEntitlementManager.updateLeaveEntitlement(leaveEntitlementVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("LeaveEntitlementVO", leaveEntitlementVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteLeaveEntitlement", method = RequestMethod.DELETE)
	public ResponseVO deleteLeaveEntitlement(@RequestParam String leaveEntitlementId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = leaveEntitlementManager.deleteLeaveEntitlement(leaveEntitlementId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Ends");
		}
		return responseVO;
	}
	
}
