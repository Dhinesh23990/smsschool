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
import com.tgi.sd.domain.config.ReligionVO;
import com.tgi.sd.manager.ReligionManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/religion")
public class ReligionService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(ReligionService.class);
	
	private ReligionManager religionManager;
	
	
	public ReligionManager getReligionManager() {
		return religionManager;
	}

	public void setReligionManager(ReligionManager religionManager) {
		this.religionManager = religionManager;
	}

	@RequestMapping(value = "/saveReligion", method = RequestMethod.POST)
	public ResponseVO saveReligion(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveReligion Starts");
		}
		ResponseVO responseVO = null;
		ReligionVO religionVO = null;
		try{
			religionVO = (ReligionVO) parseObjectFromRequest(requestVO,ReligionVO.class);
			if(null != religionVO) {
				religionVO = religionManager.saveReligion(religionVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ReligionVO",religionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveReligion Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getReligionById", method = RequestMethod.GET)
	public ResponseVO getReligionById(@RequestParam String religionId){
		if(logger.isDebugEnabled()) {
			logger.debug("getReligionById Starts");
		}
		ResponseVO responseVO = null;
		ReligionVO religionVO = null;
		try{
			religionVO = religionManager.getReligionById(religionId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ReligionVO", religionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getReligionById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllReligionBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllReligionBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = religionManager.getAllReligionBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllReligionBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateReligion", method = RequestMethod.POST)
	public ResponseVO updateReligion(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateReligion Starts");
		}
		ResponseVO responseVO = null;
		try {
			ReligionVO religionVO = (ReligionVO) parseObjectFromRequest(requestVO, ReligionVO.class);
			if (null != religionVO) {
				religionVO = religionManager.updateReligion(religionVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("ReligionVO", religionVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateReligion Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteReligion", method = RequestMethod.DELETE)
	public ResponseVO deleteReligion(@RequestParam String religionId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteReligion Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = religionManager.deleteReligion(religionId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteReligion Ends");
		}
		return responseVO;
	}
	
}
