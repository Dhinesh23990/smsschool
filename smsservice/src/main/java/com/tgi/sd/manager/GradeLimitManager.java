 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.GradeLimitVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface GradeLimitManager {

	GradeLimitVO saveGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException;

	GradeLimitVO getGradeLimitById(String gradeLimitId) throws SMSBusinessException;

	GradeLimitVO updateGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException;

	boolean deleteGradeLimit(String gradeLimitId) throws SMSBusinessException;

	long getGradeLimitCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllGradeLimitBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
