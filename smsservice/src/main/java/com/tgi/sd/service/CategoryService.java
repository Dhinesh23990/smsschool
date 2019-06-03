package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.CategoryManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/category")
public class CategoryService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(CategoryService.class);
	
	private CategoryManager categoryManager;
	
	public CategoryManager getCategoryManager() {
		return categoryManager;
	}

	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public ResponseVO saveCategory(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveCategory Starts");
		}
		ResponseVO responseVO = null;
		CategoryVO categoryVO = null;
		try{
			categoryVO = (CategoryVO) parseObjectFromRequest(requestVO,CategoryVO.class);
			if(null != categoryVO) {
				categoryVO = categoryManager.saveCategory(categoryVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CategoryVO",categoryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
			
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveCategory Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getCategoryById", method = RequestMethod.GET)
	public ResponseVO getCategoryById(@RequestParam String categoryId){
		if(logger.isDebugEnabled()) {
			logger.debug("getCategoryById Starts");
		}
		ResponseVO responseVO = null;
		CategoryVO categoryVO = null;
		try{
			categoryVO = categoryManager.getCategoryById(categoryId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("CategoryVO", categoryVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getCategoryById Ends");
		}
		return responseVO;
	}
	@RequestMapping(value="/getAllCategoryBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllCategoryBySchoolId(@RequestParam String schoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = categoryManager.getAllCategoryBySchoolId(schoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public ResponseVO updateCategory(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateCategory Starts");
		}
		ResponseVO responseVO = null;
		CategoryVO categoryVO = null;
		try {
           categoryVO = (CategoryVO) parseObjectFromRequest(requestVO, CategoryVO.class);
			if (null != categoryVO) {
				categoryVO = categoryManager.updateCategory(categoryVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("CategoryVO", categoryVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateCategory Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.DELETE)
	public ResponseVO deleteCategory(@RequestParam String categoryId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCategory Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = categoryManager.deleteCategory(categoryId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteCategory Ends");
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
