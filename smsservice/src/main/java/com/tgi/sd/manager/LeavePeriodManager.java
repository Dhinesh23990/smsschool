 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LeavePeriodManager {

	LeavePeriodVO saveLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException;

	LeavePeriodVO getLeavePeriodById(String leavePeriodId) throws SMSBusinessException;

	LeavePeriodVO updateLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException;

	boolean deleteLeavePeriod(String leavePeriodId) throws SMSBusinessException;

	long getLeavePeriodCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllLeavePeriodBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getAllLeavePeriodByMasterId(String schoolId,
			String masterId) throws SMSBusinessException;
	
	
}
