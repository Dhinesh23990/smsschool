package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.SubjectSetDAO;
import com.tgi.sd.domain.config.SubjectSetVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class SubjectSetManagerImpl implements SubjectSetManager{
	
	private static Logger logger = Logger.getLogger(SubjectSetManagerImpl.class);
	
	private SubjectSetDAO subjectSetDAO;
	
	public SubjectSetDAO getSubjectSetDAO() {
		return subjectSetDAO;
	}
	public void setSubjectSetDAO(SubjectSetDAO subjectSetDAO) {
		this.subjectSetDAO = subjectSetDAO;
	}
	
	@Override
	public SubjectSetVO saveSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet starts");
		}
		
		subjectSetVO = subjectSetDAO.saveSubjectSet(subjectSetVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet ends");
		}
		return subjectSetVO;
		
	}
	@Override
	public SubjectSetVO getSubjectSetById(String subjectSetId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById Starts");
		}
		
		SubjectSetVO subjectSetVO = subjectSetDAO.getSubjectSetById(subjectSetId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById Ends");
		}
		
		return subjectSetVO;
	}
	
	@Override
	public Map<String, Object> getAllSubjectSetBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = subjectSetDAO.getSubjectSetCountBySchoolId(schoolId);
		List<SubjectSetVO> subjectSetVOs = subjectSetDAO.getAllSubjectSetBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("subjectSetVOs", subjectSetVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getSubjectSetCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetCountBySchoolId Starts");
		}

		long cnt = subjectSetDAO.getSubjectSetCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public SubjectSetVO updateSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Starts");
		}
		
		subjectSetVO = subjectSetDAO.updateSubjectSet(subjectSetVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Ends");
		}
		return subjectSetVO;
	}
	
	@Override
	public boolean deleteSubjectSet(String subjectSetId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Starts");
		}
		
		boolean isDeleted = subjectSetDAO.deleteSubjectSet(subjectSetId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Ends");
		}
		return isDeleted;
	}
	
}
