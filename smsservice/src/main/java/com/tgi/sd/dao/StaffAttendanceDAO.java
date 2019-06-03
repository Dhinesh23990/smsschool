/**
 * 
 */
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.StaffAttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface StaffAttendanceDAO {

	/**
	 * @param staffAttendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	StaffAttendanceVO saveStaffAttendance(StaffAttendanceVO staffAttendance) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	StaffAttendanceVO getStaffAttendance(String id) throws SMSBusinessException;

	
	/**
	 * 
	 * @param staffAttendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	StaffAttendanceVO updateStaffAttendance(StaffAttendanceVO staffAttendance) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<StaffAttendanceVO> getAllStaffAttendance() throws SMSBusinessException;
	
	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Long getStaffAttendanceCount() throws SMSBusinessException;

	/**
	 * 
	 * @param day
	 * @param standard
	 * @param section
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<StaffAttendanceVO> getStaffAttendanceByClassAndSection(String schoolId,Date day, String staffId, String attendanceDayAfter,Date endDate) throws SMSBusinessException;


}
