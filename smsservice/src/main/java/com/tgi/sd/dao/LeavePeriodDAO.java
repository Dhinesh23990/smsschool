package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface LeavePeriodDAO {

	LeavePeriodVO saveLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException;

	LeavePeriodVO getLeavePeriodById(String leavePeriodId) throws SMSBusinessException;

	List<LeavePeriodVO> getAllLeavePeriodBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getLeavePeriodCountBySchoolId(String schoolId) throws SMSBusinessException;

	LeavePeriodVO updateLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException;

	boolean deleteLeavePeriod(String leavePeriodId) throws SMSBusinessException;

	LeavePeriodVO getLeavePeriodByName(String leavePeriodName, String id, String schoolId)
			throws SMSBusinessException;

	List<LeavePeriodVO> getAllLeavePeriodByMasterId(String schoolId,
			String masterId) throws SMSBusinessException;

}
