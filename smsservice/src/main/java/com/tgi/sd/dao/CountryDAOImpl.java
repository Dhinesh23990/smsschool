package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class CountryDAOImpl extends GenericHibernateDAOImpl<CountryVO, String> implements CountryDAO {

	private static Logger logger = Logger.getLogger(CountryDAOImpl.class);

	@Override
	public CountryVO saveCountry(CountryVO countryVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveCountry() starts");
		}
		countryVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(countryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCountry() ends");
		}
		return countryVO;
	}
	
	@Override
	public CountryVO getCountryByName(String countryName,String id, String schoolId) throws SMSBusinessException {
		
		CountryVO countryVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CountryVO WHERE countryName = :countryName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("countryName", countryName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<CountryVO> countryList = query.list();
			
			if (countryList != null && countryList.size() > 0)
				countryVO = countryList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCountryByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return countryVO;
	}	

	@Override
	public CountryVO getCountryById(String countryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryById() starts");
		}
		
		CountryVO countryVO =  (CountryVO) super.getSession().get(CountryVO.class, countryId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryById() ends");
		}
		return countryVO;
	}

	@Override
	public List<CountryVO> getAllCountryBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<CountryVO> countryList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCountryBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CountryVO WHERE schoolId = :schId");

			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			countryList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllCountryBySchoolId ends");
		}
		
		return countryList;

	}

	@Override
	public long getCountryCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		int userOrderCount = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getCountryCountBySchoolId Starts");
		}
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from CountryVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCountryCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	
	@Override
	public CountryVO updateCountry(CountryVO countryVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCountry Starts");
		}
		
		super.getSession().saveOrUpdate(countryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCountry Ends");
		}
		return countryVO;
	}

	@Override
	public boolean deleteCountry(String countryId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCountry Starts");
		}
		
		boolean isDeleted= false;
		try {
			Session session = getSession();
			CountryVO adminVO = new CountryVO();
			adminVO.setId(countryId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCountry Ends");
		}
		return isDeleted;

	}

}
