package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.MediumDAO;
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class MediumManagerImpl implements MediumManager{
	
	private static Logger logger = Logger.getLogger(MediumManagerImpl.class);
	
	private MediumDAO mediumDAO;
	
	public MediumDAO getMediumDAO() {
		return mediumDAO;
	}
	public void setMediumDAO(MediumDAO mediumDAO) {
		this.mediumDAO = mediumDAO;
	}
	
	@Override
	public MediumVO saveMedium(MediumVO mediumVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveMedium starts");
		}
		MediumVO medium = mediumDAO.getMediumByName(mediumVO.getMediumName(),null,mediumVO.getSchoolId());	
		if(medium!=null){
			throw new SMSBusinessException(SMSConstants.MEDIUM_ALREADY_EXISTS);
		}	
		mediumVO = mediumDAO.saveMedium(mediumVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveMedium ends");
		}
		return mediumVO;
		
	}
	@Override
	public MediumVO getMediumById(String mediumId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumById Starts");
		}
		
		MediumVO mediumVO = mediumDAO.getMediumById(mediumId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumById Ends");
		}
		
		return mediumVO;
	}
	
	@Override
	public Map<String, Object> getAllMediumBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = mediumDAO.getMediumCountBySchoolId(schoolId);
		List<MediumVO> mediumVOs = mediumDAO.getAllMediumBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("mediumVOs", mediumVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getMediumCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumCountBySchoolId Starts");
		}

		long cnt = mediumDAO.getMediumCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public MediumVO updateMedium(MediumVO mediumVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMedium Starts");
		}
		
		MediumVO medium = mediumDAO.getMediumByName(mediumVO.getMediumName(),mediumVO.getId(),mediumVO.getSchoolId());	
		if(medium!=null){
			throw new SMSBusinessException(SMSConstants.MEDIUM_ALREADY_EXISTS);
		}	
		mediumVO = mediumDAO.updateMedium(mediumVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateMedium Ends");
		}
		return mediumVO;
	}
	
	@Override
	public boolean deleteMedium(String mediumId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMedium Starts");
		}
		
		boolean isDeleted = mediumDAO.deleteMedium(mediumId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMedium Ends");
		}
		return isDeleted;
	}
	
}
