package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.GenderDAO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class GenderManagerImpl implements GenderManager{
	
	private static Logger logger = Logger.getLogger(GenderManagerImpl.class);
	
	private GenderDAO genderDAO;
	
	public GenderDAO getGenderDAO() {
		return genderDAO;
	}
	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}
	
	@Override
	public GenderVO saveGender(GenderVO genderVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveGender starts");
		}
		
		GenderVO gender = genderDAO.getGenderByName(genderVO.getGender(),null,genderVO.getSchoolId());	
		if(gender!=null){
			throw new SMSBusinessException(SMSConstants.GENDER_ALREADY_EXISTS);
		}	
		genderVO = genderDAO.saveGender(genderVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGender ends");
		}
		return genderVO;
		
	}
	@Override
	public GenderVO getGenderById(String genderId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderById Starts");
		}
		
		GenderVO genderVO = genderDAO.getGenderById(genderId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderById Ends");
		}
		
		return genderVO;
	}
	
	@Override
	public Map<String, Object> getAllGenderBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = genderDAO.getGenderCountBySchoolId(schoolId);
		List<GenderVO> religionVOs = genderDAO.getAllGenderBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("religionVOs", religionVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	
		
	@Override
	public GenderVO updateGender(GenderVO genderVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateGender Starts");
		}
		
		GenderVO gender = genderDAO.getGenderByName(genderVO.getGender(),genderVO.getId(),genderVO.getSchoolId());	
		if(gender!=null){
			throw new SMSBusinessException(SMSConstants.GENDER_ALREADY_EXISTS);
		}	
		genderVO = genderDAO.updateGender(genderVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateGender Ends");
		}
		return genderVO;
	}
	
	@Override
	public boolean deleteGender(String genderId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGender Starts");
		}
		
		boolean isDeleted = genderDAO.deleteGender(genderId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGender Ends");
		}
		return isDeleted;
	}
	
}
