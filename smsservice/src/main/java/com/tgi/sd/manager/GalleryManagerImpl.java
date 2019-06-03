package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.CategoryDAO;
import com.tgi.sd.dao.EventDAO;
import com.tgi.sd.dao.GalleryDAO;
import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.domain.GalleryVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class GalleryManagerImpl implements GalleryManager{
	
	private static Logger logger = Logger.getLogger(GalleryManagerImpl.class);
	
	private GalleryDAO galleryDAO;
	
	public GalleryDAO getGalleryDAO() {
		return galleryDAO;
	}
	public void setGalleryDAO(GalleryDAO galleryDAO) {
		this.galleryDAO = galleryDAO;
	}
	
	@Override
	public GalleryVO saveGallery(GalleryVO galleryVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveGallery starts");
		}
		/*GalleryVO gallery = galleryDAO.getGalleryByName(galleryVO.getGalleryName(), null,
				galleryVO.getSchoolId());*/
		/*if(gallery != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}*/
		galleryVO = galleryDAO.saveGallery(galleryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGallery ends");
		}
		return galleryVO;
		
	}
	@Override
	public GalleryVO getGalleryById(String galleryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryById Starts");
		}
		
		GalleryVO galleryVO = galleryDAO.getGalleryById(galleryId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryById Ends");
		}
		
		return galleryVO;
	}
	
	@Override
	public Map<String, Object> getAllGalleryBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = galleryDAO.getGalleryCountBySchoolId(schoolId);
		List<GalleryVO> galleryVOs = galleryDAO.getAllGalleryBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("galleryVOs", galleryVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getGalleryCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryCountBySchoolId Starts");
		}

		long cnt = galleryDAO.getGalleryCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public GalleryVO updateGallery(GalleryVO galleryVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateGallery Starts");
		}
		
		GalleryVO gallery = galleryDAO.getGalleryByName( galleryVO.getId(),
				galleryVO.getSchoolId());
		if(gallery != null) {
			throw new SMSBusinessException(SMSConstants.EVENT_ALREADY_EXISTS);
		}
		galleryVO = galleryDAO.updateGallery(galleryVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateGallery Ends");
		}
		return galleryVO;
	}
	
	@Override
	public boolean deleteGallery(String galleryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGallery Starts");
		}
		
		boolean isDeleted = galleryDAO.deleteGallery(galleryId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGallery Ends");
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
