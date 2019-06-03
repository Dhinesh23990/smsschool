/**
 * 
 */
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface TeacherDAO {

	/**
	 * @param teacher
	 * @return
	 */
	TeacherVO saveTeacher(TeacherVO teacher) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 */
	TeacherVO getTeacher(String id) throws SMSBusinessException;

	TeacherVO getTeacherByStaffId(String schoolId,String staffId) throws SMSBusinessException;
	/**
	 * 
	 * @param teacher
	 * @return
	 */
	TeacherVO updateTeacher(TeacherVO teacher) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 */
	List<TeacherVO> getAllTeacher(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 */
	TeacherVO getTeacherByUserOid(String id) throws SMSBusinessException;

	/**
	 * 
	 * @param teacherIds
	 * @return
	 */
	List<TeacherVO> getAllTeacherByIds(List<String> teacherIds) throws SMSBusinessException;

	/**
	 * 
	 * @param staffName
	 * @param initial
	 * @param string 
	 * @return
	 */
	long getTeacherCountByNameAndInitial(String staffName, String initial, String schoolId, String id) throws SMSBusinessException;

	boolean deleteTeacher(String teacherId) throws SMSBusinessException;

	List<TeacherVO> getAllTeacherByGender(String schoolId,String gender) throws SMSBusinessException;

	long getAllTeacherCountByDateOfJoiningYear(Date dateOfJoining,String schoolId) throws SMSBusinessException;

	long getTeacherCountBySchoolId(String schoolId) throws SMSBusinessException;

	TeacherVO getTeacherEmployeeNumber(String employeeNumber, String schoolId, String id) throws SMSBusinessException;

}
