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
import com.tgi.sd.domain.config.ExamVO;
import com.tgi.sd.manager.ExamManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/exam")
public class ExamService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(ExamService.class);
	
	private ExamManager examManager;
	
	
	public ExamManager getExamManager() {
		return examManager;
	}

	public void setExamManager(ExamManager examManager) {
		this.examManager = examManager;
	}

	@RequestMapping(value = "/saveExam", method = RequestMethod.POST)
	public ResponseVO saveExam(@RequestBody String requestVO) {
		if(logger.isDebugEnabled()) {
			logger.debug("saveExam Starts");
		}
		ResponseVO responseVO = null;
		ExamVO examVO = null;
		try{
			examVO = (ExamVO) parseObjectFromRequest(requestVO,ExamVO.class);
			if(null != examVO) {
				examVO = examManager.saveExam(examVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ExamVO",examVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("saveExam Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getExamById", method = RequestMethod.GET)
	public ResponseVO getExamById(@RequestParam String examId){
		if(logger.isDebugEnabled()) {
			logger.debug("getExamById Starts");
		}
		ResponseVO responseVO = null;
		ExamVO examVO = null;
		try{
			examVO = examManager.getExamById(examId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("ExamVO", examVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("getExamById Ends");
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getAllExamBySchoolId",method=RequestMethod.GET)
	public ResponseVO getAllExamBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize)
	{
		if(logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Starts");
		}
		ResponseVO responseVO = null;
	    try{
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap = examManager.getAllExamBySchoolId(shoolId, pageIndex, pageSize);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    if(logger.isDebugEnabled()) {
			logger.debug("getAllExamBySchoolId Ends");
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateExam", method = RequestMethod.POST)
	public ResponseVO updateExam(@RequestBody String requestVO)  {
		if(logger.isDebugEnabled()) {
			logger.debug("updateExam Starts");
		}
		ResponseVO responseVO = null;
		try {
			ExamVO examVO = (ExamVO) parseObjectFromRequest(requestVO, ExamVO.class);
			if (null != examVO) {
				examVO = examManager.updateExam(examVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("ExamVO", examVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("updateExam Ends");
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteExam", method = RequestMethod.DELETE)
	public ResponseVO deleteExam(@RequestParam String examId)  {
		if(logger.isDebugEnabled()) {
			logger.debug("deleteExam Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = examManager.deleteExam(examId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if(logger.isDebugEnabled()) {
			logger.debug("deleteExam Ends");
		}
		return responseVO;
	}
	
}
