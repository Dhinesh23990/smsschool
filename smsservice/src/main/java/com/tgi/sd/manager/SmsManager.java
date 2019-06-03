/**
 * 
 */
package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.ComposeSMS;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface SmsManager {

	SmsLogVO sendSMS(ComposeSMS composeSMS) throws SMSBusinessException;

	 Map<String, Object> getAllSmsLog(String schoolId,int pageIndex,int pageSize) throws SMSBusinessException;

	 Map<String, Object> getAllSmsHistoryBySmsLogId(String smsLogId, String status,int pageIndex, int pageSize) throws SMSBusinessException; 
}
