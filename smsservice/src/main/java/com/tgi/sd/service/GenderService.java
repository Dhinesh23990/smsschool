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
import com.tgi.sd.domain.config.GenderVO;
import com.tgi.sd.manager.GenderManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/gender")
public class GenderService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(GenderService.class);
	
	private GenderManager genderManager;
	
	
	public GenderManager getGenderManager() {
		return genderManager;
	}

	public void setGenderManager(GenderManager genderManager) {
		this.genderManager = genderManager;
	}

	@RequestMapping(value = "/saveGender", method = RequestMethod.POST)
	public ResponseVO saveGender(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveGender Starts");
		}
		ResponseVO responseVO = null;
		GenderVO genderVO = null;
		try{
			genderVO = (GenderVO) parseObjectFromRequest(requestVO,GenderVO.class);
			if(null != genderVO) {
				genderVO = genderManager.saveGender(genderVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GenderVO",genderVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveGender Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getGenderById", method = RequestMethod.GET)
	public ResponseVO getGenderById(@RequestParam String genderId){
		if(logger.isDebugEnabled()) {
			logger.debug("getGenderById Starts");
		}
		ResponseVO responseVO = null;
		GenderVO genderVO = null;
		try{
			genderVO = genderManager.getGenderById(genderId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GenderVO", genderVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getGenderById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllGenderBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllGenderBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = genderManager.getAllGenderBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllGenderBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updategender", method = RequestMethod.POST)
	public ResponseVO updateReligion(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updategender Starts");
		}
		ResponseVO responseVO = null;
		try {
			GenderVO genderVO = (GenderVO) parseObjectFromRequest(requestVO, GenderVO.class);
			if (null != genderVO) {
				genderVO = genderManager.updateGender(genderVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("GenderVO", genderVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updategender Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteGender", method = RequestMethod.DELETE)
	public ResponseVO deleteGender(@RequestParam String genderId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGender Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = genderManager.deleteGender(genderId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGender Ends");
		}
		return responseVO;
	}
	
}
