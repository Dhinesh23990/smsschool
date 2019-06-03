package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface MediumDAO {

	MediumVO saveMedium(MediumVO mediumVO) throws SMSBusinessException;

	MediumVO getMediumById(String mediumId) throws SMSBusinessException;

	List<MediumVO> getAllMediumBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getMediumCountBySchoolId(String schoolId) throws SMSBusinessException;

	MediumVO updateMedium(MediumVO mediumVO) throws SMSBusinessException;

	boolean deleteMedium(String mediumId) throws SMSBusinessException;

	MediumVO getMediumByName(String mediumName, String id, String schoolId)
			throws SMSBusinessException;
	
	MediumVO getMediumByMediumName(String mediumName, String schoolId)
			throws SMSBusinessException;
	

}
