package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.TestVO;

public interface TestManager {
	public void create(TestVO testVo);
	public TestVO getTestById(String id);
	public TestVO getTestByCriteria(Map<String, Object> criteriaMap);
}
