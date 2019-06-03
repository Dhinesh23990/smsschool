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

import com.tgi.sd.domain.AttendanceSummaryVO;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.manager.AttendanceManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class AttendanceService  extends SMSBaseService {

	private static Logger logger = Logger.getLogger(AttendanceService.class);

	AttendanceManager attendanceManager;
	
	public AttendanceManager getAttendanceManager() {
		return attendanceManager;
	}

	public void setAttendanceManager(AttendanceManager attendanceManager) {
		this.attendanceManager = attendanceManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO saveAttendance(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveAttendance :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AttendanceVO attendanceVO = (AttendanceVO) parseObjectFromRequest(requestVO,AttendanceVO.class);
			attendanceVO = attendanceManager.saveAttendance(attendanceVO);
			responseObjectMap.put("attendanceVO", attendanceVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveAttendance :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.SAVE_BULK_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO saveBulkAttendance(@RequestBody List<AttendanceVO> attendance) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveBulkAttendance :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			attendanceManager.saveBulkAttendance(attendance);
			responseObjectMap.put("isBulkAttendanceSaved", true);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End saveBulkAttendance :::");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.GET_STUDENTS_BY_CLASS_AND_SECTION, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getStudentsByClassAndSection(@RequestParam String schoolId,@RequestParam Date date,@RequestParam String standard,
			@RequestParam String section) {
		
		List<AttendanceSummaryVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllAttendance");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			attendanceList =  attendanceManager.getStudentsByClassAndSection(schoolId,date,standard,section);
			responseObjectMap.put("attendanceList", attendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllAttendance");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENTS_BY_CLASS_SECTION_DATE, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getStudentsByClassAndSectionAndDate(@RequestParam String schoolId,@RequestParam Date date,@RequestParam String standard,@RequestParam String section,Date endDate) {
		
		List<AttendanceSummaryVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByClassAndSectionAndDate");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			attendanceList =  attendanceManager.getStudentsByClassAndSectionAndDate(schoolId,date,standard,section, endDate);
			responseObjectMap.put("attendanceList", attendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByClassAndSectionAndDate");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_STUDENTS_BY_STUDENTID_DATE, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getStudentsByStudentIdAndDate(@RequestParam String schoolId,
			@RequestParam String studentId,@RequestParam Date date,Date endDate) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentsByStudentIdAndDate");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap =  attendanceManager.getStudentsByStudentIdAndDate(schoolId,studentId,date,endDate);
			//responseObjectMap.put("attendanceList", attendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentsByStudentIdAndDate");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.UPDATE_ATTENDANCE, method = RequestMethod.POST)
	public @ResponseBody ResponseVO updateAttendance(@RequestBody String requestVO) throws ValidationException{
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateAttendance :::" );
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			AttendanceVO attendanceVO = (AttendanceVO) parseObjectFromRequest(requestVO,AttendanceVO.class);
			attendanceVO = attendanceManager.updateAttendance(attendanceVO);
			responseObjectMap.put("attendanceVO", attendanceVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAttendance :::" );
		}
		return responseVO;	
	}	
	
	/*	@RequestMapping(value = EndPointConstants.GET_STUDENT, method = RequestMethod.GET)
	public @ResponseBody AttendanceVO getAttendance(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAttendance Id:::" + id);
		}
		AttendanceVO attendance =  attendanceManager.getAttendance(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAttendance :::" + attendance.getId());
		}
		return attendance;
	}
	
*/
	
	@RequestMapping(value = EndPointConstants.GET_ALL_ATTENDANCE, method = RequestMethod.GET)
	public @ResponseBody ResponseVO getAllAttendance() throws SMSBusinessException {
		
		ResponseVO responseVO = null;
		List<AttendanceSummaryVO> attendanceList = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			attendanceList =  attendanceManager.getAllAttendance();
			responseObjectMap.put("attendanceList", attendanceList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("End updateAttendance :::" );
		}
		return responseVO;	
	}	
/*	
	@RequestMapping(value = EndPointConstants.SEARCH_STUDENT, method = RequestMethod.POST)
	public @ResponseBody List<AttendanceVO> searchAttendance(@RequestParam String searchString,@RequestParam int skip,@RequestParam int pageSize) {
		
		List<AttendanceVO> attendanceList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start searchAttendance");
		}
		attendanceList =  attendanceManager.getAttendanceBySearchString(searchString, skip, pageSize);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End searchAttendance");
		}
		return attendanceList;
	}	*/
}
