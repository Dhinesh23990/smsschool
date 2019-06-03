package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface BatchConfigurationDAO {

	BatchConfigurationVO saveBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException;

	BatchConfigurationVO getBatchConfigurationById(String batchConfigurationsId) throws SMSBusinessException;

	List<BatchConfigurationVO> getAllBatchConfigurationBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

	long getBatchConfigurationCountBySchoolId(String schoolId) throws SMSBusinessException;

	BatchConfigurationVO updateBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException;

	boolean deleteBatchConfiguration(String batchConfigurationId) throws SMSBusinessException;

	BatchConfigurationVO getBatchConfigByName(String batchConfigId, BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException;

	List<BatchConfigurationVO> getAllClassTeacherByIds(String schoolId,List<String> batchIds,
			List<String> sectionIds, List<String> classIds) throws SMSBusinessException;

	BatchConfigurationVO getBatchConfigByStaffId(String staffId, String schoolId) throws SMSBusinessException;

	List<String> getAllTeacherIdsByClassAndSectionId(String classId,String sectionId, String schoolId,String batchId) throws SMSBusinessException;
}
