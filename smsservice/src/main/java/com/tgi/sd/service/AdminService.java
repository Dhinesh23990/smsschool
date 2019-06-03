package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.AdminManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class AdminService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(AdminService.class);

	AdminManager adminManager;
	
	public AdminManager getAdminManager() {
		return adminManager;
	}

	public void setAdminManager(AdminManager adminManager) {
		this.adminManager = adminManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_ADMIN, method = RequestMethod.POST)
	public ResponseVO saveAdmin(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAdmin :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AdminVO adminVO = (AdminVO) parseObjectFromRequest(requestVO,AdminVO.class);
			if(null != adminVO) {
				adminVO = adminManager.saveAdmin(adminVO);
				responseObjectMap.put("adminVO", adminVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAdmin :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_ADMIN, method = RequestMethod.GET)
	public ResponseVO getAdmin(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAdmin Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AdminVO adminVO =  adminManager.getAdmin(id);
			responseObjectMap.put("adminVO", adminVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAdmin :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.VIEW_ADMIN, method = RequestMethod.GET)
	public ResponseVO viewAdmin(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start viewAdmin Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AdminVO adminVO =  adminManager.viewAdmin(id);
			responseObjectMap.put("adminVO", adminVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End viewAdmin :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.UPDATE_ADMIN, method = RequestMethod.POST)
	public ResponseVO updateAdmin(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAdmin :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AdminVO adminVO = (AdminVO) parseObjectFromRequest(requestVO,AdminVO.class);
			if(null != adminVO) {
				adminVO = adminManager.updateAdmin(adminVO);
				responseObjectMap.put("adminVO", adminVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAdmin :::");
		}
		return responseVO;	
	}	
	
	@RequestMapping(value = EndPointConstants.GET_ALL_ADMIN, method = RequestMethod.GET)
	public ResponseVO getAllAdmin(@RequestParam String schoolId,int pageIndex, int pageSize) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAdmin");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap =  adminManager.getAllAdmin(schoolId,pageIndex,pageSize);
			//responseObjectMap.put("adminList", adminList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllAdmin");
		}
		return responseVO;
	}	
	
	/*
	@RequestMapping(value = EndPointConstants.SEARCH_STUDENT, method = RequestMethod.POST)
	public ResponseVO searchAdmin(@RequestParam String searchString,@RequestParam int skip,@RequestParam int pageSize) {
		
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start searchAdmin");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			adminList =  adminManager.getAdminBySearchString(searchString, skip, pageSize);
			responseObjectMap.put("adminList", adminList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End searchAdmin");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_ALLSTUDENTS_BY_CLASS_AND_SECTION, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getALLAdminsByClassAndSection(@RequestParam String standard,@RequestParam String section) {
		
		List<AdminVO> adminList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getALLAdminsByClassAndSection");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			adminList =  adminManager.getALLAdminsByClassAndSection(standard,section);
			responseObjectMap.put("adminList", adminList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getALLAdminsByClassAndSection");
		}
		return responseVO;
	}	
	
	*/
	@RequestMapping(value = EndPointConstants.DELETE_ADMIN, method = RequestMethod.DELETE)
	public ResponseVO deleteAdmin(@RequestParam String adminId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteAdmin Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = adminManager.deleteAdmin(adminId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteAdmin Ends");
		}
		return responseVO;
	}
	
	
}
