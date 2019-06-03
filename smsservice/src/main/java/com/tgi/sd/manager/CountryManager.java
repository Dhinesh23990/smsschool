 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface CountryManager {

	CountryVO saveCountry(CountryVO countryVO) throws SMSBusinessException;

	CountryVO getCountryById(String countryId) throws SMSBusinessException;

	CountryVO updateCountry(CountryVO countryVO) throws SMSBusinessException;

	boolean deleteCountry(String countryId) throws SMSBusinessException;

	long getCountryCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllCountryBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
