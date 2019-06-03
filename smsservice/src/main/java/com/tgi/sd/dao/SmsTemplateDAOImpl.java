package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.SmsTemplateVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */

@Transactional
public class SmsTemplateDAOImpl extends GenericHibernateDAOImpl<SmsTemplateVO, String> implements SmsTemplateDAO {
	private static Logger logger = Logger.getLogger(SmsTemplateDAOImpl.class);

	public SmsTemplateVO saveSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveSmsTemplate :::" + smsTemplate.getShortDescription());
		}
		smsTemplate.setId(UUID.randomUUID().toString());
		
		super.getSession().save(smsTemplate);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}

	public SmsTemplateVO getSmsTemplate(String id) throws SMSBusinessException {
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSmsTemplate Id:::" + id);
		}
		
		SmsTemplateVO smsTemplate = (SmsTemplateVO) super.getSession().get(SmsTemplateVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}

	@Override
	public SmsTemplateVO updateSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateSmsTemplate :::" + smsTemplate.getShortDescription());
		}
		
		super.getSession().saveOrUpdate(smsTemplate);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsTemplateVO> getAllSmsTemplate(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException { 
		
		List<SmsTemplateVO> smsTemplateList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsTemplate");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from SmsTemplateVO WHERE schoolId = :schId");
	
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			smsTemplateList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsTemplate");
		}
		return smsTemplateList;	
	}

	@Override
	public long getSmsTemplateCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getSmsTemplateCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SmsTemplateVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getSmsTemplateCountBySchoolId Ends");
		}
		return userOrderCount;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public SmsTemplateVO getSmsTemplateByUserOid(String userOid) throws SMSBusinessException {
		
		SmsTemplateVO smsTemplateVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentByUserOid");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from StudentVO WHERE userOid = :userOid");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userOid", userOid);
			
			List<SmsTemplateVO> smsList = query.list();
			
			if (smsList != null && smsList.size() > 0)
				smsTemplateVO = smsList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentByIdOrNo end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return smsTemplateVO;
	}
	
	@Override
	public boolean deleteSmsTemplate(String smsTemplateId) throws SMSBusinessException  {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SmsTemplateVO leave = new SmsTemplateVO();
			leave.setId(smsTemplateId);
			session.delete(leave);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Ends");
		}
		return isDeleted;

	}
}
