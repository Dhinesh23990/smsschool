package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.ExamSessionVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface ExamSessionDAO {

	ExamSessionVO saveExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException;

	ExamSessionVO getExamSessionById(String examSessionId) throws SMSBusinessException;

	List<ExamSessionVO> getAllExamSessionBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getExamSessionCountBySchoolId(String schoolId) throws SMSBusinessException;

	ExamSessionVO updateExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException;

	boolean deleteExamSession(String examSessionId) throws SMSBusinessException;

	ExamSessionVO getExamSessionByName(String examSessionName, String id, String schoolId)
			throws SMSBusinessException;

}
