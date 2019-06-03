package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.NotificationDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.NotificationVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class NotificationManagerImpl implements NotificationManager{
	
	private static Logger logger = Logger.getLogger(NotificationManagerImpl.class);
	
	private NotificationDAO notificationDAO;
	
	private StudentDAO studentDAO;
	
	public NotificationDAO getNotificationDAO() {
		return notificationDAO;
	}
	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	@Override
	public NotificationVO saveNotification(NotificationVO notificationVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveNotification starts");
		}
		NotificationVO notification = notificationDAO.getNotificationByName(notificationVO.getMessage(), null,
				notificationVO.getSchoolId());
		if(notification != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		notificationVO = notificationDAO.saveNotification(notificationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveNotification ends");
		}
		return notificationVO;
		
	}
	@Override
	public NotificationVO getNotificationById(String notificationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationById Starts");
		}
		
		NotificationVO notificationVO = notificationDAO.getNotificationById(notificationId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationById Ends");
		}
		
		return notificationVO;
	}
	
	@Override
	public Map<String, Object> getAllNotificationBySchoolId(String schoolId,String mobileNo, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Starts");
		}
			
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	List<String> studentIds =new ArrayList<String>();
    	List<String>  classIds =new ArrayList<String>();
    	List<StudentVO> studentList = studentDAO.getStudentsByMobileNoAndSchoolId(schoolId, mobileNo);
    	if(studentList != null) {
    		for(StudentVO studentVO : studentList) {
    			studentIds.add(studentVO.getId());
    			classIds.add(studentVO.getClassId());
    		}
    	}
    	long totalRecords = notificationDAO.getNotificationCountBySchoolId(schoolId,studentIds,classIds);
		List<NotificationVO> notificationVOs = notificationDAO.getAllNotificationBySchoolId(schoolId,studentIds,classIds, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("notificationVOs", notificationVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getNotificationCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationCountBySchoolId Starts");
		}

		long cnt = notificationDAO.getNotificationCountBySchoolId(schoolId, null, null);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public NotificationVO updateNotification(NotificationVO notificationVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateNotification Starts");
		}
		
		NotificationVO notification = notificationDAO.getNotificationByName(notificationVO.getMessage(), notificationVO.getId(),
				notificationVO.getSchoolId());
		if(notification != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		notificationVO = notificationDAO.updateNotification(notificationVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateNotification Ends");
		}
		return notificationVO;
	}
	
	@Override
	public boolean deleteNotification(String notificationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNotification Starts");
		}
		
		boolean isDeleted = notificationDAO.deleteNotification(notificationId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNotification Ends");
		}
		return isDeleted;
	}
	@Override
	public Map<String, Object> getNotificationsByFilter(FilterVO filterVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationsByFilter Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<NotificationVO> notificationVOs = notificationDAO.getNotificationsBySchoolIdAndDate(filterVO.getSchoolId(),
				null, null);
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationsByFilter Ends");
		}		
		responseObjectsMap.put("notificationVOs", notificationVOs);
		return responseObjectsMap;
	}
	
	@Override
	public Map<String, Object> getAllNotifiBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNotifiBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = notificationDAO.getNotifiCountBySchoolId(schoolId);
		List<NotificationVO> notificationVOs = notificationDAO.getAllNotifiBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("notificationVOs", notificationVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllNotifiBySchoolId Ends");
		}
		return responseObjectsMap;
	}
		
	

}
