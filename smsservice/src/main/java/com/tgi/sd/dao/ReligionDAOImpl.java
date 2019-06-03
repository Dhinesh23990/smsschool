package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class ReligionDAOImpl extends GenericHibernateDAOImpl<ReligionVO, String> implements ReligionDAO {

	private static Logger logger = Logger.getLogger(ReligionDAOImpl.class);

	@Override
	public ReligionVO saveReligion(ReligionVO religionVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveReligion() starts");
		}
		religionVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(religionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveReligion() ends");
		}
		return religionVO;
	}

	@Override
	public ReligionVO getReligionByName(String religionName,String id, String schoolId) throws SMSBusinessException {
		
		ReligionVO religionVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ReligionVO WHERE religionName = :religionName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("religionName", religionName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<ReligionVO> religionList = query.list();
			
			if (religionList != null && religionList.size() > 0)
				religionVO = religionList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getReligionByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return religionVO;
	}
	
	@Override
	public ReligionVO getReligionById(String religionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionById() starts");
		}
		
		ReligionVO religionVO =  (ReligionVO) super.getSession().get(ReligionVO.class, religionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionById() ends");
		}
		return religionVO;
	}

	@Override
	public List<ReligionVO> getAllReligionBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<ReligionVO> religionLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ReligionVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			religionLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Ends");
		}
		return religionLst;

	}

	@Override
	public long getReligionCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ReligionVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getReligionCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public ReligionVO updateReligion(ReligionVO religionVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateReligion Starts");
		}
		
		super.getSession().saveOrUpdate(religionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateReligion Ends");
		}
		return religionVO;
	}

	@Override
	public boolean deleteReligion(String religionId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteReligion Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			ReligionVO adminVO = new ReligionVO();
			adminVO.setId(religionId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteReligion Ends");
		}
		return isDeleted;

	}

}
