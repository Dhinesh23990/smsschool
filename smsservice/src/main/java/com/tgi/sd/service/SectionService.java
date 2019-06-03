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
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.manager.SectionManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/section")
public class SectionService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(SectionService.class);
	
	private SectionManager sectionManager;
	
	
	public SectionManager getSectionManager() {
		return sectionManager;
	}

	public void setSectionManager(SectionManager sectionManager) {
		this.sectionManager = sectionManager;
	}

	@RequestMapping(value = "/saveSection", method = RequestMethod.POST)
	public ResponseVO saveSection(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveSection Starts");
		}
		ResponseVO responseVO = null;
		SectionVO sectionVO = null;
		try{
			sectionVO = (SectionVO) parseObjectFromRequest(requestVO,SectionVO.class);
			if(null != sectionVO) {
				sectionVO = sectionManager.saveSection(sectionVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SectionVO",sectionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveSection Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getSectionById", method = RequestMethod.GET)
	public ResponseVO getSectionById(@RequestParam String sectionId){
		if(logger.isDebugEnabled()) {
			logger.debug("getSectionById Starts");
		}
		ResponseVO responseVO = null;
		SectionVO sectionVO = null;
		try{
			sectionVO = sectionManager.getSectionById(sectionId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("SectionVO", sectionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getSectionById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllSectionBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllSectionBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = sectionManager.getAllSectionBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
	public ResponseVO updateSection(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateSection Starts");
		}
		ResponseVO responseVO = null;
		try {
			SectionVO sectionVO = (SectionVO) parseObjectFromRequest(requestVO, SectionVO.class);
			if (null != sectionVO) {
				sectionVO = sectionManager.updateSection(sectionVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("SectionVO", sectionVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateSection Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteSection", method = RequestMethod.DELETE)
	public ResponseVO deleteSection(@RequestParam String sectionId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSection Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = sectionManager.deleteSection(sectionId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteSection Ends");
		}
		return responseVO;
	}
	
}
