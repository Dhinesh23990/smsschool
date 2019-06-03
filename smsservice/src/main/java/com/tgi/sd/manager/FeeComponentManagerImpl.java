package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.FeeComponentDAO;
import com.tgi.sd.dao.FeeTypeDAO;
import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class FeeComponentManagerImpl implements FeeComponentManager{
	
	private static Logger logger = Logger.getLogger(FeeComponentManagerImpl.class);
	
	private FeeComponentDAO feeComponentDAO;
	
	private FeeTypeDAO feeTypeDAO;
	
	public FeeTypeDAO getFeeTypeDAO() {
		return feeTypeDAO;
	}
	public void setFeeTypeDAO(FeeTypeDAO feeTypeDAO) {
		this.feeTypeDAO = feeTypeDAO;
	}
	
	
	public FeeComponentDAO getFeeComponentDAO() {
		return feeComponentDAO;
	}
	public void setFeeComponentDAO(FeeComponentDAO feeComponentDAO) {
		this.feeComponentDAO = feeComponentDAO;
	}
	
	@Override
	public FeeComponentVO saveFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent starts");
		}
		FeeComponentVO feeComponent = feeComponentDAO.getFeeComponentByName(feeComponentVO.getFeeComponent(), null,
				feeComponentVO.getSchoolId());
		if(feeComponent != null) {
			throw new SMSBusinessException(SMSConstants.FEE_COMPONENT_ALREADY_EXISTS);
		}
		feeComponentVO = feeComponentDAO.saveFeeComponent(feeComponentVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent ends");
		}
		return feeComponentVO;
		
	}
	@Override
	public FeeComponentVO getFeeComponentById(String feeComponentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById Starts");
		}
		
		FeeComponentVO feeComponentVO = feeComponentDAO.getFeeComponentById(feeComponentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById Ends");
		}
		
		return feeComponentVO;
	}
	
	@Override
	public Map<String, Object> getAllFeeComponentBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = feeComponentDAO.getFeeComponentCountBySchoolId(schoolId);
		List<FeeComponentVO> feeComponentVOs = feeComponentDAO.getAllFeeComponentBySchoolId(schoolId, pageIndex, pageSize);
		for(FeeComponentVO feeComponentVO:feeComponentVOs){
			FeeTypeVO feeTypeVO = feeTypeDAO.getFeeTypeById(feeComponentVO.getFeeTypeId());
			feeComponentVO.setFeeTypeName(feeTypeVO.getFeeType());
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("feeComponentVOs", feeComponentVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getFeeComponentCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentCountBySchoolId Starts");
		}

		long cnt = feeComponentDAO.getFeeComponentCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public FeeComponentVO updateFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Starts");
		}
		
		FeeComponentVO feeComponent = feeComponentDAO.getFeeComponentByName(feeComponentVO.getFeeComponent(), feeComponentVO.getId(),
				feeComponentVO.getSchoolId());
		if(feeComponent != null) {
			throw new SMSBusinessException(SMSConstants.FEE_COMPONENT_ALREADY_EXISTS);
		}
		feeComponentVO = feeComponentDAO.updateFeeComponent(feeComponentVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Ends");
		}
		return feeComponentVO;
	}
	
	@Override
	public boolean deleteFeeComponent(String feeComponentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Starts");
		}
		
		boolean isDeleted = feeComponentDAO.deleteFeeComponent(feeComponentId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Ends");
		}
		return isDeleted;
	}
	
}
