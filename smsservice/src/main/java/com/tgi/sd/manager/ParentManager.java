/**
 * 
 */
package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface ParentManager {

	/**
	 * @param parent
	 * @return
	 * @throws SMSBusinessException 
	 */
	ParentVO saveParent(ParentVO parent) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
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
	 * @return
	 */
	List<ParentVO> getAllParent() throws SMSBusinessException;

	ParentVO getParentByMobileNo(String mobileNo) throws SMSBusinessException;

	Map<String, Object> registerParentByMobileNo(String mobileNo) throws SMSBusinessException;

	Map<String, Object> getSchoolListByParentMobileNo(String mobileNo) throws SMSBusinessException;
}
