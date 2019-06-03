package com.tgi.sd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class PaymentModeDAOImpl extends GenericHibernateDAOImpl<PaymentModeVO, String> implements PaymentModeDAO {

	private static Logger logger = Logger.getLogger(PaymentModeDAOImpl.class);

	@Override
	public PaymentModeVO savePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("savePaymentMode() starts");
		}
		paymentModeVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(paymentModeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("savePaymentMode() ends");
		}
		return paymentModeVO;
	}
	
	@Override
	public PaymentModeVO getPaymentModeByName(String studentId, String batchId, String id, String schoolId) throws SMSBusinessException {
		
		PaymentModeVO paymentModeVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByName Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE studentId = :studentId");
			queryBuilder.append(" and batchId = :batchId");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("studentId", studentId);
			query.setParameter("batchId", batchId);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<PaymentModeVO> paymentList = query.list();
			
			if (paymentList != null && paymentList.size() > 0)
				paymentModeVO = paymentList.get(0);


			if (logger.isDebugEnabled()) {
				logger.debug("getPaymentByName Ends");
			}
		
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByName Ends");
		}
		return paymentModeVO;
	}
	
	@Override
	public PaymentModeVO getPaymentModeByStudentId(String schoolId, String studentId, String term, String batchId) throws SMSBusinessException {
		
		PaymentModeVO paymentModeVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" and term = :term");
			queryBuilder.append(" and batchId = :batchId");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("studentId", studentId);
			query.setParameter("term", term);
			query.setParameter("batchId", batchId);
			
			List<PaymentModeVO> paymentList = query.list();
			
			if (paymentList != null && paymentList.size() > 0)
				paymentModeVO = paymentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getPaymentByName Ends");
			}
		
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenId Ends");
		}
		return paymentModeVO;
	}
	
	@Override
	public PaymentModeVO getPaymentModesByStudenId(String schoolId, String studentId, String batchId) throws SMSBusinessException {
		
		PaymentModeVO paymentModeVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenId Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" and batchId = :batchId");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("studentId", studentId);
			query.setParameter("batchId", batchId);
			
			List<PaymentModeVO> paymentmodeList = query.list();
			
			if (paymentmodeList != null && paymentmodeList.size() > 0)
				paymentModeVO = paymentmodeList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getPaymentByName Ends");
			}
		
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenId Ends");
		}
		return paymentModeVO;
	}

	@Override
	public PaymentModeVO getPaymentModeById(String paymentModeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById() starts");
		}
		
		PaymentModeVO paymentModeVO = (PaymentModeVO) super.getSession().get(PaymentModeVO.class, paymentModeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById() ends");
		}
		return paymentModeVO;
	}

	@Override
	public List<PaymentModeVO> getAllPaymentModeBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<PaymentModeVO> paymentModeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo > 0) {
				query.setFirstResult((pageNo - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
		return paymentModeLst;

	}

	@Override
	public long getPaymentModeCountBySchoolId(String schoolId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {

			logger.debug("getPaymentModeCountBySchoolId Starts");
		}
		
		long count = 0;
		
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from PaymentModeVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public PaymentModeVO updatePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Starts");
		}
		
		super.getSession().saveOrUpdate(paymentModeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Ends");
		}
		return paymentModeVO;
	}

	@Override
	public boolean deletePaymentMode(String paymentModeId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			PaymentModeVO payment = new PaymentModeVO();
			payment.setId(paymentModeId);
			session.delete(payment);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Ends");
		}
		return isDeleted;

	}


	@Override
	public List<PaymentModeVO> getAllPaymentModeStatusBySchoolId(String schoolId, String studentId, String term,
			String batchId) throws SMSBusinessException {
		
		List<PaymentModeVO> paymentModeLst = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllPaymentModeStatusBySchoolId is Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" and term = :term");
			queryBuilder.append(" and batchId = :batchId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			query.setParameter("studentId", studentId);
			query.setParameter("term", term);
			query.setParameter("batchId", batchId);

			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllPaymentModeStatusBySchoolId is Ends");
		}
		return paymentModeLst;
	}

	@Override
	public List<PaymentModeVO> getAllPaymentModeStatusBySchoolId(String schoolId, String studentId, String batchId) throws SMSBusinessException {
		
		List<PaymentModeVO> paymentModeLst = null;
				
		if(logger.isDebugEnabled()){
			logger.debug("getAllPaymentModeStatusBySchoolId is Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE studentId = :studentId");
			queryBuilder.append(" and schoolId = :schoolId");
			queryBuilder.append(" and batchId = :batchId");
						
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("studentId", studentId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("batchId", batchId);
			
			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllPaymentModeStatusBySchoolId is Ends");
		}
		return paymentModeLst;
	}

	@Override
	public List<PaymentModeVO> getAllPaymentModeByStudentIdClassIdBatchId(String schoolId,String studentId, String classId,
			String batchId, int pageIndex, int pageSize) throws SMSBusinessException {


		List<PaymentModeVO> paymentModeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentModeVO WHERE schoolId = :schoolId and studentId = :studentId and classId = :classId and batchId = :batchId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			query.setParameter("studentId", studentId);
			query.setParameter("classId", classId);
			query.setParameter("batchId", batchId);

			if (pageIndex > 0) {
				query.setFirstResult((pageIndex - 1) * pageSize);
				query.setMaxResults(pageSize);
			}

			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
		return paymentModeLst;
	}
	
	public PaymentModeVO getPaymentModeByStudenIdByTerm(String schoolId,String studentId, String classId,
			String batchId, String term) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenIdByTerm Starts");
		}
		
		PaymentModeVO paymentModeVO = null;
		
		try {
		Criteria criteria = getSession().createCriteria(PaymentModeVO.class);	
		criteria.add(Restrictions.eq("schoolId", schoolId));
		criteria.add(Restrictions.eq("studentId", studentId));
		criteria.add(Restrictions.eq("classId", classId));
		criteria.add(Restrictions.eq("batchId", batchId));
		criteria.add(Restrictions.eq("term", term));
		
		List<PaymentModeVO> paymentModeVOs = criteria.list();
		
		if(paymentModeVOs != null && paymentModeVOs.size() >0) {
			
			paymentModeVO  = paymentModeVOs.get(0);
		}
		
		
		}catch (Exception e) {
			
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenIdByTerm Ends");
		}
		
		return paymentModeVO;
	}
	
}
