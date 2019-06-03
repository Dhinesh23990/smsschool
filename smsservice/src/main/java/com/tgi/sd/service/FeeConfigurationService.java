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
import com.tgi.sd.domain.fms.FeeConfigurationVO;
import com.tgi.sd.manager.FeeConfigurationManager;
import com.tgi.sd.service.base.SMSBaseService;

@RestController
@RequestMapping("/rest/feeConfiguration")
public class FeeConfigurationService extends SMSBaseService {

	private static Logger logger = Logger.getLogger(FeeConfigurationService.class);

	private FeeConfigurationManager feeConfigurationManager;

	public FeeConfigurationManager getFeeConfigurationManager() {
		return feeConfigurationManager;
	}

	public void setFeeConfigurationManager(FeeConfigurationManager feeConfigurationManager) {
		this.feeConfigurationManager = feeConfigurationManager;
	}

	@RequestMapping(value = "/saveFeeConfiguration", method = RequestMethod.POST)
	public ResponseVO saveFeeConfiguration(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration Starts");
		}
		ResponseVO responseVO = null;
		FeeConfigurationVO feeConfigurationVO = null;
		try {
			feeConfigurationVO = (FeeConfigurationVO) parseObjectFromRequest(requestVO, FeeConfigurationVO.class);
			if (null != feeConfigurationVO) {
				feeConfigurationVO = feeConfigurationManager.saveFeeConfiguration(feeConfigurationVO);
			}
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("saveFeeConfiguration Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getFeeConfigurationById", method = RequestMethod.GET)
	public ResponseVO getFeeConfigurationById(@RequestParam String feeConfigurationId) {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById Starts");
		}
		ResponseVO responseVO = null;
		FeeConfigurationVO feeConfigurationVO = null;
		try {
			feeConfigurationVO = feeConfigurationManager.getFeeConfigurationById(feeConfigurationId);
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVO);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationById Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getAllFeeConfigurationBySchoolId", method = RequestMethod.GET)
	public ResponseVO getAllFeeConfigurationBySchoolId(@RequestParam String shoolId, int pageIndex, int pageSize) {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Starts");
		}
		ResponseVO responseVO = null;
		try {

			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getAllFeeConfigurationBySchoolId(shoolId, pageIndex, pageSize);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationBySchoolId Ends");
		}
		return responseVO;

	}

	@RequestMapping(value = "/getAllFeeConfigurationByClassFeeCategory", method = RequestMethod.GET)
	public ResponseVO getAllFeeConfigurationByClassFeeCategory(@RequestParam String schoolId, String classId,
			String feeCategory) {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getAllFeeConfigurationByClassFeeCategory(schoolId, classId,
					feeCategory);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeConfigurationByClassFeeCategory Ends");
		}
		return responseVO;

	}

	/*
	 * @RequestMapping(value="/getFeeConfigurationByStudentId",method=RequestMethod.
	 * GET) public ResponseVO getFeeConfigurationByStudentId(@RequestParam String
	 * schoolId,@RequestParam String term,
	 * 
	 * @RequestParam String batchId,@RequestParam String searchString) {
	 * if(logger.isDebugEnabled()) {
	 * logger.debug("getFeeConfigurationByStudentId Starts"); } ResponseVO
	 * responseVO = null; try{ Map<String, Object> responseObjectsMap = new
	 * HashMap<String, Object>(); responseObjectsMap =
	 * feeConfigurationManager.getFeeConfigurationByStudentId(schoolId, term,
	 * batchId, searchString); responseVO =
	 * createSuccessResponseVO(responseObjectsMap); }catch(Throwable e){
	 * logger.error(e.getMessage()); responseVO =
	 * createErrorResponseVO(e.getMessage()); } if(logger.isDebugEnabled()) {
	 * logger.debug("getFeeConfigurationByStudentId Ends"); } return responseVO;
	 * 
	 * }
	 */

	

	@RequestMapping(value = "/updateFeeConfiguration", method = RequestMethod.POST)
	public ResponseVO updateFeeConfiguration(@RequestBody String requestVO) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Starts");
		}
		ResponseVO responseVO = null;
		try {
			FeeConfigurationVO feeConfigurationVO = (FeeConfigurationVO) parseObjectFromRequest(requestVO,
					FeeConfigurationVO.class);
			if (null != feeConfigurationVO) {
				feeConfigurationVO = feeConfigurationManager.updateFeeConfiguration(feeConfigurationVO);
				Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
				responseObjectsMap.put("FeeConfigurationVO", feeConfigurationVO);
				responseVO = createSuccessResponseVO(responseObjectsMap);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("updateFeeConfiguration Ends");
		}
		return responseVO;

	}

	@RequestMapping(value = "/deleteFeeConfiguration", method = RequestMethod.DELETE)
	public ResponseVO deleteFeeConfiguration(@RequestParam String feeConfigurationId) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Starts");
		}
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		ResponseVO responseVO = null;
		boolean isDeleted = false;
		try {
			isDeleted = feeConfigurationManager.deleteFeeConfiguration(feeConfigurationId);
			responseObjectsMap.put("isDeleted", isDeleted);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("deleteFeeConfiguration Ends");
		}
		return responseVO;
	}
	/*
	 * for filter service in feePaymentType
	 */

	@RequestMapping(value = "/getAllclassesByYear", method = RequestMethod.GET)

	public ResponseVO getAllclassesByYear(@RequestParam String year) {
		if (logger.isDebugEnabled()) {
			logger.debug("getAllclassesByYear Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> reaponseObjectsMap = new HashMap<String, Object>();
			reaponseObjectsMap = feeConfigurationManager.getAllclassesByYear(year);
			responseVO = createSuccessResponseVO(reaponseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAllclassesByYear Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/gettAllSectionsByYearClasses", method = RequestMethod.GET)
	public ResponseVO gettAllSectionsByYearClasses(@RequestParam String year, String classes) {
		if (logger.isDebugEnabled()) {
			logger.debug("gettAllSectionsByYearClasses Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> reaponseObjectsMap = new HashMap<String, Object>();
			reaponseObjectsMap = feeConfigurationManager.gettAllSectionsByYearClasses(year, classes);
			responseVO = createSuccessResponseVO(reaponseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("gettAllSectionsByYearClasses Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getStudentDetailsById", method = RequestMethod.GET)
	public ResponseVO getStudentDetailsById(@RequestParam String schoolId, @RequestParam String studentid,
			@RequestParam String term) {
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentDetailsById is Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getStudentDetailsById(schoolId, studentid, term);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getStudentDetailsById is Ends");
		}
		return responseVO;
	}

	@RequestMapping(value = "/getFeeConfigurationByTermId", method = RequestMethod.GET)
	public ResponseVO getFeeConfigurationByTermId(@RequestParam String term) {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByTermId is Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getFeeConfigurationByTermId(term);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByTermId is Ends");
		}

		return responseVO;
	}

	
	@RequestMapping(value = "/getFeeConfigurationByStudentId", method = RequestMethod.GET)
	public ResponseVO getFeeConfigurationByStudentId(@RequestParam String schoolId, @RequestParam String className,
			@RequestParam String batchId, @RequestParam String searchString, @RequestParam String sectionName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByStudentId Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getFeeConfigurationByStudentId(schoolId, className, batchId,
					searchString, sectionName);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByStudentId Ends");
		}
		return responseVO;

	}
	
	
	@RequestMapping(value = "/getAllFeeconfigurationDetailsByStudentId", method = RequestMethod.GET)
	public ResponseVO getAllFeeconfigurationDetailsByStudentId(@RequestParam String schoolId, @RequestParam String batchId,
			@RequestParam String classId,@RequestParam String sectionId,
			@RequestParam String searchString) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationByStudentId is Starts");
		}
		
		ResponseVO responseVO = null;
		
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getAllFeeconfigurationDetailsByStudentId(schoolId, batchId,classId,sectionId, searchString);
			responseVO = createSuccessResponseVO(responseObjectsMap);
			
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllFeeconfigurationByStudentId is Ends");
		}
		return responseVO;
	
		
	}
	
	@RequestMapping(value = "/getFeeConfigurationByStudentIdAndTerm", method = RequestMethod.GET)
	public ResponseVO getFeeConfigurationByStudentIdAndTerm(@RequestParam String schoolId,@RequestParam String batchId,@RequestParam String studentId, @RequestParam String term,String studentName) {
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByTermId is Starts");
		}
		ResponseVO responseVO = null;
		try {
			Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
			responseObjectsMap = feeConfigurationManager.getFeeConfigurationByStudentIdAndTerm(schoolId,batchId,studentId, term,studentName);
			responseVO = createSuccessResponseVO(responseObjectsMap);
		} catch (Throwable e) {
			logger.error(e.getMessage());
			responseVO = createErrorResponseVO(e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getFeeConfigurationByTermId is Ends");
		}

		return responseVO;
	}

	
}
