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
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.manager.LanguageManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/language")
public class LanguageService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(LanguageService.class);
	
	private LanguageManager languageManager;
	
	
	public LanguageManager getLanguageManager() {
		return languageManager;
	}

	public void setLanguageManager(LanguageManager languageManager) {
		this.languageManager = languageManager;
	}

	@RequestMapping(value = "/saveLanguage", method = RequestMethod.POST)
	public ResponseVO saveLanguage(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveLanguage Starts");
		}
		ResponseVO responseVO = null;
		LanguageVO languageVO = null;
		try{
			languageVO = (LanguageVO) parseObjectFromRequest(requestVO,LanguageVO.class);
			if(null != languageVO) {
				languageVO = languageManager.saveLanguage(languageVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LanguageVO",languageVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveLanguage Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getLanguageById", method = RequestMethod.GET)
	public ResponseVO getLanguageById(@RequestParam String languageId){
		if(logger.isDebugEnabled()) {
			logger.debug("getLanguageById Starts");
		}
		ResponseVO responseVO = null;
		LanguageVO languageVO = null;
		try{
			languageVO = languageManager.getLanguageById(languageId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("LanguageVO", languageVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getLanguageById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllLanguageBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllLanguageBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = languageManager.getAllLanguageBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateLanguage", method = RequestMethod.POST)
	public ResponseVO updateLanguage(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateLanguage Starts");
		}
		ResponseVO responseVO = null;
		try {
			LanguageVO languageVO = (LanguageVO) parseObjectFromRequest(requestVO, LanguageVO.class);
			if (null != languageVO) {
				languageVO = languageManager.updateLanguage(languageVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("LanguageVO", languageVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateLanguage Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteLanguage", method = RequestMethod.DELETE)
	public ResponseVO deleteLanguage(@RequestParam String languageId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = languageManager.deleteLanguage(languageId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Ends");
		}
		return responseVO;
	}
	
}
