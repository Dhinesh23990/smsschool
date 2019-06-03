package com.tgi.sd.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.dao.ClassDAO;
import com.tgi.sd.dao.FeeComponentDAO;
import com.tgi.sd.dao.FeeConfigurationDAO;
import com.tgi.sd.dao.FeeTypeDAO;
import com.tgi.sd.dao.PaymentDAO;
import com.tgi.sd.dao.PaymentModeDAO;
import com.tgi.sd.dao.SectionDAO;
import com.tgi.sd.dao.StudentDAO;
import com.tgi.sd.domain.StudentVO;
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.LanguageVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.domain.config.StateVO;
import com.tgi.sd.domain.fms.FeeComponentVO;
import com.tgi.sd.domain.fms.FeeConfigurationTypeVO;
import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.domain.fms.FeeTypeVO;
import com.tgi.sd.domain.fms.PaymentModeTypeVO;
import com.tgi.sd.domain.fms.PaymentModeVO;
import com.tgi.sd.domain.fms.PaymentVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class FeeConfigurationManagerImpl implements FeeConfigurationManager{
	
	private static Logger logger = Logger.getLogger(FeeConfigurationManagerImpl.class);
	
	private FeeConfigurationDAO feeConfigurationDAO;
	
	private FeeComponentDAO feeComponentDAO;
	
	private FeeTypeDAO feeTypeDAO;
	
	private ClassDAO classDAO;
	
	private SectionDAO sectionDAO;
	
	private BatchDAO batchDAO;
	
	private StudentDAO studentDAO;
	
	private PaymentModeDAO paymentModeDAO;
	
	private PaymentDAO paymentDAO;
	
	
	public PaymentDAO getPaymentDAO() {
		return paymentDAO;
	}
	public void setPaymentDAO(PaymentDAO paymentDAO) {
		this.paymentDAO = paymentDAO;
	}
	public PaymentModeDAO getPaymentModeDAO() {
		return paymentModeDAO;
	}
	public void setPaymentModeDAO(PaymentModeDAO paymentModeDAO) {
		this.paymentModeDAO = paymentModeDAO;
	}
	
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	public FeeTypeDAO getFeeTypeDAO() {
		return feeTypeDAO;
	}
	public void setFeeTypeDAO(FeeTypeDAO feeTypeDAO) {
		this.feeTypeDAO = feeTypeDAO;
	}
	
	public FeeComponentDAO getFeeComponentDAO() {
		return feeComponentDAO;
	}
	public void setFeeComponentDAO(FeeComponentDAO feeComponentDAO) {
		this.feeComponentDAO = feeComponentDAO;
	}
	
	public FeeConfigurationDAO getFeeConfigurationDAO() {
		return feeConfigurationDAO;
	}
	public void setFeeConfigurationDAO(FeeConfigurationDAO feeConfigurationDAO) {
		this.feeConfigurationDAO = feeConfigurationDAO;
	}
	
	public ClassDAO getClassDAO() {
		return classDAO;
	}
	public void setClassDAO(ClassDAO classDAO) {
		this.classDAO = classDAO;
	}
	
	public SectionDAO getSectionDAO() {
		return sectionDAO;
	}
	public void setSectionDAO(SectionDAO sectionDAO) {
		this.sectionDAO = sectionDAO;
	}
	
	public StudentDAO getStudentDAO() {
		return studentDAO;
	}
	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	
	@Override
	public FeeConfigurationVO saveFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration starts");
		}
		
		FeeConfigurationVO findConfigurationVO = feeConfigurationDAO.getFeeConfigurationByBatchId(feeConfigurationVO.getBatchId(),
				feeConfigurationVO.getClassId(),feeConfigurationVO.getTerm(),feeConfigurationVO.getSchoolId(),feeConfigurationVO.getId());
		if(findConfigurationVO!=null){
			throw new SMSBusinessException(SMSConstants.FEECONFIG_ALREADY_EXIST);
		}
		feeConfigurationVO = feeConfigurationDAO.saveFeeConfiguration(feeConfigurationVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration ends");
		}
		return feeConfigurationVO;
		
	}
	@Override
	public FeeConfigurationVO getFeeConfigurationById(String feeConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById Starts");
		}
		
		FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationById(feeConfigurationId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById Ends");
		}
		
		return feeConfigurationVO;
	}
	
	@Override
	public Map<String, Object> getAllFeeConfigurationBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	
    	List<FeeTypeVO> feeTypeLst = feeTypeDAO.getAllFeeTypeBySchoolId(schoolId, 0, 0);
    	List<BatchVO> batchList = batchDAO.getAllBatchBySchoolId(schoolId, null, null);
		Map<String,String> batchMap = batchList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBatchName()));
    	List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, null, null);
		Map<String,String> classMap = classList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getClassName()));
		List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
		Map<String,String> sectionMap = sectionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getSectionName()));		
    	
    	long totalRecords = feeConfigurationDAO.getFeeConfigurationCountBySchoolId(schoolId);
		List<FeeConfigurationVO> feeConfigurationVOs = feeConfigurationDAO.getAllFeeConfigurationBySchoolId(schoolId, pageIndex, pageSize);
		for(FeeConfigurationVO feeConf:feeConfigurationVOs){
			//Set child items
			if (feeConf.getFeeTypes() == null || feeConf.getFeeTypes().size() <=0) {
				feeConf.setFeeTypes(getFeeConfigType(feeTypeLst,feeConf));
			}
			//Set names
			if(StringUtils.isNotBlank(feeConf.getBatchId())){
				feeConf.setBatchName(batchMap.get(feeConf.getBatchId()));
			}			
			if(StringUtils.isNotBlank(feeConf.getClassId())){
				feeConf.setClassName(classMap.get(feeConf.getClassId()));
			}
			if(StringUtils.isNotBlank(feeConf.getSectionId())){
				feeConf.setSectionName(sectionMap.get(feeConf.getSectionId()));
			}
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("feeConfigurationVOs", feeConfigurationVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	private List<FeeConfigurationTypeVO> getFeeConfigType(List<FeeTypeVO> feeLst,FeeConfigurationVO conf) {
		List<FeeConfigurationTypeVO> feeConfTypeLst = new ArrayList<FeeConfigurationTypeVO>();
		for (FeeTypeVO ftype: feeLst) {
			FeeConfigurationTypeVO f = new FeeConfigurationTypeVO();
			f.setFeeType(ftype.getFeeType());
			f.setAmount(ftype.getAmount());
			f.setChecked(ftype.getChecked());
			f.setFeeConfigurationId(conf.getId());
			f.setFeeTypeId(ftype.getId());
			f.setRecurringPerYear(ftype.getRecurringPerYear());
			f.setRemDuration(ftype.getRemDuration());
			f.setSchoolId(ftype.getSchoolId());
			feeConfTypeLst.add(f);
		}
		
		return feeConfTypeLst;
	}
	
	@Override
	public Map<String, Object> getAllFeeConfigurationByClassFeeCategory(String schoolId, String classId, String feeCategory) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	
    	List<FeeTypeVO> feeTypeLst = feeTypeDAO.getAllFeeTypeBySchoolId(schoolId, 0, 0);
    	List<BatchVO> batchList = batchDAO.getAllBatchBySchoolId(schoolId, null, null);
		Map<String,String> batchMap = batchList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBatchName()));
    	List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, null, null);
		Map<String,String> classMap = classList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getClassName()));
		List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
		Map<String,String> sectionMap = sectionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getSectionName()));	
		
    	long totalRecords = feeConfigurationDAO.getFeeConfigurationCountBySchoolId(schoolId);
		List<FeeConfigurationVO> feeConfigurationVOs = feeConfigurationDAO.getAllFeeConfigurationByClassFeeCategory(schoolId, classId, feeCategory);
		for(FeeConfigurationVO feeConf:feeConfigurationVOs){
			
			//Set child items
			if (feeConf.getFeeTypes() == null || feeConf.getFeeTypes().size() <=0) {
				feeConf.setFeeTypes(getFeeConfigType(feeTypeLst,feeConf));
			}
			//Set names
			if(StringUtils.isNotBlank(feeConf.getBatchId())){
				feeConf.setBatchName(batchMap.get(feeConf.getBatchId()));
			}			
			if(StringUtils.isNotBlank(feeConf.getClassId())){
				feeConf.setClassName(classMap.get(feeConf.getClassId()));
			}
			if(StringUtils.isNotBlank(feeConf.getSectionId())){
				feeConf.setSectionName(sectionMap.get(feeConf.getSectionId()));
			}
		}
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("feeConfigurationVOs", feeConfigurationVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Ends");
		}
		return responseObjectsMap;
	}
	

	@Override
	public long getFeeConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationCountBySchoolId Starts");
		}

		long cnt = feeConfigurationDAO.getFeeConfigurationCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public FeeConfigurationVO updateFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Starts");
		}
		
		
		feeConfigurationVO = feeConfigurationDAO.updateFeeConfiguration(feeConfigurationVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Ends");
		}
		return feeConfigurationVO;
	}
	
	@Override
	public boolean deleteFeeConfiguration(String feeConfigurationId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Starts");
		}
		
		boolean isDeleted = feeConfigurationDAO.deleteFeeConfiguration(feeConfigurationId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Ends");
		}
		return isDeleted;
	}
	
	
	
	@Override
	public Map<String,Object> getAllclassesByYear(String year) throws SMSBusinessException{
	
		if (logger.isDebugEnabled()) {
			logger.debug("getAllclassesByYear Starts");
		}
		Map<String,Object> responseObjectsMap =new HashMap<String,Object>();
		List<FeeConfigurationVO> batchclass=feeConfigurationDAO.getAllclassesByYear(year);
		if(batchclass.isEmpty()){
			throw new SMSBusinessException(SMSConstants.CLASS_LIST_EMPTY);
		}
		responseObjectsMap.put("batchclass", batchclass);
		if(logger.isDebugEnabled()){
			logger.debug("getAllclassesByYear Ends");
		}
		return responseObjectsMap;
	}
	
	
	@Override
	public Map<String,Object>gettAllSectionsByYearClasses(String year,String classes) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("gettAllSectionsByYearClasses Starts");
		}
		Map<String,Object> responseObjectsMap = new HashMap<String,Object>();
		List<FeeConfigurationVO> sections=feeConfigurationDAO.gettAllSectionsByYearClasses(year,classes);
		if(sections.isEmpty()){
			throw new SMSBusinessException(SMSConstants.SECTION_LIST_EMPTY);
		}
		responseObjectsMap.put("sections",sections);
		if (logger.isDebugEnabled()) {
			logger.debug("gettAllSectionsByYearClasses Ends");
		}
		return responseObjectsMap;
	}
	
	
	
	@Override
	public Map<String, Object> getStudentDetailsById(String schoolId,
			String studentid, String term) throws SMSBusinessException {
		if(logger.isDebugEnabled()){
			logger.debug("getAllFeeconfigurationDetailsByStudentId is Starts");
		}
		Map<String,Object> responseObjectsMap = new HashMap<String, Object>();
		StudentVO studentVO = studentDAO.getStudentById(schoolId,studentid);
		if(studentVO==null){
			throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		
		FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationByBatchId(studentVO.getBatchId(),studentVO.getClassId(),term,
				schoolId,studentVO.getId());
		responseObjectsMap.put("StudentVO", studentVO);
		responseObjectsMap.put("feeConfigurationVO", feeConfigurationVO);
		return responseObjectsMap;
	}
	
	
	@Override
	public Map<String, Object> getFeeConfigurationByTermId(String term)
			throws SMSBusinessException {
		Map<String,Object> responseObjectsMap = new HashMap<String, Object>();
		FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationByTermId(term);
		responseObjectsMap.put("feeConfigurationVO", feeConfigurationVO);
//		responseObjectsMap.put("amount", feeConfigurationVO.getAmount());
		return responseObjectsMap;		
		
	}
	
	
	
	
	@Override
	public Map<String, Object> getFeeConfigurationByStudentId(String schoolId,String className,String batchId,String searchString,String sectionName) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start getStudentByIdOrNo");
		}
		
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		StudentVO studentVO =  studentDAO.getStudentByIdOrNo(schoolId,searchString);
		
		if(studentVO==null){
			throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		}
		
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())){
			SectionVO sectionVO = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionVO.getSectionName());
		}
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		
		
		/*FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationsByBatchId(batchId,studentVO.getClassId(),
				schoolId,studentVO.getId());
		
		if(feeConfigurationVO==null){
			throw new SMSBusinessException(SMSConstants.FEECONFIG_EMPTY);
		}*/
		
		PaymentModeVO paymentMode = paymentModeDAO.getPaymentModesByStudenId(schoolId, studentVO.getId(), batchId);
		
		//PaymentModeVO paymentMode = paymentModeDAO.getPaymentModeByStudentId(schoolId, studentVO.getId(), term, batchId);
		
		if(paymentMode != null){
			throw new SMSBusinessException(SMSConstants.PAYMENT_PAID);
		}
		
//		responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVO);
		responseObjectsMap.put("StudentVO", studentVO);
		responseObjectsMap.put("PaymentModeVO", paymentMode);
		if (logger.isDebugEnabled()) {
			logger.debug("End getStudentByIdOrNo");
		}
		return responseObjectsMap;	
	}
	
	
	
	@Override
	public Map<String, Object> getAllFeeconfigurationDetailsByStudentId(String schoolId,String batchId,String classId,String sectionId,  String searchString) throws SMSBusinessException{
		
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllFeeconfigurationDetailsByStudentId is Starts");
		}
		
		Map<String,Object> responseObjectsMap = new HashMap<String, Object>();
		
		
		StudentVO studentVO = studentDAO.getStudentBySearchString(schoolId, classId, batchId, sectionId,searchString);
		if(studentVO==null){
			throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		}
		List<FeeConfigurationVO> feeConfigurationVOs = feeConfigurationDAO.getAllFeeconfigurationByStudentId(schoolId, classId,batchId);
		if(feeConfigurationVOs == null || feeConfigurationVOs.size() <= 0){
			throw new SMSBusinessException(SMSConstants.FEE_CONFIG_EMPTY);
		}
		
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}

		List<FeeTypeVO> feeTypeLst = feeTypeDAO.getAllFeeTypeBySchoolId(schoolId, 0, 0);
    	List<BatchVO> batchList = batchDAO.getAllBatchBySchoolId(schoolId, null, null);
		Map<String,String> batchMap = batchList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBatchName()));
    	List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, null, null);
		Map<String,String> classMap = classList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getClassName()));
		List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
		Map<String,String> sectionMap = sectionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getSectionName()));	
		
		
		Map<String,Double> studentPaidAmountMap = new HashMap<String,Double>();
		List<PaymentModeVO> paymentModes = paymentModeDAO.getAllPaymentModeStatusBySchoolId(schoolId, studentVO.getId(), studentVO.getBatchId());
		if(paymentModes != null){
			for(PaymentModeVO paymentvo :paymentModes) {
				studentPaidAmountMap.put(paymentvo.getTerm(), Double.parseDouble(StringUtils.defaultString(paymentvo.getPending(),"0")));
			}
			//studentPaidAmountMap = paymentModes.stream().collect(Collectors.toMap(pay -> pay.getTerm(), pay -> pay.getPending()));
		}
		
		if(StringUtils.isNotBlank(studentVO.getSection())) {
		SectionVO sectionvo = sectionDAO.getSectionById(studentVO.getSection());
		studentVO.setSectionName(sectionvo.getSectionName());
		}
			
		for(FeeConfigurationVO feeConf:feeConfigurationVOs){
			//Set child items
			if (feeConf.getFeeTypes() == null || feeConf.getFeeTypes().size() <=0) {
				feeConf.setFeeTypes(getFeeConfigType(feeTypeLst,feeConf));
			}
			//Set names
			if(StringUtils.isNotBlank(feeConf.getBatchId())){
				feeConf.setBatchName(batchMap.get(feeConf.getBatchId()));
			}			
			if(StringUtils.isNotBlank(feeConf.getClassId())){
				feeConf.setClassName(classMap.get(feeConf.getClassId()));
			}
			if(StringUtils.isNotBlank(feeConf.getSectionId())){
				feeConf.setSectionName(sectionMap.get(feeConf.getSectionId()));
			}
			if(StringUtils.isNotBlank(feeConf.getTerm())) {
				Double pendingAmount = studentPaidAmountMap.get(feeConf.getTerm());
				feeConf.setPendingAmount((pendingAmount != null &&  pendingAmount > 0) ? pendingAmount : feeConf.getAmount());
			}
		}		
				
		responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVOs);
		//responseObjectsMap.put("PaymentModeVO", paymentMode);
		responseObjectsMap.put("StudentVO", studentVO);
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllFeeconfigurationDetailsByStudentId is Ends");
		}
		return responseObjectsMap;
	}
	
	/*
	@Override
	public Map<String, Object> getAllFeeconfigurationDetailsByStudentId(String schoolId,String batchId,String classId,String sectionId,  String searchString) throws SMSBusinessException{
		
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllFeeconfigurationDetailsByStudentId is Starts");
		}
		
		Map<String,Object> responseObjectsMap = new HashMap<String, Object>();
		
		StudentVO studentVO = studentDAO.getStudentBySearchString(schoolId, classId, batchId, searchString);
		
		if(studentVO==null){
			throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		
		List<FeeConfigurationVO> feeConfigurationVOs = null;
		PaymentModeVO paymentMode = paymentModeDAO.getPaymentModesByStudenId(schoolId, studentVO.getStudentId(), studentVO.getBatchId());
		if(paymentMode == null){
			List<FeeTypeVO> feeTypeLst = feeTypeDAO.getAllFeeTypeBySchoolId(schoolId, 0, 0);
	    	List<BatchVO> batchList = batchDAO.getAllBatchBySchoolId(schoolId, null, null);
			Map<String,String> batchMap = batchList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getBatchName()));
	    	List<ClassVO> classList = classDAO.getAllClassBySchoolId(schoolId, null, null);
			Map<String,String> classMap = classList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getClassName()));
			List<SectionVO> sectionList = sectionDAO.getAllSectionBySchoolId(schoolId, null, null);
			Map<String,String> sectionMap = sectionList.stream().collect(Collectors.toMap(x -> x.getId(),x -> x.getSectionName()));	
			
			feeConfigurationVOs = feeConfigurationDAO.getAllFeeconfigurationByStudentId(schoolId, classId,batchId);
			
			if(feeConfigurationVOs == null || feeConfigurationVOs.size() <= 0){
				throw new SMSBusinessException(SMSConstants.FEE_CONFIG_EMPTY);
			}
				
			for(FeeConfigurationVO feeConf:feeConfigurationVOs){
				//Set child items
				if (feeConf.getFeeTypes() == null || feeConf.getFeeTypes().size() <=0) {
					feeConf.setFeeTypes(getFeeConfigType(feeTypeLst,feeConf));
				}
				//Set names
				if(StringUtils.isNotBlank(feeConf.getBatchId())){
					feeConf.setBatchName(batchMap.get(feeConf.getBatchId()));
				}			
				if(StringUtils.isNotBlank(feeConf.getClassId())){
					feeConf.setClassName(classMap.get(feeConf.getClassId()));
				}
				if(StringUtils.isNotBlank(feeConf.getSectionId())){
					feeConf.setSectionName(sectionMap.get(feeConf.getSectionId()));
				}
			}
			
			paymentMode = getPaymentFromConfig(paymentMode,feeConfigurationVOs,studentVO.getId());
		}
		
				
		responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVOs);
		responseObjectsMap.put("PaymentModeVO", paymentMode);
		responseObjectsMap.put("StudentVO", studentVO.getId());
		
		if(logger.isDebugEnabled()){
			logger.debug("getAllFeeconfigurationDetailsByStudentId is Ends");
		}
		return responseObjectsMap;
	}	
	*/
	private PaymentModeVO getPaymentFromConfig(PaymentModeVO paymentMode,List<FeeConfigurationVO> feeConfigurationVOs,String studentId) {
		if (paymentMode == null)
			paymentMode = new PaymentModeVO();
		
		for (FeeConfigurationVO feeConf: feeConfigurationVOs) {
			paymentMode.setId(UUID.randomUUID().toString());
			paymentMode.setSchoolId(feeConf.getSchoolId());
			paymentMode.setBatchId(feeConf.getBatchId());
			paymentMode.setClassId(feeConf.getClassId());
			paymentMode.setStudentId(studentId);
			paymentMode.setAmount(Double.toString(feeConf.getAmount()));
			paymentMode.setFeeConfigurationId(feeConf.getId());
			paymentMode.setTerm(feeConf.getTerm());
			
			//set feetypes
			List<PaymentModeTypeVO> fLst = new ArrayList<PaymentModeTypeVO>();
			for (FeeConfigurationTypeVO ftype : feeConf.getFeeTypes()) {
				PaymentModeTypeVO f = new PaymentModeTypeVO();
				f.setFeeType(ftype.getFeeType());
				f.setAmount(ftype.getAmount());
				f.setChecked(ftype.getChecked());
				f.setPaymentModeId(paymentMode.getId());
				f.setFeeTypeId(ftype.getId());
				f.setRecurringPerYear(ftype.getRecurringPerYear());
				f.setRemDuration(ftype.getRemDuration());
				f.setSchoolId(ftype.getSchoolId());
				fLst.add(f);
			}
			paymentMode.setFeeTypes(fLst);
		}
		
		return paymentMode;
	}
	@Override
	public Map<String, Object> getFeeConfigurationByStudentIdAndTerm(String schoolId, String batchId,String studentId,String term,String studentName)
			throws SMSBusinessException {
		

		Map<String,Object> responseObjectsMap = new HashMap<String, Object>();
		
		FeeConfigurationVO feeConfigurationVO = feeConfigurationDAO.getFeeConfigurationByStudentIdAndTerm(schoolId,batchId,studentId,term);
		if(feeConfigurationVO == null){
			throw new SMSBusinessException(SMSConstants.FEE_CONFIG_EMPTY);
		}

		
		
		StudentVO studentVO = studentDAO.getStudentBySearchString(schoolId, null, batchId, null,studentName);
		if(studentVO==null){
			throw new SMSBusinessException(SMSConstants.STUDENT_IDS_EMPTY);
		}
		if(StringUtils.isNotBlank(studentVO.getClassId())){
			ClassVO classVO = classDAO.getClassById(studentVO.getClassId());
			studentVO.setClassName(classVO.getClassName());
		}
		
		
		if(StringUtils.isNotBlank(studentVO.getBatchId())){
			BatchVO batchVO = batchDAO.getBatchById(studentVO.getBatchId());
			studentVO.setBatchName(batchVO.getBatchName());
		}
		if(StringUtils.isNotBlank(studentVO.getSection())) {
			SectionVO sectionvo = sectionDAO.getSectionById(studentVO.getSection());
			studentVO.setSectionName(sectionvo.getSectionName());
		}
				
		
		PaymentModeVO paymentModeVO = paymentModeDAO.getPaymentModeByStudenIdByTerm(feeConfigurationVO.getSchoolId(),studentVO.getId(),feeConfigurationVO.getClassId(),feeConfigurationVO.getBatchId(),feeConfigurationVO.getTerm());
		
		
		if (paymentModeVO != null) {
			feeConfigurationVO.setPendingAmount(Double.valueOf(paymentModeVO.getPending()));
			feeConfigurationVO.setPaidAmount(Double.valueOf(paymentModeVO.getAmount()));
			feeConfigurationVO.setStatus(paymentModeVO.getStatus());
			feeConfigurationVO.getFeeTypes().forEach(confFeeType -> paymentModeVO.getFeeTypes().stream().forEach(x -> {
				if (x.getFeeType().equals(confFeeType.getFeeType())) {
					Double balanceAmount = confFeeType.getAmount() - x.getAmount();
					x.setBalanceAmount(balanceAmount);
				}
			}));
		}else {
			feeConfigurationVO.setPendingAmount(feeConfigurationVO.getAmount());
			feeConfigurationVO.setStatus(SMSConstants.PAYMENT_STATUS);
		}
		
		responseObjectsMap.put("feeConfigurationVO", feeConfigurationVO);
		responseObjectsMap.put("StudentVO", studentVO);
		responseObjectsMap.put("PaymentModeVO", paymentModeVO);

		return responseObjectsMap;		
		
	}
	
}