package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ReligionDAO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class ReligionManagerImpl implements ReligionManager{
	
	private static Logger logger = Logger.getLogger(ReligionManagerImpl.class);
	
	private ReligionDAO religionDAO;
	
	public ReligionDAO getReligionDAO() {
		return religionDAO;
	}
	public void setReligionDAO(ReligionDAO religionDAO) {
		this.religionDAO = religionDAO;
	}
	
	@Override
	public ReligionVO saveReligion(ReligionVO religionVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveReligion starts");
		}
		
		ReligionVO religion = religionDAO.getReligionByName(religionVO.getReligionName(),null,religionVO.getSchoolId());	
		if(religion!=null){
			throw new SMSBusinessException(SMSConstants.RELIGION_ALREADY_EXISTS);
		}	
		religionVO = religionDAO.saveReligion(religionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveReligion ends");
		}
		return religionVO;
		
	}
	@Override
	public ReligionVO getReligionById(String religionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionById Starts");
		}
		
		ReligionVO religionVO = religionDAO.getReligionById(religionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionById Ends");
		}
		
		return religionVO;
	}
	
	@Override
	public Map<String, Object> getAllReligionBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = religionDAO.getReligionCountBySchoolId(schoolId);
		List<ReligionVO> religionVOs = religionDAO.getAllReligionBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("religionVOs", religionVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getReligionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionCountBySchoolId Starts");
		}

		long cnt = religionDAO.getReligionCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public ReligionVO updateReligion(ReligionVO religionVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateReligion Starts");
		}
		
		ReligionVO religion = religionDAO.getReligionByName(religionVO.getReligionName(),religionVO.getId(),religionVO.getSchoolId());	
		if(religion!=null){
			throw new SMSBusinessException(SMSConstants.RELIGION_ALREADY_EXISTS);
		}	
		religionVO = religionDAO.updateReligion(religionVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateReligion Ends");
		}
		return religionVO;
	}
	
	@Override
	public boolean deleteReligion(String religionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteReligion Starts");
		}
		
		boolean isDeleted = religionDAO.deleteReligion(religionId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteReligion Ends");
		}
		return isDeleted;
	}
	
}
