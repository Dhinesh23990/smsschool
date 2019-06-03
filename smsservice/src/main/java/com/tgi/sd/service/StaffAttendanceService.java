package com.tgi.sd.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.ValidationException;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.StaffAttendanceSummaryVO;
import com.tgi.sd.domain.StaffAttendanceVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.manager.StaffAttendanceManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class StaffAttendanceService  extends SMSBaseService {

	private static Logger logger = Logger.getLogger(StaffAttendanceService.class);

	StaffAttendanceManager staffAttendanceManager;
	
	public StaffAttendanceManager getStaffAttendanceManager() {
		return staffAttendanceManager;
	}

	public void setStaffAttendanceManager(StaffAttendanceManager staffAttendanceManager) {
		this.staffAttendanceManager = staffAttendanceManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_TEACHER_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO saveStaffAttendance(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveStaffAttendance :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StaffAttendanceVO staffAttendanceVO = (StaffAttendanceVO) parseObjectFromRequest(requestVO,StaffAttendanceVO.class);
			staffAttendanceVO = staffAttendanceManager.saveStaffAttendance(staffAttendanceVO);
			responseObjectMap.put("staffAttendanceVO", staffAttendanceVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveStaffAttendance :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.SAVE_TEACHER_BULK_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO saveBulkStaffAttendance(@RequestBody List<StaffAttendanceVO> staffAttendance) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveBulkStaffAttendance :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			staffAttendanceManager.saveBulkStaffAttendance(staffAttendance);
			responseObjectMap.put("isBulkStaffAttendanceSaved", true);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End saveBulkStaffAttendance :::");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.GET_TEACHERS_BY_CLASS_AND_SECTION, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getStudentsByClassAndSection(@RequestParam String schoolId,@RequestParam Date date,
			@RequestParam String staffId,@RequestParam String attendanceDayAfter) {
		
		List<StaffAttendanceSummaryVO> staffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllStaffAttendance");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			staffAttendanceList =  staffAttendanceManager.getTeachersByClassAndSection(schoolId,date,staffId,attendanceDayAfter);
			responseObjectMap.put("staffAttendanceList", staffAttendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllStaffAttendance");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_TEACHERS_BY_CLASS_SECTION_DATE, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getTeachersAttentance(@RequestParam String schoolId,Date startdate,@RequestParam String staffId,
			String dayAfter,Date endDate) {
		
		List<StaffAttendanceSummaryVO> staffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getTeachersAttentance");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			staffAttendanceList =  staffAttendanceManager.getTeachersAttentance(schoolId,startdate,staffId,dayAfter, endDate);
			responseObjectMap.put("staffAttendanceList", staffAttendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getTeachersAttentance");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.UPDATE_TEACHER_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO updateStaffAttendance(@RequestBody String requestVO) throws ValidationException{
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateStaffAttendance :::" );
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			StaffAttendanceVO staffAttendanceVO = (StaffAttendanceVO) parseObjectFromRequest(requestVO,StaffAttendanceVO.class);
			staffAttendanceVO = staffAttendanceManager.updateStaffAttendance(staffAttendanceVO);
			responseObjectMap.put("staffAttendanceVO", staffAttendanceVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStaffAttendance :::" );
		}
		return responseVO;	
	}	
	
	/*	@RequestMapping(value = EndPointConstants.GET_STUDENT, method = RequestMethod.GET)
	public @ResponseBody StaffAttendanceVO getStaffAttendance(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStaffAttendance Id:::" + id);
		}
		StaffAttendanceVO staffAttendance =  staffAttendanceManager.getStaffAttendance(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getStaffAttendance :::" + staffAttendance.getId());
		}
		return staffAttendance;
	}
	
*/
	
	@RequestMapping(value = EndPointConstants.GET_ALL_TEACHER_ATTENDANCE, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getAllStaffAttendance() throws SMSBusinessException {
		
		ResponseVO responseVO = null;
		List<StaffAttendanceSummaryVO> staffAttendanceList = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			staffAttendanceList =  staffAttendanceManager.getAllStaffAttendance();
			responseObjectMap.put("staffAttendanceList", staffAttendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateStaffAttendance :::" );
		}
		return responseVO;	
	}	
/*	
	@RequestMapping(value = EndPointConstants.SEARCH_STUDENT, method = RequestMethod.POST)
	public @ResponseBody List<StaffAttendanceVO> searchStaffAttendance(@RequestParam String searchString,@RequestParam int skip,@RequestParam int pageSize) {
		
		List<StaffAttendanceVO> staffAttendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start searchStaffAttendance");
		}
		staffAttendanceList =  staffAttendanceManager.getStaffAttendanceBySearchString(searchString, skip, pageSize);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End searchStaffAttendance");
		}
		return staffAttendanceList;
	}	*/
}
