package com.tgi.sd.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class CourseDAOImpl extends GenericHibernateDAOImpl<CourseVO, String> implements CourseDAO {

	private static Logger logger = Logger.getLogger(CourseDAOImpl.class);

	@Override
	public CourseVO saveCourse(CourseVO courseVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse() starts");
		}
		
		super.getSession().save(courseVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCourse() ends");
		}
		return courseVO;
	}

	@Override
	public CourseVO getCourseById(String courseId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById() starts");
		}
		
		CourseVO courseVO =  (CourseVO) super.getSession().get(CourseVO.class, courseId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseById() ends");
		}
		return courseVO;
	}
	
	@Override
	public CourseVO getCourseByName(String courseName,String id, String schoolId) throws SMSBusinessException {
		
		CourseVO courseVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CourseVO WHERE courseName = :courseName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("courseName", courseName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<CourseVO> courseList = query.list();
			
			if (courseList != null && courseList.size() > 0)
				courseVO = courseList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCourseByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return courseVO;
	}
	
	@Override
	public CourseVO getCourseByCourseName(String courseName,String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseByCourseName Starts");
		}
		CourseVO course = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CourseVO WHERE courseName = :courseName");
			queryBuilder.append(" and (schoolId = :schoolId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("courseName", courseName);
			query.setParameter("schoolId", schoolId);
			
			List<CourseVO> courseList = query.list();
			
			if (courseList != null && courseList.size() > 0)
				course = courseList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCourseByCourseName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return course;
	}

	@Override
	public List<CourseVO> getAllCourseBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<CourseVO> courseLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from CourseVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			courseLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCourseBySchoolId Ends");
		}
		return courseLst;

	}

	@Override
	public long getCourseCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getCourseCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from CourseVO WHERE schoolId = :schId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getCourseCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public CourseVO updateCourse(CourseVO courseVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCourse Starts");
		}
		
		super.getSession().saveOrUpdate(courseVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCourse Ends");
		}
		return courseVO;
	}

	@Override
	public boolean deleteCourse(String courseId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCourse Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			CourseVO adminVO = new CourseVO();
			adminVO.setId(courseId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCourse Ends");
		}
		return isDeleted;

	}

}
