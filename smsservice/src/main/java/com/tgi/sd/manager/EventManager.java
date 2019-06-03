 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface EventManager {

	EventVO saveEvent(EventVO eventVO) throws SMSBusinessException;

	EventVO getEventById(String eventId) throws SMSBusinessException;

	EventVO updateEvent(EventVO eventVO) throws SMSBusinessException;

	boolean deleteEvent(String eventId) throws SMSBusinessException;

	long getEventCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllEventBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getEventsByFilter(FilterVO filterVO) throws SMSBusinessException;
	
	
}
