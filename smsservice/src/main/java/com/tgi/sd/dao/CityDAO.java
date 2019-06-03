package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface CityDAO {

	CityVO saveCity(CityVO cityVO) throws SMSBusinessException;

	CityVO getCityById(String cityId) throws SMSBusinessException;

	List<CityVO> getAllCityBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getCityCountBySchoolId(String schoolId) throws SMSBusinessException;

	CityVO updateCity(CityVO cityVO) throws SMSBusinessException;

	boolean deleteCity(String cityId) throws SMSBusinessException;

	CityVO getCityByName(String cityName, String id, String schoolId)
			throws SMSBusinessException;

	List<CityVO> getAllCityByStateCountryName(String countryName,
			String stateName) throws SMSBusinessException;

}
