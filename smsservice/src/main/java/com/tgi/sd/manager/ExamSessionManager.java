 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.ExamSessionVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface ExamSessionManager {

	ExamSessionVO saveExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException;

	ExamSessionVO getExamSessionById(String examSessionId) throws SMSBusinessException;

	ExamSessionVO updateExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException;

	boolean deleteExamSession(String examSessionId) throws SMSBusinessException;

	long getExamSessionCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllExamSessionBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
