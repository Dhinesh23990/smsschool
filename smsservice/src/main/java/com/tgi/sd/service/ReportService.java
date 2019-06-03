package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.UserCountVO;
import com.tgi.sd.manager.ReportManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class ReportService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(ReportService.class);

	ReportManager reportManager;
	
	public ReportManager getReportManager() {
		return reportManager;
	}


	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}


	@RequestMapping(value = EndPointConstants.GET_USER_COUNT, method = RequestMethod.GET)
	public ResponseVO getUsersCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getUsersCount");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			UserCountVO userCountVO =  reportManager.getUsersCount();
			responseObjectMap.put("userCountVO", userCountVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getUsersCount");
		}
		return responseVO;
	}
	
	
}
