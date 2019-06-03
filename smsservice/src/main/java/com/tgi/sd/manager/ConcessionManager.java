 package com.tgi.sd.manager;

import java.util.Map;

import com.tgi.sd.domain.fms.ConcessionVO;
import com.tgi.sd.exception.SMSBusinessException;

public interface ConcessionManager {

	ConcessionVO saveConcession(ConcessionVO concessionVO) throws SMSBusinessException;

	ConcessionVO getConcessionById(String concessionId) throws SMSBusinessException;

	ConcessionVO updateConcession(ConcessionVO concessionVO) throws SMSBusinessException;

	boolean deleteConcession(String concessionId) throws SMSBusinessException;

	long getConcessionCountBySchoolId(String shoolId) throws SMSBusinessException;

	Map<String, Object> getAllConcessionBySchoolId(String shoolId, int pageIndex,
			int pageSize) throws SMSBusinessException;
	
	
}
