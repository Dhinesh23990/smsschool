package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.CategoryVO;
import com.tgi.sd.exception.SMSBusinessException;

public class CategoryDAOImpl extends GenericHibernateDAOImpl<CategoryVO, String> implements CategoryDAO {

	private static Logger logger = Logger.getLogger(CategoryDAOImpl.class);
	
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
	public CategoryVO saveCategory(CategoryVO categoryVO)
			throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveCategory() starts");
		}
		categoryVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(categoryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveCategory() ends");
		}
		return categoryVO;
	}

	@Override
	public CategoryVO getCategoryById(String categoryId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryById() starts");
		}
		
		CategoryVO categoryVO = (CategoryVO) super.getSession().get(CategoryVO.class, categoryId);	
		
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryById() ends");
		}
		return categoryVO;
	}

	@Override
	public List<CategoryVO> getAllCategoryBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {

		List<CategoryVO> categoryLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("from CategoryVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);

			categoryLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllCategoryBySchoolId Ends");
		}
		return categoryLst;

	}

	@Override
	public long getCategoryCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getCategoryCountBySchoolId Starts");
		}
		int count = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from CategoryVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			count = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryCountBySchoolId Ends");
		}
		return count;
	}

	@Override
	public CategoryVO updateCategory(CategoryVO categoryVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateCategory Starts");
		}
	
		super.getSession().saveOrUpdate(categoryVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateEvent Ends");
		}
		return categoryVO;
	}


	@Override
	public boolean deleteCategory(String categoryId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteCategory Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			CategoryVO adminVO = new CategoryVO();
			adminVO.setId(categoryId);
			session.delete(adminVO);			
			isDeleted= true;
			
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteCategory Ends");
		}
		return isDeleted;

	}

	@Override
	public CategoryVO getCategoryByName(String categoryName, String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getCategoryByName Starts");
		}
		
		CategoryVO categoryVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from CategoryVO WHERE categoryName = :categoryName");
			queryBuilder.append(" and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("categoryName", categoryName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
			
			List<CategoryVO> catList = query.list();
			
			if (catList != null && catList.size() > 0)
				categoryVO = catList.get(0);

			if (logger.isDebugEnabled()) {
				logger.debug("getCategoryByName Ends");
			}

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			 throw new SMSBusinessException(re.getMessage());
		}
		
		return categoryVO;
	}
}
