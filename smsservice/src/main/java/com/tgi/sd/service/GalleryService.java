package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.tgi.sd.domain.GalleryVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.GalleryManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/gallery")
public class GalleryService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(GalleryService.class);
	
	private GalleryManager galleryManager;
	
	public GalleryManager getGalleryManager() {
		return galleryManager;
	}

	public void setGalleryManager(GalleryManager galleryManager) {
		this.galleryManager = galleryManager;
	}

	@RequestMapping(value = "/saveGallery", method = RequestMethod.POST)
	public ResponseVO saveGallery(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveGallery Starts");
		}
		ResponseVO responseVO = null;
		GalleryVO galleryVO = null;
		try{
			galleryVO = (GalleryVO) parseObjectFromRequest(requestVO,GalleryVO.class);
			if(null != galleryVO) {
				galleryVO = galleryManager.saveGallery(galleryVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GalleryVO",galleryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
			
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveGallery Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getGalleryById", method = RequestMethod.GET)
	public ResponseVO getGalleryById(@RequestParam String galleryId){
		if(logger.isDebugEnabled()) {
			logger.debug("getGalleryById Starts");
		}
		ResponseVO responseVO = null;
		GalleryVO galleryVO = null;
		try{
			galleryVO = galleryManager.getGalleryById(galleryId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("GalleryVO", galleryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getGalleryById Ends");
		}
		return responseVO;
	}
	@RequestMapping(value="/getAllGalleryBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllGalleryBySchoolId(@RequestParam String schoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = galleryManager.getAllGalleryBySchoolId(schoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateGallery", method = RequestMethod.POST)
	public ResponseVO updateGallery(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateGallery Starts");
		}
		ResponseVO responseVO = null;
		GalleryVO galleryVO = null;
		try {
			galleryVO = (GalleryVO) parseObjectFromRequest(requestVO, GalleryVO.class);
			if (null != galleryVO) {
				galleryVO = galleryManager.updateGallery(galleryVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("GalleryVO", galleryVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateGallery Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteGallery", method = RequestMethod.DELETE)
	public ResponseVO deleteGallery(@RequestParam String galleryId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGallery Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = galleryManager.deleteGallery(galleryId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteGallery Ends");
		}
		return responseVO;
	}

	
	/*@RequestMapping(value="/getEventsByFilter",method=RequestMethod.POST)
	public ResponseVO getEventsByFilter(@RequestBody String requestVO)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		FilterVO filterVO = null;
		try{
			filterVO = (FilterVO) parseObjectFromRequest(requestVO,FilterVO.class);
			if(null != filterVO) {
	    		responseObjectsMap = eventManager.getEventsByFilter(filterVO);
	    		responseVO = createSuccessResponseVO(responseObjectsMap);
	    	}
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Ends");
		}
	    return responseVO;
		
	}*/


	
}
