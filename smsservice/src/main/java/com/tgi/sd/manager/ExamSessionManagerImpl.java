package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ExamSessionDAO;
import com.tgi.sd.domain.config.ExamSessionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class ExamSessionManagerImpl implements ExamSessionManager{
	
	private static Logger logger = Logger.getLogger(ExamSessionManagerImpl.class);
	
	private ExamSessionDAO examSessionDAO;
	
	public ExamSessionDAO getExamSessionDAO() {
		return examSessionDAO;
	}
	public void setExamSessionDAO(ExamSessionDAO examSessionDAO) {
		this.examSessionDAO = examSessionDAO;
	}
	
	@Override
	public ExamSessionVO saveExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveExamSession starts");
		}
		ExamSessionVO examSession = examSessionDAO.getExamSessionByName(examSessionVO.getExamSessionName(), null,
				examSessionVO.getSchoolId());
		if(examSession != null) {
			throw new SMSBusinessException(SMSConstants.EXAM_SESSION_ALREADY_EXISTS);
		}
		examSessionVO = examSessionDAO.saveExamSession(examSessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveExamSession ends");
		}
		return examSessionVO;
		
	}
	@Override
	public ExamSessionVO getExamSessionById(String examSessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionById Starts");
		}
		
		ExamSessionVO examSessionVO = examSessionDAO.getExamSessionById(examSessionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionById Ends");
		}
		
		return examSessionVO;
	}
	
	@Override
	public Map<String, Object> getAllExamSessionBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = examSessionDAO.getExamSessionCountBySchoolId(schoolId);
		List<ExamSessionVO> examSessionVOs = examSessionDAO.getAllExamSessionBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("examSessionVOs", examSessionVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getExamSessionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionCountBySchoolId Starts");
		}

		long cnt = examSessionDAO.getExamSessionCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public ExamSessionVO updateExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateExamSession Starts");
		}
		
		ExamSessionVO examSession = examSessionDAO.getExamSessionByName(examSessionVO.getExamSessionName(), examSessionVO.getId(),
				examSessionVO.getSchoolId());
		if(examSession != null) {
			throw new SMSBusinessException(SMSConstants.EXAM_SESSION_ALREADY_EXISTS);
		}
		examSessionVO = examSessionDAO.updateExamSession(examSessionVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateExamSession Ends");
		}
		return examSessionVO;
	}
	
	@Override
	public boolean deleteExamSession(String examSessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Starts");
		}
		
		boolean isDeleted = examSessionDAO.deleteExamSession(examSessionId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Ends");
		}
		return isDeleted;
	}
	
}
