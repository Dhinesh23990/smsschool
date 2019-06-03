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
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.config.NationalityVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class NationalityDAOImpl extends GenericHibernateDAOImpl<NationalityVO, String> implements NationalityDAO {

	private static Logger logger = Logger.getLogger(NationalityDAOImpl.class);

	@Override
	public NationalityVO saveNationality(NationalityVO nationalityVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveNationality() starts");
		}
		nationalityVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(nationalityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveNationality() ends");
		}
		return nationalityVO;
	}
	
	@Override
	public NationalityVO getNationalityByName(String nationalityName,String id, String schoolId) throws SMSBusinessException {
		
		NationalityVO nationalityVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from NationalityVO WHERE nationalityName = :nationalityName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("nationalityName", nationalityName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<NationalityVO> nationalList = query.list();
			
			if (nationalList != null && nationalList.size() > 0)
				nationalityVO = nationalList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getNationalityByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return nationalityVO;
	}

	@Override
	public NationalityVO getNationalityById(String nationalityId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityById() starts");
		}
		NationalityVO nationalityVO =  (NationalityVO) super.getSession().get(NationalityVO.class, nationalityId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityById() ends");
		}
		return nationalityVO;
	}

	@Override
	public List<NationalityVO> getAllNationalityBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<NationalityVO> nationalList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from NationalityVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			nationalList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllNationalityBySchoolId Ends");
		}
		return nationalList;

	}

	@Override
	public long getNationalityCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getNationalityCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from NationalityVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getNationalityCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public NationalityVO updateNationality(NationalityVO nationalityVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateNationality Starts");
		}
		
		super.getSession().saveOrUpdate(nationalityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateNationality Ends");
		}
		return nationalityVO;
	}

	@Override
	public boolean deleteNationality(String nationalityId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteNationality Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			NationalityVO adminVO = new NationalityVO();
			adminVO.setId(nationalityId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteNationality Ends");
		}
		return isDeleted;

	}

}
