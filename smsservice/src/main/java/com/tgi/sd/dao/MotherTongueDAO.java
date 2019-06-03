package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface MotherTongueDAO {

	MotherTongueVO saveMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException;

	MotherTongueVO getMotherTongueById(String motherTongueId) throws SMSBusinessException;

	List<MotherTongueVO> getAllMotherTongueBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getMotherTongueCountBySchoolId(String schoolId) throws SMSBusinessException;

	MotherTongueVO updateMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException;

	boolean deleteMotherTongue(String motherTongueId) throws SMSBusinessException;

	MotherTongueVO getMotherTongueByName(String motherTongueName, String id,
			String schoolId) throws SMSBusinessException;

}
