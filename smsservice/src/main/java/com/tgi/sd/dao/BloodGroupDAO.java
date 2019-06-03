package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface BloodGroupDAO {

	BloodGroupVO saveBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException;

	BloodGroupVO getBloodGroupById(String bloodGroupId) throws SMSBusinessException;

	List<BloodGroupVO> getAllBloodGroupBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getBloodGroupCountBySchoolId(String schoolId) throws SMSBusinessException;

	BloodGroupVO updateBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException;

	boolean deleteBloodGroup(String bloodGroupId) throws SMSBusinessException;

	BloodGroupVO getBloodGroupByName(String bloodGroupName, String id, String schoolId)
			throws SMSBusinessException;

}
