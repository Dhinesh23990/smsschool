package com.tgi.sd.service;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.tgi.sd.common.service.BaseService;
import com.tgi.sd.domain.BaseVO;
import com.tgi.sd.domain.RequestVO;
import com.tgi.sd.domain.TestVO;
import com.tgi.sd.manager.TestManager;

@RestController
@RequestMapping("/home")
public class TestService extends BaseService  {
	private static Logger logger = Logger.getLogger(TestService.class);
	
	private TestManager testManager;
	
	@RequestMapping(value = "/testCreateNewService", method = RequestMethod.POST)
	public TestVO createNewTest(@RequestBody String requestVO) throws Exception {
		//String requestVO = "{\"RequestVO\":{\"testVO\":{\"name\":\"Sachin Tendulkar\",\"address\":\"main road\",\"pincode\":\"625014\"}}}";
		TestVO testVo = (TestVO) parseVoFromRequest(requestVO,TestVO.class);
		if (null != testVo) {
			testManager.create(testVo);
		}
		return testVo;
	}
	
	@RequestMapping(value = "/testCreateNewServiceWithBody", method = RequestMethod.POST)
	public TestVO createNewTestWithBody(@RequestBody String bodyStr) throws Exception {
		//String requestVO = "{\"RequestVO\":{\"testVO\":{\"name\":\"Sachin Tendulkar\",\"address\":\"main road\",\"pincode\":\"625014\"}}}";
		TestVO testVo = (TestVO) parseVoFromRequest(bodyStr,TestVO.class);
		if (null != testVo) {
			testManager.create(testVo);
		}
		return testVo;
	}	
	
	@RequestMapping(value = "/testGetService", method = RequestMethod.GET)
	public TestVO getTest(@RequestParam String id) throws Exception {
		
		TestVO testVo = null;
		
		testVo = testManager.getTestById(id);

		return testVo;
	}	
	
	@RequestMapping(value = "/testGetByCriteriaService", method = RequestMethod.GET)
	public TestVO getTest1(@RequestParam String name) throws Exception {
		TestVO testVo = null;
		
		HashMap h = new HashMap();
		h.put("name", name);
		testVo = testManager.getTestByCriteria(h);

		return testVo;
	}		
	

	public TestManager getTestManager() {
		return testManager;
	}

	public void setTestManager(TestManager testManager) {
		this.testManager = testManager;
	}
	

}
