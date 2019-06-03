package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface FeeConfigurationDAO {

	FeeConfigurationVO saveFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException;

	FeeConfigurationVO getFeeConfigurationById(String feeConfigurationId) throws SMSBusinessException;

	List<FeeConfigurationVO> getAllFeeConfigurationBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getFeeConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException;

	FeeConfigurationVO updateFeeConfiguration(FeeConfigurationVO feeConfigurationVO) throws SMSBusinessException;

	boolean deleteFeeConfiguration(String feeConfigurationId) throws SMSBusinessException;

	List<FeeConfigurationVO> getAllFeeConfigurationByClassFeeCategory(
			String schoolId, String classId, String feeCategory) throws SMSBusinessException;

	FeeConfigurationVO getFeeConfigurationByBatchId(String batchId,
			String classId, String term, String schoolId, String id) throws SMSBusinessException;

	List<FeeConfigurationVO> getFeeConfigurationsByBatchId(String batchId,
			String classId, String schoolId, String id) throws SMSBusinessException;
	
	List<FeeConfigurationVO> getAllclassesByYear(String year) throws SMSBusinessException;
	
	List<FeeConfigurationVO> gettAllSectionsByYearClasses(String year,String classes) throws SMSBusinessException;
	
	FeeConfigurationVO getFeeConfigurationByTermId(String term ) throws SMSBusinessException;

	List<FeeConfigurationVO> getAllFeeconfigurationByStudentId(String schoolId,String classId, String batchId) throws SMSBusinessException;

	FeeConfigurationVO getFeeConfigurationByStudentIdAndTerm(String schoolId, String batchId,String studentId, String term) throws SMSBusinessException;

}
