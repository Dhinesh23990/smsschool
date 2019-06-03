package com.tgi.sd.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.StaffAttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class StaffAttendanceDAOImpl extends GenericHibernateDAOImpl<StaffAttendanceVO, String> implements StaffAttendanceDAO {
	private static Logger logger = Logger.getLogger(StaffAttendanceDAOImpl.class);

	public StaffAttendanceVO saveStaffAttendance(StaffAttendanceVO staffStaffAttendance) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStaffAttendance :::");
		}
		super.getSession().save(staffStaffAttendance);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
	}


	@Override
	public StaffAttendanceVO updateStaffAttendance(StaffAttendanceVO staffStaffAttendance) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStaffAttendance :::");
		}
		
		super.getSession().saveOrUpdate(staffStaffAttendance);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StaffAttendanceVO> getAllStaffAttendance() throws SMSBusinessException { 
		List<StaffAttendanceVO> staffStaffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStaffAttendance");
		}
		Long userOrderCount = 0L;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StaffAttendanceVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllAttendance");
		}
		return staffStaffAttendanceList;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getStaffAttendanceCount() throws SMSBusinessException {
		
		List<StaffAttendanceVO> staffStaffAttendanceList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStaffAttendanceCount");
		}
		Long userOrderCount = 0L;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StaffAttendanceVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStaffAttendanceCount");
		}
		return (long) staffStaffAttendanceList.size();	
	}

	@Override
	public List<StaffAttendanceVO> getStaffAttendanceByClassAndSection(String schoolId,Date day ,String staffId,String attendanceDayAfter,Date endDate) throws SMSBusinessException {
		
			List<StaffAttendanceVO> staffAttendanceList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStaffAttendanceByClassAndSection start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StaffAttendanceVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(staffId))
				queryBuilder.append(" and staffId = :staffId");
			if(StringUtils.isNotBlank(attendanceDayAfter))
				queryBuilder.append(" and attendanceDayAfter = :attendanceDayAfter");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(staffId))
				query.setParameter("staffId", staffId);
			if(StringUtils.isNotBlank(attendanceDayAfter))
				query.setParameter("attendanceDayAfter", attendanceDayAfter);
			
			staffAttendanceList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getStaffAttendanceByClassAndSection end");
		}

		return staffAttendanceList;
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
	public StaffAttendanceVO getStaffAttendance(String id) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStaffAttendance Id:::" + id);
		}
		
		StaffAttendanceVO staffStaffAttendance = (StaffAttendanceVO) super.getSession().get(StaffAttendanceVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStaffAttendance :::" + staffStaffAttendance.getId());
		}
		return staffStaffAttendance;
		
	}

}
