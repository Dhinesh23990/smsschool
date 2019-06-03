package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.NotificationVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.NotificationManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/notification")
public class NotificationService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(NotificationService.class);
	
	private NotificationManager notificationManager;
	
	
	public NotificationManager getNotificationManager() {
		return notificationManager;
	}

	public void setNotificationManager(NotificationManager notificationManager) {
		this.notificationManager = notificationManager;
	}

	@RequestMapping(value = "/saveNotification", method = RequestMethod.POST)
	public ResponseVO saveNotification(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveNotification Starts");
		}
		ResponseVO responseVO = null;
		NotificationVO notificationVO = null;
		try{
			notificationVO = (NotificationVO) parseObjectFromRequest(requestVO,NotificationVO.class);
			if(null != notificationVO) {
				notificationVO = notificationManager.saveNotification(notificationVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("NotificationVO",notificationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveNotification Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getNotificationById", method = RequestMethod.GET)
	public ResponseVO getNotificationById(@RequestParam String notificationId){
		if(logger.isDebugEnabled()) {
			logger.debug("getNotificationById Starts");
		}
		ResponseVO responseVO = null;
		NotificationVO notificationVO = null;
		try{
			notificationVO = notificationManager.getNotificationById(notificationId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("NotificationVO", notificationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getNotificationById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllNotificationBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllNotificationBySchoolId(@RequestParam String schoolId,String mobileNo, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = notificationManager.getAllNotificationBySchoolId(schoolId, mobileNo, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateNotification", method = RequestMethod.POST)
	public ResponseVO updateNotification(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateNotification Starts");
		}
		ResponseVO responseVO = null;
		try {
			NotificationVO notificationVO = (NotificationVO) parseObjectFromRequest(requestVO, NotificationVO.class);
			if (null != notificationVO) {
				notificationVO = notificationManager.updateNotification(notificationVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("NotificationVO", notificationVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateNotification Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteNotification", method = RequestMethod.DELETE)
	public ResponseVO deleteNotification(@RequestParam String notificationId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteNotification Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = notificationManager.deleteNotification(notificationId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteNotification Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getNotificationsByFilter",method=RequestMethod.POST)
	public ResponseVO getNotificationsByFilter(@RequestBody String requestVO)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getNotificationsByFilter Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		FilterVO filterVO = null;
		try{
			filterVO = (FilterVO) parseObjectFromRequest(requestVO,FilterVO.class);
			if(null != filterVO) {
	    		responseObjectsMap = notificationManager.getNotificationsByFilter(filterVO);
	    		responseVO = createSuccessResponseVO(responseObjectsMap);
	    	}
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getNotificationsByFilter Ends");
		}
	    return responseVO;
		
	}
	
	
	@RequestMapping(value="/getAllNotifiBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllNotifiBySchoolId(@RequestParam String schoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllNotifiBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = notificationManager.getAllNotifiBySchoolId(schoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllNotifiBySchoolId Ends");
		}
	    return responseVO;
		
	}
}
