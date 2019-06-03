package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.SubjectDAO;
import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class SubjectManagerImpl implements SubjectManager{
	
	private static Logger logger = Logger.getLogger(SubjectManagerImpl.class);
	
	private SubjectDAO subjectDAO;
	
	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}
	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}
	
	@Override
	public SubjectVO saveSubject(SubjectVO subjectVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubject starts");
		}
		
		SubjectVO subject = subjectDAO.getSubjectByName(subjectVO.getSubjectName(),null,subjectVO.getSchoolId());	
		if(subject!=null){
			throw new SMSBusinessException(SMSConstants.SUBJECT_ALREADY_EXISTS);
		}	
		subjectVO = subjectDAO.saveSubject(subjectVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubject ends");
		}
		return subjectVO;
		
	}
	@Override
	public SubjectVO getSubjectById(String subjectId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectById Starts");
		}
		
		SubjectVO subjectVO = subjectDAO.getSubjectById(subjectId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectById Ends");
		}
		
		return subjectVO;
	}
	
	@Override
	public Map<String, Object> getAllSubjectBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = subjectDAO.getSubjectCountBySchoolId(schoolId);
		List<SubjectVO> subjectVOs = subjectDAO.getAllSubjectBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("subjectVOs", subjectVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getSubjectCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectCountBySchoolId Starts");
		}

		long cnt = subjectDAO.getSubjectCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public SubjectVO updateSubject(SubjectVO subjectVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubject Starts");
		}
		
		SubjectVO subject = subjectDAO.getSubjectByName(subjectVO.getSubjectName(),subjectVO.getId(),subjectVO.getSchoolId());	
		if(subject!=null){
			throw new SMSBusinessException(SMSConstants.SUBJECT_ALREADY_EXISTS);
		}	
		subjectVO = subjectDAO.updateSubject(subjectVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubject Ends");
		}
		return subjectVO;
	}
	
	@Override
	public boolean deleteSubject(String subjectId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubject Starts");
		}
		
		boolean isDeleted = subjectDAO.deleteSubject(subjectId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubject Ends");
		}
		return isDeleted;
	}
	
}
