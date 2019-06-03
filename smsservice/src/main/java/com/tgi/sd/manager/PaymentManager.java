package com.tgi.sd.manager;

import java.util.Map;
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface PaymentManager {

	PaymentVO savePayment(PaymentVO paymentVO) throws SMSBusinessException;

	PaymentVO updatePayment(PaymentVO paymentVO) throws SMSBusinessException;

	PaymentVO getPaymentById(String paymentId) throws SMSBusinessException;

	boolean deletePayment(String paymentId) throws SMSBusinessException;

	long getPaymentCountBySchoolId(String schoolId) throws SMSBusinessException;

	Map<String, Object> getAllPaymentBySchoolId(String schoolId, int pageIndex, int pageSize)
			throws SMSBusinessException;

	Map<String, Object> getAllPaymentStatusBySchoolId(String schoolId,String batchId, String classId, String sectionId, String studentId)
			throws SMSBusinessException;

}
