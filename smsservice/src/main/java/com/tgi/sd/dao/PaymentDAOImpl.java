package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.exception.SMSBusinessException;


public class PaymentDAOImpl extends GenericHibernateDAOImpl<PaymentVO, String> implements PaymentDAO {

	private static Logger logger = Logger.getLogger(PaymentDAOImpl.class);

	@Override
	public PaymentVO savePayment(PaymentVO paymentVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("savePayment() starts");
		}
		paymentVO.setId(UUID.randomUUID().toString());

		super.getSession().save(paymentVO);

		if (logger.isDebugEnabled()) {
			logger.debug("savePayment() ends");
		}
		return paymentVO;
	}

	@Override
	public PaymentVO updatePayment(PaymentVO paymentVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Starts");
		}
		
		super.getSession().saveOrUpdate(paymentVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Ends");
		}
		return paymentVO;
	}
	
	@Override
	public PaymentVO getPaymentByName(String studentId, String batchId, String id, String schoolId)
			throws SMSBusinessException {

		PaymentVO paymentVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByName Starts");
		}
	
		try {
		Session session = getSession();
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("from PaymentVO WHERE studentId = :studentId");
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
		
		List<PaymentVO> paymentList = query.list();
		
		if (paymentList != null && paymentList.size() > 0)
			paymentVO = paymentList.get(0);


		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByName Ends");
		}
	
		} catch (HibernateException re) {
		logger.error("" + re.getMessage());
		 throw new SMSBusinessException(re.getMessage());
	}

		return paymentVO;
	}

	@Override
	public PaymentVO getPaymentByStudentId(String schoolId, String studentId, String term, String batchId)
			throws SMSBusinessException {

		PaymentVO paymentVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByStudentId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentVO WHERE studentId = :studentId");
			queryBuilder.append(" and batchId = :batchId");
			queryBuilder.append(" and schoolId = :schoolId");
			queryBuilder.append(" and term = :term");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("studentId", studentId);
			query.setParameter("batchId", batchId);
			query.setParameter("schoolId", schoolId);
			query.setParameter("term", term);
			
			List<PaymentVO> paymentList = query.list();
			
			if (paymentList != null && paymentList.size() > 0)
				paymentVO = paymentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getPaymentByName Ends");
			}
		
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByStudentId Ends");
		}
		return paymentVO;
	}

	@Override
	public PaymentVO getPaymentByStudenId(String schoolId, String studentId, String batchId)
			throws SMSBusinessException {

		PaymentVO paymentVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByStudenId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and studentId = :studentId");
			queryBuilder.append(" and batchId = :batchId");
			
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("schoolId", schoolId);
			query.setParameter("batchId", batchId);
			query.setParameter("batchId", batchId);
			
			List<PaymentVO> paymentList = query.list();
			
			if (paymentList != null && paymentList.size() > 0)
				paymentVO = paymentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getPaymentByName Ends");
			}
		
			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentByStudenId Ends");
		}
		return paymentVO;
	}

	@Override
	public PaymentVO getPaymentById(String paymentId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById() starts");
		}
	
		PaymentVO paymentVO = (PaymentVO) super.getSession().get(PaymentVO.class, paymentId);

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById() ends");
		}
		return paymentVO;
	}

	@Override
	public List<PaymentVO> getAllPaymentBySchoolId(String schoolId, int pageNo, int pageSize)
			throws SMSBusinessException {

		List<PaymentVO> paymentModeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentVO WHERE schoolId = :schId");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Ends");
		}
		
		return paymentModeLst;

	}

	@Override
	public long getPaymentCountBySchoolId(String schoolId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {

			logger.debug("getPaymentCountBySchoolId Starts");
		}
		
		long count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from PaymentVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentCountBySchoolId Ends");
		}
		return count;
	}

	

	@Override
	public boolean deletePayment(String paymentId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deletePayment Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			PaymentVO payment = new PaymentVO();
			payment.setId(paymentId);
			session.delete(payment);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deletePayment Ends");
		}
		return isDeleted;

	}

	@Override
	public List<PaymentVO> getAllPaymentStatusBySchoolId(String schoolId, String studentId, String term, String batchId)
			throws SMSBusinessException {

		List<PaymentVO> paymentModeLst = null;
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId is Starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentVO WHERE schoolId = :schoolId");
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
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId is Ends");
		}
		return paymentModeLst;

	}


	@Override
	public List<PaymentVO> getAllPaymentStatusWithSchoolId(String schoolId, String batchId, String classId,
			String sectionId, String studentId) throws SMSBusinessException {
		
		List<PaymentVO> paymentModeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId is Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from PaymentVO WHERE schoolId = :schoolId");
			queryBuilder.append(" and batchId = :batchId");
			queryBuilder.append(" and classId = :classId");
			queryBuilder.append(" and sectionId = :sectionId");
			queryBuilder.append(" and studentId = :studentId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schoolId", schoolId);
			query.setParameter("batchId", batchId);
			query.setParameter("classId", classId);
			query.setParameter("sectionId", sectionId);
			query.setParameter("studentId", studentId);

			paymentModeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId is Ends");
		}
		return paymentModeLst;
	}
	

}

