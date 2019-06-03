package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.EventManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/event")
public class EventService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(EventService.class);
	
	private EventManager eventManager;
	
	
	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public ResponseVO saveEvent(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveEvent Starts");
		}
		ResponseVO responseVO = null;
		EventVO eventVO = null;
		try{
			eventVO = (EventVO) parseObjectFromRequest(requestVO,EventVO.class);
			if(null != eventVO) {
				eventVO = eventManager.saveEvent(eventVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("EventVO",eventVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveEvent Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getEventById", method = RequestMethod.GET)
	public ResponseVO getEventById(@RequestParam String eventId){
		if(logger.isDebugEnabled()) {
			logger.debug("getEventById Starts");
		}
		ResponseVO responseVO = null;
		EventVO eventVO = null;
		try{
			eventVO = eventManager.getEventById(eventId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("EventVO", eventVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getEventById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllEventBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllEventBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = eventManager.getAllEventBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateEvent", method = RequestMethod.POST)
	public ResponseVO updateEvent(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateEvent Starts");
		}
		ResponseVO responseVO = null;
		try {
			EventVO eventVO = (EventVO) parseObjectFromRequest(requestVO, EventVO.class);
			if (null != eventVO) {
				eventVO = eventManager.updateEvent(eventVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("EventVO", eventVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateEvent Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.DELETE)
	public ResponseVO deleteEvent(@RequestParam String eventId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteEvent Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = eventManager.deleteEvent(eventId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteEvent Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getEventsByFilter",method=RequestMethod.POST)
	public ResponseVO getEventsByFilter(@RequestBody String requestVO)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		FilterVO filterVO = null;
		try{
			filterVO = (FilterVO) parseObjectFromRequest(requestVO,FilterVO.class);
			if(null != filterVO) {
	    		responseObjectsMap = eventManager.getEventsByFilter(filterVO);
	    		responseVO = createSuccessResponseVO(responseObjectsMap);
	    	}
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Ends");
		}
	    return responseVO;
		
	}
	
}
