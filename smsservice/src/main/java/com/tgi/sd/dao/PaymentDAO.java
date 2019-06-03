package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface PaymentDAO {

	PaymentVO savePayment(PaymentVO paymentVO) throws SMSBusinessException;

	PaymentVO updatePayment(PaymentVO paymentVO) throws SMSBusinessException;
	
	PaymentVO getPaymentById(String paymentId) throws SMSBusinessException;

	List<PaymentVO> getAllPaymentBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	boolean deletePayment(String paymentId) throws SMSBusinessException;
	
	long getPaymentCountBySchoolId(String schoolId) throws SMSBusinessException;

	PaymentVO getPaymentByName(String studentId, String batchId, String id, String schoolId)
			throws SMSBusinessException;

	PaymentVO getPaymentByStudentId(String schoolId, String studentId,
			String term,String batchId) throws SMSBusinessException;
	
	List<PaymentVO> getAllPaymentStatusBySchoolId(String schoolId, String studentId,
			String term , String batchId) throws SMSBusinessException;

	
	PaymentVO getPaymentByStudenId(String schoolId, String studentId,
			String batchId) throws SMSBusinessException;

	
	List<PaymentVO> getAllPaymentStatusWithSchoolId(String schoolId,String batchId,String classId,String sectionId,String studentId)
			throws SMSBusinessException;	

	
}
