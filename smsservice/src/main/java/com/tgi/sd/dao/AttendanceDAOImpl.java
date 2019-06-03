/**
 * 
 */
package com.tgi.sd.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
@Transactional
public class AttendanceDAOImpl extends GenericHibernateDAOImpl<AttendanceVO, String> implements AttendanceDAO {
	private static Logger logger = Logger.getLogger(AttendanceDAOImpl.class);

	public AttendanceVO saveAttendance(AttendanceVO attendance) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAttendance :::");
		}
		
		super.getSession().save(attendance);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAttendance :::" + attendance.getId());
		}
		return attendance;
	}

	public AttendanceVO getAttendance(String id) throws SMSBusinessException {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAttendance Id:::" + id);
		}
		
		AttendanceVO attendanceVO =  (AttendanceVO) super.getSession().get(AttendanceVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAttendance :::" + attendanceVO.getId());
		}
		return attendanceVO;
	}

	@Override
	public AttendanceVO updateAttendance(AttendanceVO attendance) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAttendance :::");
		}
	
		super.getSession().saveOrUpdate(attendance);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAttendance :::" + attendance.getId());
		}
		return attendance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttendanceVO> getAllAttendance() throws SMSBusinessException { 
		List<AttendanceVO> attendanceList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAttendance");
		}
		Long userOrderCount = 0L;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AttendanceVO");
			
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
		return attendanceList;	
	}


	@Override
	public Long getAttendanceCount() throws SMSBusinessException {
		List<AttendanceVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAttendanceCount");
		}
		Long userOrderCount = 0L;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AttendanceVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAttendanceCount");
		}
		return userOrderCount;	
	}
	
	
	@Override
	public List<AttendanceVO> getStudentsByStudentIdAndDate(String schoolId,String studentId,Date day,Date endDate) throws SMSBusinessException {
	/*
		Query query = new Query();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(day);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1;
		int dayOfmonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(null !=endDate) {
			GregorianCalendar calendarEnd = new GregorianCalendar();
			calendarEnd.setTime(endDate);
			int endYear = calendarEnd.get(Calendar.YEAR);
			int endMonth = calendarEnd.get(Calendar.MONTH) +1;
			int endDayOfmonth = calendarEnd.get(Calendar.DAY_OF_MONTH);
			query.addCriteria(Criteria.where("day").ne(null).andOperator(
		            Criteria.where("day").gte(getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)),
		            Criteria.where("day").lte(getDate(endYear+"-"+endMonth+"-"+endDayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))
		        ));
		} else {
			query.addCriteria(Criteria.where("day").ne(null).andOperator(
	            Criteria.where("day").gte(getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)),
	            Criteria.where("day").lte(getDate(year+"-"+month+"-"+dayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))
	        )); 
		}
		query.addCriteria(Criteria.where("schoolId").is(schoolId));
		query.addCriteria(Criteria.where("studentId").is(studentId));
		query.with(new Sort(new Order(Direction.ASC, SMSConstants.DAY)));
		List<AttendanceVO> attendanceList = (List<AttendanceVO>) findByQuery(query, AttendanceVO.class);
	 
		return attendanceList;
*/
		return null;
	}

	@Override
	public List<AttendanceVO> getAttendanceByClassAndSection(String schoolId,Date day ,String standard,String section,Date endDate) throws SMSBusinessException {
	
		List<AttendanceVO> attendanceList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getParentByMobileNo");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from AttendanceVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and day = :day");
			queryBuilder.append(" and standard = :standard");
			queryBuilder.append(" and section = :section");
			//queryBuilder.append(" and endDate = :endDate");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("day", day);
			query.setParameter("standard", standard);
			query.setParameter("section", section);
			//query.setParameter("endDate", endDate);
			
			attendanceList = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("getParentByMobileNo end");
			}

			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return attendanceList;
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

}
