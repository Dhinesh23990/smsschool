 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.ExamVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface ExamManager {

	ExamVO saveExam(ExamVO examVO) throws SMSBusinessException;

	ExamVO getExamById(String examId) throws SMSBusinessException;

	ExamVO updateExam(ExamVO examVO) throws SMSBusinessException;

	boolean deleteExam(String examId) throws SMSBusinessException;

	long getExamCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllExamBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
