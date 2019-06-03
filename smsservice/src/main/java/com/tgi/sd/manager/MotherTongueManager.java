 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface MotherTongueManager {

	MotherTongueVO saveMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException;

	MotherTongueVO getMotherTongueById(String motherTongueId) throws SMSBusinessException;

	MotherTongueVO updateMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException;

	boolean deleteMotherTongue(String motherTongueId) throws SMSBusinessException;

	long getMotherTongueCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllMotherTongueBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
