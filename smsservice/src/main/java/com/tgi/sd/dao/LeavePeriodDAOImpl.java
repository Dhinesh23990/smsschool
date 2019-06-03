package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class LeavePeriodDAOImpl extends GenericHibernateDAOImpl<LeavePeriodVO, String> implements LeavePeriodDAO {

	private static Logger logger = Logger.getLogger(LeavePeriodDAOImpl.class);

	@Override
	public LeavePeriodVO saveLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod() starts");
		}
		leavePeriodVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(leavePeriodVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeavePeriod() ends");
		}
		return leavePeriodVO;
	}
	
	@Override
	public LeavePeriodVO getLeavePeriodByName(String leavePeriodName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodByName Starts");
		}
		
		LeavePeriodVO leavePeriodVO = null;
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from LeavePeriodVO WHERE leavePeriodName = :leavePeriodName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("leavePeriodName", leavePeriodName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<LeavePeriodVO> leaveList = query.list();
			
			if (leaveList != null && leaveList.size() > 0)
				leavePeriodVO = leaveList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getLeaveMasterByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodByName Ends");
		}
		return leavePeriodVO;
	}

	@Override
	public LeavePeriodVO getLeavePeriodById(String leavePeriodId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById() starts");
		}
		LeavePeriodVO leavePeriodVO = (LeavePeriodVO) super.getSession().get(LeavePeriodVO.class, leavePeriodId);
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodById() ends");
		}
		return leavePeriodVO;
	}

	@Override
	public List<LeavePeriodVO> getAllLeavePeriodBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<LeavePeriodVO> leavePeriodLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from LeavePeriodVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			leavePeriodLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodBySchoolId Ends");
		}
		return leavePeriodLst;

	}
	
	@Override
	public List<LeavePeriodVO> getAllLeavePeriodByMasterId(String schoolId,
			String leaveMasterId) throws SMSBusinessException {
	
		List<LeavePeriodVO> leavePeriodVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeavePeriodByMasterId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from LeavePeriodVO WHERE leaveMasterId = :leaveMasterId");
			queryBuilder.append(" and schoolId = :schoolId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("leaveMasterId", leaveMasterId);
			query.setParameter("schoolId", schoolId);
			
			
			List<LeavePeriodVO> leaveList = query.list();
			
			if (leaveList != null && leaveList.size() > 0)
				leavePeriodVO = (List<LeavePeriodVO>) leaveList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getAllLeavePeriodByMasterId Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return leavePeriodVO;

	}

	@Override
	public long getLeavePeriodCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getLeavePeriodCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from LeavePeriodVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLeavePeriodCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public LeavePeriodVO updateLeavePeriod(LeavePeriodVO leavePeriodVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Starts");
		}
		
		super.getSession().saveOrUpdate(leavePeriodVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeavePeriod Ends");
		}
		return leavePeriodVO;
	}

	@Override
	public boolean deleteLeavePeriod(String leavePeriodId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			LeavePeriodVO leave = new LeavePeriodVO();
			leave.setId(leavePeriodId);
			session.delete(leave);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeavePeriod Ends");
		}
		return isDeleted;

	}

}
