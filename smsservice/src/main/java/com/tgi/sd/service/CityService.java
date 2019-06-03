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
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.manager.CityManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/city")
public class CityService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CityService.class);
	
	private CityManager cityManager;
	
	
	public CityManager getCityManager() {
		return cityManager;
	}

	public void setCityManager(CityManager cityManager) {
		this.cityManager = cityManager;
	}

	@RequestMapping(value = "/saveCity", method = RequestMethod.POST)
	public ResponseVO saveCity(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveCity Starts");
		}
		ResponseVO responseVO = null;
		CityVO cityVO = null;
		try{
			cityVO = (CityVO) parseObjectFromRequest(requestVO,CityVO.class);
			if(null != cityVO) {
				cityVO = cityManager.saveCity(cityVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CityVO",cityVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveCity Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getCityById", method = RequestMethod.GET)
	public ResponseVO getCityById(@RequestParam String cityId){
		if(logger.isDebugEnabled()) {
			logger.debug("getCityById Starts");
		}
		ResponseVO responseVO = null;
		CityVO cityVO = null;
		try{
			cityVO = cityManager.getCityById(cityId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CityVO", cityVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getCityById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllCityBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllCityBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCityBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = cityManager.getAllCityBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCityBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value="/getAllCityByStateCountryName",method=RequestMethod.GET)
	public ResponseVO getAllStateByCountryName(@RequestParam String countryName,String stateName)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = cityManager.getAllCityByStateCountryName(countryName,stateName);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateCity", method = RequestMethod.POST)
	public ResponseVO updateCity(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateCity Starts");
		}
		ResponseVO responseVO = null;
		try {
			CityVO cityVO = (CityVO) parseObjectFromRequest(requestVO, CityVO.class);
			if (null != cityVO) {
				cityVO = cityManager.updateCity(cityVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("CityVO", cityVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateCity Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteCity", method = RequestMethod.DELETE)
	public ResponseVO deleteCity(@RequestParam String cityId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCity Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = cityManager.deleteCity(cityId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCity Ends");
		}
		return responseVO;
	}
	
}
