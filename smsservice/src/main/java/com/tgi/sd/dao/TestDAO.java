package com.tgi.sd.dao;

import java.util.List;
import java.util.Map;

import com.tgi.sd.domain.TestVO;

public interface TestDAO {
	public void save(TestVO testVo);
	public TestVO get(String id);
	public List<TestVO> getTestByCriteria(String name);
	
}
