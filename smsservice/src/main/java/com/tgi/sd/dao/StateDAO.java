package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface StateDAO {

	StateVO saveState(StateVO stateVO) throws SMSBusinessException;

	StateVO getStateById(String stateId) throws SMSBusinessException;

	List<StateVO> getAllStateBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getStateCountBySchoolId(String schoolId) throws SMSBusinessException;

	StateVO updateState(StateVO stateVO) throws SMSBusinessException;

	boolean deleteState(String stateId) throws SMSBusinessException;

	StateVO getStateByName(String stateName, String id, String schoolId)
			throws SMSBusinessException;

	List<StateVO> getAllStateByCountryName(String countryName) throws SMSBusinessException;

}
