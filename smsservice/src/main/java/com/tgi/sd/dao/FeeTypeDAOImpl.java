package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class FeeTypeDAOImpl extends GenericHibernateDAOImpl<FeeTypeVO, String> implements FeeTypeDAO {

	private static Logger logger = Logger.getLogger(FeeTypeDAOImpl.class);

	@Override
	public FeeTypeVO saveFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeType() starts");
		}
		feeTypeVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(feeTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeType() ends");
		}
		return feeTypeVO;
	}
	
	@Override
	public FeeTypeVO getFeeTypeByName(String feeTypeName, String id, String schoolId) throws SMSBusinessException {
		
		FeeTypeVO feeTypeVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeTypeVO WHERE feeType = :feeTypeName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("feeTypeName", feeTypeName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<FeeTypeVO> feeTypeList = query.list();
			
			if (feeTypeList != null && feeTypeList.size() > 0)
				feeTypeVO = feeTypeList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getFeeTypeByName Ends");
			}
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return feeTypeVO;
	}

	@Override
	public FeeTypeVO getFeeTypeById(String feeTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById() starts");
		}
		
		FeeTypeVO feeTypeVO =  (FeeTypeVO) super.getSession().get(FeeTypeVO.class, feeTypeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeById() ends");
		}
		return feeTypeVO;
	}

	@Override
	public List<FeeTypeVO> getAllFeeTypeBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<FeeTypeVO> feeTypeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeTypeVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			feeTypeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeTypeBySchoolId Ends");
		}
		return feeTypeLst;

	}

	@Override
	public long getFeeTypeCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		long count = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getFeeTypeCountBySchoolId Starts");
		}
	
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from FeeTypeVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeTypeCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public FeeTypeVO updateFeeType(FeeTypeVO feeTypeVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Starts");
		}
		
		super.getSession().saveOrUpdate(feeTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeType Ends");
		}
		return feeTypeVO;
	}

	@Override
	public boolean deleteFeeType(String feeTypeId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Starts");
		}
		boolean isDeleted = false;
		try {
			
			Session session = getSession();
			FeeTypeVO feetype = new FeeTypeVO();
			feetype.setId(feeTypeId);
			session.delete(feetype);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeType Ends");
		}
		return isDeleted;

	}

}
