package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class ClassManagerImpl implements ClassManager{
	
	private static Logger logger = Logger.getLogger(ClassManagerImpl.class);
	
	private ClassDAO classDAO;
	
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	@Override
	public ClassVO saveClass(ClassVO classVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveClass starts");
		}
		
		ClassVO checkclassvo = classDAO.getClassByName(classVO.getClassName(),null,classVO.getSchoolId());	
		if(checkclassvo!=null){
			throw new SMSBusinessException(SMSConstants.CLASS_ALREADY_EXISTS);
		}	
		classVO = classDAO.saveClass(classVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveClass ends");
		}
		return classVO;
		
	}
	@Override
	public ClassVO getClassById(String classId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getClassById Starts");
		}
		
		ClassVO classVO = classDAO.getClassById(classId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getClassById Ends");
		}
		
		return classVO;
	}
	
	@Override
	public Map<String, Object> getAllClassBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = classDAO.getClassCountBySchoolId(schoolId);
		List<ClassVO> classVOs = classDAO.getAllClassBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("classVOs", classVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getClassCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getClassCountBySchoolId Starts");
		}

		long cnt = classDAO.getClassCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getClassCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public ClassVO updateClass(ClassVO classVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateClass Starts");
		}
		ClassVO checkclassvo = classDAO.getClassByName(classVO.getClassName(),classVO.getId(),classVO.getSchoolId());	
		if(checkclassvo!=null){
			throw new SMSBusinessException(SMSConstants.CLASS_ALREADY_EXISTS);
		}		
		
		classVO = classDAO.updateClass(classVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateClass Ends");
		}
		return classVO;
	}
	
	@Override
	public boolean deleteClass(String classId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteClass Starts");
		}
		
		boolean isDeleted = classDAO.deleteClass(classId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteClass Ends");
		}
		return isDeleted;
	}
	
}
