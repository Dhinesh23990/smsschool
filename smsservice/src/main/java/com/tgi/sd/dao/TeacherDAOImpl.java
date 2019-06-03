/**
 * 
 */
package com.tgi.sd.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */

@Transactional
public class TeacherDAOImpl extends GenericHibernateDAOImpl<TeacherVO, String> implements TeacherDAO {
	
	private static Logger logger = Logger.getLogger(TeacherDAOImpl.class);

	public TeacherVO saveTeacher(TeacherVO teacher) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveTeacher :::" + teacher.getStaffName());
		}
		
		super.getSession().save(teacher);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveTeacher :::" + teacher.getId());
		}
		return teacher;
	}

	public TeacherVO getTeacher(String id) throws SMSBusinessException {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeacher Id:::" + id);
		}
		
		TeacherVO teacher =  (TeacherVO) super.getSession().get(TeacherVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getTeacher :::" + teacher.getId());
		}
		return teacher;
	}

	@Override
	public TeacherVO updateTeacher(TeacherVO teacher) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateTeacher :::" + teacher.getStaffName());
		}

		super.getSession().saveOrUpdate(teacher);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateTeacher :::" + teacher.getId());
		}
		return teacher;
	}
	
	@Override
	public long getTeacherCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getTeacherCountBySchoolId Starts");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from TeacherVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getTeacherCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherVO> getAllTeacher(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException { 
		List<TeacherVO> teacherList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacher");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageIndex != null && pageIndex > 0) {
				query.setFirstResult((pageIndex - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			teacherList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacher");
		}
		return teacherList;	
	}
	
	@Override
	public List<TeacherVO> getAllTeacherByGender(String schoolId,String gender) throws SMSBusinessException { 
		
		List<TeacherVO> teacherList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacherByGender");
		}

		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			teacherList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacherByGender");
		}
		return teacherList;	
	}
	
	@Override
	public TeacherVO getTeacherEmployeeNumber(String employeeNumber, String schoolId, String id) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getTeacherEmployeeNumber Starts");
		}
		TeacherVO teacher = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and employeeNumber = :employeeNumber");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("employeeNumber", employeeNumber);
			query.setParameter("schoolId", schoolId);
		
		List<TeacherVO> teacherList = query.list();
		
			if (teacherList != null && teacherList.size() > 0)
				teacher = teacherList.get(0);
		
			} catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
			}
				if (logger.isDebugEnabled()) {
					logger.debug("getTeacherEmployeeNumber end");
		}
		return teacher;
	}
	
	@Override
	public TeacherVO getTeacherByStaffId(String schoolId,String staffId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getTeacherEmployeeNumber Starts");
		}
		TeacherVO teacher = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and staffId = :staffId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("staffId", staffId);
			query.setParameter("schoolId", schoolId);
		
		List<TeacherVO> teacherList = query.list();
		
			if (teacherList != null && teacherList.size() > 0)
				teacher = teacherList.get(0);
		
			} catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
			}
				if (logger.isDebugEnabled()) {
					logger.debug("getTeacherEmployeeNumber end");
		}
		return teacher;
	}
	

	@Override
	public TeacherVO getTeacherByUserOid(String userOid) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeacherByUserOid Starts");
		}
		TeacherVO teacher = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE userOid = :userOid");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userOid", userOid);
			
			List<TeacherVO> teacherList = query.list();
			
			if (teacherList != null && teacherList.size() > 0)
				teacher = teacherList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getTeacherByUserOid end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return teacher;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeacherVO> getAllTeacherByIds(List<String> ids) throws SMSBusinessException { 
		
/*		List<TeacherVO> teacherList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllTeacher");
		}
		Query query = new Query();
		query.addCriteria(Criteria.where("id").in(ids));
		teacherList =  (List<TeacherVO>) findByQuery(query, TeacherVO.class);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllTeacher");
		}
		return teacherList;	
	*/
		return null;
		}

	@Override
	public long getTeacherCountByNameAndInitial(String staffName, String initial, String schoolId, String id) throws SMSBusinessException {
		long currentMilli = 0;
		if(logger.isDebugEnabled()){
					logger.debug("TeacherDAOImpl.getTeacherCountByNameAndInitial Starts--->{}"+System.currentTimeMillis());
					currentMilli = System.currentTimeMillis();
		}

		//lookupName =ValidationUtil.escapeSpecialCharacters(lookupName);

		int userOrderCount = 0;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from TeacherVO WHERE schoolId = :schId");
			if(StringUtils.isNotBlank(staffName))
				queryBuilder.append(" and staffName = :staffName");
			if(StringUtils.isNotBlank(initial))
				queryBuilder.append(" and initial = :initial");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			if(StringUtils.isNotBlank(staffName))
				query.setParameter("staffName", staffName);
			if(StringUtils.isNotBlank(initial))
				query.setParameter("initial", initial);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
	return userOrderCount;		
	}
	
	
	
	
	@Override
	public long getAllTeacherCountByDateOfJoiningYear(Date dateOfJoining, String schoolId) throws SMSBusinessException {
		/*Query query = new Query();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(dateOfJoining);
		int year = calendar.get(Calendar.YEAR);
		query.addCriteria(Criteria.where("dateOfJoining").ne(null).andOperator(
            Criteria.where("dateOfJoining").gte(getDate(year+"-01-01 00:00:00", DB_FORMAT_DATETIME)),
            Criteria.where("dateOfJoining").lte(getDate(year+"-12-31 23:59:59", DB_FORMAT_DATETIME))
        )); 
		
		query.addCriteria(Criteria.where("schoolId").is(schoolId));
		List<TeacherVO> teacherList = (List<TeacherVO>) findByQuery(query, TeacherVO.class);
	 
		return (long) teacherList.size();
	*/	
		return 0;
	}
	
    public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";        

	  public static Date getDate(String dateStr, String format) {
	        final DateFormat formatter = new SimpleDateFormat(format);
	        try {
	            return formatter.parse(dateStr);
	        } catch (ParseException e) {                
	            return null;
	        }
	    }
	
	@Override
	public boolean deleteTeacher(String teacherId) throws SMSBusinessException  {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Starts");
		}
		boolean isDeleted= false;
		
		try {
			Session session = getSession();
			TeacherVO adminVO = new TeacherVO();
			adminVO.setId(teacherId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteTeacher Ends");
		}
		return isDeleted;

	}
}
