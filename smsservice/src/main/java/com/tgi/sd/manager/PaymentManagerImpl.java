package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.FeeConfigurationDAO;
import com.tgi.sd.dao.PaymentDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class PaymentManagerImpl implements PaymentManager {

	private static Logger logger = Logger.getLogger(PaymentManagerImpl.class);

	private PaymentDAO paymentDAO;

	private StudentDAO studentDAO;
	
	private FeeConfigurationDAO feeConfigurationDAO; 
	
	

	public FeeConfigurationDAO getFeeConfigurationDAO() {
		return feeConfigurationDAO;
	}

	public void setFeeConfigurationDAO(FeeConfigurationDAO feeConfigurationDAO) {
		this.feeConfigurationDAO = feeConfigurationDAO;
	}

	public PaymentDAO getPaymentDAO() {
		return paymentDAO;
	}

	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Override
	public PaymentVO savePayment(PaymentVO paymentVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("savePayment starts");
		}
		// paymentModeVO.setStatus(SMSConstants.FILEUPLOAD_CREATED);
		PaymentVO payment = paymentDAO.getPaymentByName(paymentVO.getStudentId(), paymentVO.getBatchId(),
				paymentVO.getSectionId(), paymentVO.getSchoolId());
		if (payment != null) {
			throw new SMSBusinessException(SMSConstants.PAY_NAME_ALREADY_EXISTS);
		}
		payment = paymentDAO.savePayment(paymentVO);

		if (logger.isDebugEnabled()) {
			logger.debug("savePayment ends");
		}
		return paymentVO;
	}

	
	@Override
	public PaymentVO updatePayment(PaymentVO paymentVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Starts");
		}

		PaymentVO payment = paymentDAO.getPaymentByName(paymentVO.getStudentId(), paymentVO.getBatchId(),
				paymentVO.getId(), paymentVO.getSchoolId());

		if (payment != null) {
			throw new SMSBusinessException(SMSConstants.PAY_NAME_ALREADY_EXISTS);
		}
		payment = paymentDAO.updatePayment(paymentVO);

		if (logger.isDebugEnabled()) {
			logger.debug("updatePayment Ends");
		}
		return payment;
	}
	
	
	@Override
	public PaymentVO getPaymentById(String paymentId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById Starts");
		}

		PaymentVO paymentVO = paymentDAO.getPaymentById(paymentId);

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentById Ends");
		}

		return paymentVO;
	}

	@Override
	public Map<String, Object> getAllPaymentBySchoolId(String schoolId, int pageIndex, int pageSize)
			throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Starts");
		}

		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		long totalRecords = paymentDAO.getPaymentCountBySchoolId(schoolId);
		List<PaymentVO> paymentVOs = paymentDAO.getAllPaymentBySchoolId(schoolId, pageIndex, pageSize);
		for (PaymentVO paymentVO : paymentVOs) {
			StudentVO student = studentDAO.getStudent(paymentVO.getStudentId());
			paymentVO.setStudentVO(student);
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
		responseObjectsMap.put("paymentModeVOs", paymentVOs);

		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentBySchoolId Ends");
		}
		return responseObjectsMap;
	}

	@Override
	public long getPaymentCountBySchoolId(String schoolId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentCountBySchoolId Starts");
		}

		long cnt = paymentDAO.getPaymentCountBySchoolId(schoolId);

		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentCountBySchoolId Ends");
		}
		return cnt;
	}

	

	@Override
	public boolean deletePayment(String paymentId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Starts");
		}

		boolean isDeleted = paymentDAO.deletePayment(paymentId);

		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Ends");
		}
		return isDeleted;
	}
	

	@Override
	public Map<String, Object> getAllPaymentStatusBySchoolId(String schoolId,String batchId, String classId, String sectionId, String studentId )
			throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId Starts");
		}

		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		//StudentVO studentVO = studentDAO.getStudentByStudentId(studentId, studentVO.getSchoolId());
		FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationById(batchId);
		//if(studentVO==null){
		//	throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		//}
		List<PaymentVO> paymentVOs = paymentDAO.getAllPaymentStatusWithSchoolId(schoolId, batchId, classId, sectionId, studentId);
				
				//getPaymentByStudentId(studentVO.getSchoolId(),studentId, batchId);
		
		for (PaymentVO paymentVO : paymentVOs) {
			
		}
		responseObjectsMap.put("paymentModeVOs", paymentVOs);

		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentStatusBySchoolId Ends");
		}
		return responseObjectsMap;
	}

	
}
