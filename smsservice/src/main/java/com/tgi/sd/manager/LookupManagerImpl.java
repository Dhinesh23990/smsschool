package com.tgi.sd.manager;

import java.util.List;
import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
import com.tgi.sd.dao.LookupDAO;
import com.tgi.sd.domain.LookupVO;
import com.tgi.sd.domain.RoleAccessVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;

public class LookupManagerImpl implements LookupManager{
	private static Logger logger = Logger.getLogger(LookupManagerImpl.class);

	private LookupDAO lookupDAO;

	@Override
	public List<LookupVO>  getLookupByTypeId(String type, String value, String keyParam) throws SMSBusinessException {
		if(logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId() starts");
		}
		List<LookupVO> lookupVOs = lookupDAO.getLookupByTypeId(type,value,keyParam);
		if(lookupVOs == null) {
			throw new SMSBusinessException(ErrorConstants.LOOKUP_INFORMATION_NOT_FOUND);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId() ends");
		}
		return lookupVOs;
	}

	public LookupDAO getLookupDAO() {
		return lookupDAO;
	}

	public void setLookupDAO(LookupDAO lookupDAO) {
		this.lookupDAO = lookupDAO;
	}

	@Override
	public MultiMap getLookupList() throws SMSBusinessException {
		if(logger.isDebugEnabled()) {
			logger.debug("getLookupList() starts");
		}
		MultiMap lookupVOs = lookupDAO.getLookupList();
		if(lookupVOs == null) {
			throw new SMSBusinessException(ErrorConstants.LOOKUP_INFORMATION_NOT_FOUND);
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("getLookupList() ends");
		}
		return lookupVOs;
	}

	@Override
	public List<RoleVO> getRoleList() throws SMSBusinessException {
		if(logger.isDebugEnabled()) {
			logger.debug("getRoleList() starts");
		}
		List<RoleVO> roleVOs = lookupDAO.getRoleList();
				
		if(logger.isDebugEnabled()) {
			logger.debug("getRoleList() ends");
		}
		return roleVOs;
	}

	@Override
	public List<RoleAccessVO> getRoleAccessByRoleId(String companyId,String roleId)
			throws SMSBusinessException {
		if(logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId() starts");
		}
		List<RoleAccessVO> roleAccessVOs = lookupDAO.getRoleAccessByRoleId(companyId,roleId);
				
		if(logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId() ends");
		}
		return roleAccessVOs;
	}

}
