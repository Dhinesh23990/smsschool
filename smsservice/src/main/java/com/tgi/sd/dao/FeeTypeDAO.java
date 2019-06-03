package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface FeeTypeDAO {

	FeeTypeVO saveFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException;

	FeeTypeVO getFeeTypeById(String feeTypeId) throws SMSBusinessException;

	List<FeeTypeVO> getAllFeeTypeBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getFeeTypeCountBySchoolId(String schoolId) throws SMSBusinessException;

	FeeTypeVO updateFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException;

	boolean deleteFeeType(String feeTypeId) throws SMSBusinessException;

	FeeTypeVO getFeeTypeByName(String feeTypeName, String id, String schoolId)
			throws SMSBusinessException;

}
