package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.EducationQualificationDAO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class EducationQualificationManagerImpl implements EducationQualificationManager{
	
	private static Logger logger = Logger.getLogger(CourseManagerImpl.class);
	
	private EducationQualificationDAO educationQualificationDAO;
	
	public EducationQualificationDAO getEducationQualificationDAO() {
		return educationQualificationDAO;
	}
	public void setEducationQualificationDAO(EducationQualificationDAO educationQualificationDAO) {
		this.educationQualificationDAO = educationQualificationDAO;
	}
	
	@Override
	public EducationalVO saveEducation(EducationalVO educationalVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse starts");
		}
		educationalVO.setId(UUID.randomUUID().toString());
		EducationalVO course = educationQualificationDAO.getEducationByName(educationalVO.getEducationName(),null,educationalVO.getSchoolId());	
		if(course!=null){
			throw new SMSBusinessException(SMSConstants.Education_ALREADY_EXIST);
		}	
		educationalVO = educationQualificationDAO.saveEducation(educationalVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse ends");
		}
		return educationalVO;
		
	}
	@Override
	public EducationalVO getEducationById(String educationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEducationById Starts");
		}
		
		EducationalVO educationalVO = educationQualificationDAO.getEducationById(educationId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEducationById Ends");
		}
		
		return educationalVO;
	}
	
	@Override
	public Map<String, Object> getAllEducationBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEducationBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = educationQualificationDAO.getEducationCountBySchoolId(schoolId);
		List<EducationalVO> educationalVOs = educationQualificationDAO.getAllEducationBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("educationalVOs", educationalVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllEducationBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getCourseCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseCountBySchoolId Starts");
		}

		long cnt = educationQualificationDAO.getEducationCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public EducationalVO updateEducation(EducationalVO educationalVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateEducation Starts");
		}
		
		EducationalVO educational = educationQualificationDAO.getEducationByName(educationalVO.getEducationName(),educationalVO.getId(),educationalVO.getSchoolId());	
		if(educational!=null){
			throw new SMSBusinessException(SMSConstants.Education_ALREADY_EXIST);
		}	
		educationalVO = educationQualificationDAO.updateEducation(educationalVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateEducation Ends");
		}
		return educationalVO;
	}
	
	@Override
	public boolean deleteEducation(String educationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEducation Starts");
		}
		
		boolean isDeleted = educationQualificationDAO.deleteEducation(educationId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEducation Ends");
		}
		return isDeleted;
	}
	
}
