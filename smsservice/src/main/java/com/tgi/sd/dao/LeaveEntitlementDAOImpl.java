package com.tgi.sd.dao;

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
import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class LeaveEntitlementDAOImpl extends GenericHibernateDAOImpl<LeaveEntitlementVO, String> implements LeaveEntitlementDAO {

	private static Logger logger = Logger.getLogger(LeaveEntitlementDAOImpl.class);

	@Override
	public LeaveEntitlementVO saveLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement() starts");
		}
		leaveEntitlementVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(leaveEntitlementVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement() ends");
		}
		return leaveEntitlementVO;
	}
	
	@Override
	public LeaveEntitlementVO isLeavePeriodAssignedForStaff(String leavePeriodId, String id, String schoolId, String staffId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementByName Starts");
		}
		
		LeaveEntitlementVO leaveEntitlementVO = null;
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from LeaveEntitlementVO WHERE leavePeriodId = :leavePeriodId");
			queryBuilder.append(" and staffId = :staffId");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("leavePeriodId", leavePeriodId);
			query.setParameter("staffId", staffId);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<LeaveEntitlementVO> leaveEntitlementList = query.list();
			
			if (leaveEntitlementList != null && leaveEntitlementList.size() > 0)
				leaveEntitlementVO = leaveEntitlementList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getLeaveEntitlementByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return leaveEntitlementVO;
	}

	@Override
	public LeaveEntitlementVO getLeaveEntitlementById(String leaveEntitlementId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById() starts");
		}
		
		LeaveEntitlementVO leaveEntitlementVO = (LeaveEntitlementVO) super.getSession().get(LeaveEntitlementVO.class, leaveEntitlementId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById() ends");
		}
		return leaveEntitlementVO;
	}

	@Override
	public List<LeaveEntitlementVO> getAllLeaveEntitlementBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<LeaveEntitlementVO> leaveEntitlementLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from LeaveEntitlementVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			leaveEntitlementLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Ends");
		}
		return leaveEntitlementLst;

	}
	
	@Override
	public List<LeaveEntitlementVO> getAllLeaveEntitlementBySchoolIdAndStaffId(String schoolId, String id) throws SMSBusinessException {

		List<LeaveEntitlementVO> leaveEntitlementVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolIdAndStaffId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from LeaveEntitlementVO WHERE schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			
			if(id != null)
				query.setParameter("Id", id);
			
			List<LeaveEntitlementVO> leaveEntitlementList = query.list();
			
			if (leaveEntitlementList != null && leaveEntitlementList.size() > 0)
				leaveEntitlementVO = (List<LeaveEntitlementVO>) leaveEntitlementList.get(0);

			leaveEntitlementList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolIdAndStaffId Ends");
		}
		return leaveEntitlementVO;

	}

	@Override
	public long getLeaveEntitlementCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getLeaveEntitlementCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from LeaveEntitlementVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public LeaveEntitlementVO updateLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Starts");
		}
	
		super.getSession().saveOrUpdate(leaveEntitlementVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Ends");
		}
		return leaveEntitlementVO;
	}

	@Override
	public boolean deleteLeaveEntitlement(String leaveEntitlementId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			LeaveEntitlementVO leaveEntitle = new LeaveEntitlementVO();
			leaveEntitle.setId(leaveEntitlementId);
			session.delete(leaveEntitle);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Ends");
		}
		return isDeleted;

	}

}
