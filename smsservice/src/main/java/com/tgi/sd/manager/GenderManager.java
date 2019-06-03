 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface GenderManager {

	GenderVO saveGender(GenderVO genderVO) throws SMSBusinessException;

	GenderVO getGenderById(String genderId) throws SMSBusinessException;

	GenderVO updateGender(GenderVO genderVO) throws SMSBusinessException;

	boolean deleteGender(String genderId) throws SMSBusinessException;

	Map<String, Object> getAllGenderBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
