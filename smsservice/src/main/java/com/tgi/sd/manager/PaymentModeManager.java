 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface PaymentModeManager {

	PaymentModeVO savePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException;

	PaymentModeVO getPaymentModeById(String paymentModeId) throws SMSBusinessException;

	PaymentModeVO updatePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException;

	boolean deletePaymentMode(String paymentModeId) throws SMSBusinessException;

	long getPaymentModeCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllPaymentModeBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllPaymentModeByStudentIdClassIdBatchId(String schoolId, String studentId, String classId, String batchId,
			int pageIndex, int pageSize)throws SMSBusinessException;

	Map<String, Object> getPaymentModeByStudenIdByTerm(String schoolId, String studentId, String classId,
			String batchId, String term)throws SMSBusinessException;
	
	
}
