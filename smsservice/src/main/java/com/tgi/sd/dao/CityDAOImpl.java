package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class CityDAOImpl extends GenericHibernateDAOImpl<CityVO, String>  implements CityDAO {

	private static Logger logger = Logger.getLogger(CityDAOImpl.class);

	@Override
	public CityVO saveCity(CityVO cityVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCity() starts");
		}
		cityVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(cityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCity() ends");
		}
		return cityVO;
	}
	
	@Override
	public CityVO getCityByName(String cityName,String id, String schoolId) throws SMSBusinessException {
		
		CityVO city = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCityByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CityVO WHERE cityName = :cityName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("cityName", cityName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<CityVO> cityList = query.list();
			
			if (cityList != null && cityList.size() > 0)
				city = cityList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCityByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return city;
	}

	@Override
	public CityVO getCityById(String cityId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCityById() starts");
		}
	
		CityVO cityVO =  (CityVO) super.getSession().get(CityVO.class, cityId);
	
		if (logger.isDebugEnabled()) {
			logger.debug("getCityById() ends");
		}
		return cityVO;
	}

	@Override
	public List<CityVO> getAllCityBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {
		List<CityVO> cityList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCityBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CityVO WHERE schoolId = :schId");

			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			cityList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllCityBySchoolId");
		}
		return cityList;	
	}

	@Override
	public long getCityCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getCityCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from CityVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCityCountBySchoolId Ends");
		}
		return userOrderCount;
	}
	
	@Override
	public List<CityVO> getAllCityByStateCountryName(String countryName, String stateName) throws SMSBusinessException {

		List<CityVO> cityList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from CityVO where countryId = :countryName");
			queryBuilder.append(" and stateId = :stateName");
			queryBuilder.append(" ORDER by cityName ASC");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("countryName", countryName);
			query.setParameter("stateName", stateName);
			
			cityList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
			if (logger.isDebugEnabled()) {
			logger.debug("getAllCityByStateCountryName Ends");
		}
		return cityList;

	}

	@Override
	public CityVO updateCity(CityVO cityVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCity Starts");
		}
		
		super.getSession().saveOrUpdate(cityVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCity Ends");
		}
		return cityVO;
	}

	@Override
	public boolean deleteCity(String cityId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCity Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			CityVO adminVO = new CityVO();
			adminVO.setId(cityId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCity Ends");
		}
		return isDeleted;

	}

}
