package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class FeeComponentDAOImpl extends GenericHibernateDAOImpl<FeeComponentVO, String> implements FeeComponentDAO {

	private static Logger logger = Logger.getLogger(FeeComponentDAOImpl.class);

	@Override
	public FeeComponentVO saveFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent() starts");
		}
		feeComponentVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(feeComponentVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeComponent() ends");
		}
		return feeComponentVO;
	}
	
	@Override
	public FeeComponentVO getFeeComponentByName(String feeComponentName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentByName Starts");
		}
		FeeComponentVO feeComponentVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeComponentVO WHERE feeComponent = :feeComponentName");
			queryBuilder.append(" and schoolId = :schId");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("feeComponentName", feeComponentName);
			query.setParameter("schId", schoolId);
			if (id!=null)
				query.setParameter("id", id);
			
			List<FeeComponentVO> feecompList = query.list();
			
			if (feecompList != null && feecompList.size() > 0)
				feeComponentVO = feecompList.get(0);

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentByName Ends");
		}
		return feeComponentVO;
	}

	@Override
	public FeeComponentVO getFeeComponentById(String feeComponentId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById() starts");
		}
		FeeComponentVO feeComponentVO =  (FeeComponentVO) super.getSession().get(FeeComponentVO.class, feeComponentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentById() ends");
		}
		return feeComponentVO;
	}

	@Override
	public List<FeeComponentVO> getAllFeeComponentBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<FeeComponentVO> feeComponentLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeComponentVO WHERE schoolId = :schId");

			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			feeComponentLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeComponentBySchoolId Ends");
		}
		return feeComponentLst;

	}

	
	@Override
	public long getFeeComponentCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		int userOrderCount = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getFeeComponentCountBySchoolId Starts");
		}
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from FeeComponentVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeComponentCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public FeeComponentVO updateFeeComponent(FeeComponentVO feeComponentVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Starts");
		}
	
		super.getSession().saveOrUpdate(feeComponentVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeComponent Ends");
		}
		return feeComponentVO;
	}

	@Override
	public boolean deleteFeeComponent(String feeComponentId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			FeeComponentVO feecom = new FeeComponentVO();
			feecom.setId(feeComponentId);
			session.delete(feecom);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeComponent Ends");
		}
		return isDeleted;

	}

}
