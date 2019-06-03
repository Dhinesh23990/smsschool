package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.domain.config.MotherTongueVO;
import com.tgi.sd.exception.SMSBusinessException;


@Transactional
public class MotherTongueDAOImpl extends GenericHibernateDAOImpl<MotherTongueVO, String> implements MotherTongueDAO {

	private static Logger logger = Logger.getLogger(MotherTongueDAOImpl.class);

	@Override
	public MotherTongueVO saveMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue() starts");
		}
		motherTongueVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(motherTongueVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveMotherTongue() ends");
		}
		return motherTongueVO;
	}

	@Override
	public MotherTongueVO getMotherTongueByName(String motherTongueName,String id, String schoolId) throws SMSBusinessException {
		
		MotherTongueVO motherTongueVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MotherTongueVO WHERE motherTongueName = :motherTongueName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("motherTongueName", motherTongueName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<MotherTongueVO> mothertongueList = query.list();
			
			if (mothertongueList != null && mothertongueList.size() > 0)
				motherTongueVO = mothertongueList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getMotherTongueByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return motherTongueVO;
	}
	
	@Override
	public MotherTongueVO getMotherTongueById(String motherTongueId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById() starts");
		}
		
		MotherTongueVO motherTongueVO =  (MotherTongueVO) super.getSession().get(MotherTongueVO.class, motherTongueId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueById() ends");
		}
		return motherTongueVO;
	}

	@Override
	public List<MotherTongueVO> getAllMotherTongueBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<MotherTongueVO> motherTongueLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from MotherTongueVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			motherTongueLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllMotherTongueBySchoolId Ends");
		}
		return motherTongueLst;

	}

	@Override
	public long getMotherTongueCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from MotherTongueVO WHERE schoolId = :schId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMotherTongueCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public MotherTongueVO updateMotherTongue(MotherTongueVO motherTongueVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Starts");
		}
		super.getSession().saveOrUpdate(motherTongueVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMotherTongue Ends");
		}
		return motherTongueVO;
	}

	@Override
	public boolean deleteMotherTongue(String motherTongueId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			CourseVO adminVO = new CourseVO();
			adminVO.setId(motherTongueId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteMotherTongue Ends");
		}
		return isDeleted;

	}

}
