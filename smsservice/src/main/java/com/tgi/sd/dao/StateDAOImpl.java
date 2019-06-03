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
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.CityVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;


@Transactional
public class StateDAOImpl extends GenericHibernateDAOImpl<StateVO, String> implements StateDAO {

	private static Logger logger = Logger.getLogger(StateDAOImpl.class);

	@Override
	public StateVO saveState(StateVO stateVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveState() starts");
		}
		stateVO.setId(UUID.randomUUID().toString());
	
		super.getSession().save(stateVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveState() ends");
		}
		return stateVO;
	}
	
	@Override
	public StateVO getStateByName(String stateName,String id, String schoolId) throws SMSBusinessException {
		
		StateVO state = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStateByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StateVO WHERE stateName = :stateName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("stateName", stateName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<StateVO> stateList = query.list();
			
			if (stateList != null && stateList.size() > 0)
				state = stateList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCityByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return state;
	}
	

	@Override
	public StateVO getStateById(String stateId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStateById() starts");
		}
		StateVO state =  (StateVO) super.getSession().get(StateVO.class, stateId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStateById() ends");
		}
		return state;
	}

	@Override
	public List<StateVO> getAllStateBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<StateVO> stateLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStateBySchoolId Starts");
		}

		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StateVO WHERE schoolId = :schId");

			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			stateLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStateBySchoolId ends");
		}
		
		return stateLst;

	}
	
	
	@Override
	public List<StateVO> getAllStateByCountryName(String countryName) throws SMSBusinessException {

		List<StateVO> stateList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from StateVO where countryId = :countryName");
			queryBuilder.append(" ORDER by stateName");
		
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("countryName", countryName);
			
			stateList = query.list();
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStateByCountryName Ends");
		}
		return stateList;

	}

	@Override
	public long getStateCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getStateCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StateVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStateCountBySchoolId Ends");
		}
		
		return userOrderCount;
	}

	@Override
	public StateVO updateState(StateVO stateVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateState Starts");
		}
		super.getSession().saveOrUpdate(stateVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateState Ends");
		}
		return stateVO;
	}

	@Override
	public boolean deleteState(String stateId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteState Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			StateVO adminVO = new StateVO();
			adminVO.setId(stateId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteState Ends");
		}
		return isDeleted;

	}

}
