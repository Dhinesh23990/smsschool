package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.PincodeDAO;
import com.tgi.sd.domain.config.PincodeVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class PincodeManagerImpl implements PincodeManager{
	
	private static Logger logger = Logger.getLogger(PincodeManagerImpl.class);
	
	private PincodeDAO pincodeDAO;
	
	public PincodeDAO getPincodeDAO() {
		return pincodeDAO;
	}
	public void setPincodeDAO(PincodeDAO pincodeDAO) {
		this.pincodeDAO = pincodeDAO;
	}
	
	@Override
	public PincodeVO savePincode(PincodeVO pincodeVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("savePincode starts");
		}
		
		pincodeVO = pincodeDAO.savePincode(pincodeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("savePincode ends");
		}
		return pincodeVO;
		
	}
	@Override
	public PincodeVO getPincodeById(String pincodeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeById Starts");
		}
		
		PincodeVO pincodeVO = pincodeDAO.getPincodeById(pincodeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeById Ends");
		}
		
		return pincodeVO;
	}
	
	@Override
	public Map<String, Object> getAllPincodeBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = pincodeDAO.getPincodeCountBySchoolId(schoolId);
		List<PincodeVO> pincodeVOs = pincodeDAO.getAllPincodeBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("pincodeVOs", pincodeVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getPincodeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeCountBySchoolId Starts");
		}

		long cnt = pincodeDAO.getPincodeCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public PincodeVO updatePincode(PincodeVO pincodeVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updatePincode Starts");
		}
		
		pincodeVO = pincodeDAO.updatePincode(pincodeVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updatePincode Ends");
		}
		return pincodeVO;
	}
	
	@Override
	public boolean deletePincode(String pincodeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deletePincode Starts");
		}
		
		boolean isDeleted = pincodeDAO.deletePincode(pincodeId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deletePincode Ends");
		}
		return isDeleted;
	}
	
}
