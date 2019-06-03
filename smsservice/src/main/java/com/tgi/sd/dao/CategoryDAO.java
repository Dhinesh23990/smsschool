package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface CategoryDAO {

    CategoryVO saveCategory(CategoryVO categoryVO) throws SMSBusinessException;

    CategoryVO getCategoryById(String categoryId) throws SMSBusinessException;

	List<CategoryVO> getAllCategoryBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getCategoryCountBySchoolId(String schoolId) throws SMSBusinessException;

	CategoryVO updateCategory(CategoryVO categoryVO) throws SMSBusinessException;

	boolean deleteCategory(String categoryId) throws SMSBusinessException;

	CategoryVO getCategoryByName(String categoryName, String id, String schoolId)
			throws SMSBusinessException;

	//List<CategoryVO> getCategorysBySchoolIdAndDate(String schoolId, Date startDate, Date endDate) throws SMSBusinessException;

}
