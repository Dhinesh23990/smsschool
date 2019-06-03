package com.tgi.sd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgi.sd.domain.ResponseVO;
import com.tgi.sd.domain.UserVO;
import com.tgi.sd.manager.UserManager;
import com.tgi.sd.service.base.SMSBaseService;
import com.tgi.sd.util.EndPointConstants;

@RestController
@RequestMapping("/rest/user")
public class UserService extends SMSBaseService {
	
	private static Logger logger = Logger.getLogger(UserService.class);
	private UserManager userManager;
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseVO saveUser(@RequestBody String requestVO) {
		ResponseVO responseVO = null;
		UserVO userVO = null;
		try{
			userVO = (UserVO) parseObjectFromRequest(requestVO,UserVO.class);
			if(null != userVO) {
				userManager.saveUser(userVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("UserVO",userVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public ResponseVO getUserById(@RequestParam String id,String customerId,String deviceId){
		ResponseVO responseVO = null;
		UserVO userVO = null;
		try{
			userVO = userManager.getById(id);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("UserVO", userVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
	}
	
	@RequestMapping(value="/getUserByCriteria",method=RequestMethod.GET)
	public ResponseVO getUserByCriteria(@RequestParam String socialType, String socialId,String customerId,String deviceId){
		ResponseVO responseVO = null;
		UserVO userVO = null;
		try {
			HashMap<String,String> h = new HashMap<String,String>();
			h.put("socialType", socialType);
			h.put("socialId", socialId);
			userVO = userManager.getByCriteria(h);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("UserVO", userVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;	
	}
	@RequestMapping(value="/validateUser",method=RequestMethod.GET)
	public ResponseVO login(@RequestParam String userName, String password,String customerId,String deviceId)
	{
		ResponseVO responseVO = null;
		UserVO userVO = null;
		try {
			HashMap<String,String> h = new HashMap<String,String>();
			h.put("userName", userName);
			h.put("password", password);

			userVO = userManager.authentication(h);

			if (null != userVO && null != userVO.getId()) {
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("UserVO", userVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}else{
				responseVO = createErrorResponseVO("User Id is empty");
			}
		}catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		return responseVO;	
	}
	
	@RequestMapping(value="/getAllUserForEntity",method=RequestMethod.GET)
	public ResponseVO getAllUserEnity(@RequestParam String entityId, String pageNo, String pageSize,String customerId,String deviceId)
	{
		 System.out.println("----Reached here----");
		ResponseVO responseVO = null;
	    List<UserVO> userVO = null;
	    
	    try{
	    	long totalRec = userManager.getAllPromotionCountEntity(entityId);
	    	System.out.println("----totalRecords----" + totalRec);
	    	
	    	int pg = 0;
	    	if (null != pageNo)
	    		pg = Integer.parseInt(pageNo);
	    	int pgSize = 0;
	    	if (null != pageSize)
	    		pgSize = Integer.parseInt(pageSize);	
	    	
	    	userVO = userManager.getAllUserForEntity(entityId, pg, pgSize);
	    	
	    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
	    	responseObjectsMap.put("totalRecordCount", totalRec);
	    	responseObjectsMap.put("UserVO", userVO);
	    	responseVO = createSuccessResponseVO(responseObjectsMap);
	    }catch(Throwable e){
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
	    return responseVO;
		
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ResponseVO updateUser(@RequestBody String requestVO)  {
		
		ResponseVO responseVO = null;
		try {
			UserVO userVO = (UserVO) parseObjectFromRequest(requestVO, UserVO.class);
			if (null != userVO) {
				userManager.update(userVO);

				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("UserVO", userVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	public ResponseVO deleteUser(@RequestParam String userId)  {
		
		ResponseVO responseVO = null;
		UserVO userVO = new UserVO();
		try {
			userVO.setId(userId);
			if (null != userVO) {
				userManager.delete(userVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("UserVO", userVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		return responseVO;
		
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public @ResponseBody ResponseVO changePassword(@RequestParam String userId,@RequestParam String oldPassword,
			@RequestParam String newPassword ,@RequestParam String confirmPassword){
		if (logger.isDebugEnabled()) {
			logger.debug("Start changePassword :::" + userId);
		}
		ResponseVO responseVO = null;
		UserVO userVO = new UserVO();
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			 userVO =  userManager.changePassword(userId,oldPassword,newPassword,confirmPassword);
			responseObjectMap.put("UserVO", userVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End changePassword :::" + newPassword);
		}
		return responseVO;
	}
	
	/*@RequestMapping(value = EndPointConstants.GET_USER, method = RequestMethod.GET)
	public @ResponseBody UserVO getUser(@RequestParam String id) throws BusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("Start getUser Id:::" + id);
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			UserCountVO userCountVO =  reportManager.getUsersCount();
			responseObjectMap.put("userCountVO", userCountVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		UserVO user =  userManager.getUser(id);
		
		if (logger.isDebugEnabled()) {
			logger.debug("End getUser :::" + user.getId());
		}
		return user;
	}
	*/
	@RequestMapping(value = EndPointConstants.UPDATE_USER, method = RequestMethod.POST)
	public @ResponseBody UserVO updateUser(@RequestBody UserVO user){
		if (logger.isDebugEnabled()) {
			logger.debug("Start updateUser :::" + user.getFirstName());
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			//user = userManager.updateUser(user);
			//responseObjectMap.put("userCountVO", userCountVO);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("End updateUser :::" + user.getId());
		}
		return user;	
	}	
	
	@RequestMapping(value = EndPointConstants.RESET_PASSWORD, method = RequestMethod.GET)
	public ResponseVO resetPassword(@RequestParam String userId){
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start resetPassword");
		}
		ResponseVO responseVO = null;
		Map<String,Object> responseObjectMap = new HashMap<String,Object>();
		try {
			//userManager.resetPassword(userId);
			responseObjectMap.put("isReseted", true);
			responseVO = createSuccessResponseVO(responseObjectMap);
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("End resetPassword");
		}
		return responseVO;
	}	
	
	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	

}
