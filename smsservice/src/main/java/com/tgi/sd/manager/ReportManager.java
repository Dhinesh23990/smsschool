/**
 * 
 */
package com.tgi.sd.manager;

import com.tgi.sd.domain.UserCountVO;
import com.tgi.sd.exception.SMSBusinessException;


/**
 * @author SGSAuthour
 *
 */
public interface ReportManager {

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	UserCountVO getUsersCount() throws SMSBusinessException;
}
