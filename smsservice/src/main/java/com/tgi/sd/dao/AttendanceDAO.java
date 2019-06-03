/**
 * 
 */
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.AttendanceVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface AttendanceDAO {

	/**
	 * @param attendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	AttendanceVO saveAttendance(AttendanceVO attendance) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	AttendanceVO getAttendance(String id) throws SMSBusinessException;

	
	/**
	 * 
	 * @param attendance
	 * @return
	 * @throws SMSBusinessException 
	 */
	AttendanceVO updateAttendance(AttendanceVO attendance) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<AttendanceVO> getAllAttendance() throws SMSBusinessException;
	
	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Long getAttendanceCount() throws SMSBusinessException;

	/**
	 * 
	 * @param day
	 * @param standard
	 * @param section
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<AttendanceVO> getAttendanceByClassAndSection(String schoolId,Date day, String standard, String section,Date endDate) throws SMSBusinessException;

	List<AttendanceVO> getStudentsByStudentIdAndDate(String schoolId,
			String studentId, Date date, Date endDate) throws SMSBusinessException;


}
