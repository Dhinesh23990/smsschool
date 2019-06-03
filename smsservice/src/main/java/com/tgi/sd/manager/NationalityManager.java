 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface NationalityManager {

	NationalityVO saveNationality(NationalityVO nationalityVO) throws SMSBusinessException;

	NationalityVO getNationalityById(String nationalityId) throws SMSBusinessException;

	NationalityVO updateNationality(NationalityVO nationalityVO) throws SMSBusinessException;

	boolean deleteNationality(String nationalityId) throws SMSBusinessException;

	long getNationalityCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllNationalityBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
