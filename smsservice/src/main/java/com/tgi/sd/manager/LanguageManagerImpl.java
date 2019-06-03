package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.LanguageDAO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class LanguageManagerImpl implements LanguageManager{
	
	private static Logger logger = Logger.getLogger(LanguageManagerImpl.class);
	
	private LanguageDAO languageDAO;
	
	public LanguageDAO getLanguageDAO() {
		return languageDAO;
	}
	public void setLanguageDAO(LanguageDAO languageDAO) {
		this.languageDAO = languageDAO;
	}
	
	@Override
	public LanguageVO saveLanguage(LanguageVO languageVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveLanguage starts");
		}
		
		LanguageVO language = languageDAO.getLanguageByName(languageVO.getLanguageName(),null,languageVO.getSchoolId());	
		if(language!=null){
			throw new SMSBusinessException(SMSConstants.LANAGUAGE_ALREADY_EXISTS);
		}	
		languageVO = languageDAO.saveLanguage(languageVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLanguage ends");
		}
		return languageVO;
		
	}
	@Override
	public LanguageVO getLanguageById(String languageId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageById Starts");
		}
		
		LanguageVO languageVO = languageDAO.getLanguageById(languageId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageById Ends");
		}
		
		return languageVO;
	}
	
	@Override
	public Map<String, Object> getAllLanguageBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = languageDAO.getLanguageCountBySchoolId(schoolId);
		List<LanguageVO> languageVOs = languageDAO.getAllLanguageBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("languageVOs", languageVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getLanguageCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageCountBySchoolId Starts");
		}

		long cnt = languageDAO.getLanguageCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public LanguageVO updateLanguage(LanguageVO languageVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLanguage Starts");
		}
		
		LanguageVO language = languageDAO.getLanguageByName(languageVO.getLanguageName(),languageVO.getId(),languageVO.getSchoolId());	
		if(language!=null){
			throw new SMSBusinessException(SMSConstants.LANAGUAGE_ALREADY_EXISTS);
		}	
		languageVO = languageDAO.updateLanguage(languageVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateLanguage Ends");
		}
		return languageVO;
	}
	
	@Override
	public boolean deleteLanguage(String languageId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Starts");
		}
		
		boolean isDeleted = languageDAO.deleteLanguage(languageId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Ends");
		}
		return isDeleted;
	}
	
}
