 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.exception.SMSBusinessException;

public interface DashboardManager {
	
	public Map<String, Object> getAllDashboardCount(String schoolId) throws SMSBusinessException;

	public Map<String, Object> getAllSuperAdminDashboardCount() throws SMSBusinessException;

	public Map<String, Object> getAllConfigurationBySchoolId(String schoolId) throws SMSBusinessException;

	public Map<String, Object> getAllSMSCount() throws SMSBusinessException;
}
