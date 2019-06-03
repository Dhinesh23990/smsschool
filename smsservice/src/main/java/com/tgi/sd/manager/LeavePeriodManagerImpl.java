package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.LeaveMasterDAO;
import com.tgi.sd.dao.LeavePeriodDAO;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class LeavePeriodManagerImpl implements LeavePeriodManager{
	
	private static Logger logger = Logger.getLogger(LeavePeriodManagerImpl.class);
	
	private LeavePeriodDAO leavePeriodDAO;
	
	private LeaveMasterDAO leaveMasterDAO;
	
	public LeaveMasterDAO getLeaveMasterDAO() {
		return leaveMasterDAO;
	}
	public void setLeaveMasterDAO(LeaveMasterDAO leaveMasterDAO) {
		this.leaveMasterDAO = leaveMasterDAO;
	}
	
	public LeavePeriodDAO getLeavePeriodDAO() {
		return leavePeriodDAO;
	}
	public void setLeavePeriodDAO(LeavePeriodDAO leavePeriodDAO) {
		this.leavePeriodDAO = leavePeriodDAO;
	}
	
	@Override
	public LeavePeriodVO saveLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod starts");
		}
		LeavePeriodVO leavePeriod = leavePeriodDAO.getLeavePeriodByName(leavePeriodVO.getPeriodName(), null,
				leavePeriodVO.getSchoolId());
		if(leavePeriod != null) {
			throw new SMSBusinessException(SMSConstants.PERIOD_NAME_ALREADY_EXISTS);
		}
		leavePeriodVO = leavePeriodDAO.saveLeavePeriod(leavePeriodVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod ends");
		}
		return leavePeriodVO;
		
	}
	@Override
	public LeavePeriodVO getLeavePeriodById(String leavePeriodId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById Starts");
		}
		
		LeavePeriodVO leavePeriodVO = leavePeriodDAO.getLeavePeriodById(leavePeriodId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById Ends");
		}
		
		return leavePeriodVO;
	}
	
	@Override
	public Map<String, Object> getAllLeavePeriodBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = leavePeriodDAO.getLeavePeriodCountBySchoolId(schoolId);
		List<LeavePeriodVO> leavePeriodVOs = leavePeriodDAO.getAllLeavePeriodBySchoolId(schoolId, pageIndex, pageSize);
		for(LeavePeriodVO leavePeriodVO : leavePeriodVOs){
			LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(leavePeriodVO.getLeaveMasterId());
			leavePeriodVO.setLeaveMasterName(leaveMasterVO.getLeaveName());
		}
		
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("leavePeriodVOs", leavePeriodVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllLeavePeriodByMasterId(String schoolId,
			String masterId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodByMasterId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<LeavePeriodVO> leavePeriodVOs = leavePeriodDAO.getAllLeavePeriodByMasterId(schoolId, masterId);
		for(LeavePeriodVO leavePeriodVO : leavePeriodVOs){
			LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(leavePeriodVO.getLeaveMasterId());
			leavePeriodVO.setLeaveMasterName(leaveMasterVO.getLeaveName());
		}
    	responseObjectsMap.put("leavePeriodVOs", leavePeriodVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodByMasterId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getLeavePeriodCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodCountBySchoolId Starts");
		}

		long cnt = leavePeriodDAO.getLeavePeriodCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public LeavePeriodVO updateLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Starts");
		}
		
		LeavePeriodVO leavePeriod = leavePeriodDAO.getLeavePeriodByName(leavePeriodVO.getPeriodName(), leavePeriodVO.getId(),
				leavePeriodVO.getSchoolId());
		if(leavePeriod != null) {
			throw new SMSBusinessException(SMSConstants.PERIOD_NAME_ALREADY_EXISTS);
		}
		leavePeriodVO = leavePeriodDAO.updateLeavePeriod(leavePeriodVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Ends");
		}
		return leavePeriodVO;
	}
	
	@Override
	public boolean deleteLeavePeriod(String leavePeriodId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Starts");
		}
		
		boolean isDeleted = leavePeriodDAO.deleteLeavePeriod(leavePeriodId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Ends");
		}
		return isDeleted;
	}
	
}
