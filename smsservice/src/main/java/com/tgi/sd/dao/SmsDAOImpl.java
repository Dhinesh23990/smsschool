package com.tgi.sd.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.SmsHistoryVO;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.domain.SmsVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class SmsDAOImpl extends GenericHibernateDAOImpl<SmsVO, String> implements SmsDAO {
	private static Logger logger = Logger.getLogger(SmsDAOImpl.class);

	public SmsLogVO saveSmsLog(SmsLogVO smsLog) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveSmsLog :::");
		}
		
		super.getSession().save(smsLog);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveSmsLog :::" + smsLog.getId());
		}
		return smsLog;
	}


	@Override
	public List<SmsLogVO> getAllSmsLog(String schoolId,Integer pageIndex,Integer pageSize) throws SMSBusinessException { 
		
		List<SmsLogVO> smsLogList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsLog");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SmsLogVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			smsLogList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsLog");
		}
		
		return smsLogList;	
	}
	
	@Override
	public long getSmsLogCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		long count = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getSmsLogCountBySchoolId Starts");
		}
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SmsLogVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSmsLogCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public List<SmsLogVO> getSendTodaySmsCount(String schoolId,Date sendOn) throws SMSBusinessException {
	
		List<SmsLogVO> smsLogVOList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSendTodaySmsCount");
		}
		
		try {		
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SmsLogVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and sendOn = :sendOn");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("sendOn", sendOn);
			
			smsLogVOList = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("getSendTodaySmsCount end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return smsLogVOList;
	}
	
	
	 public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";        

	  public static Date getDate(String dateStr, String format) {
	        final DateFormat formatter = new SimpleDateFormat(format);
	        try {
	            return formatter.parse(dateStr);
	        } catch (ParseException e) {                
	            return null;
	        }
	    }
	
	
	@Override
	public SmsHistoryVO saveSmsHistory(SmsHistoryVO smsHistory) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start SmsHistoryVO :::");
		}
		
		super.getSession().save(smsHistory);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End SmsHistoryVO :::" + smsHistory.getId());
		}
		return smsHistory;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SmsHistoryVO> getAllSmsHistory(String smsLogId,String status,Integer pageIndex, Integer pageSize) throws SMSBusinessException {
		
		List<SmsHistoryVO> smsLogList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsHistory");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SmsHistoryVO WHERE smsLogId = :smsLog");
			queryBuilder.append(" and status = :status");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("smsLog", smsLogId);
			query.setParameter("status", status);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			smsLogList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsHistory");
		}
		return smsLogList;	
	}

	@Override
	public long getSmsHistoryBySchoolId(String smsLogId,String status) throws SMSBusinessException {
		
		long count = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getSmsHistoryBySchoolId Starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SmsHistoryVO WHERE smsLogId = :smsLogId");
			queryBuilder.append(" and status = :status");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("smsLogId", smsLogId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSmsHistoryBySchoolId Ends");
		}
		return count;
	}
}
