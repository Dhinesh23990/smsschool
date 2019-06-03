package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.LeaveEntitlementDAO;
import com.tgi.sd.dao.LeaveMasterDAO;
import com.tgi.sd.dao.LeavePeriodDAO;
import com.tgi.sd.dao.TeacherDAO;
import com.tgi.sd.domain.LeaveEntitlementVO;
import com.tgi.sd.domain.LeaveMasterVO;
import com.tgi.sd.domain.LeavePeriodVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class LeaveEntitlementManagerImpl implements LeaveEntitlementManager{
	
	private static Logger logger = Logger.getLogger(LeaveEntitlementManagerImpl.class);
	
	private LeaveEntitlementDAO leaveEntitlementDAO;
	
	private LeaveMasterDAO leaveMasterDAO;
	
	private TeacherDAO teacherDAO;
	
	private LeavePeriodDAO leavePeriodDAO;
	
	public TeacherDAO getTeacherDAO() {
		return teacherDAO;
	}

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}
	
	public LeaveMasterDAO getLeaveMasterDAO() {
		return leaveMasterDAO;
	}
	public void setLeaveMasterDAO(LeaveMasterDAO leaveMasterDAO) {
		this.leaveMasterDAO = leaveMasterDAO;
	}
	
	public LeaveEntitlementDAO getLeaveEntitlementDAO() {
		return leaveEntitlementDAO;
	}
	public void setLeaveEntitlementDAO(LeaveEntitlementDAO leaveEntitlementDAO) {
		this.leaveEntitlementDAO = leaveEntitlementDAO;
	}
	
	public LeavePeriodDAO getLeavePeriodDAO() {
		return leavePeriodDAO;
	}
	public void setLeavePeriodDAO(LeavePeriodDAO leavePeriodDAO) {
		this.leavePeriodDAO = leavePeriodDAO;
	}
	
	@Override
	public LeaveEntitlementVO saveLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement starts");
		}
		LeaveEntitlementVO leaveEntitlement = leaveEntitlementDAO.isLeavePeriodAssignedForStaff(leaveEntitlementVO.getLeavePeriodId(), null,
				leaveEntitlementVO.getSchoolId(), leaveEntitlementVO.getStaffId());
		if(leaveEntitlement != null) {
			throw new SMSBusinessException(SMSConstants.LEAVE_NAME_ALREADY_EXISTS);
		}
		leaveEntitlementVO = leaveEntitlementDAO.saveLeaveEntitlement(leaveEntitlementVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveLeaveEntitlement ends");
		}
		return leaveEntitlementVO;
		
	}
	@Override
	public LeaveEntitlementVO getLeaveEntitlementById(String leaveEntitlementId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById Starts");
		}
		
		LeaveEntitlementVO leaveEntitlementVO = leaveEntitlementDAO.getLeaveEntitlementById(leaveEntitlementId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementById Ends");
		}
		
		return leaveEntitlementVO;
	}
	
	@Override
	public Map<String, Object> getAllLeaveEntitlementBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = leaveEntitlementDAO.getLeaveEntitlementCountBySchoolId(schoolId);
		List<LeaveEntitlementVO> leaveEntitlementVOs = leaveEntitlementDAO.getAllLeaveEntitlementBySchoolId(schoolId, pageIndex, pageSize);
		for(LeaveEntitlementVO leaveEntitlementVO : leaveEntitlementVOs){
			LeaveMasterVO leaveMasterVO = leaveMasterDAO.getLeaveMasterById(leaveEntitlementVO.getLeaveMasterId());
			leaveEntitlementVO.setLeaveMasterName(leaveMasterVO.getLeaveName());
			TeacherVO teacher = teacherDAO.getTeacher(leaveEntitlementVO.getStaffId());
			leaveEntitlementVO.setStaffName(teacher.getStaffName());
			LeavePeriodVO leavePeriodVO = leavePeriodDAO.getLeavePeriodById(leaveEntitlementVO.getLeavePeriodId());
			String leaveperiodname = leavePeriodVO.getPeriodName()+"("+ leavePeriodVO.getStartDate() +" - "+ leavePeriodVO.getEndDate() +")";
			leaveEntitlementVO.setLeavePeriodName(leaveperiodname);
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("leaveEntitlementVOs", leaveEntitlementVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllLeaveEntitlementBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getLeaveEntitlementCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementCountBySchoolId Starts");
		}

		long cnt = leaveEntitlementDAO.getLeaveEntitlementCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getLeaveEntitlementCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public LeaveEntitlementVO updateLeaveEntitlement(LeaveEntitlementVO leaveEntitlementVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Starts");
		}
		
		LeaveEntitlementVO leaveEntitlement = leaveEntitlementDAO.isLeavePeriodAssignedForStaff(leaveEntitlementVO.getLeavePeriodId(), leaveEntitlementVO.getId(),
				leaveEntitlementVO.getSchoolId(),leaveEntitlementVO.getStaffId());
		if(leaveEntitlement != null) {
			throw new SMSBusinessException(SMSConstants.LEAVE_PERIOD_ALREADY_ASSIGNED);
		}
		leaveEntitlementVO = leaveEntitlementDAO.updateLeaveEntitlement(leaveEntitlementVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateLeaveEntitlement Ends");
		}
		return leaveEntitlementVO;
	}
	
	@Override
	public boolean deleteLeaveEntitlement(String leaveEntitlementId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Starts");
		}
		
		boolean isDeleted = leaveEntitlementDAO.deleteLeaveEntitlement(leaveEntitlementId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteLeaveEntitlement Ends");
		}
		return isDeleted;
	}
	
}
