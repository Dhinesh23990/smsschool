package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.SubjectTypeDAO;
import com.tgi.sd.domain.config.SubjectTypeVO;
import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class SubjectTypeManagerImpl implements SubjectTypeManager{
	
	private static Logger logger = Logger.getLogger(SubjectTypeManagerImpl.class);
	
	private SubjectTypeDAO subjectTypeDAO;
	
	public SubjectTypeDAO getSubjectTypeDAO() {
		return subjectTypeDAO;
	}
	public void setSubjectTypeDAO(SubjectTypeDAO subjectTypeDAO) {
		this.subjectTypeDAO = subjectTypeDAO;
	}
	
	@Override
	public SubjectTypeVO saveSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectType starts");
		}
		
		SubjectTypeVO subjectType = subjectTypeDAO.getSubjectTypeByName(subjectTypeVO.getSubjectTypeName(),null,subjectTypeVO.getSchoolId());	
		if(subjectType!=null){
			throw new SMSBusinessException(SMSConstants.SUBJECTTYPE_ALREADY_EXISTS);
		}	
		subjectTypeVO = subjectTypeDAO.saveSubjectType(subjectTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectType ends");
		}
		return subjectTypeVO;
		
	}
	@Override
	public SubjectTypeVO getSubjectTypeById(String subjectTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById Starts");
		}
		
		SubjectTypeVO subjectTypeVO = subjectTypeDAO.getSubjectTypeById(subjectTypeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById Ends");
		}
		
		return subjectTypeVO;
	}
	
	@Override
	public Map<String, Object> getAllSubjectTypeBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = subjectTypeDAO.getSubjectTypeCountBySchoolId(schoolId);
		List<SubjectTypeVO> subjectTypeVOs = subjectTypeDAO.getAllSubjectTypeBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("subjectTypeVOs", subjectTypeVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getSubjectTypeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeCountBySchoolId Starts");
		}

		long cnt = subjectTypeDAO.getSubjectTypeCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public SubjectTypeVO updateSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Starts");
		}
		
		SubjectTypeVO subjectType = subjectTypeDAO.getSubjectTypeByName(subjectTypeVO.getSubjectTypeName(),subjectTypeVO.getId(),subjectTypeVO.getSchoolId());	
		if(subjectType!=null){
			throw new SMSBusinessException(SMSConstants.SUBJECTTYPE_ALREADY_EXISTS);
		}	
		subjectTypeVO = subjectTypeDAO.updateSubjectType(subjectTypeVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Ends");
		}
		return subjectTypeVO;
	}
	
	@Override
	public boolean deleteSubjectType(String subjectTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Starts");
		}
		
		boolean isDeleted = subjectTypeDAO.deleteSubjectType(subjectTypeId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Ends");
		}
		return isDeleted;
	}
	
}
