package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ExamDAO;
import com.tgi.sd.domain.config.ExamVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class ExamManagerImpl implements ExamManager{
	
	private static Logger logger = Logger.getLogger(ExamManagerImpl.class);
	
	private ExamDAO examDAO;
	
	public ExamDAO getExamDAO() {
		return examDAO;
	}
	public void setExamDAO(ExamDAO examDAO) {
		this.examDAO = examDAO;
	}
	
	@Override
	public ExamVO saveExam(ExamVO examVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveExam starts");
		}
		ExamVO exam = examDAO.getExamByName(examVO.getExamName(), null,
				examVO.getSchoolId());
		if(exam != null) {
			throw new SMSBusinessException(SMSConstants.EXAM_ALREADY_EXISTS);
		}
		examVO = examDAO.saveExam(examVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveExam ends");
		}
		return examVO;
		
	}
	@Override
	public ExamVO getExamById(String examId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamById Starts");
		}
		
		ExamVO examVO = examDAO.getExamById(examId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamById Ends");
		}
		
		return examVO;
	}
	
	@Override
	public Map<String, Object> getAllExamBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = examDAO.getExamCountBySchoolId(schoolId);
		List<ExamVO> examVOs = examDAO.getAllExamBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("examVOs", examVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getExamCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamCountBySchoolId Starts");
		}

		long cnt = examDAO.getExamCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public ExamVO updateExam(ExamVO examVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateExam Starts");
		}
		
		ExamVO exam = examDAO.getExamByName(examVO.getExamName(), examVO.getId(),
				examVO.getSchoolId());
		if(exam != null) {
			throw new SMSBusinessException(SMSConstants.EXAM_ALREADY_EXISTS);
		}
		examVO = examDAO.updateExam(examVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateExam Ends");
		}
		return examVO;
	}
	
	@Override
	public boolean deleteExam(String examId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExam Starts");
		}
		
		boolean isDeleted = examDAO.deleteExam(examId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExam Ends");
		}
		return isDeleted;
	}
	
}
