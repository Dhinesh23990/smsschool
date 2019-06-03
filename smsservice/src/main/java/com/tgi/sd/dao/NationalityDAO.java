package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface NationalityDAO {

	NationalityVO saveNationality(NationalityVO nationalityVO) throws SMSBusinessException;

	NationalityVO getNationalityById(String nationalityId) throws SMSBusinessException;

	List<NationalityVO> getAllNationalityBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getNationalityCountBySchoolId(String schoolId) throws SMSBusinessException;

	NationalityVO updateNationality(NationalityVO nationalityVO) throws SMSBusinessException;

	boolean deleteNationality(String nationalityId) throws SMSBusinessException;

	NationalityVO getNationalityByName(String nationalityName, String id,
			String schoolId) throws SMSBusinessException;

}
