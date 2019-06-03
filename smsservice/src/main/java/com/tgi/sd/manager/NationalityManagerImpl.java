package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.NationalityDAO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class NationalityManagerImpl implements NationalityManager{
	
	private static Logger logger = Logger.getLogger(NationalityManagerImpl.class);
	
	private NationalityDAO nationalityDAO;
	
	public NationalityDAO getNationalityDAO() {
		return nationalityDAO;
	}
	public void setNationalityDAO(NationalityDAO nationalityDAO) {
		this.nationalityDAO = nationalityDAO;
	}
	
	@Override
	public NationalityVO saveNationality(NationalityVO nationalityVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveNationality starts");
		}
		
		NationalityVO nationality = nationalityDAO.getNationalityByName(nationalityVO.getNationalityName(),null,nationalityVO.getSchoolId());	
		if(nationality!=null){
			throw new SMSBusinessException(SMSConstants.NATIONALITY_ALREADY_EXISTS);
		}	
		nationalityVO = nationalityDAO.saveNationality(nationalityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveNationality ends");
		}
		return nationalityVO;
		
	}
	@Override
	public NationalityVO getNationalityById(String nationalityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityById Starts");
		}
		
		NationalityVO nationalityVO = nationalityDAO.getNationalityById(nationalityId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityById Ends");
		}
		
		return nationalityVO;
	}
	
	@Override
	public Map<String, Object> getAllNationalityBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = nationalityDAO.getNationalityCountBySchoolId(schoolId);
		List<NationalityVO> nationalityVOs = nationalityDAO.getAllNationalityBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("nationalityVOs", nationalityVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getNationalityCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityCountBySchoolId Starts");
		}

		long cnt = nationalityDAO.getNationalityCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public NationalityVO updateNationality(NationalityVO nationalityVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateNationality Starts");
		}
		
		NationalityVO nationality = nationalityDAO.getNationalityByName(nationalityVO.getNationalityName(),nationalityVO.getId(),nationalityVO.getSchoolId());	
		if(nationality!=null){
			throw new SMSBusinessException(SMSConstants.NATIONALITY_ALREADY_EXISTS);
		}	
		nationalityVO = nationalityDAO.updateNationality(nationalityVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateNationality Ends");
		}
		return nationalityVO;
	}
	
	@Override
	public boolean deleteNationality(String nationalityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNationality Starts");
		}
		
		boolean isDeleted = nationalityDAO.deleteNationality(nationalityId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNationality Ends");
		}
		return isDeleted;
	}
	
}
