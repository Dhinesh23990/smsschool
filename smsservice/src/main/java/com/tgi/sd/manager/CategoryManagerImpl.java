package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CategoryDAO;
import com.tgi.sd.dao.EventDAO;
import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class CategoryManagerImpl implements CategoryManager{
	
	private static Logger logger = Logger.getLogger(CategoryManagerImpl.class);
	

	private CategoryDAO categoryDAO;
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}
	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	@Override
	public CategoryVO saveCategory(CategoryVO categoryVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveCategory starts");
		}
		CategoryVO category = categoryDAO.getCategoryByName(categoryVO.getCategoryName(), null,
				categoryVO.getSchoolId());
		if(category != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		categoryVO = categoryDAO.saveCategory(categoryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCategory ends");
		}
		return categoryVO;
		
	}
	@Override
	public CategoryVO getCategoryById(String categoryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryById Starts");
		}
		
		CategoryVO categoryVO = categoryDAO.getCategoryById(categoryId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryById Ends");
		}
		
		return categoryVO;
	}
	
	@Override
	public Map<String, Object> getAllCategoryBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = categoryDAO.getCategoryCountBySchoolId(schoolId);
		List<CategoryVO> categoryVOs = categoryDAO.getAllCategoryBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("categoryVOs", categoryVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getCategoryCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryCountBySchoolId Starts");
		}

		long cnt = categoryDAO.getCategoryCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public CategoryVO updateCategory(CategoryVO categoryVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateCategory Starts");
		}
		
		CategoryVO category = categoryDAO.getCategoryByName(categoryVO.getCategoryName(), categoryVO.getId(),
				categoryVO.getSchoolId());
		if(category != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		categoryVO = categoryDAO.updateCategory(categoryVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateCategory Ends");
		}
		return categoryVO;
	}
	
	@Override
	public boolean deleteCategory(String categoryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCategory Starts");
		}
		
		boolean isDeleted = categoryDAO.deleteCategory(categoryId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCategory Ends");
		}
		return isDeleted;
	}
	/*@Override
	public Map<String, Object> getEventsByFilter(FilterVO filterVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		List<EventVO> eventVOs = eventDAO.getEventsBySchoolIdAndDate(filterVO.getSchoolId(),
				filterVO.getEventStartDate(), filterVO.getEventEndDate());
		if (logger.isDebugEnabled()) {
			logger.debug("getEventsByFilter Ends");
		}		
		responseObjectsMap.put("eventVOs", eventVOs);
		return responseObjectsMap;
	}*/
	
	
}
