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
import com.tgi.sd.domain.config.BatchConfigurationVO;
import com.tgi.sd.manager.BatchConfigurationManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/batchConfiguration")
public class BatchConfigurationService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(BatchConfigurationService.class);
	
	private BatchConfigurationManager batchConfigurationManager;
	
	
	public BatchConfigurationManager getBatchConfigurationManager() {
		return batchConfigurationManager;
	}

	public void setBatchConfigurationManager(
			BatchConfigurationManager batchConfigurationManager) {
		this.batchConfigurationManager = batchConfigurationManager;
	}

	@RequestMapping(value = "/saveBatchConfiguration", method = RequestMethod.POST)
	public ResponseVO saveBatchConfiguration(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration Starts");
		}
		ResponseVO responseVO = null;
		BatchConfigurationVO batchConfigurationVO = null;
		try{
			batchConfigurationVO = (BatchConfigurationVO) parseObjectFromRequest(requestVO,BatchConfigurationVO.class);
			if(null != batchConfigurationVO) {
				batchConfigurationVO = batchConfigurationManager.saveBatchConfiguration(batchConfigurationVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BatchConfigurationVO",batchConfigurationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveBatchConfiguration Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getBatchConfigurationById", method = RequestMethod.GET)
	public ResponseVO getBatchConfigurationById(@RequestParam String batchConfigurationId){
		if(logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById Starts");
		}
		ResponseVO responseVO = null;
		BatchConfigurationVO batchConfigurationVO = null;
		try{
			batchConfigurationVO = batchConfigurationManager.getBatchConfigurationById(batchConfigurationId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("BatchConfigurationVO", batchConfigurationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getBatchConfigurationById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllBatchConfigurationBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllBatchConfigurationBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = batchConfigurationManager.getAllBatchConfigurationBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllBatchConfigurationBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateBatchConfiguration", method = RequestMethod.POST)
	public ResponseVO updateBatchConfiguration(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Starts");
		}
		ResponseVO responseVO = null;
		try {
			BatchConfigurationVO batchConfigurationVO = (BatchConfigurationVO) parseObjectFromRequest(requestVO, BatchConfigurationVO.class);
			if (null != batchConfigurationVO) {
				batchConfigurationVO = batchConfigurationManager.updateBatchConfiguration(batchConfigurationVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("BatchConfigurationVO", batchConfigurationVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateBatchConfiguration Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteBatchConfiguration", method = RequestMethod.DELETE)
	public ResponseVO deleteBatchConfiguration(@RequestParam String batchConfigurationId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = batchConfigurationManager.deleteBatchConfiguration(batchConfigurationId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteBatchConfiguration Ends");
		}
		return responseVO;
	}
	
}
