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
import com.tgi.sd.domain.fms.ConcessionVO;
import com.tgi.sd.manager.ConcessionManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/concession")
public class ConcessionService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(ConcessionService.class);
	
	private ConcessionManager concessionManager;
	
	
	public ConcessionManager getConcessionManager() {
		return concessionManager;
	}

	public void setConcessionManager(ConcessionManager concessionManager) {
		this.concessionManager = concessionManager;
	}

	@RequestMapping(value = "/saveConcession", method = RequestMethod.POST)
	public ResponseVO saveConcession(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveConcession Starts");
		}
		ResponseVO responseVO = null;
		ConcessionVO concessionVO = null;
		try{
			concessionVO = (ConcessionVO) parseObjectFromRequest(requestVO,ConcessionVO.class);
			if(null != concessionVO) {
				concessionVO = concessionManager.saveConcession(concessionVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ConcessionVO",concessionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveConcession Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getConcessionById", method = RequestMethod.GET)
	public ResponseVO getConcessionById(@RequestParam String concessionId){
		if(logger.isDebugEnabled()) {
			logger.debug("getConcessionById Starts");
		}
		ResponseVO responseVO = null;
		ConcessionVO concessionVO = null;
		try{
			concessionVO = concessionManager.getConcessionById(concessionId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ConcessionVO", concessionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getConcessionById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllConcessionBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllConcessionBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = concessionManager.getAllConcessionBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllConcessionBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateConcession", method = RequestMethod.POST)
	public ResponseVO updateConcession(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateConcession Starts");
		}
		ResponseVO responseVO = null;
		try {
			ConcessionVO concessionVO = (ConcessionVO) parseObjectFromRequest(requestVO, ConcessionVO.class);
			if (null != concessionVO) {
				concessionVO = concessionManager.updateConcession(concessionVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("ConcessionVO", concessionVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateConcession Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteConcession", method = RequestMethod.DELETE)
	public ResponseVO deleteConcession(@RequestParam String concessionId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteConcession Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = concessionManager.deleteConcession(concessionId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteConcession Ends");
		}
		return responseVO;
	}
	
}
