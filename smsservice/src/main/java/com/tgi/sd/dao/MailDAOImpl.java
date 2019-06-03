package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.MailVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class MailDAOImpl extends GenericHibernateDAOImpl<MailVO, String> implements MailDAO {

	private static Logger logger = Logger.getLogger(MailDAOImpl.class);
	
	
	@Override
	public List<MailVO> getMailListByType(String mailType, String status) throws SMSBusinessException {
		
		List<MailVO> mailList = null;
	
		if (logger.isDebugEnabled()) {
			logger.debug("getMailListByType starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MailVO WHERE mailType = :mailType");
			queryBuilder.append(" and status = :status");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("mailType", mailType);
			query.setParameter("status", status);
			
			mailList = query.list();

			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getMailListByType ends");
		}
		return mailList;
	}
	
	@Override
	public List<MailVO> getAllQueueMail(String sendSmsQueue)throws SMSBusinessException {
		
		List<MailVO> mailList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getMailListByType starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MailVO WHERE status = :sendSmsQueue");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("sendSmsQueue", sendSmsQueue);
			
			
			mailList = query.list();

			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getMailListByType ends");
		}
		return mailList;
	}
	
	@Override
	public MailVO saveMail(MailVO mailVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveMail() starts");
		}
		
		super.getSession().save(mailVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("saveMail() ends");
		}
		
		return mailVO;
	}

	@Override
	public void updateMailStatus(String mailId, String status,
			Date currentDate, int maxRetryCount)  throws SMSBusinessException{

		if (logger.isDebugEnabled()) {
			logger.debug("updateMailStatus starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from MailVO WHERE status = :status");
			queryBuilder.append(" and retryCount = :maxRetryCount");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("status", status);
			query.setParameter("maxRetryCount", maxRetryCount);

			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateMailStatus ends");
		}

	}
	
	@Override
	public MailVO updateMail(MailVO mailVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMail() starts");
		}
		super.getSession().saveOrUpdate(mailVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateMail() ends");
		}
		
		return mailVO;
	}

}
