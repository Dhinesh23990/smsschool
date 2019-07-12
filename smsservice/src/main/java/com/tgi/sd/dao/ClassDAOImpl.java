package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.exception.SMSBusinessException;


@Transactional
public class ClassDAOImpl extends GenericHibernateDAOImpl<ClassVO, String> implements ClassDAO {

	private static Logger logger = Logger.getLogger(ClassDAOImpl.class);

	@Override
	public ClassVO saveClass(ClassVO classVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveClass() starts");
		}
		classVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(classVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveClass() ends");
		}
		return classVO;
	}
	
	@Override
	public ClassVO getClassByName(String className,String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getClassByName Starts");
		}
		
		ClassVO classVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ClassVO WHERE className = :className");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("className", className);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
		
		List<ClassVO> classList = query.list();
		
			if (classList != null && classList.size() > 0)
				classVO = classList.get(0);
		
			} catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
			}
		
			if (logger.isDebugEnabled()) {
			logger.debug("getClassByName Ends");
		}
		
		
		return classVO;
	}
	

	@Override
	public ClassVO getClassById(String classId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getClassById() starts");
		}
		
		ClassVO classVO =  (ClassVO) super.getSession().get(ClassVO.class, classId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getClassById() ends");
		}
		return classVO;
	}

	@Override
	public List<ClassVO> getAllClassBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<ClassVO> classLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ClassVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo >0) {	
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			classLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Ends");
		}
		return classLst;

	}

	@Override
	public long getClassCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getClassCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from ClassVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getClassCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public ClassVO updateClass(ClassVO classVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateClass Starts");
		}
		super.getSession().saveOrUpdate(classVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateClass Ends");
		}
		return classVO;
	}

	@Override
	public boolean deleteClass(String classId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteClass Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			ClassVO adminVO = new ClassVO();
			adminVO.setId(classId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteClass Ends");
		}
		return isDeleted;

	}

}
