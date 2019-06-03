package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class SectionManagerImpl implements SectionManager{
	
	private static Logger logger = Logger.getLogger(SectionManagerImpl.class);
	
	private SectionDAO sectionDAO;
	
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	
	@Override
	public SectionVO saveSection(SectionVO sectionVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveSection starts");
		}
		
		SectionVO section = sectionDAO.getSectionByName(sectionVO.getSectionName(),null,sectionVO.getSchoolId());	
		if(section!=null){
			throw new SMSBusinessException(SMSConstants.SECTION_ALREADY_EXISTS);
		}	
		sectionVO = sectionDAO.saveSection(sectionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSection ends");
		}
		return sectionVO;
		
	}
	@Override
	public SectionVO getSectionById(String sectionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionById Starts");
		}
		
		SectionVO sectionVO = sectionDAO.getSectionById(sectionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionById Ends");
		}
		
		return sectionVO;
	}
	
	@Override
	public Map<String, Object> getAllSectionBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = sectionDAO.getSectionCountBySchoolId(schoolId);
		List<SectionVO> sectionVOs = sectionDAO.getAllSectionBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("sectionVOs", sectionVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getSectionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionCountBySchoolId Starts");
		}

		long cnt = sectionDAO.getSectionCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public SectionVO updateSection(SectionVO sectionVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSection Starts");
		}
		
		SectionVO section = sectionDAO.getSectionByName(sectionVO.getSectionName(),sectionVO.getId(),sectionVO.getSchoolId());	
		if(section!=null){
			throw new SMSBusinessException(SMSConstants.SECTION_ALREADY_EXISTS);
		}	
		sectionVO = sectionDAO.updateSection(sectionVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateSection Ends");
		}
		return sectionVO;
	}
	
	@Override
	public boolean deleteSection(String sectionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSection Starts");
		}
		
		boolean isDeleted = sectionDAO.deleteSection(sectionId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSection Ends");
		}
		return isDeleted;
	}
	
}
