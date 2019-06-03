package com.tgi.sd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ComposeSMS;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.SmsHistoryVO;
import com.tgi.sd.domain.SmsLogVO;
import com.tgi.sd.domain.SmsTemplateVO;
import com.tgi.sd.manager.SmsManager;
import com.tgi.sd.manager.SmsTemplateManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class SMSTemplateService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(SMSTemplateService.class);

	SmsManager smsManager;
	
	SmsTemplateManager smsTemplateManager;
	
	public SmsTemplateManager getSmsTemplateManager() {
		return smsTemplateManager;
	}

	public void setSmsTemplateManager(SmsTemplateManager smsTemplateManager) {
		this.smsTemplateManager = smsTemplateManager;
	}
	
	public SmsManager getSmsManager() {
		return smsManager;
	}

	public void setSmsManager(SmsManager smsManager) {
		this.smsManager = smsManager;
	}
	
	@RequestMapping(value = EndPointConstants.SEND_SMS, method = RequestMethod.POST)
	public ResponseVO sendSMS(@RequestBody ComposeSMS composeSMS){
		if (logger.isDebugEnabled()) {
			logger.debug("Start sendSMS :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			SmsLogVO smsLog = smsManager.sendSMS(composeSMS);
			responseObjectMap.put("smsLog", smsLog);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error("Error in sendSMS Service" + e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End sendSMS :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_ALL_SMS_LOG, method = RequestMethod.GET)
	public ResponseVO getAllSmsLog(@RequestParam String schoolId,int pageIndex, int pageSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsLog :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap = smsManager.getAllSmsLog(schoolId,pageIndex,pageSize);
			//responseObjectMap.put("smsLogList", smsLogList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsLog :::");
		}
		return responseVO;
	}
	

	@RequestMapping(value = EndPointConstants.GET_ALL_SMS_HISTORY_BY_LOG_ID, method = RequestMethod.GET)
	public ResponseVO getAllSmsHistoryBySmsLogId(@RequestParam String smsLogId , @RequestParam String status,int pageIndex, int pageSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsHistoryBySmsLogId :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap = smsManager.getAllSmsHistoryBySmsLogId(smsLogId, status,pageIndex,pageSize);
			//responseObjectMap.put("smsLogList", smsLogList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsHistoryBySmsLogId :::");
		}
		return responseVO;
	}
	
	
	@RequestMapping(value = EndPointConstants.GET_SMSTEMPLATE, method = RequestMethod.GET)
	public ResponseVO getSmsTemplate(@RequestParam String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getSmsTemplate Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			SmsTemplateVO smsLog =  smsTemplateManager.getSmsTemplate(id);
			responseObjectMap.put("smsLog", smsLog);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getSmsTemplate :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.SAVE_SMSTEMPLATE, method = RequestMethod.POST)
	public ResponseVO saveSmsTemplate(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveSmsTemplate :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			SmsTemplateVO smsTemplateVO = (SmsTemplateVO) parseObjectFromRequest(requestVO,SmsTemplateVO.class);
			if(null != smsTemplateVO) {
				smsTemplateVO = smsTemplateManager.saveSmsTemplate(smsTemplateVO);
				responseObjectMap.put("smsTemplateVO", smsTemplateVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveSmsTemplate :::");
		}
		return responseVO;	
	}		
	
	@RequestMapping(value = EndPointConstants.UPDATE_SMSTEMPLATE, method = RequestMethod.POST)
	public ResponseVO updateSmsTemplate(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateSmsTemplate :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			SmsTemplateVO smsTemplateVO = (SmsTemplateVO) parseObjectFromRequest(requestVO,SmsTemplateVO.class);
			if(null != smsTemplateVO) {
				smsTemplateVO = smsTemplateManager.updateSmsTemplate(smsTemplateVO);
				responseObjectMap.put("smsTemplateVO", smsTemplateVO);
				responseVO = createSuccessResponseVO(responseObjectMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateSmsTemplate :::");
		}
		return responseVO;	
	}	
	
	@RequestMapping(value = EndPointConstants.GET_ALL_SMSTEMPLATE, method = RequestMethod.GET)
	public ResponseVO getAllSmsTemplate(@RequestParam String schoolId,int pageIndex, int pageSize) {
		
		List<SmsTemplateVO> smsLogList = null;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllSmsTemplate");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			responseObjectMap =  smsTemplateManager.getAllSmsTemplate(schoolId,pageIndex,pageSize);
			//responseObjectMap.put("smsLogList", smsLogList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllSmsTemplate");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.DELETE_SMSTEMPLATE, method = RequestMethod.DELETE)
	public ResponseVO deleteSmsTemplate(@RequestParam String smsTemplateId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = smsTemplateManager.deleteSmsTemplate(smsTemplateId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSmsTemplate Ends");
		}
		return responseVO;
	}
	
	
}
