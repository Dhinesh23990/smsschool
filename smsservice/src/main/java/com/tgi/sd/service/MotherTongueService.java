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
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.manager.MotherTongueManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/motherTongue")
public class MotherTongueService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(MotherTongueService.class);
	
	private MotherTongueManager motherTongueManager;
	
	
	public MotherTongueManager getMotherTongueManager() {
		return motherTongueManager;
	}

	public void setMotherTongueManager(MotherTongueManager motherTongueManager) {
		this.motherTongueManager = motherTongueManager;
	}

	@RequestMapping(value = "/saveMotherTongue", method = RequestMethod.POST)
	public ResponseVO saveMotherTongue(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue Starts");
		}
		ResponseVO responseVO = null;
		MotherTongueVO motherTongueVO = null;
		try{
			motherTongueVO = (MotherTongueVO) parseObjectFromRequest(requestVO,MotherTongueVO.class);
			if(null != motherTongueVO) {
				motherTongueVO = motherTongueManager.saveMotherTongue(motherTongueVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("MotherTongueVO",motherTongueVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getMotherTongueById", method = RequestMethod.GET)
	public ResponseVO getMotherTongueById(@RequestParam String motherTongueId){
		if(logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById Starts");
		}
		ResponseVO responseVO = null;
		MotherTongueVO motherTongueVO = null;
		try{
			motherTongueVO = motherTongueManager.getMotherTongueById(motherTongueId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("MotherTongueVO", motherTongueVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllMotherTongueBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllMotherTongueBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = motherTongueManager.getAllMotherTongueBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateMotherTongue", method = RequestMethod.POST)
	public ResponseVO updateMotherTongue(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Starts");
		}
		ResponseVO responseVO = null;
		try {
			MotherTongueVO motherTongueVO = (MotherTongueVO) parseObjectFromRequest(requestVO, MotherTongueVO.class);
			if (null != motherTongueVO) {
				motherTongueVO = motherTongueManager.updateMotherTongue(motherTongueVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("MotherTongueVO", motherTongueVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteMotherTongue", method = RequestMethod.DELETE)
	public ResponseVO deleteMotherTongue(@RequestParam String motherTongueId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = motherTongueManager.deleteMotherTongue(motherTongueId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Ends");
		}
		return responseVO;
	}
	
}
