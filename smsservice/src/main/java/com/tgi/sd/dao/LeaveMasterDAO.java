package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface LeaveMasterDAO {

	LeaveMasterVO saveLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException;

	LeaveMasterVO getLeaveMasterById(String leaveMasterId) throws SMSBusinessException;

	List<LeaveMasterVO> getAllLeaveMasterBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getLeaveMasterCountBySchoolId(String schoolId) throws SMSBusinessException;

	LeaveMasterVO updateLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException;

	boolean deleteLeaveMaster(String leaveMasterId) throws SMSBusinessException;

	LeaveMasterVO getLeaveMasterByName(String leaveMasterName, String id, String schoolId)
			throws SMSBusinessException;

}
