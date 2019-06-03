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
import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class AdminDAOImpl extends GenericHibernateDAOImpl<AdminVO, String> implements AdminDAO {
	private static Logger logger = Logger.getLogger(AdminDAOImpl.class);

	public AdminVO saveAdmin(AdminVO admin) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAdmin");
		}
		
		super.getSession().save(admin);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAdmin");
		}
		return admin;
	}

	public AdminVO getAdmin(String id) throws SMSBusinessException {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAdmin");
		}
		
		AdminVO admin =  (AdminVO) super.getSession().get(AdminVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAdmin");
		}
		return admin;
	}

	@Override
	public AdminVO updateAdmin(AdminVO admin) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAdmin :::");
		}
		
		super.getSession().saveOrUpdate(admin);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAdmin :::" );
		}
		return admin;
	}
	
	@Override
	public long getAdminCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getAdminCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAdminCountBySchoolId ends");
		}
		
		return userOrderCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminVO> getAllAdmin(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException { 
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAdmin");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from AdminVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageIndex != null && pageIndex > 0) {
				query.setFirstResult((pageIndex - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			adminList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdmin ends");
		}
		
		
		return adminList;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getAdminCount() throws SMSBusinessException {
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAdminCount");
		}
		Long userOrderCount = 0L;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAdminCount ends");
		}
		
		
		return userOrderCount;
	}

	@Override
	public AdminVO getAdminByUserOid(String userOid) throws SMSBusinessException {	
		if (logger.isDebugEnabled()) {
			logger.debug("getAdminByUserOid() start");
		}
		
		AdminVO adminVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from AdminVO WHERE userId = :userOid");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userOid", userOid);
			
			List<AdminVO> adminList = query.list();
			
			if (adminList != null && adminList.size() > 0)
				adminVO = adminList.get(0);


		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAdminByUserOid() end");
		}
		
		return adminVO;
	}
	

	
	@Override
	public List<AdminVO> getAllAdminsByIds(List<String> ids) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdminsByIds Start");
		}
		
		List<AdminVO> studntList = null;
		try {
			Session session = getSession();
			StringBuilder queryString = new StringBuilder();
			queryString
					.append("from AdminVO WHERE Id in (:ids)");
			Query query = session.createQuery(queryString.toString());
			query.setParameter("ids", ids);

			studntList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdminsByIds end");
		}
		
		return studntList;
	}
	
	@Override
	public long getAllAdminCountByDateOfJoiningYear(Date dateOfJoining, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("OrderDAO getAllAdminCountByDateOfJoiningYear start");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO WHERE schoolId = :schId");
			queryBuilder.append(" and dateOfJoining = :dateOfJoining");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("dateOfJoining", dateOfJoining);
			
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}	
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllAdminCountByDateOfJoiningYear end");
		}
		
		return userOrderCount;
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
	public long getAdminCountByName(String adminName, String surName, String schoolId, String id) throws SMSBusinessException {
		long currentMilli = 0;
		if(logger.isDebugEnabled()){
				logger.debug("getAdminCountByName start");
		}

		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO WHERE schoolId = :schId");
			queryBuilder.append(" and firstName like :firstNm");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			if(surName != null)
				queryBuilder.append(" and initial like :surName");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("firstNm", adminName+"%");
			if (id!=null)
				query.setParameter("id", id);
			if(surName != null)
				query.setParameter("surName", surName);
			
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}	
		
		if(logger.isDebugEnabled()){
			logger.debug("getAdminCountByName end");
		}
		
		return userOrderCount;
	}
	
	@Override
	public long getAdminCountByUserName(String userName, String schoolId, String id) throws SMSBusinessException {
		long currentMilli = 0;
		if(logger.isDebugEnabled()){
			logger.debug("getAdminCountByUserName start");
		}

		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from AdminVO WHERE schoolId = :schId");
			queryBuilder.append(" and userName like :userName");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("userName", userName+"%");
			if (id!=null)
				query.setParameter("id", id);
			
			
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;


		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}	
		
		if(logger.isDebugEnabled()){
			logger.debug("getAdminCountByUserName end");
		}
		
		return userOrderCount;
	}
	
	
	@Override
	public AdminVO getAdminEmployeeNumber(String employeeNumber, String schoolId, String id) throws SMSBusinessException {	
		if(logger.isDebugEnabled()){
			logger.debug("getAdminEmployeeNumber start");
		}
		
		AdminVO adminVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from AdminVO WHERE employeeNumber = :empNo");
			queryBuilder.append(" and schoolId = :schId");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("empNo", employeeNumber);
			query.setParameter("schId", schoolId);
			if (id!=null)
				query.setParameter("id", id);
			
			List<AdminVO> adminList = query.list();
			
			if (adminList != null && adminList.size() > 0)
				adminVO = adminList.get(0);

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("AdminVO getAdminEmployeeNumber ends");
		}
		
		return adminVO;
	}
	
	@Override
	public AdminVO getAdminEmailId(String emailId, String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("AdminVO getAdminEmailId start");
		}
		
		AdminVO adminVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from AdminVO WHERE emailId = :emailId");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("emailId", emailId);
			if (id!=null)
				query.setParameter("id", id);
			
			List<AdminVO> adminList = query.list();
			
			if (adminList != null && adminList.size() > 0)
				adminVO = adminList.get(0);

			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("AdminVO getAdminEmailId end");
		}
		
		return adminVO;
	}
	
	
	@Override
	public boolean deleteAdmin(String adminId) throws SMSBusinessException  {
		if (logger.isDebugEnabled()) {
			logger.debug("delete ordreVO  starts");
		}
		boolean isDeleted= false;
		try {
			Session session = getSession();
			AdminVO adminVO = new AdminVO();
			adminVO.setId(adminId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("delete ordreVO end");
		}
		return isDeleted;

	}
}
