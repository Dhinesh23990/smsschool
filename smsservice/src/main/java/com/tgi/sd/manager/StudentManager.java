/**
 * 
 */
package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.tgi.sd.domain.BulkImportStudentDetailVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.TeacherVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface StudentManager {

	/**
	 * @param Student
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO saveStudent(StudentVO Student) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO getStudent(String id) throws SMSBusinessException;

	/**
	 * 
	 * @param student
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO updateStudent(StudentVO student) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Map<String, Object> getAllStudent(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException;

	List<StudentVO> getStudentBySearchString(String searchString, int skip, int limit) throws SMSBusinessException;

	boolean deleteStudent(String studentId) throws SMSBusinessException;

	List<StudentVO> getALLStudentsByClassAndSection(String schoolId,String standard, String section) throws SMSBusinessException;

	StudentVO viewStudent(String id) throws SMSBusinessException;
	StudentVO getviewStudent(String id) throws SMSBusinessException;

	List<StudentVO> getALLStudentsByClassAndSectionAndGender(String schoolId,String standard,
			String section, String gender) throws SMSBusinessException;

	StudentVO getStudentByIdOrNo(String schoolId, String studentId) throws SMSBusinessException;

	Map<String, Object> bulkImportStudentDetails(String schoolId,String classlId,String sectionId,
			MultipartFile[] file) throws SMSBusinessException, Exception;

	List<BulkImportStudentDetailVO> getUploadStudentsByClassAndSection(String schoolId,
			String classId, String sectionId, String batchId) throws SMSBusinessException;

	BulkImportStudentDetailVO updateBulkImportStudent(
			BulkImportStudentDetailVO bulkImportStudentDetailVO) throws SMSBusinessException;

	List<StudentVO> getStudentsByMobileNoAndSchoolId(String schoolId, String mobileNo) throws SMSBusinessException;

	List<TeacherVO> getStudentsAllocateTeacher(String studentId, String schoolId) throws SMSBusinessException;
}
