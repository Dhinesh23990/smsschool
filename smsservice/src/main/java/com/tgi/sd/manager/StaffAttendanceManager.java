/**
 * 
 */
package com.tgi.sd.manager;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.StaffAttendanceSummaryVO;
import com.tgi.sd.domain.StaffAttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface StaffAttendanceManager {

	/**
	 * @param StaffAttendance
	 * @return
	 */
	StaffAttendanceVO saveStaffAttendance(StaffAttendanceVO StaffAttendance) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 */
	StaffAttendanceVO getStaffAttendance(String id) throws SMSBusinessException;

	/**
	 * 
	 * @param staffAttendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	StaffAttendanceVO updateStaffAttendance(StaffAttendanceVO staffAttendance) throws SMSBusinessException;

	List<StaffAttendanceSummaryVO>  getTeachersByClassAndSection(String schoolId, Date date, String staffId, String attendanceDayAfter) throws SMSBusinessException;

	void saveBulkStaffAttendance(List<StaffAttendanceVO> staffAttendanceList) throws SMSBusinessException;

	List<StaffAttendanceSummaryVO> getAllStaffAttendance() throws SMSBusinessException;

	List<StaffAttendanceSummaryVO> getTeachersAttentance(String schoolId,Date startdate,
			String staffId, String dayAfter, Date endDate) throws SMSBusinessException;
}
