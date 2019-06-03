package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.BatchDAO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class BatchManagerImpl implements BatchManager{
	
	private static Logger logger = Logger.getLogger(BatchManagerImpl.class);
	
	private BatchDAO batchDAO;
	
	public BatchDAO getBatchDAO() {
		return batchDAO;
	}
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}
	
	@Override
	public BatchVO saveBatch(BatchVO batchVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatch starts");
		}
		BatchVO batch = batchDAO.getBatchByName(batchVO.getBatchName(),null,batchVO.getSchoolId(),batchVO.getBatchCode());	
		if(batch!=null){
			throw new SMSBusinessException(SMSConstants.BATCHNAME_ALREADY_EXIST);
		}
		batchVO = batchDAO.saveBatch(batchVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBatch ends");
		}
		return batchVO;
		
	}
	@Override
	public BatchVO getBatchById(String batchId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchById Starts");
		}
		
		BatchVO batchVO = batchDAO.getBatchById(batchId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchById Ends");
		}
		
		return batchVO;
	}
	
	@Override
	public Map<String, Object> getAllBatchBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = batchDAO.getBatchCountBySchoolId(schoolId);
		List<BatchVO> batchVOs = batchDAO.getAllBatchBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("batchVOs", batchVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getBatchCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchCountBySchoolId Starts");
		}

		long cnt = batchDAO.getBatchCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBatchCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public BatchVO updateBatch(BatchVO batchVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatch Starts");
		}
		BatchVO batch = batchDAO.getBatchByName(batchVO.getBatchName(),batchVO.getId(),batchVO.getSchoolId(),batchVO.getBatchCode());	
		if(batch!=null){
			throw new SMSBusinessException(SMSConstants.BATCHNAME_ALREADY_EXIST);
		}
		batchVO = batchDAO.updateBatch(batchVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateBatch Ends");
		}
		return batchVO;
	}
	
	@Override
	public boolean deleteBatch(String batchId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatch Starts");
		}
		
		boolean isDeleted = batchDAO.deleteBatch(batchId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBatch Ends");
		}
		return isDeleted;
	}
	
}
