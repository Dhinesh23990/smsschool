package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.fms.ConcessionVO;
import com.tgi.sd.exception.SMSBusinessException;

public class ConcessionDAOImpl extends GenericHibernateDAOImpl<ConcessionVO, String> implements ConcessionDAO {

	private static Logger logger = Logger.getLogger(ConcessionDAOImpl.class);

	@Override
	public ConcessionVO saveConcession(ConcessionVO concessionVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveConcession() starts");
		}
		concessionVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(concessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveConcession() ends");
		}
		return concessionVO;
	}
	
	@Override
	public ConcessionVO getConcessionByName(String concessionName, String id, String schoolId) throws SMSBusinessException {
		
		ConcessionVO concessionVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ConcessionVO WHERE concessionName = :concessionName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("concessionName", concessionName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<ConcessionVO> concessList = query.list();
			
			if (concessList != null && concessList.size() > 0)
				concessionVO = concessList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCountryByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionByName Ends");
		}
		return concessionVO;
	}

	@Override
	public ConcessionVO getConcessionById(String concessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionById() starts");
		}
		
		ConcessionVO concessionVO =  (ConcessionVO) super.getSession().get(ConcessionVO.class, concessionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionById() ends");
		}
		return concessionVO;
	}

	@Override
	public List<ConcessionVO> getAllConcessionBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<ConcessionVO> concessionLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ConcessionVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			concessionLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Ends");
		}
		return concessionLst;

	}

	@Override
	public long getConcessionCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		int count = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getConcessionCountBySchoolId Starts");
		}
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ConcessionVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getConcessionCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public ConcessionVO updateConcession(ConcessionVO concessionVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateConcession Starts");
		}
		
		super.getSession().saveOrUpdate(concessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateConcession Ends");
		}
		return concessionVO;
	}

	@Override
	public boolean deleteConcession(String concessionId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteConcession Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			ConcessionVO concession = new ConcessionVO();
			concession.setId(concessionId);
			session.delete(concession);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteConcession Ends");
		}
		return isDeleted;

	}

}
