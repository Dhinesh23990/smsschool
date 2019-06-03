 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface FeeConfigurationManager {

	FeeConfigurationVO saveFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException;

	FeeConfigurationVO getFeeConfigurationById(String feeConfigurationId) throws SMSBusinessException;

	FeeConfigurationVO updateFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException;

	boolean deleteFeeConfiguration(String feeConfigurationId) throws SMSBusinessException;

	long getFeeConfigurationCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllFeeConfigurationBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	Map<String, Object> getAllFeeConfigurationByClassFeeCategory(
			String schoolId, String classId, String feeCategory) throws SMSBusinessException;
/*
	Map<String, Object> getFeeConfigurationByStudentId(String schoolId, String term, String batchId,
			String searchString) throws SMSBusinessException;*/

	Map<String, Object> getFeeConfigurationByStudentId(String schoolId, String className, String batchId,
			String searchString, String sectionName) throws SMSBusinessException;
	
	Map<String, Object> getAllFeeconfigurationDetailsByStudentId(String schoolId,String batchId,String classId,String sectionId,  String searchString) throws SMSBusinessException;
	
	Map<String,Object>getAllclassesByYear(String year) throws SMSBusinessException;
	
	Map<String,Object>gettAllSectionsByYearClasses(String year,String classes) throws SMSBusinessException;
	
	Map<String,Object> getStudentDetailsById(String schoolId,String studentid,String term) throws SMSBusinessException;
	
	Map<String,Object> getFeeConfigurationByTermId(String term) throws SMSBusinessException;

	Map<String, Object> getFeeConfigurationByStudentIdAndTerm(String schoolId, String batchId,String studentId,String term,String studentName) throws SMSBusinessException;
	
	
	
	
}
