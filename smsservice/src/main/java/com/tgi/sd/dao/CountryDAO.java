package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface CountryDAO {

	CountryVO saveCountry(CountryVO countryVO) throws SMSBusinessException;

	CountryVO getCountryById(String countryId) throws SMSBusinessException;

	List<CountryVO> getAllCountryBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getCountryCountBySchoolId(String schoolId) throws SMSBusinessException;

	CountryVO updateCountry(CountryVO countryVO) throws SMSBusinessException;

	boolean deleteCountry(String countryId) throws SMSBusinessException;

	CountryVO getCountryByName(String countryName, String id, String schoolId)
			throws SMSBusinessException;

}
