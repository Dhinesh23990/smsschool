package com.tgi.sd.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.domain.EventVO;
import com.tgi.sd.domain.GalleryVO;
import com.tgi.sd.domain.config.EducationalVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;
import com.tgi.sd.util.SMSUtility;

public class GalleryDAOImpl extends GenericHibernateDAOImpl<GalleryVO, String> implements GalleryDAO {

	private static Logger logger = Logger.getLogger(GalleryDAOImpl.class);
	
	/*public static final String DB_FORMAT_DATETIME = "yyyy-M-d HH:mm:ss";
	@Override
	public List<CategoryVO> getCategorysBySchoolIdAndDate(String schoolId,Date startDate,Date endDate) throws SMSBusinessException {
		Query query = new Query();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1;
		int dayOfmonth = calendar.get(Calendar.DAY_OF_MONTH);
		
		if(null !=endDate) {
			GregorianCalendar calendarEnd = new GregorianCalendar();
			calendarEnd.setTime(endDate);
			int endYear = calendarEnd.get(Calendar.YEAR);
			int endMonth = calendarEnd.get(Calendar.MONTH) +1;
			int endDayOfmonth = calendarEnd.get(Calendar.DAY_OF_MONTH);
			query.addCriteria(Criteria.where("eventStartDate").ne(null).orOperator(
		            Criteria.where("eventStartDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)).andOperator(Criteria.where("eventStartDate").lte(SMSUtility.getDate(endYear+"-"+endMonth+"-"+endDayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))),
		            Criteria.where("eventEndDate").lte(SMSUtility.getDate(endYear+"-"+endMonth+"-"+endDayOfmonth+" 23:59:59", DB_FORMAT_DATETIME)).andOperator(Criteria.where("eventEndDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)))
		        ));
		} else {
			query.addCriteria(Criteria.where("eventStartDate").ne(null).andOperator(
	            Criteria.where("eventStartDate").gte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 00:00:00", DB_FORMAT_DATETIME)),
	            Criteria.where("eventStartDate").lte(SMSUtility.getDate(year+"-"+month+"-"+dayOfmonth+" 23:59:59", DB_FORMAT_DATETIME))
	        )); 
		}
		query.addCriteria(Criteria.where("schoolId").is(schoolId));
		query.with(new Sort(new Order(Direction.ASC, "eventStartDate")));
		List<EventVO> eventVOList = (List<EventVO>) findByQuery(query, EventVO.class);
	 
		return eventVOList;
	}
*/

	@Override
	public GalleryVO saveGallery(GalleryVO galleryVO)
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveGallery() starts");
		}
		galleryVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(galleryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveGallery() ends");
		}
		return galleryVO;
	}

	@Override
	public GalleryVO getGalleryById(String galleryId)
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryById() starts");
		}
		
		GalleryVO galleryVO = (GalleryVO) super.getSession().get(GalleryVO.class, galleryId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryById() ends");
		}
		return galleryVO;
	}


	@Override
	public List<GalleryVO> getAllGalleryBySchoolId(String schoolId,
			Integer pageIndex, Integer pageSize) throws SMSBusinessException {
		
		List<GalleryVO> galleryLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from GalleryVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageIndex - 1) * pageSize);
			query.setMaxResults(pageSize);

			galleryLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllGalleryBySchoolId Ends");
		}
		return galleryLst;

	}

	@Override
	public long getGalleryCountBySchoolId(String schoolId)
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getGalleryCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from GalleryVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryCountBySchoolId Ends");
		}
		return userOrderCount;
	}
	@Override
	public GalleryVO updateGallery(GalleryVO galleryVO)
			throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateGallery Starts");
		}
		
		super.getSession().saveOrUpdate(galleryVO);
		
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
		boolean isDeleted = false;
		try {
			Session session = getSession();
			GalleryVO adminVO = new GalleryVO();
			adminVO.setId(galleryId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteGallery Ends");
		}
		return isDeleted;

	}

	@Override
	public GalleryVO getGalleryByName(String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getGalleryByName Starts");
		}
		
		GalleryVO galleryVO = null;
		
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from GalleryVO WHERE schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
	
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<GalleryVO> galleryList = query.list();
			
			if (galleryList != null && galleryList.size() > 0)
				galleryVO = galleryList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getGalleryByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return galleryVO;
	}
}
