package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface PaymentModeDAO {

	PaymentModeVO savePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException;

	PaymentModeVO getPaymentModeById(String paymentModeId) throws SMSBusinessException;

	List<PaymentModeVO> getAllPaymentModeBySchoolId(String schoolId) throws SMSBusinessException;

	long getPaymentModeCountBySchoolId(String schoolId) throws SMSBusinessException;

	PaymentModeVO updatePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException;

	boolean deletePaymentMode(String paymentModeId) throws SMSBusinessException;

	PaymentModeVO getPaymentModeByName(String studentId, String batchId, String id, String schoolId)
			throws SMSBusinessException;

	PaymentModeVO getPaymentModeByStudentId(String schoolId, String studentId,
			String term,String batchId) throws SMSBusinessException;
	
	PaymentModeVO getPaymentModesByStudenId(String schoolId, String studentId,
			String batchId) throws SMSBusinessException;

	List<PaymentModeVO> getAllPaymentModeStatusBySchoolId(String schoolId, String studentId,
			String term , String batchId) throws SMSBusinessException;

	List<PaymentModeVO> getAllPaymentModeStatusBySchoolId(String schoolId, String studentId, String batchId)
			throws SMSBusinessException;

	List<PaymentModeVO> getAllPaymentModeByStudentIdClassIdBatchId(String schoolId,String studentId, String classId, String batchId,
			int pageIndex, int pageSize)throws SMSBusinessException;

	PaymentModeVO getPaymentModeByStudenIdByTerm(String schoolId, String studentId, String classId, String batchId,
			String term)throws SMSBusinessException;	

	
}
