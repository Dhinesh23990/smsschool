 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface MediumManager {

	MediumVO saveMedium(MediumVO mediumVO) throws SMSBusinessException;

	MediumVO getMediumById(String mediumId) throws SMSBusinessException;

	MediumVO updateMedium(MediumVO mediumVO) throws SMSBusinessException;

	boolean deleteMedium(String mediumId) throws SMSBusinessException;

	long getMediumCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllMediumBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
