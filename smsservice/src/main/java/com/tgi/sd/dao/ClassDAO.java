package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface ClassDAO {

	ClassVO saveClass(ClassVO classVO) throws SMSBusinessException;

	ClassVO getClassById(String classId) throws SMSBusinessException;

	List<ClassVO> getAllClassBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getClassCountBySchoolId(String schoolId) throws SMSBusinessException;

	ClassVO updateClass(ClassVO classVO) throws SMSBusinessException;

	boolean deleteClass(String classId) throws SMSBusinessException;

	ClassVO getClassByName(String className, String id, String schoolId)
			throws SMSBusinessException;

}
