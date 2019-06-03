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
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class GenderDAOImpl extends GenericHibernateDAOImpl<GenderVO, String> implements GenderDAO {

	private static Logger logger = Logger.getLogger(GenderDAOImpl.class);

	@Override
	public GenderVO saveGender(GenderVO genderVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGender() starts");
		}
		genderVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(genderVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGender() ends");
		}
		return genderVO;
	}

	@Override
	public GenderVO getGenderByName(String gender,String id, String schoolId) throws SMSBusinessException {
		
		GenderVO genderVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from GenderVO WHERE gender = :gender");
			queryBuilder.append(" and schoolId = :schoolId");
				if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("gender", gender);
			query.setParameter("schoolId", schoolId);
			
				if(id != null)
				query.setParameter("Id", id);
			
			List<GenderVO> genderList = query.list();
			
				if (genderList != null && genderList.size() > 0)
				genderVO = genderList.get(0);

				if (logger.isDebugEnabled()) {
				logger.debug("getCityByName end");
			}

			} 	catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
		}
		
		return genderVO;
	}
	
	@Override
	public GenderVO getGenderById(String genderId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderById() starts");
		}
		
		GenderVO genderVO =  (GenderVO) super.getSession().get(GenderVO.class, genderId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderById() ends");
		}
		return genderVO;
	}

	@Override
	public List<GenderVO> getAllGenderBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<GenderVO> genderList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from GenderVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			genderList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Ends");
		}
		return genderList;

	}

	@Override
	public long getGenderCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getGenderCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from GenderVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGenderCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public GenderVO updateGender(GenderVO genderVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateGender Starts");
		}
	
		super.getSession().saveOrUpdate(genderVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateGender Ends");
		}
		return genderVO;
	}

	@Override
	public boolean deleteGender(String genderId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("DeleteGender Starts");
		}
		boolean isDeleted = false;
		try {
			
			Session session = getSession();
			GenderVO adminVO = new GenderVO();
			adminVO.setId(genderId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("DeleteGender Ends");
		}
		
		return isDeleted;

	}

}
