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
import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.manager.FeeComponentManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/feeComponent")
public class FeeComponentService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(FeeComponentService.class);
	
	private FeeComponentManager feeComponentManager;
	
	
	public FeeComponentManager getFeeComponentManager() {
		return feeComponentManager;
	}

	public void setFeeComponentManager(FeeComponentManager feeComponentManager) {
		this.feeComponentManager = feeComponentManager;
	}

	@RequestMapping(value = "/saveFeeComponent", method = RequestMethod.POST)
	public ResponseVO saveFeeComponent(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent Starts");
		}
		ResponseVO responseVO = null;
		FeeComponentVO feeComponentVO = null;
		try{
			feeComponentVO = (FeeComponentVO) parseObjectFromRequest(requestVO,FeeComponentVO.class);
			if(null != feeComponentVO) {
				feeComponentVO = feeComponentManager.saveFeeComponent(feeComponentVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeComponentVO",feeComponentVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getFeeComponentById", method = RequestMethod.GET)
	public ResponseVO getFeeComponentById(@RequestParam String feeComponentId){
		if(logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById Starts");
		}
		ResponseVO responseVO = null;
		FeeComponentVO feeComponentVO = null;
		try{
			feeComponentVO = feeComponentManager.getFeeComponentById(feeComponentId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeComponentVO", feeComponentVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllFeeComponentBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllFeeComponentBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = feeComponentManager.getAllFeeComponentBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateFeeComponent", method = RequestMethod.POST)
	public ResponseVO updateFeeComponent(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Starts");
		}
		ResponseVO responseVO = null;
		try {
			FeeComponentVO feeComponentVO = (FeeComponentVO) parseObjectFromRequest(requestVO, FeeComponentVO.class);
			if (null != feeComponentVO) {
				feeComponentVO = feeComponentManager.updateFeeComponent(feeComponentVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("FeeComponentVO", feeComponentVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteFeeComponent", method = RequestMethod.DELETE)
	public ResponseVO deleteFeeComponent(@RequestParam String feeComponentId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = feeComponentManager.deleteFeeComponent(feeComponentId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Ends");
		}
		return responseVO;
	}
	
}
