package com.tgi.sd.manager;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.tgi.sd.dao.TestDAO;
import com.tgi.sd.domain.TestVO;

public class TestManagerImpl implements TestManager{
	
	private static Logger logger = Logger.getLogger(TestManagerImpl.class);
			
	private TestDAO testDAO;
	
	@Override
	public void create(TestVO testVo) {
		if (logger.isDebugEnabled()) {
			logger.debug("TestManagerImpl create() start");
		}
		
		testDAO.save(testVo);
		
		if (logger.isDebugEnabled()) {
			logger.debug("TestManagerImpl create() End");
		}
	}
	
	@Override
	public TestVO getTestById(String id) {
		if (logger.isDebugEnabled()) {
			logger.debug("BarManagerImpl getBarByCriteria() starts");
		}
		
		TestVO testVo = null;
		
		testVo = testDAO.get(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("TestManagerImpl create() End");
		}
		
		return testVo;
	}
	
	public TestVO getTestByCriteria(Map<String, Object> criteriaMap) {
		if (logger.isDebugEnabled()) {
			logger.debug("BarManagerImpl getBarByCriteria() starts");
		}
		
		TestVO testVo = null;
		String name = (String) criteriaMap.get("name");
		
		List<TestVO> testVos = testDAO.getTestByCriteria(name);
		if (null != testVos && testVos.size() > 0)
			testVo = testVos.get(0);
		
		if (logger.isDebugEnabled()) {
			logger.debug("TestManagerImpl create() End");
		}
		
		return testVo;	
	}
	
	
	public TestDAO getTestDAO() {
		return testDAO;
	}

	public void setTestDAO(TestDAO testDAO) {
		this.testDAO = testDAO;
	}
}
