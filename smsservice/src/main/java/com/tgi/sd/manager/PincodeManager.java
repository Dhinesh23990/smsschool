 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.PincodeVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface PincodeManager {

	PincodeVO savePincode(PincodeVO pincodeVO) throws SMSBusinessException;

	PincodeVO getPincodeById(String pincodeId) throws SMSBusinessException;

	PincodeVO updatePincode(PincodeVO pincodeVO) throws SMSBusinessException;

	boolean deletePincode(String pincodeId) throws SMSBusinessException;

	long getPincodeCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllPincodeBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
