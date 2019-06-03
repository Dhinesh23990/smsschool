package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class CountryManagerImpl implements CountryManager{
	
	private static Logger logger = Logger.getLogger(CountryManagerImpl.class);
	
	private CountryDAO countryDAO;
	
	public CountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	@Override
	public CountryVO saveCountry(CountryVO countryVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCountry starts");
		}
		
		CountryVO country = countryDAO.getCountryByName(countryVO.getCountryName(),null,countryVO.getSchoolId());	
		if(country!=null){
			throw new SMSBusinessException(SMSConstants.COUNTRY_ALREADY_EXISTS);
		}	
		countryVO = countryDAO.saveCountry(countryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCountry ends");
		}
		return countryVO;
		
	}
	@Override
	public CountryVO getCountryById(String countryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryById Starts");
		}
		
		CountryVO countryVO = countryDAO.getCountryById(countryId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryById Ends");
		}
		
		return countryVO;
	}
	
	@Override
	public Map<String, Object> getAllCountryBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCountryBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = countryDAO.getCountryCountBySchoolId(schoolId);
		List<CountryVO> countryVOs = countryDAO.getAllCountryBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("countryVOs", countryVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCountryBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getCountryCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryCountBySchoolId Starts");
		}

		long cnt = countryDAO.getCountryCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public CountryVO updateCountry(CountryVO countryVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCountry Starts");
		}
		
		CountryVO country = countryDAO.getCountryByName(countryVO.getCountryName(),countryVO.getId(),countryVO.getSchoolId());	
		if(country!=null){
			throw new SMSBusinessException(SMSConstants.COUNTRY_ALREADY_EXISTS);
		}	
		countryVO = countryDAO.updateCountry(countryVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCountry Ends");
		}
		return countryVO;
	}
	
	@Override
	public boolean deleteCountry(String countryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCountry Starts");
		}
		
		boolean isDeleted = countryDAO.deleteCountry(countryId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCountry Ends");
		}
		return isDeleted;
	}
	
}
