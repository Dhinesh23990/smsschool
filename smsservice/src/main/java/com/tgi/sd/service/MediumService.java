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
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.manager.MediumManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/medium")
public class MediumService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(MediumService.class);
	
	private MediumManager mediumManager;
	
	
	public MediumManager getMediumManager() {
		return mediumManager;
	}

	public void setMediumManager(MediumManager mediumManager) {
		this.mediumManager = mediumManager;
	}

	@RequestMapping(value = "/saveMedium", method = RequestMethod.POST)
	public ResponseVO saveMedium(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveMedium Starts");
		}
		ResponseVO responseVO = null;
		MediumVO mediumVO = null;
		try{
			mediumVO = (MediumVO) parseObjectFromRequest(requestVO,MediumVO.class);
			if(null != mediumVO) {
				mediumVO = mediumManager.saveMedium(mediumVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("MediumVO",mediumVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveMedium Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getMediumById", method = RequestMethod.GET)
	public ResponseVO getMediumById(@RequestParam String mediumId){
		if(logger.isDebugEnabled()) {
			logger.debug("getMediumById Starts");
		}
		ResponseVO responseVO = null;
		MediumVO mediumVO = null;
		try{
			mediumVO = mediumManager.getMediumById(mediumId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("MediumVO", mediumVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getMediumById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllMediumBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllMediumBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = mediumManager.getAllMediumBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateMedium", method = RequestMethod.POST)
	public ResponseVO updateMedium(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateMedium Starts");
		}
		ResponseVO responseVO = null;
		try {
			MediumVO mediumVO = (MediumVO) parseObjectFromRequest(requestVO, MediumVO.class);
			if (null != mediumVO) {
				mediumVO = mediumManager.updateMedium(mediumVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("MediumVO", mediumVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateMedium Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteMedium", method = RequestMethod.DELETE)
	public ResponseVO deleteMedium(@RequestParam String mediumId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteMedium Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = mediumManager.deleteMedium(mediumId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteMedium Ends");
		}
		return responseVO;
	}
	
}
