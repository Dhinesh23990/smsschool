package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.exception.SMSBusinessException;

public class ParentDAOImpl extends GenericHibernateDAOImpl<ParentVO, String> implements ParentDAO {

	private static Logger logger = Logger.getLogger(LeaveMasterDAOImpl.class);
	
	@Override
	public ParentVO saveParent(ParentVO student) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveParent() starts");
		}
		student.setId(UUID.randomUUID().toString());
		
		super.getSession().save(student);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveParent() ends");
		}
		
		return student;
	}
	
	@Override
	public ParentVO getParent(String id) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getParent() starts");
		}
		
		ParentVO leaveMasterVO = (ParentVO) super.getSession().get(ParentVO.class, id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getParent() ends");
		}
		return leaveMasterVO;
	}

	@Override
	public ParentVO updateParent(ParentVO parent) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateParent Starts");
		}
	
		super.getSession().saveOrUpdate(parent);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateParent Ends");
		}
		return parent;
	}

	@Override
	public List<ParentVO> getAllParent() throws SMSBusinessException { 

		List<ParentVO> parentVO = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllParent starts");
		}
		
		try {
		
		Session session = getSession();
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("from ParentVO");
		
		Query query = session.createQuery(queryBuilder.toString());
		
		parentVO  = query.list();
		
		
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllParent ends");
		}
		return parentVO;
	}
	
	
	@Override
	public List<ParentVO> getParentBySearchString(String searchString) throws SMSBusinessException {
		
			List<ParentVO> parentList = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ParentVO WHERE parentName = :searchString");

			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("searchString", searchString);
			
			parentList = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId ends");
		}
		return parentList;
	}
	
	@Override
	public ParentVO getParentByUserOid(String userOid) throws SMSBusinessException {
		
			ParentVO parent = null;
			
			if (logger.isDebugEnabled()) {
			logger.debug("Start getParentByUserOid starts");
			}
		
			try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ParentVO WHERE userOid = :userOid");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("userOid", userOid);
			
			List<ParentVO> parentList = query.list();
			
			if (parentList != null && parentList.size() > 0)
				parent = parentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getParentByUserOid end");
			}

			} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
			}
		
			return parent;
		
	}

	@Override
	public ParentVO getParentByStudentId(String studentId) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById() starts");
		}
		
		ParentVO parentVO = (ParentVO) super.getSession().get(ParentVO.class, studentId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveMasterById() ends");
		}
		return parentVO;
	}
	
	@Override
	public ParentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException {
		
		ParentVO parent = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentAdmissionNumber");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from ParentVO WHERE parentMobileNumber1 = :mobileNo");
		
			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("mobileNo", mobileNo);
			
			List<ParentVO> parentList = query.list();
			
			if (parentList != null && parentList.size() > 0)
				parent = parentList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getStudentAdmissionNumber end");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return parent;
		
	}

}
