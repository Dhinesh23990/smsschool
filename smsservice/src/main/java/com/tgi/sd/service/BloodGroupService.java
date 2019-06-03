package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.manager.BloodGroupManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/bloodgroup")
public class BloodGroupService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(BloodGroupService.class);
	
	private BloodGroupManager bloodGroupManager;
	
	
	public BloodGroupManager getBloodGroupManager() {
		return bloodGroupManager;
	}

	public void setBloodGroupManager(BloodGroupManager bloodGroupManager) {
		this.bloodGroupManager = bloodGroupManager;
	}

	@RequestMapping(value = "/saveBloodGroup", method = RequestMethod.POST)
	public ResponseVO saveBloodGroup(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup Starts");
		}
		ResponseVO responseVO = null;
		BloodGroupVO bloodGroupVO = null;
		try{
			bloodGroupVO = (BloodGroupVO) parseObjectFromRequest(requestVO,BloodGroupVO.class);
			if(null != bloodGroupVO) {
				bloodGroupVO = bloodGroupManager.saveBloodGroup(bloodGroupVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BloodGroupVO",bloodGroupVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getBloodGroupById", method = RequestMethod.GET)
	public ResponseVO getBloodGroupById(@RequestParam String bloodGroupId){
		if(logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById Starts");
		}
		ResponseVO responseVO = null;
		BloodGroupVO bloodGroupVO = null;
		try{
			bloodGroupVO = bloodGroupManager.getBloodGroupById(bloodGroupId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BloodGroupVO", bloodGroupVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllBloodGroupBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllBloodGroupBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = bloodGroupManager.getAllBloodGroupBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateBloodGroup", method = RequestMethod.POST)
	public ResponseVO updateBloodGroup(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Starts");
		}
		ResponseVO responseVO = null;
		try {
			BloodGroupVO bloodGroupVO = (BloodGroupVO) parseObjectFromRequest(requestVO, BloodGroupVO.class);
			if (null != bloodGroupVO) {
				bloodGroupVO = bloodGroupManager.updateBloodGroup(bloodGroupVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("BloodGroupVO", bloodGroupVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteBloodGroup", method = RequestMethod.DELETE)
	public ResponseVO deleteBloodGroup(@RequestParam String bloodGroupId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = bloodGroupManager.deleteBloodGroup(bloodGroupId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Ends");
		}
		return responseVO;
	}
	
}
