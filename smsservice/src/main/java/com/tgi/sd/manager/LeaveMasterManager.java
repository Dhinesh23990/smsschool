 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LeaveMasterManager {

	LeaveMasterVO saveLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException;

	LeaveMasterVO getLeaveMasterById(String leaveMasterId) throws SMSBusinessException;

	LeaveMasterVO updateLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException;

	boolean deleteLeaveMaster(String leaveMasterId) throws SMSBusinessException;

	long getLeaveMasterCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllLeaveMasterBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
