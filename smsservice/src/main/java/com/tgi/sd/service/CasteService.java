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
import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.manager.CasteManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/caste")
public class CasteService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CasteService.class);
	
	private CasteManager casteManager;
	
	
	public CasteManager getCasteManager() {
		return casteManager;
	}

	public void setCasteManager(CasteManager casteManager) {
		this.casteManager = casteManager;
	}

	@RequestMapping(value = "/saveCaste", method = RequestMethod.POST)
	public ResponseVO saveCaste(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveCaste Starts");
		}
		ResponseVO responseVO = null;
		CasteVO casteVO = null;
		try{
			casteVO = (CasteVO) parseObjectFromRequest(requestVO,CasteVO.class);
			if(null != casteVO) {
				casteVO = casteManager.saveCaste(casteVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CasteVO",casteVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveCaste Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getCasteById", method = RequestMethod.GET)
	public ResponseVO getCasteById(@RequestParam String casteId){
		if(logger.isDebugEnabled()) {
			logger.debug("getCasteById Starts");
		}
		ResponseVO responseVO = null;
		CasteVO casteVO = null;
		try{
			casteVO = casteManager.getCasteById(casteId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CasteVO", casteVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getCasteById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllCasteBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllCasteBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = casteManager.getAllCasteBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCasteBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateCaste", method = RequestMethod.POST)
	public ResponseVO updateCaste(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateCaste Starts");
		}
		ResponseVO responseVO = null;
		try {
			CasteVO casteVO = (CasteVO) parseObjectFromRequest(requestVO, CasteVO.class);
			if (null != casteVO) {
				casteVO = casteManager.updateCaste(casteVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("CasteVO", casteVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateCaste Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteCaste", method = RequestMethod.DELETE)
	public ResponseVO deleteCaste(@RequestParam String casteId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCaste Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = casteManager.deleteCaste(casteId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCaste Ends");
		}
		return responseVO;
	}
	
}
