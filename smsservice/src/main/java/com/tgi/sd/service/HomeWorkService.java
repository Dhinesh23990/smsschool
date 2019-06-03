package com.tgi.sd.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.HomeWorkVO;
import com.tgi.sd.domain.FilterVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.HomeWorkManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/homework")
public class HomeWorkService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(HomeWorkService.class);
	
	private HomeWorkManager homeworkManager;
	
	public HomeWorkManager getHomeworkManager() {
		return homeworkManager;
	}

	public void setHomeworkManager(HomeWorkManager homeworkManager) {
		this.homeworkManager = homeworkManager;
	}

	@RequestMapping(value = "/saveHomeWork", method = RequestMethod.POST)
	public ResponseVO saveHomeWork(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveHomeWork Starts");
		}
		
		ResponseVO responseVO = null;
		HomeWorkVO homeworkVO = null;
		try{
			homeworkVO = (HomeWorkVO) parseObjectFromRequest(requestVO,HomeWorkVO.class);
			if(null != homeworkVO) {
				homeworkVO = homeworkManager.saveHomeWork(homeworkVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("HomeWorkVO",homeworkVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveHomeWork Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getHomeWorkById", method = RequestMethod.GET)
	public ResponseVO getHomeWorkById(@RequestParam String homeworkId){
		if(logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById Starts");
		}
		ResponseVO responseVO = null;
		HomeWorkVO homeworkVO = null;
		try{
			homeworkVO = homeworkManager.getHomeWorkById(homeworkId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("HomeWorkVO", homeworkVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getHomeWorkById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllHomeWorkByStudentId",method=RequestMethod.GET)
	public ResponseVO getAllHomeWorkBySchoolId(@RequestParam String studentId,String schoolId,String mobileNo, int pageIndex, int pageSize)
	{
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	System.out.println("ids ===>>> "+studentId);
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = homeworkManager.getAllHomeWorkBySchoolId(schoolId,studentId, mobileNo, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    	System.out.println("ids responseObjectsMap===>>> "+responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllHomeWorkBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateHomeWork", method = RequestMethod.POST)
	public ResponseVO updateNotification(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Starts");
		}
		ResponseVO responseVO = null;
		try {
			HomeWorkVO homeworkVO = (HomeWorkVO) parseObjectFromRequest(requestVO, HomeWorkVO.class);
			if (null != homeworkVO) {
				homeworkVO = homeworkManager.updateHomeWork(homeworkVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("HomeWorkVO", homeworkVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateHomeWork Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteHomeWork", method = RequestMethod.DELETE)
	public ResponseVO deleteHomeWork(@RequestParam String homeworkId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = homeworkManager.deleteHomeWork(homeworkId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteHomeWork Ends");
		}
		return responseVO;
	}	
	
	@RequestMapping(value="/getHomeWorkByFilter",method=RequestMethod.POST)
	public ResponseVO getHomeWorkByFilter(@RequestBody String requestVO)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getHomeWorkByFilter Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		FilterVO filterVO = null;
		try{
			filterVO = (FilterVO) parseObjectFromRequest(requestVO,FilterVO.class);
			if(null != filterVO) {
	    		responseObjectsMap = homeworkManager.getHomeWorkByFilter(filterVO);
	    		responseVO = createSuccessResponseVO(responseObjectsMap);
	    	}
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getHomeWorkByFilter Ends");
		}
	    return responseVO;
		
	}
	
}
