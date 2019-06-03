package com.tgi.sd.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.NotificationVO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtility;

@Transactional
public class NotificationDAOImpl extends GenericHibernateDAOImpl<NotificationVO, String>  implements NotificationDAO {

	private static Logger logger = Logger.getLogger(NotificationDAOImpl.class);

	@Override
	public NotificationVO saveNotification(NotificationVO notificationVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveNotification() starts");
		}
		notificationVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(notificationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveNotification() ends");
		}
		return notificationVO;
	}
	
	@Override
	public NotificationVO getNotificationByName(String notificationName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationByName Starts");
		}
		
		NotificationVO notificationVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from NotificationVO WHERE notificationName = :notificationName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("notificationName", notificationName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<NotificationVO> notifiList = query.list();
			
			if (notifiList != null && notifiList.size() > 0)
				notificationVO = notifiList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationByName Ends");
		}
		return notificationVO;
	}

	@Override
	public NotificationVO getNotificationById(String notificationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationById() starts");
		}
		NotificationVO notificationVO =  (NotificationVO) super.getSession().get(NotificationVO.class, notificationId);		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationById() ends");
		}
		return notificationVO;
	}

	@Override
	public List<NotificationVO> getAllNotificationBySchoolId(String schoolId,List<String> studentIds, List<String> classIds, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Starts");
		}
		List<NotificationVO> notificationLst = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from EducationalVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			notificationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNotificationBySchoolId Ends");
		}
		return notificationLst;

	}

	@Override
	public long getNotificationCountBySchoolId(String schoolId,List<String> studentIds, List<String> classIds) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getNotificationCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from NotificationVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getNotificationCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public NotificationVO updateNotification(NotificationVO notificationVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateNotification Starts");
		}
		super.getSession().saveOrUpdate(notificationVO);
		
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
		boolean isDeleted = false;
		try {
			Session session = getSession();
			NotificationVO adminVO = new NotificationVO();
			adminVO.setId(notificationId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNotification Ends");
		}
		return isDeleted;

	}

/*
	public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";
	@Override
	public List<NotificationVO> getNotificationsBySchoolIdAndDate(String schoolId,Date startDate,Date endDate) throws SMSBusinessException {
		Query query = new Query();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1;
		int dayOfmonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(null !=endDate) {
			GregorianCalendar calendarEnd = new GregorianCalendar();
			calendarEnd.setTime(endDate);
			int endYear = calendarEnd.get(Calendar.YEAR);
			int endMonth = calendarEnd.get(Calendar.MONTH) +1;
			int endDayOfmonth = calendarEnd.get(Calendar.DAY_OF_MONTH);
			query.addCriteria(Criteria.where("notificationStartDate").ne(null).orOperator(
		            Criteria.where("notificationStartDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)).andOperator(Criteria.where("notificationStartDate").lte(SMSUtility.getDate(endYear+"-"+endMonth+"-"+endDayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))),
		            Criteria.where("notificationEndDate").lte(SMSUtility.getDate(endYear+"-"+endMonth+"-"+endDayOfmonth+" 23:59:59", DB_FORMAT_DATETIME)).andOperator(Criteria.where("notificationEndDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)))
		        ));
		} else {
			query.addCriteria(Criteria.where("notificationStartDate").ne(null).andOperator(
	            Criteria.where("notificationStartDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)),
	            Criteria.where("notificationStartDate").lte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))
	        )); 
		}
		query.addCriteria(Criteria.where("schoolId").is(schoolId));
		query.with(new Sort(new Order(Direction.ASC, "notificationStartDate")));
		List<NotificationVO> notificationVOList = (List<NotificationVO>) findByQuery(query, NotificationVO.class);
	 
		return notificationVOList;
	}
*/
	@Override
	public long getNotifiCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getNotifiCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from NotificationVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getNotifiCountBySchoolId Ends");
		}
		return userOrderCount;
	}
	

	@Override
	public List<NotificationVO> getAllNotifiBySchoolId(String schoolId, Integer pageIndex, Integer pageSize)
			throws SMSBusinessException {
		
			List<NotificationVO> notifiList = null;
		
			if (logger.isDebugEnabled()) {
				logger.debug("getAllNotifiBySchoolId Starts");
			}
			try {
				Session session = getSession();
				StringBuilder queryBuilder = new StringBuilder();
				queryBuilder.append("from NotificationVO WHERE schoolId = :schId");
				queryBuilder.append(" ORDER by createdDate DESC");
				Query query = session.createQuery(queryBuilder.toString());
				query.setParameter("schId", schoolId);

				query.setFirstResult((pageIndex - 1) * pageSize);
				query.setMaxResults(pageSize);

				notifiList = query.list();

			} catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("getAllNotifiBySchoolId Ends");
			}
			return notifiList;

		}

	@Override
	public List<NotificationVO> getNotificationsBySchoolIdAndDate(String schoolId, Date startDate, Date endDate)
			throws SMSBusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
