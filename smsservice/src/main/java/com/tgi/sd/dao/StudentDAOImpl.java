package com.tgi.sd.dao;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.BulkImportStudentDetailVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.exception.SMSBusinessException;


@Transactional
public class StudentDAOImpl extends GenericHibernateDAOImpl<StudentVO, String> implements StudentDAO {
	private static Logger logger = Logger.getLogger(StudentDAOImpl.class);

	public StudentVO saveStudent(StudentVO student) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStudent :::" + student.getStudentName());
		}
		
		super.getSession().save(student);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStudent :::" + student.getId());
		}
		return student;
	}

	public StudentVO getStudent(String studentId) throws SMSBusinessException {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudent Id:::" + studentId);
		}
		
		StudentVO student =  (StudentVO) super.getSession().get(StudentVO.class, studentId);
		
		return student;
	}

	@Override
	public StudentVO updateStudent(StudentVO student) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStudent :::" + student.getStudentName());
		}
		
		super.getSession().saveOrUpdate(student);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStudent :::" + student.getId());
		}
		return student;
	}
	
	@Override
	public BulkImportStudentDetailVO updateBulkImportStudent(BulkImportStudentDetailVO bulkImportStudentDetailVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStudent :::");
		}
		super.getSession().saveOrUpdate(bulkImportStudentDetailVO);
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStudent :::");
		}
		return bulkImportStudentDetailVO;
	}
	
	@Override
	public long getStudentCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public StudentVO getStudentByIdOrNo(String schoolId,String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentByIdOrNo Starts");
		}
		
		StudentVO student = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and (studentId = :studentId");
			queryBuilder.append(" or parentMobileNumber1 = :mobileNo)");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("studentId", studentId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("mobileNo", studentId);
			
			List<StudentVO> studentList = query.list();
			
			if (studentList != null && studentList.size() > 0)
				student = studentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentByIdOrNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return student;
	}
	
	@Override
	public StudentVO getStudentByStudentId(String studentId, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getStudentByStudentId Starts");
		}
		
		StudentVO student = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and studentId = :studentId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("studentId", studentId);
			query.setParameter("schoolId", schoolId);
			
			List<StudentVO> studentList = query.list();
			
			if (studentList != null && studentList.size() > 0)
				student = studentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentByIdOrNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return student;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<StudentVO> getAllStudent(String schoolId,Integer pageIndex,Integer pageSize) throws SMSBusinessException { 
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStudent");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStudent");
		}
		return studentList;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long getStudentCount() throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentCount");
		}
		
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO");
			
			Query query = session.createQuery(queryBuilder.toString());
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentCount");
		}
		return (long)userOrderCount;	
	}

	@Override
	public long getAllStudentCountByBatchCode(String batchId,String classId, String schoolId) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStudentCountByBatchCode");
		}
		
		int userOrderCount = 0;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO WHERE schoolId = :schId");
			queryBuilder.append(" and batchId = :batchId");
			if(StringUtils.isNotBlank(classId))
				queryBuilder.append(" and classId = :classId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("batchId", batchId);
			if(StringUtils.isNotBlank(classId))
				query.setParameter("classId", classId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		return userOrderCount;

	}
	
	@Override
	public StudentVO getStudentByUserOid(String userOid) throws SMSBusinessException {
		StudentVO student = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentByUserOid");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE userOid = :userOid");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userOid", userOid);
			
			List<StudentVO> studentList = query.list();
			
			if (studentList != null && studentList.size() > 0)
				student = studentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentByIdOrNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return student;
		
	}
	
	@Override
	public StudentVO getStudentAdmissionNumber(String admissionNumber, String schoolId, String id) throws SMSBusinessException {
		StudentVO student = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentAdmissionNumber");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE admissionNumber = :admissionNumber");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("admissionNumber", admissionNumber);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<StudentVO> studentList = query.list();
			
			if (studentList != null && studentList.size() > 0)
				student = studentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentAdmissionNumber end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return student;
	}
	
	@Override
	public List<StudentVO> getStudentBySearchString(String searchString,int skip,int limit) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentBySearchString start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE studentName like :searchStr");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("searchStr", "%" + searchString + "%");

			//query.setFirstResult((pageIndex - 1) * pageSize);
			//query.setMaxResults(pageSize);

			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentBySearchString end");
		}
		
		return studentList;
	}

	@Override
	public List<StudentVO> getStudentsByClassAndSection(String schoolId, String standard, String section) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentsByClassAndSection start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(standard))
				queryBuilder.append(" and classId = :classId");
			if(StringUtils.isNotBlank(section))
				queryBuilder.append(" and section = :section");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(standard))
				query.setParameter("classId", standard);
			if(StringUtils.isNotBlank(section))
				query.setParameter("section", section);
			
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getStudentsByClassAndSection end");
		}

		return studentList;
	}
	
	@Override
	public List<StudentVO> getAllStudentByGender(String schoolId, String gender) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByGender start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByGender end");
		}

		return studentList;

	}
	
	@Override
	public List<StudentVO> getAllStudentByClassAndGender(String standard,String gender) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByClassAndGender start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE classId = :standard");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("standard", standard);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByClassAndGender end");
		}

		return studentList;
	}
	
	@Override
	public List<StudentVO> getAllStudentBySectionAndGender(String section,String gender) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentBySectionAndGender start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE sectionId = :section");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("section", section);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentBySectionAndGender end");
		}

		return studentList;
	}
	
	@Override
	public List<BulkImportStudentDetailVO> getUploadStudentsByClassAndSection(String schoolId,String classId,
			String sectionId, String batchId) throws SMSBusinessException {
		
		List<BulkImportStudentDetailVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getUploadStudentsByClassAndSection start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BulkImportStudentDetailVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and classId = :classId");
			queryBuilder.append(" and sectionId = :sectionId");
			queryBuilder.append(" and batch = :batchId");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("classId", classId);
			query.setParameter("sectionId", sectionId);
			query.setParameter("batchId", batchId);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getUploadStudentsByClassAndSection end");
		}

		return studentList;
	}
	
	@Override
	public List<StudentVO> getAllStudentByListClassAndGender(String schoolId, List<String> className,String gender) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByListClassAndGender start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByListClassAndGender end");
		}

		return studentList;
	}
	
	@Override
	public List<StudentVO> getAllStudentVOByBatchCode(String batchId, String classId, String sectionId,
			String schoolId) throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentVOByBatchCode start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and classId = :classId");
			queryBuilder.append(" and sectionId = :sectionId");
			queryBuilder.append(" and batch = :batchId");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("classId", classId);
			query.setParameter("sectionId", sectionId);
			query.setParameter("batchId", batchId);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentVOByBatchCode end");
		}

		return studentList;
	}
	
	@Override
	public List<StudentVO> getAllStudentByListSectionAndGender(String schoolId,List<String> sectionList,String standard, 
			String gender) throws SMSBusinessException {
		
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByListSectionAndGender start");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and classId = :standard");
			//queryBuilder.append(" and sectionId = :sectionId");
			if(StringUtils.isNotBlank(gender))
				queryBuilder.append(" and gender = :gender");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("classId", standard);
			//query.setParameter("sectionId", sectionId);
			if(StringUtils.isNotBlank(gender))
				query.setParameter("gender", gender);
			
			studentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByListSectionAndGender end");
		}

		return studentList;
	}
	
	
	@Override
	public List<StudentVO> getAllStudentsByIds(List<String> ids) throws SMSBusinessException {
		/*
		Query query = new Query();
			query.addCriteria(Criteria.where(SMSConstants.MONGO_ID).in(ids));
		List<StudentVO> studntList = (List<StudentVO>) findByQuery(query, StudentVO.class);
		return studntList;
		*/
		return null;
	}
	
	

	@Override
	public long getStudentCountByName(String studentName, String surName, String schoolId,String id) throws SMSBusinessException {
		
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentCountByName start");
		}
		
		int userOrderCount = 0;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from StudentVO WHERE schoolId = :schId");
			queryBuilder.append(" and studentName like :studentName");
			if(StringUtils.isNotBlank(surName))
				queryBuilder.append(" and surName = :surName");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("studentName", "%" + studentName + "%");
			if(StringUtils.isNotBlank(surName))
				query.setParameter("surName", "%" + surName + "%");
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentCountByName end");
		}
		
		return userOrderCount;
	}
	
	@Override
	public boolean deleteStudent(String studentId) throws SMSBusinessException  {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteStudent Starts");
		}
		boolean isDeleted= false;
		try {
			Session session = getSession();
			StudentVO adminVO = new StudentVO();
			adminVO.setId(studentId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("deleteStudent Ends");
		}
		return isDeleted;

	}
	
	@Override
	public BulkImportStudentDetailVO saveBulkImportStudent(BulkImportStudentDetailVO bulkImportStudentDetailVO) throws SMSBusinessException {
		/*
		if (logger.isDebugEnabled()) {
			logger.debug("Start bulkImportStudentDetailVO :::");
		}
		super.getSession().save(bulkImportStudentDetailVO);
		if (logger.isDebugEnabled()) {
			logger.debug("End bulkImportStudentDetailVO :::");
		}
		return bulkImportStudentDetailVO;
		*/
		return null;
	}
	
	@Override
	public StudentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException {
				
		/*
		Query query = new Query();
		query.addCriteria(Criteria.where("parentMobileNumber1").is(mobileNo));
		List<StudentVO> parentList = (List<StudentVO>) findByQuery(query, StudentVO.class);
		if(parentList != null && parentList.size() >0){
			return parentList.get(0);
		}else{
			return null;
		}
		*/
		return null;
	}
	
	@Override
	public List<String> getSchoolIdsByParentMobileNo(String mobileNo) throws SMSBusinessException {
		/*
		Query query = new Query();
		query.addCriteria(Criteria.where("parentMobileNumber1").is(mobileNo));
		List<String> schoolIds = getUniqueField(StudentVO.class, "schoolId", query);
		return schoolIds;
		*/
		return null;
	}

	@Override
	public List<StudentVO> getStudentsByMobileNoAndSchoolId(String schoolId, String mobileNo)
			throws SMSBusinessException {
		List<StudentVO> studentList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getParentByMobileNo");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and mobileNo = :mobileNo");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("mobileNo", mobileNo);
			
			studentList = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("getParentByMobileNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return studentList;
	}

	@Override
	public StudentVO getStudentById(String schoolId, String studentid) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getStudentByStudentId Starts");
		}
		/*
		Query query = new Query();
		query.addCriteria(Criteria.where(SMSConstants.MONGO_ID).is(studentid));
		query.addCriteria(Criteria.where(SMSConstants.SCHOOL_ID).is(schoolId));
		//StudentVO studentVO =  (StudentVO) findByQuery(query, StudentVO.class);
		return getMongoTemplate().findOne(query, StudentVO.class);
	*/
		return null;
		//return studentVO;
	}

	@Override
	public StudentVO getStudentView(String studentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentView Starts");
		}
		
		StudentVO student =  (StudentVO) super.getSession().get(StudentVO.class, studentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentView end");
		}
		
		return student;
	}

	@Override
	public StudentVO getStudentBySearchString(String schoolId,String classId, String batchId, String section, String searchString) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getStudentByStudentId Starts");
		}
		
		StudentVO student = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from StudentVO WHERE schoolId = :schId");
			queryBuilder.append(" or batchId = :batchId");
			queryBuilder.append(" or classId = :classId");
			queryBuilder.append(" or (studentName like :studentName");
			queryBuilder.append(" or surName = :surName)");			
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
			query.setParameter("batchId", batchId);
			query.setParameter("classId", classId);
			query.setParameter("studentName", "%" + searchString + "%");
			query.setParameter("surName", "%" + searchString + "%");
			
			List<StudentVO> studentList = query.list();
			
			if (studentList != null && studentList.size() > 0)
				student = studentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentByIdOrNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return student;
	}	
}
