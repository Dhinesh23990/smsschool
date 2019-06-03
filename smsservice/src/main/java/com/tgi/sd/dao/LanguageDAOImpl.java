package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class LanguageDAOImpl extends GenericHibernateDAOImpl<LanguageVO, String> implements LanguageDAO {

	private static Logger logger = Logger.getLogger(LanguageDAOImpl.class);

	@Override
	public LanguageVO saveLanguage(LanguageVO languageVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveLanguage() starts");
		}
		languageVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(languageVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLanguage() ends");
		}
		return languageVO;
	}
	
	@Override
	public LanguageVO getLanguageByName(String languageName,String id, String schoolId) throws SMSBusinessException {
		
		LanguageVO languageVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from LanguageVO WHERE languageName = :languageName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("languageName", languageName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<LanguageVO> langList = query.list();
			
			if (langList != null && langList.size() > 0)
				languageVO = langList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getLanguageByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return languageVO;
	}
	

	@Override
	public LanguageVO getLanguageById(String languageId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageById() starts");
		}

		LanguageVO languageVO =  (LanguageVO) super.getSession().get(LanguageVO.class, languageId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageById() ends");
		}
		return languageVO;
	}

	@Override
	public List<LanguageVO> getAllLanguageBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<LanguageVO> languageLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from LanguageVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			languageLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLanguageBySchoolId Ends");
		}
		return languageLst;

	}

	@Override
	public long getLanguageCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from LanguageVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getLanguageCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public LanguageVO updateLanguage(LanguageVO languageVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateLanguage Starts");
		}
		super.getSession().saveOrUpdate(languageVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLanguage Ends");
		}
		return languageVO;
	}

	@Override
	public boolean deleteLanguage(String languageId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			LanguageVO adminVO = new LanguageVO();
			adminVO.setId(languageId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLanguage Ends");
		}
		return isDeleted;

	}

}
