package com.tgi.sd.dao;

import java.util.List;
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
import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.domain.SchoolVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.domain.config.CourseVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;


@Transactional
public class SchoolDAOImpl extends GenericHibernateDAOImpl<SchoolVO, String> implements SchoolDAO {

	private static Logger logger = Logger.getLogger(SchoolDAOImpl.class);

	@Override
	public SchoolVO saveSchool(SchoolVO schoolVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSchool() starts");
		}
		
		super.getSession().save(schoolVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSchool() ends");
		}
		return schoolVO;
	}
	
	@Override
	public SchoolVO getSchoolByName(String schoolName, String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolByName Starts");
		}
		
		SchoolVO schoolVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SchoolVO WHERE schoolName = :schoolName");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolName", schoolName);
			if(id != null)
				query.setParameter("Id", id);
			
			List<SchoolVO> schoolList = query.list();
			
			if (schoolList != null && schoolList.size() > 0)
				schoolVO = schoolList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getSchoolByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return schoolVO;
	}
	
	@Override
	public UserVO getSchoolByUserName(String userName, String id) throws SMSBusinessException {

		UserVO userVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolByUserName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from UserVO WHERE userName = :userName");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userName", userName);
			if(id != null)
				query.setParameter("Id", id);
			
			List<UserVO> userList = query.list();
			
			if (userList != null && userList.size() > 0)
				userVO = userList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getSchoolByUserName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		return userVO;
	}
	
	@Override
	public UserVO getSchoolByEmailId(String contactEmailId, String id) throws SMSBusinessException {
		
		UserVO userVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolByEmailId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from UserVO WHERE contactEmailId = :contactEmailId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("contactEmailId", contactEmailId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<UserVO> userList = query.list();
			
			if (userList != null && userList.size() > 0)
				userVO = userList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getSchoolByEmailId Ends");
			}
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return userVO;
	}

	@Override
	public SchoolVO getSchoolById(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolById() starts");
		}
		SchoolVO schoolVO =  (SchoolVO) super.getSession().get(SchoolVO.class, schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolById() ends");
		}
		return schoolVO;
	}

	@Override
	public List<SchoolVO> getAllSchool(int pageNo, int pageSize, String status) throws SMSBusinessException {

		List<SchoolVO> schoolLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchool Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			if (StringUtils.isNotBlank(status)) {
			queryBuilder.append("from SchoolVO WHERE status = :status");
			}else {
				queryBuilder.append("from SchoolVO");
			}
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			if (StringUtils.isNotBlank(status)) {
			query.setParameter("status", status);
			}
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			schoolLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchool Ends");
		}
		return schoolLst;

	}

	@Override
	public long getAllSchoolCount() throws SMSBusinessException {
		
		List<SchoolVO> schoolList = null;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getAllSchoolCount Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SchoolVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSchoolCount Ends");
		}
		return (long)userOrderCount;
	}

	@Override
	public SchoolVO updateSchool(SchoolVO schoolVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSchool Starts");
		}
		
		super.getSession().saveOrUpdate(schoolVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchool Ends");
		}
		return schoolVO;
	}
	
	@Override
	public boolean updateSchoolStatus(String schoolId, String status) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSchoolStatus Starts");
		}
		boolean isStatusUpdated = false;
		try {
			Session session = getSession();
			SchoolVO schlVO = new SchoolVO();
			schlVO.setId(schoolId);
			schlVO.setStatus(status);
			session.delete(schlVO);			
			isStatusUpdated= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateSchoolStatus Ends");
		}
		return isStatusUpdated;

	}
	
	@Override
	public boolean updateUserStatus(String schoolId, String status) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateUserStatus Starts");
		}
		boolean isStatusUpdated = false;
		try {
			Session session = getSession();
			SchoolVO schlVO = new SchoolVO();
			schlVO.setId(schoolId);
			schlVO.setStatus(status);
			session.delete(schlVO);			
			isStatusUpdated= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateUserStatus Ends");
		}
		return isStatusUpdated;

	}
	
	@Override
	public boolean updateAdminStatus(String schoolId, String status) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateAdminStatus Starts");
		}
		boolean isStatusUpdated = false;
		try {
			Session session = getSession();
			SchoolVO schlVO = new SchoolVO();
			schlVO.setId(schoolId);
			schlVO.setStatus(status);
			session.delete(schlVO);			
			isStatusUpdated= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateAdminStatus Ends");
		}
		return isStatusUpdated;

	}

	@Override
	public boolean deleteSchool(String schoolId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSchool Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SchoolVO adminVO = new SchoolVO();
			adminVO.setId(schoolId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSchool Ends");
		}
		return isDeleted;

	}
	
	@Override
	public List<SchoolVO> getSchoolByIds(List<String> schoolIds) throws SMSBusinessException {
/*		Query query = new Query();
		query.addCriteria(Criteria.where(SMSConstants.MONGO_ID).in(schoolIds));
		query.with(new Sort(new Order(Direction.ASC, SMSConstants.SCHOOL_NAME)));
		List<SchoolVO> schoolLst = (List<SchoolVO>) findByQuery(query, SchoolVO.class);
		if (logger.isDebugEnabled()) {
			logger.debug("getSchoolByIds Ends");
		}
		return schoolLst;
	}
	*/
		return null;
	}
}
