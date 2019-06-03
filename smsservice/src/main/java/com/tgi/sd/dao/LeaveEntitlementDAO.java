package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface LeaveEntitlementDAO {

	LeaveEntitlementVO saveLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException;

	LeaveEntitlementVO getLeaveEntitlementById(String leaveEntitlementId) throws SMSBusinessException;

	List<LeaveEntitlementVO> getAllLeaveEntitlementBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getLeaveEntitlementCountBySchoolId(String schoolId) throws SMSBusinessException;

	LeaveEntitlementVO updateLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException;

	boolean deleteLeaveEntitlement(String leaveEntitlementId) throws SMSBusinessException;

	LeaveEntitlementVO isLeavePeriodAssignedForStaff(String leavePeriodId, String id, String schoolId, String staffId)
			throws SMSBusinessException;

	List<LeaveEntitlementVO> getAllLeaveEntitlementBySchoolIdAndStaffId(String schoolId,
			String id) throws SMSBusinessException;

}
