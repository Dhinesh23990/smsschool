package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.config.BatchVO;
import com.tgi.sd.manager.BatchManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/batch")
public class BatchService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(BatchService.class);
	
	private BatchManager batchManager;
	
	
	public BatchManager getBatchManager() {
		return batchManager;
	}

	public void setBatchManager(BatchManager batchManager) {
		this.batchManager = batchManager;
	}

	@RequestMapping(value = "/saveBatch", method = RequestMethod.POST)
	public ResponseVO saveBatch(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveBatch Starts");
		}
		ResponseVO responseVO = null;
		BatchVO batchVO = null;
		try{
			batchVO = (BatchVO) parseObjectFromRequest(requestVO,BatchVO.class);
			if(null != batchVO) {
				batchVO = batchManager.saveBatch(batchVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BatchVO",batchVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveBatch Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getBatchById", method = RequestMethod.GET)
	public ResponseVO getBatchById(@RequestParam String batchId){
		if(logger.isDebugEnabled()) {
			logger.debug("getBatchById Starts");
		}
		ResponseVO responseVO = null;
		BatchVO batchVO = null;
		try{
			batchVO = batchManager.getBatchById(batchId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BatchVO", batchVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getBatchById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllBatchBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllBatchBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = batchManager.getAllBatchBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllBatchBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateBatch", method = RequestMethod.POST)
	public ResponseVO updateBatch(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateBatch Starts");
		}
		ResponseVO responseVO = null;
		try {
			BatchVO batchVO = (BatchVO) parseObjectFromRequest(requestVO, BatchVO.class);
			if (null != batchVO) {
				batchVO = batchManager.updateBatch(batchVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("BatchVO", batchVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateBatch Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
	public ResponseVO deleteBatch(@RequestParam String batchId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBatch Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = batchManager.deleteBatch(batchId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBatch Ends");
		}
		return responseVO;
	}
	
}
