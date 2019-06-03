package com.tgi.sd.dao;

import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.common.dao.GenericHibernateDAOImpl;
import com.tgi.sd.common.exception.JMRuntimeException;
import com.tgi.sd.domain.TestVO;

@Transactional
public class TestDAOImpl extends GenericHibernateDAOImpl<TestVO, String> implements TestDAO {
	private static Logger logger = Logger.getLogger(TestDAOImpl.class);
	
	@Override
	public void save(TestVO testVo)  {
		if (logger.isDebugEnabled()) {
			logger.debug("TestDAOImpl saveBar() starts");
		}
		
		try {
			testVo.setId(UUID.randomUUID().toString());
		
			super.getSession().save(testVo);
		}
		catch (HibernateException re) {
			logger.error("" + re.getMessage());
		}
		
	}
	
	@Override
	public TestVO get(String testId) {
		if (logger.isDebugEnabled()) {

			logger.debug("BarDAOImpl getBarById() Starts");
		}
		
		TestVO testVO = null;
		try {
			// to find by barId in Bar Collection
			testVO = (TestVO) super.getSession().get(TestVO.class, testId);
	
			if (logger.isDebugEnabled()) {
	
				logger.debug("BarDAOImpl getBarById() Ends");
			}
		}
		catch (HibernateException re) {
			logger.error("" + re.getMessage());
		}
		
		return testVO;
	}
	
	@Override
	public List<TestVO> getTestByCriteria(String name) {
		if (logger.isDebugEnabled()) {

			logger.debug("BarDAOImpl getBarById() Starts");
		}
		
		List<TestVO> testVOs = null;
		try {
			// to find by barId in Bar Collection
			Session session = getSession();
			
			StringBuilder queryString = new StringBuilder();
			queryString.append("FROM TestVO WHERE name=:customerName");
			Query query = session.createQuery(queryString.toString());
			
			testVOs = query.list(); 
			
			
			if (logger.isDebugEnabled()) {
	
				logger.debug("BarDAOImpl getBarById() Ends");
			}
		}
		catch (HibernateException re) {
			logger.error("" + re.getMessage());
		}
		
		return testVOs;
	}
}
