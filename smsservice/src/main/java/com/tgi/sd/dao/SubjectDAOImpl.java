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
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.domain.config.SubjectVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class SubjectDAOImpl extends GenericHibernateDAOImpl<SubjectVO, String> implements SubjectDAO {

	private static Logger logger = Logger.getLogger(SubjectDAOImpl.class);

	@Override
	public SubjectVO saveSubject(SubjectVO subjectVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubject() starts");
		}
		subjectVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(subjectVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubject() ends");
		}
		return subjectVO;
	}
	
	@Override
	public SubjectVO getSubjectByName(String subjectName,String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectByName Starts");
		}
		SubjectVO subjectVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SubjectVO WHERE subjectName = :subjectName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("subjectName", subjectName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<SubjectVO> subList = query.list();
			
			if (subList != null && subList.size() > 0)
				subjectVO = subList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectByName Ends");
		}
		return subjectVO;
	}
	

	@Override
	public SubjectVO getSubjectById(String subjectId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectById() starts");
		}
		SubjectVO subjectVO =  (SubjectVO) super.getSession().get(SubjectVO.class, subjectId);	
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectById() ends");
		}
		return subjectVO;
	}

	@Override
	public List<SubjectVO> getAllSubjectBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<SubjectVO> subjectLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from SubjectVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by subjectName");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			subjectLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectBySchoolId Ends");
		}
		return subjectLst;

	}

	@Override
	public long getSubjectCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getSubjectCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SubjectVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public SubjectVO updateSubject(SubjectVO subjectVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSubject Starts");
		}
		
		super.getSession().saveOrUpdate(subjectVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubject Ends");
		}
		return subjectVO;
	}

	@Override
	public boolean deleteSubject(String subjectId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubject Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SubjectVO adminVO = new SubjectVO();
			adminVO.setId(subjectId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubject Ends");
		}
		return isDeleted;

	}

}
