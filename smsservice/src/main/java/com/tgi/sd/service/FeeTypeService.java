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
import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.manager.FeeTypeManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/feeType")
public class FeeTypeService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(FeeTypeService.class);
	
	private FeeTypeManager feeTypeManager;
	
	
	public FeeTypeManager getFeeTypeManager() {
		return feeTypeManager;
	}

	public void setFeeTypeManager(FeeTypeManager feeTypeManager) {
		this.feeTypeManager = feeTypeManager;
	}

	@RequestMapping(value = "/saveFeeType", method = RequestMethod.POST)
	public ResponseVO saveFeeType(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveFeeType Starts");
		}
		ResponseVO responseVO = null;
		FeeTypeVO feeTypeVO = null;
		try{
			feeTypeVO = (FeeTypeVO) parseObjectFromRequest(requestVO,FeeTypeVO.class);
			if(null != feeTypeVO) {
				feeTypeVO = feeTypeManager.saveFeeType(feeTypeVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeTypeVO",feeTypeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveFeeType Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getFeeTypeById", method = RequestMethod.GET)
	public ResponseVO getFeeTypeById(@RequestParam String feeTypeId){
		if(logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById Starts");
		}
		ResponseVO responseVO = null;
		FeeTypeVO feeTypeVO = null;
		try{
			feeTypeVO = feeTypeManager.getFeeTypeById(feeTypeId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeTypeVO", feeTypeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllFeeTypeBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllFeeTypeBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = feeTypeManager.getAllFeeTypeBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateFeeType", method = RequestMethod.POST)
	public ResponseVO updateFeeType(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateFeeType Starts");
		}
		ResponseVO responseVO = null;
		try {
			FeeTypeVO feeTypeVO = (FeeTypeVO) parseObjectFromRequest(requestVO, FeeTypeVO.class);
			if (null != feeTypeVO) {
				feeTypeVO = feeTypeManager.updateFeeType(feeTypeVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("FeeTypeVO", feeTypeVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateFeeType Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteFeeType", method = RequestMethod.DELETE)
	public ResponseVO deleteFeeType(@RequestParam String feeTypeId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = feeTypeManager.deleteFeeType(feeTypeId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Ends");
		}
		return responseVO;
	}
	
}
