package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.DashboardManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/dashboard")
public class DashboardService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(DashboardService.class);
	private DashboardManager dashboardManager;
	
	public DashboardManager getDashboardManager() {
		return dashboardManager;
	}

	public void setDashboardManager(DashboardManager dashboardManager) {
		this.dashboardManager = dashboardManager;
	}
	
	
	@RequestMapping(value = "/getAllDashboardCount", method = RequestMethod.GET)
	public ResponseVO getAllDashboardCount(@RequestParam String schoolId) {
		ResponseVO responseVO = null;
		try{
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		
			responseObjectsMap = dashboardManager.getAllDashboardCount(schoolId);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getAllSuperAdminDashboardCount", method = RequestMethod.GET)
	public ResponseVO getAllSuperAdminDashboardCount() {
		ResponseVO responseVO = null;
		try{
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		
			responseObjectsMap = dashboardManager.getAllSuperAdminDashboardCount();
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getAllConfigurationBySchoolId", method = RequestMethod.GET)
	public ResponseVO getAllConfigurationBySchoolId(@RequestParam String schoolId) {
		ResponseVO responseVO = null;
		try{
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		
			responseObjectsMap = dashboardManager.getAllConfigurationBySchoolId(schoolId);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getAllSMSCount", method = RequestMethod.GET)
	public ResponseVO getAllSMSCount() {
		ResponseVO responseVO = null;
		try{
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		
			responseObjectsMap = dashboardManager.getAllSMSCount();
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	

}
