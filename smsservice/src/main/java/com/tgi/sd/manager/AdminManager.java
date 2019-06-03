/**
 * 
 */
package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.AdminVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface AdminManager {

	/**
	 * @param Admin
	 * @return
	 * @throws SMSBusinessException 
	 */
	AdminVO saveAdmin(AdminVO Admin) throws SMSBusinessException;

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
	Map<String, Object> getAllAdmin(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException;

	//List<AdminVO> getAdminBySearchString(String searchString, int skip, int limit) throws SMSBusinessException;

	boolean deleteAdmin(String adminId) throws SMSBusinessException;

	AdminVO viewAdmin(String id) throws SMSBusinessException;

	//List<AdminVO> getALLAdminsByClassAndSection(String standard, String section) throws SMSBusinessException;
}
