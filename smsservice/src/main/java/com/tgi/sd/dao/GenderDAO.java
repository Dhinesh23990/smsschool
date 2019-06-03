package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface GenderDAO {

	GenderVO saveGender(GenderVO genderVO) throws SMSBusinessException;

	GenderVO getGenderById(String genderId) throws SMSBusinessException;

	List<GenderVO> getAllGenderBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getGenderCountBySchoolId(String schoolId) throws SMSBusinessException;

	GenderVO updateGender(GenderVO genderVO) throws SMSBusinessException;

	boolean deleteGender(String genderId) throws SMSBusinessException;

	GenderVO getGenderByName(String gender,
			String id, String schoolId) throws SMSBusinessException;

}
