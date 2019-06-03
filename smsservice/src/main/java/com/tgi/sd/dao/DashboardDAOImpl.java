package com.tgi.sd.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.SmsVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class DashboardDAOImpl extends GenericHibernateDAOImpl<StudentVO, String> implements DashboardDAO {

	private static Logger logger = Logger.getLogger(DashboardDAOImpl.class);

	@Override
	public long getAllStudentCount(String schoolId) throws SMSBusinessException {

		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentCount Starts");
		}
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentCount ends");
		}
		return count;
	}
	
	
	@Override
	public long getAllTeacherCount(String schoolId) throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllTeacherCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from TeacherVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllTeacherCount ends");
		}
		return count;
	}
	
	@Override
	public long getAllAdminCount(String schoolId) throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdminCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdminCount ends");
		}
		return count;
	}

	@Override
	public long getAllParentCount() throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllParentCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ParentVO");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllParentCount ends");
		}
		return count;
	}
	
	@Override
	public long getAllGenderCount(String genderId, String schoolId) throws SMSBusinessException{
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from GenderVO WHERE Id = :genderId");
			queryBuilder.append(" and schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("genderId", genderId);
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderCount ends");
		}
		return count;
	}
	
	@Override
	public long getAllActiveSchoolCount(String status) throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveSchoolCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SchoolVO WHERE status = :status");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("status", status);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllActiveSchoolCount ends");
		}
		return count;
	}
	
	@Override
	public long getAllSchoolCount() throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchoolCount starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SchoolVO");
			
			Query query = session.createQuery(queryBuilder.toString());			
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderCount ends");
		}
		return count;
	}

	@Override
	public long getAllBoysCountByClassSection(String classId,String sectionId,String genderId,String schoolId) throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBoysCountByClassSection starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBoysCountByClassSection ends");
		}
		return count;
	}
	
	public List<SmsVO> getAllSmsCount()  throws SMSBusinessException {
		
		List<SmsVO> smsList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSmsCount starts");
		}
		int userOrderCount = 0;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SmsVO");
			
			Query query = session.createQuery(queryBuilder.toString());		
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSmsCount ends");
		}
		return smsList;
	}
}
