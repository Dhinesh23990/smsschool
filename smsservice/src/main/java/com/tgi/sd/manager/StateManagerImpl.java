package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CountryDAO;
import com.tgi.sd.dao.StateDAO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class StateManagerImpl implements StateManager{
	
	private static Logger logger = Logger.getLogger(StateManagerImpl.class);
	
	private StateDAO stateDAO;
	
	private CountryDAO countryDAO;
	
	public StateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public CountryDAO getCountryDAO() {
		return countryDAO;
	}
	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public StateVO saveState(StateVO stateVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveState starts");
		}
		
		StateVO state = stateDAO.getStateByName(stateVO.getStateName(),null,stateVO.getSchoolId());	
		if(state!=null){
			throw new SMSBusinessException(SMSConstants.STATE_ALREADY_EXISTS);
		}	
		stateVO = stateDAO.saveState(stateVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveState ends");
		}
		return stateVO;
		
	}
	@Override
	public StateVO getStateById(String stateId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStateById Starts");
		}
		
		StateVO stateVO = stateDAO.getStateById(stateId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStateById Ends");
		}
		
		return stateVO;
	}
	
	@Override
	public Map<String, Object> getAllStateBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStateBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = stateDAO.getStateCountBySchoolId(schoolId);
		List<StateVO> stateVOs = stateDAO.getAllStateBySchoolId(schoolId, pageIndex, pageSize);
		for(StateVO stateVO : stateVOs){
			CountryVO countryVO = countryDAO.getCountryById(stateVO.getCountryId());
			stateVO.setCountryName(countryVO.getCountryName());
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("stateVOs", stateVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllStateBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllStateByCountryName(String countryName) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<StateVO> stateVOs = stateDAO.getAllStateByCountryName(countryName);
		
		if(stateVOs.isEmpty()){
			throw new SMSBusinessException(SMSConstants.STATE_LIST_EMPTY);
		}	
    	responseObjectsMap.put("stateVOs", stateVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getStateCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStateCountBySchoolId Starts");
		}

		long cnt = stateDAO.getStateCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStateCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public StateVO updateState(StateVO stateVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateState Starts");
		}
		StateVO state = stateDAO.getStateByName(stateVO.getStateName(),stateVO.getId(),stateVO.getSchoolId());	
		if(state!=null){
			throw new SMSBusinessException(SMSConstants.STATE_ALREADY_EXISTS);
		}	
		stateVO = stateDAO.updateState(stateVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateState Ends");
		}
		return stateVO;
	}
	
	@Override
	public boolean deleteState(String stateId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteState Starts");
		}
		
		boolean isDeleted = stateDAO.deleteState(stateId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteState Ends");
		}
		return isDeleted;
	}
	
}
