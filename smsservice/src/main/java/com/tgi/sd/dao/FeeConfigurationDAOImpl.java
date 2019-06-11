package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.CountryVO;
import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class FeeConfigurationDAOImpl extends GenericHibernateDAOImpl<FeeConfigurationVO, String> implements FeeConfigurationDAO {

	private static Logger logger = Logger.getLogger(FeeConfigurationDAOImpl.class);

	@Override
	public FeeConfigurationVO saveFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration() starts");
		}
		
		feeConfigurationVO.setId(UUID.randomUUID().toString());
	
		super.getSession().save(feeConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration() ends");
		}
		return feeConfigurationVO;
	}
	
	@Override
	public FeeConfigurationVO getFeeConfigurationById(String feeConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById() starts");
		}
		
		FeeConfigurationVO feeConfigurationVO =  (FeeConfigurationVO) super.getSession().get(FeeConfigurationVO.class, feeConfigurationId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById() ends");
		}
		return feeConfigurationVO;
	}

	@Override
	public List<FeeConfigurationVO> getAllFeeConfigurationBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<FeeConfigurationVO> feeConfigLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schId");

			queryBuilder.append(" ORDER by feeTypeId");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			feeConfigLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Ends");
		}
		return feeConfigLst;
	}
	
	
	public FeeConfigurationVO getFeeConfigurationByBatchId(String batchId,String classId, String term, String schoolId,
			String id) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByBatchId Starts");
		}
		FeeConfigurationVO feeConfigurationVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE batchId = :batchId");
			queryBuilder.append(" and classId = :classId");
			queryBuilder.append(" and term = :term");
			queryBuilder.append(" and schoolId = :schId");
			if (id!=null)
				queryBuilder.append(" and Id = :id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("batchId", batchId);
			query.setParameter("classId", classId);
			query.setParameter("term", term);
			query.setParameter("schId", schoolId);
			if (id!=null)
				query.setParameter("id", id);
			
			List<FeeConfigurationVO> feeconfigList = query.list();
			
			if (feeconfigList != null && feeconfigList.size() > 0)
				feeConfigurationVO = feeconfigList.get(0);

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByBatchId Starts");
		}
		return feeConfigurationVO;
	}
	
	@Override
	public List<FeeConfigurationVO> getAllFeeConfigurationByClassFeeCategory(String schoolId, String classId, String feeCategory) throws SMSBusinessException {

		List<FeeConfigurationVO> feeConfigurationLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and classId = :classId");
			if (StringUtils.isNotBlank(feeCategory))
				queryBuilder.append(" and feeCategory = :feeCategory");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			query.setParameter("classId", classId);
			if (StringUtils.isNotBlank(feeCategory))
				query.setParameter("feeCategory", feeCategory);
			
			feeConfigurationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Ends");
		}
		return feeConfigurationLst;
	}

	@Override
	public long getFeeConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		int userOrderCount = 0;
		
		if (logger.isDebugEnabled()) {

			logger.debug("getFeeConfigurationCountBySchoolId Starts");
		}

		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from FeeConfigurationVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public FeeConfigurationVO updateFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Starts");
		}
	
		super.getSession().saveOrUpdate(feeConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Ends");
		}
		return feeConfigurationVO;
	}

	@Override
	public boolean deleteFeeConfiguration(String feeConfigurationId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			FeeConfigurationVO feeconfig = new FeeConfigurationVO();
			feeconfig.setId(feeConfigurationId);
			session.delete(feeconfig);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Ends");
		}
		return isDeleted;

	}
	
	public List<FeeConfigurationVO> getFeeConfigurationsByBatchId(String batchId,String classId,  String schoolId,
			String id) throws SMSBusinessException {
	
		List<FeeConfigurationVO> feeConfigurationLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByBatchId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(classId))
				queryBuilder.append(" and classId = :classId");
			if(StringUtils.isNotBlank(batchId))
				queryBuilder.append(" and batchId = :batchId");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(classId))
				query.setParameter("classId", classId);
			if(StringUtils.isNotBlank(batchId))
				query.setParameter("batchId", batchId);
			
			
			feeConfigurationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByBatchId Starts");
		}
		return feeConfigurationLst;
	}
	@Override
	public FeeConfigurationVO getFeeConfigurationByTermId(String term) throws SMSBusinessException {
			
		if(logger.isDebugEnabled()){
			logger.debug("getFeeConfigurationByTermId Starts");
		}
		
		FeeConfigurationVO feeConfigurationVO =  (FeeConfigurationVO) super.getSession().get(FeeConfigurationVO.class, term);
	
		if(logger.isDebugEnabled()){
			logger.debug("getFeeConfigurationByTermId ends");
		}
		
		return feeConfigurationVO;
	}

	@Override
	public List<FeeConfigurationVO> getAllclassesByYear(String year) throws SMSBusinessException {
		
		List<FeeConfigurationVO> classlist = null;
		
				if(logger.isDebugEnabled()){
					logger.debug("getAllclassesByYear Starts");
				}
				try {
					Session session = getSession();
					StringBuilder queryBuilder = new StringBuilder();
					
					queryBuilder.append("from FeeConfigurationVO WHERE batchId = :year");
					
					Query query = session.createQuery(queryBuilder.toString());
					query.setParameter("year", year);

					classlist = query.list();

				} catch (HibernateException re) {
					logger.error("" + re.getMessage());
					throw new SMSBusinessException(re.getMessage());
				}
				if (logger.isDebugEnabled()) {
					logger.debug("getAllStateByCountryName Ends");
				}
				return classlist;
	}

	@Override
	public List<FeeConfigurationVO> gettAllSectionsByYearClasses(String year, String classes)
			throws SMSBusinessException {
		
		List<FeeConfigurationVO> sectionlist = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("gettAllSectionsByYearClasses Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(classes))
				queryBuilder.append(" and classId = :classes");
			if(StringUtils.isNotBlank(year))
				queryBuilder.append(" and batchId = :year");
			Query query = session.createQuery(queryBuilder.toString());
			
			if(StringUtils.isNotBlank(classes))
				query.setParameter("classes", classes);
			if(StringUtils.isNotBlank(year))
				query.setParameter("year", year);
			
			
			sectionlist = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if(logger.isDebugEnabled()){
			logger.debug("gettAllSectionsByYearClasses Ends");
		}
		return sectionlist;
	
	}



	@Override
	public List<FeeConfigurationVO> getAllFeeconfigurationByStudentId(String schoolId, String classId, String batchId) throws SMSBusinessException {
		
		List<FeeConfigurationVO> feeConfigurationLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationDetailsByStudentId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(classId)) {
				queryBuilder.append(" and classId = :classId");
			}
			if(StringUtils.isNotBlank(batchId)) {
				queryBuilder.append(" and batchId = :batchId");
			}
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(classId)) {
				query.setParameter("classId", classId);
			}
			if(StringUtils.isNotBlank(batchId)) {
				query.setParameter("batchId", batchId);
			}
			
			feeConfigurationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationDetailsByStudentId ends");
		}
		return feeConfigurationLst;
	
	}

	@Override
	public FeeConfigurationVO getFeeConfigurationByStudentIdAndTerm(String schoolId, String batchId,String studentId, String term)
			throws SMSBusinessException {
		
		FeeConfigurationVO feesConfigVO = null;
		if(logger.isDebugEnabled()){
			logger.debug("gettAllSectionsByYearClasses Starts");
		}try {
			
			Session session = getSession();
			
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId=:schoolId and batchId=:batchId and term=:term");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("batchId", batchId);			
			query.setParameter("term", term);
			
			List<FeeConfigurationVO> feesConfigLstVO = query.list();
			
			if(feesConfigLstVO != null && feesConfigLstVO.size() > 0) {
				feesConfigVO = feesConfigLstVO.get(0);
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
		}			
		return feesConfigVO;
	}
}


/*
 * 
 * 	@Override
	public List<FeeConfigurationVO> getAllFeeconfigurationDetailsByStudentId(String schoolId, String batchId,String classId, 
			String sectionId, String studentId) throws SMSBusinessException {
		
		List<FeeConfigurationVO> feeConfigurationLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationDetailsByStudentId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from FeeConfigurationVO WHERE schoolId = :schoolId");
			if(StringUtils.isNotBlank(classId))
				queryBuilder.append(" and classId = :classId");
			if(StringUtils.isNotBlank(batchId))
				queryBuilder.append(" and batchId = :batchId");
			if(StringUtils.isNotBlank(studentId))
				queryBuilder.append(" and studentId = :studentId");
			if(StringUtils.isNotBlank(sectionId))
				queryBuilder.append(" and sectionId = :sectionId");
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			if(StringUtils.isNotBlank(classId))
				query.setParameter("classId", classId);
			if(StringUtils.isNotBlank(batchId))
				query.setParameter("batchId", batchId);
			if(StringUtils.isNotBlank(studentId))
				query.setParameter("studentId", studentId);
			if(StringUtils.isNotBlank(sectionId))
				query.setParameter("sectionId", sectionId);
			
			
			feeConfigurationLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationDetailsByStudentId ends");
		}
		return feeConfigurationLst;
	}
 */

	