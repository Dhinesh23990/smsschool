 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface BatchConfigurationManager {

	BatchConfigurationVO saveBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException;

	BatchConfigurationVO getBatchConfigurationById(String batchConfigurationId) throws SMSBusinessException;

	BatchConfigurationVO updateBatchConfiguration(BatchConfigurationVO batchConfigurationVO) throws SMSBusinessException;

	boolean deleteBatchConfiguration(String batchConfigurationId) throws SMSBusinessException;

	long getBatchConfigurationCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllBatchConfigurationBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
