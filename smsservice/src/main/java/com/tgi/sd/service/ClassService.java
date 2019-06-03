package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.config.ClassVO;
import com.tgi.sd.manager.ClassManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/class")
public class ClassService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(ClassService.class);
	
	private ClassManager classManager;
	
	
	public ClassManager getClassManager() {
		return classManager;
	}

	public void setClassManager(ClassManager classManager) {
		this.classManager = classManager;
	}

	@RequestMapping(value = "/saveClass", method = RequestMethod.POST)
	public ResponseVO saveClass(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveClass Starts");
		}
		ResponseVO responseVO = null;
		ClassVO classVO = null;
		try{
			classVO = (ClassVO) parseObjectFromRequest(requestVO,ClassVO.class);
			if(null != classVO) {
				classVO = classManager.saveClass(classVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ClassVO",classVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveClass Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getClassById", method = RequestMethod.GET)
	public ResponseVO getClassById(@RequestParam String classId){
		if(logger.isDebugEnabled()) {
			logger.debug("getClassById Starts");
		}
		ResponseVO responseVO = null;
		ClassVO classVO = null;
		try{
			classVO = classManager.getClassById(classId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ClassVO", classVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getClassById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllClassBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllClassBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = classManager.getAllClassBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllClassBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateClass", method = RequestMethod.POST)
	public ResponseVO updateClass(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateClass Starts");
		}
		ResponseVO responseVO = null;
		try {
			ClassVO classVO = (ClassVO) parseObjectFromRequest(requestVO, ClassVO.class);
			if (null != classVO) {
				classVO = classManager.updateClass(classVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("ClassVO", classVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateClass Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteClass", method = RequestMethod.DELETE)
	public ResponseVO deleteClass(@RequestParam String classId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteClass Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = classManager.deleteClass(classId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteClass Ends");
		}
		return responseVO;
	}
	
}
