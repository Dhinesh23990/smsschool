package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.ExamVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface ExamDAO {

	ExamVO saveExam(ExamVO examVO) throws SMSBusinessException;

	ExamVO getExamById(String examId) throws SMSBusinessException;

	List<ExamVO> getAllExamBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getExamCountBySchoolId(String schoolId) throws SMSBusinessException;

	ExamVO updateExam(ExamVO examVO) throws SMSBusinessException;

	boolean deleteExam(String examId) throws SMSBusinessException;

	ExamVO getExamByName(String examName, String id, String schoolId)
			throws SMSBusinessException;

}
