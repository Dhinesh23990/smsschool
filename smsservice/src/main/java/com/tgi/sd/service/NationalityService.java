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
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.manager.NationalityManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/nationality")
public class NationalityService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(NationalityService.class);
	
	private NationalityManager nationalityManager;
	
	
	public NationalityManager getNationalityManager() {
		return nationalityManager;
	}

	public void setNationalityManager(NationalityManager nationalityManager) {
		this.nationalityManager = nationalityManager;
	}

	@RequestMapping(value = "/saveNationality", method = RequestMethod.POST)
	public ResponseVO saveNationality(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveNationality Starts");
		}
		ResponseVO responseVO = null;
		NationalityVO nationalityVO = null;
		try{
			nationalityVO = (NationalityVO) parseObjectFromRequest(requestVO,NationalityVO.class);
			if(null != nationalityVO) {
				nationalityVO = nationalityManager.saveNationality(nationalityVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("NationalityVO",nationalityVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveNationality Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getNationalityById", method = RequestMethod.GET)
	public ResponseVO getNationalityById(@RequestParam String nationalityId){
		if(logger.isDebugEnabled()) {
			logger.debug("getNationalityById Starts");
		}
		ResponseVO responseVO = null;
		NationalityVO nationalityVO = null;
		try{
			nationalityVO = nationalityManager.getNationalityById(nationalityId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("NationalityVO", nationalityVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getNationalityById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllNationalityBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllNationalityBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = nationalityManager.getAllNationalityBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateNationality", method = RequestMethod.POST)
	public ResponseVO updateNationality(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateNationality Starts");
		}
		ResponseVO responseVO = null;
		try {
			NationalityVO nationalityVO = (NationalityVO) parseObjectFromRequest(requestVO, NationalityVO.class);
			if (null != nationalityVO) {
				nationalityVO = nationalityManager.updateNationality(nationalityVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("NationalityVO", nationalityVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateNationality Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteNationality", method = RequestMethod.DELETE)
	public ResponseVO deleteNationality(@RequestParam String nationalityId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteNationality Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = nationalityManager.deleteNationality(nationalityId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteNationality Ends");
		}
		return responseVO;
	}
	
}
