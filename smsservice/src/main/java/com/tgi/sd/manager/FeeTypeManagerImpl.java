package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.FeeTypeDAO;
import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class FeeTypeManagerImpl implements FeeTypeManager{
	
	private static Logger logger = Logger.getLogger(FeeTypeManagerImpl.class);
	
	private FeeTypeDAO feeTypeDAO;
	
	public FeeTypeDAO getFeeTypeDAO() {
		return feeTypeDAO;
	}
	public void setFeeTypeDAO(FeeTypeDAO feeTypeDAO) {
		this.feeTypeDAO = feeTypeDAO;
	}
	
	@Override
	public FeeTypeVO saveFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeType starts");
		}
		FeeTypeVO feeType = feeTypeDAO.getFeeTypeByName(feeTypeVO.getFeeType(), null,
				feeTypeVO.getSchoolId());
		if(feeType != null) {
			throw new SMSBusinessException(SMSConstants.FEE_TYPE_ALREADY_EXISTS);
		}
		feeTypeVO = feeTypeDAO.saveFeeType(feeTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeType ends");
		}
		return feeTypeVO;
		
	}
	@Override
	public FeeTypeVO getFeeTypeById(String feeTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById Starts");
		}
		
		FeeTypeVO feeTypeVO = feeTypeDAO.getFeeTypeById(feeTypeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById Ends");
		}
		
		return feeTypeVO;
	}
	
	@Override
	public Map<String, Object> getAllFeeTypeBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = feeTypeDAO.getFeeTypeCountBySchoolId(schoolId);
		List<FeeTypeVO> feeTypeVOs = feeTypeDAO.getAllFeeTypeBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("feeTypeVOs", feeTypeVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getFeeTypeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeCountBySchoolId Starts");
		}

		long cnt = feeTypeDAO.getFeeTypeCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeCountBySchoolId Ends");
		}
		return cnt;
	}
	
	@Override
	public FeeTypeVO updateFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Starts");
		}
		
		feeTypeVO = feeTypeDAO.updateFeeType(feeTypeVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Ends");
		}
		return feeTypeVO;
	}

	
	/*	
	@Override
	public FeeTypeVO updateFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Starts");
		}
		
		FeeTypeVO feeType = feeTypeDAO.getFeeTypeByName(feeTypeVO.getFeeType(), feeTypeVO.getId(),
				feeTypeVO.getSchoolId());
		if(feeType != null) {
			throw new SMSBusinessException(SMSConstants.FEE_TYPE_ALREADY_EXISTS);
		}
		feeTypeVO = feeTypeDAO.updateFeeType(feeTypeVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Ends");
		}
		return feeTypeVO;
	}
	*/
	
	@Override
	public boolean deleteFeeType(String feeTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Starts");
		}
		
		boolean isDeleted = feeTypeDAO.deleteFeeType(feeTypeId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Ends");
		}
		return isDeleted;
	}
	
}
