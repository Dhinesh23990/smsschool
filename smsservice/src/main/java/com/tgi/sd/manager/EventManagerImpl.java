package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.EventDAO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class EventManagerImpl implements EventManager{
	
	private static Logger logger = Logger.getLogger(EventManagerImpl.class);
	
	private EventDAO eventDAO;
	
	public EventDAO getEventDAO() {
		return eventDAO;
	}
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	@Override
	public EventVO saveEvent(EventVO eventVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveEvent starts");
		}
		EventVO event = eventDAO.getEventByName(eventVO.getEventName(), null,
				eventVO.getSchoolId());
		/*if(event != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}*/
		eventVO = eventDAO.saveEvent(eventVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveEvent ends");
		}
		return eventVO;
		
	}
	@Override
	public EventVO getEventById(String eventId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventById Starts");
		}
		
		EventVO eventVO = eventDAO.getEventById(eventId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventById Ends");
		}
		
		return eventVO;
	}
	
	@Override
	public Map<String, Object> getAllEventBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = eventDAO.getEventCountBySchoolId(schoolId);
		List<EventVO> eventVOs = eventDAO.getAllEventBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("eventVOs", eventVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getEventCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventCountBySchoolId Starts");
		}

		long cnt = eventDAO.getEventCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public EventVO updateEvent(EventVO eventVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateEvent Starts");
		}
		
		EventVO event = eventDAO.getEventByName(eventVO.getEventName(), eventVO.getId(),
				eventVO.getSchoolId());
		/*if(event != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}*/
		eventVO = eventDAO.updateEvent(eventVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateEvent Ends");
		}
		return eventVO;
	}
	
	@Override
	public boolean deleteEvent(String eventId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEvent Starts");
		}
		
		boolean isDeleted = eventDAO.deleteEvent(eventId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEvent Ends");
		}
		return isDeleted;
	}
	@Override
	public Map<String, Object> getEventsByFilter(FilterVO filterVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<EventVO> eventVOs = eventDAO.getEventsBySchoolIdAndDate(filterVO.getSchoolId(),
				filterVO.getEventStartDate(), filterVO.getEventEndDate());
		if (logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Ends");
		}		
		responseObjectsMap.put("eventVOs", eventVOs);
		return responseObjectsMap;
	}
	
}
