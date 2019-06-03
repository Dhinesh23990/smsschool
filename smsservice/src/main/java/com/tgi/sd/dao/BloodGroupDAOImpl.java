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
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional

public class BloodGroupDAOImpl extends GenericHibernateDAOImpl<CountryVO, String> implements BloodGroupDAO {

	private static Logger logger = Logger.getLogger(BloodGroupDAOImpl.class);

	@Override
	public BloodGroupVO saveBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup() starts");
		}
		bloodGroupVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(bloodGroupVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup() ends");
		}
		return bloodGroupVO;
	}

	@Override
	public BloodGroupVO getBloodGroupByName(String bloodGroupName,String id, String schoolId) throws SMSBusinessException {
		
		BloodGroupVO bloodGroupVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BloodGroupVO WHERE bloodgroupName = :bloodGroupName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("bloodGroupName", bloodGroupName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<BloodGroupVO> bloodgrpList = query.list();
			
			if (bloodgrpList != null && bloodgrpList.size() > 0)
				bloodGroupVO = bloodgrpList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getBloodGroupByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return bloodGroupVO;
	}
	
	@Override
	public BloodGroupVO getBloodGroupById(String bloodGroupId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById() starts");
		}
		BloodGroupVO bloodGroupVO =  (BloodGroupVO) super.getSession().get(BloodGroupVO.class, bloodGroupId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById() ends");
		}
		return bloodGroupVO;
	}

	@Override
	public List<BloodGroupVO> getAllBloodGroupBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<BloodGroupVO> bloodList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BloodGroupVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			bloodList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Ends");
		}
		return bloodList;

	}

	@Override
	public long getBloodGroupCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from BloodGroupVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public BloodGroupVO updateBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Starts");
		}
		
		super.getSession().saveOrUpdate(bloodGroupVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Ends");
		}
		return bloodGroupVO;
	}

	@Override
	public boolean deleteBloodGroup(String bloodGroupId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Starts");
		}
		boolean isDeleted= false;
		
		try {
			Session session = getSession();
			BloodGroupVO adminVO = new BloodGroupVO();
			adminVO.setId(bloodGroupId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Ends");
		}
		return isDeleted;

	}

}
