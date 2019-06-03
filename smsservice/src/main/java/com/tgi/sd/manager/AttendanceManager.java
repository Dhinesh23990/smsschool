/**
 * 
 */
package com.tgi.sd.manager;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.AttendanceSummaryVO;
import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface AttendanceManager {

	/**
	 * @param Attendance
	 * @return
	 */
	AttendanceVO saveAttendance(AttendanceVO Attendance) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 */
	AttendanceVO getAttendance(String id) throws SMSBusinessException;

	/**
	 * 
	 * @param attendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	AttendanceVO updateAttendance(AttendanceVO attendance) throws SMSBusinessException;

	List<AttendanceSummaryVO> getStudentsByClassAndSection(String schoolId, Date date, String standard, String section) throws SMSBusinessException;

	void saveBulkAttendance(List<AttendanceVO> attendanceList) throws SMSBusinessException;

	List<AttendanceSummaryVO> getAllAttendance() throws SMSBusinessException;

	List<AttendanceSummaryVO> getStudentsByClassAndSectionAndDate(String schoolId,Date date,
			String standard, String section, Date endDate) throws SMSBusinessException;

	Map<String, Object> getStudentsByStudentIdAndDate(String schoolId,
			String studentId, Date date, Date endDate) throws SMSBusinessException;
}
