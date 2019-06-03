package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class EventDAOImpl extends GenericHibernateDAOImpl<EventVO, String> implements EventDAO {

	private static Logger logger = Logger.getLogger(EventDAOImpl.class);

	@Override
	public EventVO saveEvent(EventVO eventVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveEvent() starts");
		}
		eventVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(eventVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveEvent() ends");
		}
		return eventVO;
	}
	
	@Override
	public EventVO getEventByName(String eventName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventByName Starts");
		}
		EventVO eventVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from EventVO WHERE eventName = :eventName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("eventName", eventName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<EventVO> eventList = query.list();
			
			if (eventList != null && eventList.size() > 0)
				eventVO = eventList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventByName Ends");
		}
		return eventVO;
	}

	@Override
	public EventVO getEventById(String eventId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventById() starts");
		}
		
		EventVO eventVO = (EventVO) super.getSession().get(EventVO.class, eventId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventById() ends");
		}
		return eventVO;
	}

	@Override
	public List<EventVO> getAllEventBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<EventVO> eventLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from EventVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			eventLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEventBySchoolId Ends");
		}
		return eventLst;

	}

	@Override
	public long getEventCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getEventCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from EventVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public EventVO updateEvent(EventVO eventVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateEvent Starts");
		}
		
		super.getSession().saveOrUpdate(eventVO);
		
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
		boolean isDeleted = false;
		try {
			Session session = getSession();
			EventVO adminVO = new EventVO();
			adminVO.setId(eventId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEvent Ends");
		}
		return isDeleted;

	}

	public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";
	@Override
	public List<EventVO> getEventsBySchoolIdAndDate(String schoolId,Date startDate,Date endDate) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getEventsBySchoolIdAndDate end");
		}
		
		List<EventVO> eventVOList = null;
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from EventVO WHERE schoolId = :schId");
			queryBuilder.append(" and startDate = :startDate");
			queryBuilder.append(" and endDate = :endDate");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
			
			List<EventVO> eventList = query.list();
			
			if (eventList != null && eventList.size() > 0)
				eventVOList = (List<EventVO>) eventList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getEventsBySchoolIdAndDate end");
			}
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}	
		
		return eventVOList;
	}
}
