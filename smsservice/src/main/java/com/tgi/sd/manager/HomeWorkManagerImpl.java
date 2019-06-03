package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.HomeWorkDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.HomeWorkVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class HomeWorkManagerImpl implements HomeWorkManager{
	
	private static Logger logger = Logger.getLogger(HomeWorkManagerImpl.class);
	
	private HomeWorkDAO homeworkDAO;
	
	private StudentDAO studentDAO;
	
	
	public HomeWorkDAO getHomeworkDAO() {
		return homeworkDAO;
	}
	public void setHomeworkDAO(HomeWorkDAO homeworkDAO) {
		this.homeworkDAO = homeworkDAO;
	}
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	@Override
	public HomeWorkVO saveHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveHomeWork starts");
		}
		HomeWorkVO homework = homeworkDAO.getHomeWorkByName(homeworkVO.getMessage(), null,
				homeworkVO.getSchoolId(),homeworkVO.getStudentId());
		if(homework != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		homeworkVO = homeworkDAO.saveHomeWork(homeworkVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveHomeWork ends");
		}
		return homeworkVO;
		
	}
	@Override
	public HomeWorkVO getHomeWorkById(String homeworkId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById Starts");
		}
		
		HomeWorkVO homeworkVO = homeworkDAO.getHomeWorkById(homeworkId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById Ends");
		}
		
		return homeworkVO;
	}
	
	@Override
	public Map<String, Object> getAllHomeWorkBySchoolId(String schoolId,String studentId,String mobileNo, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Starts");
		}
			
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	/*List<String> studentIds =new ArrayList<String>();
    	List<String>  classIds =new ArrayList<String>();
    	List<StudentVO> studentList = studentDAO.getStudentsByMobileNoAndStudentId(schoolId,studentId,mobileNo);
    	if(studentList != null) {
    		for(StudentVO studentVO : studentList) {
    			studentIds.add(studentVO.getId());
    			classIds.add(studentVO.getClassId());
    		}
    	}*/
    	long totalRecords = homeworkDAO.getHomeWorkCountBySchoolId(schoolId,studentId);
		List<HomeWorkVO> homeworkVOs = homeworkDAO.getAllHomeWorkBySchoolId(schoolId,studentId,pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("homeworkVOs", homeworkVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getHomeWorkCountBySchoolId(String schoolId,String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkCountBySchoolId Starts");
		}

		long cnt = homeworkDAO.getHomeWorkCountBySchoolId(schoolId,studentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public HomeWorkVO updateHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Starts");
		}
		
		HomeWorkVO homework = homeworkDAO.getHomeWorkByName(homeworkVO.getMessage(), homeworkVO.getId(),
				homeworkVO.getSchoolId(),homeworkVO.getStudentId());
		if(homework != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		homeworkVO = homeworkDAO.updateHomeWork(homeworkVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Ends");
		}
		return homeworkVO;
	}
	
	@Override
	public boolean deleteHomeWork(String homeworkId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Starts");
		}
		
		boolean isDeleted = homeworkDAO.deleteHomeWork(homeworkId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Ends");
		}
		return isDeleted;
	}
	@Override
	public Map<String, Object> getHomeWorkByFilter(FilterVO filterVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkByFilter Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<HomeWorkVO> homeworkVOs = homeworkDAO.getHomeWorkBySchoolIdAndDate(filterVO.getSchoolId(),
				null, null);
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkByFilter Ends");
		}		
		responseObjectsMap.put("homeworkVOs", homeworkVOs);
		return responseObjectsMap;
	}
	
}
