package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class BatchConfigurationDAOImpl extends GenericHibernateDAOImpl<BatchConfigurationVO, String> implements BatchConfigurationDAO {

	private static Logger logger = Logger.getLogger(BatchConfigurationDAOImpl.class);

	@Override
	public BatchConfigurationVO saveBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration() starts");
		}
		batchConfigurationVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(batchConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration() ends");
		}
		return batchConfigurationVO;
	}

	@Override
	public BatchConfigurationVO getBatchConfigurationById(String batchConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById() starts");
		}
		BatchConfigurationVO batchConfigurationVO =  (BatchConfigurationVO) super.getSession().get(BatchConfigurationVO.class, batchConfigurationId);
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById() ends");
		}
		return batchConfigurationVO;
	}

	@Override
	public List<BatchConfigurationVO> getAllBatchConfigurationBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<BatchConfigurationVO> batchConfigurationLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from BatchConfigurationVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			batchConfigurationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Ends");
		}
		return batchConfigurationLst;

	}
	
	@Override
	public BatchConfigurationVO getBatchConfigByName(String id,BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException {
		
		BatchConfigurationVO batchConfiguration = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigByName Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
		
			queryBuilder.append("from BatchConfigurationVO where batchId = :batchId");
			queryBuilder.append(" and mediumId = :mediumId");
			queryBuilder.append(" and sectionId = :sectionId");
			queryBuilder.append(" and classId = :classId");
			queryBuilder.append(" and classTeacherId = :classTeacherId");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("batchId", batchConfigurationVO.getBatchId());
			query.setParameter("mediumId", batchConfigurationVO.getMediumId());
			query.setParameter("sectionId", batchConfigurationVO.getSectionId());
			query.setParameter("classId", batchConfigurationVO.getClassId());
			query.setParameter("classTeacherId", batchConfigurationVO.getClassTeacherId());
			query.setParameter("schoolId", batchConfigurationVO.getSchoolId());
			
			if(id != null)
				query.setParameter("Id", id);
			
			List<BatchConfigurationVO> batchList = query.list();
			
			if (batchList != null && batchList.size() > 0)
				batchConfiguration = batchList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getBatchConfigByName Ends");
			}
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return batchConfiguration;
	}

	@Override
	public long getBatchConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getBatchConfigurationCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from BatchConfigurationVO WHERE schoolId = :schId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public BatchConfigurationVO updateBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Starts");
		}
		
		super.getSession().saveOrUpdate(batchConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Ends");
		}
		return batchConfigurationVO;
	}
	
	@Override
	public List<BatchConfigurationVO> getAllClassTeacherByIds(String schoolId,List<String> batchIds,
			List<String> sectionIds, List<String> classIds) throws SMSBusinessException {
		
		List<BatchConfigurationVO> batchConfigList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllClassTeacherByIds start");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BatchConfigurationVO WHERE schoolId = :schoolId");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			batchConfigList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getAllStudentByListSectionAndGender end");
		}

		return batchConfigList;
	}
	
	@Override
	public BatchConfigurationVO getBatchConfigByStaffId(String staffId,String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchConfigByStaffId Starts");
		}
		
		BatchConfigurationVO batchConfiguration = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from TeacherVO WHERE staffId = :staffId");
			queryBuilder.append(" and schoolId = :schoolId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("staffId", staffId);
			query.setParameter("schoolId", schoolId);
			
			List<BatchConfigurationVO> batchConfigList = query.list();
			
			if (batchConfigList != null && batchConfigList.size() > 0)
				batchConfiguration = batchConfigList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getBatchConfigByStaffId Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return batchConfiguration;

	}

	@Override
	public boolean deleteBatchConfiguration(String batchConfigurationId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			BatchConfigurationVO adminVO = new BatchConfigurationVO();
			adminVO.setId(batchConfigurationId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}if (logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Ends");
		}
		return isDeleted;

	}

	@Override
	public List<String> getAllTeacherIdsByClassAndSectionId(String classId, String sectionId, String schoolId,String batchId)
			throws SMSBusinessException {
		
		List<String> batchConfigList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllTeachersByClassAndSectionId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from BatchConfigurationVO WHERE classId = :classId");
			queryBuilder.append(" and sectionId = :sectionId");
			queryBuilder.append(" and schoolId = :schoolId");
			queryBuilder.append(" and batchId = :batchId");
			queryBuilder.append(" and mobileNo = :mobileNo");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("classId", classId);
			query.setParameter("sectionId", sectionId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("batchId", batchId);
			
			batchConfigList = query.list();
			
			if (logger.isDebugEnabled()) {
				logger.debug("getAllTeachersByClassAndSectionId Ends");
			}
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return batchConfigList;
	}

}
