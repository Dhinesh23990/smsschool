package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.manager.PaymentModeManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/paymentMode")
public class PaymentModeService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(PaymentModeService.class);
	
	private PaymentModeManager paymentModeManager;
	
	
	public PaymentModeManager getPaymentModeManager() {
		return paymentModeManager;
	}

	public void setPaymentModeManager(PaymentModeManager paymentModeManager) {
		this.paymentModeManager = paymentModeManager;
	}

	@RequestMapping(value = "/savePaymentMode", method = RequestMethod.POST)
	public ResponseVO savePaymentMode(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("savePaymentMode Starts");
		}
		ResponseVO responseVO = null;
		PaymentModeVO paymentModeVO = null;
		try{
			paymentModeVO = (PaymentModeVO) parseObjectFromRequest(requestVO,PaymentModeVO.class);
			if(null != paymentModeVO) {
				paymentModeVO = paymentModeManager.savePaymentMode(paymentModeVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PaymentModeVO",paymentModeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("savePaymentMode Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getPaymentModeById", method = RequestMethod.GET)
	public ResponseVO getPaymentModeById(@RequestParam String paymentModeId){
		if(logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById Starts");
		}
		ResponseVO responseVO = null;
		PaymentModeVO paymentModeVO = null;
		try{
			paymentModeVO = paymentModeManager.getPaymentModeById(paymentModeId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PaymentModeVO", paymentModeVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllPaymentModeBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllPaymentModeBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = paymentModeManager.getAllPaymentModeBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	
	@RequestMapping(value="/getAllPaymentModeByStudentIdClassIdBatchId",method=RequestMethod.GET)
	public ResponseVO getAllPaymentModeByStudentIdClassIdBatchId(@RequestParam String schoolId, String studentId, String classId,String batchId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = paymentModeManager.getAllPaymentModeByStudentIdClassIdBatchId(schoolId, studentId, classId,batchId,pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	
	@RequestMapping(value="/getPaymentModeByStudenIdByTerm",method=RequestMethod.GET)
	public ResponseVO getPaymentModeByStudenIdByTerm(@RequestParam String schoolId, String studentId, String classId,String batchId,String term)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = paymentModeManager.getPaymentModeByStudenIdByTerm(schoolId, studentId, classId,batchId,term);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updatePaymentMode", method = RequestMethod.POST)
	public ResponseVO updatePaymentMode(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Starts");
		}
		ResponseVO responseVO = null;
		try {
			PaymentModeVO paymentModeVO = (PaymentModeVO) parseObjectFromRequest(requestVO, PaymentModeVO.class);
			if (null != paymentModeVO) {
				paymentModeVO = paymentModeManager.updatePaymentMode(paymentModeVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("PaymentModeVO", paymentModeVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deletePaymentMode", method = RequestMethod.DELETE)
	public ResponseVO deletePaymentMode(@RequestParam String paymentModeId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = paymentModeManager.deletePaymentMode(paymentModeId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Ends");
		}
		return responseVO;
	}
	
}
