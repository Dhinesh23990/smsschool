 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface BloodGroupManager {

	BloodGroupVO saveBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException;

	BloodGroupVO getBloodGroupById(String bloodGroupId) throws SMSBusinessException;

	BloodGroupVO updateBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException;

	boolean deleteBloodGroup(String bloodGroupId) throws SMSBusinessException;

	long getBloodGroupCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllBloodGroupBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
