package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface FeeComponentDAO {

	FeeComponentVO saveFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException;

	FeeComponentVO getFeeComponentById(String feeComponentId) throws SMSBusinessException;

	List<FeeComponentVO> getAllFeeComponentBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getFeeComponentCountBySchoolId(String schoolId) throws SMSBusinessException;

	FeeComponentVO updateFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException;

	boolean deleteFeeComponent(String feeComponentId) throws SMSBusinessException;

	FeeComponentVO getFeeComponentByName(String feeComponentName, String id, String schoolId)
			throws SMSBusinessException;

}
