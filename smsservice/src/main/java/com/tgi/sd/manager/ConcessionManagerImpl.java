package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.ConcessionDAO;
import com.tgi.sd.domain.fms.ConcessionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class ConcessionManagerImpl implements ConcessionManager{
	
	private static Logger logger = Logger.getLogger(ConcessionManagerImpl.class);
	
	private ConcessionDAO concessionDAO;
	
	public ConcessionDAO getConcessionDAO() {
		return concessionDAO;
	}
	public void setConcessionDAO(ConcessionDAO concessionDAO) {
		this.concessionDAO = concessionDAO;
	}
	
	@Override
	public ConcessionVO saveConcession(ConcessionVO concessionVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveConcession starts");
		}
		ConcessionVO concession = concessionDAO.getConcessionByName(concessionVO.getConcessionName(), null,
				concessionVO.getSchoolId());
		if(concession != null) {
			throw new SMSBusinessException(SMSConstants.CONCESSION_NAME_ALREADY_EXISTS);
		}
		concessionVO = concessionDAO.saveConcession(concessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveConcession ends");
		}
		return concessionVO;
		
	}
	@Override
	public ConcessionVO getConcessionById(String concessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionById Starts");
		}
		
		ConcessionVO concessionVO = concessionDAO.getConcessionById(concessionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionById Ends");
		}
		
		return concessionVO;
	}
	
	@Override
	public Map<String, Object> getAllConcessionBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = concessionDAO.getConcessionCountBySchoolId(schoolId);
		List<ConcessionVO> concessionVOs = concessionDAO.getAllConcessionBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("concessionVOs", concessionVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getConcessionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionCountBySchoolId Starts");
		}

		long cnt = concessionDAO.getConcessionCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public ConcessionVO updateConcession(ConcessionVO concessionVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateConcession Starts");
		}
		
		ConcessionVO concession = concessionDAO.getConcessionByName(concessionVO.getConcessionName(), concessionVO.getId(),
				concessionVO.getSchoolId());
		if(concession != null) {
			throw new SMSBusinessException(SMSConstants.CONCESSION_NAME_ALREADY_EXISTS);
		}
		concessionVO = concessionDAO.updateConcession(concessionVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateConcession Ends");
		}
		return concessionVO;
	}
	
	@Override
	public boolean deleteConcession(String concessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteConcession Starts");
		}
		
		boolean isDeleted = concessionDAO.deleteConcession(concessionId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteConcession Ends");
		}
		return isDeleted;
	}
	
}
