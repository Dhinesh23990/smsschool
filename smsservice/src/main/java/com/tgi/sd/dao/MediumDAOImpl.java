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
import com.tgi.sd.domain.config.MediumVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class MediumDAOImpl extends GenericHibernateDAOImpl<MediumVO, String>  implements MediumDAO {

	private static Logger logger = Logger.getLogger(MediumDAOImpl.class);

	@Override
	public MediumVO saveMedium(MediumVO mediumVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveMedium() starts");
		}
		mediumVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(mediumVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveMedium() ends");
		}
		return mediumVO;
	}
	
	@Override
	public MediumVO getMediumByName(String mediumName,String id, String schoolId) throws SMSBusinessException {
		
		MediumVO mediumVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MediumVO WHERE mediumName = :mediumName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("mediumName", mediumName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<MediumVO> mediumList = query.list();
			
			if (mediumList != null && mediumList.size() > 0)
				mediumVO = mediumList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getMediumByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return mediumVO;
	}
	
	
	@Override
	public MediumVO getMediumByMediumName(String mediumName, String schoolId) throws SMSBusinessException {
		
		MediumVO mediumVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MediumVO WHERE mediumName = :mediumName");
			queryBuilder.append(" and schoolId = :schoolId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("mediumName", mediumName);
			query.setParameter("schoolId", schoolId);
			
			List<MediumVO> mediumList = query.list();
			
			if (mediumList != null && mediumList.size() > 0)
				mediumVO = mediumList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getMediumByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return mediumVO;
	}

	@Override
	public MediumVO getMediumById(String mediumId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumById() starts");
		}
		
		MediumVO mediumVO =  (MediumVO) super.getSession().get(MediumVO.class, mediumId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumById() ends");
		}
		return mediumVO;
	}

	@Override
	public List<MediumVO> getAllMediumBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<MediumVO> mediumList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MediumVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			mediumList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMediumBySchoolId Ends");
		}
		return mediumList;

	}

	@Override
	public long getMediumCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getMediumCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from MediumVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMediumCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public MediumVO updateMedium(MediumVO mediumVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateMedium Starts");
		}
		
		super.getSession().saveOrUpdate(mediumVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMedium Ends");
		}
		return mediumVO;
	}

	@Override
	public boolean deleteMedium(String mediumId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteMedium Starts");
		}
		boolean isDeleted = false;
		
		try {
			Session session = getSession();
			CityVO adminVO = new CityVO();
			adminVO.setId(mediumId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMedium Ends");
		}
		return isDeleted;

	}

}
