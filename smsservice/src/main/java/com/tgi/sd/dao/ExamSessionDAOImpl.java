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
import com.tgi.sd.domain.config.ExamSessionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class ExamSessionDAOImpl extends GenericHibernateDAOImpl<ExamSessionVO, String>  implements ExamSessionDAO {

	private static Logger logger = Logger.getLogger(ExamSessionDAOImpl.class);

	@Override
	public ExamSessionVO saveExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveExamSession() starts");
		}
		examSessionVO.setId(UUID.randomUUID().toString());
		super.getSession().save(examSessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveExamSession() ends");
		}
		return examSessionVO;
	}
	
	@Override
	public ExamSessionVO getExamSessionByName(String examSessionName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionByName Starts");
		}
		
		ExamSessionVO examSessionVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ExamSessionVO WHERE examSessionName = :examSessionName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("examSessionName", examSessionName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<ExamSessionVO> examsesList = query.list();
			
			if (examsesList != null && examsesList.size() > 0)
				examSessionVO = examsesList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCasteByName end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionByName Ends");
		}
		return examSessionVO;
	}

	@Override
	public ExamSessionVO getExamSessionById(String examSessionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionById() starts");
		}
		ExamSessionVO examSessionVO =(ExamSessionVO) super.getSession().get(ExamSessionVO.class, examSessionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionById() ends");
		}
		return examSessionVO;
	}

	@Override
	public List<ExamSessionVO> getAllExamSessionBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<ExamSessionVO> examSessionLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from ExamSessionVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			examSessionLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Ends");
		}
		return examSessionLst;

	}

	@Override
	public long getExamSessionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getExamSessionCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ExamSessionVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getExamSessionCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public ExamSessionVO updateExamSession(ExamSessionVO examSessionVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateExamSession Starts");
		}
		
		super.getSession().saveOrUpdate(examSessionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateExamSession Ends");
		}
		return examSessionVO;
	}

	@Override
	public boolean deleteExamSession(String examSessionId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			ExamSessionVO adminVO = new ExamSessionVO();
			adminVO.setId(examSessionId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Ends");
		}
		return isDeleted;

	}

}
