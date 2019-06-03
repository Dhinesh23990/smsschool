 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface StateManager {

	StateVO saveState(StateVO stateVO) throws SMSBusinessException;

	StateVO getStateById(String stateId) throws SMSBusinessException;

	StateVO updateState(StateVO stateVO) throws SMSBusinessException;

	boolean deleteState(String stateId) throws SMSBusinessException;

	long getStateCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllStateBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getAllStateByCountryName(String countryName) throws SMSBusinessException;
	
	
}
