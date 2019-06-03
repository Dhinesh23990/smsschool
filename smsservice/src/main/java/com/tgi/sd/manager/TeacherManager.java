/**
 * 
 */
package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface TeacherManager {

	/**
	 * @param Teacher
	 * @return
	 * @throws SMSBusinessException 
	 */
	TeacherVO saveTeacher(TeacherVO Teacher) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	TeacherVO getTeacher(String id) throws SMSBusinessException;

	TeacherVO updateTeacher(TeacherVO teacher) throws SMSBusinessException;

	Map<String, Object> getAllTeacher(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException;

	boolean deleteTeacher(String teacherId) throws SMSBusinessException;

	TeacherVO viewTeacher(String teacherId) throws SMSBusinessException;

	List<TeacherVO> getAllTeacherByGender(String schoolId,String gender) throws SMSBusinessException;

}
