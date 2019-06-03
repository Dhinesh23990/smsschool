package com.tgi.sd.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tgi.sd.dao.BloodGroupDAO;
import com.tgi.sd.domain.config.BloodGroupVO;
import com.tgi.sd.exception.SMSBusinessException;
import com.tgi.sd.util.SMSConstants;

public class BloodGroupManagerImpl implements BloodGroupManager{
	
	private static Logger logger = Logger.getLogger(BloodGroupManagerImpl.class);
	
	private BloodGroupDAO bloodGroupDAO;
	
	public BloodGroupDAO getBloodGroupDAO() {
		return bloodGroupDAO;
	}
	public void setBloodGroupDAO(BloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}
	
	@Override
	public BloodGroupVO saveBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException{
		if (logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup starts");
		}
		BloodGroupVO bloodGroup = bloodGroupDAO.getBloodGroupByName(
				bloodGroupVO.getBloodgroupName(), null,
				bloodGroupVO.getSchoolId());	
		if(bloodGroup!=null){
			throw new SMSBusinessException(SMSConstants.BLOOD_GROUP_ALREADY_EXIST);
		}
		bloodGroupVO = bloodGroupDAO.saveBloodGroup(bloodGroupVO);
		
		if (logger.isDebugEnabled()) {
			logger.debug("saveBloodGroup ends");
		}
		return bloodGroupVO;
		
	}
	@Override
	public BloodGroupVO getBloodGroupById(String bloodGroupId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById Starts");
		}
		
		BloodGroupVO bloodGroupVO = bloodGroupDAO.getBloodGroupById(bloodGroupId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupById Ends");
		}
		
		return bloodGroupVO;
	}
	
	@Override
	public Map<String, Object> getAllBloodGroupBySchoolId(String schoolId, int pageIndex,int pageSize) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Starts");
		}
		
    	Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
    	long totalRecords = bloodGroupDAO.getBloodGroupCountBySchoolId(schoolId);
		List<BloodGroupVO> bloodGroupVOs = bloodGroupDAO.getAllBloodGroupBySchoolId(schoolId, pageIndex, pageSize);
		responseObjectsMap.put(SMSConstants.TOTAL_RECORDS, totalRecords);
    	responseObjectsMap.put("bloodGroupVOs", bloodGroupVOs);
		
    	if (logger.isDebugEnabled()) {
			logger.debug("getAllBloodGroupBySchoolId Ends");
		}
		return responseObjectsMap;
	}
	
	@Override
	public long getBloodGroupCountBySchoolId(String schoolId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupCountBySchoolId Starts");
		}

		long cnt = bloodGroupDAO.getBloodGroupCountBySchoolId(schoolId);
		
		if (logger.isDebugEnabled()) {
			logger.debug("getBloodGroupCountBySchoolId Ends");
		}
		return cnt;
	}
		
	@Override
	public BloodGroupVO updateBloodGroup(BloodGroupVO bloodGroupVO) throws SMSBusinessException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Starts");
		}
		BloodGroupVO bloodGroup = bloodGroupDAO.getBloodGroupByName(
				bloodGroupVO.getBloodgroupName(), bloodGroupVO.getId(),
				bloodGroupVO.getSchoolId());
		if(bloodGroup!=null){
			throw new SMSBusinessException(SMSConstants.BLOOD_GROUP_ALREADY_EXIST);
		}
		bloodGroupVO = bloodGroupDAO.updateBloodGroup(bloodGroupVO);
			
		if (logger.isDebugEnabled()) {
			logger.debug("updateBloodGroup Ends");
		}
		return bloodGroupVO;
	}
	
	@Override
	public boolean deleteBloodGroup(String bloodGroupId) throws SMSBusinessException {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Starts");
		}
		
		boolean isDeleted = bloodGroupDAO.deleteBloodGroup(bloodGroupId);
			
		if (logger.isDebugEnabled()) {
			logger.debug("deleteBloodGroup Ends");
		}
		return isDeleted;
	}
	
}
