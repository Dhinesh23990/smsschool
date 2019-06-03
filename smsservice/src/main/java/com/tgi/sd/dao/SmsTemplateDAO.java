/**
 * 
 */
package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.SmsTemplateVO;
import com.tgi.sd.exception.SMSBusinessException;

/**
 * @author SGSAuthour
 *
 */
public interface SmsTemplateDAO {

	/**
	 * @param smsTemplate
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsTemplateVO saveSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException;

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
	List<SmsTemplateVO> getAllSmsTemplate(String schoolId,Integer pageIndex, Integer pageSize) throws SMSBusinessException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SMSBusinessException 
	 */
	SmsTemplateVO getSmsTemplateByUserOid(String id) throws SMSBusinessException;

	boolean deleteSmsTemplate(String smsTemplateId) throws SMSBusinessException;

	long getSmsTemplateCountBySchoolId(String schoolId) throws SMSBusinessException;

}
