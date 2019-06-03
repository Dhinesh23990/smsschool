package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.GradeLimitVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface GradeLimitDAO {

	GradeLimitVO saveGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException;

	GradeLimitVO getGradeLimitById(String gradeLimitId) throws SMSBusinessException;

	List<GradeLimitVO> getAllGradeLimitBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getGradeLimitCountBySchoolId(String schoolId) throws SMSBusinessException;

	GradeLimitVO updateGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException;

	boolean deleteGradeLimit(String gradeLimitId) throws SMSBusinessException;

	GradeLimitVO getGradeLimitByName(String gradeLimitName, String id, String schoolId)
			throws SMSBusinessException;

}
