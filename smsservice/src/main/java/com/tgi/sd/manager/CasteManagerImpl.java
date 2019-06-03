package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CasteDAO;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class CasteManagerImpl implements CasteManager{
	
	private static Logger logger = Logger.getLogger(CasteManagerImpl.class);
	
	private CasteDAO casteDAO;
	
	public CasteDAO getCasteDAO() {
		return casteDAO;
	}
	public void setCasteDAO(CasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	
	@Override
	public CasteVO saveCaste(CasteVO casteVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCaste starts");
		}
		CasteVO caste = casteDAO.getCasteByName(casteVO.getCasteName(), null,
				casteVO.getSchoolId());
		if(caste != null) {
			throw new SMSBusinessException(SMSConstants.CASTE_ALREADY_EXISTS);
		}
		casteVO = casteDAO.saveCaste(casteVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCaste ends");
		}
		return casteVO;
		
	}
	@Override
	public CasteVO getCasteById(String casteId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteById Starts");
		}
		
		CasteVO casteVO = casteDAO.getCasteById(casteId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteById Ends");
		}
		
		return casteVO;
	}
	
	@Override
	public Map<String, Object> getAllCasteBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = casteDAO.getCasteCountBySchoolId(schoolId);
		List<CasteVO> casteVOs = casteDAO.getAllCasteBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("casteVOs", casteVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getCasteCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteCountBySchoolId Starts");
		}

		long cnt = casteDAO.getCasteCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public CasteVO updateCaste(CasteVO casteVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCaste Starts");
		}
		
		CasteVO caste = casteDAO.getCasteByName(casteVO.getCasteName(), casteVO.getId(),
				casteVO.getSchoolId());
		if(caste != null) {
			throw new SMSBusinessException(SMSConstants.CASTE_ALREADY_EXISTS);
		}
		casteVO = casteDAO.updateCaste(casteVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCaste Ends");
		}
		return casteVO;
	}
	
	@Override
	public boolean deleteCaste(String casteId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCaste Starts");
		}
		
		boolean isDeleted = casteDAO.deleteCaste(casteId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCaste Ends");
		}
		return isDeleted;
	}
	
}
