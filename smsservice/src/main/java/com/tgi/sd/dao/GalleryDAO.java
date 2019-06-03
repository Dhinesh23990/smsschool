package com.tgi.sd.dao;

import java.util.Date;
import java.util.List;

import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.GalleryVO;
import com.tgi.sd.exception.SMSBusinessException;


public interface GalleryDAO {

    GalleryVO saveGallery(GalleryVO galleryVO) throws SMSBusinessException;

    GalleryVO getGalleryById(String galleryId) throws SMSBusinessException;

	List<GalleryVO> getAllGalleryBySchoolId(String schoolId, Integer pageIndex,
			Integer pageSize) throws SMSBusinessException;

	long getGalleryCountBySchoolId(String schoolId) throws SMSBusinessException;

	GalleryVO updateGallery(GalleryVO galleryVO) throws SMSBusinessException;

	boolean deleteGallery(String galleryId) throws SMSBusinessException;

	GalleryVO getGalleryByName(String id, String schoolId)
			throws SMSBusinessException;

	//List<CategoryVO> getCategorysBySchoolIdAndDate(String schoolId, Date startDate, Date endDate) throws SMSBusinessException;

}
