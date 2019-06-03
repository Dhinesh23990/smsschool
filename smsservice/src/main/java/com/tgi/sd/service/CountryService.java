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
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.manager.CountryManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/country")
public class CountryService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CountryService.class);
	
	private CountryManager countryManager;
	
	
	public CountryManager getCountryManager() {
		return countryManager;
	}

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}

	@RequestMapping(value = "/saveCountry", method = RequestMethod.POST)
	public ResponseVO saveCountry(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveCountry Starts");
		}
		ResponseVO responseVO = null;
		CountryVO countryVO = null;
		try{
			countryVO = (CountryVO) parseObjectFromRequest(requestVO,CountryVO.class);
			if(null != countryVO) {
				countryVO = countryManager.saveCountry(countryVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CountryVO",countryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveCountry Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getCountryById", method = RequestMethod.GET)
	public ResponseVO getCountryById(@RequestParam String countryId){
		if(logger.isDebugEnabled()) {
			logger.debug("getCountryById Starts");
		}
		ResponseVO responseVO = null;
		CountryVO countryVO = null;
		try{
			countryVO = countryManager.getCountryById(countryId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CountryVO", countryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getCountryById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllCountryBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllCountryBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCountryBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = countryManager.getAllCountryBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCountryBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateCountry", method = RequestMethod.POST)
	public ResponseVO updateCountry(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateCountry Starts");
		}
		ResponseVO responseVO = null;
		try {
			CountryVO countryVO = (CountryVO) parseObjectFromRequest(requestVO, CountryVO.class);
			if (null != countryVO) {
				countryVO = countryManager.updateCountry(countryVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("CountryVO", countryVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateCountry Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteCountry", method = RequestMethod.DELETE)
	public ResponseVO deleteCountry(@RequestParam String countryId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCountry Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = countryManager.deleteCountry(countryId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCountry Ends");
		}
		return responseVO;
	}
	
}
