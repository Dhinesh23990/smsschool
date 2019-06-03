 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.NotificationVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface NotificationManager {

	NotificationVO saveNotification(NotificationVO notificationVO) throws SMSBusinessException;

	NotificationVO getNotificationById(String notificationId) throws SMSBusinessException;

	NotificationVO updateNotification(NotificationVO notificationVO) throws SMSBusinessException;

	boolean deleteNotification(String notificationId) throws SMSBusinessException;

	long getNotificationCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllNotificationBySchoolId(String shoolId,String mobileNo, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getNotificationsByFilter(FilterVO filterVO) throws SMSBusinessException;
	
	Map<String, Object> getAllNotifiBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

}
