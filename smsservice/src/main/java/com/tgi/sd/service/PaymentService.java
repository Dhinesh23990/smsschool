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
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.manager.PaymentManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/payment")
public class PaymentService extends SMSBaseService {

	private static Logger logger = Logger.getLogger(PaymentService.class);

	private PaymentManager paymentManager;

	public PaymentManager getPaymentManager() {
		return paymentManager;
	}

	public void setPaymentManager(PaymentManager paymentManager) {
		this.paymentManager = paymentManager;
	}

	@RequestMapping(value = "/savePayment", method = RequestMethod.POST)
	public ResponseVO savePayment(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("savePayment Starts");
		}
		ResponseVO responseVO = null;
		PaymentVO paymentVO = null;
		try {
			paymentVO = (PaymentVO) parseObjectFromRequest(requestVO, PaymentVO.class);
			if (null != paymentVO) {
				paymentVO = paymentManager.savePayment(paymentVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PaymentVO", paymentVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("savePayment Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public ResponseVO updatePayment(@RequestBody String requestVO) {

		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Starts");
		}
		ResponseVO responseVO = null;
		try {
			PaymentVO paymentVO = (PaymentVO) parseObjectFromRequest(requestVO, PaymentVO.class);
			if (null != paymentVO) {
				paymentVO = paymentManager.updatePayment(paymentVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("PaymentVO", paymentVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getPaymentById", method = RequestMethod.GET)
	public ResponseVO getPaymentById(@RequestParam String paymentId) {

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById Starts");
		}
		ResponseVO responseVO = null;
		PaymentVO paymentVO = null;
		try {
			paymentVO = paymentManager.getPaymentById(paymentId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("PaymentVO", paymentVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getAllPaymentBySchoolId", method = RequestMethod.GET)
	public ResponseVO getAllPaymentBySchoolId(@RequestParam String schoolId, int pageIndex, int pageSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Starts");
		}
		ResponseVO responseVO = null;
		try {

			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = paymentManager.getAllPaymentBySchoolId(schoolId, pageIndex, pageSize);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Ends");
		}
		return responseVO;

	}

	@RequestMapping(value = "/deletePayment", method = RequestMethod.DELETE)
	public ResponseVO deletePayment(@RequestParam String paymentId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deletePayment Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = paymentManager.deletePayment(paymentId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deletePayment Ends");
		}
		return responseVO;
	}

	
	@RequestMapping(value = "/getAllPaymentStatusBySchoolId", method = RequestMethod.GET)
	public ResponseVO getAllPaymentStatusBySchoolId(@RequestParam  String schoolId,String batchId,String classId,String sectionId,String studentId) {
	
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId Starts");
		}
		ResponseVO responseVO = null;
		try {

			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = paymentManager.getAllPaymentStatusBySchoolId(schoolId,batchId, classId, sectionId, studentId);
					//getAllPaymentBySchoolId(schoolId, pageIndex, pageSize);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId Ends");
		}
		return responseVO;
	}

	
}
