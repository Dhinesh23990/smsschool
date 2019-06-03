 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface CategoryManager {

	CategoryVO saveCategory(CategoryVO categoryVO) throws SMSBusinessException;

	CategoryVO getCategoryById(String categoryId) throws SMSBusinessException;

	CategoryVO updateCategory(CategoryVO categoryVO) throws SMSBusinessException;

	boolean deleteCategory(String categoryId) throws SMSBusinessException;

	long getCategoryCountBySchoolId(String schoolId) throws SMSBusinessException;

	Map<String, Object> getAllCategoryBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

//	Map<String, Object> getCategorysByFilter(FilterVO filterVO) throws SMSBusinessException;
	
	
}
