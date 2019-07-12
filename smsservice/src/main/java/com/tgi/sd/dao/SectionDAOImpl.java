package com.tgi.sd.dao;

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
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.domain.config.SectionVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

@Transactional
public class SectionDAOImpl extends GenericHibernateDAOImpl<SectionVO, String> implements SectionDAO {

	private static Logger logger = Logger.getLogger(SectionDAOImpl.class);

	@Override
	public SectionVO saveSection(SectionVO sectionVO) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("saveSection() starts");
		}
		sectionVO.setId(UUID.randomUUID().toString());
		
		super.getSession().save(sectionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveSection() ends");
		}
		return sectionVO;
	}

	@Override
	public SectionVO getSectionByName(String sectionName,String id, String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionByName Starts");
		}
		
		SectionVO sectionVO = null;
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SectionVO WHERE sectionName = :sectionName and schoolId = :schoolId");
			if(id != null)
				queryBuilder.append(" and Id = :Id");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("sectionName", sectionName);
			query.setParameter("schoolId", schoolId);
			if(id != null)
				query.setParameter("Id", id);
		
		List<SectionVO> sectionList = query.list();
		
			if (sectionList != null && sectionList.size() > 0)
				sectionVO = sectionList.get(0);
		
			} catch (HibernateException re) {
				logger.error("" + re.getMessage());
				throw new SMSBusinessException(re.getMessage());
			}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionByName Ends");
		}
		
		return sectionVO;
	}
	
	@Override
	public SectionVO getSectionById(String sectionId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionById() starts");
		}
		SectionVO sectionVO =  (SectionVO) super.getSession().get(SectionVO.class, sectionId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionById() ends");
		}
		return sectionVO;
	}

	@Override
	public List<SectionVO> getAllSectionBySchoolId(String schoolId, Integer pageNo, Integer pageSize) throws SMSBusinessException {
		
		List<SectionVO> sectionLst = null;
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Starts");
		}
		try {
			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			
			queryBuilder.append("from SectionVO WHERE schoolId = :schId");
			queryBuilder.append(" ORDER by createdDate DESC");
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);

			if (pageNo != null && pageNo >0) {	
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
			}

			sectionLst = query.list();

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllSectionBySchoolId Ends");
		}
		return sectionLst;

	}

	@Override
	public long getSectionCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {

			logger.debug("getSectionCountBySchoolId Starts");
		}
		int userOrderCount = 0;
		try {

			Session session = getSession();
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select count(*) from SectionVO WHERE schoolId = :schId");
			
			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("schId", schoolId);
						
			Long orderCount =(Long) query.uniqueResult();
			userOrderCount = (int) (long)orderCount;

		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getSectionCountBySchoolId Ends");
		}
		return userOrderCount;
	}

	@Override
	public SectionVO updateSection(SectionVO sectionVO) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("updateSection Starts");
		}
		
		super.getSession().saveOrUpdate(sectionVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateSection Ends");
		}
		return sectionVO;
	}

	@Override
	public boolean deleteSection(String sectionId) throws SMSBusinessException {

		if (logger.isDebugEnabled()) {
			logger.debug("deleteSection Starts");
		}
		boolean isDeleted = false;
		try {
			Session session = getSession();
			SectionVO adminVO = new SectionVO();
			adminVO.setId(sectionId);
			session.delete(adminVO);			
			isDeleted= true;
		} catch (HibernateException re) {
			logger.error("" + re.getMessage());
			throw new SMSBusinessException(re.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSection Ends");
		}
		return isDeleted;

	}

}
