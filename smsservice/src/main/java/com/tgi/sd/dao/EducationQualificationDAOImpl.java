package com.tgi.sd.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class EducationQualificationDAOImpl extends GenericHibernateDAOImpl<EducationalVO, String> implements EducationQualificationDAO {

	private static Logger logger = Logger.getLogger(CourseDAOImpl.class);

	@Override
	public EducationalVO saveEducation(EducationalVO educationalVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveEducation() starts");
		}
		
		super.getSession().save(educationalVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveEducation() ends");
		}
		return educationalVO;
	}

	@Override
	public EducationalVO getEducationById(String educationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById() starts");
		}
		
		EducationalVO educationalVO = (EducationalVO) super.getSession().get(EducationalVO.class, educationId);		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById() ends");
		}
		return educationalVO;
	}
	
	@Override
	public EducationalVO getEducationByName(String educationName,String id, String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseByName Starts");
		}
		
		EducationalVO educationalVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from EducationalVO WHERE educationName = :educationName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("educationName", educationName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<EducationalVO> eduList = query.list();
			
			if (eduList != null && eduList.size() > 0)
				educationalVO = eduList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCourseByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return educationalVO;
	}

	@Override
	public List<EducationalVO> getAllEducationBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<EducationalVO> educationList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEducationBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from EducationalVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			educationList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllEducationBySchoolId Ends");
		}
		return educationList;

	}

	@Override
	public long getEducationCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getEducationCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from EducationalVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getEducationCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public EducationalVO updateEducation(EducationalVO educationalVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateEducation Starts");
		}
		
		super.getSession().saveOrUpdate(educationalVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateEducation Ends");
		}
		return educationalVO;
	}

	@Override
	public boolean deleteEducation(String educationId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteEducation Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			EducationalVO adminVO = new EducationalVO();
			adminVO.setId(educationId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEducation Ends");
		}
		return isDeleted;

	}

}
