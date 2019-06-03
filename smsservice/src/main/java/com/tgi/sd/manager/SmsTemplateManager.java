/**
 * 
 */
package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.SmsTemplateVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface SmsTemplateManager {

	/**
	 * @param SmsTemplate
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsTemplateVO saveSmsTemplate(SmsTemplateVO SmsTemplate) throws SMSBusinessException;

	/**
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsTemplateVO getSmsTemplate(String id) throws SMSBusinessException;

	/**
	 * 
	 * @param smsTemplate
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsTemplateVO updateSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException;

	/**
	 * 
	 * @return
	 * @throws SMSBusinessException 
	 */
	Map<String, Object> getAllSmsTemplate(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException;

	boolean deleteSmsTemplate(String smsTemplateId) throws SMSBusinessException;

}
