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
import com.tgi.sd.domain.config.SubjectTypeVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class SubjectTypeDAOImpl extends GenericHibernateDAOImpl<SubjectTypeVO, String> implements SubjectTypeDAO {

	private static Logger logger = Logger.getLogger(SubjectTypeDAOImpl.class);

	@Override
	public SubjectTypeVO saveSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectType() starts");
		}
		subjectTypeVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(subjectTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectType() ends");
		}
		return subjectTypeVO;
	}
	
	@Override
	public SubjectTypeVO getSubjectTypeByName(String subjectTypeName,String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeByName Starts");
		}

		SubjectTypeVO subjectTypeVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SubjectTypeVO WHERE subjectTypeName = :subjectTypeName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("subjectTypeName", subjectTypeName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<SubjectTypeVO> subList = query.list();
			
			if (subList != null && subList.size() > 0)
				subjectTypeVO = subList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeByName Ends");
		}
		return subjectTypeVO;
	}
	

	@Override
	public SubjectTypeVO getSubjectTypeById(String subjectTypeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById() starts");
		}
		
		SubjectTypeVO subjectTypeVO =  (SubjectTypeVO) super.getSession().get(SubjectTypeVO.class, subjectTypeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeById() ends");
		}
		return subjectTypeVO;
	}

	@Override
	public List<SubjectTypeVO> getAllSubjectTypeBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<SubjectTypeVO> subjectTypeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from SubjectTypeVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			subjectTypeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectTypeBySchoolId Ends");
		}
		return subjectTypeLst;

	}

	@Override
	public long getSubjectTypeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getSubjectTypeCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SubjectTypeVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectTypeCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public SubjectTypeVO updateSubjectType(SubjectTypeVO subjectTypeVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Starts");
		}
		
		super.getSession().saveOrUpdate(subjectTypeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectType Ends");
		}
		return subjectTypeVO;
	}

	@Override
	public boolean deleteSubjectType(String subjectTypeId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SubjectTypeVO adminVO = new SubjectTypeVO();
			adminVO.setId(subjectTypeId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectType Ends");
		}
		return isDeleted;

	}

}
