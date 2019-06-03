 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.config.CasteVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface CasteManager {

	CasteVO saveCaste(CasteVO casteVO) throws SMSBusinessException;

	CasteVO getCasteById(String casteId) throws SMSBusinessException;

	CasteVO updateCaste(CasteVO casteVO) throws SMSBusinessException;

	boolean deleteCaste(String casteId) throws SMSBusinessException;

	long getCasteCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllCasteBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
