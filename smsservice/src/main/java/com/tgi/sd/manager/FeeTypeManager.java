 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface FeeTypeManager {

	FeeTypeVO saveFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException;

	FeeTypeVO getFeeTypeById(String feeTypeId) throws SMSBusinessException;

	FeeTypeVO updateFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException;

	boolean deleteFeeType(String feeTypeId) throws SMSBusinessException;

	long getFeeTypeCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllFeeTypeBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
