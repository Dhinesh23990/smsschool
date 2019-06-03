 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface ReligionManager {

	ReligionVO saveReligion(ReligionVO religionVO) throws SMSBusinessException;

	ReligionVO getReligionById(String religionId) throws SMSBusinessException;

	ReligionVO updateReligion(ReligionVO religionVO) throws SMSBusinessException;

	boolean deleteReligion(String religionId) throws SMSBusinessException;

	long getReligionCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllReligionBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
