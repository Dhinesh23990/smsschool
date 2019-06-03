 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface BatchManager {

	BatchVO saveBatch(BatchVO batchVO) throws SMSBusinessException;

	BatchVO getBatchById(String batchId) throws SMSBusinessException;

	BatchVO updateBatch(BatchVO batchVO) throws SMSBusinessException;

	boolean deleteBatch(String batchId) throws SMSBusinessException;

	long getBatchCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllBatchBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
