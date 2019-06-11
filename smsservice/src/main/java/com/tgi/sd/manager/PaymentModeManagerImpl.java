package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.tgi.sd.common.DateUtil;
import com.tgi.sd.common.Utility;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.FeeConfigurationDAO;
import com.tgi.sd.dao.PaymentModeDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.domain.fms.FeeConfigurationTypeVO;
import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class PaymentModeManagerImpl implements PaymentModeManager{
	
	private static Logger logger = Logger.getLogger(PaymentModeManagerImpl.class);
	
	private PaymentModeDAO paymentModeDAO;
	
	private StudentDAO studentDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	private FeeConfigurationDAO feeConfigurationDAO;
	
	
	public PaymentModeDAO getPaymentModeDAO() {
		return paymentModeDAO;
	}
	public void setPaymentModeDAO(PaymentModeDAO paymentModeDAO) {
		this.paymentModeDAO = paymentModeDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}			
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public FeeConfigurationDAO getFeeConfigurationDAO() {
		return feeConfigurationDAO;
	}
	public void setFeeConfigurationDAO(FeeConfigurationDAO feeConfigurationDAO) {
		this.feeConfigurationDAO = feeConfigurationDAO;
	}
	@Override
	public PaymentModeVO savePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("savePaymentMode starts");
		}
		//paymentModeVO.setStatus(SMSConstants.FILEUPLOAD_CREATED);
		PaymentModeVO paymentMode = paymentModeDAO.getPaymentModeByName(paymentModeVO.getStudentId(),paymentModeVO.getBatchId(), null,
				paymentModeVO.getSchoolId());
	/*	if(paymentMode != null) {
			throw new SMSBusinessException(SMSConstants.PAY_NAME_ALREADY_EXISTS);
		}*/
		paymentModeVO.setPaymentDate(com.tgi.sd.common.Utility.convertSystemTimeToGMTDate(DateUtil.getCurrentDateTime()));
		paymentModeVO = paymentModeDAO.savePaymentMode(paymentModeVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("savePaymentMode ends");
		}
		return paymentModeVO;
		
	}
	@Override
	public PaymentModeVO getPaymentModeById(String paymentModeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById Starts");
		}
		
		PaymentModeVO paymentModeVO = paymentModeDAO.getPaymentModeById(paymentModeId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeById Ends");
		}
		
		return paymentModeVO;
	}
	
	@Override
	public Map<String, Object> getAllPaymentModeBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = paymentModeDAO.getPaymentModeCountBySchoolId(schoolId);
		List<PaymentModeVO> paymentModeVOs = paymentModeDAO.getAllPaymentModeBySchoolId(schoolId, pageIndex, pageSize);
		List<FeeConfigurationVO> feeConfigurationVOs = feeConfigurationDAO.getAllFeeConfigurationBySchoolId(schoolId, pageIndex, pageSize);
		
		List<List<FeeConfigurationTypeVO>> feesType= feeConfigurationVOs.stream().map(h -> h.getFeeTypes()).collect(Collectors.toList());
		
		for(PaymentModeVO paymentModeVO : paymentModeVOs){
			StudentVO student =  studentDAO.getStudent(paymentModeVO.getStudentId());
			ClassVO classVO =  classDAO.getClassById(student.getClassId());
			SectionVO sectionVO = sectionDAO.getSectionById(student.getSection());
			student.setClassName(classVO.getClassName());
			student.setSectionName(sectionVO.getSectionName());
			paymentModeVO.setStudentVO(student);
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("paymentModeVOs", paymentModeVOs);
    	responseObjectsMap.put("feesType", feesType);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getPaymentModeCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeCountBySchoolId Starts");
		}

		long cnt = paymentModeDAO.getPaymentModeCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public PaymentModeVO updatePaymentMode(PaymentModeVO paymentModeVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Starts");
		}
		
		paymentModeVO.setPaymentDate(com.tgi.sd.common.Utility.convertSystemTimeToGMTDate(DateUtil.getCurrentDateTime()));
		PaymentModeVO paymentMode = paymentModeDAO.getPaymentModeByName(paymentModeVO.getStudentId(),paymentModeVO.getBatchId(), paymentModeVO.getId(),
				paymentModeVO.getSchoolId());		
		paymentModeVO = paymentModeDAO.updatePaymentMode(paymentModeVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updatePaymentMode Ends");
		}
		return paymentModeVO;
	}
	
	@Override
	public boolean deletePaymentMode(String paymentModeId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Starts");
		}
		
		boolean isDeleted = paymentModeDAO.deletePaymentMode(paymentModeId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deletePaymentMode Ends");
		}
		return isDeleted;
	}
	@Override
	public Map<String, Object> getAllPaymentModeByStudentIdClassIdBatchId(String schoolId,String studentId, String classId,
			String batchId, int pageIndex, int pageSize) throws SMSBusinessException {
	

		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = paymentModeDAO.getPaymentModeCountBySchoolId(studentId);
		List<PaymentModeVO> paymentModeVOs = paymentModeDAO.getAllPaymentModeByStudentIdClassIdBatchId(schoolId,studentId, classId,batchId,pageIndex, pageSize);
		for(PaymentModeVO paymentModeVO : paymentModeVOs){
			StudentVO student =  studentDAO.getStudent(paymentModeVO.getStudentId());
			ClassVO classVO =  classDAO.getClassById(paymentModeVO.getClassId());
			SectionVO sectionVO = sectionDAO.getSectionById(student.getSection());
			student.setClassName(classVO.getClassName());
			student.setSectionName(sectionVO.getSectionName());
			paymentModeVO.setStudentVO(student);
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("paymentModeVOs", paymentModeVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllPaymentModeBySchoolId Ends");
		}
		return responseObjectsMap;
	
	}
	@Override
	public Map<String, Object> getPaymentModeByStudenIdByTerm(String schoolId, String studentId, String classId,
			String batchId, String term) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenIdByTerm Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = paymentModeDAO.getPaymentModeCountBySchoolId(studentId);
		PaymentModeVO paymentModeVO = paymentModeDAO.getPaymentModeByStudenIdByTerm(schoolId,studentId, classId,batchId,term);
		

    	responseObjectsMap.put("PaymentModeVO", paymentModeVO);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getPaymentModeByStudenIdByTerm Ends");
		}
		return responseObjectsMap;
	
	}
	
}
