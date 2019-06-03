package com.tgi.sd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.LookupVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.RoleAccessVO;
import com.tgi.sd.domain.RoleVO;
import com.tgi.sd.manager.LookupManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/lookup")
public class LookupService extends SMSBaseService {
private static Logger logger = Logger.getLogger(LookupService.class);
	
	private LookupManager lookupManager;	
	
	@RequestMapping(value = "/getLookupByType", method = RequestMethod.GET)
	public ResponseVO getLookupByTypeId(@RequestParam String type,String value, String keyParam) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId() starts");
		}
		
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		List<LookupVO> lookupVOs = new ArrayList<LookupVO>();
		try {
			lookupVOs = lookupManager.getLookupByTypeId(type,value,keyParam);
			responseObjectMap.put("lookupVos", lookupVOs);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getJobDetailById() ends");
		}
		
		return responseVO;
	}
	@RequestMapping(value = "/getLookupList", method = RequestMethod.GET)
	public ResponseVO getLookupByTypeId() throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("getLookupByTypeId() starts");
		}
		
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		 MultiMap lookupVOs = new MultiHashMap();
		try {
			lookupVOs = lookupManager.getLookupList();				
			responseObjectMap.put("lookupVos", lookupVOs);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getJobDetailById() ends");
		}
		
		return responseVO;
	}
	
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	public ResponseVO getRoleList() throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleList() starts");
		}
		
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		List<RoleVO> roleList = new ArrayList<RoleVO>();
		try {
			roleList = lookupManager.getRoleList();				
			responseObjectMap.put("roleList", roleList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleList() ends");
		}
		
		return responseVO;
	}
	
	@RequestMapping(value = "/getRoleAccessByRoleId", method = RequestMethod.GET)
	public ResponseVO getRoleAccessByRoleId(@RequestParam String companyId,@RequestParam String roleId) throws Exception{
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId() starts");
		}
		
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		List<RoleAccessVO> roleAccessList = new ArrayList<RoleAccessVO>();
		try {
			roleAccessList = lookupManager.getRoleAccessByRoleId(companyId,roleId);				
			responseObjectMap.put("roleAccessList", roleAccessList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getRoleAccessByRoleId() ends");
		}
		
		return responseVO;
	}
	
	public LookupManager getLookupManager() {
		return lookupManager;
	}

	public void setLookupManager(LookupManager lookupManager) {
		this.lookupManager = lookupManager;
	}
	

}
