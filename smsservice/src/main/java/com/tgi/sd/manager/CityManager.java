 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface CityManager {

	CityVO saveCity(CityVO cityVO) throws SMSBusinessException;

	CityVO getCityById(String cityId) throws SMSBusinessException;

	CityVO updateCity(CityVO cityVO) throws SMSBusinessException;

	boolean deleteCity(String cityId) throws SMSBusinessException;

	long getCityCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllCityBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getAllCityByStateCountryName(String countryName,
			String stateName) throws SMSBusinessException;
	
	
}
