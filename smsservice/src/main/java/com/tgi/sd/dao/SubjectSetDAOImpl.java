package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.SubjectSetVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class SubjectSetDAOImpl extends GenericHibernateDAOImpl<SubjectSetVO, String> implements SubjectSetDAO {

	private static Logger logger = Logger.getLogger(SubjectSetDAOImpl.class);

	@Override
	public SubjectSetVO saveSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet() starts");
		}
		subjectSetVO.setId(UUID.randomUUID().toString());
	
		super.getSession().save(subjectSetVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSubjectSet() ends");
		}
		return subjectSetVO;
	}

	@Override
	public SubjectSetVO getSubjectSetById(String subjectSetId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById() starts");
		}
		SubjectSetVO subjectSetVO =(SubjectSetVO) super.getSession().get(SubjectSetVO.class, subjectSetId);	
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetById() ends");
		}
		return subjectSetVO;
	}

	@Override
	public List<SubjectSetVO> getAllSubjectSetBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {
		
		List<SubjectSetVO> subjectSetLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from SubjectSetVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			subjectSetLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSubjectSetBySchoolId Ends");
		}
		return subjectSetLst;

	}

	@Override
	public long getSubjectSetCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getSubjectSetCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SubjectSetVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSubjectSetCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public SubjectSetVO updateSubjectSet(SubjectSetVO subjectSetVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Starts");
		}
	
		super.getSession().saveOrUpdate(subjectSetVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSubjectSet Ends");
		}
		return subjectSetVO;
	}

	@Override
	public boolean deleteSubjectSet(String subjectSetId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SubjectSetVO adminVO = new SubjectSetVO();
			adminVO.setId(subjectSetId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSubjectSet Ends");
		}
		return isDeleted;

	}

}
