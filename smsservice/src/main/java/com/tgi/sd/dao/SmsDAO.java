/**
 * 
 */
package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.SmsHistoryVO;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface SmsDAO {

	/**
	 * 
	 * @param smsLog
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsLogVO saveSmsLog(SmsLogVO smsLog) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<SmsLogVO> getAllSmsLog(String schoolId,Integer pageIndex,Integer pageSize) throws SMSBusinessException;
	 
	List<SmsLogVO> getSendTodaySmsCount(String schoolId, Date day) throws SMSBusinessException;
	
	/**
	 * 
	 * @param smsLog
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsHistoryVO saveSmsHistory(SmsHistoryVO smsLog) throws SMSBusinessException;
	
	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	List<SmsHistoryVO> getAllSmsHistory(String smsLogId, String status,Integer pageIndex, Integer pageSize) throws SMSBusinessException;

	long getSmsLogCountBySchoolId(String schoolId) throws SMSBusinessException;

	long getSmsHistoryBySchoolId(String smsLogId,String status) throws SMSBusinessException;

 
}
