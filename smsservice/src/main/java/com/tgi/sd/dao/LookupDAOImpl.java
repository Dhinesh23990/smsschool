package com.tgi.sd.dao;

import java.util.List;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.LookupVO;
import com.tgi.sd.domain.RoleAccessVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.exception.SMSBusinessException;


@Transactional
public class LookupDAOImpl extends GenericHibernateDAOImpl<LookupVO, String> implements LookupDAO {
	private static Logger logger = Logger.getLogger(LookupDAOImpl.class);

	@Override
	public List<LookupVO> getLookupByTypeId(String type, String value, String keyParam) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId starts");
		}
		List<LookupVO> lookupVos = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			if(StringUtils.isNotBlank(type))
				queryBuilder.append("from LookupVO WHERE type = :type");
			if(StringUtils.isNotBlank(value))
				queryBuilder.append(" and value = :value");
			if(StringUtils.isNotBlank(keyParam))
				queryBuilder.append(" and keyParam = :keyParam");
			Query query = session.createQuery(queryBuilder.toString());
	
			if(StringUtils.isNotBlank(type))
				query.setParameter("type", type);
			
			if(StringUtils.isNotBlank(value))
				query.setParameter("value", value);
			
			if(StringUtils.isNotBlank(keyParam))
				query.setParameter("keyParam", keyParam);
			
			lookupVos = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId ends");
		}
		return lookupVos;
	}

	@Override
	public MultiMap getLookupList() throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupList starts");
		}
		List<LookupVO> lookupVos = null;
		MultiMap lookupMap = new MultiHashMap();

		try {
		
		Session session = getSession();
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("from LookupVO");
		
		Query query = session.createQuery(queryBuilder.toString());
		
		lookupVos  = query.list();
		
		
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupList ends");
		}
		
		return lookupMap;
	}

	@Override
	public List<RoleVO> getRoleList() throws SMSBusinessException {
		
		List<RoleVO> roleVOs = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleList starts");
		}
		
		try {
		
		Session session = getSession();
		StringBuilder queryBuilder = new StringBuilder();
		
		queryBuilder.append("from RoleVO");
		
		Query query = session.createQuery(queryBuilder.toString());
		
		roleVOs  = query.list();
		
		
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleList ends");
		}
		
		return roleVOs;
	}

	@Override
	public List<RoleAccessVO> getRoleAccessByRoleId(String companyId, String roleId)
			throws SMSBusinessException {
		
		List<RoleAccessVO> roleAccessVOs = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId starts");
		}
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from RoleAccessVO WHERE companyId = :companyId");
			queryBuilder.append(" and roleId = :roleId");

			Query query = session.createQuery(queryBuilder.toString());
			
			query.setParameter("companyId", companyId);
			query.setParameter("roleId", roleId);

			roleAccessVOs = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
	
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId ends");
		}
		return roleAccessVOs;
	}

}
