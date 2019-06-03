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
import com.tgi.sd.domain.config.ExamSessionVO;
import com.tgi.sd.manager.ExamSessionManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/examSession")
public class ExamSessionService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(ExamSessionService.class);
	
	private ExamSessionManager examSessionManager;
	
	
	public ExamSessionManager getExamSessionManager() {
		return examSessionManager;
	}

	public void setExamSessionManager(ExamSessionManager examSessionManager) {
		this.examSessionManager = examSessionManager;
	}

	@RequestMapping(value = "/saveExamSession", method = RequestMethod.POST)
	public ResponseVO saveExamSession(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveExamSession Starts");
		}
		ResponseVO responseVO = null;
		ExamSessionVO examSessionVO = null;
		try{
			examSessionVO = (ExamSessionVO) parseObjectFromRequest(requestVO,ExamSessionVO.class);
			if(null != examSessionVO) {
				examSessionVO = examSessionManager.saveExamSession(examSessionVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ExamSessionVO",examSessionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveExamSession Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getExamSessionById", method = RequestMethod.GET)
	public ResponseVO getExamSessionById(@RequestParam String examSessionId){
		if(logger.isDebugEnabled()) {
			logger.debug("getExamSessionById Starts");
		}
		ResponseVO responseVO = null;
		ExamSessionVO examSessionVO = null;
		try{
			examSessionVO = examSessionManager.getExamSessionById(examSessionId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ExamSessionVO", examSessionVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getExamSessionById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllExamSessionBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllExamSessionBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = examSessionManager.getAllExamSessionBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllExamSessionBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateExamSession", method = RequestMethod.POST)
	public ResponseVO updateExamSession(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateExamSession Starts");
		}
		ResponseVO responseVO = null;
		try {
			ExamSessionVO examSessionVO = (ExamSessionVO) parseObjectFromRequest(requestVO, ExamSessionVO.class);
			if (null != examSessionVO) {
				examSessionVO = examSessionManager.updateExamSession(examSessionVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("ExamSessionVO", examSessionVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateExamSession Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteExamSession", method = RequestMethod.DELETE)
	public ResponseVO deleteExamSession(@RequestParam String examSessionId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = examSessionManager.deleteExamSession(examSessionId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteExamSession Ends");
		}
		return responseVO;
	}
	
}
