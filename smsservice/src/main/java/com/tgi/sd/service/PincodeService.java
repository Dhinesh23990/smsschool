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
import com.tgi.sd.domain.config.PincodeVO;
import com.tgi.sd.manager.PincodeManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/pincode")
public class PincodeService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(PincodeService.class);
	
	private PincodeManager pincodeManager;
	
	
	public PincodeManager getPincodeManager() {
		return pincodeManager;
	}

	public void setPincodeManager(PincodeManager pincodeManager) {
		this.pincodeManager = pincodeManager;
	}

	@RequestMapping(value = "/savePincode", method = RequestMethod.POST)
	public ResponseVO savePincode(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("savePincode Starts");
		}
		ResponseVO responseVO = null;
		PincodeVO pincodeVO = null;
		try{
			pincodeVO = (PincodeVO) parseObjectFromRequest(requestVO,PincodeVO.class);
			if(null != pincodeVO) {
				pincodeVO = pincodeManager.savePincode(pincodeVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PincodeVO",pincodeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("savePincode Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getPincodeById", method = RequestMethod.GET)
	public ResponseVO getPincodeById(@RequestParam String pincodeId){
		if(logger.isDebugEnabled()) {
			logger.debug("getPincodeById Starts");
		}
		ResponseVO responseVO = null;
		PincodeVO pincodeVO = null;
		try{
			pincodeVO = pincodeManager.getPincodeById(pincodeId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PincodeVO", pincodeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getPincodeById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllPincodeBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllPincodeBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = pincodeManager.getAllPincodeBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updatePincode", method = RequestMethod.POST)
	public ResponseVO updatePincode(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updatePincode Starts");
		}
		ResponseVO responseVO = null;
		try {
			PincodeVO pincodeVO = (PincodeVO) parseObjectFromRequest(requestVO, PincodeVO.class);
			if (null != pincodeVO) {
				pincodeVO = pincodeManager.updatePincode(pincodeVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("PincodeVO", pincodeVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updatePincode Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deletePincode", method = RequestMethod.DELETE)
	public ResponseVO deletePincode(@RequestParam String pincodeId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deletePincode Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = pincodeManager.deletePincode(pincodeId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deletePincode Ends");
		}
		return responseVO;
	}
	
}
