/**
 * 
 */
package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface ParentDAO {

	/**
	 * @param parent
	 * @return
	 * @throws SMSBusinessException 
	 */
	ParentVO saveParent(ParentVO parent) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	ParentVO getParent(String id) throws SMSBusinessException;

	
	/**
	 * 
	 * @param parent
	 * @return
	 * @throws SMSBusinessException 
	 */
	ParentVO updateParent(ParentVO parent) throws SMSBusinessException;

	/**
	 * 
	 * @param instutionId
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<ParentVO> getAllParent() throws SMSBusinessException;

	/**
	 * 
	 * @param emailId
	 * @return
	 * @throws SMSBusinessException 
	 */
	ParentVO getParentByUserOid(String emailId) throws SMSBusinessException;

	/**
	 * 
	 * @param searchString
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<ParentVO> getParentBySearchString(String searchString) throws SMSBusinessException;

	ParentVO getParentByStudentId(String studentId) throws SMSBusinessException;

	ParentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException;

}
