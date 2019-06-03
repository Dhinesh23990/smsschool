 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.GalleryVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface GalleryManager {

	GalleryVO saveGallery(GalleryVO galleryVO) throws SMSBusinessException;

	GalleryVO getGalleryById(String galleryId) throws SMSBusinessException;

	GalleryVO updateGallery(GalleryVO galleryVO) throws SMSBusinessException;

	boolean deleteGallery(String galleryId) throws SMSBusinessException;

	long getGalleryCountBySchoolId(String schoolId) throws SMSBusinessException;

	Map<String, Object> getAllGalleryBySchoolId(String schoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;

//	Map<String, Object> getCategorysByFilter(FilterVO filterVO) throws SMSBusinessException;
	
	
}
