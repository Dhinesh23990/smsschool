package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.PincodeVO;
import com.tgi.sd.exception.SMSBusinessException;

@Transactional
public class PincodeDAOImpl extends GenericHibernateDAOImpl<PincodeVO, String> implements PincodeDAO {

	private static Logger logger = Logger.getLogger(PincodeDAOImpl.class);

	@Override
	public PincodeVO savePincode(PincodeVO pincodeVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("savePincode() starts");
		}
		pincodeVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(pincodeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("savePincode() ends");
		}
		return pincodeVO;
	}

	@Override
	public PincodeVO getPincodeById(String pincodeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeById() starts");
		}
		PincodeVO pincodeVO =  (PincodeVO) super.getSession().get(PincodeVO.class, pincodeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeById() ends");
		}
		return pincodeVO;
	}

	@Override
	public List<PincodeVO> getAllPincodeBySchoolId(String schoolId, int pageNo, int pageSize) throws SMSBusinessException {

		List<PincodeVO> pincodeLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from PincodeVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			pincodeLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPincodeBySchoolId Ends");
		}
		return pincodeLst;

	}

	@Override
	public long getPincodeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getPincodeCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from PincodeVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getPincodeCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public PincodeVO updatePincode(PincodeVO pincodeVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updatePincode Starts");
		}
		
		super.getSession().saveOrUpdate(pincodeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updatePincode Ends");
		}
		return pincodeVO;
	}

	@Override
	public boolean deletePincode(String pincodeId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deletePincode Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			PincodeVO adminVO = new PincodeVO();
			adminVO.setId(pincodeId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deletePincode Ends");
		}
		return isDeleted;

	}

}
