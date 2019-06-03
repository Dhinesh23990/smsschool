package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.BatchConfigurationDAO;
import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.CourseDAO;
import com.tgi.sd.dao.MediumDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class BatchConfigurationManagerImpl implements BatchConfigurationManager{
	
	private static Logger logger = Logger.getLogger(BatchConfigurationManagerImpl.class);
	
	private BatchConfigurationDAO batchConfigurationDAO;
	
	private BatchDAO batchDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	private MediumDAO mediumDAO;
	
	private CourseDAO courseDAO;
	
	private TeacherDAO teacherDAO;
	
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	public MediumDAO getMediumDAO() {
		return mediumDAO;
	}
	public void setMediumDAO(MediumDAO mediumDAO) {
		this.mediumDAO = mediumDAO;
	}
	
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	public BatchConfigurationDAO getBatchConfigurationDAO() {
		return batchConfigurationDAO;
	}
	public void setBatchConfigurationDAO(BatchConfigurationDAO batchConfigurationDAO) {
		this.batchConfigurationDAO = batchConfigurationDAO;
	}
	@Override
	public BatchConfigurationVO saveBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration starts");
		}
		
		BatchConfigurationVO batchConfiguration = batchConfigurationDAO.getBatchConfigByName(null,batchConfigurationVO);	
		if(batchConfiguration!=null){
			throw new SMSBusinessException(SMSConstants.BATCHCONFIG_ALREADY_EXIST);
		}
		batchConfigurationVO = batchConfigurationDAO.saveBatchConfiguration(batchConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration ends");
		}
		return batchConfigurationVO;
		
	}
	@Override
	public BatchConfigurationVO getBatchConfigurationById(String batchConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById Starts");
		}
		
		BatchConfigurationVO batchConfigurationVO = batchConfigurationDAO.getBatchConfigurationById(batchConfigurationId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById Ends");
		}
		
		return batchConfigurationVO;
	}
	
	@Override
	public Map<String, Object> getAllBatchConfigurationBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = batchConfigurationDAO.getBatchConfigurationCountBySchoolId(schoolId);
		List<BatchConfigurationVO> batchConfigurationVOs = batchConfigurationDAO.getAllBatchConfigurationBySchoolId(schoolId, pageIndex, pageSize);
		for(BatchConfigurationVO batchConfigurationVO : batchConfigurationVOs){
			BatchVO batchVO = batchDAO.getBatchById(batchConfigurationVO.getBatchId());
			batchConfigurationVO.setBatchName(batchVO.getBatchName());
			ClassVO classVO = classDAO.getClassById(batchConfigurationVO.getClassId());
			batchConfigurationVO.setClassName(classVO.getClassName());
			SectionVO sectionVO = sectionDAO.getSectionById(batchConfigurationVO.getSectionId());
			batchConfigurationVO.setSectionName(sectionVO.getSectionName());
			MediumVO mediumVO = mediumDAO.getMediumById(batchConfigurationVO.getMediumId());
			batchConfigurationVO.setMediumName(mediumVO.getMediumName());
			CourseVO courseVO = courseDAO.getCourseById(batchConfigurationVO.getCourseId());
			batchConfigurationVO.setCourseName(courseVO.getCourseName());
			TeacherVO teacher = teacherDAO.getTeacher(batchConfigurationVO.getClassTeacherId());
			batchConfigurationVO.setClassTeacherName(teacher.getStaffName());
			
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("batchConfigurationVOs", batchConfigurationVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getBatchConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationCountBySchoolId Starts");
		}

		long cnt = batchConfigurationDAO.getBatchConfigurationCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public BatchConfigurationVO updateBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Starts");
		}
		
		BatchConfigurationVO batchConfiguration = batchConfigurationDAO.getBatchConfigByName(batchConfigurationVO.getId(),batchConfigurationVO);	
		if(batchConfiguration!=null){
			throw new SMSBusinessException(SMSConstants.BATCHCONFIG_ALREADY_EXIST);
		}
		batchConfigurationVO = batchConfigurationDAO.updateBatchConfiguration(batchConfigurationVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Ends");
		}
		return batchConfigurationVO;
	}
	
	@Override
	public boolean deleteBatchConfiguration(String batchConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Starts");
		}
		
		boolean isDeleted = batchConfigurationDAO.deleteBatchConfiguration(batchConfigurationId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Ends");
		}
		return isDeleted;
	}
	
}
