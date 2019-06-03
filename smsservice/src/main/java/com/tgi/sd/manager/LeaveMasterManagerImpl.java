package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.LeaveMasterDAO;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class LeaveMasterManagerImpl implements LeaveMasterManager{
	
	private static Logger logger = Logger.getLogger(LeaveMasterManagerImpl.class);
	
	private LeaveMasterDAO leaveMasterDAO;
	
	public LeaveMasterDAO getLeaveMasterDAO() {
		return leaveMasterDAO;
	}
	public void setLeaveMasterDAO(LeaveMasterDAO leaveMasterDAO) {
		this.leaveMasterDAO = leaveMasterDAO;
	}
	
	@Override
	public LeaveMasterVO saveLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster starts");
		}
		LeaveMasterVO leaveMaster = leaveMasterDAO.getLeaveMasterByName(leaveMasterVO.getLeaveName(), null,
				leaveMasterVO.getSchoolId());
		if(leaveMaster != null) {
			throw new SMSBusinessException(SMSConstants.LEAVE_NAME_ALREADY_EXISTS);
		}
		leaveMasterVO = leaveMasterDAO.saveLeaveMaster(leaveMasterVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster ends");
		}
		return leaveMasterVO;
		
	}
	@Override
	public LeaveMasterVO getLeaveMasterById(String leaveMasterId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById Starts");
		}
		
		LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(leaveMasterId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById Ends");
		}
		
		return leaveMasterVO;
	}
	
	@Override
	public Map<String, Object> getAllLeaveMasterBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = leaveMasterDAO.getLeaveMasterCountBySchoolId(schoolId);
		List<LeaveMasterVO> leaveMasterVOs = leaveMasterDAO.getAllLeaveMasterBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("leaveMasterVOs", leaveMasterVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getLeaveMasterCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterCountBySchoolId Starts");
		}

		long cnt = leaveMasterDAO.getLeaveMasterCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public LeaveMasterVO updateLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Starts");
		}
		
		LeaveMasterVO leaveMaster = leaveMasterDAO.getLeaveMasterByName(leaveMasterVO.getLeaveName(), leaveMasterVO.getId(),
				leaveMasterVO.getSchoolId());
		if(leaveMaster != null) {
			throw new SMSBusinessException(SMSConstants.LEAVE_NAME_ALREADY_EXISTS);
		}
		leaveMasterVO = leaveMasterDAO.updateLeaveMaster(leaveMasterVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Ends");
		}
		return leaveMasterVO;
	}
	
	@Override
	public boolean deleteLeaveMaster(String leaveMasterId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Starts");
		}
		
		boolean isDeleted = leaveMasterDAO.deleteLeaveMaster(leaveMasterId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Ends");
		}
		return isDeleted;
	}
	
}
