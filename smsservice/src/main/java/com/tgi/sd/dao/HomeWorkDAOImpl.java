package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.HomeWorkVO;
import com.tgi.sd.exception.SMSBusinessException;

public class HomeWorkDAOImpl extends GenericHibernateDAOImpl<HomeWorkVO, String> implements HomeWorkDAO {

	private static Logger logger = Logger.getLogger(HomeWorkDAOImpl.class);

	@Override
	public HomeWorkVO saveHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveHomeWork() starts");
		}
		homeworkVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(homeworkVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveHomeWork() ends");
		}
		return homeworkVO;
	}
	
	@Override
	public HomeWorkVO getHomeWorkByName(String HomeWorkName, String id, String schoolId,String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkByName Starts");
		}
		
		HomeWorkVO homeworkVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from HomeWorkVO WHERE HomeWorkName = :HomeWorkName");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("educationName", HomeWorkName);
			query.setParameter("studentId", studentId);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<HomeWorkVO> homeList = query.list();
			
			if (homeList != null && homeList.size() > 0)
				homeworkVO = homeList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getHomeWorkByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return homeworkVO;
	}

	@Override
	public HomeWorkVO getHomeWorkById(String homeworkId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById() starts");
		}
	
		HomeWorkVO homeworkVO = (HomeWorkVO) super.getSession().get(HomeWorkVO.class, homeworkId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById() ends");
		}
		return homeworkVO;
	}

	@Override
	public List<HomeWorkVO> getAllHomeWorkBySchoolId(String schoolId,String studentId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<HomeWorkVO> homeworkLst = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from HomeWorkVO WHERE schoolId = :schId");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("studentId", studentId);
			
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			homeworkLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Ends");
		}
		return homeworkLst;

	}

	@Override
	public long getHomeWorkCountBySchoolId(String schoolId,String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getHomeWorkCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from HomeWorkVO WHERE studentId = :studId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("studId", studentId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getHomeWorkCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public HomeWorkVO updateHomeWork(HomeWorkVO homeworkVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Starts");
		}
		
		super.getSession().saveOrUpdate(homeworkVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Ends");
		}
		return homeworkVO;
	}

	@Override
	public boolean deleteHomeWork(String homeworkId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			HomeWorkVO homework = new HomeWorkVO();
			homework.setId(homeworkId);
			session.delete(homework);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Ends");
		}
		return isDeleted;

	}

	public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";
	@Override
	public List<HomeWorkVO> getHomeWorkBySchoolIdAndDate(String schoolId,Date startDate,Date endDate) throws SMSBusinessException {
		
		List<HomeWorkVO> homeworkVOList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentsByClassAndSection start");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and startDate = :startDate");
			queryBuilder.append(" and endDate = :endDate");
				
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);

			List<HomeWorkVO> homeList = query.list();
			
			if (homeList != null && homeList.size() > 0)
				homeworkVOList = (List<HomeWorkVO>) homeList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getHomeWorkByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
	 
		return homeworkVOList;
	}
}
