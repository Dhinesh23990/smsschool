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
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional

public class BatchDAOImpl extends GenericHibernateDAOImpl<BatchVO, String>  implements BatchDAO {

	private static Logger logger = Logger.getLogger(BatchDAOImpl.class);

	@Override
	public BatchVO saveBatch(BatchVO batchVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatch() starts");
		}
		batchVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(batchVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatch() ends");
		}
		return batchVO;
	}

	@Override
	public BatchVO getBatchByName(String BatchName,String id, String schoolId, String batchCode) throws SMSBusinessException {
		
		BatchVO batchVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BatchVO WHERE batchName=:BatchName and batchCode=:batchCode");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("BatchName", BatchName);
			query.setParameter("batchCode", batchCode);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<BatchVO> batchList = query.list();
			
			if (batchList != null && batchList.size() > 0)
				batchVO = batchList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getBatchByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return batchVO;
	}
	
	@Override
	public BatchVO getBatchByBatchName(String batchCode,String schoolId) throws SMSBusinessException {
		
		BatchVO batchVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchByBatchName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BatchVO WHERE batchCode = :batchCode");
			queryBuilder.append(" and schoolId = :schoolId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("batchCode", batchCode);
			query.setParameter("schoolId", schoolId);
			
			List<BatchVO> batchList = query.list();
			
			if (batchList != null && batchList.size() > 0)
				batchVO = batchList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCityByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return batchVO;
	}
	
	@Override
	public BatchVO getBatchById(String batchId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchById() starts");
		}
		
		BatchVO batchVO =  (BatchVO) super.getSession().get(BatchVO.class, batchId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchById() ends");
		}
		return batchVO;
	}

	@Override
	public List<BatchVO> getAllBatchBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<BatchVO> batchList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BatchVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			batchList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Ends");
		}
		return batchList;

	}

	@Override
	public long getBatchCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getBatchCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from BatchVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public BatchVO updateBatch(BatchVO batchVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateBatch Starts");
		}
		super.getSession().saveOrUpdate(batchVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatch Ends");
		}
		return batchVO;
	}

	@Override
	public boolean deleteBatch(String batchId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatch Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			BatchVO adminVO = new BatchVO();
			adminVO.setId(batchId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}isDeleted = true;
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatch Ends");
		}
		return isDeleted;

	}

}
