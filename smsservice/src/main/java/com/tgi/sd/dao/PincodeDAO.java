package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.PincodeVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface PincodeDAO {

	PincodeVO savePincode(PincodeVO pincodeVO) throws SMSBusinessException;

	PincodeVO getPincodeById(String pincodeId) throws SMSBusinessException;

	List<PincodeVO> getAllPincodeBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getPincodeCountBySchoolId(String schoolId) throws SMSBusinessException;

	PincodeVO updatePincode(PincodeVO pincodeVO) throws SMSBusinessException;

	boolean deletePincode(String pincodeId) throws SMSBusinessException;

}
