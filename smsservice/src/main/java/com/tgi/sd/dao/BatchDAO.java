package com.tgi.sd.dao;

import java.util.List;

import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface BatchDAO {

	BatchVO saveBatch(BatchVO batchVO) throws SMSBusinessException;

	BatchVO getBatchById(String batchId) throws SMSBusinessException;

	List<BatchVO> getAllBatchBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getBatchCountBySchoolId(String schoolId) throws SMSBusinessException;

	BatchVO updateBatch(BatchVO batchVO) throws SMSBusinessException;

	boolean deleteBatch(String batchId) throws SMSBusinessException;

	BatchVO getBatchByName(String batchName, String id, String schoolId, String batchCode)
			throws SMSBusinessException;
	
	BatchVO getBatchByBatchName(String batchCode, String schoolId)
			throws SMSBusinessException;

}
