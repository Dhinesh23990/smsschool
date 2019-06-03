/**
 * 
 */
package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tgi.sd.dao.SmsTemplateDAO;
import com.tgi.sd.domain.SmsTemplateVO;
import com.tgi.sd.exception.ErrorConstants;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

/**
 * @author SGSAuthour
 *
 */
@Component
public class SmsTemplateManagerImpl implements SmsTemplateManager {
	
	private static Logger logger = Logger.getLogger(SmsTemplateManagerImpl.class);

	SmsTemplateDAO smsTemplateDAO;
	
	public SmsTemplateDAO getSmsTemplateDAO() {
		return smsTemplateDAO;
	}
	
	public void setSmsTemplateDAO(SmsTemplateDAO smsTemplateDAO) {
		this.smsTemplateDAO = smsTemplateDAO;
	}

	public SmsTemplateVO saveSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException {
		smsTemplate.setId(UUID.randomUUID().toString());
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveSmsTemplate :::" );
		}
		smsTemplate = smsTemplateDAO.saveSmsTemplate(smsTemplate);
		if (logger.isDebugEnabled()) {
			logger.debug("End saveSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}

	public SmsTemplateVO getSmsTemplate(String id) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSmsTemplate Id:::" + id);
		}
		
		SmsTemplateVO smsTemplate =  smsTemplateDAO.getSmsTemplate(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}
	
	@Override
	public SmsTemplateVO updateSmsTemplate(SmsTemplateVO smsTemplate) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateSmsTemplate :::");
		}
		if(StringUtils.isBlank(smsTemplate.getId())){
    		throw new SMSBusinessException(ErrorConstants.ID_NOT_EXISTS_WHILE_UPDATING);
		}
		smsTemplate = smsTemplateDAO.updateSmsTemplate(smsTemplate);
		if (logger.isDebugEnabled()) {
			logger.debug("End updateSmsTemplate :::" + smsTemplate.getId());
		}
		return smsTemplate;
	}

	@Override
	public Map<String, Object> getAllSmsTemplate(String schoolId,int pageIndex, int pageSize) throws SMSBusinessException {
		List<SmsTemplateVO> smsTemplateList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsTemplate");
		}
		smsTemplateList =  smsTemplateDAO.getAllSmsTemplate(schoolId,pageIndex,pageSize);
		
		long totalRecords = smsTemplateDAO.getSmsTemplateCountBySchoolId(schoolId);
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("smsTemplateList", smsTemplateList);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsTemplate");
		}
		return responseObjectsMap;	
	}
	
	public boolean deleteSmsTemplate(String smsTemplateId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Starts");
		}
		
		boolean isDeleted = smsTemplateDAO.deleteSmsTemplate(smsTemplateId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Ends");
		}
		return isDeleted;
	}

}
