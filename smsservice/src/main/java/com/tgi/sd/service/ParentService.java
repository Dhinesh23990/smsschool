package com.tgi.sd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ParentVO;
import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.manager.ParentManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
public class ParentService extends SMSBaseService  {

	private static Logger logger = Logger.getLogger(ParentService.class);

	ParentManager parentManager;
	
	public ParentManager getParentManager() {
		return parentManager;
	}

	public void setParentManager(ParentManager parentManager) {
		this.parentManager = parentManager;
	}

	@RequestMapping(value = EndPointConstants.SAVE_PARENT, method = RequestMethod.POST)
	public ResponseVO saveParent(@RequestBody String requestVO){
		if (logger.isDebugEnabled()) {
			logger.debug("Start saveParent :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			ParentVO parentVO = (ParentVO) parseObjectFromRequest(requestVO,ParentVO.class);
			parentVO = parentManager.saveParent(parentVO);
			responseObjectMap.put("parentVO", parentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End saveParent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_PARENT, method = RequestMethod.GET)
	public ResponseVO getParent(@RequestParam String id) {
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			ParentVO parentVO = parentManager.getParent(id);
			responseObjectMap.put("parentVO", parentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.UPDATE_PARENT, method = RequestMethod.POST)
	public ResponseVO updateParent(@RequestBody String requestVO){
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateParent :::");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			ParentVO parentVO = (ParentVO) parseObjectFromRequest(requestVO,ParentVO.class);
			parentVO = parentManager.updateParent(parentVO);
			responseObjectMap.put("parentVO", parentVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateParent :::");
		}
		return responseVO;
	}	
	
	@RequestMapping(value = EndPointConstants.GET_ALL_PARENTT, method = RequestMethod.GET)
	public ResponseVO getAllParent() {
		List<ParentVO> parentList;
		if (logger.isDebugEnabled()) {
			logger.debug("Start getAllParent :::" );
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			parentList = parentManager.getAllParent();
			responseObjectMap.put("parentList", parentList);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getAllParent :::");
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.REGISTER_PARENT_BY_MOBILENO, method = RequestMethod.POST)
	public ResponseVO registerParentByMobileNo(@RequestParam String mobileNo) {
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = null;
		try {
			responseObjectMap = parentManager.registerParentByMobileNo(mobileNo);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = EndPointConstants.GET_SCHOOL_LIST_BY_PARENT_MOBILENO, method = RequestMethod.GET)
	public ResponseVO getSchoolListByParentMobileNo(@RequestParam String mobileNo) {
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = null;
		try {
			responseObjectMap = parentManager.getSchoolListByParentMobileNo(mobileNo);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;	
		
	}
}
