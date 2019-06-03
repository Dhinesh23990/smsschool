package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface ReligionDAO {

	ReligionVO saveReligion(ReligionVO religionVO) throws SMSBusinessException;

	ReligionVO getReligionById(String religionId) throws SMSBusinessException;

	List<ReligionVO> getAllReligionBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getReligionCountBySchoolId(String schoolId) throws SMSBusinessException;

	ReligionVO updateReligion(ReligionVO religionVO) throws SMSBusinessException;

	boolean deleteReligion(String religionId) throws SMSBusinessException;

	ReligionVO getReligionByName(String religionName,
			String id, String schoolId) throws SMSBusinessException;

}
