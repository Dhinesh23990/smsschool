package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.GradeLimitDAO;
import com.tgi.sd.domain.config.GradeLimitVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class GradeLimitManagerImpl implements GradeLimitManager{
	
	private static Logger logger = Logger.getLogger(GradeLimitManagerImpl.class);
	
	private GradeLimitDAO gradeLimitDAO;
	
	public GradeLimitDAO getGradeLimitDAO() {
		return gradeLimitDAO;
	}
	public void setGradeLimitDAO(GradeLimitDAO gradeLimitDAO) {
		this.gradeLimitDAO = gradeLimitDAO;
	}
	
	@Override
	public GradeLimitVO saveGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit starts");
		}
		GradeLimitVO gradeLimit = gradeLimitDAO.getGradeLimitByName(gradeLimitVO.getGradeName(),null,gradeLimitVO.getSchoolId());	
		if(gradeLimit!=null){
			throw new SMSBusinessException(SMSConstants.GRADENAME_ALREADY_EXIST);
		}
		gradeLimitVO = gradeLimitDAO.saveGradeLimit(gradeLimitVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit ends");
		}
		return gradeLimitVO;
		
	}
	@Override
	public GradeLimitVO getGradeLimitById(String gradeLimitId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById Starts");
		}
		
		GradeLimitVO gradeLimitVO = gradeLimitDAO.getGradeLimitById(gradeLimitId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById Ends");
		}
		
		return gradeLimitVO;
	}
	
	@Override
	public Map<String, Object> getAllGradeLimitBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = gradeLimitDAO.getGradeLimitCountBySchoolId(schoolId);
		List<GradeLimitVO> gradeLimitVOs = gradeLimitDAO.getAllGradeLimitBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("gradeLimitVOs", gradeLimitVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getGradeLimitCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitCountBySchoolId Starts");
		}

		long cnt = gradeLimitDAO.getGradeLimitCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public GradeLimitVO updateGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Starts");
		}
		GradeLimitVO gradeLimit = gradeLimitDAO.getGradeLimitByName(gradeLimitVO.getGradeName(),gradeLimitVO.getId(),gradeLimitVO.getSchoolId());	
		if(gradeLimit!=null){
			throw new SMSBusinessException(SMSConstants.GRADENAME_ALREADY_EXIST);
		}
		gradeLimitVO = gradeLimitDAO.updateGradeLimit(gradeLimitVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Ends");
		}
		return gradeLimitVO;
	}
	
	@Override
	public boolean deleteGradeLimit(String gradeLimitId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Starts");
		}
		
		boolean isDeleted = gradeLimitDAO.deleteGradeLimit(gradeLimitId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Ends");
		}
		return isDeleted;
	}
	
}
