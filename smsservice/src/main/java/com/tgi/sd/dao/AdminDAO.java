/**
 * 
 */
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface AdminDAO {

	/**
	 * @param admin
	 * @return
	 * @throws SMSBusinessException 
	 */
	AdminVO saveAdmin(AdminVO admin) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	AdminVO getAdmin(String id) throws SMSBusinessException;

	
	/**
	 * 
	 * @param admin
	 * @return
	 * @throws SMSBusinessException 
	 */
	AdminVO updateAdmin(AdminVO admin) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<AdminVO> getAllAdmin(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException;
	
	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Long getAdminCount() throws SMSBusinessException;

	/**
	 * 
	 * @param userOid
	 * @return
	 * @throws SMSBusinessException 
	 */
	AdminVO getAdminByUserOid(String userOid) throws SMSBusinessException;

	/**
	 * 
	 * @param searchString
	 * @param skip
	 * @param limit
	 * @return
	 * @throws SMSBusinessException 
	 */
	//List<AdminVO> getAdminBySearchString(String searchString, int skip, int limit) throws SMSBusinessException;

	//List<AdminVO> getAdminsByClassAndSection(String standard, String section) throws SMSBusinessException;

//	List<AdminVO> getAllAdminByGender(String gender) throws SMSBusinessException;

	//List<AdminVO> getAllAdminBySectionAndGender(String section, String gender) throws SMSBusinessException;

	//List<AdminVO> getAllAdminByClassAndGender(String standard, String gender) throws SMSBusinessException;

	List<AdminVO> getAllAdminsByIds(List<String> receiverIds) throws SMSBusinessException;

	long getAdminCountByName(String adminName, String surName, String schoolId, String id) throws SMSBusinessException;
	
	long getAllAdminCountByDateOfJoiningYear(Date dateOfJoining,String schoolId) throws SMSBusinessException;

	boolean deleteAdmin(String adminId) throws SMSBusinessException;

	long getAdminCountByUserName(String userName, String schoolId, String id) throws SMSBusinessException;

	long getAdminCountBySchoolId(String schoolId) throws SMSBusinessException;

	AdminVO getAdminEmployeeNumber(String employeeNumber, String schoolId,
			String id) throws SMSBusinessException;

	AdminVO getAdminEmailId(String emailId, String id) throws SMSBusinessException;


}
