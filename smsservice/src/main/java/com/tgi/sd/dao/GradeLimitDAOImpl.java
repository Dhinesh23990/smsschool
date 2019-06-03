package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.GradeLimitVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class GradeLimitDAOImpl extends GenericHibernateDAOImpl<GradeLimitVO, String> implements GradeLimitDAO {

	private static Logger logger = Logger.getLogger(GradeLimitDAOImpl.class);

	@Override
	public GradeLimitVO saveGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit() starts");
		}
		gradeLimitVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(gradeLimitVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGradeLimit() ends");
		}
		return gradeLimitVO;
	}

	@Override
	public GradeLimitVO getGradeLimitByName(String GradeLimitName,String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitByName Starts");
		}
		
		GradeLimitVO gradeLimitVO = null;
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from GradeLimitVO WHERE GradeLimitName = :GradeLimitName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("GradeLimitName", GradeLimitName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<GradeLimitVO> gradeList = query.list();
			
			if (gradeList != null && gradeList.size() > 0)
				gradeLimitVO = gradeList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getGradeLimitByName Ends");
			}
			
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		return gradeLimitVO;
	}

	
	@Override
	public GradeLimitVO getGradeLimitById(String gradeLimitId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById() starts");
		}
		
		GradeLimitVO gradeLimitVO =  (GradeLimitVO) super.getSession().get(GradeLimitVO.class, gradeLimitId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitById() ends");
		}
		return gradeLimitVO;
	}

	@Override
	public List<GradeLimitVO> getAllGradeLimitBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {
		
		List<GradeLimitVO> gradeLimitLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from GradeLimitVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			gradeLimitLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGradeLimitBySchoolId Ends");
		}
		return gradeLimitLst;

	}

	@Override
	public long getGradeLimitCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getGradeLimitCountBySchoolId Starts");
		}
		
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from GradeLimitVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getGradeLimitCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public GradeLimitVO updateGradeLimit(GradeLimitVO gradeLimitVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Starts");
		}

		super.getSession().saveOrUpdate(gradeLimitVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateGradeLimit Ends");
		}
		return gradeLimitVO;
	}

	@Override
	public boolean deleteGradeLimit(String gradeLimitId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			GradeLimitVO adminVO = new GradeLimitVO();
			adminVO.setId(gradeLimitId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGradeLimit Ends");
		}
		return isDeleted;

	}

}
