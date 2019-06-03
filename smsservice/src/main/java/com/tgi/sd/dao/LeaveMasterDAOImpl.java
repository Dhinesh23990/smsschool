package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class LeaveMasterDAOImpl extends GenericHibernateDAOImpl<LeaveMasterVO, String> implements LeaveMasterDAO {

	private static Logger logger = Logger.getLogger(LeaveMasterDAOImpl.class);

	@Override
	public LeaveMasterVO saveLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster() starts");
		}
		leaveMasterVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(leaveMasterVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveMaster() ends");
		}
		return leaveMasterVO;
	}
	
	@Override
	public LeaveMasterVO getLeaveMasterByName(String leaveMasterName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterByName Starts");
		}
		
		LeaveMasterVO leaveMasterVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from LeaveMasterVO WHERE leaveMasterName = :leaveMasterName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("leaveMasterName", leaveMasterName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<LeaveMasterVO> leaveList = query.list();
			
			if (leaveList != null && leaveList.size() > 0)
				leaveMasterVO = leaveList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getLeaveMasterByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return leaveMasterVO;
	}

	@Override
	public LeaveMasterVO getLeaveMasterById(String leaveMasterId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById() starts");
		}
		
		LeaveMasterVO leaveMasterVO = (LeaveMasterVO) super.getSession().get(LeaveMasterVO.class, leaveMasterId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById() ends");
		}
		return leaveMasterVO;
	}

	@Override
	public List<LeaveMasterVO> getAllLeaveMasterBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<LeaveMasterVO> leaveMasterLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from LeaveMasterVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			leaveMasterLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveMasterBySchoolId Ends");
		}
		return leaveMasterLst;

	}

	@Override
	public long getLeaveMasterCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getLeaveMasterCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from LeaveMasterVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public LeaveMasterVO updateLeaveMaster(LeaveMasterVO leaveMasterVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Starts");
		}
	
		super.getSession().saveOrUpdate(leaveMasterVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveMaster Ends");
		}
		return leaveMasterVO;
	}

	@Override
	public boolean deleteLeaveMaster(String leaveMasterId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			LeaveMasterVO leave = new LeaveMasterVO();
			leave.setId(leaveMasterId);
			session.delete(leave);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveMaster Ends");
		}
		return isDeleted;

	}

}
