package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.NotificationVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface NotificationDAO {

	NotificationVO saveNotification(NotificationVO notificationVO) throws SMSBusinessException;

	NotificationVO getNotificationById(String notificationId) throws SMSBusinessException;

	List<NotificationVO> getAllNotificationBySchoolId(String schoolId,List<String> studentIds, List<String> classIds, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getNotificationCountBySchoolId(String schoolId,List<String> studentIds, List<String> classIds) throws SMSBusinessException;

	NotificationVO updateNotification(NotificationVO notificationVO) throws SMSBusinessException;

	boolean deleteNotification(String notificationId) throws SMSBusinessException;

	NotificationVO getNotificationByName(String notificationName, String id, String schoolId)
			throws SMSBusinessException;

	List<NotificationVO> getNotificationsBySchoolIdAndDate(String schoolId, Date startDate, Date endDate) throws SMSBusinessException;

	long getNotifiCountBySchoolId(String schoolId) throws SMSBusinessException;

	List<NotificationVO> getAllNotifiBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

}
