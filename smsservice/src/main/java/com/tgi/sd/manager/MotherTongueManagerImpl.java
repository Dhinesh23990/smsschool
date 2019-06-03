package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.MotherTongueDAO;
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class MotherTongueManagerImpl implements MotherTongueManager{
	
	private static Logger logger = Logger.getLogger(MotherTongueManagerImpl.class);
	
	private MotherTongueDAO motherTongueDAO;
	
	public MotherTongueDAO getMotherTongueDAO() {
		return motherTongueDAO;
	}
	public void setMotherTongueDAO(MotherTongueDAO motherTongueDAO) {
		this.motherTongueDAO = motherTongueDAO;
	}
	
	@Override
	public MotherTongueVO saveMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue starts");
		}
		
		MotherTongueVO mothertongue = motherTongueDAO.getMotherTongueByName(motherTongueVO.getMotherTongueName(),null,motherTongueVO.getSchoolId());	
		if(mothertongue!=null){
			throw new SMSBusinessException(SMSConstants.MOTHERTONGUE_ALREADY_EXISTS);
		}	
		motherTongueVO = motherTongueDAO.saveMotherTongue(motherTongueVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue ends");
		}
		return motherTongueVO;
		
	}
	@Override
	public MotherTongueVO getMotherTongueById(String motherTongueId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById Starts");
		}
		
		MotherTongueVO motherTongueVO = motherTongueDAO.getMotherTongueById(motherTongueId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById Ends");
		}
		
		return motherTongueVO;
	}
	
	@Override
	public Map<String, Object> getAllMotherTongueBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = motherTongueDAO.getMotherTongueCountBySchoolId(schoolId);
		List<MotherTongueVO> motherTongueVOs = motherTongueDAO.getAllMotherTongueBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("motherTongueVOs", motherTongueVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getMotherTongueCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueCountBySchoolId Starts");
		}

		long cnt = motherTongueDAO.getMotherTongueCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public MotherTongueVO updateMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Starts");
		}
		
		MotherTongueVO mothertongue = motherTongueDAO.getMotherTongueByName(motherTongueVO.getMotherTongueName(),motherTongueVO.getId(),motherTongueVO.getSchoolId());	
		if(mothertongue!=null){
			throw new SMSBusinessException(SMSConstants.MOTHERTONGUE_ALREADY_EXISTS);
		}	
		motherTongueVO = motherTongueDAO.updateMotherTongue(motherTongueVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Ends");
		}
		return motherTongueVO;
	}
	
	@Override
	public boolean deleteMotherTongue(String motherTongueId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Starts");
		}
		
		boolean isDeleted = motherTongueDAO.deleteMotherTongue(motherTongueId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Ends");
		}
		return isDeleted;
	}
	
}
