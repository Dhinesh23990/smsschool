 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface LeaveEntitlementManager {

	LeaveEntitlementVO saveLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException;

	LeaveEntitlementVO getLeaveEntitlementById(String leaveEntitlementId) throws SMSBusinessException;

	LeaveEntitlementVO updateLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException;

	boolean deleteLeaveEntitlement(String leaveEntitlementId) throws SMSBusinessException;

	long getLeaveEntitlementCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllLeaveEntitlementBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
