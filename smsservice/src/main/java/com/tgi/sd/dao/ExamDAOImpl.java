package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.ExamVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class ExamDAOImpl extends GenericHibernateDAOImpl<ExamVO, String> implements ExamDAO {

	private static Logger logger = Logger.getLogger(ExamDAOImpl.class);

	@Override
	public ExamVO saveExam(ExamVO examVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveExam() starts");
		}
		examVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(examVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveExam() ends");
		}
		return examVO;
	}
	
	@Override
	public ExamVO getExamByName(String examName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamByName Starts");
		}
		ExamVO examVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ExamVO WHERE examName = :examName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("examName", examName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<ExamVO> examList = query.list();
			
			if (examList != null && examList.size() > 0)
				examVO = examList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getExamByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return examVO;
	}

	@Override
	public ExamVO getExamById(String examId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamById() starts");
		}
		
		ExamVO examVO =  (ExamVO) super.getSession().get(ExamVO.class, examId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamById() ends");
		}
		return examVO;
	}

	@Override
	public List<ExamVO> getAllExamBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<ExamVO> examLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from ExamVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			examLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Ends");
		}
		return examLst;

	}

	@Override
	public long getExamCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getExamCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ExamVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getExamCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public ExamVO updateExam(ExamVO examVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateExam Starts");
		}
		
		super.getSession().saveOrUpdate(examVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateExam Ends");
		}
		return examVO;
	}

	@Override
	public boolean deleteExam(String examId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteExam Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			ExamVO adminVO = new ExamVO();
			adminVO.setId(examId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExam Ends");
		}
		return isDeleted;

	}

}
