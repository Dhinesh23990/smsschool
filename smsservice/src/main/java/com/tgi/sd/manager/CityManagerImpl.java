package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CityDAO;
import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.StateDAO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class CityManagerImpl implements CityManager{
	
	private static Logger logger = Logger.getLogger(CityManagerImpl.class);
	
	private CityDAO cityDAO;
	
	private CountryDAO countryDAO;
	
	private StateDAO stateDAO;
	
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	
	public CountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}
	
	public StateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	@Override
	public CityVO saveCity(CityVO cityVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCity starts");
		}
		
		CityVO city = cityDAO.getCityByName(cityVO.getCityName(), null,
				cityVO.getSchoolId());
		if(city != null) {
			throw new SMSBusinessException(SMSConstants.CITY_ALREADY_EXISTS);
		}
		
		cityVO = cityDAO.saveCity(cityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCity ends");
		}
		return cityVO;
		
	}
	@Override
	public CityVO getCityById(String cityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCityById Starts");
		}
		
		CityVO cityVO = cityDAO.getCityById(cityId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCityById Ends");
		}
		
		return cityVO;
	}
	
	@Override
	public Map<String, Object> getAllCityBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCityBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = cityDAO.getCityCountBySchoolId(schoolId);
		List<CityVO> cityVOs = cityDAO.getAllCityBySchoolId(schoolId, pageIndex, pageSize);
		for(CityVO cityVO : cityVOs){
			CountryVO countryVO = countryDAO.getCountryById(cityVO.getCountryId());
			cityVO.setCountryName(countryVO.getCountryName());
			StateVO stateVO = stateDAO.getStateById(cityVO.getStateId());
			cityVO.setStateName(stateVO.getStateName());
		}
	
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("cityVOs", cityVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCityBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllCityByStateCountryName(String countryName, String stateName) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<CityVO> cityVOs = cityDAO.getAllCityByStateCountryName(countryName, stateName);
		
		if(cityVOs.isEmpty()){
			throw new SMSBusinessException(SMSConstants.CITY_LIST_EMPTY);
		}	
    	responseObjectsMap.put("cityVOs", cityVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Ends");
		}
		return responseObjectsMap;
	}
	
	
	@Override
	public long getCityCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCityCountBySchoolId Starts");
		}

		long cnt = cityDAO.getCityCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCityCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public CityVO updateCity(CityVO cityVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCity Starts");
		}
		
		CityVO city = cityDAO.getCityByName(cityVO.getCityName(), cityVO.getId(),
				cityVO.getSchoolId());
		if(city != null) {
			throw new SMSBusinessException(SMSConstants.CITY_ALREADY_EXISTS);
		}
		cityVO = cityDAO.updateCity(cityVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCity Ends");
		}
		return cityVO;
	}
	
	@Override
	public boolean deleteCity(String cityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCity Starts");
		}
		
		boolean isDeleted = cityDAO.deleteCity(cityId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCity Ends");
		}
		return isDeleted;
	}
	
}
