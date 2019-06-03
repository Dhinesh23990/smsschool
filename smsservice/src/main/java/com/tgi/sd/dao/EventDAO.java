package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.EventVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface EventDAO {

	EventVO saveEvent(EventVO eventVO) throws SMSBusinessException;

	EventVO getEventById(String eventId) throws SMSBusinessException;

	List<EventVO> getAllEventBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getEventCountBySchoolId(String schoolId) throws SMSBusinessException;

	EventVO updateEvent(EventVO eventVO) throws SMSBusinessException;

	boolean deleteEvent(String eventId) throws SMSBusinessException;

	EventVO getEventByName(String eventName, String id, String schoolId)
			throws SMSBusinessException;

	List<EventVO> getEventsBySchoolIdAndDate(String schoolId, Date startDate, Date endDate) throws SMSBusinessException;

}
