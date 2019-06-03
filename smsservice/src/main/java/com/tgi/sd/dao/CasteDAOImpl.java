package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class CasteDAOImpl extends GenericHibernateDAOImpl<CasteVO, String> implements CasteDAO {

	private static Logger logger = Logger.getLogger(CasteDAOImpl.class);

	@Override
	public CasteVO saveCaste(CasteVO casteVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveCaste() starts");
		}
		casteVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(casteVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCaste() ends");
		}
		return casteVO;
	}
	
	@Override
	public CasteVO getCasteByName(String casteName, String id, String schoolId) throws SMSBusinessException {
		
		CasteVO casteVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CasteVO WHERE casteName = :casteName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("casteName", casteName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<CasteVO> casteList = query.list();
			
			if (casteList != null && casteList.size() > 0)
				casteVO = casteList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return casteVO;
	}

	@Override
	public CasteVO getCasteById(String casteId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteById() starts");
		}
		CasteVO casteVO =  (CasteVO) super.getSession().get(CasteVO.class, casteId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteById() ends");
		}
		return casteVO;
	}

	@Override
	public List<CasteVO> getAllCasteBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<CasteVO> casteList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from CasteVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo >0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			casteList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Ends");
		}
		return casteList;

	}

	@Override
	public long getCasteCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from CasteVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getCasteCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public CasteVO updateCaste(CasteVO casteVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCaste Starts");
		}
		super.getSession().saveOrUpdate(casteVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCaste Ends");
		}
		return casteVO;
	}

	@Override
	public boolean deleteCaste(String casteId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCaste Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			CasteVO adminVO = new CasteVO();
			adminVO.setId(casteId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCaste Ends");
		}
		return isDeleted;

	}

}
