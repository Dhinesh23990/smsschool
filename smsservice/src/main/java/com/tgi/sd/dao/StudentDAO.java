/**
 * 
 */
package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.BulkImportStudentDetailVO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface StudentDAO {

	/**
	 * @param student
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO saveStudent(StudentVO student) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO getStudent(String id) throws SMSBusinessException;
	
	StudentVO getStudentView(String studentId) throws SMSBusinessException;
	
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
	List<StudentVO> getAllStudent(String schoolId,Integer pageIndex,Integer pageSize) throws SMSBusinessException;
	
	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Long getStudentCount() throws SMSBusinessException;

	/**
	 * 
	 * @param userOid
	 * @return
	 * @throws SMSBusinessException 
	 */
	StudentVO getStudentByUserOid(String userOid) throws SMSBusinessException;

	/**
	 * 
	 * @param searchString
	 * @param skip
	 * @param limit
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<StudentVO> getStudentBySearchString(String searchString, int skip, int limit) throws SMSBusinessException;

	List<StudentVO> getStudentsByClassAndSection(String schoolId,String standard, String section) throws SMSBusinessException;

	List<StudentVO> getAllStudentByGender(String schoolId,String gender) throws SMSBusinessException;

	List<StudentVO> getAllStudentBySectionAndGender(String section, String gender) throws SMSBusinessException;

	List<StudentVO> getAllStudentByClassAndGender(String standard, String gender) throws SMSBusinessException;

	List<StudentVO> getAllStudentsByIds(List<String> receiverIds) throws SMSBusinessException;

	long getStudentCountByName(String studentName, String surName, String schoolId,String id) throws SMSBusinessException;
	
	long getStudentCountBySchoolId(String schoolId) throws SMSBusinessException;

	boolean deleteStudent(String studentId) throws SMSBusinessException;

	List<StudentVO> getAllStudentByListClassAndGender(String schoolId, List<String> className,
			String gender) throws SMSBusinessException;

	List<StudentVO> getAllStudentByListSectionAndGender(String schoolId,
			List<String> sectionList, String standard, String gender) throws SMSBusinessException;

	long getAllStudentCountByBatchCode(String batchId, String classId, String schoolId) throws SMSBusinessException;

	StudentVO getStudentAdmissionNumber(String admissionNumber, String schoolId, String id) throws SMSBusinessException;

	StudentVO getStudentByIdOrNo(String schoolId, String studentId) throws SMSBusinessException;

	StudentVO getStudentByStudentId(String studentId, String schoolId)  throws SMSBusinessException;

	BulkImportStudentDetailVO saveBulkImportStudent(BulkImportStudentDetailVO bulkImportStudentDetailVO)
			throws SMSBusinessException;

	List<BulkImportStudentDetailVO> getUploadStudentsByClassAndSection(String schoolId,
			String classId, String sectionId, String batchId) throws SMSBusinessException;

	BulkImportStudentDetailVO updateBulkImportStudent(
			BulkImportStudentDetailVO bulkImportStudentDetailVO) throws SMSBusinessException;

	List<StudentVO> getAllStudentVOByBatchCode(String batchId, String classId,
			String sectionId, String schoolId ) throws SMSBusinessException;

	StudentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException;

	List<String> getSchoolIdsByParentMobileNo(String mobileNo) throws SMSBusinessException;

	List<StudentVO> getStudentsByMobileNoAndSchoolId(String schoolId, String mobileNo) throws SMSBusinessException;
	
	StudentVO getStudentById(String schoolId, String studentid)  throws SMSBusinessException;
	
	
	public StudentVO getStudentBySearchString(String schoolId,String classId, String batchId,String sectionId, String searchString) throws SMSBusinessException;


}
