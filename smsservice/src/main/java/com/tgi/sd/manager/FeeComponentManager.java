 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface FeeComponentManager {

	FeeComponentVO saveFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException;

	FeeComponentVO getFeeComponentById(String feeComponentId) throws SMSBusinessException;

	FeeComponentVO updateFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException;

	boolean deleteFeeComponent(String feeComponentId) throws SMSBusinessException;

	long getFeeComponentCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllFeeComponentBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
